package learn.annotation.demo;


import learn.annotation.face.MethonAnnoatation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class TestMethodAnnotation {


    public static void main(String[] args) {

        Class c = null;
        try {
            c = Class.forName("com.learn.annotation.demo.Student");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Method[] m = c.getDeclaredMethods();
        for(Method method : m) {
            MethonAnnoatation annotation = method.getAnnotation(MethonAnnoatation.class);
            if(annotation != null) {
                System.out.println(annotation.name());
                System.out.println(method.getName());
                System.out.println(method.getReturnType());
            }


        }

    }


}
