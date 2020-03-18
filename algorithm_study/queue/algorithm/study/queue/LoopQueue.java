package algorithm.study.queue;
/**
 * 循环队列——基于java数组实现
 * @Package:algorithm.study.queue
 * @ClassName:LoopQueue
 * @Description:TODO 
 * @author:Jiangxb
 * @date:2019年3月13日 下午4:24:50
 *	front == tail 队列为空
 *	(tail + 1) % capacity == front 队列满
 */
public class LoopQueue<E> implements Queue<E>{
	/**
	 * 数组变量；capacity(容量)=data数组的长度
	 */
	private E[] data;
	
	/**
	 * 队首所在的是索引
	 */
	private int front;
	
	/**
	 * 队列最后一个元素的下一个位置，也就是新元素入队时的索引。
	 */
	private int tail;
	
	/**
	 * 实际的元素个数
	 */
	private int size;
	
	public LoopQueue(int capacity){
		// 对于循环数组要有意识得浪费一个容量
		data = (E[]) new Object[capacity + 1];
		front = 0;
		tail = 0;
		size = 0;
	}
	
	public LoopQueue(){
		this(10);
	}
	
	/**
	 * @Title:getCapacity
	 * @Description:TODO 得到容量
	 * @return:int
	 * @author:Jiangxb
	 * @date: 2019年3月13日 下午4:46:32
	 */
	public int getCapacity(){
		return data.length - 1;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return front == tail;
	}

	@Override
	public void enqueue(E e) {
		// TODO Auto-generated method stub
		// 判断队列是否满
		if((tail + 1) % data.length == front){
			resize(getCapacity() * 2);
		}
		// 添加元素
		data[tail] = e;
		// 维护下一个元素所处的索引位置
		tail = (tail + 1) % data.length;
		size ++;
	}

	/**
	 * @Title:resize
	 * @Description:TODO 扩容
	 * @return:void
	 * @author:Jiangxb
	 * @date: 2019年3月13日 下午4:51:54
	 */
	private void resize(int newCapacity){
		// 新的数组变量(中转)
		E[] newData = (E[])new Object[newCapacity + 1];
		for (int i = 0; i < size; i++) {
			/**
			 * 说明：若当前队首的索引不在0的位置上，那么newData[0] = data[队首的索引]；
			 * 	因为是循环，因此 i+队首的索引，模上data.lenth 为的是防止下标越界。
			 */
			newData[i] = data[(i + front) % data.length];
		}
		data = newData;
		// 维护队首所在的索引位置
		front = 0;
		tail = size;
	}
	
	/**
	 * （所有方法时间复杂度O(1)）
	 */
	@Override
	public E dequeue() {
		// TODO Auto-generated method stub
		if(isEmpty()){
			throw new IllegalArgumentException("Cannot dequeue from an empty queue");
		}
		// 得到队首
		E ret = data[front];
		// 队首赋值为null，启动GC
		data[front] = null;
		// 维护队首所在的索引位置（本来应该直接front++，但是由于在循环队列中）
		front = (front + 1) % data.length;
		size --;
		
		// 当出队到实际元素数量 等于 容量的四分之一时，缩容二分之一
		if(size == getCapacity() / 4 && getCapacity() / 2 != 0){
			resize(getCapacity() / 2);
		}
		
		return ret;
	}

	@Override
	public E getFront() {
		// TODO Auto-generated method stub
		if(isEmpty()){
			throw new IllegalArgumentException("Queue is empty");
		}
		return data[front];
	}
	
	@Override
	public String toString(){
		StringBuilder res = new StringBuilder();
		res.append(String.format("LoopQueue:size = %d,capatity=%d\n", size,getCapacity()));
		res.append("front [");
		// 循环遍历
		for(int i = front; i != tail ;i = (i + 1) % data.length){
			res.append(data[i]);
			// 判断i是否是最后一个元素
			if((i + 1) % data.length != tail){
				res.append(", ");
			}
		}
		res.append("] tail");
		return res.toString();
	}
}
