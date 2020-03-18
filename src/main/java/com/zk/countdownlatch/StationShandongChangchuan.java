package com.zk.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * 继承抽象类，根据不同的调度站。实现不同的check()方法
 */
public class StationShandongChangchuan extends DangerCenter {

	public StationShandongChangchuan(CountDownLatch countDown) {
		super(countDown, "山东长川调度站");
	}

	@Override
	public void check() {
		System.out.println("正在检查 [" + this.getStation() + "]...");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("检查 [" + this.getStation() + "] 完毕，可以发车~");
	}

}
