package com.example.springboot_demo;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.example.springboot.SpringbootDemoApplication;
import com.example.springboot.entity.*;
import com.example.springboot.service.FilterProcess;
import com.example.springboot.util.HttpUtils2;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import io.lettuce.core.ScriptOutputType;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.WebSocket;
import java.nio.ByteBuffer;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootDemoApplication.class)
@Slf4j
public class SpringbootDemoApplicationTests {


    @Resource
    private MongoTemplate mongoTemplate;

    @Test
    public void testWebSocket() throws InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        WebSocket webSocket = client.newWebSocketBuilder()
                .buildAsync(URI.create("ws://127.0.0.1:8899/netStatusCon/eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiIiLCJhcmVhQ29kZSI6IjYxIiwiaXNzIjoi6ZmV6KW_IiwiaWF0IjoxNTg5NDIzMzAzfQ.lMAJ0u7U4_HFtNwjZEPiSfNEQWrhLK-TSfs2hLpoUPw"), new WebSocket.Listener() {

                    @Override
                    public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
                        // request one more
                        webSocket.request(1);

                        // Print the message when it's available
                        return CompletableFuture.completedFuture(data)
                                .thenAccept(System.out::println);
                    }

                    @Override
                    public void onOpen(WebSocket webSocket) {
                        System.out.println("websocket opened.");
                    }

                    @Override
                    public CompletionStage<?> onBinary(WebSocket webSocket, ByteBuffer data, boolean last) {
                        webSocket.request(1);

                        return null;
                    }

                    @Override
                    public CompletionStage<?> onPing(WebSocket webSocket, ByteBuffer message) {
                        webSocket.request(1);
                        System.out.println("ping");
                        System.out.println(message.asCharBuffer().toString());
                        return null;
                    }

                    @Override
                    public CompletionStage<?> onPong(WebSocket webSocket, ByteBuffer message) {
                        webSocket.request(1);
                        System.out.println("pong");
                        return null;
                    }

                    @Override
                    public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
                        System.out.println("closed, status(" + statusCode + "), cause:" + reason);
                        webSocket.sendClose(statusCode, reason);
                        return null;
                    }

