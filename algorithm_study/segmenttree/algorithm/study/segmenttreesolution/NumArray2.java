package algorithm.study.segmenttreesolution;
/**
 * leetcode303:区域和检索-不可变
 * @Package:algorithm.study.segmenttreesolution
 * @ClassName:NumArray2
 * @Description:TODO 
 * @author:Jiangxb
 * @date:2019年3月30日 下午4:40:38
 *	不使用线段树 来处理,因为这里的数据是静态的
 */
public class NumArray2 {
	
	// sum[i]存储前i个元素和；sum[0] = 0;
	// sum[i]存储num[0,i-1]的和
	private int[] sum;
	
	public NumArray2(int[] nums) {
        sum = new int[nums.length + 1];
        sum[0] = 0;
        for (int i = 1; i < sum.length; i++) {
        	/**
        	 * 说明：
        	 * 		sum[1] = sum[0] + nums[0]
        	 * 		sum[2] = sum[1] + nums[1]
        	 * 		sum[3] = sum[2] + nums[2]
        	 * 		....
        	 */
        	sum[i] = sum[i-1] + nums[i-1];
		}
        
        // 测试打印 sum中的和
        for (int i = 0; i < sum.length; i++) {
        	System.out.print(sum[i] + ",");
		}
    }
    
    public int sumRange(int i, int j) {
    	System.out.println();
    	/**
    	 * 举例：sumRange(1,2)
    	 * ==》sum[3] - sum[1]
    	 */
        return sum[j+1] - sum[i];
    }
    
    public static void main(String[] args) {
    	int[] nums = {-2,0,3,-5,2,-1};
    	
    	NumArray2 obj = new NumArray2(nums);
    	
    	System.out.println(obj.sumRange(1,2));
//    	System.out.println(obj.sumRange(2,5));
//    	System.out.println(obj.sumRange(0,5));
	}
}
