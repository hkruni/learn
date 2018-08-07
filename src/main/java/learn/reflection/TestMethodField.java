package learn.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by hukai on 2018/7/26.
 */
public class TestMethodField {

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {
        Member member = new Member();
        member.setUsername("hkruni");
        member.setAge(18);
        member.setGender("男");
        member.setPassword("19980101@qq");
        member.setNickname("kai");
        member.setTruename("胡凯");

        Class c = member.getClass();

        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            System.out.println("字段名称 ：" + field.getName() + "字段的值 :" + field.get(member));
            if (field.getName().equals("age"))
                field.set(member,30);
            else
                field.set(member,"测试");
        }
        System.out.println(member);

        System.out.println("------------------");
        Method[] methods = c.getDeclaredMethods();
        for (Method method : methods) {
            String methodName = method.getName();
            System.out.println("方法名称 ：" + methodName);
            if (methodName.startsWith("set") && !methodName.contains("Age")) {
                method.invoke(member,"新值");
            }

            if (methodName.startsWith("get") && !methodName.contains("Age")) {
                String result = (String) method.invoke(member);
                System.out.println("method 获取值:" + result);
            }
        }

        System.out.println(member);

    }


}
