package com.jerry.socket.io.bpio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TimeServer {

    //Pseudo(伪)  IO  
    public static void main(String[] args) throws IOException {
        int port = 8080;
        ServerSocket server = null;
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 20, 5, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(20), new ThreadPoolExecutor.DiscardOldestPolicy());

        try {
            server = new ServerSocket(port);
            System.out.println("Server start : " + port);
            Socket socket = null;
            while (true) {
                System.out.println("..");
                System.out.println("statics :  getActiveCount=" + executor.getActiveCount() + " getCompletedTaskCount=" + executor.getCompletedTaskCount() + " getTaskCount=" + executor.getTaskCount());
                socket = server.accept();
                executor.execute(new TimeServerHandler(socket));
            }
        } finally {
            if (server != null) {
                System.out.println("The time server close");
                server.close();
                server = null;
            }
        }
    }
}
