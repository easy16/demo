package com.caicongyang.provider;
 
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
 
import com.caicongyang.server.SayHelloService;
import com.caicongyang.server.SayHelloServiceImpl;
 
/**
 * 
 * <p> 
 * Title: Provider.java 
 * Package com.caicongyang.provider 
 * </p>
 * <p>
 * Description: RPC服务提供者，供客户端调用
 * <p>
 * @author Tom.Cai
 * @created 2016-2-23 下午10:31:40 
 * @version V1.0 
 *
 */
public class Provider {
  
  public static void main(String[] args) {
    ServerSocket server =  null;
    ObjectOutputStream out = null;
    try {
      server = new ServerSocket(8080);
      Socket socket =null;
      
      while(true){
        System.out.println("---开始监听---");
        socket = server.accept();
        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
        String interfaceName = input.readUTF(); //接口名称
        String methodName = input.readUTF(); //方法名称
        Class<?>[] parameterType = (Class<?>[]) input.readObject(); //方法类型
        Object[] arguments = (Object[]) input.readObject();    //参数列表
        System.out.println("接收到的参数："+Arrays.toString(arguments));
        
        //根据接口名称获取class
        Class<?> serviceInterfaceClass = Class.forName(interfaceName);
        //根据方法名称和参数类型反射得到方法
        Method method = serviceInterfaceClass.getMethod(methodName, parameterType);
        //服务实例化（这里做简单处理，正常应该根据得到的接口名称serviceInterfaceClass获取对应的service,但本demo只提供一个服务）
        SayHelloService service = new SayHelloServiceImpl();
        
        //反射执行这个方法
        Object result = method.invoke(service, arguments);
        
        //写会处理结果
        out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(result);
        
        
      }
    } catch (Exception e) {
      e.printStackTrace();
    }finally{
      try {
        out.close();
      } catch (IOException e1) {
        e1.printStackTrace();
      }
      if(server!=null){
        try {
          server.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
        server = null;
      }
    }
  }
}
