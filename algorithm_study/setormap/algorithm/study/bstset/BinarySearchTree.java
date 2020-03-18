package algorithm.study.bstset;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;

import algorithm.study.queue.ArrayQueue;

/**
 * 二分搜索树：不包含重复元素
 * @Package:algorithm.study.binarysearchtree
 * @ClassName:BinarySearchTree
 * @Description:TODO 
 * @author:Jiangxb
 * @date:2019年3月17日 下午4:21:22
 * 	<E extends Comparable<E>> ——> 限定类型为可比性
 * 	
 * @param <E>
 */
public class BinarySearchTree<E extends Comparable<E>> {
	
	/* 节点类 */
	private class Node{
		public E e;
		public Node left;	// 左孩子
		public Node right;	// 右孩子
		
		public Node(E e){
			this.e = e;
			left = null;
			right = null;
		}
	}
	/**
	 * 根节点
	 */
	private Node root;
	
	/**
	 * 节点数
	 */
	private int size;
	
	public BinarySearchTree(){
		root = null;
		size = 0;
	}
	
	/**
	 * @Title:size
	 * @Description:TODO 数的节点个数
	 * @return:int
	 * @author:Jiangxb
	 * @date: 2019年3月17日 下午4:25:22
	 */
	public int size(){
		return size;
	}
	
	/**
	 * @Title:isEmpty
	 * @Description:TODO 二分搜索树 是否为空
	 * @return:boolean
	 * @author:Jiangxb
	 * @date: 2019年3月17日 下午4:25:46
	 */
	public boolean isEmpty(){
		return size == 0;
	}
	
	/**
	 * @Title:add
	 * @Description:TODO 向我们二分搜索树中添加新元素e
	 * @return:void
	 * @author:Jiangxb
	 * @date: 2019年3月17日 下午4:36:57
	 */
	public void add(E e){
		// 根节点为空时
//		if(root == null){
//			root = new Node(e);
//			size ++ ;
//		}else{
//			add1(root,e);
//		}
		
		// add() 改善后的方法调用，因为已经在这个递归方法里面限制了为空的操作，这里就不需要重复判断了
		root = add(root,e);
	}
	
	/**
	 * @Title:add1
	 * @Description:TODO 向以node为根的二分搜索树 中插入元素e，递归算法
	 * @return:void
	 * @author:Jiangxb
	 * @date: 2019年3月17日 下午4:38:43
	 */
	private void add1(Node node,E e){
		/**
		 * 第一步：递归终止条件
		 */
		if(e.equals(node.e)){
			return;	// 待插入的元素e 与 节点的值 一样
		}else if(e.compareTo(node.e) < 0 && node.left == null){
			// 待插入的元素e < 节点元素，并且节点左孩子为空，作为节点的左孩子插入
			node.left = new Node(e);
			size ++;
			return;
		}else if(e.compareTo(node.e) > 0 && node.right == null){
			// 待插入的元素e > 节点元素，并且节点右孩子为空，作为节点的右孩子插入
			node.right = new Node(e);
			size ++;
			return;
		}
		
		/**
		 * 第二步：细化问题
		 */
		if(e.compareTo(node.e) < 0){
			add1(node.left,e);	// 左孩子
		}else{ //e.compareTo(node.e) > 0
			add1(node.right,e);	// 右孩子
		}
	}
	
	/**
	 * @Title:add
	 * @Description:TODO 对上面方法的简化(递归的实现)
	 * @return:Node	返回插入新节点后二分搜索树的根
	 * @author:Jiangxb
	 * @date: 2019年3月17日 下午5:00:53
	 */
	private Node add(Node node,E e){
		/**
		 * 第一步：递归终止条件
		 * 	如果根节点为空则直接创建一个节点
		 */
		if(node == null){
			size ++;
			return new Node(e);
		}
		
		/**
		 * 第二步：细化问题
		 */
		if(e.compareTo(node.e) < 0){
			node.left = add(node.left,e);	// 左孩子
		}else if(e.compareTo(node.e) > 0){ 
			node.right = add(node.right,e);	// 右孩子
		}
		
		return node;
	}
	
	/**
	 * @Title:contains
	 * @Description:TODO 看二分搜索树中是否包含元素e(递归实现)
	 * @return:boolean
	 * @author:Jiangxb
	 * @date: 2019年3月19日 下午2:37:58
	 */
	public boolean contains(E e){
		return contains(root, e);
	}
	
