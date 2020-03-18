package algorithm.study.maxheapsolution;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * 解决：leetcode347:前k个高频元素;基于java提供的优先队列【最小堆】
 * @Package:algorithm.study.solution
 * @ClassName:Solution
 * @Description:TODO 
 * @author:Jiangxb
 * @date:2019年3月29日 下午2:22:40
 *	示例：[1,1,1,2,2,3]，k=2，返回[1,2]
 *	java提供的PriorityQueue：内部默认是 【最小堆】
 */
public class Solution1 {
	
	private class Freq implements Comparable<Freq>{
		
        public int e;		// 针对频次<1,3>的key：1 ——(1出现了三次)
        public int freq;	// 针对频次<1,3>的value：3 ——(1出现了三次)

        public Freq(int e, int freq){
            this.e = e;
            this.freq = freq;
        }
        
        /**
         * 对于优先队列来说，是可比较的，因此重写这个类
         * compareTo
         * 	如果指定的数与参数相等返回0；
         * 	如果指定的数小于参数时返回-1；
         * 	如果指定的数大于参数时返回1
         */
        @Override
        public int compareTo(Freq another){
        	// 频次越低 优先级 越高
            if(this.freq > another.freq)
                return 1;
            else if(this.freq < another.freq)
                return -1;
            else
                return 0;
        }
    }
	
	public List<Integer> topKFrequent(int[] nums, int k) {
		// 频次统计<num的值，频次默认1>
		TreeMap<Integer, Integer> treeMap = new TreeMap<>();
		for (int num : nums) {
			if(treeMap.containsKey(num)){
				// 若存在key，则value+1
				treeMap.put(num, treeMap.get(num) + 1);
			}else{
				treeMap.put(num, 1);
			}
		}
		
		// 优先队列(频次越低 优先级 越高)
		PriorityQueue<Freq> pq = new PriorityQueue<>();
        for(int key: treeMap.keySet()){
            if(pq.size() < k)
                pq.add(new Freq(key, treeMap.get(key)));
            else if(treeMap.get(key).compareTo(pq.peek().freq) > 0){
                pq.remove();
                pq.add(new Freq(key, treeMap.get(key)));
            }
            
            if(pq.size() > 0){
				System.out.println(pq.peek().e);	// 测试优先队列 队首
			}
        }

        LinkedList<Integer> res = new LinkedList<>();
        while(!pq.isEmpty())
            res.add(pq.remove().e);
        return res;
	}
	
	private static void printList(List<Integer> nums){
        for(Integer num: nums)
            System.out.print(num + " ");
        System.out.println();
    }
	
	public static void main(String[] args) {
		int[] nums = {1, 2, 2, 3 , 3 ,3};
        int k = 2;
        printList((new Solution1()).topKFrequent(nums, k));
	}
}
