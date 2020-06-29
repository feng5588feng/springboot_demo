package com.example.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.example.springboot.entity.Ssg100TemplateConfig;
import com.example.springboot.entity.TokenData;
import com.example.springboot.service.api.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

/**
 * @ClassName
 * @Description
 * @Author tuoww
 * @DATE 2019/12/10
 * @Version V1.0
 **/
@RestController
@RequestMapping("test")
@Slf4j
public class HttpController {
    @Autowired
    DemoService demoService;

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private MongoTemplate mongoTemplate;


    final static SimpleDateFormat sdf = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

    final static String startTime = sdf.format(new Date());

    private static int corePoolSize = Runtime.getRuntime().availableProcessors();

    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, corePoolSize + 1, 10l, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(1000));

    private static ExecutorService pool = Executors.newFixedThreadPool(10);


    @GetMapping("test")
    public String threadTest() {
        demoService.testThread();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        demoService.testThread();
        return "success";
    }

    @GetMapping("mongoTest")
    public void mongoTest() {
        Query srcQueryTmp = new Query(Criteria.where("_id").is("5ed99b8dc938227559c151e6"));
        Ssg100TemplateConfig ssg100TemplateConfig = mongoTemplate.findOne(srcQueryTmp, Ssg100TemplateConfig.class);

        //组装所有模块数据
        Query query = new Query(Criteria.where("_id").is("5ed5c3f7c938227559c14997"));
        /*Update update = new Update()
                .inc("srv_tunnel.ver", 1).set("srv_tunnel", ssg100TemplateConfig.getSrvTunnel().put("ver", null))
                .inc("upg.ver", 1).set("upg", ssg100TemplateConfig.getUpg().put("ver", null))
                .inc("agent.ver", 1).set("agent", ssg100TemplateConfig.getAgent().put("ver", null))
                .inc("master_man_channel.ver", 1).set("master_man_channel", ssg100TemplateConfig.getMasterManChannel())
                .inc("stat.ver", 1).set("stat", ssg100TemplateConfig.getStat().put("ver", null))
                .set("mmc_load_time", ssg100TemplateConfig.getMmcLoadTime())
                .inc("ver", 1);*/
        Update update = new Update()
                //.inc("srv_tunnel.ver", 1)
                .set("srv_tunnel", ssg100TemplateConfig.getSrvTunnel())
                .set("mmc_load_time", ssg100TemplateConfig.getMmcLoadTime())
                .inc("ver", 1);
        mongoTemplate.updateFirst(query, update, "TSG000");
    }


    @GetMapping("pushToken")
    public void pushToken(TokenData token) throws Exception {
        log.info("推送终端token服务开始执行,token={}", token.toString());
        String urlIp = "http://127.0.0.1:8888";
        //url不为空，推送
        if (!StringUtils.isBlank(urlIp)) {
            String url = urlIp + "/v1/pushToken/push";
            //返回注册结果
            String response;
            try {

                Map<String, Object> paramMap = new HashMap<>();
                paramMap.put("serial", "11111111");
                paramMap.put("areaCode", "22222");
                paramMap.put("token", "aaaaaaaaaaa");
                /*TokenData token = new TokenData();
                token.setAreaCode("");
                token.setSerial("");
                token.setToken("");*/
                response = restTemplate.postForObject(url, paramMap, String.class);
                log.info("推送终端token返回信息:{}", response);
            } catch (Exception e) {
                log.error("推送终端token服务失败,{}", e.getMessage());
            }
        } else {
            log.info("无推送备用TAC地址不推送token,areaCode：{}", token.getAreaCode());
        }
        log.info("推送终端token服务执行结束");

    }


    @GetMapping("test3")
    public String threadTest3() {
        System.out.println("threadTest3:" + sdf.format(new Date()));

        List<String> strs = new ArrayList<>();
        strs.add("A");
        strs.add("B");
        strs.add("C");
        strs.add("D");
        strs.add("E");
        strs.add("F");
        strs.add("G");
        strs.add("H");
        strs.add("I");
        strs.add("J");
        strs.add("K");
        strs.add("L");
        strs.add("M");
        strs.add("N");
        strs.add("O");
        strs.add("P");
        strs.add("Q");
        strs.add("R");
        strs.add("S");
        strs.add("T");
        strs.add("U");
        strs.add("V");
        strs.add("W");
        strs.add("X");
        strs.add("Y");
        strs.add("Z");


        //另起线程发送
        new Thread(() -> {
            boolean pushFinish = false;


            List<String> lastStrs = strs;
            List<String> subStrs;
            if (lastStrs.size() > 5) {
                subStrs = lastStrs.subList(0, 5);
            } else {
                subStrs = lastStrs;
            }
            while (subStrs != null && subStrs.size() > 0) {
                CountDownLatch latch = new CountDownLatch(subStrs.size());

                System.out.println("当前个数===" + lastStrs.size());
                //判断当前剩余个数
                if (lastStrs.size() <= 5) {
                    pushFinish = true;
                }

                //执行线程任务
                for (String str : subStrs) {
                    pool.execute(() -> {
                        demoService.testService(str);
                        latch.countDown();
                    });
                }
                try {
                    System.out.println("latch开始等待");
                    latch.await();// 等待所有人任务结束
                    System.out.println("latch结束等待");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //所有
                if (pushFinish) {
                    //全部发送完毕
                    System.out.println("全部结束等待");
                    break;
                }
                //获取剩余子集(strs个数大于5)
                lastStrs = lastStrs.subList(5, lastStrs.size());
                //获取新的待执行子集
                if (lastStrs.size() >= 5) {
                    subStrs = lastStrs.subList(0, 5);
                } else {
                    subStrs = lastStrs;
                }

            }

        }).start();


        System.out.println("threadTest3:" + sdf.format(new Date()));
        return "success";
    }

    public void testList(List<String> a) {
        new Thread(() -> {
            a.clear();
            a.size();
            List<String> b = a.subList(0, 5);
            for (String c : b) {

            }
        });
    }


    @GetMapping("test2")
    public String threadTest2() {

        System.out.println("mian方法开始执行:" + sdf.format(new Date()));

        //使用Future方式执行多任务
        List<Future<Integer>> list = new ArrayList<Future<Integer>>();

        new Thread(() -> {
            /*for (int i = 0; i < 10; i++) {
                Future<Integer> future=pool.submit(() -> {
                    Thread.sleep(1000);
                    return 111;
                });
                list.add(future);
            }*/

            for (int i = 0; i < 10; i++) {
                Future<Integer> future = executor.submit(new Callable<Integer>() {
                    public Integer call() throws Exception {
                        Thread.sleep(1000);
                        return 222;
                    }
                });
                list.add(future);
            }

            //pool.shutdown();

            for (Future<Integer> future : list) {
                try {
                    System.out.println("返回：" + future.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("线程内方法全部执行完毕");
        }).start();


        System.out.println("mian方法结束执行:" + sdf.format(new Date()));

        return "success";
    }
}
