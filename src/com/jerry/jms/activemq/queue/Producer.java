package com.jerry.jms.activemq.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 消息生产者
 * 
 * @author Jerry Wang
 * 
 */
public class Producer {
	private static final String USERNAME = "system";
	private static final String PASSWORD = "manager";
	private static final String BROKEURL = "tcp://192.168.142.128:61616";
	private static final int SENDNUM = 10000;
	
	public static void main(String[] args) {
        //连接工厂
        ConnectionFactory connectionFactory;
        //连接
        Connection connection = null;
        //会话 接受或者发送消息的线程
        Session session;
        //消息的目的地
        Destination destination;
        //消息生产者
        MessageProducer messageProducer;
        //实例化连接工厂
        connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKEURL);

        try {
            //通过连接工厂获取连接
            connection = connectionFactory.createConnection();
            //启动连接
            connection.start();
            //创建session
            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            //创建一个名称为HelloWorld的消息队列
            destination = session.createQueue("HelloWorld");
            //创建消息生产者
            messageProducer = session.createProducer(destination);
            //发送消息
            for (int i = 0; i < SENDNUM; i++) {
                //创建一条文本消息 
                TextMessage message = session.createTextMessage("ActiveMQ 发送消息 " +i);
                System.out.println("发送消息：Activemq 发送消息" + i);
                //通过消息生产者发出消息 
                messageProducer.send(message);
            }

            session.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(connection != null){
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
