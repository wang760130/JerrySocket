package com.jerry.socket.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TCP通讯服务器端
 * @author JerryWang
 *
 */
public class SocketServer {
	ServerSocket serverSocket = null;
	Socket socket = null;
	public  void openServer() {
		try {
			//创建ServerSocket对象（并绑定端口）
			serverSocket = new ServerSocket(9600);;

			/**
			 * 调用accept方法，接收客户端的数据
			 * 对于accept方法的调用将造成阻塞，直到ServerSocket接受到一个连接请求为止。
			 * 	一旦连接请求被接受，服务器可以读客户socket中的请求。
			 */
			socket = serverSocket.accept();
			
			//调用getInputStream方法，接收客户端输出的数据，进行I/O
			InputStream in = socket.getInputStream();
			InputStreamReader reader = new InputStreamReader(in);
			BufferedReader buffer = new BufferedReader(reader);
			
//			BufferedReader buffer = new BufferedReader(
//				new InputStreamReader(socket.getInputStream())
//			);
			
			//输出客户端传过来的数据
			System.out.println("接受客户端数据：");
			String line = buffer.readLine();
			System.out.println(line);			
			
			//给客户端传输数据
			PrintWriter pw = new PrintWriter(socket.getOutputStream());
			pw.println(line + " form server");
			pw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}		
		}
	}
	
	public static void main(String[] args) {
		SocketServer socketServer = new SocketServer();
		socketServer.openServer();
	}
}
