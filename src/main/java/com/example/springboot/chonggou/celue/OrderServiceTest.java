package com.example.springboot.chonggou.celue;

public class OrderServiceTest {
    /*public static void main(String[] args) {
        OrderServiceExecutor executor1 = new OrderServiceExecutor(new MySqlSaveOrderStrategy());
        executor1.save("001");


        OrderServiceExecutor executor2 = new OrderServiceExecutor(new NoSqlSaveOrderStrategy());
        executor2.save("002");
    }*/

    public static void main(String[] args) {
        OrderServiceExecutor executor1 = new OrderServiceExecutor((String orderNo) -> System.out.println("order:" + orderNo + " save to mysql"));
        executor1.save("001");

        OrderServiceExecutor executor2 = new OrderServiceExecutor((String orderNo) -> System.out.println("order:" + orderNo + " save to nosql"));
        executor2.save("002");
    }
}