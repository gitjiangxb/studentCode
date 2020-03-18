package algorithm.study.avltreeset;

import algorithm.study.avltree.AVLTree;
import algorithm.study.set.Set;

/**
 * 基于【AVL】	实现Set
 * @Package:algorithm.study.avltreeset
 * @ClassName:AVLSet
 * @Description:TODO 
 * @author:Jiangxb
 * @date:2019年4月8日 上午10:42:39
 * 
 * @param <E>
 */
public class AVLSet<E extends Comparable<E>> implements Set<E> {
	
	private AVLTree<E, Object> avl;

    public AVLSet(){
        avl = new AVLTree<>();
    }

    @Override
    public int getSize(){
        return avl.getSize();
    }

    @Override
    public boolean isEmpty(){
        return avl.isEmpty();
    }

    @Override
    public void add(E e){
        avl.add(e, null);
    }

    @Override
    public boolean contains(E e){
        return avl.contains(e);
    }

    @Override
    public void remove(E e){
        avl.remove(e);
    }
}
