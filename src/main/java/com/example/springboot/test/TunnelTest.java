package com.example.springboot.test;

import com.alibaba.fastjson.JSON;
import com.example.springboot.entity.TerminalNetstat;
import com.example.springboot.entity.TerminalTunnelStatus;
import com.squareup.moshi.Json;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TunnelTest {

    public static void main(String[] args) {


        String jsonStr="{\n" +
                "    \"clientId\": \"TSG1006112129004\",\n" +
                "    \"netstat\": \"Proto Recv-Q Send-Q Local Address           Foreign Address         State       PID/Program name    \\ntcp        0      0 0.0.0.0:80              0.0.0.0:*               LISTEN      1750/uhttpd\\ntcp        0      0 172.16.100.2:53         0.0.0.0:*               LISTEN      30308/dnsmasq\\ntcp        0      0 127.0.0.1:53            0.0.0.0:*               LISTEN      30308/dnsmasq\\ntcp        0      0 192.168.33.1:53         0.0.0.0:*               LISTEN      30308/dnsmasq\\ntcp        0      0 10.131.5.185:53         0.0.0.0:*               LISTEN      30308/dnsmasq\\ntcp        0      0 172.18.1.64:53          0.0.0.0:*               LISTEN      30308/dnsmasq\\ntcp        0      0 0.0.0.0:22              0.0.0.0:*               LISTEN      1482/dropbear\\ntcp        0      0 0.0.0.0:9191            0.0.0.0:*               LISTEN      1629/uhttpd\\ntcp        0      0 172.18.1.64:57400       172.18.1.218:6667       TIME_WAIT   -\\ntcp        0      0 172.18.1.64:57394       172.18.1.218:6667       TIME_WAIT   -\\ntcp        0      0 172.18.1.64:57396       172.18.1.218:6667       TIME_WAIT   -\\ntcp        0      0 172.18.1.64:57386       172.18.1.218:6667       TIME_WAIT   -\\ntcp        0      0 172.18.1.64:57416       172.18.1.218:6667       TIME_WAIT   -\\ntcp        0      0 172.18.1.64:57388       172.18.1.218:6667       TIME_WAIT   -\\ntcp        0      0 172.18.1.64:57382       172.18.1.218:6667       TIME_WAIT   -\\ntcp        0      0 172.18.1.64:57402       172.18.1.218:6667       TIME_WAIT   -\\ntcp        0      0 172.18.1.64:57406       172.18.1.218:6667       TIME_WAIT   -\\ntcp        0      0 172.18.1.64:57380       172.18.1.218:6667       TIME_WAIT   -\\ntcp        0      0 172.18.1.64:57412       172.18.1.218:6667       TIME_WAIT   -\\ntcp        0      0 172.18.1.64:57410       172.18.1.218:6667       TIME_WAIT   -\\ntcp        0      0 172.18.1.64:57414       172.18.1.218:6667       TIME_WAIT   -\\ntcp        0      0 172.18.1.64:57390       172.18.1.218:6667       TIME_WAIT   -\\ntcp        0      0 172.18.1.64:57384       172.18.1.218:6667       TIME_WAIT   -\\ntcp        0      0 172.18.1.64:57404       172.18.1.218:6667       TIME_WAIT   -\\ntcp        0      0 172.18.1.64:57392       172.18.1.218:6667       TIME_WAIT   -\\ntcp        0      0 172.18.1.64:57408       172.18.1.218:6667       TIME_WAIT   -\\ntcp        0      0 172.18.1.64:57398       172.18.1.218:6667       TIME_WAIT   -\\ntcp        0      0 :::80                   :::*                    LISTEN      1750/uhttpd\\ntcp        0      0 fe80::fc5:7919:71f7:37d0:53 :::*                    LISTEN      30308/dnsmasq\\ntcp        0      0 fd9d:19f6:cc81:0:20c:43ff:fe15:5010:53 :::*                    LISTEN      30308/dnsmasq\\ntcp        0      0 fda0:e5d4:6e8e:4::1:53  :::*                    LISTEN      30308/dnsmasq\\ntcp        0      0 fda0:e5d4:6e8e:0:20c:43ff:fe15:5010:53 :::*                    LISTEN      30308/dnsmasq\\ntcp        0      0 fda0:e5d4:6e8e::595:53  :::*                    LISTEN      30308/dnsmasq\\ntcp        0      0 fe80::78a3:51ff:fe00:144:53 :::*                    LISTEN      30308/dnsmasq\\ntcp        0      0 fe80::7aa3:51ff:fe00:144:53 :::*                    LISTEN      30308/dnsmasq\\ntcp        0      0 fd5b:d4ad:3744::1:53    :::*                    LISTEN      30308/dnsmasq\\ntcp        0      0 ::1:53                  :::*                    LISTEN      30308/dnsmasq\\ntcp        0      0 fe80::7aa3:51ff:fe00:102:53 :::*                    LISTEN      30308/dnsmasq\\ntcp        0      0 fe80::4460:34ff:fe9c:dfdb:53 :::*                    LISTEN      30308/dnsmasq\\ntcp        0      0 fe80::20c:43ff:fe15:5011:53 :::*                    LISTEN      30308/dnsmasq\\ntcp        0      0 fd5b:d4ad:3744:10::1:53 :::*                    LISTEN      30308/dnsmasq\\ntcp        0      0 fe80::20c:43ff:fe15:5012:53 :::*                    LISTEN      30308/dnsmasq\\ntcp        0      0 fe80::20c:43ff:fe15:5010:53 :::*                    LISTEN      30308/dnsmasq\\ntcp        0      0 :::22                   :::*                    LISTEN      1482/dropbear\\ntcp        0      0 :::9191                 :::*                    LISTEN      1629/uhttpd\\nudp        0      0 0.0.0.0:44075           0.0.0.0:*                           24327/ttvpn\\nudp        0      0 172.16.100.2:53         0.0.0.0:*                           30308/dnsmasq\\nudp        0      0 127.0.0.1:53            0.0.0.0:*                           30308/dnsmasq\\nudp        0      0 192.168.33.1:53         0.0.0.0:*                           30308/dnsmasq\\nudp        0      0 10.131.5.185:53         0.0.0.0:*                           30308/dnsmasq\\nudp        0      0 172.18.1.64:53          0.0.0.0:*                           30308/dnsmasq\\nudp        0      0 0.0.0.0:67              0.0.0.0:*                           30308/dnsmasq\\nudp        0      0 :::546                  :::*                                29678/odhcp6c\\nudp        0      0 :::547                  :::*                                1606/odhcpd\\nudp        0      0 :::547                  :::*                                1606/odhcpd\\nudp        0      0 fe80::fc5:7919:71f7:37d0:53 :::*                                30308/dnsmasq\\nudp        0      0 fd9d:19f6:cc81:0:20c:43ff:fe15:5010:53 :::*                                30308/dnsmasq\\nudp        0      0 fda0:e5d4:6e8e:4::1:53  :::*                                30308/dnsmasq\\nudp        0      0 fda0:e5d4:6e8e:0:20c:43ff:fe15:5010:53 :::*                                30308/dnsmasq\\nudp        0      0 fda0:e5d4:6e8e::595:53  :::*                                30308/dnsmasq\\nudp        0      0 fe80::78a3:51ff:fe00:144:53 :::*                                30308/dnsmasq\\nudp        0      0 fe80::7aa3:51ff:fe00:144:53 :::*                                30308/dnsmasq\\nudp        0      0 fd5b:d4ad:3744::1:53    :::*                                30308/dnsmasq\\n\",\n" +
                "    \"utime\":\"1589016462\"\n" +
                "}";

        TerminalNetstat t1 = JSON.parseObject(jsonStr, TerminalNetstat.class);

        List<TerminalNetstat> ts = new ArrayList<>();
        for(int i = 0 ;i<2000;i++){
            TerminalNetstat newT = null;
            try {
                newT = (TerminalNetstat) t1.deepClone();
            } catch (Exception e) {
                e.printStackTrace();
            }
            ts.add(newT);
        }
        String sendData = JSON.toJSONString(ts);
        log.info("ScheduleTunnelTask任务查询出TerminalTunnelStatus状态数据{}条", ts.size());
        log.debug("查询到的TerminalTunnelStatus状态总数据占用内存大小：" + sendData.getBytes().length);
    }


}
