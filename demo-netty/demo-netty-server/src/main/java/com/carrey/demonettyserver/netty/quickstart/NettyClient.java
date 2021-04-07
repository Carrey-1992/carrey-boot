package com.carrey.demonettyserver.netty.quickstart;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @className NettyClient
 * @description 
 * @author Conway
 * @date 2021/3/12 2:26 下午
*/
public class NettyClient {
    public static void main(String[] args) throws InterruptedException {
        //客户端需要一个事件循环
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            // 创建客户端启动对象
            // 注意客户端使用的不是ServiceBootstrap而是Bootstrap
            Bootstrap bootstrap = new Bootstrap();
            // 设置相关参数
            bootstrap.group(group) //设置线程组
            .channel(NioSocketChannel.class) // 使用 NioSocketChannel作为客户端的通道实现
            .handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    // 加入处理器
                    socketChannel.pipeline().addLast(new NettyClientHandler());
                }
            });
            System.out.println("netty client start");
            //启动客户端去连接服务端
            ChannelFuture channelFuture = bootstrap.connect("172.0.0.1", 9000).sync();
            //对关闭通道进行监听
            channelFuture.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
}
