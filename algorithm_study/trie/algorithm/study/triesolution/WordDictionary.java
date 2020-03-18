package algorithm.study.triesolution;

import java.util.TreeMap;

/**
 * leetcode211. 添加与搜索单词 - 数据结构设计
 * @Package:algorithm.study.triesolution
 * @ClassName:WordDictionary
 * @Description:TODO 
 * @author:Jiangxb
 * @date:2019年3月31日 下午5:28:45
 *
 */
public class WordDictionary {
	
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
	
	private Node root;
	
	/** Initialize your data structure here. */
    public WordDictionary() {
        root = new Node();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if(cur.next.get(c) == null){
				cur.next.put(c, new Node());
			}
			cur = cur.next.get(c);
		}
        cur.isWord = true;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return match(root, word, 0);
    }
    
    /**
     * @Title:match
     * @Description:TODO 从以node为根中去匹配word字符串中的字符(index索引位置上对应的字符)
     * @return:boolean
     * @author:Jiangxb
     * @date: 2019年3月31日 下午5:35:10
     */
    private boolean match(Node node,String word,int index){
    	/**
    	 * 第一步：递归终止
    	 */
    	if(index == word.length()){
    		return node.isWord;
    	}
    	
    	/**
    	 * 第二步：递归细化
    	 */
    	char c = word.charAt(index);	// 当前考虑的字符
    	if(c != '.'){
    		if(node.next.get(c) == null){
    			return false;
    		}
    		return match(node.next.get(c),word,index +1);
    	}else{
    		/**
    		 * 存在‘.’ 时，对node.next任意匹配一个，因此得到node.next.keySet()的key的集合
    		 */
    		for (char nextChar : node.next.keySet()) {
				if(match(node.next.get(nextChar),word,index+1)){
					return true;
				}
			}
    		return false;
    	}
    }
    
    public static void main(String[] args){
    	WordDictionary wordDictionary = new WordDictionary();
    	wordDictionary.addWord("bad");
    	wordDictionary.addWord("dad");
    	wordDictionary.addWord("mad");
    	System.out.println(wordDictionary.search("pad")); 	// false
    	System.out.println(wordDictionary.search("bad")); 	// true
    	System.out.println(wordDictionary.search(".ad")); 	// true
    	System.out.println(wordDictionary.search("b..")); 	// true
    }
}
