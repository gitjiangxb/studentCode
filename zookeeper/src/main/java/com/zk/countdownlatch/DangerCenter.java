package com.zk.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * 抽象类，用于演示 危险品化工车监控中心 统一检查
 * 线程，需要重写run()
 * TODO CountDownLatch应用场景
 * 1.一个线程的执行需要等到其他的线程执行完毕后才能执行
 * 2.多个线程在某一时刻同时执行
 */
public abstract class DangerCenter implements Runnable {

	private CountDownLatch countDown;		// 计数器
	private String station;					// 调度站
	private boolean ok;						// 调度站针对当前自己的站点进行检查，是否检查ok的标志
	
	public DangerCenter(CountDownLatch countDown, String station) {
		this.countDown = countDown;
		this.station = station;
		this.ok = false;
	}

	@Override
	public void run() {
		try {
			check();	// 检查危化品车(下面的抽象方法)
			// 如果检查完成，没有意外，就设置为 true
			ok = true;
		} catch (Exception e) {
			e.printStackTrace();
			ok = false;
		} finally {
			// 当我们check完毕后，会继续一个累减
			if (countDown != null) {
				countDown.countDown();
			}
		}
	}

	/**
	 * 检查危化品车
	 * 蒸罐
	 * 汽油
	 * 轮胎
	 * gps
	 * ...
	 */
	public abstract void check();

	public CountDownLatch getCountDown() {
		return countDown;
	}
	public void setCountDown(CountDownLatch countDown) {
		this.countDown = countDown;
	}
	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station = station;
	}
	public boolean isOk() {
		return ok;
	}
	public void setOk(boolean ok) {
		this.ok = ok;
	}
	
}
