package com.hotel.utils.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class SplitFile {
	public static void main(String[] args) throws IOException {
		//原文件（待拆分的文件）
		File resFile = new File("E:\\音乐\\吕口口 - 世间美好与你环环相扣（女版）（翻自 柏松）.mp3");
		//拆分后的目录
		File splitDir = new File("e:\\音乐\\splitDir");
		splitFile(resFile,splitDir);
		//软件使用次数：5
/*		if(hasRemainingTries()) {
			//拆分文件

		}else {
			System.out.println("使用次数已到！");
		}*/
	}
	//判断 是否有还有试用次数
	//思路：将当前用的次数保存在硬盘，然后每一次使用时 和5比较
/*	public static boolean hasRemainingTries() throws FileNotFoundException, IOException {
		Properties prop = new Properties();//3
		int count = 0 ;
		//每使用一次： 1.先获取之前用了几次（3）  2.再将之前次数+1(4)  
		
		//查询本次之前 已经用了几次
		prop.load(new FileInputStream(  "E:\\音乐\\splitDir\\tries.properties"));
		String times = prop.getProperty("times") ;
		//null, 1放回去
		if(times == null ) {
			count =1 ;
			prop.setProperty("times", count+"") ;
		}else {
			int timeCount = Integer.parseInt( times)  ;
			timeCount++ ;
			prop.setProperty("times", timeCount+"") ;
			//1 2  5 6
			if(timeCount>5) {
				return false;
			}
		}
		prop.store(new FileOutputStream("E:\\splitDir\\tries.properties"), "try times...");
		return true ;
	}*/
	
	
	//拆分				resFile：源文件				splitDir:拆分后的目录			
	public static void splitFile(File resFile,File splitDir) throws IOException {
		if(!splitDir.exists()) {
			splitDir.mkdirs() ;
		}
		//思路 : 拆分：1个输入流   ，n个输出流 （a,b,c）
		//合并： n个输入流 ,1个输出流 （注意顺序a,b,c）
		
		//拆分：1个输入流
		InputStream in = new FileInputStream(resFile);
		OutputStream out = null ;
		byte[] buf = new byte[1024*1024];//定义缓冲区为1M,当缓冲区填满时，一次性刷出成一个文件
		int len = -1;
		int count = 1 ;
		while(  (len=in.read(buf))!=-1   ) {
			out = new FileOutputStream( new File(splitDir, count++ +".part")    );
			out.write(buf, 0, len);
			out.close();//关闭流、关闭之前会强行清理缓冲区
		}
		
		//拆分的时候：如何 将文件名、分割的数量保留，为后续合并做准备
		//再生成一个配置文件9.conf 保存上述描述信息
		//方式一：
		out = new FileOutputStream(  new File(splitDir,"conf.properties"));
		//查询当前操作系统的换行符
//		String lineSeperator = System.getProperty("line.separator") ;
//		out.write(("filename="+resFile.getName()).getBytes() );
//		out.write(lineSeperator.getBytes());
//		out.write(("partcount="  + (count-1) ).getBytes() );
//		out.close(); 
		//方式二： Properties,将内存中的多个 属性以 key=value的形式 写到硬盘中
		Properties prop = new Properties();
		prop.setProperty("filename", resFile.getName()) ;
		prop.setProperty("partcount", (count-1)+"" );
		//写入硬盘 (保存：持久化)
		prop.store(out, "file configuration...");
		out.close(); 
		in.close(); 
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
