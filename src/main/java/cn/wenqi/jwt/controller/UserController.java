package cn.wenqi.jwt.controller;

import cn.wenqi.jwt.service.User;
import cn.wenqi.jwt.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author wenqi
 */
@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/user/{id}")
    public ResponseEntity<User> user(@PathVariable("id") Long id){
        User user=new User();
        user.setAccount(UUID.randomUUID().toString());
        user.setPwd(UUID.randomUUID().toString());
        user.setRoleId(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() throws InterruptedException {
        userService.syncMethod();
        userService.syncMethod();
        userService.syncMethod();
        userService.syncMethod();
        userService.syncMethod();
        userService.syncMethod();
        userService.syncMethod();
        userService.syncMethod();
        userService.syncMethod();
        return ResponseEntity.ok("ok");
    }
}
