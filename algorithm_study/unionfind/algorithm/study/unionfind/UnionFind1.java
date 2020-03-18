package algorithm.study.unionfind;
/**
 * 基于【数组】，第一版Union-Find
 * @Package:algorithm.study.unionfind
 * @ClassName:UnionFind1
 * @Description:TODO 
 * @author:Jiangxb
 * @date:2019年4月3日 上午9:55:50
	0	1	2	3	4	5	6	7	8	9
	—————————————————————————————————————	
	0	1	0	1	0	1	0	1	0	1
 */
public class UnionFind1 implements UnionFind{
	// 存放每个数据所属的那个集合的编号
	private int[] id;
	
	/**
	 * 
	 * @Title:UnionFind1
	 * @param size	传入用户并查集具体需要考虑多少个元素
	 */
	public UnionFind1(int size){
		id = new int[size];
		// 初始时，每个元素是独立的，还未合并。
		for (int i = 0; i < id.length; i++) {
			// 每个元素的id值设置为i(意为：每个元素对应的集合编号不一样)
			id[i] = i;
		}
	}

	/**
	 * 查看元素p和元素q是否所属一个集合;O(1)
	 */
	@Override
	public boolean isConnected(int p, int q) {
		// TODO Auto-generated method stub
		// 判断集合编号是否相等
		return find(p) == find(q);
	}

	/**
	 * 合并操作:合并元素p和元素q所属的集合；O(n)
	 */
	@Override
	public void unionElements(int p, int q) {
		// TODO Auto-generated method stub
		int pID = find(p);
		int qID = find(q);
		
		if(pID == qID){
			return; // 已经在同一个集合了
		}
		
		// 具体的合并操作，将一个集合的id赋值为另外一个集合的id
		for (int i = 0; i < id.length; i++) {
			if(id[i] == pID){
				id[i] = qID;
			}
		}
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return id.length;
	}
	
	/**
	 * @Title:find
	 * @Description:TODO 查找元素p所对应的集合编号
	 * @return:int
	 * @author:Jiangxb
	 * @date: 2019年4月3日 上午10:06:34
	 * O(1)
	 */
	private int find(int p){
		if(p < 0 && p >= id.length){
			throw new IllegalArgumentException("p is not of bound.");
		}
		return id[p];
	}
}