	/**
	 * @Title:contains
	 * @Description:TODO 看以node为根的二分搜索树中是否包含元素e，递归算法
	 * @return:boolean
	 * @author:Jiangxb
	 * @date: 2019年3月19日 下午2:39:41
	 */
	private boolean contains(Node node,E e){
		/**
		 * 第一步：递归终止条件
		 * 	如果根节点node为空则直接返回 false
		 */
		if(node == null){
			return false;
		}
		
		/**
		 * 第二步：细化问题(具体的查找逻辑)
		 */
		if(e.compareTo(node.e) == 0){		// 元素e 等于 根节点
			return true;	
		}else if(e.compareTo(node.e) < 0){	// 元素e 在其根节点 左子树
			return contains(node.left,e);
		}else{ //e.compareTo(node.e) > 0	// 元素e 在其根节点 右子树
			return contains(node.right,e);
		}
	}
	
	/**
	 * @Title:preOrder
	 * @Description:TODO 二分搜索树的前序遍历(根 左 右)
	 * @return:void
	 * @author:Jiangxb
	 * @date: 2019年3月19日 下午3:02:14
	 */
	public void preOrder(){
		preOrder(root);
	}
	
	/**
	 * @Title:preOrder
	 * @Description:TODO 前序遍历 以node为根的二分搜索树，递归算法
	 * @return:void
	 * @author:Jiangxb
	 * @date: 2019年3月19日 下午3:03:30
	 */
	private void preOrder(Node node){
		/**
		 * 第一步：递归终止算法
		 */
		if(node == null){
			return;
		}
		/**
		 * 第二步：细化问题(也叫真正的递归逻辑)
		 */
		System.out.println(node.e);
		preOrder(node.left);
		preOrder(node.right);
	}
	
	/**
	 * @Title:preOrderNR
	 * @Description:TODO 二分搜索树前序遍历的非递归写法
	 * @return:void
	 * @author:Jiangxb
	 * @date: 2019年3月19日 下午4:41:30
	 * 【中 左 右】
	 */
	public void preOrderNR(){
		Stack<Node> stack = new Stack<>();
		stack.push(root);	// 第一步：先将根节点入栈
		while(!stack.isEmpty()){
			// 当前要访问的节点(栈顶元素)
			Node cur = stack.pop();
			
			System.out.println(cur.e);
			
			// 先右孩子入栈
			if(cur.right != null){
				stack.push(cur.right);
			}
			
			// 再左孩子入栈
			if(cur.left != null){
				stack.push(cur.left);
			}
		}
	}
	
	/**
	 * @Title:inOrderNR
	 * @Description:TODO 二分搜索树中序遍历的非递归写法
	 * @return:void
	 * @author:Jiangxb
	 * @date: 2019年3月20日 上午9:49:03
	 * 【左 中 右】
	 */
	public void inOrderNR(){
		Stack<Node> stack = new Stack<>();
		// 当前要访问的节点(根节点)
		Node cur = root;
		while(cur != null || !stack.isEmpty()){
			// 压入该节点的所有左子树
			while(cur != null){
				stack.push(cur);
				cur = cur.left;
			}
			// 取出栈顶元素(出栈)
			cur = stack.pop();
			System.out.println(cur.e);
			// 将栈顶元素的右孩子入栈:若无右孩子，会接着出栈；若有右孩子，执行内层的while将其入栈；
			cur = cur.right;
		}
	}
	
	/**
	 * @Title:postOrderNR
	 * @Description:TODO 二分搜索树后序遍历的非递归写法
	 * @return:void
	 * @author:Jiangxb
	 * @date: 2019年3月20日 下午1:37:01
	 * 【左 右 中】
	 */
	public void postOrderNR(){
		Stack<Node> stack = new Stack<>();
		// 当前要访问的节点(根节点)
		Node cur = root;	
		// 前一次访问的节点
		Node last = null;
		while(cur != null || !stack.isEmpty()){
			// 压入该节点的所有左子树
			while(cur != null){
				stack.push(cur);
				cur = cur.left;
			}
			
			cur =  stack.peek();	// 栈顶元素
			
			if(cur.right != null && last != cur.right){
				// 如果当前节点存在右孩子 且 不等于上次访问的节点
				cur = cur.right;
			}else if(null == cur.right || last == cur.right){
				System.out.println(cur.e);
				// 覆盖前一次访问的节点
				last = cur;
				stack.pop();
				cur = null;
			}
		}
	}
	
	/**
	 * @Title:inOrder
	 * @Description:TODO 二分搜索树的中序遍历(左  根 右)
	 * @return:void
	 * @author:Jiangxb
	 * @date: 2019年3月19日 下午3:42:22
	 */
	public void inOrder(){
		inOrder(root);
	}
	
	/**
	 * @Title:inOrder
	 * @Description:TODO 中序遍历 以node为根的二分搜索树，递归算法
	 * @return:void
	 * @author:Jiangxb
	 * @date: 2019年3月19日 下午3:42:49
	 */
	private void inOrder(Node node){
		if(node == null){
			return;
		}
		
		inOrder(node.left);
		System.out.println(node.e);
		inOrder(node.right);
	}
	
