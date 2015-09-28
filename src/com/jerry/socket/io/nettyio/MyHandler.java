package com.jerry.socket.io.nettyio;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class MyHandler extends ChannelHandlerAdapter {

    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("my active");
        ctx.fireChannelActive();
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("my read");
        ctx.fireChannelRead(msg);
    }

    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("complete");
        ctx.fireChannelReadComplete();
    }

}
