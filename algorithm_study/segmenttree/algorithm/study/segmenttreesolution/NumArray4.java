package algorithm.study.segmenttreesolution;

import algorithm.study.segmenttree.SegmentTree;

/**
 * leetcode307. 区域和检索 - 数组可修改
 * @Package:algorithm.study.segmenttreesolution
 * @ClassName:NumArray4
 * @Description:TODO 
 * @author:Jiangxb
 * @date:2019年3月31日 下午1:57:35
 *	利用自定义的线段树来完成这个问题的逻辑 
 */
public class NumArray4 {
 	
	private SegmentTree<Integer> segmentTree;
	
	public NumArray4(int[] nums) {
		// 先做校验
		if(nums.length > 0){
			Integer[] data = new Integer[nums.length];
			for (int i = 0; i < data.length; i++) {
				data[i] = nums[i];
			}
			
			segmentTree = new SegmentTree<>(data, (a,b) -> a+b);
		}
    }
    
	// O(logn)
    public void update(int index, int val) {
    	segmentTree.set(index, val);
    }
    
    // O(logn)
    public int sumRange(int i, int j) {
    	if(segmentTree == null){
    		throw new IllegalArgumentException("Segment Tree is null");
    	}
    	return segmentTree.query(i, j);
    }
    
    public static void main(String[] args) {
    	int[] nums = {-2,0,3,-5,2,-1};
    	
    	NumArray4 obj = new NumArray4(nums);
    	obj.update(1, 1);
    	System.out.println(obj.sumRange(1,2));
	}
}
