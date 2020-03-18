package algorithm.study.unionfind;

/**
 * 基于【数组】，第五版Union-Find
 * @Package:algorithm.study.unionfind
 * @ClassName:UnionFind5
 * @Description:TODO 
 * @author:Jiangxb
 * @date:2019年4月6日 下午3:03:15
 *	路径压缩——优化
 */
public class UnionFind5 implements UnionFind {
	// parent i,表示第i个元素它指向的一个节点
	private int[] parent;
	// rank[i] 表示根节点为i的树的高度
	private int[] rank;
	
	
	public UnionFind5(int size){
		parent = new int[size];
		rank = new int[size];
		
		for (int i = 0; i < parent.length; i++) {
			// 在初始的时候，每个节点都指向自己；也就是每个节点是独立的树
			parent[i] = i;
			// 初始化的时候，每个元素都是一个集合，初始元素的层数/高度为1
			rank[i] = 1;	
		}
	}

	/**
	 * 查看元素p和元素q是否所属一个集合;O(h)
	 */
	@Override
	public boolean isConnected(int p, int q) {
		// TODO Auto-generated method stub
		return find(p) == find(q);
	}

	/**
	 * 合并操作:合并元素p和元素q所属的集合；O(h)
	 */
	@Override
	public void unionElements(int p, int q) {
		// TODO Auto-generated method stub
		int pRoot = find(p);
		int qRoot = find(q);
		
		// 如果p的根节点 == q的根节点，表示已经是同一个集合了，直接返回即可
		if(pRoot == qRoot){
			return;
		}
		/**
		 * 否则，q的根节点 指向p的根节点
		 * 	根据两个元素所在树的rank不同 判断 合并方向
		 * 		将rank低的集合 合并到 rank高的集合上。
		 */
		if(rank[pRoot] < rank[qRoot]){
			parent[pRoot] = qRoot;	// pRoot这个节点 指向 qRoot这个节点
		}else if(rank[pRoot] > rank[qRoot]){	
			parent[qRoot] = pRoot;
		}else{// rank[pRoot] = rank[qRoot]
			parent[pRoot] = qRoot;
			rank[qRoot] +=1;
			// 上下两种都可以
//			parent[qRoot] = pRoot;
//			rank[pRoot] +=1;
		}
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return parent.length;
	}
	
	/**
	 * @Title:find
	 * @Description:TODO 查找过程，查找元素p所对应的集合编号
	 * @return:int
	 * @author:Jiangxb
	 * @date: 2019年4月3日 下午2:45:41
	 * O(h)复杂度，h为树的高度
	 */
	private int find(int p){
		if(p < 0 && p >= parent.length){
			throw new IllegalArgumentException("p is not of bound.");
		}
		// 查找根节点位置
		while(p != parent[p]){
			/**
			 * 路径压缩优化
			 * 	将p这个节点的父亲 指向p这个节点的父亲的父亲
			 */
			parent[p] = parent[parent[p]];
			p = parent[p];
		}
		return p;
	}
}
