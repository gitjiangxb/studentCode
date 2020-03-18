package algorithm.study.trieorset;

import java.util.ArrayList;
import java.util.Iterator;

import algorithm.study.bstset.BSTSet;
import algorithm.study.bstset.FileOperation;
import algorithm.study.trie.Trie;

/**
 * 对比基于【二分搜索树】 实现 集合 与 基于【java提供的TreeMap】实现Trie
 * @Package:algorithm.study.trieorset
 * @ClassName:Main
 * @Description:TODO 
 * @author:Jiangxb
 * @date:2019年3月31日 下午4:55:42
 *	都去存储字符串，比较性能差异；比较方式：读取原版读物的单词
 */
public class Main {
	public static void main(String[] args){
		System.out.println("Pride and Prejudice");
		
		ArrayList<String> words = new ArrayList<>();
		if(FileOperation.readFile("pride-and-prejudice.txt", words)){
			/**
             * BSTSet
             */
			long startTime = System.nanoTime();
			BSTSet<String> set = new BSTSet<>();
			for(String word : words){
				set.add(word);
			}
			
			for(String word : words){
				set.contains(word);
			}
			
			long endTime = System.nanoTime(); 
			double time = (endTime - startTime) / 1000000000.0;
			System.out.println("Total different words: " + set.getSize());
            System.out.println("BSTSet: " + time + " s");
            
            /**
             * Trie
             */
            startTime = System.nanoTime();
            Trie trie = new Trie();
            for(String word: words)
                trie.add(word);

            for(String word: words)
                trie.contains(word);

            endTime = System.nanoTime();
            time = (endTime - startTime) / 1000000000.0;
            System.out.println("Total different words: " + trie.getSize());
            System.out.println("Trie: " + time + " s");
		}
	}
}
