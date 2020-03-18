package algorithm.study.maxheapqueue;

import algorithm.study.maxheap.MaxHeap;

/**
 * 基于【最大堆】实现优先队列
 * @Package:algorithm.study.maxheapqueue
 * @ClassName:priorityQueue
 * @Description:TODO 
 * @author:Jiangxb
 * @date:2019年3月29日 下午1:50:18
 *
 */
public class PriorityQueue<E extends Comparable<E>> implements Queue<E>{
	
	private MaxHeap<E> maxHeap;
	
	public PriorityQueue(){
		maxHeap = new MaxHeap<>();
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return maxHeap.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return maxHeap.isEmpty();
	}

	@Override
	public void enqueue(E e) {
		// TODO Auto-generated method stub
		maxHeap.add(e);
	}

	@Override
	public E dequeue() {
		// TODO Auto-generated method stub
		return maxHeap.extractMax();
	}

	@Override
	public E getFront() {
		// TODO Auto-generated method stub
		// 队首元素 == 就是堆顶的最大元素
		return maxHeap.findMax();
	}
	
}
