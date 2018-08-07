package learn.json.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import learn.json.Instance;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;

/**
 * Created by hukai on 2018/7/22.
 */
public class ObjectMapperTest {

    private static  ObjectMapper objectMapper = new ObjectMapper();


    public static String BeanToStr(Object o) {
        try {
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }


    public static void main(String[] args) {
        Instance instance = null;
        try {
            instance = new Instance("hukai","海淀区丹棱街",31,98.56, DateUtils.parseDate("1987-10-21","yyyy-MM-dd"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String  s1 = BeanToStr(instance);
        System.out.println(s1);

    }
}
