package algorithm.study.linkedlist.solution;
/**
 * 递归实现 求数组元素的和
 * @Package:algorithm.study.linkedlist.solution
 * @ClassName:Sum
 * @Description:TODO 
 * @author:Jiangxb
 * @date:2019年3月15日 下午4:09:55
 * 说明：1、求解最基本问题
 * 		2、把原问题转化成更小的问题
 */
public class Sum {
	
	public static void main(String[] args) {
		int[] arr = {1,2,3,4,5,6,7,8};
		System.out.println(new Sum().sum(arr));
	}
	
	/**
	 * @Title:sum
	 * @Description:TODO 这个方法公开给用户
	 * @return:int
	 * @author:Jiangxb
	 * @date: 2019年3月15日 下午4:17:06
	 */
	public static int sum(int[] arr){
		return sum(arr,0);
	}
	
	/**
	 * @Title:sum
	 * @Description:TODO 计算arr[l...n)这个区间内所有元素的和
	 * @return:int
	 * @author:Jiangxb
	 * @date: 2019年3月15日 下午4:11:11
	 * 注意：在写递归函数的时候一定要注重【递归函数本身】的语意。递归函数就是一个函数，完成某项功能
	 * 	对于sum()函数来说：就是计算arr[l...n)范围里的数字和。
	 */
	private static int sum(int[] arr,int l){
		// 数组为空
		if(l == arr.length){
			return 0;	// <——求解最基本问题
		}
		return arr[l] + sum(arr,l+1); // <——把原问题转化成更小的问题 
	}
}
