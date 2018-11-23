package learn.netty.nettyServer;

import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.util.AttributeKey;

/**
 * @author
 */
public class SimpleClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String message = msg.toString();
        System.out.println("receive server message : " + message);
        if (message.contains("ok")) {
            Thread.sleep(1000);
            ctx.channel().writeAndFlush("haha");
            ctx.channel().writeAndFlush("12345");
            //ctx.channel().writeAndFlush("\r\n");
        }

//        if ("ping".equals(msg.toString())) {
//            ctx.channel().writeAndFlush("ping\r\n");
//            return;
//        }
        //ctx.channel().attr(AttributeKey.valueOf("sssss")).set(msg);
        //ctx.channel().close();

//        Response response = JSONObject.parseObject(msg.toString(),Response.class);
//        DefaultFuture.receive(response);


    }
}