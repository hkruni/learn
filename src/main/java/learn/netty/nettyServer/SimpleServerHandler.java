package learn.netty.nettyServer;

import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @author
 */
public class SimpleServerHandler extends ChannelInboundHandlerAdapter {


    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("channelActive");

    }

    //收到完整的包才执行包括\r\n
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("receive client message :" + msg.toString());
        ctx.channel().writeAndFlush("is ok\r\n");
        //ctx.close();//服务端关闭channel，客户端的f.channel().closeFuture().sync();会立马返回，然后客户端关闭


//        ServerRequest request = JSONObject.parseObject(msg.toString(),ServerRequest.class);
//        Response resp = new Response();
//        resp.setId(request.getId());//response的ID就是request的ID
//        resp.setResult("is ok");
//        ctx.channel().writeAndFlush(JSONObject.toJSONString(resp));
//        ctx.channel().writeAndFlush("\r\n");
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent)evt;
            if(event.state().equals(IdleState.READER_IDLE)) {
                System.out.println("读空闲");
                ctx.channel().close();
            }else if (event.state().equals(IdleState.WRITER_IDLE)) {
                System.out.println("写空闲");
                ctx.channel().close();


            }else if (event.state().equals(IdleState.ALL_IDLE)) {
                System.out.println("读写空闲");
                ctx.channel().writeAndFlush("ping\r\n");
            }
        }
    }


    //收到客户端消息就行执行，客户端多个writeflush就执行多次
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelReadComplete");
    }
}