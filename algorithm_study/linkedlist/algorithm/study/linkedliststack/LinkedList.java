package algorithm.study.linkedliststack;


/**
 * 链表(带虚拟头节点dummyHead)
 * @Package:algorithm.study.linkedlist
 * @ClassName:LinkedList
 * @Description:TODO 
 * @author:Jiangxb
 * @date:2019年3月14日 上午10:41:08
 * 
 * @param <E>
 */
public class LinkedList<E> {
	
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
			this.next = node;
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
	 * 虚拟头节点(为null，指向真实的头节点)
	 */
	private Node dummyHead;
	/**
	 * 链表中的元素个数
	 */
	int size;
	
	public LinkedList(){
		dummyHead = new Node(null,null);
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
		// 先将虚拟头节点赋值给prev
		Node prev = dummyHead;
		// 循环次数=指定位置；因为prev目前指向的是 虚拟头接口(null)
		for (int i = 0; i < index; i++) {
			prev = prev.next;	// 待添加位置的前一个节点
		}
		// 生成一个node节点
		Node node = new Node(e);
		// 添加操作(需要先指向 下一个节点，要不然会造成数据丢失)
		node.next = prev.next;
		prev.next = node;
		// 简化上面三句
//		prev.next = new Node(e,prev.next);
		// 维护 链表数量
		size ++;
	}
	
	/**
	 * @Title:addFirst
	 * @Description:TODO 在链表头添加元素
	 * @return:void
	 * @author:Jiangxb
	 * @date: 2019年3月14日 上午11:34:29
	 */
	public void addFirst(E e){
		add(0,e);
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
	
	/**
	 * @Title:get
	 * @Description:TODO 获取链表的元素
	 * @return:E
	 * @author:Jiangxb
	 * @date: 2019年3月14日 下午2:06:10
	 * 说明：获得链表的第index(0-based)个位置的元素
	 * 	在链表中不是一个常用操作，练习用
	 */
	public E get(int index){
		// 索引的判断
		if(index < 0 || index > size){
			throw new IllegalArgumentException("Get failed.Illegal index.");
		}
		// 获取当前链表真实的第一个元素(头节点)
		Node cur = dummyHead.next;
		for(int i=0 ; i < index ; i ++){
			cur = cur.next;
		}
		// 返回节点 里的元素
		return cur.e;
	}
	
	/**
	 * @Title:getFirst
	 * @Description:TODO 获得链表的第一个元素
	 * @return:E
	 * @author:Jiangxb
	 * @date: 2019年3月14日 下午2:10:13
	 */
	public E getFirst(){
		return get(0);
	}
	
	/**
	 * @Title:getLast
	 * @Description:TODO 获得链表的最后一个元素
	 * @return:E
	 * @author:Jiangxb
	 * @date: 2019年3月14日 下午2:10:27
	 */
	public E getLast(){
		return get(size - 1);
	}
	
	/**
	 * @Title:set
	 * @Description:TODO 更新链表元素
	 * @return:void
	 * @author:Jiangxb
	 * @date: 2019年3月14日 下午2:13:54
	 * 说明：修改链表的第index(0-based)个位置的元素为e
	 * 	在链表中不是一个常用的操作，练习用
	 */
	public void set(int index,E e){
		// 索引的判断
		if(index < 0 || index > size){
			throw new IllegalArgumentException("Get failed.Illegal index.");
		}
		
		Node cur = dummyHead.next;
		for(int i=0 ; i < index ; i ++){
			cur = cur.next;
		}
		cur.e = e;
	}
	
	/**
	 * @Title:contains
	 * @Description:TODO 查找链表中是否有元素e
	 * @return:boolean
	 * @author:Jiangxb
	 * @date: 2019年3月14日 下午2:16:48
	 */
	public boolean contains(E e){
		Node cur = dummyHead.next;
		// cur 为空的时候退出
		while(cur != null){
			if(cur.e.equals(e)){
				return true;
			}
			cur = cur.next;
		}
		return false;
	}
	
	/**
	 * @Title:remove
	 * @Description:TODO 删除链表中的元素
	 * @return:E
	 * @author:Jiangxb
	 * @date: 2019年3月14日 下午2:31:50
	 * 说明：从链表中删除index(0-based)位置的元素，返回删除的元素
	 * 	在链表中不是一个常用的操作，练习用
	 * 关键所在：找到要添加的节点的前一个节点(prev)
	 */
	public E remove(int index){
		// 索引的判断
		if(index < 0 || index > size){
			throw new IllegalArgumentException("Remove failed.Illegal index.");
		}
		
		Node prev = dummyHead;
		// 得到待删除位置的前一个节点
		for (int i = 0; i < index; i++) {
			prev = prev.next;
		}
		// 得到待删除节点
		Node retNode = prev.next;
		// 删除的操作
		prev.next = retNode.next;
		retNode.next = null;
		// 维护链表数量
		size --;
		return retNode.e;
	}
	
	/**
	 * @Title:removeFrist
	 * @Description:TODO 删除链表的头节点
	 * @return:E
	 * @author:Jiangxb
	 * @date: 2019年3月14日 下午2:37:05
	 */
	public E removeFrist(){
		return remove(0);
	}
	
	/**
	 * @Title:removeLast
	 * @Description:TODO 删除链表的尾节点
	 * @return:E
	 * @author:Jiangxb
	 * @date: 2019年3月14日 下午2:37:17
	 */
	public E removeLast(){
		return remove(size - 1);
	}
	
	@Override
	public String toString(){
		StringBuilder res = new StringBuilder();
		Node cur = dummyHead.next;
		while(cur != null){
			res.append(cur + "->");
			cur = cur.next;
		}
		
		// 同等意义
//		for(Node cur = dummyHead.next ; cur != null ; cur = cur.next){
//			res.append(cur + "->");
//		}
		
		res.append("NULL");
		return res.toString();
	}
}