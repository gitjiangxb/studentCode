package com.nxtele.did.utils;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;


public class RabbitMqUtils {


    private static String host;
    private static int port;
    private static String username;
    private static String password;

    private static Channel channel = null;
    private static ConnectionFactory factory = null;
    private static Connection connection = null;
    public static Channel getChannel() throws Exception{
        if( channel == null || !channel.isOpen() ){
            //创建连接工厂
            if( factory == null ){
                factory = new ConnectionFactory();
                System.out.println("创建连接工厂");
            }
            //设置RabbitMQ相关信息
            factory.setHost(host);//106.15.34.94 127.0.0.1
            factory.setUsername(username); //mymq  wcj
            factory.setPassword(password); //mymq  wcj
            factory.setPort(port);
            factory.setVirtualHost("/");
            //创建一个新的连接
            if( connection == null || !connection.isOpen() ){
                connection = factory.newConnection();
                System.out.println("创建一个新的连接");
            }
            //创建一个通道
            channel = connection.createChannel();
        }
        return channel;
    }


    public static void close(Channel channel,Connection connection){
        try{
            if( channel != null ){
                channel.close();
            }
            if( connection != null ){
                connection.close();
            }
        }catch( Exception e ){
            e.printStackTrace();
        }
    }

    public static void close(){
        try{
            if( channel != null ){
                channel.close();
            }
            if( connection != null ){
                connection.close();
            }
        }catch( Exception e ){
            e.printStackTrace();
        }
    }

    public static void delQueue(Channel channel,String queueName){
        try {
            channel.queueDelete(queueName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Value("${mqHost}")
    public void setHost(String host) {
        RabbitMqUtils.host = host;
    }


    @Value("${mqPort}")
    public void setPort(int port) {
        RabbitMqUtils.port = port;
    }


    @Value("&{mqUsername}")
    public void setUsername(String username) {
        RabbitMqUtils.username = username;
    }


    @Value("${mqPassword}")
    public void setPassword(String password) {
        RabbitMqUtils.password = password;
    }
}
