package algorithm.study.linkedlistmap;

import algorithm.study.map.Map;

/**
 * 基于【链表】实现 映射类Map
 * @Package:algorithm.study.linkedlistmap
 * @ClassName:LinkedListMap
 * @Description:TODO 
 * @author:Jiangxb
 * @date:2019年3月25日 下午5:04:36
 * 
 * @param <K>
 * @param <V>
 */ 
public class LinkedListMap<K,V> implements Map<K, V> {
	private class Node{
		public K key;
		public V value;
		public Node next;
		
		public Node(K key,V value,Node next){
			this.key = key;
			this.value = value;
			this.next = next;
		}
		
		public Node(){
			this(null,null,null);
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return key.toString() + " : " + value.toString();
		}
	}
	// 虚拟头节点(为null，指向真实的头节点)
	private Node dummyHead;
	private int size;
	
	public LinkedListMap(){
		dummyHead = new Node();
		size = 0;
	}

	/**
	 * @Title:getNode
	 * @Description:TODO 通过key得到Map
	 * @return:Node
	 * @author:Jiangxb
	 * @date: 2019年3月25日 下午5:19:55
	 */
	private Node getNode(K key){
		Node cur = dummyHead.next;
		// 遍历循环链表(找到key对应的Node对象)，直到为空时退出。
		while(cur != null){
			if(cur.key.equals(key)){
				return cur;
			}
			cur = cur.next;
		}
		return null;	// 表示不存在这个key的Map
	}
	
	// key 唯一
	@Override
	public void add(K key, V value) {
		// TODO Auto-generated method stub
		Node node = getNode(key);
		if(node == null){	// 表示不存在key
			dummyHead.next = new Node(key, value, dummyHead.next);
			size ++;
		}else{
			// 若存在key后，则替换value；key唯一
			node.value = value;
		}
	}

	@Override
	public V remove(K key) {
		// TODO Auto-generated method stub
		Node prev = dummyHead;
		while(prev.next != null){
			if(prev.next.key.equals(key)){
				break;
			}
			prev = prev.next;
		}
		
		if(prev.next != null){
			Node delNode = prev.next;
			prev.next = delNode.next;
			delNode.next = null;
			size--;
			return delNode.value;
		}
		return null;
	}

	@Override
	public boolean contains(K key) {
		// TODO Auto-generated method stub
		return getNode(key) != null;
	}

	@Override
	public V get(K key) {
		// TODO Auto-generated method stub
		Node node = getNode(key);
		return node == null ? null : node.value;
	}

	@Override
	public void set(K key, V newValue) {
		// TODO Auto-generated method stub
		Node node = getNode(key);
		
		if(node == null){	// 不存在时，报错
			throw new IllegalArgumentException(key + " doesn`t exist");
		}
		// 替换值
		node.value = newValue;
		
		
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
	
	
}
