package com.smile.demo.hbase.utils;

import com.alibaba.fastjson.JSON;
import com.smile.demo.hbase.entity.DataVolume;
import com.smile.demo.hbase.entity.ScanParam;
import com.smile.demo.hbase.enums.HbaseErrorEnum;
import com.smile.demo.hbase.exception.NotExistsException;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.PageFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * hbase操作工具类
 * @author smile
 */
@Component
@Slf4j
public class HbaseClientUtils {

    private static final int CACHING_SIZE = 100;

    private final Connection connection;

    public HbaseClientUtils(Connection connection) {
        this.connection = connection;
    }


    /**
     * 创建表
     * - 只有一个列族
     * @param tableName 表名
     * @param colFamily 列族
     * @throws IOException
     */
    public void createTable(String tableName, String colFamily) throws IOException {
        TableName table = TableName.valueOf(tableName);
        try (HBaseAdmin admin = (HBaseAdmin) connection.getAdmin()) {
            if (admin.tableExists(table)) {
                log.warn("表[{}]已存在!", tableName);
                return;
            }
            ColumnFamilyDescriptor cfd = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(colFamily))
                    // 设置数据版本数量
                    .setMaxVersions(1)
                    // 设置副本数，默认是3
                    .setDFSReplication((short) 2)
                    .build();
            TableDescriptor tableDes = TableDescriptorBuilder.newBuilder(table).setColumnFamily(cfd).build();
            admin.createTable(tableDes);
        }
    }

    /**
     * 删除表
     * @param tableName 表名称
     */
    public void deleteTable(String tableName) throws IOException {
        TableName tName = TableName.valueOf(tableName);
        try (HBaseAdmin admin = (HBaseAdmin) connection.getAdmin()) {
            if (admin.tableExists(tName)) {
                admin.disableTable(tName);
                admin.deleteTable(tName);
            } else {
                log.error("表 {} 不存在!", tableName);
                return;
            }
        }
    }

    /**
     * 列出hbase中所有的表
     */
    public List<String> listTables() throws IOException {
        List<String> tables = new ArrayList<>(8);
        try (HBaseAdmin admin = (HBaseAdmin) connection.getAdmin()) {
            TableName[] tableNames = admin.listTableNames();
            for (TableName tableName : tableNames) {
                tables.add(tableName.getNameAsString());
            }
        }
        return tables;

    }

    /**
     * 判断table是否存在
     * @param tableName 表名
     */
    public boolean tableExists(String tableName) throws IOException {
        try (HBaseAdmin admin = (HBaseAdmin) connection.getAdmin()) {
            return admin.tableExists(TableName.valueOf(tableName));
        }
    }


    /**
     * 根据rowkey获取指定列数据
     * @param tableName 表名
     * @param rowkey    rowkey
     * @param colFamily 列族
     * @param cols      列
     * @return Result
     */
    public Result getData(String tableName, String rowkey, String colFamily, List<String> cols) throws IOException, NotExistsException {
        if (!tableExists(tableName)) {
            throw new NotExistsException("表[" + tableName + "]不存在");
        }
        try(Table table = connection.getTable(TableName.valueOf(tableName))) {
            Get get = new Get(Bytes.toBytes(rowkey));
            if (null != cols) {
                cols.forEach(col -> get.addColumn(Bytes.toBytes(colFamily), Bytes.toBytes(col)));
            }
            return table.get(get);
        }
    }

    /**
     * 获取列族下所有的列
     * @param tableName 表名
     * @param rowkey    rowkey
     * @param colFamily 列族
     * @return Result
     */
    public Result getData(String tableName, String colFamily, String rowkey) throws IOException, NotExistsException {
        return getData(tableName, rowkey, colFamily, null);
    }


    /**
     * 批量获取数据
     * - 左闭右开
     */
    public ResultScanner scanData(ScanParam param) throws IOException, NotExistsException {
        log.debug("ScanBaseParam ： {}", JSON.toJSON(param));
        if (!tableExists(param.getTableName())) {
            throw new NotExistsException(HbaseErrorEnum.TABLE_NOT_EXISTS.getMsg());
        }
        try (Table table = connection.getTable(TableName.valueOf(param.getTableName()))){
            Scan scan = new Scan();
            if (null != param.getReversed()) {
                scan.setReversed(param.getReversed());
            } else {
                scan.setReversed(false);
            }
            if (null != param.getStartRow()) {
                scan.withStartRow(Bytes.toBytes(param.getStartRow()));
            }
            if (null != param.getStopRow()) {
                scan.withStopRow(Bytes.toBytes(param.getStopRow()));
            }
            if (null != param.getColumns()) {
                for (String col : param.getColumns()) {
                    scan.addColumn(Bytes.toBytes(param.getColFamily()), Bytes.toBytes(col));
                }
            }
            if (0 != param.getPageSize()) {
                PageFilter pageFilter = new PageFilter(param.getPageSize());
                scan.setFilter(pageFilter);
            }
            scan.setCaching(CACHING_SIZE);
            return table.getScanner(scan);
        }
    }

    /**
     * 批量获取数据
     * - 左闭右开
     * - 过滤器
     */
    public ResultScanner scanDataByFilters(ScanParam param, List<Filter> filters) throws IOException, NotExistsException {
        if (!tableExists(param.getTableName())) {
            throw new NotExistsException(HbaseErrorEnum.TABLE_NOT_EXISTS.getMsg());
        }
        try (Table table = connection.getTable(TableName.valueOf(param.getTableName()))) {
            Scan scan = new Scan();
            scan.withStartRow(Bytes.toBytes(param.getStartRow()));
            scan.withStopRow(Bytes.toBytes(param.getStopRow()));
            for (String col : param.getColumns()) {
                scan.addColumn(Bytes.toBytes(param.getColFamily()), Bytes.toBytes(col));
            }
            filters.add(new PageFilter(param.getPageSize()));
            FilterList filterList = new FilterList(filters);
            scan.setFilter(filterList);
            scan.setCaching(CACHING_SIZE);
            return table.getScanner(scan);
        }

    }



    /**
     * 插入单行数据
     */
    public void put(String tableName, String colFamily, String rowkey, Map<String, String> data) throws NotExistsException, IOException{
        if (!tableExists(tableName)) {
            throw new NotExistsException(HbaseErrorEnum.TABLE_NOT_EXISTS.getMsg());
        }
        try (Table table = connection.getTable(TableName.valueOf(tableName))){
            Put put = new Put(Bytes.toBytes(rowkey));
            for (Map.Entry<String, String> entry : data.entrySet()) {
                put.addColumn(Bytes.toBytes(colFamily), Bytes.toBytes(entry.getKey()), Bytes.toBytes(entry.getValue()));
            }
            table.put(put);
        }
    }

    /**
     * 批量插入
     */
    public void batchPut(String tableName, String colFamily, List<DataVolume> dataVolumes) throws NotExistsException, IOException{
        if (!tableExists(tableName)) {
            throw new NotExistsException(HbaseErrorEnum.TABLE_NOT_EXISTS.getMsg());
        }
        try (Table table = connection.getTable(TableName.valueOf(tableName))){
            List<Put> puts = new ArrayList<>();
            for(DataVolume dataVolume : dataVolumes) {
                Put put = new Put(Bytes.toBytes(dataVolume.getRowkey()));
                for (Map.Entry<String, String> entry : dataVolume.getData().entrySet()) {
                    put.addColumn(Bytes.toBytes(colFamily), Bytes.toBytes(entry.getKey()), ObjectUtils.toByte(entry.getValue()));
                }
                puts.add(put);
            }
            table.put(puts);
        }
    }

    /**
     * 将扫描的结果拼装成map
     */
    public Map<String, String> buildResultMap(Result result) {
        Map<String, String> map = new HashMap<>(1);
        if (result.isEmpty()) {
            log.warn("查询结果为空");
            return null;
        }
        result.listCells().forEach(cell -> {
            map.put(new String(CellUtil.cloneQualifier(cell)), new String(CellUtil.cloneValue(cell)));
        });
        return map;
    }

    /**
     * 拼接list
     * - 用于分页
     */
    public List<Map<String, String>> createResults(ResultScanner resultScanner, int count) {
        List<Map<String, String>> results = new ArrayList<>();
        int num = 0;
        for (Result result : resultScanner) {
            results.add(buildResultMap(result));
            num += 1;
            if (num == count) {
                break;
            }
        }
        return results;
    }
}
