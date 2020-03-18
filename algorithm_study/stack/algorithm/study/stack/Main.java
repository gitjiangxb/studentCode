package algorithm.study.stack;

public class Main {
	public static void main(String[] args) {
		ArrayStack<Integer> stack = new ArrayStack<>();
		
		for (int i = 0; i < 5; i++) {
			// ÈëÕ»
			stack.push(i);
			System.out.println(stack);
		}
		
		// ³öÕ»
		stack.pop();
		System.out.println(stack);
		
		System.out.println("Õ»¶¥£º" + stack.peek());
	}
}
