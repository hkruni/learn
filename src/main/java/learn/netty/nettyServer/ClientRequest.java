package learn.netty.nettyServer;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by hukai on 2018/8/22.
 */
public class ClientRequest {

    private final long id;//每个请求的唯一ID识别

    private Object content;//发送内容

    private final AtomicLong aid = new AtomicLong(1);

    public ClientRequest() {
        id = aid.incrementAndGet();
    }

    public Object getContent() {
        return content;
    }

    public AtomicLong getAid() {
        return aid;
    }

    public long getId() {
        return id;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
