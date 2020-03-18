package com.zk.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * 继承抽象类，根据不同的调度站。实现不同的check()方法
 */
public class StationBeijingIMooc extends DangerCenter {

	public StationBeijingIMooc(CountDownLatch countDown) {
		super(countDown, "北京慕课调度站");
	}

	@Override
	public void check() {
		System.out.println("正在检查 [" + this.getStation() + "]...");
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("检查 [" + this.getStation() + "] 完毕，可以发车~");
	}

}
