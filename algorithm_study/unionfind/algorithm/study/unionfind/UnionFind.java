package algorithm.study.unionfind;
/**
 * 并查集接口
 * @Package:algorithm.study.unionfind
 * @ClassName:UnionFind
 * @Description:TODO 
 * @author:Jiangxb
 * @date:2019年4月2日 下午4:53:31
 *
 */
public interface UnionFind {
	/**
	 * @Title:isConnected
	 * @Description:TODO 两个参数是否所属同一个集合(也就是是否可以连接的)
	 * @return:boolean
	 * @author:Jiangxb
	 * @date: 2019年4月2日 下午4:54:29
	 */
	boolean isConnected(int p,int q);
	
	/**
	 * @Title:unionElements
	 * @Description:TODO 将两个元素 并 在一起
	 * @return:void
	 * @author:Jiangxb
	 * @date: 2019年4月2日 下午4:57:20
	 */
	void unionElements(int p,int q);
	
	/**
	 * @Title:getSize
	 * @Description:TODO 返回并查集的个数
	 * @return:int
	 * @author:Jiangxb
	 * @date: 2019年4月2日 下午4:57:56
	 */
	int getSize();
}
