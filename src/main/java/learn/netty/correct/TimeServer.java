/*
 * Copyright 2013-2018 Lilinfeng.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package learn.netty.correct;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @author lilinfeng
 * @date 2014年2月14日
 * @version 1.0
 */
public class TimeServer {

    public void bind(int port) throws Exception {
		//接收客户端accept请求的线程组
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		//处理具体业务（读写事件）的线程组
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {

			ServerBootstrap b = new ServerBootstrap();

			//Bootstrap通过职责链模式启动
			b.group(bossGroup, workerGroup)//绑定两个线程组
				.channel(NioServerSocketChannel.class)//指定哪种类型的通道
				.childHandler(new ChildChannelHandler());//给读写事件的线程通道绑定handler去处理读写事件

			ChannelFuture f = b.bind(port).sync();

			// 等待服务端监听端口关闭
			f.channel().closeFuture().sync();
		} finally {

			// 优雅退出，释放线程池资源
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
    }

    private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
		/**
		 * 拦截链加入各种handler
		 * @param arg0
		 * @throws Exception
		 */
		@Override
		protected void initChannel(SocketChannel arg0) throws Exception {
			arg0.pipeline().addLast(new TimeServerHandler());
		}
    }

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
		int port = 8088;
		if (args != null && args.length > 0) {
			try {
				port = Integer.valueOf(args[0]);
			} catch (NumberFormatException e) {
			// 采用默认值
			}
		}
		new TimeServer().bind(port);
    }
}
