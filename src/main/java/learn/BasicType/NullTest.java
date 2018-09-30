package learn.BasicType;

import learn.apacheCommons.Student;


/**
 * && 短路与 即，如果前半部分是false 则后半部分就不会再计算了。
 || 短路 或 即，如果前半部分是true 则后半部分就不会在运算。
 */
public class NullTest {

    public static void main(String[] args) {

        Student s = new Student();
        System.out.println(s.getName());
        s = null;

        if (s == null || s.getName() == null) {
            System.out.println("异常");
        } else {
            System.out.println("正常");
        }
    }
}