	/**
	 * @Title:postOrder
	 * @Description:TODO 二分搜索树的后序遍历(左 右 根)
	 * @return:void
	 * @author:Jiangxb
	 * @date: 2019年3月19日 下午3:52:21
	 */
	public void postOrder(){
		postOrder(root);
	}
	
	/**
	 * @Title:postOrder
	 * @Description:TODO 后序遍历 以node为根的二分搜索树，递归算法
	 * @return:void
	 * @author:Jiangxb
	 * @date: 2019年3月19日 下午3:52:08
	 */
	private void postOrder(Node node){
		if(node == null){
			return;
		}
		
		postOrder(node.left);
		postOrder(node.right);
		System.out.println(node.e);
	}
	
	/**
	 * @Title:levelOrder
	 * @Description:TODO 二分搜索树的层序遍历
	 * @return:void
	 * @author:Jiangxb
	 * @date: 2019年3月21日 上午9:48:29
	 * 借助队列【先进先出】
	 */
	public void levelOrder(){
		Queue<Node> queue = new LinkedList<>();
		// 根节点 入队
		queue.add(root);
		while(!queue.isEmpty()){
			// 当前要出队的节点
			Node cur = queue.remove();
			System.out.println(cur.e);
			
			if(cur.left != null){
				queue.add(cur.left);
			}
			
			if(cur.right != null){
				queue.add(cur.right);
			}
		}
	}
	
	/**
	 * @Title:minmun
	 * @Description:TODO 寻找二分搜索树的最小元素
	 * @return:E
	 * @author:Jiangxb
	 * @date: 2019年3月21日 下午3:55:17
	 */
	public E minimun(){
		if(size == 0){
			throw new IllegalArgumentException("BST is empty!");
		}
		return minimun(root).e;
	}
	
	/**
	 * @Title:minimun
	 * @Description:TODO 返回以node为根的二分搜索树的最小值所在的节点
	 * @return:void
	 * @author:Jiangxb
	 * @date: 2019年3月21日 下午3:56:54
	 */
	private Node minimun(Node node) {
		/**
		 * 第一步：递归终止条件
		 */
		if(node.left == null){
			return node;
		}
		/**
		 * 第二步：递归细化(一直向左走)
		 */
		return minimun(node.left);
	}
	
	/**
	 * @Title:maximun
	 * @Description:TODO 寻找二分搜索树的最大元素
	 * @return:E
	 * @author:Jiangxb
	 * @date: 2019年3月21日 下午3:59:49
	 */
	public E maximun(){
		if(size == 0){
			throw new IllegalArgumentException("BST is empty!");
		}
		return maximun(root).e;
	}
	
	/**
	 * @Title:maximun
	 * @Description:TODO 返回以node为根的二分搜索树的最大值所在的节点
	 * @return:Node
	 * @author:Jiangxb
	 * @date: 2019年3月21日 下午4:00:10
	 */
	private Node maximun(Node node) {
		/**
		 * 第一步：递归终止条件
		 */
		if(node.right == null){
			return node;
		}
		/**
		 * 第二步：递归细化(一直向右走)
		 */
		return maximun(node.right);
	}

	
	/**
	 * @Title:removeMin
	 * @Description:TODO 从二分搜索树中删除最小值所在的节点，并返回最小值
	 * @return:E
	 * @author:Jiangxb
	 * @date: 2019年3月21日 下午4:06:39
	 */
	public E removeMin(){
		E ret = minimun();
		root = removeMin(root);
		return ret;
	}
	
	/**
	 * @Title:removeMin
	 * @Description:TODO 删除掉以node为根的二分搜索树中的最小节点
	 * @return:Node	返回删除节点后新的二分搜索树的根
	 * @author:Jiangxb
	 * @date: 2019年3月21日 下午4:08:48
	 */
	private Node removeMin(Node node){
		/**
		 * 第一步：递归终止方法，递归到底(以找到最小节点)
		 */
		if(node.left == null){
			// 保存当前节点的右子树(因为当前节点的左子树已经为空了，删除掉这个节点之后，那么这个节点的右子树的根节点就是新的根节点)
			Node rightNode = node.right;
			node.right = null;
			size --;
			return rightNode;
		}
		
		/**
		 * 第二步：递归逻辑，递归为到底
		 * 	去删除当前节点的左子树中的最小值，将结果返回给当前节点的左孩子。
		 */
		node.left = removeMin(node.left);
		return node;
	}
	
