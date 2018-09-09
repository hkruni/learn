package learn.netty.nettyServer;

/**
 * Created by hukai on 2018/8/22.
 */
public class Response {

    private Long id;
    private Object result;//响应结果

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
