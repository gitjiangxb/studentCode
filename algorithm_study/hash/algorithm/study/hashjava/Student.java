package algorithm.study.hashjava;
/**
 * 复合类中 重写hashCode()
 * @Package:algorithm.study.hash
 * @ClassName:Student
 * @Description:TODO 
 * @author:Jiangxb
 * @date:2019年4月16日 下午3:29:01
 *	想将这个类 作为 hash表中的键进行存储的话，需要将这个类转化为hash函数
 */
public class Student {
	int grade;	// 年级
	int cls;	// 班级
	String firstName;
	String lastName;
	
	Student(int grade, int cls, String firstName, String lastName){
        this.grade = grade;
        this.cls = cls;
        this.firstName = firstName;
        this.lastName = lastName;
    }
	
	/**
	 * 当这里不去覆盖的时候，会调用Object类的hashCode()方法
	 * 	Object类的hashCode()，是根据每一个对象的地址映射成一个整型。
	 */
	@Override
	public int hashCode() {
		// hash(date) = (((Date.year % M) * B + Date.month) % M * B + Date.day) % M
		
		int B = 31;
		int hash = 0;
		
		hash = hash * B + ((Integer)grade).hashCode();		// 为了防止
		hash = hash * B + ((Integer)cls).hashCode();
		hash = hash * B + firstName.toLowerCase().hashCode();	// 不区分大小写，BOBO / bobo 同一个人
		hash = hash * B + lastName.toLowerCase().hashCode();
		
		return hash;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj)	// 同一个引用
            return true;

        if(obj == null)
            return false;

        if(getClass() != obj.getClass())
            return false;

        // 最后来比较类的各个属性值是否相同
        Student another = (Student)obj;
        return this.grade == another.grade &&
                this.cls == another.cls &&
                this.firstName.toLowerCase().equals(another.firstName.toLowerCase()) &&
                this.lastName.toLowerCase().equals(another.lastName.toLowerCase());
	}
	
}
