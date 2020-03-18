package algorithm.study.hash;

import java.util.TreeMap;

/**
 * 基于【TreeMap,红黑树】实现哈希表
 * @Package:algorithm.study.hash
 * @ClassName:HashTable
 * @Description:TODO 
 * @author:Jiangxb
 * @date:2019年4月16日 下午5:23:39
 *
 */
public class HashTable<K,V> {
	// TreeMap数组
	private TreeMap<K,V>[] hashtable;
	// hashTable长度
	private int size;
	// hashTable存储了多少个元素(这个值对性能的影响非常大)
	private int M;
	
	/**
	 * 扩容/缩容 操作
	 */
	private static final int upperTol = 10;		// 平均每个元素冲突的元素个数 上界为10
	private static final int lowerTol = 2;		// 平均每个元素冲突的元素个数 下界为2
	private static final int initCapacity = 7;	//	默认的初始容量
	
	@SuppressWarnings("unchecked")
	public HashTable(int M){
		this.M = M;
		size = 0;
		hashtable = new TreeMap[M];
		for (int i = 0; i < M; i++) {
			// 初始化数组的每个索引位置都是一个TreeMap类型
			hashtable[i] = new TreeMap<>();
		}
	}
	
	public HashTable(){
		this(initCapacity);	// 默认的大小
	}
	
	/**
	 * @Title:hash
	 * @Description:TODO 得到key的索引值
	 * @return:int
	 * @author:Jiangxb
	 * @date: 2019年4月16日 下午5:32:38
	 */
	private int hash(K key){
		// 做一个按位与操作，去除最高位，也就是符号位；再模上M，为的是使哈希值均匀分布
		return (key.hashCode() & 0x7fffffff) % M;
	}
	
	/**
	 * @Title:getSize
	 * @Description:TODO 得到hax表中的元素个数
	 * @return:int
	 * @author:Jiangxb
	 * @date: 2019年4月16日 下午5:33:22
	 */
	public int getSize(){
		return size;
	}
	
	/**
	 * @Title:add
	 * @Description:TODO 添加元素
	 * @return:void
	 * @author:Jiangxb
	 * @date: 2019年4月17日 上午9:03:20
	 */
	public void add(K key,V value){
		// TreeMap暂存hashtable该hash(key)索引位置上的值
		TreeMap<K, V> map = hashtable[hash(key)];
		
		if(map.containsKey(key)){
			map.put(key, value);
		}else{
			map.put(key, value);
			size ++;
			
			/**	
			 * N / M >= upperTol ==》 N >= upperTol * M
			 */
			if(size >= upperTol * M){
				resize( 2 * M);	// 扩容
			}
		}
	}
	
	/**
	 * @Title:remove
	 * @Description:TODO 删除key索引位置上的值
	 * @return:V
	 * @author:Jiangxb
	 * @date: 2019年4月17日 上午9:22:53
	 */
	public V remove(K key){
		// TreeMap暂存hashtable该hash(key)索引位置上的值
		TreeMap<K, V> map = hashtable[hash(key)];
		V ret = null;
		if(map.containsKey(key)){
			ret = map.remove(key);
			size --;
			
			/**
			 * N / M < lowerTol ==》 N < lowerTol * M
			 * 在缩容的时候还要处理边界值:
			 * 		M / 2 >= initCapacity
			 */
			if(size < lowerTol * M && M / 2 >= initCapacity){
				resize(M / 2);	// 缩容
			}
		}
		return ret;
	}
	

	/**
	 * @Title:set
	 * @Description:TODO 修改key索引位置上的值为value
	 * @return:void
	 * @author:Jiangxb
	 * @date: 2019年4月17日 上午9:24:27
	 */
	public void set(K key,V value){
		// TreeMap暂存hashtable该hash(key)索引位置上的值
		TreeMap<K, V> map = hashtable[hash(key)];
		
		// 若不存在时，抛出异常
		if(!map.containsKey(key)){
			throw new IllegalArgumentException(key + " doesn't exist!");
		}
		map.put(key, value);
	}
	
	/**
	 * @Title:contains
	 * @Description:TODO 判断是否存在key这个索引的值
	 * @return:boolean
	 * @author:Jiangxb
	 * @date: 2019年4月17日 上午9:25:27
	 */
	public boolean contains(K key){
		return hashtable[hash(key)].containsKey(key);
	}
	
	/**
	 * @Title:get
	 * @Description:TODO 根据key得到key对应的值
	 * @return:V
	 * @author:Jiangxb
	 * @date: 2019年4月17日 上午9:28:12
	 */
	public V get(K key){
		return hashtable[hash(key)].get(key);
	}
	
	/**
	 * @Title:resize
	 * @Description:TODO 扩容 /缩容 操作
	 * @return:void
	 * @author:Jiangxb
	 * @date: 2019年4月17日 上午10:43:10
	 */
	private void resize(int newM) {
		// 创建一个新的平衡数 数组
		TreeMap<K, V>[]  newHashTable = new TreeMap[newM];
		
		for (int i = 0; i < newM; i++) {
			// 初始化数组的每个索引位置都是一个TreeMap类型
			newHashTable[i] = new TreeMap<>();
		}
		
		/**
		 * 将原hashtable中的内容，放入newHashTable中
		 */
		int oldM = M;	// 细节说明：保存扩容/缩容 之前的M大小
		this.M = newM;	// 细节说明：因为hash()这个方法里面是模上M，而这里的M大小已经改变了(newM)
		for (int i = 0; i < oldM; i++) {
			TreeMap<K, V> map = hashtable[i]; // 得到原hashtable索引位置上的值
			/**
			 * 一个索引位置上的值保存的形式：
			 * 	{anonymous=1, away=1, for=1, language=1,。。。。}
			 */
			for(K key : map.keySet()){
				// 取出该索引位置中的全部key，再一一求hash值，再放入新的newHashTable中
				newHashTable[hash(key)].put(key, map.get(key));
			}
		}
		
		this.hashtable = newHashTable;
	}
}
