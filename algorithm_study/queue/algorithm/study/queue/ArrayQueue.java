package algorithm.study.queue;
/**
 * 数组队列——基于自定义的【动态数组】实现
 * @Package:algorithm.study.queue
 * @ClassName:ArrayQueue
 * @Description:TODO 
 * @author:Jiangxb
 * @date:2019年3月13日 下午3:32:24
 * 
 * @param <E>
 */
public class ArrayQueue<E> implements Queue<E> {
	/**
	 * 基于动态数组的成员变量
	 */
	private Array<E> array;
	
	public ArrayQueue(int capacity) {
		array = new Array<>(capacity);
	}
	
	public ArrayQueue() {
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
	public void enqueue(E e) {
		// TODO Auto-generated method stub
		array.addLast(e);
	}

	/**
	 * 出队（时间复杂度O(n),其他O(1)）
	 */
	@Override
	public E dequeue() {
		// TODO Auto-generated method stub
		return array.removeFirst();
	}

	@Override
	public E getFront() {
		// TODO Auto-generated method stub
		return array.getFirst();
	}
	
	public int getCatacity(){
		return array.getCapacity();
	}
	
	@Override
	public String toString(){
		StringBuilder res = new StringBuilder();
		res.append("Queue：");
		// 队首
		res.append("front [");
		for (int i = 0; i < array.getSize(); i++) {
			res.append(array.get(i));
			if(i != array.getSize()-1){
				res.append(", ");
			}
		}
		res.append("] tail");	
		return res.toString();
	}
	
}
