package com.yc.C83pfstSpringBoot.biz;

import java.io.IOException;
import java.util.Hashtable;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@ServerEndpoint(value="/websocket/{id}")
@Component
public class MyWebSocket {

	//hash用来存放每个客户端对应的id
	private static Hashtable<String,Session> webSocketMap=new Hashtable<>();
	
	//连接建立成功调用的方法
	@OnOpen
	public void onOpen(@PathParam("id")String id,Session session ) {
		System.out.println(id+"已连接成功！");
		webSocketMap.put(id,session);
	}
	//连接关闭调用的方法
	@OnClose
	public void onClose(Session session) {
		//移除webSocketMap中的会话
	}
	//收到客户端消息后调用的方法
	@OnMessage
	public void onMessage(String message,Session session) throws IOException {
		//message=“a:nihao" 
		String []ss=message.split(":");
		String id=ss[0];
		String msg=ss[1];
		Session targetSession=webSocketMap.get(id);
		if(targetSession!=null) {
			targetSession.getBasicRemote().sendText(msg);
			
		}else {
			System.out.println(id+"不在线！");
		}
	}
	//美两分钟执行一次
	@Scheduled(cron="0 */2 * * * ?")
    public  void luckYou() throws IOException {
		for(Session session:webSocketMap.values()) {
			session.getBasicRemote().sendText("祝你好运");
		}
	}
}
