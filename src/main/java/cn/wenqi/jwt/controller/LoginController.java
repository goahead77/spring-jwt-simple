package cn.wenqi.jwt.controller;

import cn.wenqi.jwt.service.User;
import cn.wenqi.jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wenqi
 */
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public ResponseEntity<String> login(String username, String password){
        User user=new User();
        user.setAccount(username);
        user.setPwd(password);
        return userService.login(user);
    }
}
