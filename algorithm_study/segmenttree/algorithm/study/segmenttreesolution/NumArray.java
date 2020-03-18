package algorithm.study.segmenttreesolution;

import algorithm.study.segmenttree.SegmentTree;

/**
 * leetcode303:区域和检索-不可变
 * @Package:algorithm.study.segmenttreesolution
 * @ClassName:NumArray
 * @Description:TODO 
 * @author:Jiangxb
 * @date:2019年3月30日 下午4:33:31
 * 	利用自定义的线段树来完成这个问题的逻辑 
 *
 */
public class NumArray {
	
	private SegmentTree<Integer> segmentTree;
	
	public NumArray(int[] nums) {
        if(nums.length >0){
        	Integer[] data = new Integer[nums.length];
        	for (int i = 0; i < nums.length; i++) {
        		data[i] = nums[i];
			}
        	segmentTree = new SegmentTree<>(data, (a,b) -> a+b);
        }
    }
    
    public int sumRange(int i, int j) {
    	if(segmentTree == null){
    		throw new IllegalArgumentException("Segment Tree is null");
    	}
        return segmentTree.query(i, j);
    }
    
    public static void main(String[] args) {
    	int[] nums = {-2,0,3,-5,2,-1};
    	NumArray obj = new NumArray(nums);
    	
    	System.out.println(obj.sumRange(0,2));
    	System.out.println(obj.sumRange(2,5));
    	System.out.println(obj.sumRange(0,5));
	}
}
