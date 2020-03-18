package algorithm.study.queue;

import java.util.Random;

/**
 * 测试数组队列、循环队列、数组队列与循环队列与链表队列(linkedlist包下)的比较
 * @Package:algorithm.study.queue
 * @ClassName:Main
 * @Description:TODO 
 * @author:Jiangxb
 * @date:2019年3月14日 下午3:51:33
 *
 */
public class Main {
	public static void main(String[] args) {
//		ArrayQueue<Integer> queue = new ArrayQueue<>();
//		for (int i = 0; i < 6; i++) {
//			queue.enqueue(i);
//			System.err.println(queue);
//			// 每入队三个元素 就出队一个元素
//			if(i % 3 == 2){
//				queue.dequeue();
//				System.err.println(queue);
//			}
//		}
		
//		LoopQueue<Integer> loopQueue = new LoopQueue<>();
//		for (int i = 0; i < 10; i++) {
//			loopQueue.enqueue(i);
//			System.err.println(loopQueue);
//			// 每入队三个元素 就出队一个元素
//			if(i % 3 == 2){
//				loopQueue.dequeue();
//				System.err.println(loopQueue);
//			}
//		}
		
		// 插入10万个数
		int opCount = 100000;
		ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
		double arrayQueueTime = testQueus(arrayQueue, opCount);
		System.out.println("ArrayQueue Time:" + arrayQueueTime + " s");
		
		LoopQueue<Integer> loopQueue = new LoopQueue<>();
		double loopQueueTime = testQueus(loopQueue, opCount);
		System.out.println(" LoopQueue Time:" + loopQueueTime + " s");
		
		LinkedListQueue<Integer> linkedListQueue = new LinkedListQueue<>();
		double linkedListQueueTime = testQueus(linkedListQueue, opCount);
		System.out.println("LinkedListQueue Time:" + linkedListQueueTime + " s");
	}
	
	/**
	 * 测试使用q运行opCount个enqueue(入队) 和 dequeue(出队)操作所需要的实际；单位：秒
	 * @Title:testQueus
	 * @Description:TODO 
	 * @return:double
	 * @author:Jiangxb
	 * @date: 2019年3月14日 上午10:01:19
	 */
	private static double testQueus(Queue<Integer> q,int opCount){
		// 开始时间
		long startTime = System.nanoTime();
		
		// 随机数
		Random random = new Random();
		
		// 入队
		for (int i = 0; i < opCount; i++) {
			q.enqueue(random.nextInt(Integer.MAX_VALUE));
		}
		// 出队
		for (int i = 0; i < opCount; i++) {
			q.dequeue();
		}
		
		//结束时间
		long endTime = System.nanoTime();
		
		// 消耗时间(秒)
		return  (endTime - startTime) / 1000000000.0;
	}
}
