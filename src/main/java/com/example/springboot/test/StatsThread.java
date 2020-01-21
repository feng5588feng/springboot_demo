package com.example.springboot.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * @ClassName
 * @Description
 * @Author tuoww
 * @DATE 2019/12/10
 * @Version V1.0
 **/
public class StatsThread implements Runnable  {
    String statsName;
    int runTime;

    final static SimpleDateFormat sdf = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

    final static String startTime = sdf.format(new Date());

    public StatsThread(String statsName, int runTime) {
        this.statsName = statsName;
        this.runTime = runTime;
    }

    public void run() {
        /*try {
            System.out.println(statsName+ " do stats begin at "+ startTime);
            //模拟任务执行时间
            Thread.sleep(runTime);
            System.out.println(statsName + " do stats complete at "+ sdf.format(new Date()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        System.out.println(statsName+ " do stats begin at "+ startTime);
        //模拟任务执行时间
        //Thread.sleep(runTime);
        System.out.println(statsName + " do stats complete at "+ sdf.format(new Date()));
    }
}
