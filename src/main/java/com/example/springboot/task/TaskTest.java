package com.example.springboot.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TaskTest {


    @Scheduled(cron = "0 0/1 * * * ?")
    public void reSendTManagersNeedToNSNMC() {

        try {
            /*int i = 1;
            while (true){
                i++;
                System.out.println(i);
                Thread.sleep(2000);
                if(i>70){
                    break;
                }
            }
            System.out.println("完成");*/

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
