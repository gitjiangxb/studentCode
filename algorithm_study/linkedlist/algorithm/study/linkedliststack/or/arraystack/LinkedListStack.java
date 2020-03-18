package algorithm.study.linkedliststack.or.arraystack;
/**
 * 基于【链表】实现的栈
 * @Package:algorithm.study.linkedliststack
 * @ClassName:LinkedListStack
 * @Description:TODO 
 * @author:Jiangxb
 * @date:2019年3月14日 下午3:20:08
 * 
 * @param <E>
 */
public class LinkedListStack<E> implements Stack<E> {
	/**
	 * 定义链表对象
	 */
	private LinkedList<E> list;
	
	public LinkedListStack(){
		list = new LinkedList<>();
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return list.getSize();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return list.isEmpty();
	}

	@Override
	public void push(E e) {
		// TODO Auto-generated method stub
		// 入栈 == 链表头插入元素
		list.addFirst(e);
	}

	@Override
	public E pop() {
		// TODO Auto-generated method stub
		// 出栈 == 链表头删除元素
		return list.removeFrist();
	}

	@Override
	public E peek() {
		// TODO Auto-generated method stub
		// 栈顶 == 链表头节点
		return list.getFirst();
	}
	
	@Override
	public String toString(){
		StringBuilder res = new StringBuilder();
		res.append("LinkedListStack: top ");
		res.append(list);
		return res.toString();
	}
	
}
