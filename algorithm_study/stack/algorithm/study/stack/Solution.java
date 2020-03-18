package algorithm.study.stack;

/**
 * 解决leetcode官网第20题号的题目：括号匹配
 * @Package:algorithm.study.stack
 * @ClassName:Solution
 * @Description:TODO 
 * @author:Jiangxb
 * @date:2019年3月13日 上午11:23:45
 *	给定一个只包含'(',')','{','}','[',']'的字符串，判断字符串是否有效
 *		如：”[]“ 有效，”(]“ 无效
 */
public class Solution {
	
	public static void main(String[] args) {
		Solution solution = new Solution();
		String str = "({{})";
		System.out.println(solution.isValid(str));
	}
	
	public boolean isValid(String s){
		// 创建栈(JDK带的)
//		Stack<Character> stack = new Stack<>();
		// 使用自己创建的基于数组实现的栈
		ArrayStack<Character> stack = new ArrayStack<>();
		// 按照字符长度进行循环
		for (int i = 0; i < s.length(); i++) {
			// 取出字符(当前比较字符)
			char c = s.charAt(i);
			if(c == '(' || c == '{' || c == '['){
				stack.push(c);
			}else{
				// 如果栈为空，返回false
				if(stack.isEmpty()){
					return false;
				}
				// 出栈字符(栈顶)
				char topChar = stack.pop();
				
				if(topChar != '(' && c == ')')
					return false;
				if(topChar != '[' && c == ']')
					return false;
				if(topChar != '{' && c == '}')
					return false;
			}
		}
		
		// 不为空时，还是不匹配，说明栈里字符没得匹配
		return stack.isEmpty();
	}
}
