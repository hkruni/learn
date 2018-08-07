package learn.reflection;

import java.lang.reflect.Method;


/**
 * getDeclaredMethod*()获取的是类自身声明的所有方法，包含public、protected和private方法。
 getMethod*()获取的是类的所有共有方法，这就包括自身的所有public方法，和从基类继承的、从接口实现的所有public方法。
 */
public class TestConstruct {

    public static void main(String[] args) throws Exception  {
        Class c = Class.forName("learn.reflection.Member");

        Member member = (Member) c.newInstance();

        Method setUsername = c.getDeclaredMethod("setUsername",new Class[]{String.class});
        setUsername.invoke(member,"hu");

        Method getUsername = c.getDeclaredMethod("getUsername",new  Class[]{});
        String username = (String) getUsername.invoke(member);
        System.out.println(username);


        Method getLength = c.getMethod("getLength",new Class[]{String.class,String.class});
        Integer count = (Integer) getLength.invoke(member,new String[]{"1","222"});
        System.out.println(count);



    }
}

