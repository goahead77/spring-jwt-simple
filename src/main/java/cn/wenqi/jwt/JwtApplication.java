package cn.wenqi.jwt;

import cn.wenqi.jwt.interceptor.TokenInterceptor;
import cn.wenqi.jwt.model.MessageModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Collections;
import java.util.UUID;

/**
 * @author wenqi
 */
@Controller
@SpringBootApplication
@EnableAsync
@EnableScheduling
@Slf4j
public class JwtApplication implements WebMvcConfigurer {

    @Autowired
    private TokenInterceptor tokenInterceptor;

    public static void main(String[] args) {
        SpringApplication.run(JwtApplication.class,args);
    }

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor).excludePathPatterns("/login","/token/**","/*");
    }

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Scheduled(cron = "*/1 * * * * ?")
    public void sendMessage(){
        log.info("主动发送消息...");
        messagingTemplate.convertAndSendToUser("u1","/listen/order",
                new MessageModel("发送给u1的消息", UUID.randomUUID().toString()));
        messagingTemplate.convertAndSendToUser("u2","/listen/order",
                new MessageModel("发送给u2的消息", UUID.randomUUID().toString()));
        messagingTemplate.convertAndSendToUser("u3","/listen/order",
                new MessageModel("发送给u3的消息", UUID.randomUUID().toString()));
    }

}
