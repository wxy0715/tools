/**
 * Project Name:dt48_springMVC4
 * File Name:Task1.java
 * Package Name:cn.java.tasks
 * Date:下午5:27:31
 * Copyright (c) 2017, bluemobi All Rights Reserved.
 *
*/

package cn.java.tasks;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Description: <br/>
 * Date: 下午5:27:31 <br/>
 * 
 * @author dingP
 * @version
 * @see
 */
@Component
public class Task1 {
    // @Scheduled(fixedRate = 2000)
    @Scheduled(cron = "2 40 17 * * ?")
    public void task1() {
        Date cTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = sdf.format(cTime);
        System.out.println(str);
    }
}
