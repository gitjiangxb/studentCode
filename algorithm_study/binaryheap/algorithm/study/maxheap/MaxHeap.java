package algorithm.study.maxheap;
/**
 * 基于【动态数组】实现 最大堆
 * @Package:algorithm.study.maxheap
 * @ClassName:MaxHeap
 * @Description:TODO 
 * @author:Jiangxb
 * @date:2019年3月27日 下午4:08:12
 * 
 * @param <E>
 */
public class MaxHeap<E extends Comparable<E>>  {
	private Array<E> data;
	
	public MaxHeap(int capacity){
		data = new Array<>(capacity);
	}
	
	public MaxHeap(){
		data = new Array<>();
	}
	
	public MaxHeap(E[] arr){
		data = new Array<>(arr);
		// 得到最后一个非叶子节点的索引
		int index = parent(arr.length - 1);
		// 从最后一个非叶子节点往前遍历
		for (int i = index; i >= 0; i--) {
			siftDown(i);	// 下沉操作
		}
	}
	
	/**
	 * @Title:size
	 * @Description:TODO 返回堆中的元素个数
	 * @return:int
	 * @author:Jiangxb
	 * @date: 2019年3月27日 下午4:41:57
	 */
	public int size(){
		return data.getSize();
	}
	
	/**
	 * @Title:isEmpty
	 * @Description:TODO 返回一个布尔值，表示堆是否为空
	 * @return:boolean
	 * @author:Jiangxb
	 * @date: 2019年3月27日 下午4:42:35
	 */
	public boolean isEmpty(){
		return data.isEmpty();
	}
	
	/**
	 * @Title:parent
	 * @Description:TODO 返回完全二叉树的数组表示中，一个索引index所表示的元素的父亲节点的索引是？
	 * @return:int
	 * @author:Jiangxb
	 * @date: 2019年3月27日 下午4:43:30
	 */
	private int parent(int index){
		if(index == 0){
			throw new IllegalArgumentException("index-0 doesn`t have parent");
		}
		return (index - 1) / 2;
	}
	
	/**
	 * @Title:leftChild
	 * @Description:TODO 返回完全二叉树的数组表示中，一个索引index所表示的元素的左孩子节点的索引是？
	 * @return:int
	 * @author:Jiangxb
	 * @date: 2019年3月27日 下午4:50:59
	 */
	private int leftChild(int index){
		return index * 2 + 1;
	}
	
	/**
	 * @Title:rightChild
	 * @Description:TODO 返回完全二叉树的数组表示中，一个索引index所表示的元素的右孩子节点的索引是？
	 * @return:int
	 * @author:Jiangxb
	 * @date: 2019年3月27日 下午4:51:26
	 */
	private int rightChild(int index){
		return index * 2 + 2;
	}
	
	/**
	 * @Title:add
	 * @Description:TODO 向堆中添加元素
	 * @return:void
	 * @author:Jiangxb
	 * @date: 2019年3月27日 下午4:57:26
	 */
	public void add(E e){
		// 在数组的后面添加元素
		data.addLast(e);
		
		// 维护堆的性质
		siftUp(data.getSize()-1);
	}

	/**
	 * @Title:siftUp
	 * @Description:TODO 根据插入的元素所在的索引，去满足堆的性质
	 * @return:void
	 * @author:Jiangxb
	 * @date: 2019年3月27日 下午4:58:36
	 * 上浮：父节点 >= 孩子节点
	 */
	private void siftUp(int k) {
		// TODO Auto-generated method stub
		/**
		 * 说明：索引必须大于0，k > 0
		 * 
		 * data.get(parent(k)).compareTo(data.get(k))<0
		 * 该索引对应的父节点的值 小于 该索引的值，堆上浮
		 */
		while(k > 0 && data.get(parent(k)).compareTo(data.get(k))<0){
			// k所在的元素 与 k的父节点元素 进行位置交换
			data.swap(k, parent(k));
			k = parent(k);
		}
	}
	
	/**
	 * @Title:findMax
	 * @Description:TODO 得到堆中的最大元素
	 * @return:E
	 * @author:Jiangxb
	 * @date: 2019年3月27日 下午5:16:18
	 */
	public E findMax(){
		if(data.getSize() == 0){
			throw new IllegalArgumentException("Can not findMax when heap is empty");
		}
		return data.get(0);
	}
	
	/**
	 * @Title:extractMax
	 * @Description:TODO 取出堆中的最大元素
	 * @return:E
	 * @author:Jiangxb
	 * @date: 2019年3月27日 下午5:15:50
	 */
	public E extractMax(){
		E ret = findMax();
		/**
		 * 删除操作
		 */
		data.swap(0, data.getSize()-1);	// 将第一个元素 与 最后一个元素 交换位置；第一个元素变成了最后一个元素
		data.removeLast();	// 删除末尾的元素，最大的元素已经删除了。
		siftDown(0);		// 进行下沉操作(对现在的第一个元素去进行堆性质的满足，下沉)
		return ret;
	}
	
	/**
	 * @Title:siftDown
	 * @Description:TODO 根据索引，去满足堆的性质
	 * @return:void
	 * @author:Jiangxb
	 * @date: 2019年3月27日 下午5:20:49
	 * 下沉：父节点 >= 孩子节点
	 * 注意：当在下沉操作的时候，推顶的元素 与 最后一个元素继续了互换位置，再将最后一个元素删除。
	 * 	这样原最后一个元素就作为了新的堆顶，因而要维护堆的性质。
	 */
	private void siftDown(int k){
		// k索引位置的左孩子 小于 数组的元素个数(越界)。表示没有孩子了
		while(leftChild(k) < data.getSize()){
			// 得到左孩子的索引值（因为能进入循环体，表示左孩子必存在，因此不需要校验）
			int j = leftChild(k);
			/**
			 * j+1 < data.getSize()		// 右孩子所处的索引值 小于 数组的元素个数(说明它有右孩子；否则下标越界退出判断)
			 * 
			 * data.get(j+1).compareTo(data.get(j)) >0
			 * 	// 右孩子的元素值 大于 左孩子的元素值，则j赋值为右孩子的索引值
			 */
			if(j+1 < data.getSize() && data.get(j+1).compareTo(data.get(j)) >0){
				j = rightChild(k);	// j为右孩子的索引值
				// data[j] 是leftChild 和 rightChile 中的最大值
			}
			
			// 若父节点的值 比左右节点中最大的值 还大，说明没有违反堆的性质(父节点 >= 孩子节点)，直接退出；否则继续执行
			if(data.get(k).compareTo(data.get(j)) >= 0){
				break; 
			}
			
			data.swap(k, j);	// 进行交换
			// 继续循环
			k = j;
		}
	}
	
	/**
	 * @Title:replace
	 * @Description:TODO 取出最大的元素，并且替换成元素e
	 * @return:E
	 * @author:Jiangxb
	 * @date: 2019年3月29日 上午10:51:28
	 */
	public E replace(E e){
		E ret = findMax();
		data.set(0, e);		// 将新元素作为堆顶元素
		siftDown(0);		// 新的堆顶元素 也许 违背的堆的性质，因此进行下沉操作
		return ret;
	}
	
	
}
