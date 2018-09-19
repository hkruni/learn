package learn.apacheCommons;

import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;


public class BeanUtilsTest {



	public static void copyObject() {
		Student s1 = new Student("hukai",31,596,new Date());
		Student s2 = new Student();
		try {
			BeanUtils.copyProperties(s2,s1);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		System.out.println(s2);
	}

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




	public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		Student s = new Student("hukai",23,100,new Date());
		BeanUtils.setProperty(s, "name", "zoulifei");
		BeanUtils.setProperty(s, "age", 32);
		BeanUtils.setProperty(s, "createTime", new Date(System.currentTimeMillis() + 3600000));
		//System.out.println(s);
		populateDate();

		//copyObject();
		//System.out.println(RandomStringUtils.randomAscii(10));

	}

}
