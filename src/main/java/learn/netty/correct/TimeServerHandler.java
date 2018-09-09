/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package learn.netty.correct;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author lilinfeng
 * @date 2014年2月14日
 * @version 1.0
 */
public class TimeServerHandler extends ChannelHandlerAdapter {

    private int counter;

	/**
	 * 读取客户端的消息体
	 * @param ctx
	 * @param msg
	 * @throws Exception
	 */
    public void channelRead(ChannelHandlerContext ctx, Object msg)
	    throws Exception {
		ByteBuf byteBuf = (ByteBuf) msg;
		byte[] req = new byte[byteBuf.readableBytes()];
		byteBuf.readBytes(req);
		String body = new String (req,"UTF-8");
		System.out.println("The time server receive order : " + body
			+ " ; the counter is : " + ++counter);
		String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new java.util.Date(
			System.currentTimeMillis()).toString() : "BAD ORDER";
		currentTime = currentTime + System.getProperty("line.separator");
		ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
		ctx.channel().writeAndFlush(resp);
    }

	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	/**
	 * 在读取操作期间，有异常抛出时会调用
	 * @param ctx
	 * @param cause
	 */
	@Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		ctx.close();
    }
}
