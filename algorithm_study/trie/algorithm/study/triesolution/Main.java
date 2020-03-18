package algorithm.study.triesolution;

import java.util.ArrayList;
import java.util.HashMap;

import algorithm.study.bstmap.BSTMap;
import algorithm.study.bstset.FileOperation;

/**
 * 词频性能对比：基于 【二分搜索树】实现映射Map   与      基于【java提供的TreeMap】实现Trie
 * @Package:algorithm.study.triesolution
 * @ClassName:Main
 * @Description:TODO 
 * @author:Jiangxb
 * @date:2019年4月1日 下午4:40:50
 *
 */
public class Main {
	private static int testBSTMap(String str){
		int wordCount = 0;	// 词频数
		long startTime = System.nanoTime();
		ArrayList<String> words = new ArrayList<>();
		if(FileOperation.readFile("a-tale-of-two-cities.txt", words)){
			BSTMap<String, Integer> map = new BSTMap<>();
			for (String word : words) {
				if(map.contains(word)){
					// 先判断该单词是否存在，若存在则取出value+1
					map.set(word, map.get(word) + 1);
				}else{
					map.add(word, 1);
				}
			}
			System.out.println("Total different words: " + map.getSize());
			wordCount = map.get(str);
		}
		long endTime = System.nanoTime();
		System.out.println("BSTMap消耗时间：" + (endTime - startTime) / 100000000.0);
		return wordCount;
	}
	
	private static int testHashMap(String str){
		int wordCount = 0;	// 词频数
		long startTime = System.nanoTime();
		ArrayList<String> words = new ArrayList<>();
		if(FileOperation.readFile("a-tale-of-two-cities.txt", words)){
			HashMap<String, Integer> map = new HashMap<>();
			for (String word : words) {
				if(map.containsKey(word)){
					// 先判断该单词是否存在，若存在则取出value+1
					map.put(word, map.get(word) + 1);
				}else{
					map.put(word, 1);
				}
			}
			System.out.println("Total different words: " + map.size());
			wordCount = map.get(str);
		}
		long endTime = System.nanoTime();
		System.out.println("HashMap消耗时间：" + (endTime - startTime) / 100000000.0);
		return wordCount;
	}
	
	private static int testTrie(String str){
		int count = 0;	// 词频数
		long startTime = System.nanoTime();
		ArrayList<String> words = new ArrayList<>();
		if(FileOperation.readFile("a-tale-of-two-cities.txt", words)){
			WordCount wordCount = new WordCount();
			for(String word: words)
				wordCount.add(word);
			System.out.println("Total different words: " + wordCount.getSize());
			count = wordCount.getCount(str);
		}
		long endTime = System.nanoTime();
		System.out.println("Trie消耗时间：" + (endTime - startTime) / 100000000.0);
		return count;
	}
	
	public static void main(String[] args){
		System.out.println("a-tale-of-two-cities");
		System.out.println();
		System.out.println("to这个单词的词频是：" + Main.testHashMap("to"));
		System.out.println();
		System.out.println("to这个单词的词频是：" + Main.testBSTMap("to"));
		System.out.println();
		System.out.println("to这个单词的词频是：" + Main.testTrie("to"));
	}
}
