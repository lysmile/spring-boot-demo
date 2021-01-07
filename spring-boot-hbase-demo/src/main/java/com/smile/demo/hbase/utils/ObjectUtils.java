package com.smile.demo.hbase.utils;

import java.io.*;

/**
 * Object工具类
 * @author smile
 *
 */
public class ObjectUtils {


	/**
	 * 将object转换为byte数组
	 */
	public static byte[] toByte(Object obj) throws IOException {
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject(obj);
		oos.flush();
		byte[] bytes = bos.toByteArray();
		oos.close();
		bos.close();
		return bytes;
	}

	/**
	 * 将byte数组转换为object
	 */
	public static Object toObject(byte[] bytes) throws Exception {
		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		ObjectInputStream ois = new ObjectInputStream(bis);
		Object obj = ois.readObject();
		ois.close();
		bis.close();
		return obj;
	}
	
}
