package algorithm.study.set;
/**
 * 自定义集合接口
 * @Package:algorithm.study.set
 * @ClassName:Set
 * @Description:TODO 
 * @author:Jiangxb
 * @date:2019年3月24日 下午3:48:32
 *
 */
public interface Set<E> {
	void add(E e);
	void remove(E e);
	boolean contains(E e);
	int getSize();
	boolean isEmpty();
}
