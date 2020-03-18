package algorithm.study.linkedlistqueue;
/**
 * 测试 链表实现队列
 * @Package:algorithm.study.linkedlistqueue
 * @ClassName:Main
 * @Description:TODO 
 * @author:Jiangxb
 * @date:2019年3月15日 下午1:42:13
 *
 */
public class Main {
	public static void main(String[] args) {
		LinkedListQueue<Integer> linkedListQueue = new LinkedListQueue<>();
		
		for (int i = 0; i < 10; i++) {
			linkedListQueue.enqueue(i);
			System.out.println(linkedListQueue);
			// 每入队三个元素 就出队一个元素
			if(i % 3 == 2){
				linkedListQueue.dequeue();
				System.err.println(linkedListQueue);
			}
		}
	}
}
