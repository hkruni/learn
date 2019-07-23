package learn.json.jackson;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.collect.Lists;

import com.sun.org.apache.xpath.internal.SourceTree;
import learn.apacheCommons.DateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;


import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by geely
 */
@Slf4j
public class JsonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();
    static{
        //对象的所有字段全部列入，不论null或""
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        //取消默认转换timestamps形式，即不采用时间戳形式
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);
        //忽略空Bean转json的错误
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
        //忽略在json字符串中存在，但是在java对象中不存在对应属性的情况。防止错误
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        //所有的日期格式都统一为以下的样式，即yyyy-MM-dd HH:mm:ss
        objectMapper.setDateFormat(new SimpleDateFormat(DateTimeUtil.STANDARD_FORMAT));
    }


    /**
     * 对象生成json
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String obj2String(T obj){
        if(obj == null){
            return null;
        }
        try {
            return obj instanceof String ? (String)obj :  objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            log.warn("Parse Object to String error",e);
            return null;
        }
    }
    /**
     * 对象生成json（采用优雅格式输出）
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String obj2StringPretty(T obj){
        if(obj == null){
            return null;
        }
        try {
            return obj instanceof String ? (String)obj :  objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (Exception e) {
            log.warn("Parse Object to String error",e);
            return null;
        }
    }




    //json字符串生成对象
    public static <T> T string2Obj(String str,Class<T> clazz){
        if(StringUtils.isEmpty(str) || clazz == null){
            return null;
        }

        try {
            return clazz.equals(String.class)? (T)str : objectMapper.readValue(str,clazz);
        } catch (Exception e) {
            log.warn("Parse String to Object error",e);
            return null;
        }
    }



    public static <T> T string2Obj(String str, TypeReference<T> typeReference){
        if(StringUtils.isEmpty(str) || typeReference == null){
            return null;
        }
        try {
            return (T)(typeReference.getType().equals(String.class)? str : objectMapper.readValue(str,typeReference));
        } catch (Exception e) {
            log.warn("Parse String to Object error",e);
            return null;
        }
    }


    public static <T> T string2Obj(String str,Class<?> collectionClass,Class<?>... elementClasses){
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClass,elementClasses);
        try {
            return objectMapper.readValue(str,javaType);
        } catch (Exception e) {
            log.warn("Parse String to Object error",e);
            return null;
        }
    }






    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        //==============j对象转son字符串===========
        TestPojo testPojo1 = new TestPojo();
        testPojo1.setName("Geely");
        testPojo1.setId(1);
        testPojo1.setDate(new Date());
        TestPojo testPojo2 = new TestPojo();
        testPojo2.setName("John");
        testPojo2.setId(2);
        testPojo2.setDate(new Date());
        String testPojoJson = JsonUtil.obj2String(testPojo1);//转json
        System.out.println(testPojoJson);//{"name":"Geely","id":666,"date":"2018-09-11 11:47:18"}


        //==============json字符串转对象===========
        String json = "{\"name\":\"Geely\",\"id\":666,\"date\":\"2017-03-11 09:28:08\"}";
        TestPojo testPojoObject = JsonUtil.string2Obj(json,TestPojo.class);
        System.out.println(testPojoObject);




        User u1 = new User();
        u1.setId(1);
        u1.setEmail("geely@happymmall.com");
        u1.setCreateTime(new Date());
        String userJsonPretty = JsonUtil.obj2StringPretty(u1);
        System.out.println(userJsonPretty);
        User u2 = new User();
        u2.setId(2);
        u2.setEmail("geelyu2@happymmall.com");



        String user1Json = JsonUtil.obj2String(u2);
        String user1JsonPretty = JsonUtil.obj2StringPretty(u2);
        System.out.println(user1Json);
        System.out.println(user1JsonPretty);


        User user = JsonUtil.string2Obj(user1Json,User.class);


        //===========测试列表List转json============
        System.out.println("===========测试列表List============");
        List<User> userList = Lists.newArrayList();
        userList.add(u1);
        userList.add(u2);
        List<TestPojo> testPojoList = Lists.newArrayList(testPojo1,testPojo2);
        u1.setList(testPojoList);
        String userListStr = JsonUtil.obj2StringPretty(userList);
        System.out.println(userListStr);

        String fastjsonstr = JSON.toJSONString(userList);

        System.out.println("===========测试Json字符串转列表List=，两种方法===========");
        List<User> userListObj1 = JsonUtil.string2Obj(userListStr, new TypeReference<List<User>>() {});
        List<User> userListObj2 = JsonUtil.string2Obj(userListStr,List.class,User.class);
        userListObj1.forEach(System.out::println);

        System.out.println("===========测试BeanUtils===========");
        User u3 = new User();
        BeanUtils.copyProperties(u3,u1);
        System.out.println(u3);

    }





}
