package algorithm.study.avltreeorbst;

import java.util.ArrayList;
import java.util.Collections;

import algorithm.study.avltree.AVLTree;
import algorithm.study.bstset.FileOperation;
/**
 * 测试 二分搜索树 / AVL树
 * @Package:algorithm.study.avltreeorbst
 * @ClassName:Main
 * @Description:TODO 
 * @author:Jiangxb
 * @date:2019年4月12日 下午3:36:50
 *
 */
public class Main {
	public static void main(String[] args){
		System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            /**
             * 考虑最坏的情况，将words里面的单词进行排序后再去构建树
             * 		BST：会退化成链表
             * 		AVL：有自平衡机制
             *	这样测试，效果非常明显
             *		BST: 31.501706071 s
			 *		AVL: 0.103525784 s
             */
            Collections.sort(words);

            // Test BST
            long startTime = System.nanoTime();

            BSTMap<String, Integer> bst = new BSTMap<>();
            for (String word : words) {
                if (bst.contains(word))
                    bst.set(word, bst.get(word) + 1);
                else
                    bst.add(word, 1);
            }

            for(String word: words){
            	bst.contains(word);
            }

            long endTime = System.nanoTime();

            double time = (endTime - startTime) / 1000000000.0;
            System.out.println("BST: " + time + " s");


            // Test AVL Tree
            startTime = System.nanoTime();

            AVLTree<String, Integer> avl = new AVLTree<>();
            for (String word : words) {
                if (avl.contains(word))
                    avl.set(word, avl.get(word) + 1);
                else
                    avl.add(word, 1);
            }

            for(String word: words){
            	avl.contains(word);
            }

            endTime = System.nanoTime();

            time = (endTime - startTime) / 1000000000.0;
            System.out.println("AVL: " + time + " s");
        }

        System.out.println();
	}
}
