package reference;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * @ClassName: WeakHashMapDemo
 * @Description: TODO   弱引用的HashMap
 * @Author: Jiangxb
 * @Date: 2020/4/5 10:59
 * @Version 1.0
 */
public class WeakHashMapDemo {
    public static void main(String[] args) {
        MyHashMap();
        System.out.println("----------------");
        MyWeakHashMap();
    }

    private static void MyHashMap() {
        HashMap<Integer,String> map = new HashMap<>();
        Integer key = new Integer(1);
        String value = new String("HashMap");

        map.put(key,value);
        System.out.println(map);

        key = null;
        System.out.println(map);

        System.gc();
        System.out.println(map + "\t" + map.size());
    }

    // 弱引用只要垃圾回收机制一运行，就会被回收
    private static void MyWeakHashMap() {
        WeakHashMap<Integer,String> map = new WeakHashMap<>();
        Integer key = new Integer(2);
        String value = new String("WeakHashMap");

        map.put(key,value);
        System.out.println(map);

        key = null;
        System.out.println(map);

        System.gc();
        System.out.println(map + "\t" + map.size());
    }
}