                    @Override
                    public void onError(WebSocket webSocket, Throwable error) {
                        System.out.println("error: " + error.getLocalizedMessage());
                        webSocket.abort();
                    }

                }).join();


        //
        List<TerminalNetstat> sendList = new ArrayList<>();
        TerminalNetstat terminalNetstat = new TerminalNetstat();
        terminalNetstat.setClientId("123123123");
        //发送
        String sendData = JSON.toJSONString(sendList);
        webSocket.sendText(sendData, true);

        //webSocket.sendText("hello ", false);
        //webSocket.sendText("world ",true);

        TimeUnit.SECONDS.sleep(10);
        //webSocket.sendClose(WebSocket.NORMAL_CLOSURE, "ok").join();
    }


    @Test
    public void test3() {
        List<Student> list = Arrays.asList(
                new Student("九天", "男", 5000, 18, "天秤座"),
                new Student("十夜", "男", 4000, 16, "双鱼座"),
                new Student("十一郎", "男", 3000, 24, "水瓶座")
        );
        //list.stream().takeWhile(x -> x < 50).forEach(System.out::println);
        Stream.iterate(1, i -> i < 5, i -> i + 1).forEach(System.out::println);
    }


    //同步调用
    @Test
    public void test2() throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create("")).build();
        HttpResponse.BodyHandler<String> handler = HttpResponse.BodyHandlers.ofString();
        HttpResponse<String> response = client.send(request, handler);
        String body = response.body();
        System.out.println(body);
    }


    @Test
    public void testHttpClient() {
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
    */

    /**
     * public ThreadPoolExecutor(int corePoolSize,int maximumPoolSize,long keepAliveTime,
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
    }*/
    @Test
    public void queryAllTsg000ConfigData() {
        //List<Ssg100Config> ssg100s = mongoTemplate.findAll(Ssg100Config.class);
        //System.out.println(ssg100s.size());


        String defaultString = "{\"agent\":{\"ver\":1,\"status_rpt_timer\":15,\"re_auth_margin\":600,\"conn_timeout\":10,\"session_timeout\":60},\"cipher_srv\":{\"ver\":1,\"lib\":\"soft\",\"ciphering\":{\"enabled\":0,\"algo\":\"AES-256-CBC\"},\"signing\":{\"enabled\":0,\"algo\":\"RSA\",\"hash\":\"SHA256\"}},\"id\":\"5f02d0ea44b008691b7256c2\",\"lan\":{\"ver\":1,\"sub_lan_cnt\":2,\"sub_lan\":[{\"type\":0,\"tun\":\"\",\"name\":\"lan\",\"ip\":\"192.168.33.1\",\"mask\":\"255.255.255\",\"mtu\":1500,\"start\":\"192.168.33.100\",\"end\":\"192.168.33.200\",\"access\":\"DHCP\",\"net_sw_port_cnt\":2,\"net_sw_port\":\"2,3\",\"srv_sw_port_cnt\":0,\"srv_sw_port\":\"\",\"wifi_ssid_port_cnt\":1,\"wifi_ssid_port\":\"0\",\"pptp_ip_cnt\":0,\"pptp_ip\":[]},{\"type\":1,\"tun\":\"tun0\",\"name\":\"lan2\",\"ip\":\"10.131.5.185\",\"mask\":\"255.255.248\",\"mtu\":1500,\"start\":\"10.131.5.186\",\"end\":\"10.131.5.190\",\"pppoe_start_ip\":\"10.131.6.185\",\"pppoe_ip_cnt\":5,\"access\":\"DHCP,PPPoE\",\"net_sw_port_cnt\":0,\"net_sw_port\":\"\",\"srv_sw_port_cnt\":2,\"srv_sw_port\":\"0,1\",\"wifi_ssid_port_cnt\":1,\"wifi_ssid_port\":\"1\",\"pptp_ip_cnt\":0,\"pptp_ip\":[]}]},\"master_man_channel\":{\"addr\":[{\"disable\":1,\"operator\":\"dx\",\"port\":6667,\"priority\":1,\"url\":\"https://172.18.1.218\"}],\"addr_cnt\":1,\"sort_method\":1,\"ver\":1},\"mmc_load_time\":1594020074000,\"nac\":{\"ver\":1,\"mode\":2,\"mac_list_cnt\":2,\"mac_list\":[{\"mac\":\"11:22:33:44:55:66\",\"bind\":1},{\"mac\":\"22:22:33:44:55:66\",\"bind\":1}]},\"pppoe\":{\"ver\":1,\"users_cnt\":2,\"users\":[{\"user\":\"user1\",\"pass\":\"pass1\"},{\"user\":\"user1\",\"pass\":\"pass2\"}]},\"qos\":{\"ver\":1,\"up\":1,\"down\":4,\"release\":20,\"maxping\":200,\"pingcount\":10,\"connectivity\":{\"cdn\":[{\"dn\":\"baidu.com\"},{\"dn\":\"taobao.com\"},{\"dn\":\"qq.com\"}]},\"line_select\":{\"odn\":[{\"dn\":\"cip.cc\"},{\"dn\":\"myip.ipip.net\"}]}},\"slog\":{\"ver\":1,\"enable\":1,\"level\":1,\"period\":300},\"srv_tunnel\":{\"ver\":1,\"nat\":1,\"mode\":0,\"ca_crt\":\"\",\"tunnel_cnt\":2,\"tunnel\":[{\"enabled\":1,\"priority\":0,\"dev\":\"tun0\",\"mtu\":1500,\"proto\":\"udp\",\"port\":2013,\"tport\":6667,\"auth\":\"SHA256\",\"cipher\":\"AES-256-CBC\",\"sort_method\":0,\"remote_cnt\":0,\"remote\":[]},{\"auth\":\"SHA256\",\"cipher\":\"AES-256-CBC\",\"dev\":\"tun1\",\"enabled\":0,\"mtu\":1345,\"port\":59,\"priority\":1,\"proto\":\"tcp\",\"remote\":[{\"addr\":\"172.18.0.64\",\"operator\":\"dx\",\"priority\":1}],\"remote_cnt\":1,\"sort_method\":0,\"tport\":80}]},\"stat\":{\"ver\":1,\"netstat\":{\"enable\":1,\"period\":30,\"per_record_num\":64,\"mode\":\"tnup\"},\"wan\":{\"enable\":1,\"period\":30}},\"tmc\":{\"ver\":1,\"tmc_cnt\":1,\"tmc_list\":[{\"name\":\"ttitshanghaimonitorcenter\",\"sort_method\":0,\"addr_cnt\":2,\"addr\":[{\"url\":\"https://2.2.3.4\",\"port\":8443,\"operator\":\"dx\",\"priority\":0},{\"url\":\"https://2.2.3.5\",\"port\":8443,\"operator\":\"dx\",\"priority\":1}]}]},\"tsg100_module_version\":{\"agent\":1,\"cipher_srv\":1,\"lan\":1,\"master_man_channel\":1,\"nac\":1,\"pppoe\":1,\"qos\":1,\"slog\":1,\"srv_tunnel\":1,\"stat\":1,\"tmc\":1,\"upg\":1,\"wan\":1,\"wifidog\":1,\"wireless\":2},\"upg\":{\"ver\":1,\"fw_version\":\"\",\"fw_type\":\"\",\"url\":\"\",\"mode\":0,\"md5\":\"\"},\"ver\":1,\"wan\":{\"ver\":1,\"mtu\":1500,\"mac_bind\":0},\"wifidog\":{\"ver\":1,\"enabled\":0,\"ExternalInterface\":\"eth0.1\",\"gatewayinterface\":\"lan\",\"hostname\":\"119.29.216.237:8041\",\"msg\":\"<html><head><title>$title</title><metaHTTP-EQUIV='Pragma'CONTENT='no-cache'><style>body{margin:10px 60px 0 60px;font-family:bitstreamverasans,sans-serif;color:#46a43a;}a{color:#46a43a;}a:active{color:#46a43a;}a:link{color:#46a43a;}a:visited{color:#46a43a;}#header{height:30px;background-color:#B4F663;padding:20px;font-size:20pt;text-align:center;border:2px solid #46a43a;border-bottom:0;}#headerh2{margin:0pt;}#menu{width:200px;float:right;background-color:#B4F663;border:2px solid #46a43a;font-size:80%;min-height:300px;}#menuh2{margin:0;background-color:#46a43a;text-align:center;color:#B4F663;}#copyright{}#content{padding:20px;border:2px solid #46a43a;min-height:300px;}</style></head><body<divid=\\\"header\\\"><h2>$title</h2></div><divid=\\\"menu\\\"><h2>Info</h2><ul><li>Version:20090925<li>NodeID:$nodeID</ul><br><h2>Menu</h2><ul><li><ahref='/wifidog/status'>WiFiDogStatus</a><li><ahref='/wifidog/about'>AboutWiFiDog</a><li><ahref='http://www.wifidog.org'>WiFiDog'shomepage</a></ul></div><divid=\\\"content\\\"><h2>$message</h2></div><divid=\\\"copyright\\\">Copyright(C)2004-2005.ThissoftwareisreleasedundertheGNUGPLlicense.</div></body></html>\"},\"wireless\":{\"ver\":1,\"ssid_cnt\":2,\"ssid\":[{\"type\":0,\"enabled\":0,\"index\":0,\"hidden\":0,\"key\":\"8877665544\",\"name\":\"wlan\",\"encryption\":\"psk2\"},{\"type\":1,\"enabled\":0,\"index\":1,\"hidden\":0,\"key\":\"8877665544\",\"name\":\"wlan-2\",\"encryption\":\"psk2\"}],\"hot_sta\":{\"enabled\":0,\"ssid\":\"xittit\",\"key\":\"abcdeFG@\"}}}";
        Ssg100TemplateConfig ssg100TemplateConfig = JSON.parseObject(defaultString, Ssg100TemplateConfig.class);

        //保存成功生成mongodb该设备全量配置数据
        Map<String, Object> entity = JSON.parseObject(JSON.toJSONString(ssg100TemplateConfig));
        entity.put("mmc_load_time", new Date((Long) entity.get("mmc_load_time")));
        entity.put("serial", "TSG000TEST");
        entity.remove("_id");
        mongoTemplate.insert(entity, "TSG000");
        String mongodbId = entity.get("_id").toString();
        System.out.println(mongodbId);

        //根据分组id查询所有设备配置发送tas
        /*BasicDBObject dbObject = new BasicDBObject();
        dbObject.put("groupId", 1L);
        BasicDBObject fieldsObject = new BasicDBObject();
        fieldsObject.put("serial", true);
        fieldsObject.put("ver", true);
        fieldsObject.put("master_man_channel.ver", true);
        Query queryModuleVer = new BasicQuery(dbObject.toJson(), fieldsObject.toJson());
        List<JSONObject> terminalConfigs = mongoTemplate.find(queryModuleVer, JSONObject.class, "TSG000");
        System.out.println(terminalConfigs.size());
        for (JSONObject terminalConfig : terminalConfigs) {
            LinkedHashMap innerObject = (LinkedHashMap) terminalConfig.get("master_man_channel");
            int innerVer = (Integer) innerObject.get("ver");
            System.out.println(innerVer);
        }*/

        /*Criteria criteria = Criteria.where("_id").is("5ef8337ece770959fef1c228");
        Ssg100Config ssg100Config = mongoTemplate.findOne(Query.query(criteria), Ssg100Config.class);
        Map<String, Object> agent = ssg100Config.getAgent();

        try {
            Query query = new Query(Criteria.where("_id").is("5ef8337ece770959fef1c228"));
            Update update = new Update();

            Set<String> keySet = agent.keySet();
            for(String key : keySet) {
                if(!"ver".equals(key)){
                    update.set("agent." + key, agent.get(key));
                }
            }
            update.inc("agent.ver", 1);
            mongoTemplate.updateFirst(query, update, "TSG000_TEST");
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        /*Query query = new Query(Criteria.where("_id").is("5ef8337ece770959fef1c228"));
        Update update = new Update().set("agent", agent).inc("ver", 1);
        mongoTemplate.updateFirst(query, update, "TSG000_TEST");*/


        /*MongoCollection<Document> mongoCollection = mongoTemplate.getCollection("TSG100");
        Document document = mongoCollection.find(new Document("_id", new ObjectId("5e1572fb41260000bc000d63"))).first();
        String moduleData = document.toJson();
        Map<String, Object> sendMap = JSON.parseObject(moduleData, Map.class);
        Date mmtime = (Date) sendMap.get("mmc_load_time");
        Date tempDate = document.getDate("mmc_load_time");
        System.out.println(tempDate);
        System.out.println("finish....");*/
    }

    public static HashMap<String, Object> reflect(Object obj) throws IllegalAccessException, IllegalArgumentException {
        HashMap<String, Object> map = new HashMap<>();
        if (obj == null)
            return null;
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            // 字段名
            System.out.print(field.getName() + ",");
            Object _Object = field.get(obj);
            map.put(field.getName(), _Object);
        }
        return map;
    }

}
