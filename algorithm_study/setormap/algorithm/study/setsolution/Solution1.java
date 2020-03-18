package algorithm.study.setsolution;

import java.util.ArrayList;
import java.util.TreeSet;


/**
 * leetcode349：两个数组的交集(不包含重复的元素)
 * @Package:algorithm.study.setsolution
 * @ClassName:Solution1
 * @Description:TODO 
 * @author:Jiangxb
 * @date:2019年3月27日 上午10:49:28
 * TreeSet 底层基于【平衡二叉树】的实现
 */
public class Solution1 {
	
	public static int[] intersection(int[] nums1,int[] nums2){
		TreeSet<Integer> treeSet = new TreeSet<>();
		for (Integer num : nums1) {
			treeSet.add(num);
		}
		
		ArrayList<Integer> list = new ArrayList<>();
		for (Integer num : nums2) {
			if(treeSet.contains(num)){
				list.add(num);
				treeSet.remove(num);	// 为了交集里面的元素不重复
			}
		}
		
		int[] res = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			res[i] = list.get(i);
		}
		return res;
	}
	
	public static void main(String[] args) {
		int[] nums1 = {4,9,5};
		int[] nums2 = {4,9,8,9,4};
			
		int[] num = intersection(nums1, nums2);
		System.out.println(num.length);
	}
}
