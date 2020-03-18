package algorithm.study.linkedlistmap;

import java.util.ArrayList;

import algorithm.study.bstmap.BSTMap;
import algorithm.study.bstset.FileOperation;

/**
 * 测试 基于【链表】实现 映射类Map
 * @Package:algorithm.study.linkedlistmap
 * @ClassName:Main
 * @Description:TODO 
 * @author:Jiangxb
 * @date:2019年3月26日 下午3:05:57
 *
 */
public class Main {
	public static void main(String[] args) {
		
		BSTMap<String, Integer> map = new BSTMap<>();
		map.add("a", 1);
		map.add("b", 2);
		map.add("c", 3);
		System.out.println(map.getSize());
		map.remove("b");
		System.out.println(map.getSize());

		/*System.out.println("Pride and prejudice");
		
		ArrayList<String> words = new ArrayList<>();
		if(FileOperation.readFile("pride-and-prejudice.txt", words)){
			System.out.println("Total words : " + words.size());
			
			LinkedListMap<String, Integer> map = new LinkedListMap<>();
			for (String word : words) {
				if(map.contains(word)){
					// 先判断该单词是否存在，若存在则取出value+1
					map.set(word, map.get(word) + 1);
				}else{
					map.add(word, 1);
				}
			}
			System.out.println("Total different words: " + map.getSize());
			System.out.println("可以很方便的快速知道某一个词的词频：" + map.get("pride"));
		}*/
	}
}
