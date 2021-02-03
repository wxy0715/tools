package wxyUtils;


import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtils {

	//-------------------------------------------------------------------------------zip加密
	/**
	 * 根据给定密码压缩文件(s)到指定目录
	 * @param destFileName 压缩文件存放绝对路径 e.g.:D:/upload/zip/demo.zip
	 * @param passwd 密码(可为空)
	 * @param files 单个文件或文件数组(区别所在)
	 * @return 最终的压缩文件存放的绝对路径,如果为null则说明压缩失败.
	 */
	public static String compress(String destFileName, String passwd, File... files) throws IOException {
		ZipParameters parameters = new ZipParameters();
		parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE); // 压缩方式
		parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL); // 压缩级别
		if (!StringUtils.isEmpty(passwd)) {
			parameters.setEncryptFiles(true);
			parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_STANDARD); // 加密方式
			parameters.setPassword(passwd.toCharArray());
		}
		try {
			ZipFile zipFile = new ZipFile(destFileName);
			for (File file : files) {
				zipFile.addFile(file, parameters);
			}
			return destFileName;
		} catch (ZipException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据给定密码压缩文件(s)到指定位置
	 * @param destFileName 压缩文件存放绝对路径 e.g.:D:/upload/zip/demo.zip
	 * @param passwd 密码(可为空)
	 * @param filePaths 单个文件路径或文件路径数组(区别所在)
	 * @return 最终的压缩文件存放的绝对路径,如果为null则说明压缩失败.
	 */
	public static String compress(String destFileName, String passwd, String... filePaths) throws IOException {
		int size = filePaths.length;
		File[] files = new File[size];
		for (int i = 0; i < size; i++) {
			files[i] = new File(filePaths[i]);
		}
		return compress(destFileName, passwd, files);
	}

	/**
	 * 根据给定密码压缩文件(s)到指定位置
	 * @param destFileName 压缩文件存放绝对路径 e.g.:D:/upload/zip/demo.zip
	 * @param passwd 密码(可为空)
	 * @param folder 文件夹路径
	 * @return 最终的压缩文件存放的绝对路径,如果为null则说明压缩失败.
	 */
	public static String compressFolder(String destFileName, String passwd, String folder) throws IOException {
		File folderParam = new File(folder);
		if (folderParam.isDirectory()) {
			File[] files = folderParam.listFiles();
			return compress(destFileName, passwd, files);
		}
		return null;
	}

	/**
	 * 根据所给密码解压zip压缩包到指定目录
	 * 如果指定目录不存在,可以自动创建,不合法的路径将导致异常被抛出
	 * @param zipFile zip压缩包绝对路径
	 * @param dest 指定解压文件夹位置
	 * @param passwd 密码(可为空)
	 * @return 解压后的文件数组
	 * @throws ZipException
	 */
	@SuppressWarnings("unchecked")
	public static File[] deCompress(File zipFile, String dest, String passwd) throws ZipException {
		//1.判断指定目录是否存在
		File destDir = new File(dest);
		if (destDir.isDirectory() && !destDir.exists()) {
			destDir.mkdir();
		}
		//2.初始化zip工具
		ZipFile zFile = new ZipFile(zipFile);
		zFile.setFileNameCharset("UTF-8");
		if (!zFile.isValidZipFile()) {
			throw new ZipException("压缩文件不合法,可能被损坏.");
		}
		//3.判断是否已加密
		if (zFile.isEncrypted()) {
			zFile.setPassword(passwd.toCharArray());
		}
		//4.解压所有文件
		zFile.extractAll(dest);
		List<FileHeader> headerList = zFile.getFileHeaders();
		List<File> extractedFileList = new ArrayList<File>();
		for(FileHeader fileHeader : headerList) {
			if (!fileHeader.isDirectory()) {
				extractedFileList.add(new File(destDir,fileHeader.getFileName()));
			}
		}
		File [] extractedFiles = new File[extractedFileList.size()];
		extractedFileList.toArray(extractedFiles);
		return extractedFiles;
	}

	/**
	 * 解压无密码的zip压缩包到指定目录 
	 * @param zipFile zip压缩包
	 * @param dest 指定解压文件夹位置
	 * @return 解压后的文件数组
	 * @throws ZipException
	 */
	public static File[] deCompress(File zipFile, String dest){
		try {
			return deCompress(zipFile, dest, null);
		} catch (ZipException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据所给密码解压zip压缩包到指定目录 
	 * @param zipFilePath zip压缩包绝对路径
	 * @param dest 指定解压文件夹位置
	 * @param passwd 压缩包密码
	 * @return 解压后的所有文件数组
	 */

	public static File[] deCompress(String zipFilePath, String dest, String passwd){
		try {
			return deCompress(new File(zipFilePath), dest, passwd);
		} catch (ZipException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 无密码解压压缩包到指定目录
	 * @param zipFilePath zip压缩包绝对路径
	 * @param dest 指定解压文件夹位置
	 * @return 解压后的所有文件数组
	 */
	public static File[] deCompress(String zipFilePath, String dest){
		try {
			return deCompress(new File(zipFilePath), dest, null);
		} catch (ZipException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*	public static void main(String[] args) {
		compress("E:\\upload\\test.zip", "123456","E:\\upload\\department-20201203214226.csv");
		//ZipUtils.compress("E:\\upload\\zip\\"+name+".zip", "123456","E:\\upload\\"+filename);
	}*/

	//---------------------------------------------------------------------------------操作文件
	/**
	 * @description 将文件转为zip格式
	 * @param fileRealPath 待压缩的文件列表
	 * @param zipFileRealPath  压缩后的文件名称
	 */
	public static void zipFiles(String fileRealPath, String zipFileRealPath) throws IOException {
		//创建文件输出流
		FileOutputStream fileOutputStream = null;
		//创建zip文件输出流
		ZipOutputStream zipOutputStream = null;
		//创建文件输入流
		FileInputStream in = null;
		try {
			File zipFile = new File(zipFileRealPath);
			if (!zipFile.exists()) {
				zipFile.createNewFile();
			}
			//创建文件输出流
			fileOutputStream = new FileOutputStream(zipFileRealPath);
			//创建zip数据输出流对象
			zipOutputStream = new ZipOutputStream(fileOutputStream);

			//获取csv文件
			File file = new File(fileRealPath);
			if (!file.exists()) {
				throw new FileNotFoundException("文件不存在");
			}
			//创建文件输入流对象
			in = new FileInputStream(fileRealPath);
			//获取操作系统
			String separateCharacter = "";
			String os = System.getProperty("os.name");
			if (os.toLowerCase().startsWith("win")) {
				separateCharacter = "\\";
			} else {
				separateCharacter = "/";
			}
			String fileName = fileRealPath.substring(fileRealPath.lastIndexOf(separateCharacter)+1);
			//创建向压缩文件的入口
			ZipEntry zipEntry = new ZipEntry(fileName);
			zipOutputStream.putNextEntry(zipEntry);
			//向压缩文件中输出数据
			int number = 0;
			byte[] buffer = new byte[1024];
			while ((number = in.read(buffer)) != -1) {
				zipOutputStream.write(buffer,0,number);
			}
		} catch (IOException e){
			System.out.println(e);
		} finally {
			if (in != null) {
				in.close();
			}
			if (null != zipOutputStream) {
				zipOutputStream.close();
			}
			if (null != fileOutputStream) {
				fileOutputStream.close();
			}
		}
	}

	/** 复制文件 交给浏览器下载
	 * @param srcFilePath 待压缩的文件列表
	 * @param response   下载流
	 * @param fileName  压缩后的文件名称
	 * **/
	public static  void copyFileDown(String srcFilePath, HttpServletResponse response, String fileName) {
		try (
				FileInputStream inputStream = new FileInputStream(srcFilePath);
				BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
				OutputStream fileOutputStream =response.getOutputStream()) {
			response.reset();
			response.setContentType("application/x-zip-compressed");
			response.setHeader("content-disposition", "attachment; filename="+fileName);
			int b = 0;
			byte[] bytes = new byte[1024];
			while ((b = bufferedInputStream.read(bytes)) != -1) {
				fileOutputStream.write(bytes, 0, b);
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}

}