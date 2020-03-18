package algorithm.study.avltree;

import java.util.ArrayList;

import javax.management.RuntimeErrorException;

import algorithm.study.bstset.FileOperation;

/**
 * 测试 【AVLTree】
 * @Package:algorithm.study.avltree
 * @ClassName:Main
 * @Description:TODO 
 * @author:Jiangxb
 * @date:2019年4月7日 上午11:14:55
 *
 */
public class Main{
	public static void main(String[] args) {
		System.out.println("Pride and prejudice");
		
		ArrayList<String> words = new ArrayList<>();
		if(FileOperation.readFile("pride-and-prejudice.txt", words)){
			System.out.println("Total words : " + words.size());
			
			AVLTree<String, Integer> avlTree = new AVLTree<>();
			for (String word : words) {
				if(avlTree.contains(word)){
					// 先判断该单词是否存在，若存在则取出value+1
					avlTree.set(word, avlTree.get(word) + 1);
				}else{
					avlTree.add(word, 1);
				}
			}
			System.out.println("Total different words: " + avlTree.getSize());
			System.out.println("可以很方便的快速知道某一个词的词频：" + avlTree.get("pride"));
			
			System.out.println("is BST : " + avlTree.isBST());
			
			System.out.println("is Balanced : " + avlTree.isBalanced());
			
			for (String word : words){
				avlTree.remove(word);	// 执行删除操作
				if(!avlTree.isBST() || !avlTree.isBalanced()){
					// 当不满足二分搜索树 或者 平衡二叉树的性质时，抛出异常
					throw new RuntimeException("error");
				}
			}
		}
	}
}
