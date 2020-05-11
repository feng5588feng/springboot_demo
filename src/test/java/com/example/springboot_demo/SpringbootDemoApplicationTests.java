package com.example.springboot_demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.springboot.SpringbootDemoApplication;
import com.example.springboot.entity.Ssg100Config;
import com.example.springboot.entity.TokenData;
import com.example.springboot.service.AsyncService;
import com.example.springboot.service.api.DemoService;
import com.example.springboot.test.EndTask;
import com.example.springboot.test.StatsThread;
import com.example.springboot.test.ThreadPoolDemo;
import com.example.springboot.util.HttpUtils2;
import com.mongodb.client.MongoCollection;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.influxdb.dto.Point;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootDemoApplication.class)
@Slf4j
class SpringbootDemoApplicationTests {

    @Test
    public void testHttpClient(){
        TokenData data = new TokenData();
        data.setAreaCode("addd121212");
        data.setSerial("100");
        data.setToken("aaaaaaaaaaaa");
        Map<String, Object> paramMap = JSON.parseObject(JSON.toJSONString(data), new TypeReference<Map<String, Object>>() {
        });

        //url不为空，推送
        try {
            String url = "http://" + "172.1.1.2" + ":8888/v1/pushToken/push";
            String response = HttpUtils2.sendPost(url, paramMap);
            log.info("pushToken response: " + response);
        } catch (Exception e) {
            log.error("推送终端token服务失败", e);
            System.out.println("12121212212");
        }



    }

