package com.socketserver.sockets;

import com.socketserver.entity.User;
import com.vmeetcommon.utils.Result;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author LvXinming
 * @since 2023/3/17
 * <p>文字聊天 socket</p>
 */

@Component
@ServerEndpoint("/ws/chat")
public class ChatSocketServer {
    static ConcurrentHashMap<String, ChatSocketServer> socketMap = new ConcurrentHashMap<>();
    Session session;

    @OnOpen
    public void onOpen(Session session) throws IOException {
        this.session = session;
        socketMap.put(session.getId(), this);
        User user = new User(session.getId(),"已连接","tip");
        String data = Result.success("连接成功", user).toJSON();
        sendAll(data);
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        socketMap.remove(session.getId());
        User user = new User(session.getId(), "已断开", "tip");
        String data = Result.success("断开连接", user).toJSON();
        sendAll(data);
    }

    @OnMessage
    public void onMessage(String msg) throws IOException {
        User user = new User(session.getId(), msg, "msg");
        String data = Result.success("消息", user).toJSON();
        System.out.println(data);
        sendAll(data);
    }

    void sendAll(String msg) throws IOException {
        for (ChatSocketServer socket:socketMap.values()){
            socket.session.getBasicRemote().sendText(msg);
        }
    }

    void sendOne(String msg, String id) throws IOException {
        ChatSocketServer socket = socketMap.get(id);
        socket.session.getBasicRemote().sendText(msg);

    }

    void sendSome(String msg, String[] ids) throws IOException {
        for (String id:ids){
            ChatSocketServer socket = socketMap.get(id);
            socket.session.getBasicRemote().sendText(msg);
        }
    }


}
