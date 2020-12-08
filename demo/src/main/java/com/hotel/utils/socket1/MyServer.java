package com.hotel.utils.socket1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author wxy
 */
public class MyServer {
	public static void main(String[] args) throws IOException {
		//绑定服务的端口，ip：为本机Ip
		//暴漏了一个 服务，该服务的地址： 本机ip:9999
		ServerSocket server = new ServerSocket(9999);
		while(true) {
			Socket socket =  server.accept() ;
			 new Thread(new MyDownload(socket)).start(); 
		}
	}
}
