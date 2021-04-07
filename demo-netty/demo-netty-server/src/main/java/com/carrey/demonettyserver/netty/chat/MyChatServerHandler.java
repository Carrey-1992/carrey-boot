package com.carrey.demonettyserver.netty.chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.apache.commons.lang3.StringUtils;

import java.net.SocketAddress;
import java.text.SimpleDateFormat;

/**
 * @author Conway
 * @className MyChatServerHandler
 * @description
 * @date 2021/3/14 6:47 下午
 */
public class MyChatServerHandler extends SimpleChannelInboundHandler<String> {

    //创建一个单一线程的ChannelGroup
    private static DefaultChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("[客户端]" + channel.remoteAddress() + " 上线了 " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new
                java.util.Date()) + "\n");
        channelGroup.add(channel);
        System.out.println(ctx.channel().remoteAddress() + " 上线了"+ "\n");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.remove(channel);
        channelGroup.writeAndFlush("[客户端]" + channel.remoteAddress() + " 下线了 " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new
                java.util.Date()) + "\n");
        System.out.println(ctx.channel().remoteAddress() + " 下线了"+ "\n");
        System.out.println("channelGroup size=" + channelGroup.size());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        Channel channel = channelHandlerContext.channel();
        final String sendAddress = s.contains("@") ? s.substring(s.indexOf("@")+1) : "";
        final String msg = s.contains("@") ? s.substring(0,s.indexOf("@")) : s;
        channelGroup.forEach(ch -> {
            SocketAddress socketAddress = ch.remoteAddress();
            if (ch.equals(channel)) {
                ch.writeAndFlush("[本地端口] " + " 发送了："+ msg + " "+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new
                        java.util.Date()) + "\n");
            } else if(StringUtils.isNotBlank(sendAddress) && sendAddress.equals(socketAddress.toString())) {//回显自己发送的消息给自己
                ch.writeAndFlush("[客户端] " + channel.remoteAddress() +" 私自向我发送了："+ msg +" "+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new
                        java.util.Date()) + "\n");
            } else if(StringUtils.isBlank(sendAddress)) {
                ch.writeAndFlush("[客户端] " + channel.remoteAddress() +" 发送了："+ msg +" "+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new
                        java.util.Date()) + "\n");
            }
        });
    }

    /**
     * 处理异常，一般是需要关闭通道
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
