package com.zk.countdownlatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 总部监控中心
 */
public class CheckStartUp {

	// 所有监控站点的列表
	private static List<DangerCenter> stationList;
	private static CountDownLatch countDown;
	
	public CheckStartUp() {
	}
	
	public static boolean checkAllStations() throws Exception {

		// 初始化3个调度站
		countDown = new CountDownLatch(3);

		/**
		 * 把所有站点添加进list
		 * TODO 三个调度站的countDown必须是同一个，因为要做到累减
		 */

		stationList = new ArrayList<>();
		stationList.add(new StationBeijingIMooc(countDown));
		stationList.add(new StationJiangsuSanling(countDown));
		stationList.add(new StationShandongChangchuan(countDown));
		
		// 使用线程池(线程数量为调度站个数)
		Executor executor = Executors.newFixedThreadPool(stationList.size());
		
		for (DangerCenter center : stationList) {
			// 执行
			executor.execute(center);
		}

		/**
		 * 等待线程执行完毕
		 * TODO 当子线程执行完成后，在finnaly块调用countDown()方法，表示一个等待已经完成，把计数器减一，直到减为 0，主线程又开始执行。
		 */
		countDown.await();
		
		for (DangerCenter center : stationList) {
			if (!center.isOk()) {
				/**
				 * 可做更多的操作，比如哪个调度站出现了什么问题等等
				 */
				return false;
			}
		}
		
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		boolean result = CheckStartUp.checkAllStations();
		System.out.println("监控中心针对所有危化品调度站点的检查结果为：" + result);
	}

}
