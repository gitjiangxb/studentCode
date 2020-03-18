package algorithm.study.setsolution;

import java.util.TreeSet;


/**
 * leetcode 804 号问题：唯一摩尔斯密码词
 * @Package:algorithm.study.setsolution
 * @ClassName:Solution
 * @Description:TODO 
 * @author:Jiangxb
 * @date:2019年3月25日 下午4:15:58
 * 借助java提供的TreeSet来实现；
 * 	TreeSet 底层是一个平衡二叉树，基于【红黑树】实现的
 *
 */
public class Solution {
	public static int uniqueMorseRepresentations(String[] words) {
		// 26个英文字母对应摩尔斯密码表
        String[] codes = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        // 利用JDK提供的TreeSet
        TreeSet<String> set = new TreeSet<>();
        for (String word : words) {
			StringBuilder res = new StringBuilder();
			// 遍历单词字母，根据字母ASCII编码 - a(97)，得到索引，取出对应字母的摩尔斯密码
			for (int i = 0; i < word.length(); i++) {
				res.append(codes[word.charAt(i) - 'a']);
			}
			set.add(res.toString());
		}
        return set.size();
	}
	
	public static void main(String[] args) {
		String[] words = {"gin","zen","gig","msg"};
		int num = uniqueMorseRepresentations(words);
		System.out.println(num);
	}
}
