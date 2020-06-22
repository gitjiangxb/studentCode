package designPatterns.util;

import designPatterns.strategy.IReceiptHandleStrategy;

import java.io.File;
import java.io.FileFilter;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.*;

/**
 * @ClassName: ReflectionUtil
 * @Description: TODO  反射工具类
 * @Author: Jiangxb
 * @Date: 2020/06/22 11:11
 * @Version 1.0
 */
public class ReflectionUtil {

    /**
     * 定义类集合(用于存放所有加载的类)
     */
    private static final Set<Class<?>> CLASS_SET;

    static {
        // 指定加载包路径
        CLASS_SET = getClassSet("designPatterns");
    }

    public static Set<Class<?>> getClassSet(){
        return CLASS_SET;
    }

    /**
     * 获取指定包路径下的所有类
     * @param packageName
     * @return
     */
    private static Set<Class<?>> getClassSet(String packageName) {
        Set<Class<?>> classSet = new HashSet<>();
        try {

            Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".","/"));
            while (urls.hasMoreElements()){
                URL url = urls.nextElement();

                if (url != null){
                    // 得到url的协议，比如ftp, http, nntp,file,jar...
                    String protocol = url.getProtocol();

                    if (protocol.equals("file")){
                        String packagePath = url.getPath().replace("%20","");
                        addClass(classSet,packagePath,packageName);
                    } else if(protocol.equals("jar")){

                    }
                }
            }

        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return classSet;
    }

    private static void addClass(Set<Class<?>> classSet, String packagePath, String packageName) {
        final File[] files = new File(packagePath).listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory();
            }
        });

        for (File file : files){
            String fileName = file.getName();
            if (file.isFile()){
                String className = fileName.substring(0, fileName.lastIndexOf("."));
                if (packageName != null || packageName != ""){
                    className = packageName + "." + className;
                }
                doAddClass(classSet,className);
            } else {
                String subPackagePath = fileName;
                if (packagePath != null || packagePath != ""){
                    subPackagePath = packagePath + "/" + subPackagePath;
                }

                String subPackageName = fileName;
                if (packageName != null || packageName != ""){
                    subPackageName = packageName + "." + subPackageName;
                }
                addClass(classSet,subPackagePath,subPackageName);
            }
        }
    }

    private static void doAddClass(Set<Class<?>> classSet, String className) {
        Class<?> cls = loadClass(className,false);
        classSet.add(cls);
    }

    /**
     * 加载类
     * @param className     类全限定名称
     * @param isInitialized 是否在加载完成后执行静态代码块
     * @return
     */
    private static Class<?> loadClass(String className, boolean isInitialized) {
        Class<?> cls;
        try {
            cls = Class.forName(className,isInitialized,getClassLoader());
        } catch (ClassNotFoundException e){
            throw  new RuntimeException(e);
        }
        return cls;
    }

    /**
     * 获取类加载器
     * @return
     */
    private static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }


    /**
     * 获取应用包名下某父类（或接口）的所有子类（或实现类）
     * @param superClass
     * @return
     */
    public static Set<Class<?>> getClassSetBySuper(Class<?> superClass) {
        Set<Class<?>> classSet = new HashSet<>();
        for (Class<?> cls : CLASS_SET) {
            if (superClass.isAssignableFrom(cls) && !superClass.equals(cls)) {
                classSet.add(cls);
            }
        }
        return classSet;
    }

    /**
     * 获取应用包名下带有某注解的类
     * @param annotationClass
     * @return
     */
    public static Set<Class<?>> getClassSetByAnnotation(Class<? extends Annotation> annotationClass) {
        Set<Class<?>> classSet = new HashSet<>();
        for (Class<?> cls : CLASS_SET) {
            if (cls.isAnnotationPresent(annotationClass)) {
                classSet.add(cls);
            }
        }
        return classSet;
    }

    public static void main(String[] args) {
        // 获取IReceiptHandleStrategy接口的实现类
        List<IReceiptHandleStrategy> receiptHandleStrategyList = new ArrayList<>();
        Set<Class<?>> classSetBySuper = ReflectionUtil.getClassSetBySuper(IReceiptHandleStrategy.class);
        if (classSetBySuper != null && classSetBySuper.size() >0){
            for (Class<?> cla : classSetBySuper){
                try {
                    receiptHandleStrategyList.add((IReceiptHandleStrategy) cla.newInstance());
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

        System.out.println(receiptHandleStrategyList.size());
    }
}
