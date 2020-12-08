package com.hotel.utils.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author wxy
 */
public class MyClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		//客户端 连接Server发布的服务
		Socket socket = new Socket("127.0.0.1",8888);
		
		//接受服务端发送的消息 InputStream
		InputStream in = socket.getInputStream() ;
		byte[] bs = new byte[100] ;
		in.read( bs) ;
		//byte[]->String  
		System.out.println("client接受到的消息为："+  new String(bs));
		
		//客户端向服务端做出反馈（向服务端发送消息）
		OutputStream out = socket.getOutputStream() ;
		out.write("world".getBytes());
		
		out.close();
		in.close();
		socket.close();
	}
}
