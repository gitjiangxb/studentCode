package algorithm.study.linkedlistset;

import algorithm.study.set.Set;

/**
 * 基于【链表】 实现set集合
 * @Package:algorithm.study.linkedlistset
 * @ClassName:LinkedListSet
 * @Description:TODO 
 * @author:Jiangxb
 * @date:2019年3月24日 下午5:26:31
 *
 */
public class LinkedListSet<E> implements Set<E> {

	private LinkedList<E> linkedList;
	
	public LinkedListSet() {
		linkedList = new LinkedList<>();
	}
	
	@Override
	public void add(E e) {
		// TODO Auto-generated method stub
		/**
		 * 这里需要处理重复的元素；因为在链表的实现中，并没有限制不能输入重复的元素
		 * 所以这里需要先判断是否存在插入的元素e，若存在则不插入
		 */
		// 当不存在时，再添加进去
		if(!linkedList.contains(e)){
			linkedList.addFirst(e);
		}
	}

	@Override
	public void remove(E e) {
		// TODO Auto-generated method stub
		linkedList.removeElement(e);
	}

	@Override
	public boolean contains(E e) {
		// TODO Auto-generated method stub
		return linkedList.contains(e);
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return linkedList.getSize();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return linkedList.isEmpty();
	}
	
}
