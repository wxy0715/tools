package com.hotel.utils.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

/**
 * @author wxy
 */
public class MergeFile {
	public static void main(String[] args) throws IOException {
		//读取多个拆分后的文件(inputs：所有输入流的集合)
		/*List<FileInputStream> inputs = new ArrayList<>();
		for(int i=1;i<=8;i++) {
			inputs.add( new FileInputStream("E:\\splitDir\\"+i+".part"));
		}
		//指定合并后的文件输出流
		OutputStream out = new FileOutputStream("e:\\myMusic.mp3");
		
		//将多个 输入流 依次读入内存，最后再一次性输出到 myMusic.mp3
		byte[]  buf = new byte[1024*1024] ;
		
		for(FileInputStream in:inputs) {
			int len = in.read(buf) ;
			out.write(buf, 0, len);
		}
		out.close(); 
		
		for(FileInputStream in:inputs) {
			in.close();
		}
		*/
		
		//文件合并，方法二：
		//指定拆分后的文件位置
		File splitDir = new File("E:\\音乐\\splitDir");
		mergeFile(splitDir);
	}
	
	public static Properties getProperties() throws FileNotFoundException, IOException {
		//找到配置文件的位置
		String configFileName = "E:\\音乐\\splitDir\\conf.properties" ;
		Properties prop = new Properties();
		prop.load( new FileInputStream(configFileName )     );
		return prop ;
	}
	
	//文件合并
	public static void mergeFile(File splitDir) throws IOException {
		//合并之前，先读取配置信息
		Properties prop = getProperties();
		String fileName = prop.getProperty("filename") ;
		int partCount = Integer.parseInt(prop.getProperty("partcount") )  ;
		
		List<FileInputStream> inputs = new ArrayList<>();
		for(int i=1;i<=partCount;i++) {
			inputs.add( new FileInputStream("E:\\音乐\\splitDir\\"+i+".part"));
		}
		//List-->Enumeration
		Enumeration<FileInputStream>  en = Collections.enumeration(inputs );
		//多个流->1个流
		SequenceInputStream sin = new SequenceInputStream(en);
		
		//指定合并后的文件输出流
		OutputStream out = new FileOutputStream("E:\\音乐\\"+fileName);
		//sin -输出 (类似文件复制)
		byte[] buf = new byte[1024];
		int len = -1 ; 
		while((len=sin.read(buf))!=-1 ) {
			out.write(buf, 0, len);
		}
		out.close(); 
		sin.close(); 
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
