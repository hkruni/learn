package learn.redis;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.Lists;
import learn.json.jackson.JsonUtil;
import learn.json.jackson.User;
import learn.redis.model.School;
import learn.redis.model.Student;
import learn.redis.model.Teacher;
import redis.clients.jedis.Jedis;

import java.util.List;

public class Xuliehua {

    public static void main(String[] args) {

        Student s1 = new Student("小明",12,"地址1");
        Student s2 = new Student("小王",32,"地址22");
        Student s3 = new Student("小李",70,"地址333");
        List<Student> slist = Lists.newArrayList(s1,s2,s3);

        Teacher t1 = new Teacher("张老师","语文",1);
        Teacher t2 = new Teacher("李老师","英语",1);
        Teacher t3 = new Teacher("于老师","数学",2);

        List<Teacher> tlist = Lists.newArrayList(t1,t2,t3);

        School s = new School();
        s.setName("实验中学");
        s.setHigh(true);
        s.setNumber(100);
        s.setTeacherList(tlist);
        s.setStudentList(slist);

        Jedis jedis = JedisUtil.getJedis();
        String jsonStr = JsonUtil.obj2String(s);
        System.out.println(jsonStr);

        String fastjsonstr = JSON.toJSONString(s);

        jedis.set(s.getName(),fastjsonstr);

        School school = JSON.parseObject(jedis.get(s.getName()),School.class);
        System.out.println(school);

//        School school = JSON.parseObject(fastjsonstr,School.class);






;    }
}
