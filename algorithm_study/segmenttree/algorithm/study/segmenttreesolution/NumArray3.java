package algorithm.study.segmenttreesolution;
/**
 * leetcode307. 区域和检索 - 数组可修改
 * @Package:algorithm.study.segmenttreesolution
 * @ClassName:NumArray3
 * @Description:TODO 
 * @author:Jiangxb
 * @date:2019年3月31日 下午1:32:18
 *	不使用线段树进行解决。性能非常差
 */
public class NumArray3 {
	// 借助求和数组来统计nums[]数组的和（预处理的操作）
	private int[] sum;
	// 需要计算的数组
	private int[] data;
 	
	public NumArray3(int[] nums) {
		
		// 初始化
		data = new int[nums.length];
		for (int i = 0; i < nums.length; i++) {
			data[i] = nums[i];
		}
		
        sum = new int[nums.length + 1];
        sum[0] = 0;
        for (int i = 1; i < nums.length; i++) {
			sum[i] = sum[i-1] + data[i-1];
		}
    }
    
	/**
	 * @Title:update
	 * @Description:TODO 修改原数组data第index索引位置的值为val
	 * @return:void
	 * @author:Jiangxb
	 * @date: 2019年3月31日 下午1:47:01
	 * O(n)复杂度
	 */
    public void update(int index, int val) {
        data[index] = val;
        /**
         * 说明：
         * 	因为sum[index] = sum[index-1] + data[index-1]
         * 	所以当修改了data[index]的值时，不会影响sum[index]的值，
         *  而影响的是是index后面的所有数据。故 i = index+1。
         */
        for (int i = index+1; i < sum.length; i++) {
        	sum[i] =sum[i-1] + data[i-1];
		}
    }
    // O(1) 复杂度
    public int sumRange(int i, int j) {
        return sum[j+1] - sum[i];
    }
    
    public static void main(String[] args) {
    	int[] nums = {-2,0,3,-5,2,-1};
    	
    	NumArray3 obj = new NumArray3(nums);
    	obj.update(1, 1);
    	System.out.println(obj.sumRange(1,2));
	}
}
