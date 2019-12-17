package com.smile.demo.swagger.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Map;

@ApiModel(description = "此处描述会显示在前端页面上")
public class HelloSwagger {

    @ApiModelProperty(name = "id", value = "唯一标识", example = "12345566")
    private String id;

    @ApiModelProperty(name = "name", value = "名称", required = true, example = "Hello Swagger")
    private String name;

    @ApiModelProperty(name = "desc", value = "名称", required = false, example = "Hello Swagger")
    private String desc;

    @ApiModelProperty(name = "data", value = "数据体", required = true, dataType = "map", example = "{'name':'demo','version':'0.1','desc':'测试专用'}")
    private Map<String, String> data;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "HelloSwagger{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", data=" + data +
                '}';
    }
}
