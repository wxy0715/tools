/**
 * Project Name:dt48_chapter3
 * File Name:MD5Tool.java
 * Package Name:cn.java.utils
 * Date:2017年10月19日上午10:55:44
 * Copyright (c) 2017, bluemobi All Rights Reserved.
 *
*/

package cn.java.utils;

import java.security.MessageDigest;

/**
 * Description: <br/>
 * Date: 2017年10月19日 上午10:55:44 <br/>
 * 
 * @author dingP
 * @version
 * @see
 */
public class MD5Tool {
    private static final String[] digital = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e",
            "f" };

    /**
     * 
     * Description: 不要用此方法加密<br/>
     *
     * @author dingP
     * @param password
     * @return
     * @throws Exception
     */
    public static String md5(String password) throws Exception {
        String encry = "";
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        if (password != null) {
            byte[] bytes = md5.digest(password.getBytes("UTF-8"));
            for (byte b : bytes) {
                int num = b;
                if (num < 0) {
                    num = num + 256;
                }
                // 每个数字计算出两个下标
                int index1 = num / 16;
                int index2 = num % 16;
                encry += digital[index1] + digital[index2];
            }
        }
        return encry;
    }

    /**
     * 
     * Description: md5加密算法<br/>
     *
     * @author dingP
     * @param txt:需要被加密的文本内容
     * @return
     * @throws Exception
     */
    public static String encryByMD5(String txt) throws Exception {
        return md5(md5(md5(txt) + txt));
    }

}
