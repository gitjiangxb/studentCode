package algorithm.study.segmenttree;

public class Main {
	public static void main(String[] args){
		Integer[] nums = {-2,0,3,-5,2,-1};
		// 也可利用JDK的Lambda表达式 代替 匿名内部类
//		SegmentTree<Integer> segmentTree = new SegmentTree<>(nums, (a,b) -> a+b);
		SegmentTree<Integer> segmentTree = new SegmentTree<>(nums, new Merger<Integer>() {
			@Override
			public Integer meger(Integer a, Integer b) {
				// 求和
				return a + b;
			}
		});
		System.out.println(segmentTree);
		
		System.out.println(segmentTree.query(0, 2));
	}
}
