package algorithm.study.avltree;

import java.util.ArrayList;

/**
 * AVLTree整体的框架和二分搜索树是一致的
 * @Package:algorithm.study.avltree
 * @ClassName:AVLTree
 * @Description:TODO 
 * @author:Jiangxb
 * @date:2019年4月7日 上午10:51:22
 *	复制：algorithm.study.bstmap.BSTMap<K, V>
 *	
 *	修改思想：
 *		AVL树就是在二分搜索树的代码基础上补充一些代码，其实就是添加上【自平衡的机制】，
 *	使得原先实现的二分搜索树在对节点进行操作的时候可以保证整棵树是平衡的，那么在这里
 *	对于平衡的定义就是(对于每一个节点的左右子树的高度差不超过1)
 */
public class AVLTree<K extends Comparable<K>,V>{
	
	private class Node{
		public K key;
		public V value;
		public Node left,right;	// 左孩子 右孩子
		
		/**
		 * AVL 重要的机制，就是记录当前节点所处的高度值
		 */
		public int height;	
		
		public Node(K key,V value){
			this.key = key;
			this.value = value;
			left = null;
			right = null;
			/**
			 * 默认对于一个新的节点来说，相当于叶子节点，默认高度为1.
			 * 	添加一个节点都是从根节点开始出发，去找到它最终的添加位置；
			 * 这个最终的添加位置一定是一个叶子节点的位置。
			 */
			height = 1;
		}
	}
	
	private Node root;
	private int size;
	
	public AVLTree(){
		root = null;
		size = 0;
	}

	/**
	 * 补充辅助函数：获得节点node的高度
	 * @Title:getHeight
	 * @Description:TODO 
	 * @return:int
	 * @author:Jiangxb
	 * @date: 2019年4月7日 上午11:23:12
	 */
	private int getHeight(Node node){
		if(node == null){
			return 0;
		}
		return node.height;
	}
	
	/**
	 * 补充辅助函数：获得节点node的平衡因子
	 * @Title:getBalanceFactor
	 * @Description:TODO 
	 * @return:int
	 * @author:Jiangxb
	 * @date: 2019年4月7日 上午11:37:41
	 * 	平衡因子：对与每一个节点而言，它的左右子树的高度差(左子树的高度-右子树的高度)
	 */
	private int getBalanceFactor(Node node){
		if(node == null){
			return 0;
		}
		
		return getHeight(node.left) - getHeight(node.right);
	}
	
	/**
	 * 补充辅助函数：判断该二叉树是否是一颗二分搜索树
	 * @Title:isBST
	 * @Description:TODO 
	 * @return:boolean
	 * @author:Jiangxb
	 * @date: 2019年4月7日 上午11:47:07
	 * 利用二分搜索树的一个性质来判断：
	 * 		对于一颗二分搜索树来说，当我们使用中序遍历的时候，遍历的结果所有的元素是按顺序排列的。
	 */
	public boolean isBST(){
		ArrayList<K> keys = new ArrayList<>();
		// 中序遍历
		inOrder(root,keys);
		for (int i = 1; i < keys.size(); i++) {
			/**
			 * 如果满足二分搜索树中序遍历的性质，那么keys中保存的元素就会是升序的结果
			 * 	如果keys[0] > keys[1] ,不满足升序的性质
			 */
			if(keys.get(i-1).compareTo(keys.get(i)) > 0){
				return false;
			}
		}
		return true;
	}
	
	
	/**
	 * 补充辅助函数：对node为根节点的二分搜索树进行中序遍历，递归实现
	 * @Title:inOrder
	 * @Description:TODO 
	 * @return:void
	 * @author:Jiangxb
	 * @date: 2019年4月7日 上午11:50:55
	 */
	private void inOrder(Node node, ArrayList<K> keys) {
		/**
		 * 第一步：递归终止条件
		 */
		if(node == null){
			return;
		}
		
		/**
		 * 第二步：递归细化
		 * 	中序遍历：左 中 右
		 */
		inOrder(node.left, keys);
		keys.add(node.key);	// keys中保存这颗树中所有的键
		inOrder(node.right, keys);
	}
	
	/**
	 * 补充辅助函数：判断该二叉树是否是一颗平衡二叉树
	 * @Title:isBalanced
	 * @Description:TODO 
	 * @return:boolean
	 * @author:Jiangxb
	 * @date: 2019年4月7日 上午11:59:51
	 */
	public boolean isBalanced(){
		return isBalanced(root);
	}

