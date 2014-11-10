package com.jerry.socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * UDP通客户端器端
 * @author Wangjiajun 
 * @Email  wangjiajun@58.com
 *
 */
public class SocketClient {
	DatagramSocket socket = null;
	public void sendServer() {
		try {
			socket = new DatagramSocket();
			String str = "Hello! I am Client";
			byte[] data = str.getBytes();
			
			InetAddress addr = InetAddress.getByName("127.0.0.1");
			int port = 9600;
			
			DatagramPacket packet = new DatagramPacket(data,0,data.length,addr,port);			
			socket.send(packet);
			
			//接收服务器端发送的数据
			byte[] buf=new byte[100];
			DatagramPacket packet1 =new DatagramPacket(buf,0,buf.length);
			socket.receive(packet1);
			String msg=new String(buf,0,packet.getLength());
			System.out.print("recevie: " + msg);
			
			socket.close();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			socket.close();
		}
	}
}
