package com.queue;

import java.util.List;

/**
 * @ClassName: BlockingQueueDemo
 * @Description: TODO 阻塞队列
 * @Author: Jiangxb
 * @Date: 2020/3/18 15:53
 * @Version 1.0
 *  ArrayBlockingQueue：
 *      是一个基于数据结构的有界阻塞队列，此队列按FIFO(先进先出)
 *   原则对元素进行排序
 *  LinkedBlockingQueue：
 *      是一个基于链表结构的阻塞队列，此队列按FIFO（先进先出）排序元素，
 *   吞吐量通常要高于ArrayBlockingQueue。
 *  SynchronousQueue：
 *      一个不存储元素的阻塞队列。每个插入操作必须等到另一个线程调用移除
 *   操作，否则插入操作一直处于阻塞状态，吞吐量通常要高于LinkedBlockingQueue
 *
 * 1、队列
 * 2、阻塞队列
 *  2.1、阻塞队列有没有好的一面
 *      比如：火锅店排队
 *  2.2、不得不阻塞，你如何管理
 *      比如：银行排队、医院抽血排队
 */
public class BlockingQueueDemo {
    public static void main(String[] args) {
        List list = null;
    }
}