	/**
	 * 补充辅助函数：判断以node为根的二叉树是否是一课平衡二叉树，递归实现
	 * @Title:isBalanced
	 * @Description:TODO 
	 * @return:boolean
	 * @author:Jiangxb
	 * @date: 2019年4月7日 下午12:00:59
	 */
	private boolean isBalanced(Node node) {
		// TODO Auto-generated method stub
		/**
		 * 第一步：递归终止条件
		 */
		if(node == null){
			return true;
		}
		
		/**
		 * 第二步：递归细化
		 */
		int balancedFactor = getBalanceFactor(node);	// 得到平衡因子
		
		if(Math.abs(balancedFactor) > 1){
			return false;	// 平衡因子大于 1时，表示该树不是平衡二叉树了
		}
		// 递归去判断其 左右子树是否满足这个特性
		return isBalanced(node.left) && isBalanced(node.right);
	}
	
	/**
	 * 补充辅助函数：对节点y进行向右旋转操作，返回旋转后新的根节点x
	 * @Title:rightRotate
	 * @Description:TODO 
	 * @return:Node
	 * @author:Jiangxb
	 * @date: 2019年4月7日 下午9:36:50
	 * 	// 对节点y进行向右旋转操作，返回旋转后新的根节点x
	    //        y                              x
	    //       / \                           /   \
	    //      x   T4     向右旋转 (y)        	  z     y
	    //     / \       - - - - - - - ->    / \   / \
	    //    z   T3                       T1  T2 T3 T4
	    //   / \
	    // T1   T2
	 */
	private Node rightRotate(Node y){
		Node x = y.left;
		Node T3 = x.right;
		
		// 向右旋转过程
		x.right = y;
		y.left = T3;
		
		// 更新height(x y的值发生了高度变化)
		y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
		x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
		
		return x;
	}
	
	/**
	 * 补充辅助函数：对节点y进行向左旋转操作，返回旋转后新的根节点x
	 * @Title:leftRotate
	 * @Description:TODO 
	 * @return:Node
	 * @author:Jiangxb
	 * @date: 2019年4月7日 下午9:39:06
	 * 
	 *  //    y                             x
	    //  /  \                          /   \
	    // T1   x      向左旋转 (y)         y     z
	    //     / \   - - - - - - - ->   / \   / \
	    //   T2  z                     T1 T2 T3 T4
	    //      / \
	    //     T3 T4
	 */
	private Node leftRotate(Node y) {
		Node x = y.right;
		Node T2 = x.left;
		
		// 向左旋转过程
		x.left = y;
		y.right = T2;
		
		// 更新height(x y的值发生了高度变化)
		y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
		x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
		
		return x;
	}
	
	
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
		 * 	在这一步，不需要维护height高度值，因为在构造函数中默认为1
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
		
		/**
		 * 对于以当前这个node为根的二分搜索树添加了一个新的节点后，
		 * 	相应的我们需要对当前node的height值进行更新:
		 * 		当前节点的高度 = 1 + 左右子树中的最大高度值
		 */
		node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
		
		/**
		 * 添加完节点后，再计算平衡因子
		 */
		int balanceFactor = getBalanceFactor(node);
		
		// 如果balanceFactor的绝对值大于1，说明在这个节点上，我们整课二分搜索树不再满足平衡二叉树的条件
//		if(Math.abs(balanceFactor) > 1){
//			System.out.println("unbalanced : " + balanceFactor);
//		}
		
		/**
		 * 平衡维护
		 * 前提引入——平衡因子：对与每一个节点而言，它的左右子树的高度差(左子树的高度-右子树的高度)
		 * 	
		 * 右旋转：
		 * 	如果平衡因子 > 1，左子树与右子树的高度差是超过了1的，并且是左子树(结合平衡因子的算法)这边的平衡因子要高的。
		 * 	&&
		 * 	当前这个节点它的左子树这个节点的平衡因子 >= 0;
		 * 	 在这种情况下说明：其实对于当前这个node是不平衡的，不平衡的原因是在于它的左侧的左侧添加了一个节点。
		 * 										12
		 *								8				18
		 *							5		11		17
		 *						4		7
		 *					2
		 * 		说明：节点【8】为不平衡节点，它的原因是它的左侧的左侧添加了一个节点【2】,(节点【5】的平衡因子：2-1=1，如不存在节点【2】，此时平衡因子为：1-1=0)
		 * 
		 * 左旋转：
		 * 	....... 与右旋转 对称的
		 */
		if(balanceFactor > 1 && getBalanceFactor(node.left) >= 0){
			// (LL)_右旋转
			return rightRotate(node);
		}
		
