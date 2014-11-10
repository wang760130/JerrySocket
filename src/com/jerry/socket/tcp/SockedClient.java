package com.jerry.socket.tcp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * TCP通客户端器端
 * @author JerryWang
 *
 */
public class SockedClient {
	Socket socket = null;
	public void sendServer() {
		try {
			//创建Socket对象（并连接服务器）
//			socket = new Socket("127.0.0.1",9000);			
			socket = new Socket("localhost",9600);
			
			//调用getOutputStream方法，进行I/O
			PrintWriter pw = new PrintWriter(socket.getOutputStream());
			//向服务器发送消息
			pw.println("Hello World");
			pw.println("this is client message");
			//将数据一次性发出去
			pw.flush();	
			
			//接收服务器返回的数据
			InputStream is = socket.getInputStream();
			InputStreamReader reader = new InputStreamReader(is);
			BufferedReader buffer = new BufferedReader(reader);
			
//			BufferedReader br=new BufferedReader(  
//			         new InputStreamReader(socket.getInputStream()));  
			String line = buffer.readLine();			
			System.out.println("服务器端返回的数据："+line+"  ");
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
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
		SockedClient sockedClient = new SockedClient();
		sockedClient.sendServer();
	}
}
