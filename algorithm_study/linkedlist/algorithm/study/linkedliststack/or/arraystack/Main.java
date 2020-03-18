package algorithm.study.linkedliststack.or.arraystack;

import java.util.Random;

/**
 * 自定义动态数组实现的栈 对比 链表实现的栈
 * @Package:algorithm.study.arraystack
 * @ClassName:Main
 * @Description:TODO 
 * @author:Jiangxb
 * @date:2019年3月14日 下午3:42:45
 * 
 */
public class Main {
	public static void main(String[] args) {
		// 插入10万个数
		int opCount = 100000;
		
		ArrayStack<Integer> arrayStack = new ArrayStack<>();
		double arrayStackTime = testStack(arrayStack, opCount);
		System.out.println("ArrayStack Time:" + arrayStackTime + " s");
		
		LinkedListStack<Integer> linkedListStack = new LinkedListStack<>();
		double linkedListStackTime = testStack(linkedListStack, opCount);
		System.out.println("LinkedListStack Time:" + linkedListStackTime + " s");
	}
	
	/**
	 * 测试使用stack运行opCount个push(入栈) 和 pop(出栈)操作所需要的实际；单位：秒
	 * @Title:testStack
	 * @Description:TODO 
	 * @return:double
	 * @author:Jiangxb
	 * @date: 2019年3月14日 下午3:35:05
	 */
	private static double testStack(Stack<Integer> stack,int opCount){
		// 开始时间
		long startTime = System.nanoTime();
		
		// 随机数
		Random random = new Random();
		
		// 入栈
		for (int i = 0; i < opCount; i++) {
			stack.push(random.nextInt(Integer.MAX_VALUE));
		}
		// 出栈
		for (int i = 0; i < opCount; i++) {
			stack.pop();
		}
		
		//结束时间
		long endTime = System.nanoTime();
		
		// 消耗时间(秒)
		return  (endTime - startTime) / 1000000000.0;
	}
}
