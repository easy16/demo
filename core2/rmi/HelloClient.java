import java.rmi.AlreadyBoundException;
import java.net.MalformedURLException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.Naming;
import java.rmi.NotBoundException;

/** 
* Created by IntelliJ IDEA. 
* User: leizhimin 
* Date: 2008-8-7 22:21:07 
* 客户端测试，在客户端调用远程对象上的远程方法，并返回结果。 
*/ 
public class HelloClient { 
    public static void main(String args[]){ 
        try { 
            //在RMI服务注册表中查找名称为RHello的对象，并调用其上的方法 
            IHello rhello =(IHello) Naming.lookup("rmi://10.185.9.2:8888/RHello"); 
            System.out.println(rhello.helloWorld()); 
            System.out.println(rhello.sayHelloToSomeBody("clientrmi")); 
        } catch (NotBoundException e) { 
            e.printStackTrace(); 
        } catch (MalformedURLException e) { 
            e.printStackTrace(); 
        } catch (RemoteException e) { 
            e.printStackTrace();   
        } 
    } 
}
