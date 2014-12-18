package com.jerry.socket.io.abio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TimeServer {

    public static void main(String[] args) throws IOException {
        int port = 8080;
        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            System.out.println("Server start: " + port);
            Socket socket = null;
            while (true) {
                System.out.println(".."); 
                socket = server.accept();//此外产生阻塞  这也是阻塞IO的来源
                new Thread(new TimeServerHandler(socket)).start();
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
