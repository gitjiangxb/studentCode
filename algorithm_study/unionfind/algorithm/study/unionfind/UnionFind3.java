package algorithm.study.unionfind;
/**
 * 基于【数组】，第三版Union-Find
 * @Package:algorithm.study.unionfind
 * @ClassName:UnionFind3
 * @Description:TODO 
 * @author:Jiangxb
 * @date:2019年4月6日 下午2:04:26
 *	基于每个树的节点大小来合并
 */
public class UnionFind3 implements UnionFind {
	// parent i,表示第i个元素它指向的一个节点
	private int[] parent;
	// sz[i] 表示以i为根的集合中元素个数
	private int[] sz;
	
	
	public UnionFind3(int size){
		parent = new int[size];
		sz = new int[size];
		
		for (int i = 0; i < parent.length; i++) {
			// 在初始的时候，每个节点都指向自己；也就是每个节点是独立的树
			parent[i] = i;
			// 初始化的时候，每个元素都是一个集合，初始元素个数为1
			sz[i] = 1;	
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
		 * 	根据两个元素所在树的元素个数不同 判断 合并方向
		 * 		将元素个数少的集合 合并到 元素个数多的集合上。
		 */
		if(sz[pRoot] < sz[qRoot]){
			parent[pRoot] = qRoot;
			sz[qRoot] += sz[pRoot];
		}else{	// sz[pRoot] >= sz[qRoot]
			parent[qRoot] = pRoot;
			sz[pRoot] += sz[qRoot];
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
			p = parent[p];
		}
		return p;
	}
}
