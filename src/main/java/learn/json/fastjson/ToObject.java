package learn.json.fastjson;

import com.alibaba.fastjson.JSON;
import learn.json.Instance;
import learn.json.ResultVO;

import java.util.List;

public class ToObject {

    public static void main(String[] args) {
        String jsonStr = "{\"code\":0,\"data\":[{\"address\":\"海淀\",\"age\":28,\"birthDay\":\"2013年06月21日\",\"name\":\"my\",\"score\":23.35},{\"address\":\"西城\",\"age\":29,\"birthDay\":\"2013年06月21日\",\"name\":\"deshuang\"," +
                "\"score\":23.35},{\"address\":\"东城\",\"age\":30,\"birthDay\":\"2013年06月21日\",\"name\":\"shushou\",\"score\":23.35},{\"address\":\"朝阳区\"," +
                "\"age\":31,\"birthDay\":\"2013年06月21日\",\"name\":\"dasao\",\"score\":23.35}],\"msg\":\"成功\"}";

        ResultVO vo = JSON.parseObject(jsonStr,ResultVO.class);
        System.out.println(vo.getCode());
        System.out.println(vo.getMsg());
        for(Instance i : vo.getData()) {
            System.out.println(i);
        }

        System.out.println("-----------泛型------------");
        String dataStr = JSON.parseObject(jsonStr).getString("data");
        List<Instance> list = JSON.parseArray(dataStr,Instance.class);
        for(Instance i : list) {
            System.out.println(i);
        }

    }
}
