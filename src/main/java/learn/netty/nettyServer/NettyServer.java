package learn.netty.nettyServer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.AttributeKey;
import learn.netty.ch6.AuthHandler;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;

import java.net.InetAddress;
import java.util.concurrent.TimeUnit;

/**
 * Created by hukai on 2018/8/21.
 */
public class NettyServer {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);//客户端连接线程
        EventLoopGroup workerGroup = new NioEventLoopGroup();//客户端读写线程,默认线程个数为CPU核心数*2

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)//允许128个通道
                    .childOption(ChannelOption.SO_KEEPALIVE,false)
                    //.childAttr(AttributeKey.newInstance("childAttr"), "childAttrValue")
                    //.handler(new ServerHandler())
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) {
                            ch.pipeline().addLast(new DelimiterBasedFrameDecoder(65535, Delimiters.lineDelimiter()[0]));
                            ch.pipeline().addLast(new StringDecoder());
                            //ch.pipeline().addLast(new IdleStateHandler(6,4,2, TimeUnit.SECONDS));
                            ch.pipeline().addLast(new SimpleServerHandler());
                            ch.pipeline().addLast(new StringEncoder());


                        }
                    });

            ChannelFuture f = b.bind(8888).sync();
//            CuratorFramework client = ZookeeperFactory.create();
//            InetAddress inetAddress =InetAddress.getLocalHost();
//            System.out.println(inetAddress.getHostAddress());
//            System.out.println(client);
//            client.create().withMode(CreateMode.EPHEMERAL).forPath(Constant.Server_PATH + inetAddress.getHostAddress());

            f.channel().closeFuture().sync();
        }catch (Exception e) {
            e.printStackTrace();
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
