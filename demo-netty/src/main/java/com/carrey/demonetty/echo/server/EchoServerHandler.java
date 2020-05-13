package com.carrey.demonetty.echo.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.util.CharsetUtil;

/**
 * @author Conway
 * @className EchoServerHandler
 * @description 业务核心处理逻辑 EchoServerHandler
 * @date 2020/5/13 2:07 下午
 */
@ChannelHandler.Sharable//1.表示一个ChannelHandler可以被多个Channel安全的共享
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * channelRead() - 每个信息入站都会调用
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //2.ByteBuf是一个存储字节的容器，最大特点就是使用方便，它既有自己的读索引和写索引，方便你对整段字节缓存进行读写，
        //3.也支持get/set，方便你对其中每一个字节进行读写
        ByteBuf in = (ByteBuf)msg;
        //4.日志消息输出到控制台
        String researchMessage = in.toString(io.netty.util.CharsetUtil.UTF_8);
        System.out.println("Server received:接收到消息【 " + researchMessage + " 】");
        ctx.fireChannelRead(msg);
        //5.将所接收的消息返回给发送者。注意，这还没有冲刷数据
        ctx.write(in);
    }

    /**
     * channelReadComplete() - 通知处理器最后的 channelRead() 是当前批处理中的最后一条消息时调用
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //6.冲刷所有待审消息到远程节点。关闭通道后，操作完成
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                .addListener(ChannelFutureListener.CLOSE);
    }

    /**
     * exceptionCaught()- 读操作时捕获到异常时调用
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        //7.打印异常堆栈跟踪
        cause.printStackTrace();
        //8.关闭通道
        ctx.close();
    }
}
