package com.carrey.demonettyclient.echo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author Conway
 * @className EchoClient
 * @description
 * @date 2020/5/13 3:05 下午
 */
public class EchoClient {

    private final String host;
    private final int port;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            //1.创建 Bootstrap
            Bootstrap b = new Bootstrap();
            //2.指定 EventLoopGroup 来处理客户端事件。由于我们使用 NIO 传输，所以用到了 NioEventLoopGroup 的实现
            b.group(group)
                    //3.使用的 channel 类型是一个用于 NIO 传输
                    .channel(NioSocketChannel.class)
                    //4.设置服务器的 InetSocketAddress
                    .remoteAddress(new InetSocketAddress(host, port))
                    //5.当建立一个连接和一个新的通道时，创建添加到 EchoClientHandler 实例 到 channel pipeline
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch)
                                throws Exception {
                            ch.pipeline().addLast(new EchoClientHandler());
                        }
                    });
            //6.连接到远程;等待连接完成
            ChannelFuture f = b.connect().sync();
            //7.阻塞直到 Channel 关闭
            f.channel().closeFuture().sync();
        } finally {
            //8.调用 shutdownGracefully() 来关闭线程池和释放所有资源
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws Exception {
//        if (args.length != 2) {
//            System.err.println(
//                    "Usage: " + EchoClient.class.getSimpleName() +
//                            " <host> <port>");
//            return;
//        }

//        final String host = args[0];
//        final int port = Integer.parseInt(args[1]);

        new EchoClient("localhost", 8080).start();
    }

}
