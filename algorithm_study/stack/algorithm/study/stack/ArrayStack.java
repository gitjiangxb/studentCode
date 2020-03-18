package algorithm.study.stack;

/**
 * 基于【动态数组】实现的栈
 * @Package:algorithm.study.stack
 * @ClassName:ArrayStack
 * @Description:TODO 
 * @author:Jiangxb
 * @date:2019年3月13日 上午10:21:17
 * 	各方法的时间复杂度都是 O(1)
 * @param <E>
 */
public class ArrayStack<E> implements Stack<E> {
	
	/**
	 * 基于动态数组的成员变量
	 */
	private Array<E> array;
	
	/**
	 * 带容积的构造函数
	 * @Title:ArrayStack
	 * @param capacity
	 */
	public ArrayStack(int capacity) {
		array = new Array<>(capacity);
	}
	
	/**
	 * 默认无参构造函数
	 * @Title:ArrayStack
	 */
	public ArrayStack() {
		array = new Array<>();
	}
	
	
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return array.getSize();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return array.isEmpty();
	}

	@Override
	public void push(E e) {
		// TODO Auto-generated method stub
		array.addLast(e);
	}

	@Override
	public E pop() {
		// TODO Auto-generated method stub
		return array.removeLast();
	}

	@Override
	public E peek() {
		// TODO Auto-generated method stub
		return array.getLast();
	}
	
	/**
	 * @Title:getCapacity
	 * @Description:TODO 独有的方法，不是Stack接口的。得到容积
	 * @return:int
	 * @author:Jiangxb
	 * @date: 2019年3月13日 上午10:28:44
	 */
	public int getCapacity(){
		return array.getCapacity();
	}
	
	@Override
	public String toString(){
		StringBuilder res = new StringBuilder();
		res.append("Stack：");
		res.append('[');
		for (int i = 0; i < array.getSize(); i++) {
			res.append(array.get(i));
			if(i != array.getSize()-1){
				res.append(", ");
			}
		}
		// 右侧为栈顶
		res.append("] top");	
		return res.toString();
	}

}
