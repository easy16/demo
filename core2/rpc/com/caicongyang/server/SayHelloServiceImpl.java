package com.caicongyang.server;
 
 
/**
 * 
 * <p> 
 * Title: SayHelloServiceImpl.java 
 * Package com.caicongyang.server 
 * </p>
 * <p>
 * Description: 服务实现类
 * <p>
 * @author Tom.Cai
 * @created 2016-2-23 下午10:37:41 
 * @version V1.0 
 *
 */
public class SayHelloServiceImpl implements SayHelloService {
 

  public String sayHello(String str) {
    if("hello".equals(str)){
      return "hello world!";
    }else{
      return "error!";
    }
  }
 
}

