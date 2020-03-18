package algorithm.study.setsolution;

import java.util.ArrayList;
import java.util.TreeMap;


/**
 * leetcode350：两个数组的交集(包含重复的元素)
 * @Package:algorithm.study.setsolution
 * @ClassName:Solution1
 * @Description:TODO 
 * @author:Jiangxb
 * @date:2019年3月27日 上午10:49:28
 * 	TreeMap 底层基于【平衡二叉树】的实现
 *	这里可以用集合来实现，这里用映射来实现
 */
public class Solution2 {
	
	public static int[] intersection(int[] nums1,int[] nums2){
		// key：元素 ，value：频次
		TreeMap<Integer, Integer> treeMap = new TreeMap<>();
		
		for (int num : nums1) {
			if(!treeMap.containsKey(num)){
				treeMap.put(num, 1);	// 不包含这个key时，频次赋值1
			}else{
				treeMap.put(num, treeMap.get(num) + 1);	// 若存在，value+1
			}
		}
		
		// 用来记录交集
		ArrayList<Integer> list = new ArrayList<>();
		
		for (int num : nums2) {
			// treeMap中存在这个key的时候，添加到list中
			if(treeMap.containsKey(num)){
				list.add(num);
				treeMap.put(num, treeMap.get(num) - 1);	// 若找到一次，则修改频次-1
				if(treeMap.get(num) == 0){
					treeMap.remove(num);	// 若频次等于0，将其删除。
				}
			}
		}
		
		int[] res = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			res[i] = list.get(i);
		}
		return res;
	}
	
	public static void main(String[] args) {
		int[] nums1 = {4,9,5,8};
		int[] nums2 = {4,9,8,9,4};
			
		int[] num = intersection(nums1, nums2);
		System.out.println(num.length);
	}
}
