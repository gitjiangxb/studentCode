package algorithm.study.maxheap;

import java.util.Iterator;
import java.util.Random;

/**
 * 测试 【数组】实现最大堆
 * @Package:algorithm.study.maxheap
 * @ClassName:Main
 * @Description:TODO 
 * @author:Jiangxb
 * @date:2019年3月28日 上午9:51:34
 *
 */
public class Main {
	/**
	 * @Title:testHeap
	 * @Description:TODO 验证Heapify过程 与 add逐个添加的性能
	 * @return:double
	 * @author:Jiangxb
	 * @date: 2019年3月29日 上午11:26:42
	 * 	isHeapify为true ：   Heapify过程
	 * 	isHeapify为false ： add逐个添加过程
	 */
	private static double testHeap(Integer[] testData,boolean isHeapify){
		long startTime = System.nanoTime();
		MaxHeap<Integer> maxHeap;
		if(isHeapify){
			maxHeap = new MaxHeap<>(testData);
		}else{
			maxHeap = new MaxHeap<>();
			for (Integer num : testData) {
				maxHeap.add(num);
			}
		}
		/**
		 * 验证堆的正确性		
		 */
		// 保存出堆的元素
		int[] pop = new int[testData.length];
		for (int i = 0; i < testData.length; i++) {
			pop[i] = maxHeap.extractMax();
		}
		
		// 进行校验(pop数组，应该是从大到小排列的)
		for (int i = 1 ; i < testData.length ; i ++) {
			if(pop[i-1] < pop[i]){
				System.out.println("Error");
			}
		}
		System.out.println("Test MaxHeap completed.");
		
		long entTime = System.nanoTime();
		return (entTime - startTime) / 1000000000.0;
	}
	
	public static void main(String[] args){
		int n = 1000000;
		Random random = new Random();
		Integer[] testData = new Integer[n];
		// 往测试数组中插入n个随机数(0-最大值)
		for (int i = 0; i < n; i++) {
			testData[i] = random.nextInt(Integer.MAX_VALUE);
		}
		
		double time1 = testHeap(testData, false);
		System.out.println("Without heapify: " + time1 + " s");
		
		double time2 = testHeap(testData, true);
		System.out.println("With heapify: " + time2 + " s");
		
	}
}
