package learn.json.fastjson;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import learn.json.Instance;
import learn.json.ResultVoUtil;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class ToStrUtils {

    public static <T>String fromObjToJson(T o) {

        return null;
    }

    public static void main(String[] args) throws ParseException {

        System.out.println("---------普通对象----------");
        Date date = DateUtils.parseDate("2013-06-21","yyyy-MM-dd");
        Instance instance = new Instance("hukai","海淀区",100,23.35,date);
        System.out.println(JSON.toJSONString(instance));//{"address":"海淀区","age":100,"birthDay":"2013年06月21日","name":"hukai","score":23.35}
        instance = new Instance("hukai",null,100,23.35,date);
        System.out.println(JSON.toJSONString(instance));//{"age":100,"birthDay":"2013年06月21日","name":"hukai","score":23.35}  Fastjson默认对null不进行序列化
        instance = new Instance("hukai",null,null,null,date);
        System.out.println(JSON.toJSONString(instance));

        System.out.println("---------复杂对象----------");
        Instance instance1 = new Instance("my","海淀",28,23.35,date);
        Instance instance2 = new Instance("deshuang","西城",29,23.35,date);
        Instance instance3 = new Instance("shushou","东城",30,23.35,date);
        Instance instance4 = new Instance("dasao","朝阳区",31,23.35,date);
        List<Instance> list = Lists.newArrayList(instance1,instance2,instance3,instance4);
        System.out.println(JSON.toJSONString(ResultVoUtil.success(list)));


        System.out.println("---------别的方法----------");

    }
}
