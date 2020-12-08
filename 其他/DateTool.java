/**
 * Project Name:springboot_hotel
 * File Name:DateTool.java
 * Package Name:cn.java.utils
 * Date:上午10:36:23
 * Copyright (c) 2018, bluemobi All Rights Reserved.
 *
*/

package cn.java.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description: QQ：1841670794，870599752(加好友时记得备注哦) Date: 上午10:36:23 <br/>
 * 
 * @author 丁鹏(大胆开车，幽默讲课)
 * @version
 * @see
 */
public class DateTool {

    public static void main(String[] args) throws ParseException {
        String date1 = "2018-10-26";
        String date2 = "2018-10-29 10:28:29";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = sdf.parse(date1);
        Date d2 = sdf.parse(date2);
        long diffTime = d2.getTime() - d1.getTime();
        int days = (int) (diffTime / (24 * 3600 * 1000));
        System.out.println(days);
    }

}
