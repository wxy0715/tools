package wxyUtils;

import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @author wxy
 * @description 字符串工具类
 * @data 2020/12/20
 */
public class WxyStringUtil {

	public static final String LOW_STR = "abcdefghijklmnopqrstuvwxyz";
	public static final String SPECIAL_STR = "$@$!%*#_~?&";
	public static final String NUM_STR = "0123456789";
	/**
	 * 判断是否为数字
	 */
	public static Pattern numericPattern = Pattern.compile("^[0-9\\-]+$");

	/** 判断字符串是否是空**/
	public static boolean isEmpty(String str){
		if(str == null || "".equals(str)) {
			return true;
		}
		if("".equals(str.trim())) {
			return true;
		}
		return false;
	}

	/** 判断时间格式**/
	public static boolean isValidDate(String str) {
		DateFormat formatter = new SimpleDateFormat(str); //这里的时间格式根据自己需求更改（注意：格式区分大小写、格式区分大小写、格式区分大小写）
		try{
			Date date = (Date)formatter.parse(str);
			return str.equals(formatter.format(date));
		}catch(Exception e){
			return false;
		}
	}

	/**判断字符串中是否包含空格*/
	public static boolean hasBlank(String str) {
		if(isEmpty(str)) {
			return true;
		}
		return str.contains(" ");
	}

	/**
	 * 去掉字符串中所有空格，去掉前后空格可以使用trim()
	 * @param str 目标字符串
	 */
	public static String removeBlank(String str) {
		if(isEmpty(str)) {
			return null;
		}
		return str.replace(" ", "");
	}

	/**
	 * 拼串
	 * @param strings
	 */
	public static String mergeStr(String...strings) {
		StringBuilder sb = new StringBuilder();
		for (String str : strings) {
			sb.append(str);
		}
		return sb.toString();
	}

	/**
	 * 获取指定个数数字字符串
	 * @param num 个数
	 */
	public static String getRanNum(Integer num) {
		StringBuilder str = new StringBuilder();
		for(int i=0;i<num;i++) {
			str.append((int) (Math.random() * 10));
		}
		return str.toString();
	}

	/**
	 * 获取指定个数字符串
	 * @param num 个数
	 */
	public static String getRanStr(Integer num) {
		Random random = new Random();
		StringBuilder res = new StringBuilder();
		for(int i=0;i<num;i++) {
			int ranNum = random.nextInt(LOW_STR.length());
			res.append(LOW_STR.charAt(ranNum));
		}
		return res.toString();
	}
	
	/** 随机获取字符串字符 **/
	public static char getRandomChar(String str) {
		SecureRandom random = new SecureRandom();
		return str.charAt(random.nextInt(str.length()));
	}

	/**随机获取小写字符 **/
	public static char getLowChar() {
		return getRandomChar(LOW_STR);
	}

	/**随机获取大写字符 **/
	public static char getUpperChar() {
		return Character.toUpperCase(getLowChar());
	}

	/**随机获取数字字符 **/
	public static char getNumChar() {
		return getRandomChar(NUM_STR);
	}

	/** 随机获取特殊字符**/
	public static char getSpecialChar() {
		return getRandomChar(SPECIAL_STR);
	}

	/** 指定调用字符函数**/
	public static char getRandomChar(int funNum) {
		switch (funNum) {
			case 0:
				return getLowChar();
			case 1:
				return getUpperChar();
			case 2:
				return getNumChar();
			default:
				return getSpecialChar();
		}
	}

	/**指定长度，随机生成复杂密码 **/
	public String getRandomPwd(int num) {
		if (num > 32 || num < 8) {
			System.out.println("密码格式错误");
			return "";
		}
		List<Character> list = new ArrayList<>(num);
		list.add(getLowChar());
		list.add(getUpperChar());
		list.add(getNumChar());
		list.add(getSpecialChar());

		for (int i = 4; i < num; i++) {
			SecureRandom random = new SecureRandom();
			int funNum = random.nextInt(4);
			list.add(getRandomChar(funNum));
		}
		// 打乱排序
		Collections.shuffle(list);
		StringBuilder stringBuilder = new StringBuilder(list.size());
		for (Character c : list) {
			stringBuilder.append(c);
		}

		return stringBuilder.toString();
	}


	/**
	 * 判断一个字符串是否为数字
	 *
	 * @param src
	 * @return
	 */
	public static boolean isNumeric(String src) {
		boolean return_value = false;
		if (src != null && src.length() > 0) {
			java.util.regex.Matcher m = numericPattern.matcher(src);
			if (m.find()) {
				return_value = true;
			}
		}
		return return_value;
	}


	/**
	 * 是否以指定字符串结尾，忽略大小写
	 * @param str    被监测字符串
	 * @param suffix 结尾字符串
	 * @return 是否以指定字符串结尾
	 */
	public static boolean endWithIgnoreCase(CharSequence str, CharSequence suffix) {
		return endWith(str, suffix, true);
	}
	/**
	 * 字符串是否以给定字符结尾
	 * @param str 字符串
	 * @param c   字符
	 * @return 是否结尾
	 */
	public static boolean endWith(CharSequence str, char c) {
		return c == str.charAt(str.length() - 1);
	}

	/**
	 * 是否以指定字符串结尾<br>
	 * 如果给定的字符串和开头字符串都为null则返回true，否则任意一个值为null返回false
	 * @param str          被监测字符串
	 * @param suffix       结尾字符串
	 * @param isIgnoreCase 是否忽略大小写
	 * @return 是否以指定字符串结尾
	 */
	public static boolean endWith(CharSequence str, CharSequence suffix, boolean isIgnoreCase) {
		if (null == str || null == suffix) {
			if (null == str && null == suffix) {
				return true;
			}
			return false;
		}
		if (isIgnoreCase) {
			return str.toString().toLowerCase().endsWith(suffix.toString().toLowerCase());
		} else {
			return str.toString().endsWith(suffix.toString());
		}
	}

}
