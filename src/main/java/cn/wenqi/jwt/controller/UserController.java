package cn.wenqi.jwt.controller;

import cn.wenqi.jwt.service.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author wenqi
 */
@RestController
public class UserController {

    @RequestMapping("/user/{id}")
    public ResponseEntity<User> user(@PathVariable("id") Long id){
        User user=new User();
        user.setAccount(UUID.randomUUID().toString());
        user.setPwd(UUID.randomUUID().toString());
        user.setRoleId(id);
        return ResponseEntity.ok(user);
    }
}
