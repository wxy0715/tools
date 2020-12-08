package com.hotel.utils.rpc;

import java.net.InetSocketAddress;

/**
 * @author wxy
 * @create 2020/4/18 19:36
 **/
public class RPCClientTest {
	public static void main(String[] args) throws ClassNotFoundException {
		HelloService service = Client.getRemoteProxyObj(Class.forName("com.hotel.utils.rpc.HelloService" ) , new InetSocketAddress("127.0.0.1", 8888)) ;
		for (int i = 1; i <11 ; i++) {
			System.out.println( service.sayHi("老师"+i)  ) ;
		}
	}
}
