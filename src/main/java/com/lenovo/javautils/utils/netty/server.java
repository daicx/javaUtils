package com.lenovo.javautils.utils.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;


public class server {

    public static void main(String[] args) {
        //线程组
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup();
        NioEventLoopGroup eventExecutors1 = new NioEventLoopGroup();
        //服务端
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(eventExecutors, eventExecutors1)
                    .channel(NioServerSocketChannel.class)
                    //线程队列连接个数
                    .option(ChannelOption.SO_BACKLOG, 128)
                    //保持连接状态
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    //初始化通道对象
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            //给pipeline管道设置处理器
                            socketChannel.pipeline().addLast(new MyServerHandler());
                        }
                    })
            ;
            //绑定端口号，启动服务端
            ChannelFuture channelFuture = serverBootstrap.bind(6666).sync();
            //对关闭通道进行监听
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            eventExecutors.shutdownGracefully();
            eventExecutors1.shutdownGracefully();
        }


    }
}
