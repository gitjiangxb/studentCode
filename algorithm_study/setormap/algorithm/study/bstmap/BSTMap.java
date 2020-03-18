package algorithm.study.bstmap;

import algorithm.study.map.Map;

/**
 * 基于 【二分搜索树】实现映射Map
 * @Package:algorithm.study.bstmap
 * @ClassName:BSTMap
 * @Description:TODO 
 * @author:Jiangxb
 * @date:2019年3月26日 下午3:22:11
 * 
 * @param <K>
 * @param <V>
 * 说明：
 * 	BSTMap<K extends Comparable<K>,V> 基于二分搜索实现时，Key必须是可比较的类型，因此继承Comparable
 */
public class BSTMap<K extends Comparable<K>,V> implements Map<K, V>{
	private class Node{
		public K key;
		public V value;
		public Node left,right;	// 左孩子 右孩子
		public Node(K key,V value){
			this.key = key;
			this.value = value;
			left = null;
			right = null;
		}
	}
	
	private Node root;
	private int size;
	
	public BSTMap(){
		root = null;
		size = 0;
	}

	@Override
	public void add(K key, V value) {
		// TODO Auto-generated method stub
		root = add(root,key,value);
	}
	
	/**
	 * 
	 * @Title:add
	 * @Description:TODO 向以node为根的二分搜索树中插入元素（key，value），递归算法
	 * @return:Node	返回插入新节点后二分搜索树的根
	 * @author:Jiangxb
	 * @date: 2019年3月26日 下午3:36:10
	 */
	private Node add(Node node,K key,V value){
		/**
		 * 第一步：递归终止条件
		 */
		if(node == null){
			size ++;
			return new Node(key,value);
		}
		
		/**
		 * 第二步：递归细化
		 */
		if(key.compareTo(node.key) < 0){
			node.left = add(node.left,key,value);
		}else if(key.compareTo(node.key) > 0){
			node.right = add(node.right,key,value);
		}else{ // key.compareTo(node.key) = 0
			// 在映射中，当key一致时，理解成覆盖原值
			node.value = value;
		}
		return node;
	}

	@Override
	public V remove(K key) {
		// TODO Auto-generated method stub
		Node node = getNode(root, key);
		if(node != null){
			root = remove(root, key);
            return node.value;
		}
		return null;
	}
	
	/**
	 * @Title:remove
	 * @Description:TODO 删除以node为根的二分搜索树中的节点(key)，递归算法
	 * @return:Node	返回删除节点后新的二分搜索树的根
	 * @author:Jiangxb
	 * @date: 2019年3月26日 下午4:25:28
	 */
	private Node remove(Node node, K key) {
		/**
		 * 第一步：递归终止条件
		 */
		if(node == null){
			return null;
		}
		
		/**
		 * 第二步：递归细化
		 */
		if(key.compareTo(node.key) < 0){	// 待删除节点key 比 node节点key小，在左子树找待删除节点
			node.left = remove(node.left,key);
			return node;
		}else if(key.compareTo(node.key) > 0){	// // 待删除节点key 比 node节点key大，在右子树找待删除节点
			node.right = remove(node.right, key);
			return node;
		}else{	 // 待删除节点key 跟  node节点key相等，找到待删除节点了。
			// ①、待删除节点 无左孩子情况
			if(node.left == null){
				Node rightNode = node.right;
				node.right = null;
				size --;
				return rightNode;
			}
			// ②、待删除节点 无右孩子情况
			if(node.right == null){
				Node leftNode = node.left;
				node.left = null;
				size --;
				return leftNode;
			}
			/**
			 * ③、待删除节点左右子树均不为空
			 * 	后继节点：找到待删除节点——右子树中最小的节点
			 */
			Node successor = minmun(node.right);	// 返回最小节点
			successor.right = removeMin(node.right); // 返回最小节点的父节点
			successor.left = node.left;
			node.left = node.right = null;
			return successor;
		}
	}
	
	/**
	 * @Title:removeMin
	 * @Description:TODO 删除掉以node为根的二分搜索树中的最小节点
	 * @return:Node 返回删除节点后新的二分搜索树的根
	 * @author:Jiangxb
	 * @date: 2019年3月27日 上午9:17:46
	 */
	private Node removeMin(Node node) {
		// TODO Auto-generated method stub
		/**
		 * 第一步：递归终止条件
		 */
		if(node.left == null){
			Node rightNode = node.right;
			node.right = null;
			size --;
			return rightNode;
		}
		/**
		 * 第二步：递归细化
		 */
		node.left = removeMin(node.left);
		return node;
	}

	/**
	 * @Title:minmun
	 * @Description:TODO 返回以node为根的二分搜索树的最小值所在的节点
	 * @return:Node
	 * @author:Jiangxb
	 * @date: 2019年3月27日 上午9:17:35
	 */
	private Node minmun(Node node) {
		/**
		 * 第一步：递归终止条件
		 */
		if(node.left == null){
			return node;
		}
		/**
		 * 第二步：递归细化
		 */
		return minmun(node.left);
	}

	/**
	 * 辅助方法：返回以node为根节点的二分搜索树中，key所在的节点
	 * @Title:getNode
	 * @Description:TODO 递归写法
	 * @return:Node
	 * @author:Jiangxb
	 * @date: 2019年3月26日 下午3:54:56
	 */
	private Node getNode(Node node,K key){
		/**
		 * 第一步：递归终止方法
		 */
		if(node == null){
			return null;
		}
		
		/**
		 * 第二步：递归细化
		 */
		if(key.compareTo(node.key) == 0){
			return node;
		}else if(key.compareTo(node.key) < 0){
			return getNode(node.left, key);		// 往左子树找
		}else{ //key.compareTo(node.key) > 0
			return getNode(node.right, key);	// 往右子树找
		}
		
	}
	
	@Override
	public boolean contains(K key) {
		// TODO Auto-generated method stub
		return getNode(root, key) != null;
	}

	@Override
	public V get(K key) {
		// TODO Auto-generated method stub
		Node node = getNode(root, key);
		if(node == null){
			return null;
		}
		return node.value;
	}

	@Override
	public void set(K key, V newValue) {
		// TODO Auto-generated method stub
		Node node = getNode(root, key);
		if(node == null){	// 不存在时，报错
			throw new IllegalArgumentException(key + " doesn`t exist");
		}
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
