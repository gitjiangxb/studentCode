package algorithm.study.triesolution;

import java.util.TreeMap;

public class MapSum {
	
	private class Node{
		public int value;
		public TreeMap<Character, Node> next;
		
		public Node(int value){
			this.value = value;
			next = new TreeMap<>();
		}
		
		public Node(){
			this(0);
		}
	}
	
	private Node root;
	 /** Initialize your data structure here. */
    public MapSum() {
        root = new Node();
    }
    
    public void insert(String word, int val) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
        	char c = word.charAt(i);
        	if(cur.next.get(c) == null){
        		cur.next.put(c, new Node());
        	}
        	cur = cur.next.get(c);
		}
        cur.value = val;
    }
    
    public int sum(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
        	char c = prefix.charAt(i);
        	if(cur.next.get(c) == null){
        		return 0;
        	}
        	/**
        	 * 找到prefix前缀所在的位置
        	 * 如：prefix = ‘ap’，此时cur为p这个节点。
        	 *  需要遍历以p这个节点为根的所有子节点的value相加
        	 */
        	cur = cur.next.get(c);	
		}
        
        return sum(cur);
    }
    
    /**
     * @Title:sum
     * @Description:TODO 遍历node 以及 node所有子树，将其的value加起来
     * @return:int
     * @author:Jiangxb
     * @date: 2019年4月1日 下午2:42:32
     */
    private int sum(Node node){
    	/**
    	 * 第一步：递归终止条件
    	 * 	node表示为叶子节点
    	 */
    	if(node.next.size() == 0){
    		return node.value;
    	}
    	
    	/**
    	 * 第二步：递归细化
    	 * 	不为叶子节点
    	 */
    	// 得到node的value值
    	int res = node.value;
    	
    	// 遍历node的所有子树
    	for (char c : node.next.keySet()) {
    		res += sum(node.next.get(c));
		}
    	
    	return res;
    }
    
    public static void main(String[] args){
    	MapSum mapSum = new MapSum();
    	mapSum.insert("apple", 3);
//    	System.out.println(mapSum.sum("ap"));
    	mapSum.insert("app", 2);
    	System.out.println(mapSum.sum("ap"));
    }
}
