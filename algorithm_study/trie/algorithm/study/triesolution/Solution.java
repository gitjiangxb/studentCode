package algorithm.study.triesolution;

import java.util.TreeMap;

/**
 * leetcode208. 实现 Trie (前缀树)
 * @Package:algorithm.study.triesolution
 * @ClassName:Solution
 * @Description:TODO 
 * @author:Jiangxb
 * @date:2019年3月31日 下午5:17:02
 *
 */
public class Solution {
	private class Node{
		// 当前这个节点是否表示一个单词的结尾
		public boolean isWord;
		public TreeMap<Character, Node> next;
		
		public Node(boolean isWord){
			this.isWord = isWord;
			next = new TreeMap<>();
		}
		
		public Node(){
			this(false);
		}
	}
	
	private Node root;	// 根节点
	
	public Solution(){
		root = new Node();
	}
	
	public void insert(String word){
		// cur待操作节点
		Node cur = root;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);	// 提取单词中的字符
			
			// 不包含这个映射时，则创建
			if(cur.next.get(c) == null){
				cur.next.put(c, new Node());
			}
			
			// 包含了则 直接找到
			cur = cur.next.get(c);
		}
		
		// 以前这个节点并不表示任何一个单词的结尾，这样我们才认为这是一个新的单词
		if(!cur.isWord){
			cur.isWord = true;
		}
	}
	
	public boolean search(String word){
		
		Node cur = root;	// 当前操作的节点，根节点
		
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if(cur.next.get(c) == null){
				return false;	// 为null表示不存在
			}
			cur = cur.next.get(c);
		}
		
		return cur.isWord;	// 的看是否以这个单词的最后一个字符结尾
	}
	
	public boolean startsWith(String prefix){
		Node cur = root;
		for (int i = 0; i < prefix.length(); i++) {
			char c = prefix.charAt(i);
			if(cur.next.get(c) == null){
				return false;
			}
			cur = cur.next.get(c);
		}
		return true;
	}
	
	public static void main(String[] args){
		Solution trie = new Solution();
		trie.insert("apple");
		
		System.out.println(trie.search("apple"));   // 返回 true
		System.out.println(trie.search("app"));     // 返回 false
		
		System.out.println(trie.startsWith("app")); // 返回 true
		trie.insert("app");   
		System.out.println(trie.search("app"));     // 返回 true
	}
}
