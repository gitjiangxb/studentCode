package algorithm.study.avltreemap;

import algorithm.study.avltree.AVLTree;
import algorithm.study.map.Map;
/**
 * 基于【AVL】实现Map
 * @Package:algorithm.study.avltreemap
 * @ClassName:AVLMap
 * @Description:TODO 
 * @author:Jiangxb
 * @date:2019年4月8日 上午10:37:39
 * 
 * @param <K>
 * @param <V>
 */
public class AVLMap<K extends Comparable<K>, V> implements Map<K, V> {
	
	private AVLTree<K, V> avl;

    public AVLMap(){
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
    public void add(K key, V value){
        avl.add(key, value);
    }

    @Override
    public boolean contains(K key){
        return avl.contains(key);
    }

    @Override
    public V get(K key){
        return avl.get(key);
    }

    @Override
    public void set(K key, V newValue){
        avl.set(key, newValue);
    }

    @Override
    public V remove(K key){
        return avl.remove(key);
    }
}
