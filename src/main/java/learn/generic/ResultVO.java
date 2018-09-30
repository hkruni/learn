package learn.generic;

import learn.annotation.demo.Student;
import lombok.Data;
import org.omg.CORBA.PUBLIC_MEMBER;

@Data
public class ResultVO<T> {

    private Integer code;
    private String  msg;
    private T data;

    public static ResultVO LOGIN_FAIL = new ResultVO(100,"登录失败",null);
    public static ResultVO AUTH_FAIL = new ResultVO(200,"权限失败",null);
    public static ResultVO SERVER_FAIL = new ResultVO(300,"服务异常",null);

    public static Integer SUCCESS = 1;

    public ResultVO() {
    }

    public ResultVO(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }




    @Override
    public String toString() {
        return "ResultVO{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public static void main(String[] args) {
        ResultVO<Student> vo = new ResultVO();//这样只能set Student

        System.out.println(ResultVO.LOGIN_FAIL);


    }


}
