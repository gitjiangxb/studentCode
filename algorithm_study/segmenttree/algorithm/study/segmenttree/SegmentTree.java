package algorithm.study.segmenttree;


/**
 * 基于【数组】实现线段树
 * @Package:algorithm.study.segmenttree
 * @ClassName:SegmentTree
 * @Description:TODO 
 * @author:Jiangxb
 * @date:2019年3月30日 下午2:18:49
 * 
 * @param <E>
 */
public class SegmentTree<E> {
	// 组成线段树(看作一颗满二叉树)
	private E[] tree;
	private E[] data;
	private Merger<E> merger;	// 融合器(通过构造函数传入用户自定义的逻辑：如求和、最大值)
	
	/**
	 * 构造函数
	 * @Title:SegmentTree
	 * @param arr
	 * @param merger
	 * 	E[] arr：数组元素
	 * 	Merger<E> merger：表示两个元素怎么融合
	 */
	public SegmentTree(E[] arr,Merger<E> merger){
		this.merger = merger;
		
		data = (E[]) new Object[arr.length];
		for (int i = 0; i < arr.length; i++) {
			data[i] = arr[i];
		}
		/**
		 * 说明：线段树如是利用数组来实现的话，需要开设 4 * n的空间来存储
		 */
		tree = (E[]) new Object[4 * arr.length];
		
		buildSegmentTree(0,0,data.length-1);
	}
	
	/**
	 * @Title:buildSegmentTree
	 * @Description:TODO 在treeIndex的位置创建表示区间[l,r]的线段树
	 * @return:void
	 * @author:Jiangxb
	 * @date: 2019年3月30日 下午2:36:52
	 */
	private void buildSegmentTree(int treeIndex,int l,int r){
		/**
		 * 第一步：递归终止条件
		 */
		if(l == r){
			tree[treeIndex] = data[l];
			return;
		}
		
		/**
		 * 第二步：递归细化
		 */
		int leftTreeIndex = leftChild(treeIndex);
		int rightTreeIndex = rightChild(treeIndex);
		
		/**
		 * 区间中间的值：mid
		 * 分成两个区间就是：[l,mid] 和  [mid+1,r]
		 * 	
		 */
		int mid = l + (r -l) / 2; 
		buildSegmentTree(leftTreeIndex, l, mid);
		buildSegmentTree(rightTreeIndex, mid+1, r);
		
		/**
		 * 这个逻辑跟业务相关：
		 * 若求区间和：
		 * 	tree[treeIndex] = tree[leftTreeIndex] + tree[rightTreeIndex];
		 * 若是求区间的最大值：
		 * 	tree[treeIndex] = Max(tree[leftTreeIndex] + tree[rightTreeIndex]);
		 * 这个过程就是综合左右两个线段的信息来得到当前这个更大的线段的信息。
		 * 怎么综合由你的业务逻辑来决定的。若是不想写死这个逻辑，
		 * 若是希望用户可以根据自己的业务场景，我们就需要设置一个接口：Merger,让用户自己去实现
		 */
		tree[treeIndex] = merger.meger(tree[leftTreeIndex],tree[rightTreeIndex]);
	}
	
	/**
	 * @Title:getSize
	 * @Description:TODO 得到实际元素个数
	 * @return:int
	 * @author:Jiangxb
	 * @date: 2019年3月30日 下午2:22:33
	 */
	public int getSize(){
		return data.length;
	}
	
	/**
	 * @Title:get
	 * @Description:TODO 根据index得到值
	 * @return:E
	 * @author:Jiangxb
	 * @date: 2019年3月30日 下午2:22:46
	 */
	public E get(int index){
		if(index < 0 || index >= data.length){
			throw new IllegalArgumentException("Index is illegal");
		}
		return data[index];
	}
	
	/**
	 * @Title:leftChild
	 * @Description:TODO 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
	 * @return:int
	 * @author:Jiangxb
	 * @date: 2019年3月30日 下午2:25:30
	 */
	private int leftChild(int index){
		return 2 * index + 1;
	}
	
	/**
	 * @Title:rightChild
	 * @Description:TODO 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
	 * @return:int
	 * @author:Jiangxb
	 * @date: 2019年3月30日 下午2:25:43
	 */
	private int rightChild(int index){
		return 2 * index + 2;
	}
	
