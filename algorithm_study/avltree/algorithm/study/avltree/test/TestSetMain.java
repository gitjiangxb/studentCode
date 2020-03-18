package algorithm.study.avltree.test;
import java.util.ArrayList;

import algorithm.study.avltreeset.AVLSet;
import algorithm.study.bstset.BSTSet;
import algorithm.study.bstset.FileOperation;
import algorithm.study.linkedlistset.LinkedListSet;
import algorithm.study.set.Set;

/**
 * 性能对比 Set
 * @Package:algorithm.study.avltree.test
 * @ClassName:TestSetMain
 * @Description:TODO 
 * @author:Jiangxb
 * @date:2019年4月8日 上午10:43:55
 *	AVLSet / BSTSet / LinkedListSet
 */
public class TestSetMain {

    private static double testSet(Set<String> set, String filename){

        long startTime = System.nanoTime();

        System.out.println(filename);
        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile(filename, words)) {
            System.out.println("Total words: " + words.size());

            for (String word : words)
                set.add(word);
            System.out.println("Total different words: " + set.getSize());
        }

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {

        String filename = "pride-and-prejudice.txt";

        BSTSet<String> bstSet = new BSTSet<>();
        double time1 = testSet(bstSet, filename);
        System.out.println("BST Set: " + time1 + " s");

        System.out.println();

        LinkedListSet<String> linkedListSet = new LinkedListSet<>();
        double time2 = testSet(linkedListSet, filename);
        System.out.println("Linked List Set: " + time2 + " s");

        System.out.println();

        AVLSet<String> avlSet = new AVLSet<>();
        double time3 = testSet(avlSet, filename);
        System.out.println("AVL Set: " + time3 + " s");
    }
}
