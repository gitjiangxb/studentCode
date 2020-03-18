package algorithm.study.rbtree.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import algorithm.study.avltreemap.AVLMap;
import algorithm.study.bst.BST;
import algorithm.study.rbtree.RBTree;

/**
 * 测试 二分搜索树 / AVL树 / 红黑树
 * @Package:algorithm.study.rbtree.test
 * @ClassName:Main2
 * @Description:TODO 
 * @author:Jiangxb
 * @date:2019年4月12日 下午3:44:41
 *	只测试添加操作,数据随机添加
 */
public class Main2 {
	public static void main(String[] args){
		int n = 20000000;
		
		Random random = new Random();
		ArrayList<Integer> testData = new ArrayList<>(n);
		for (int i = 0; i < n; i++) {
			// 随机添加的数据
			testData.add(random.nextInt(Integer.MAX_VALUE));
		}
		
		// 测试二分搜索树
		long startTime = System.nanoTime();
		BST<Integer, Integer> bst = new BST<>();
		for (Integer data : testData) {
			bst.add(data, null);
		}
		long entTime = System.nanoTime();
		double time = (entTime - startTime) / 1000000000.0;
		System.out.println("BST " + time + " s");
		
		// 测试AVL树
		startTime = System.nanoTime();
		AVLMap<Integer, Integer> avl = new AVLMap<>();
		for (Integer data : testData) {
			avl.add(data, null);
		}
		entTime = System.nanoTime();
		time = (entTime - startTime) / 1000000000.0;
		System.out.println("AVL " + time + " s");
		
		// 测试红黑树
		startTime = System.nanoTime();
		RBTree<Integer, Integer> rbt = new RBTree<>();
		for (Integer data : testData) {
			rbt.add(data, null);
		}
		entTime = System.nanoTime();
		time = (entTime - startTime) / 1000000000.0;
		System.out.println("RBT " + time + " s");
		
	}
}
