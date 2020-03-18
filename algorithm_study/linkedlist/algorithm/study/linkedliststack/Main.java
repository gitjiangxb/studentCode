package algorithm.study.linkedliststack;

/**
 * 测试 链表实现的栈
 * @Package:algorithm.study.linkedliststack
 * @ClassName:Main
 * @Description:TODO 
 * @author:Jiangxb
 * @date:2019年3月14日 下午3:43:29
 *
 */
public class Main {
	public static void main(String[] args) {
		LinkedListStack<Integer> linkedListStack = new LinkedListStack<>();
		
		for (int i = 0; i < 5; i++) {
			linkedListStack.push(i);
			System.out.println(linkedListStack);
		}
		
		linkedListStack.pop();
		System.out.println(linkedListStack);
	}
}
