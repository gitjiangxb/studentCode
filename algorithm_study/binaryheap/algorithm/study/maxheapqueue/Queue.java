package algorithm.study.maxheapqueue;
/**
 * 定义队列接口
 * @Package:algorithm.study.queue
 * @ClassName:Queue
 * @Description:TODO 
 * @author:Jiangxb
 * @date:2019年3月13日 下午3:27:57
 * 
 * @param <E>
 */
public interface Queue<E> {
	/**
	 * @Title:getSize
	 * @Description:TODO 队列大小
	 * @return:int
	 * @author:Jiangxb
	 * @date: 2019年3月13日 下午3:29:40
	 */
	int getSize();
	
	/**
	 * @Title:isEmpty
	 * @Description:TODO 是否为空
	 * @return:boolean
	 * @author:Jiangxb
	 * @date: 2019年3月13日 下午3:29:47
	 */
	boolean isEmpty();
	
	/**
	 * @Title:enqueue
	 * @Description:TODO 入队
	 * @return:void
	 * @author:Jiangxb
	 * @date: 2019年3月13日 下午3:29:50
	 */
	void enqueue(E e);
	
	/**
	 * @Title:dequeue
	 * @Description:TODO 出队
	 * @return:E
	 * @author:Jiangxb
	 * @date: 2019年3月13日 下午3:29:54
	 */
	E dequeue();
	
	/**
	 * @Title:getFront
	 * @Description:TODO 队首
	 * @return:E
	 * @author:Jiangxb
	 * @date: 2019年3月13日 下午3:29:58
	 */
	E getFront();
}

