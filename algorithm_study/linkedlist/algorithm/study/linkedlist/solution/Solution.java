package algorithm.study.linkedlist.solution;
/**
 * leetcode 官网203：删除链表中的元素
 * @Package:algorithm.study.solution
 * @ClassName:Solution
 * @Description:TODO 
 * @author:Jiangxb
 * @date:2019年3月15日 下午2:13:03
 *		实例：给定 1-->	2-->6-->3-->4-->5-->6,val = 6,
 *			返回  1-->	2-->3-->4-->5
 */
public class Solution {
	
	/**
	 * 从链表中删除指定的元素(不带虚拟头节点)
	 * @Title:removeElements
	 * @Description:TODO 
	 * @return:ListNode
	 * @author:Jiangxb
	 * @date: 2019年3月15日 下午2:37:22
	 */
	public ListNode removeElements(ListNode head,int val){
		/**
		 * 第一种：要删除的节点正好是头节点；用循环是为了存在多个与头节点一致的元素
		 */
		while(head != null && head.val == val){
			ListNode delNode = head;
			head = head.next;
			delNode.next = null;
			
			// 在leetcode上面可以简化为
//			head = head.next;
		}
		
		/**
		 * 第二种：要删除的节点为链表中间的节点
		 * 	必须先判空，因为也许在第一种情况的时候，链表就为空了。
		 */
		if(head == null){
			return null;
		}
		ListNode prev = head;	// 此时的head头节点一点不会为要删除的节点（因为已经经过了第一种情况）
		while(prev.next != null){
			// 此时prev为待删除节点的上一个节点
			if(prev.next.val == val){
				ListNode delNode = prev.next;
				prev.next = delNode.next;
				delNode.next = null;
				// 在leetcode上面可以简化为
//				prev.next = prev.next.next;
			}else{
				// 当prev.next.val != val时，prev向后挪动一个进行判断
				prev = prev.next;
			}
		}
		return head;
	}
	
	/**
	 * 从链表中删除指定的元素(带虚拟头节点)
	 * @Title:removeElements1
	 * @Description:TODO 
	 * @return:ListNode
	 * @author:Jiangxb
	 * @date: 2019年3月15日 下午2:44:05
	 */
	public ListNode removeElements1(ListNode head,int val){
		
		// 定义虚拟头节点
		ListNode dummyHead = new ListNode(-1);
		dummyHead.next = head;	// 指向真实的头节点
		
		ListNode prev = dummyHead;	// 待删除节点的前一个节点
		while(prev.next != null){
			if(prev.next.val == val){
				ListNode delNode = prev.next;
				prev.next = delNode.next;
				delNode.next = null;
			}else{
				// 元素值不等时，向后挪动一位
				prev = prev.next;
			}
		}
		return dummyHead.next;
	}
	
	/**
	 * @Title:removeElements3
	 * @Description:TODO 采用【递归】解决链表中删除元素的问题
	 * @return:ListNode
	 * @author:Jiangxb
	 * @date: 2019年3月17日 下午12:38:34
	 * 思路分析：搜索【递归解决链表中删除元素问题.png】
	 */
	public ListNode removeElements3(ListNode head,int val){
		/**
		 * 步骤分解：第一步、求解最基本问题
		 */
		if(head == null){
			return null;
		}
		
		/**
		 * 步骤分解：第二步、把原问题转化成更小的问题 
		 */
		ListNode res = removeElements3(head.next,val);
		// 这个就是删除逻辑
		if(head.val == val){
			return res;	// 如果头节点 = 待删除的值，返回头节点后面的元素
		}else{
			head.next = res;
			return head;	// 如果头节点 != 待删除的值，返回头节点和后面的元素
		}
	}
	
	/**
	 * @Title:removeElements4
	 * @Description:TODO 对removeElements3 方法的简化操作
	 * @return:ListNode
	 * @author:Jiangxb
	 * @date: 2019年3月17日 下午12:59:49
	 */
	public ListNode removeElements4(ListNode head,int val){
		// 首先、最基本的操作
		if(head == null){
			return null;
		}
		// 然后、构建递归过程，就是用更小规模的那个问题的解去构建原规模这个问题的解
		head.next = removeElements4(head.next,val);
		 // 最后、构建最终的逻辑——执行删除的操作
		return head.val == val ? head.next : head;
	}
	
	/**
	 * 在本地测试leetcode链表代码
	 * @Title:main
	 * @Description:TODO 
	 * @return:void
	 * @author:Jiangxb
	 * @date: 2019年3月15日 下午2:58:06
	 * 在ListNode里面 添加了两个方法。
	 */
	public static void main(String[] args) {
		int[] nums = {1,2,6,3,4,5,6};
		ListNode head = new ListNode(nums);
		System.out.println(head);
		
		ListNode res = (new Solution().removeElements4(head, 6));
		System.out.println(res);
 	}
}
