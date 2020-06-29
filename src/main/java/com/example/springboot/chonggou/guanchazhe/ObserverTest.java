package com.example.springboot.chonggou.guanchazhe;

public class ObserverTest {

    public static void main(String[] args) {

        /*Subject subject = new SubjectImpl();
        subject.registerObserver(new OrderObserver());
        subject.registerObserver(new StockObserver());
        subject.notifyAllObserver("001");*/

        NewSubject subject = new NewSubject() {
        };
        subject.registerObserver((String orderNo) -> System.out.println("订单 " + orderNo + " 状态更新为【已支付】"));
        subject.registerObserver((String orderNo) -> System.out.println("订单 " + orderNo + " 已通知库房发货！"));
        subject.nofityAllObserver("002");


    }

}
