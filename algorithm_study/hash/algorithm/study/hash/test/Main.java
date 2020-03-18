package algorithm.study.hash.test;

import java.util.ArrayList;
import java.util.Collections;

import algorithm.study.avltree.AVLTree;
import algorithm.study.avltreeorbst.BSTMap;
import algorithm.study.bstset.FileOperation;
import algorithm.study.hash.HashTable;
import algorithm.study.rbtree.RBTree;

/**
 * 测试二分搜索树 / AVL树 / 红黑树	/ hash表(HashTable)
 * @Package:algorithm.study.hash.test
 * @ClassName:Main
 * @Description:TODO 
 * @author:Jiangxb
 * @date:2019年4月17日 上午9:31:20
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
             *		BST: 22.856811841 s
			 *		AVL: 0.094246362 s
			 *		RBT: 0.080776175 s
             */
//            Collections.sort(words);

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
            
         // Test RBTree
            startTime = System.nanoTime();

            RBTree<String, Integer> rbt = new RBTree<>();
            for (String word : words) {
                if (rbt.contains(word))
                	rbt.set(word, rbt.get(word) + 1);
                else
                	rbt.add(word, 1);
            }

            for(String word: words){
            	rbt.contains(word);
            }

            endTime = System.nanoTime();

            time = (endTime - startTime) / 1000000000.0;
            System.out.println("RBT: " + time + " s");
            
         // Test HashTable，扩容的大小维护在两倍
            startTime = System.nanoTime();

            /**
             * HashTable<String, Integer> hashTable = new HashTable<>();
             * 	说明：这里采用默认的大小：97，会发生非常多的hash冲突，因此导致了速度偏慢
             * HashTable<String, Integer> hashTable = new HashTable<>(131071);
             * 	这里为其指定了空间的大小。指定了大小的hashTable效率比较好。因此在使用的时候，应当估算下
             * 		这个M(素数)的大小。然后在很多情况下是无法提前知道M的大小的，该如何处理啦？
             */
            HashTable<String, Integer> hashTable = new HashTable<>();
//            HashTable<String, Integer> hashTable = new HashTable<>(131071);
            
            for (String word : words) {
                if (hashTable.contains(word))
                	hashTable.set(word, hashTable.get(word) + 1);
                else
                	hashTable.add(word, 1);
            }

            for(String word: words){
            	hashTable.contains(word);
            }

            endTime = System.nanoTime();

            time = (endTime - startTime) / 1000000000.0;
            System.out.println("hashTable: " + time + " s");
            
         // Test HashTable，扩容的大小维护在 素数
            startTime = System.nanoTime();

            algorithm.study.hashmore.HashTable<String, Integer> hashTable1 = new algorithm.study.hashmore.HashTable<>();
            
            for (String word : words) {
                if (hashTable1.contains(word))
                	hashTable1.set(word, hashTable1.get(word) + 1);
                else
                	hashTable1.add(word, 1);
            }

            for(String word: words){
            	hashTable1.contains(word);
            }

            endTime = System.nanoTime();

            time = (endTime - startTime) / 1000000000.0;
            System.out.println("hashTable1: " + time + " s");
        }

        System.out.println();
	}
}