		if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0){
            // (RR)_左旋转
			return leftRotate(node);
		}
		
		if(balanceFactor > 1 && getBalanceFactor(node.left) < 0){
			// (LR)_先左再右旋转
			node.left = leftRotate(node.left);
			return rightRotate(node);
		}
		
		if (balanceFactor < -1 && getBalanceFactor(node.right) > 0){
            // (RL)_先右再左旋转
			node.right = rightRotate(node.right);
			return leftRotate(node);
		}
		
		return node;
	}

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
		Node retNode;	// 保存待返回的节点
		
		if(key.compareTo(node.key) < 0){	// 待删除节点key 比 node节点key小，在左子树找待删除节点
			node.left = remove(node.left,key);
			// 不能直接返回了，当删除了这个节点的左子树中的节点，也许破坏了平衡性，因此需要维护
//			return node;
			retNode = node;
		}else if(key.compareTo(node.key) > 0){	// // 待删除节点key 比 node节点key大，在右子树找待删除节点
			node.right = remove(node.right, key);
//			return node;
			retNode = node;
		}else{	 // 待删除节点key 跟  node节点key相等，找到待删除节点了。
			// ①、待删除节点 无左孩子情况
			if(node.left == null){
				Node rightNode = node.right;	// 在node中删除跟节点后新的根节点
				node.right = null;
				size --;
//				return rightNode;	// 需要维护其平衡性
				retNode = rightNode;	
			}
			// ②、待删除节点 无右孩子情况
			else if(node.right == null){
				Node leftNode = node.left;
				node.left = null;
				size --;
//				return leftNode;
				retNode = leftNode;
			}else{
				/**
				 * ③、待删除节点左右子树均不为空
				 * 	后继节点：找到待删除节点——右子树中最小的节点
				 * 	找到比待删除节点大的最小节点，即待删除节点右子树的最小节点；用这个节点顶替待删除节点的位置
				 */
				Node successor = minmun(node.right);	// 返回最小节点
				/**
				 * removeMin(node.right)
				 * 删除待删除节点右子树中的最小节点并返回最小节点的父节点(这个删除操作维护平衡性有两种解决方案：1、在它方法内也添加维护操作，2、进行方法的改写如下)
				 * remove(node.right,successor.key);
				 * 		因为successor已经保存了最小节点
				 */
//				successor.right = removeMin(node.right);
				successor.right = remove(node.right,successor.key);
				successor.left = node.left;
				node.left = node.right = null;
				retNode = successor;
			}
		}
		
		// 判空处理
		if(retNode == null){
			return null;
		}
		
		/**
		 * 维护平衡性的操作：三个步骤如下
		 */
		// 1、更新height
        retNode.height = 1 + Math.max(getHeight(retNode.left), getHeight(retNode.right));

        // 2、计算平衡因子
        int balanceFactor = getBalanceFactor(retNode);

        // 3、平衡维护
        if(balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0){
			// (LL)_右旋转
			return rightRotate(retNode);
		}
		
		if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0){
            // (RR)_左旋转
			return leftRotate(retNode);
		}
		
		if(balanceFactor > 1 && getBalanceFactor(retNode.left) < 0){
			// (LR)_先左再右旋转
			retNode.left = leftRotate(retNode.left);
			return rightRotate(retNode);
		}
		
		if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0){
            // (RL)_先右再左旋转
			retNode.right = rightRotate(retNode.right);
			return leftRotate(retNode);
		}
		
		return retNode;
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
	
	public boolean contains(K key) {
		// TODO Auto-generated method stub
		return getNode(root, key) != null;
	}

	public V get(K key) {
		// TODO Auto-generated method stub
		Node node = getNode(root, key);
		if(node == null){
			return null;
		}
		return node.value;
	}

	public void set(K key, V newValue) {
		// TODO Auto-generated method stub
		Node node = getNode(root, key);
		if(node == null){	// 不存在时，报错
			throw new IllegalArgumentException(key + " doesn`t exist");
		}
		node.value = newValue;
	}

	public int getSize() {
		// TODO Auto-generated method stub
		return size;
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}
}
