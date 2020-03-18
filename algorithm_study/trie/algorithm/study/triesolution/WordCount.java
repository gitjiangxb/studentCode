package algorithm.study.triesolution;

import java.util.ArrayList;
import java.util.TreeMap;

import algorithm.study.bstset.FileOperation;


/**
 * 基于【字典树】实现词频统计功能
 * @Package:algorithm.study.triesolution
 * @ClassName:WordCount
 * @Description:TODO 
 * @author:Jiangxb
 * @date:2019年4月1日 下午3:58:27
 *
 */
public class WordCount {
	private class Node{
		public int count;		// 统计词频，默认为1
		public boolean isWord;	// 是否为一个单词的结尾，默认为false
		public TreeMap<Character, Node> next;	// 这样也可以使用HashMap来实现，HashMap更快
		
		public Node(int count,boolean isWord){
			this.count = count;
			this.isWord = isWord;
			next = new TreeMap<>();
		}
		
		public Node(){
			this(1,false);
		}
	}
	
	private int size;
	private Node root;
	
	public WordCount(){
		root = new Node();
		size = 0;
	}
	
	public int getSize(){
		return size;
	}
	
	public void add(String word){
		Node cur = root;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if(cur.next.get(c) == null){
				cur.next.put(c, new Node());
			}
			cur = cur.next.get(c);	// 表示为添加单词的最后一个字符
		}
		// 若这个字符不为单词的结尾，则表示第一次加入，修改为true并维护size
		if(!cur.isWord){
			cur.isWord = true;
			size ++;
		}else{
			cur.count = cur.count + 1;
		}
	}
	
	/**
	 * @Title:getCount
	 * @Description:TODO 根据字符串word得到词频数
	 * @return:int
	 * @author:Jiangxb
	 * @date: 2019年4月1日 下午4:26:14
	 */
	public int getCount(String word){
		Node cur = root;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if(cur.next.get(c) == null){
				return 0;	// 为null表示不存在
			}
			cur = cur.next.get(c);
		}
		return cur.count;
	}
	
	public static void main(String[] args){
		System.out.println("Pride and Prejudice");
		
		ArrayList<String> words = new ArrayList<>();
		if(FileOperation.readFile("pride-and-prejudice.txt", words)){
			WordCount wordCount = new WordCount();
			for(String word: words)
				wordCount.add(word);
			System.out.println("Total different words: " + wordCount.getSize());
			System.out.println("可以很方便的快速知道某一个词的词频：" + wordCount.getCount("pride"));
		}
	}
}
