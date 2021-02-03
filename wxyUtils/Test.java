package wxyUtils;

import java.io.File;
import java.io.IOException;

/**
 * @author wxy
 * @description
 * @data 2021/1/14
 */
public class Test {
	public static void main(String[] args) throws IOException {
/*		Class<?> printClass =  Class.forName("wxyUtils.WxyFileIoUtils");
		Method upload = printClass.getMethod("upload", String.class);
		upload.invoke(printClass.newInstance(),"/tmp/test.txt");*/
		File fileByNameCreate = WxyFileIoUtils.getFileByNameCreate("D:\\tmp\\test1.mp4",0);
		File fileByNameCreate2 = WxyFileIoUtils.getFileByNameCreate("D:\\tmp\\test3.mp4",0);
		File fileByName1 = WxyFileIoUtils.getFileByName("H:\\慕课网\\L019 - Spring Security技术栈开发企业级认证与授权（完整版） - 366元\\第3章 使用Spring MVC开发RESTful API\\3-8 使用Filter和Interceptor拦截REST服务.mp4");
		File fileByName = WxyFileIoUtils.getFileByName(	"H:\\慕课网\\L019 - Spring Security技术栈开发企业级认证与授权（完整版） - 366元\\security-master.zip");
		long l2 = System.currentTimeMillis();
		WxyFileIoUtils.uploadBufferedByte(fileByName,fileByNameCreate);
		long l3 = System.currentTimeMillis();
		System.out.println("buffered字节"+(l3-l2));
		long l = System.currentTimeMillis();
		WxyFileIoUtils.uploadNIOBufferedByte(fileByName,fileByNameCreate2);
		long l1 = System.currentTimeMillis();
		System.out.println("niobuffered"+(l1-l));
	}

}
