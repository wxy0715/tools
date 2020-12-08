package com.hotel.utils.socket1;

import java.io.FileOutputStream;
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
		Socket socket = new Socket("127.0.0.1",9999);
		//接受服务端发送的消息 InputStream
		InputStream in = socket.getInputStream() ;
		//接受每次发来的文件切片（100byte）
		byte[] bs = new byte[1000] ;
		int len = -1 ;
		OutputStream fileOut = new FileOutputStream("E:\\perss.obj") ;
		while( (len =in.read(bs))!=-1 ) {
			fileOut.write(bs,0,len);
		}
		System.out.println("下载成功！");
		fileOut.close();
		in.close();
		socket.close();
	}
	
}
