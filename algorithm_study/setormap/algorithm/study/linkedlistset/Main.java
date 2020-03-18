package algorithm.study.linkedlistset;

import java.util.ArrayList;

import algorithm.study.bstset.FileOperation;
/**
 * 对原版书的词汇量 进行统计
 * @Package:algorithm.study.set
 * @ClassName:Main
 * @Description:TODO 
 * @author:Jiangxb
 * @date:2019年3月24日 下午4:22:24
 *
 */
public class Main {
	public static void main(String[] args) {

        System.out.println("Pride and Prejudice");	// 傲慢与偏见

        ArrayList<String> words1 = new ArrayList<>();
        if(FileOperation.readFile("pride-and-prejudice.txt", words1)) {
            System.out.println("Total words: " + words1.size());

            LinkedListSet<String> set1 = new LinkedListSet<>();
            for (String word : words1)
                set1.add(word);
            System.out.println("Total different words: " + set1.getSize());
        }

        System.out.println();


        System.out.println("A Tale of Two Cities");	// 双城记

        ArrayList<String> words2 = new ArrayList<>();
        if(FileOperation.readFile("a-tale-of-two-cities.txt", words2)){
            System.out.println("Total words: " + words2.size());

            LinkedListSet<String> set2 = new LinkedListSet<>();
            for(String word: words2)
                set2.add(word);
            System.out.println("Total different words: " + set2.getSize());
        }
    }
}
