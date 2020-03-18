package juc;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName: AtomicReferenceDemo
 * @Description: TODO   原子引用
 * @Author: Jiangxb
 * @Date: 2020/3/3 21:42
 * @Version 1.0
 */
public class AtomicReferenceDemo {
    public static void main(String[] args) {
        User zs = new User("zhangsan",22);
        User ls = new User("lishi",25);

        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(zs);

        System.out.println(atomicReference.compareAndSet(zs,ls) +
                "\t " + atomicReference.toString());
        System.out.println(atomicReference.compareAndSet(zs,ls) +
                "\t " + atomicReference.toString());
    }
}

class User{
    String username;
    int age;

    public User(String username, int age) {
        this.username = username;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", age=" + age +
                '}';
    }
}
