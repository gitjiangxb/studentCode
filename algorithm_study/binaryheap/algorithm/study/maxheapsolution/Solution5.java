package algorithm.study.maxheapsolution;

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
public class Solution5 {
	
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
		PriorityQueue<Integer> pq = new PriorityQueue<>(
				// jdk1.8 提供的Lambda 表达式，来替代Solution4中的匿名类
				(a,b) -> treeMap.get(a) - treeMap.get(b)
		);
		
        for(int key: treeMap.keySet()){
            if(pq.size() < k)
                pq.add(key);
            else if(treeMap.get(key).compareTo(treeMap.get(pq.peek())) > 0){
                pq.remove();
                pq.add(key);
            }
            
            if(pq.size() > 0){
				System.out.println(treeMap.get(pq.peek()));	// 测试优先队列 队首
			}
        }

        LinkedList<Integer> res = new LinkedList<>();
        while(!pq.isEmpty())
            res.add(pq.remove());
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
        printList((new Solution5()).topKFrequent(nums, k));
	}
}
