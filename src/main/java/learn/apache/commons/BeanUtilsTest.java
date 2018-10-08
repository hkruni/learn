package learn.apache.commons;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.lang3.ArrayUtils;


public class BeanUtilsTest {


	/**
	 * 对象复制
	 */
	public static void copyObject() {
		Student s1 = new Student("hukai",31,596,new Date());
		Student s2 = new Student();
		try {
			BeanUtils.copyProperties(s2,s1);//和spring的beanutils参数相反
			System.out.println(s1.getName() == s2.getName());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		System.out.println(s2);
	}

	/**
	 * map类型转bean
	 */
	public static void populate() {
		Student s = new Student();
		Map<String,Object> map = new HashMap<String,Object>(){
			{
				put("name","aaa");
				put("age",30);
				put("result",200);
				put("createTime",new  Date());
			}
		};
		try {
			BeanUtils.populate(s,map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		System.out.println(s);
	}


	public static void populateDate() {

		ConvertUtils.register(new Converter() {
			@Override
			public Object convert(Class aClass, Object o) {
				String s = (String) o;
				//定义格式
				DateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
				try {
					Object result = dateFormat.parse(s); // 将String解析为 Date，将日期变为字符串使用format方法
					return result;
				} catch (ParseException e) {
					e.printStackTrace();
				}
				return null;
			}
		},Date.class);

		Student s = new Student();
		Map<String,Object> map = new HashMap<String,Object>(){
			{
				put("name","aaa");
				put("age",30);
				put("result",200);
				put("createTime","2017年08月23日");
			}
		};
		try {
			BeanUtils.populate(s,map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		System.out.println(s);


	}

	public static void BeanToMap(Student s) {
		Map<String, String> map = new HashMap<>();
		try {
			map = BeanUtils.describe(s);
			for (Map.Entry<String, String> entry : map.entrySet()) {
				System.out.println(entry.getKey() + " : " + entry.getValue());
			}
			//结果，都是字符串
			//result : 100
//			createTime : Sun Sep 09 12:05:18 CST 2018
//			name : hukai
//			class : class learn.apache.commons.Student
//			age : 23
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	//bean转map
	public static Map<String, Object> transBean2Map(Object obj) {

		if(obj == null){
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();
				// 过滤class属性
				if (!key.equals("class")) {
					Method getter = property.getReadMethod();
					Object value = getter.invoke(obj);
					map.put(key, value);
				}

			}
		} catch (Exception e) {
			System.out.println("transBean2Map Error " + e);
		}

		return map;

	}



	//bean转map
	public static Map<String, Object> transBean2MapByPrpperties(Object obj,String [] props) {

		if(obj == null){
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();
				// 过滤class属性
				if (ArrayUtils.contains(props,key)) {
					Method getter = property.getReadMethod();
					Object value = getter.invoke(obj);
					map.put(key, value);
				}

			}
		} catch (Exception e) {
			System.out.println("transBean2Map Error " + e);
		}

		return map;

	}



	public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		Student s = new Student("hukai",23,100,new Date());
//		BeanUtils.setProperty(s, "name", "zoulifei");
//		BeanUtils.setProperty(s, "age", 32);
//		BeanUtils.setProperty(s, "createTime", new Date(System.currentTimeMillis() + 3600000));
		//System.out.println(s);
//		populateDate();

		//copyObject();
		//System.out.println(RandomStringUtils.randomAscii(10));

		//BeanToMap(s);
		//Map<String ,Object> map = transBean2Map(s);
		Map<String ,Object> map = transBean2MapByPrpperties(s,new String[]{"name","createTime"});

		for (Map.Entry<String, Object> entry : map.entrySet()) {
			System.out.println(String.format("%s : %s", entry.getKey(), entry.getValue()));
		}



	}

}
