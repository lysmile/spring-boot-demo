package com.smile.demo.springbootfiledemo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * 文件操作工具类
 * @author yangjunqiang
 */
public class MyFileUtils {

	private static Logger logger = LoggerFactory.getLogger(MyFileUtils.class);

	// 不需要实例化
	private MyFileUtils(){ }

	/**
	 * 保存文件
	 * @param fileSavePath
	 * @param fileName
	 * @param bytes
	 * @return
	 */
	public static void saveFile(String fileSavePath, String fileName, byte[] bytes) throws IOException{

		createDir(fileSavePath);

		File file = new File(fileSavePath + fileName);
		BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
		outputStream.write(bytes);
		outputStream.close();

	}


	/**
	 * 保存文件
	 * @param multipartFile
	 * @param filePath
	 * @throws IOException
	 */
	public static void saveFile(MultipartFile multipartFile, String filePath) throws IOException{
		String fileName =multipartFile.getOriginalFilename();
		byte[] bytes = multipartFile.getBytes();
		saveFile(filePath, fileName, bytes);
	}


	/**
	 * 创建目录，如果已经存在则忽略
	 * @param filePath
	 */
	public static void createDir(String filePath){

		File file = new File(filePath);
		if(!file.exists()){
			file.mkdir();
		}else {
			logger.debug("{} 已存在", filePath);
		}
	}


}
