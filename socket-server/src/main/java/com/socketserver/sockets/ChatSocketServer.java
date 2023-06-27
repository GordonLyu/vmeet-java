package com.socketserver.sockets;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONReader;
import com.socketserver.entity.User;
import com.socketserver.linster.sender.EsKafkaMessageSender;
import com.vmeetcommon.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author LvXinming
 * @since 2023/3/17
 * <p>文字聊天 socket</p>
 */

@Component
@ServerEndpoint("/chat/{uid}")
public class ChatSocketServer {

    private static EsKafkaMessageSender sender;

    @Autowired
    void setSender(EsKafkaMessageSender sender){
        ChatSocketServer.sender = sender;
    }


    private static final ConcurrentHashMap<String, ChatSocketServer> socketMap = new ConcurrentHashMap<>();
    private Session session;
    private Integer uid;

    @OnOpen
    public void onOpen(Session session, @PathParam("uid") Integer uid) throws IOException {
        this.session = session;
        this.uid = uid;
        socketMap.put("id-" + uid, this);
        User user = new User(uid, "已连接", "tip");
        String data = Result.success("连接成功", user).toJSON();
        System.out.println("数量：" + socketMap.size());
        sendAll(data);
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        socketMap.remove("id-" + uid);
        User user = new User(uid, "已断开", "tip");
        String data = Result.success("断开连接", user).toJSON();
        System.out.println("数量：" + socketMap.size());
        sendAll(data);
    }

    @OnMessage
    public void onMessage(String msg) throws IOException {
        System.out.println(msg);
        JSONObject jsonObject = JSON.parseObject(msg);
        String data = Result.success("消息", jsonObject).toJSON();
        Integer to = jsonObject.getInteger("to");
//        List<Integer> groupTo = jsonObject.getList("groupTo",Integer.class);
        if (to == null) {
            sendAll(data);
        } else {
            sendOne(data, "id-" + to);
        }
    }

    void sendAll(String msg) throws IOException {
        System.out.println("kafka发送消息至socket客户端");
        sender.sendToDefaultChannel(msg);
        for (ChatSocketServer socket : socketMap.values()) {
            socket.session.getBasicRemote().sendText(msg);
        }

    }

    void sendOne(String msg, String id) throws IOException {
        ChatSocketServer socket = socketMap.get(id);
        for (ChatSocketServer socket1 : socketMap.values()) {
            System.out.println(socket1.uid);
        }
        socket.session.getBasicRemote().sendText(msg);

    }

    void sendSome(String msg, String[] ids) throws IOException {
        for (String id : ids) {
            ChatSocketServer socket = socketMap.get(id);
            socket.session.getBasicRemote().sendText(msg);
        }
    }


}
