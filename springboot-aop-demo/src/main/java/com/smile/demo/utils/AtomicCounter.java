package com.smile.demo.utils;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 计数器，统计当前执行的任务数
 * @author yangjunqiang
 *
 */
public class AtomicCounter {

	private static final AtomicCounter atomicCounter = new AtomicCounter();
	
	/**
	 * 单例，不允许外界主动实例化
	 */
	private AtomicCounter() { 
		
	}
	
	public static AtomicCounter getInstance() {
		return atomicCounter;
	}
	
	private static AtomicInteger counter = new AtomicInteger();
	
	public int getValue() {
		return counter.get();
	}
	
	public int increase() {
		return counter.incrementAndGet();
	}
	
	public int decrease() {
		return counter.decrementAndGet();
	}
	
}
