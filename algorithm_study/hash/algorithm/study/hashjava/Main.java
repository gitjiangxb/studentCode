package algorithm.study.hashjava;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 测试 hashCode值
 * @Package:algorithm.study.hash
 * @ClassName:Main
 * @Description:TODO 
 * @author:Jiangxb
 * @date:2019年4月16日 下午3:29:41
 *
 */
public class Main {
	public static void main(String[] args){
		/**
		 * 情况一：
		 * 	小范围正整数直接使用
		 */
		int a = 42;
		System.out.println(((Integer) a).hashCode());
		
		/**
		 * 情况二：
		 * 	
		 */
		int b = -42;
		System.out.println(((Integer) b).hashCode());
		
		/**
		 * 情况三：
		 * 	浮点型，Double的内部逻辑将其转化为了整型
		 */
		double c = 3.1415926;
        System.out.println(((Double)c).hashCode());
        
        /**
         * 情况四：
         * 	字符串，String的内部逻辑将其转化为了整型
         */
        String d = "imooc";
        System.out.println(d.hashCode());
        
        /**
         * 情况五：
         * 	复合类型，自定义类型
         */
        Student student = new Student(3, 2, "bobo", "Liu");
        System.out.println(student.hashCode());
        /**
         * 测试：
         * 	先注释掉Student类中重写的hashCode()方法，运行会发现student与student2的hashCode值不一样，
         * 		那是因为：默认调用Object类的hashCode()，而Object类的hashCode()是根据每一个对象的地址映射成一个整型。
         * 	
         * 再放开Student类中重写的hashCode()方法，运行会发现student与student2的hashCode值是一样的。
         * 此时发生了hash冲突，我们同样依旧需要比较两个不同的对象他们之间是否是相等的；他们对应的hash函数的值是虽然相等，此时产生了冲突，
         * 	为了辨别两个类的不同，我们需要判断这两个类是否相等。
         * 	重点：如果自定义类要作为hash表的键的话，我们只覆盖一个hashCode()是不够的，我们还需要覆盖equals(Object obj)，判断两个对象是否相等。
         */
        Student student2 = new Student(3, 2, "bobo", "Liu");
        System.out.println(student2.hashCode());
        
        /**
         * 利用java提供的和hash表相关的数据结构：HashSet / HashMap
         * 	传入student时，在具体的存储上会自动调用Student.hashCode(),然后计算出一个索引值，存放到相应的位置中。
         */
        HashSet<Student> set = new HashSet<>();
        set.add(student);

        HashMap<Student, Integer> scores = new HashMap<>();
        scores.put(student, 100);
        
	}
}
