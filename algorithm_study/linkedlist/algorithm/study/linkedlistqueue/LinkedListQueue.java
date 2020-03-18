package algorithm.study.linkedlistqueue;


/**
 * 单链表实现队列
 * @Package:algorithm.study.linkedlistqueue
 * @ClassName:LinkedListQueue
 * @Description:TODO 
 * @author:Jiangxb
 * @date:2019年3月14日 下午5:02:11
 * 
 * @param <E>
 */
public class LinkedListQueue<E> implements Queue<E> {
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
	 * 头节点
	 */
	private Node head;
	/**
	 * 尾节点
	 */
	private Node tail;
	
	/**
	 * 链表元素个数
	 */
	private int size;
	
	public LinkedListQueue(){
		head = null;
		tail = null;
		size = 0;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}

	// 从tail端插入元素（O(1)）
	@Override
	public void enqueue(E e) {
		// TODO Auto-generated method stub
		if(tail == null){
			tail = new Node(e);
			head = tail;
		}else{
			tail.next = new Node(e);
			tail = tail.next;
		}
		size ++;
	}

	// 从head端删除元素（O(1)）
	@Override
	public E dequeue() {
		// TODO Auto-generated method stub
		if(isEmpty()){
			throw new IllegalArgumentException("Cannot dequeue from an empty queue");
		}
		
		// 需要删除的节点
		Node retNode = head;
		// 删除操作
		head = head.next;
		retNode.next = null;
		// 注意：当删除最后一个节点时，需要维护下tail，
		if(head == null){
			tail = null;
		}
		size --;
		return retNode.e;
	}

	@Override
	public E getFront() {
		// TODO Auto-generated method stub
		if(isEmpty()){
			throw new IllegalArgumentException("Queue is empty");
		}
		return head.e;
	}
	
	@Override
	public String toString(){
		StringBuilder res = new StringBuilder();
		res.append("LinkedListQueue front [");
		Node cur = head;
		while(cur != null){
			res.append(cur + "->");
			cur = cur.next;
		}
		res.append("NULL ] tail");
		return res.toString();
	}
}