	/**
	 * @Title:query
	 * @Description:TODO 返回区间[queryL,queryR]的值
	 * @return:E
	 * @author:Jiangxb
	 * @date: 2019年3月30日 下午3:57:11
	 */
	public E query(int queryL,int queryR){
		if(queryL < 0 || queryL >= data.length ||
				queryR < 0 || queryR >= data.length || queryL > queryR){
			throw new IllegalArgumentException("Index is illegal");
		}
		
		return query(0,0,data.length-1,queryL,queryR);
	}
	
	/**
	 * @Title:query
	 * @Description:TODO 在以treeIndex为根的线段树中[l,r]的范围里，搜索区间[queryL,queryR]的值
	 * @return:E
	 * @author:Jiangxb
	 * @date: 2019年3月30日 下午4:03:43
	 */
	private E query(int treeIndex,int l,int r,int queryL,int queryR){
		/**
		 * 第一步：递归终止条件
		 */
		if(l ==queryL && r ==queryR){
			return tree[treeIndex];
		}
		
		/**
		 * 第二步：递归细化
		 */
		int mid = l + (r -l) / 2;	// 区间的中间值,分成两个区间就是：[l,mid] 和  [mid+1,r]
		int leftTreeIndex = leftChild(treeIndex);
		int rightTreeIndex = rightChild(treeIndex);
		
		if(queryL >= mid +1){
			// 如果 要查询的左区间 >= 中间值+1 ，说明直接去右子树那边找
			return query(rightTreeIndex,mid+1,r,queryL,queryR);
		}else if(queryR <= mid){
			// 如果 要查询的右区间 <= 中间值，说明直接去左子树那边找
			return query(leftTreeIndex,l,mid,queryL,queryR);
		}else{
			// 一半在左孩子 一半在右孩子
			E leftResult = query(leftTreeIndex,l,mid,queryL,mid);
			E rightResult = query(rightTreeIndex,mid+1,r,mid+1,queryR);
			return merger.meger(leftResult, rightResult);
		}
		
	}
	
	/**
	 * @Title:set
	 * @Description:TODO 将index位置的值 更新为e
	 * @return:void
	 * @author:Jiangxb
	 * @date: 2019年3月31日 下午2:05:15
	 */
	public void set(int index,E e){
		// 索引合法性的校验
		if(index < 0 || index >= data.length){
			throw new IllegalArgumentException("Index is illegal");
		}
		data[index] = e;
		set(0,0,data.length-1,index,e);
	}
	
	/**
	 * @Title:set
	 * @Description:TODO 在以treeIndex为根的线段树中更新index的值为e
	 * @return:void
	 * @author:Jiangxb
	 * @date: 2019年3月31日 下午2:07:18
	 * O(logn)
	 */
	private void set(int treeIndex,int l,int r,int index,E e){
		/**
		 * 第一步：递归终止
		 */
		if(l == r){
			tree[treeIndex] = e;
			return;
		}
		
		/**
		 * 第二步：递归细化
		 * 	说明：这里其实就是去找index这个位置对于的叶子在哪里
		 */
		int mid = l + (r -l) / 2;	// 区间的中间值,分成两个区间就是：[l,mid] 和  [mid+1,r]
		int leftTreeIndex = leftChild(treeIndex);
		int rightTreeIndex = rightChild(treeIndex);
		
		// 去右子树中找
		if(index >= mid +1){
			set(rightTreeIndex,mid + 1,r,index,e);
		}else{ // index <= mid	//去左子树中找
			set(leftTreeIndex,l,mid,index,e);
		}
		
		/**
		 * 上面的操作只是修改了tree[treeIndex] = e的值，
		 * ★.还需要修改其祖辈节点的值
		 */
		tree[treeIndex] = merger.meger(tree[leftTreeIndex], tree[rightTreeIndex]);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder res = new StringBuilder();
		res.append("[");
		for (int i = 0; i < tree.length; i++) {
			if(tree[i] != null){
				res.append(tree[i]);
			}else{
				res.append("null");
			}
			if(i != tree.length-1){
				res.append(", ");
			}
		}
		res.append("]");
		return res.toString();
	}
}
