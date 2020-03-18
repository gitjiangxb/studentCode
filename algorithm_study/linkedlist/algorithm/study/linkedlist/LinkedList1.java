package algorithm.study.linkedlist;


/**
 * 链表
 * @Package:algorithm.study.linkedlist
 * @ClassName:LinkedList
 * @Description:TODO 
 * @author:Jiangxb
 * @date:2019年3月14日 上午10:41:08
 * 
 * @param <E>
 */
public class LinkedList1<E> {
	
	// 私有节点类(内部类)
	private class Node{
		/**
		 * 存储的数据对象(真正的数据)
		 */
		public E e;
		/**
		 * 下一个节点对象的引用
		 */
		public Node next;
		
		public Node(E e,Node node){
			this.e = e;
			this.next = next;
		}
		
		public Node(E e){
			this(e,null);
		}
		
		public Node(){
			this(null,null);
		}
		
		@Override
		public String toString(){
			return e.toString();
		}
	}
	
	/**
	 * 头节点
	 */
	private Node head;
	/**
	 * 链表中的元素个数
	 */
	int size;
	
	public LinkedList1(){
		head = null;
		size = 0;
	}
	
	/**
	 * @Title:getSize
	 * @Description:TODO 获取链表中的元素个数
	 * @return:int
	 * @author:Jiangxb
	 * @date: 2019年3月14日 上午10:47:20
	 */
	public int getSize(){
		return size;
	}
	
	/**
	 * @Title:isEmpty
	 * @Description:TODO 返回链表是否为空
	 * @return:boolean
	 * @author:Jiangxb
	 * @date: 2019年3月14日 上午10:48:01
	 */
	public boolean isEmpty(){
		return size == 0;
	}
	
	/**
	 * @Title:addFirst
	 * @Description:TODO 在链表头添加元素
	 * @return:void
	 * @author:Jiangxb
	 * @date: 2019年3月14日 上午10:51:25
	 */
	public void addFirst(E e){
		// 生成一个node节点
		Node node = new Node(e);
		// 新节点指向原来的头节点
		node.next = head;
		// 维护 头节点
		head = node;
		// 简化上面三行代码
//		head = new Node(e,head);
		
		// 维护 链表数量
		size ++;
	}
	
	/**
	 * @Title:add
	 * @Description:TODO 在指定位置插入元素
	 * @return:void
	 * @author:Jiangxb
	 * @date: 2019年3月14日 上午11:05:59
	 * 说明：
	 * 	在链表的index(0-based)位置添加新的元素e
	 *  在链表中不是一个常用的操作，练习用
	 *  关键所在：找到要添加的节点的前一个节点(prev)
	 */
	public void add(int index,E e){
		// 索引的判断
		if(index < 0 || index > size){
			throw new IllegalArgumentException("Add failed.Illegal index.");
		}
		
		if(index == 0){
			// 如果指定位置为0，调用链表头插入方法
			addFirst(e);	// 对链表头的操作可以简化：为链表设立虚拟头节点(dummyHead)
		}else{
			// 先将头节点赋值给prev
			Node prev = head;
			// 循环次数=指定位置-1；因为prev需要指定到，指定位置的前一个
			for (int i = 0; i < index -1; i++) {
				prev = prev.next;
			}
			// 生成一个node节点
			Node node = new Node(e);
			// 中途挂接(需要先指向 下一个节点，要不然会造成数据丢失)
			node.next = prev.next;
			prev.next = node;
			// 简化上面三句
//			prev.next = new Node(e,prev.next);
			// 维护 链表数量
			size ++;
		}
	}
	
	/**
	 * @Title:addLast
	 * @Description:TODO 在链表尾部添加元素
	 * @return:void
	 * @author:Jiangxb
	 * @date: 2019年3月14日 上午11:22:17
	 */
	public void addLast(E e){
		add(size,e);
	}
	
	
}