	/**
	 * @Title:removeMax
	 * @Description:TODO 从二分搜索树中删除最大值所在的节点，并返回最大值
	 * @return:E
	 * @author:Jiangxb
	 * @date: 2019年3月21日 下午4:18:45
	 */
	public E removeMax(){
		E ret = maximun();
		root = removeMax(root);
		return ret;
	}
	
	/**
	 * @Title:removeMax
	 * @Description:TODO 删除掉以node为根的二分搜索树中的最大节点
	 * @return:Node	返回删除节点后新的二分搜索树的根
	 * @author:Jiangxb
	 * @date: 2019年3月21日 下午4:19:25
	 */
	private Node removeMax(Node node){
		/**
		 * 第一步：递归终止方法
		 * 	node.right == null，达到了递归到底
		 */
		if(node.right == null){
			// 保存当前节点的左子树(因为当前节点的右子树已经为空了，删除掉这个节点之后，那么这个节点的左子树的根节点就是新的根节点)
			Node rightNode = node.left;
			node.left = null;
			size --;	// 维护树的节点个数
			return rightNode;
		}
		/**
		 * 第二步：递归未到底
		 * 	去删除当前节点的右子树中的最大值，将结果返回给当前节点的右孩子。
		 */
		node.right = removeMax(node.right);
		return node;
	}
	
	
	/**
	 * @Title:remove
	 * @Description:TODO 从二分搜索树中删除元素为e的节点
	 * @return:void
	 * @author:Jiangxb
	 * @date: 2019年3月22日 下午5:22:48
	 */
	public void remove(E e){
		root = remove(root,e);
	}
	
	/**
	 * @Title:remove
	 * @Description:TODO 删除以node为根的二分搜索树中值为e的节点，递归算法
	 * @return:Node	返回删除节点后新的二分搜索树的根
	 * @author:Jiangxb
	 * @date: 2019年3月22日 下午5:24:05
	 */
	private Node remove(Node node, E e) {
		/**
		 * 第一步：递归终止条件
		 */
		if(node == null){
			return null;
		}
		
		/**
		 * 第二步：递归细化，
		 */
		if(e.compareTo(node.e) < 0){		// 待删除元素e 比 node的元素小的时候(往左子树走，找到待删除元素e)
			node.left = remove(node.left,e);
			return node;
		}else if(e.compareTo(node.e) > 0){	// 待删除元素e 比 node的元素大的时候(往右子树走，找到待删除元素e)
			node.right = remove(node.right, e);
			return node;
		}else{ // e.compareTo(node.e) = 0  	// 找到了待删除元素e 在 树中的位置(也就是要删除node这个节点)
			
			// ①、待删除节点左子树为空的情况
			if(node.left == null){
				Node rightNode = node.right;
				node.right = null;
				size --;
				return rightNode;
			}
			
			// ②、待删除节点右子树为空的情况
			if(node.right == null){
				Node leftNode = node.left;
				node.left = null;
				size--;
				return leftNode;
			}
			
			/**
			 * ③、待删除节点左右子树均不为空
			 * 思路：找到比待删除节点大的最小节点，即待删除节点右子树的最小节点（后继，其实还可以找前驱(左子树中最大的节点)）
			 * 		用这个节点顶替待删除节点的位置
			 */
			 Node successor = minimun(node.right);		// successor“后继节点”指向了待删除节点右子树的最小节点
			 successor.right = removeMin(node.right);	// 删除待删除节点右子树的最小节点
			 successor.left = node.left;	// 
			 node.left = node.right = null;
			 return successor;
		}
	}

	
	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		generateBSTString(root,0,res);
		return res.toString();
	}
	
	/**
	 * @Title:generateBSTString
	 * @Description:TODO 生成以node为根节点，深度为depth的描述二叉树的字符串
	 * @return:void
	 * @author:Jiangxb
	 * @date: 2019年3月19日 下午3:23:35
	 */
	private void generateBSTString(BinarySearchTree<E>.Node node, int depth, StringBuilder res) {
		/**
		 * 第一步：递归终止方法
		 */
		if(node == null){
			res.append(generateDepthString(depth) + "null\n");
			return;
		}
		
		/**
		 * 第二步：
		 * 	基于前序遍历的，递归算法
		 */
		res.append(generateDepthString(depth) + node.e + "\n");
		generateBSTString(node.left,depth + 1,res);
		generateBSTString(node.right,depth + 1,res);
	}

	/**
	 * @Title:generateDepthString
	 * @Description:TODO 打印层次问题
	 * @return:String
	 * @author:Jiangxb
	 * @date: 2019年3月19日 下午3:29:32
	 */
	private String generateDepthString(int depth) {
		StringBuilder res = new StringBuilder();
		for(int i = 0 ; i <depth ; i++){
			res.append("- ");
		}
		return res.toString();
	}
	
}
