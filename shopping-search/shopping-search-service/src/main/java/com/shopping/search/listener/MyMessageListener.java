package com.shopping.search.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * 接收ActiveMQ队列消息的监听器
 * <p>Title: MyMessageListener</p>
 * <p>Description: </p>
 * <p>Company: www.ghh.cn</p>
 * @version 1.0
 */
public class MyMessageListener implements MessageListener
{

    // onMessage是一个事件，当消息到达的时候，会调用这个方法
    @Override
    public void onMessage(Message message) {
        // 取消息的内容
        try {
            TextMessage textMessage = (TextMessage) message;
            // 取内容
            String text = textMessage.getText();
            System.out.println(text);
            // 其他业务逻辑...
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}