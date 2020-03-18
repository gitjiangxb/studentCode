package algorithm.study.linkedlist.solution;
/**
 * leetcode 官网203：删除链表中的元素 题目给定的ListNode
 * @Package:algorithm.study.solution
 * @ClassName:ListNode
 * @Description:TODO 
 * @author:Jiangxb
 * @date:2019年3月15日 下午2:12:17
 *
 */
public class ListNode {
	public int val;
	public ListNode next;
	
	public ListNode(int x){
		val = x;
	}
	
// 以下方法时为了本地测试代码逻辑而加的	
	
	/**
	 * 链表节点的构造函数
	 * @Title:List
	 * @Description:TODO 
	 * @return:void
	 * @author:Jiangxb
	 * @date: 2019年3月15日 下午2:49:24
	 * 	使用arr为参数，创建一个链表，当前的ListNode为
	 * 链表头节点
	 */
	public ListNode(int[] arr){
		if(arr == null || arr.length ==0){
			throw new IllegalArgumentException("arr can not be empty");
		}
		this.val = arr[0];
		// 头节点
		ListNode cur = this;
		for (int i = 1; i < arr.length; i++) {
			cur.next = new ListNode(arr[i]);
			cur = cur.next;
		}
	}
	
	/**
	 * 以当前节点为头节点的链表信息字符串
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder res = new StringBuilder();
		ListNode cur = this;
		while(cur != null){
			res.append(cur.val + "->");
			cur = cur.next;
		}
		res.append("NULL ");
		return res.toString();
	}
}
