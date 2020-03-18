package algorithm.study.stack;

/**
 * 定义栈接口
 * @Package:algorithm.study.stack
 * @ClassName:Stack
 * @Description:TODO 
 * @author:Jiangxb
 * @date:2019年3月13日 上午10:17:00
 * 
 * @param <E>
 */
public interface Stack<E> {
	/**
	 * @Title:getSize
	 * @Description:TODO 得到栈的大小
	 * @return:int
	 * @author:Jiangxb
	 * @date: 2019年3月13日 上午10:18:13
	 */
	int getSize();
	/**
	 * @Title:isEmpty
	 * @Description:TODO 判断栈是否为空
	 * @return:boolean
	 * @author:Jiangxb
	 * @date: 2019年3月13日 上午10:18:27
	 */
	boolean isEmpty();
	/**
	 * @Title:push
	 * @Description:TODO 入栈
	 * @return:void
	 * @author:Jiangxb
	 * @date: 2019年3月13日 上午10:18:38
	 */
	void push(E e);
	/**
	 * @Title:pop
	 * @Description:TODO 出栈
	 * @return:E
	 * @author:Jiangxb
	 * @date: 2019年3月13日 上午10:18:56
	 */
	E pop();
	
	/**
	 * @Title:peek
	 * @Description:TODO 查看栈顶的元素
	 * @return:E
	 * @author:Jiangxb
	 * @date: 2019年3月13日 上午10:19:34
	 */
	E peek();
}
