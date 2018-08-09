package learn.apache.commons;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.MethodUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hukai on 2018/8/7.
 */
public class FieldUtilTest {

    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        Student student = new Student("hukai",12,100,new Date());

        String name = (String)FieldUtils.readDeclaredField(student,"name",true);
        Integer age = (Integer)FieldUtils.readDeclaredField(student,"age",true);
        Date createTime = (Date)FieldUtils.readDeclaredField(student,"createTime",true);
        System.out.println(name);
        System.out.println(age);
        System.out.println(createTime);

        List<Field> list = FieldUtils.getAllFieldsList(student.getClass());
        list.stream().map(s -> s.getName()).forEach(System.out::println);
        FieldUtils.writeDeclaredField(student,"age",31,true);
        System.out.println(student);


        System.out.println("-------method-------");
        //getDeclaredMethods返回当前类内部定义的所有方法
        Arrays.stream(student.getClass().getDeclaredMethods()).map(m -> m.getName()).forEach(System.out::println);
        //返回类内部和继承的所有public方法
        Arrays.stream(student.getClass().getMethods()).map(m -> m.getName()).forEach(System.out::println);
        Method method = Student.class.getDeclaredMethod("getName");
        Method method2 = Student.class.getDeclaredMethod("setName",String.class);
        String name1  = (String)method.invoke(student,new Object[]{});
        method2.invoke(student,"lifei");
        System.out.println(name1);
        System.out.println(student);


    }
}
