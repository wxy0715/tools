package myjava.正则;

/**
 * @author wxy
 * @description
 * @data 2020/8/16
 */
public class Test {
	public static void main(String[] args) {
		boolean checkBirthday = RegexUtil_1.checkBirthday("1999-01-15");
		System.out.println(checkBirthday);
		boolean matchOne = RegexUtil.matches( "(^(?:[1-9][0-9]?|1[01][0-9]|120)$)", "010");
		System.out.println(matchOne);
	}
}
