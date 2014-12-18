package com.jerry.socket.io.cnio;

public class TimeClient {

    public static void main(String[] args) {
        int port = 8080;
        for(;;){
            new Thread(new TimeClientHandle("127.0.0.1", port), "nettyClient").start();
        }
    }
}
