package algorithm.study.unionfind;
/**
 * 基于【数组】，第二版Union-Find
 * @Package:algorithm.study.unionfind
 * @ClassName:UnionFind2
 * @Description:TODO 
 * @author:Jiangxb
 * @date:2019年4月3日 下午2:41:46
 *
 */
public class UnionFind2 implements UnionFind {
	// parent i,表示第i个元素它指向的一个节点
	private int[] parent;
	
	public UnionFind2(int size){
		parent = new int[size];
		
		for (int i = 0; i < parent.length; i++) {
			// 在初始的时候，每个节点都指向自己；也就是每个节点是独立的树
			parent[i] = i;
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
		// 否则，q的根节点 指向p的根节点
		parent[pRoot] = qRoot;
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
