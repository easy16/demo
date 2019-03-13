package com.caicongyang.consumer;
 
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;
 
import com.caicongyang.server.SayHelloService;
 
/**
 * 
 * <p> 
 * Title: Consumer.java 
 * Package com.caicongyang.consumer 
 * </p>
 * <p>
 * Description: rpc服务消费者(即客户端)
 * <p>
 * @author Tom.Cai
 * @created 2016-2-23 下午10:36:30 
 * @version V1.0 
 *
 */
public class Consumer {
  public static void main(String[] args) {
    try {
      //接口名称
      String interfaceName = SayHelloService.class.getName();
      //接口方法
      Method method = SayHelloService.class.getMethod("sayHello", java.lang.String.class);
      //参数
      Object[] arguments = {"hello"};
      
      Socket socket = new Socket("127.0.0.1",8080);
      
      //发送请求
      ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
      out.writeUTF(interfaceName);
      out.writeUTF(method.getName());
      out.writeObject(method.getParameterTypes());
      out.writeObject(arguments);
      
      //获取结果
      ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
      Object returnObject = inputStream.readObject();
      System.out.println(returnObject.toString());
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
  }
}
