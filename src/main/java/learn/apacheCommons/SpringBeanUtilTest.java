package learn.apacheCommons;

import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;

import java.util.Date;

public class SpringBeanUtilTest {


    public static void copy() {
        Student from = new Student();
        from.setName("素材");
        from.setAge(20);
        from.setCreateTime(new Date());
        from.setResult(100);
        from.setTypes(Lists.newArrayList(61,62,63,65));

        Student to = new Student();
        BeanUtils.copyProperties(from,to);

        to.getTypes().add(5);//改变from
        to.setName("新");//不改变from
        to.setCreateTime(new Date());//不改变form，如果是CreateTimeZ再修改就不行
        to.setAge(100);//不改变from
    }


    public static void main(String[] args) {
        copy();

    }

}
