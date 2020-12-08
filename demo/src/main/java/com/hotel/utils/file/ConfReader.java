package com.hotel.utils.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * @author wxy
 */
public class ConfReader {
	public static void main(String[] args) throws IOException {
		//读取配置文件
		File confFile = new File("E:\\音乐\\splitDir\\conf.properties");
		readConfig(confFile);
	}
	
	public static void readConfig(File confFile) throws IOException {
		BufferedReader bReader =  new BufferedReader( new FileReader(confFile));
		String line = null ;
		while( (line=bReader.readLine()) !=null  ) {
			String[] arr = line.split("=") ;
			if(line.startsWith("filename")) {
				System.out.println("filename:"+arr[1]);
			}else if(line.startsWith("partcount")) {
				System.out.println("partcount:"+arr[1]);
			}
		}
		bReader.close();
	}
	
	
	
	
	
	
	
	
	
	
}
