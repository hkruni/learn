package learn.netty.nettyServer;

/**
 * Created by hukai on 2018/8/22.
 */
public class ServerRequest {

    private Long id;//客户端请求ID

    private Object content;//客户端内容

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
