package algorithm.study.trie;

import java.util.TreeMap;

/**
 * 基于【java提供的TreeMap】实现Trie
 * @Package:algorithm.study.trie
 * @ClassName:Trie
 * @Description:TODO 
 * @author:Jiangxb
 * @date:2019年3月31日 下午3:51:59
 *
 */
public class Trie {
	
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
	private int size;	// 多少个单词
	
	public Trie(){
		root = new Node();
		size = 0;
	}
	
	/**
	 * @Title:getSize
	 * @Description:TODO 获得Trie中存储的单词数
	 * @return:int
	 * @author:Jiangxb
	 * @date: 2019年3月31日 下午4:16:00
	 */
	public int getSize(){
		return size;
	}
	
	/**
	 * @Title:add
	 * @Description:TODO 向Trie中添加一个新的单词word
	 * @return:void
	 * @author:Jiangxb
	 * @date: 2019年3月31日 下午4:17:12
	 * 练习：将这个方法改成递归写法？
	 */
	public void add(String word){
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
			size ++;
		}
	}
	
	/**
	 * @Title:contains
	 * @Description:TODO 查询单词word是否在Trie中
	 * @return:boolean
	 * @author:Jiangxb
	 * @date: 2019年3月31日 下午4:38:34
	 * 练习：将这个方法改成递归写法？
	 */
	public boolean contains(String word){
		
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
	
	/**
	 * @Title:isPrefix
	 * @Description:TODO 查询是否在Trie中有单词以prefix为前缀
	 * @return:boolean
	 * @author:Jiangxb
	 * @date: 2019年3月31日 下午5:09:22
	 * 	一个单词也是这个单词的前缀
	 */
	public boolean isPrefix(String prefix){
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
	
	/**
	 * @Title:remove
	 * @Description:TODO 在Trie中删除单词word
	 * @return:boolean
	 * @author:Jiangxb
	 * @date: 2019年4月2日 下午3:06:15
	 */
	public boolean remove(String word){
		if(!contains(word)){
			return false;	// 不包含这个字符串的时候，直接返回
		}
		
		// 从头开始遍历找到这个单词的最后一个字符所在位置
		Node cur = root;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if(cur.next.get(c) == null){
				return false;
			}
			cur = cur.next.get(c);
		}
		
		// 若当前cur为字符串结尾字符 并且 存在下一个时，只需要将isWord改为false即可
		if(cur.isWord && cur.next.keySet().size() != 0){
			cur.isWord = false;
			size--;
		}else{
			root.next.remove(word.charAt(0));
			size--;
		}
		return true;
	}
	
	public static void main(String[] args){
		Trie trie = new Trie();
		trie.add("apple");
		trie.add("app");
		trie.add("w");
		System.out.println(trie.contains("app"));
		System.out.println(trie.remove("app"));
		System.out.println(trie.contains("app"));
		System.out.println(trie.size);
	}
}
