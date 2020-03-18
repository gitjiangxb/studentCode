package algorithm.study.array;

public class Main {
	public static void main(String[] args) {
		Array2<Integer> arr = new Array2<>();
		for (int i = 0; i < 10; i++) {
			arr.addLast(i);
		}
		System.out.println(arr);
		
		// 在索引为1的位置添加元素100
		arr.add(1, 9);
		System.out.println(arr);
		
		arr.add(1, 99);
		System.out.println(arr);
		
		arr.removeAllElement(9);
		arr.removeAllElement(1);
		arr.removeAllElement(2);
		arr.removeAllElement(3);
		arr.removeAllElement(4);
		arr.removeAllElement(8);
		

		
//		System.out.println(arr.removeLast());
		System.out.println(arr);
		
		
	}
}
