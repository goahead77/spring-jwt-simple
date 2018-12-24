package cn.wenqi.jwt.controller.ws;

import cn.wenqi.jwt.model.MessageModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.sockjs.client.WebSocketClientSockJsSession;

import java.util.Map;
import java.util.UUID;

/**
 * @author wenqi
 * @since v1.0
 */
@RestController
@Slf4j
public class MessageController {


    @Autowired
    private SimpMessagingTemplate messagingTemplate;


    @MessageMapping("/order")
    public void orderMsg(@RequestBody Map<String,String> body){
        messagingTemplate.convertAndSendToUser("u1","/listen/order",new MessageModel(body.get("orderId"),
                UUID.randomUUID().toString()));
    }
}
