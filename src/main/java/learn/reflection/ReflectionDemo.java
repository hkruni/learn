package learn.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hukai on 2018/7/26.
 */
public class ReflectionDemo {

    /**
     * @param args
     */
    public static void main(String[] args) {
        //测试1
        test1();

        //测试2
        test2();
    }

    public static void test1(){

        MemberView fromObject = new MemberView();
        fromObject.setUsername("sunchenbin");
        fromObject.setPassword("111111");
        fromObject.setAge(21);
        fromObject.setGender("男");
        fromObject.setNickname("墨白");


        String[] fields = new String[]{}; // 没有设置属性，默认去对比两个对象
        //String[] fields = new String[]{"username","password","gender"};
        try{
            // 将一个对象转换成另一个对象，并把指定的属性值传递给这个对象，
            // 如果不指定默认去匹配两个对象的属性，存在则赋值
            Member member = (Member) constructObject(fromObject,new Member(),fields);
            System.out.println(member.toString());

            // 执行结果：username:sunchenbin password:111111 nickname:墨白 gender:男 age:21

        }catch (Exception e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void test2(){
        String className = "test.ReflectionDemo";
        String methodName = "eachOrtherToAdd";
        String[] paramTypes = new String[]{"Integer","Double","int"};
        String[] paramValues = new String[]{"1","4.3321","5"};

        // 动态加载对象并执行方法
        initLoadClass(className, methodName, paramTypes, paramValues);
    }

    private static Object constructObject(Object fromObject,Object toObject,String[] fields) throws Exception{
        // 数据源的class
        Class fromClass = fromObject.getClass();
        // 目标的class
        Class toClass = toObject.getClass();

        for(String field:fields){
            try{
                // 获取fromClass的Field
                Field fromField= fromClass.getDeclaredField(field);
                fromField.setAccessible(true);
                // 从fromClass中获取属性的值
                Object fromValue = fromField.get(fromObject);

                // 获取toClass的Field
                Field toField = toClass.getDeclaredField(field);
                toField.setAccessible(true);
                // 将fromClass中该属性的值设置给toClass中的该属性
                toField.set(toObject, fromValue);
            }catch (Exception e) {
                System.out.println(field+"属性不存在");
                // TODO: handle exception
            }

        }

        // 如果没有传递属性过来，那么默认对比from和to中的属性，存在的进行赋值操作
        if(fields.length == 0){
            Field[] fromDeclaredFields = fromClass.getDeclaredFields();
            Field[] toDeclaredFields = toClass.getDeclaredFields();
            List<String> fromList = new ArrayList<String>();
            List<String> toList = new ArrayList<String>();

            // 取出from中所有field
            Arrays.stream(fromDeclaredFields).forEach(field -> {
                field.setAccessible(true);
                fromList.add(field.getName());
            });
            // 取出to中所有field
            Arrays.stream(fromDeclaredFields).forEach(field -> {
                field.setAccessible(true);
                toList.add(field.getName());
            });

            fromList.stream().filter(s -> toList.contains(s)).forEach(s1 -> {
                try {
                    Field fromDeclaredField = fromClass.getDeclaredField(s1);
                    fromDeclaredField.setAccessible(true);
                    Object value = fromDeclaredField.get(fromObject);

                    Field toDeclaredField = toClass.getDeclaredField(s1);
                    toDeclaredField.setAccessible(true);
                    toDeclaredField.set(toObject, value);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            });



            // 循环from属性list
            for (String name : fromList){

                // to中是否包含该属性
                if(toList.contains(name)){

                    // 包含先进行取值
                    Field fromDeclaredField = fromClass.getDeclaredField(name);
                    fromDeclaredField.setAccessible(true);
                    Object value = fromDeclaredField.get(fromObject);

                    // 进行赋值操作
                    Field toDeclaredField = toClass.getDeclaredField(name);
                    toDeclaredField.setAccessible(true);
                    toDeclaredField.set(toObject, value);
                }
            }
        }

        return toObject;
    }

    public double eachOrtherToAdd(Integer one,Double two,Integer three){
        return one+two+three;
    }

    @SuppressWarnings("rawtypes")
    private static void initLoadClass(String className,String methodName,String[] paramTypes,String[] paramValues){
        try{
            // 根据calssName得到class对象
            Class cls = Class.forName(className);

            // 实例化对象
            Object obj = cls.newInstance();

            // 根据参数类型数组得到参数类型的Class数组
            Class[] parameterTypes = constructTypes(paramTypes);

            // 得到方法
            Method method = cls.getMethod(methodName, parameterTypes);

            // 根据参数类型数组和参数值数组得到参数值的obj数组
            Object[] parameterValues = constructValues(paramTypes,paramValues);

            // 执行这个方法并返回obj值
            Object returnValue = method.invoke(obj, parameterValues);

            System.out.println("测试结果2："+returnValue);

        }catch (Exception e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static Object[] constructValues(String[] paramTypes,String[] paramValues){
        Object[] obj = new Object[paramTypes.length];
        for (int i = 0; i < paramTypes.length; i++){
            if(paramTypes[i] != null && !paramTypes[i].trim().equals("")){
                if ("Integer".equals(paramTypes[i]) || "int".equals(paramTypes[i])){
                    obj[i] = Integer.parseInt(paramValues[i]);
                }else if ("Double".equals(paramTypes[i]) || "double".equals(paramTypes[i])){
                    obj[i] = Double.parseDouble(paramValues[i]);
                }else if ("Float".equals(paramTypes[i]) || "float".equals(paramTypes[i])){
                    obj[i] = Float.parseFloat(paramValues[i]);
                }else{
                    obj[i] = paramTypes[i];
                }
            }
        }
        return obj;
    }

    @SuppressWarnings("rawtypes")
    private static Class[] constructTypes(String[] paramTypes){
        Class[] cls = new Class[paramTypes.length];
        for (int i = 0; i < paramTypes.length; i++){
            if(paramTypes[i] != null && !paramTypes[i].trim().equals("")){
                if ("Integer".equals(paramTypes[i]) || "int".equals(paramTypes[i])){
                    cls[i] = Integer.class;
                }else if ("Double".equals(paramTypes[i]) || "double".equals(paramTypes[i])){
                    cls[i] = Double.class;
                }else if ("Float".equals(paramTypes[i]) || "float".equals(paramTypes[i])){
                    cls[i] = Float.class;
                }else{
                    cls[i] = String.class;
                }
            }
        }
        return cls;
    }
}