    /*@Autowired
    DemoService demoService;

    @Autowired
    AsyncService asyncService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private InfluxDBTemplate<Point> influxDBTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    final static SimpleDateFormat sdf = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

    final static String startTime = sdf.format(new Date());

    *//**
     * IO密集型任务  = 一般为2*CPU核心数（常出现于线程中：数据库数据交互、文件上传下载、网络数据传输等等）
     * CPU密集型任务 = 一般为CPU核心数+1（常出现于线程中：复杂算法）
     * 混合型任务  = 视机器配置和复杂度自测而定
     *//*
    private static int corePoolSize = Runtime.getRuntime().availableProcessors();
    *//**
     * public ThreadPoolExecutor(int corePoolSize,int maximumPoolSize,long keepAliveTime,
     *                           TimeUnit unit,BlockingQueue<Runnable> workQueue)
     * corePoolSize用于指定核心线程数量
     * maximumPoolSize指定最大线程数
     * keepAliveTime和TimeUnit指定线程空闲后的最大存活时间
     * workQueue则是线程池的缓冲队列,还未执行的线程会在队列中等待
     * 监控队列长度，确保队列有界
     * 不当的线程池大小会使得处理速度变慢，稳定性下降，并且导致内存泄露。如果配置的线程过少，则队列会持续变大，消耗过多内存。
     * 而过多的线程又会 由于频繁的上下文切换导致整个系统的速度变缓——殊途而同归。队列的长度至关重要，它必须得是有界的，这样如果线程池不堪重负了它可以暂时拒绝掉新的请求。
     * ExecutorService 默认的实现是一个无界的 LinkedBlockingQueue。
     *//*
    private static ThreadPoolExecutor executor  = new ThreadPoolExecutor(corePoolSize, corePoolSize+1, 10l, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(500));

    private static ExecutorService pool = Executors.newFixedThreadPool(50);

    @Autowired
    private MongoTemplate mongoTemplate;


    @Test
    public void testThread2(){
        System.out.println("mian方法开始执行:" + sdf.format(new Date()));

        //使用Future方式执行多任务
        List<Future<Integer>> list=new ArrayList<Future<Integer>>();

        new Thread(() -> {
            *//*for (int i = 0; i < 10; i++) {
                Future<Integer> future=pool.submit(() -> {
                    Thread.sleep(1000);
                    return 111;
                });
                list.add(future);
            }*//*

            for (int i = 0; i < 10; i++) {
                Future<Integer>  future=pool.submit(new Callable<Integer>() {
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


        System.out.println("mian方法结束执行1:" + sdf.format(new Date()));
        System.out.println("mian方法结束执行2:" + sdf.format(new Date()));
    }

    @Test
    public void testThread3(){

    }

    @Test
    public void testRedisSetFlag() {
        //设置锁
        stringRedisTemplate.opsForValue().set("sendDataFlag", "1",1, TimeUnit.HOURS);
    }

    @Test
    public void testRedisGetFlag() {
        //获取锁
        boolean isExist = stringRedisTemplate.hasKey("sendDataFlag");
        if(isExist){
            String flag = stringRedisTemplate.opsForValue().get("sendDataFlag");
            System.out.println(flag);
        }else{
            //已过期
            System.out.println("已过期");
        }

    }

    @Test
    public void testRedisDelFlag() {
        //设置锁
        stringRedisTemplate.delete("sendDataFlag");
    }


    @Test
    void contextLoads() {
        demoService.testService("123");
    }

    @Test
    public void testThread() {
        System.out.println("开始," + System.currentTimeMillis());
        for(int i=0; i < 100;i++) {
            System.out.println("i = " + i);
            asyncService.executorAsyncTask(i + "");
        }

        System.out.println("结束" + System.currentTimeMillis());
    }

    @Test
    public void sendHttpData(){
        Map<String, Object> sendMap = new HashMap<>();
        sendMap.put("serial", "addd121212");
        sendMap.put("areaCode", "100");
        sendMap.put("token", "aaaaaaaaaaaa");
        sendMap.put("access_token", "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiZXhwIjoxNTc2MDU3NjM0fQ.acqMPGHEhKRO1ktdHbbrD-HP9rNXyKN235LoBdgZHuU");

        *//*TokenData data = new TokenData();
        data.setAreaCode("addd121212");
        data.setSerial("100");
        data.setToken("aaaaaaaaaaaa");*//*

        restTemplate.postForObject("http://localhost:8888/v1/pushToken/push", sendMap, String.class);

    }

    @Test
    public void sendTokenData(){
        TokenData data = new TokenData();
        data.setAreaCode("addd121212");
        data.setSerial("100");
        data.setToken("aaaaaaaaaaaa");
        Map<String, Object> paramMap = JSON.parseObject(JSON.toJSONString(data), new TypeReference<Map<String, Object>>() {
        });
        try {
            //Response response = HttpUtils.post("http://localhost:8888/v1/pushToken/push",paramMap);
            String response = HttpUtils2.sendPost("http://localhost:8888/v1/pushToken/push",paramMap);
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void insertInflexdb(){
        Point.Builder builder = Point.measurement("test_measurement");  // 创建Builder，设置表名
        builder.addField("count",11);  // 添加Field
        builder.tag("TAG_CODE","TAG_VALUE_" + 11);    // 添加Tag
        Point point = builder.build();

        influxDBTemplate.write(point);

        System.out.println("finish....");
    }


    @Test
    public void queryAllTsg000ConfigData(){
        //List<Ssg100Config> ssg100s = mongoTemplate.findAll(Ssg100Config.class);
        //System.out.println(ssg100s.size());

        *//*Query query = new Query(Criteria.where("_id").is("5e1572fb41260000bc000d63"));
        Update update = new Update().set("mmc_load_time", new Date());
        mongoTemplate.updateFirst(query, update, "TSG100");*//*


        MongoCollection<Document> mongoCollection = mongoTemplate.getCollection("TSG100");
        Document document = mongoCollection.find(new Document("_id", new ObjectId("5e1572fb41260000bc000d63"))).first();
        String moduleData = document.toJson();
        //Map<String, Object> sendMap = JSON.parseObject(moduleData, Map.class);
        //Date mmtime = (Date) sendMap.get("mmc_load_time");
        Date tempDate = document.getDate("mmc_load_time");
        System.out.println(tempDate);
        System.out.println("finish....");
    }*/

}
