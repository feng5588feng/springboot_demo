package com.example.springboot.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.springboot.dao.TerminalGroupMapper;
import com.example.springboot.dao.TestJsonMapper;
import com.example.springboot.entity.*;
import com.example.springboot.enums.TerminalTypeEnums;
import com.example.springboot.service.api.DemoService;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import io.lettuce.core.ScriptOutputType;
import org.apache.dubbo.common.utils.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service(version = "1.0.0", timeout = 3000, retries = 0)
public class DemoServiceImpl implements DemoService {

    //private static ThreadLocal<Thread> tCache = new ThreadLocal<>();


    private static Thread th;


    @Autowired
    private TestJsonMapper testJsonMapper;

    @Autowired
    private TerminalGroupMapper terminalGroupMapper;

    @Resource
    private MongoTemplate mongoTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public String testService(String inP) {
        System.out.println("impl执行：" + inP);

        int count = terminalGroupMapper.countGroupNumByTemplateId(6L,1);
        System.out.println(count);

        /*TerminalGroup newGroup = terminalGroupMapper.selectById(1);
        JSONObject templateData = newGroup.getTemplateData();
        Long newTsg500TemplateId = Long.valueOf(templateData.get(TerminalTypeEnums.TSG100.getName()) + "");
        Long newTsg000TemplateId = Long.valueOf(templateData.get(TerminalTypeEnums.TSG000.getName()) + "");
        System.out.println(newGroup.getTemplateData());*/

        //查询终端类型查询对应模板分组
        /*String templateConfig = stringRedisTemplate.opsForValue().get("DEFAULT_SECURITY_TEMPLATE_DATA");
        if (!StringUtils.isBlank(templateConfig)) {
            System.out.println(123);
        }
        templateConfig="111111";
        stringRedisTemplate.opsForValue().set("DEFAULT_SECURITY_TEMPLATE_DATA" , templateConfig);*/

        /*MongoCollection<Document> mongoCollection = mongoTemplate.getCollection("TSG100");
        Document document = mongoCollection.find(new Document("_id", new ObjectId("5f34ed9e7e1600003b007966"))).first();

        //MongoCollection<Document> mongoCollection1 = mongoTemplate.getCollection("TSG000");
        //Document document1 = mongoCollection1.find(new Document("_id", new ObjectId("5f34ab4b3bb4f764ee67d998"))).first();

        System.out.println(document.toJson());

        Query query = Query.query(Criteria.where("_id").is("5dcce96af2d0f46d2330bec8"));
        query.fields().include("master_man_channel");
        query.fields().exclude("_id");

        Tsg100Config conf = mongoTemplate.findOne(query, Tsg100Config.class);

        if (conf != null) {
            System.out.println(conf.toString());
        }else{
            System.out.println(111);
        }

        Tsg100Config conf1 =mongoTemplate.findById(new ObjectId("5f34ed9e7e1600003b007966"),Tsg100Config.class);
        System.out.println(conf1);*/

        /*MongoCollection<Document> mongoCollection = mongoTemplate.getCollection("BATCH_DATA");

        List<String> list = new ArrayList<>();
        list.add("TSG0006120624210");
        list.add("TSG0006120624209");
        //Query query = new Query();
        //query.addCriteria(Criteria.where("serial").nin(list));

        //in查询条件
        BasicDBObject dbQuery= new BasicDBObject();
        BasicDBList values = new BasicDBList();
        values.addAll(list);
        dbQuery.put("serial", new BasicDBObject("$in",values));

        //指定返回字段
        BasicDBObject fieldsObject = new BasicDBObject();
        fieldsObject.put("serial", true);
        fieldsObject.put("_id", true);
        Query query = new BasicQuery(dbQuery.toJson(), fieldsObject.toJson());
        //querySrvTunnel.addCriteria(Criteria.where("serial").nin(list));

        List queryList = mongoTemplate.find(query, Ssg100Config.class);
        System.out.println(queryList.toString());*/

        /*TestJson testJson = testJsonMapper.selectById(1);
        System.out.println(testJson.toString());

        TestJson testJson1 = new TestJson();
        JSONObject data1 = new JSONObject();
        data1.put("name", "aaa");
        data1.put("age", 15);
        data1.put("money", 1000);
        testJson1.setName("aaa");
        testJson1.setData(data1);
        testJson1.setId(null);
        testJsonMapper.insert(testJson1);*/









        return "11111111111";
    }

    public void testThread() {
        //System.out.println("AgentThreadLocal.LOCAL" + AgentThreadLocal.LOCAL);
        //停线程
        /*if (AgentThreadLocal.get() != null) {
            Thread thread = AgentThreadLocal.get();
            System.out.println("tCache获取到id:" + thread.getId());
            thread.interrupt();
            AgentThreadLocal.remove();
        }*/
        if(th != null && !th.isInterrupted()){
            th.interrupt();
            th.stop();
            th = null;

        }

        th = new Thread(() -> {
            int i = 0;
            while (true) {
                try {
                    Thread.sleep(1000);

                    System.out.println("运行线程----" + Thread.currentThread().getId());
                    System.out.println("i----" + i++);
                    if (Thread.currentThread().isInterrupted()) {
                        //处理终端逻辑
                        System.out.println("中断线程---" + Thread.currentThread().getId());
                        break;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    AgentThreadLocal.remove();
                }

            }
        });
        //新起线程

        th.start();
        System.out.println("当前执行线程id:" + th.getId());
        //添加到ThreadLocal
        //AgentThreadLocal.set(th);
        //System.out.println(AgentThreadLocal.get());
    }
}
