package algorithm.study.hashsolution;
/**
 * leetcode387. 字符串中的第一个唯一字符
 * @Package:algorithm.study.hashsolution
 * @ClassName:Solution
 * @Description:TODO 
 * @author:Jiangxb
 * @date:2019年4月13日 下午10:44:12
 *	注意事项：您可以假定该字符串只包含小写字母。
 */
public class Solution {
	public static int firstUniqChar(String s) {
        int[] freq = new int[26];
        /**
         * 说明：
         * 	因为题目说明了，只包含26个小写字母，因此这里申请了长度为26的数组。
         * 	s.charAt(i) - 'a'是得到当前字母所在的索引值：
         * 		如当前字母为b,那么b - 'a' = 1,因此freq[1]保存的就是b这个字母出现的次数
         * 
         * a 的ASCII = 97
         */
        for (int i = 0; i < s.length(); i++) {
        	// 完整的写法
//        	freq[s.charAt(i) - 'a'] = freq[s.charAt(i) - 'a'] + 1;
			freq[s.charAt(i) - 'a'] ++;
		}
        
        /**
         * 按照插入字符的字母顺序去一一对比，若该字母索引位置上的值为1，则表示第一个唯一字符
         */
        for (int i = 0; i < s.length(); i++) {
			if(freq[s.charAt(i) - 'a'] == 1){
				return i;
			}
		}
        return -1;
    }
	
	public static void main(String[] args){
		Solution solution = new Solution();
		String str = "leetcode";
		System.out.println(solution.firstUniqChar(str));
	}
}
