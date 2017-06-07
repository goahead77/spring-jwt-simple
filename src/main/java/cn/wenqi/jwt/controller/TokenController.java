package cn.wenqi.jwt.controller;

import cn.wenqi.jwt.service.User;
import cn.wenqi.jwt.utils.Constant;
import cn.wenqi.jwt.utils.JwtUtils;
import cn.wenqi.jwt.utils.ResponseUtil;
import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wenqi
 */
@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    private JwtUtils jwt;

    @RequestMapping(value = "/refresh",method = RequestMethod.POST)
    public ResponseEntity<String> refreshToken(String token) throws Exception {
        Claims claims = jwt.parseJWT(token);
        String json = claims.getSubject();
        User user = JSONObject.parseObject(json, User.class);
        String subject = JwtUtils.generalSubject(user);
        String t = jwt.createJWT(Constant.JWT_ID, subject, Constant.JWT_TTL);
        String refreshToken=jwt.createJWT(Constant.JWT_ID, subject, Constant.JWT_REFRESH_TTL);
        JSONObject jo = new JSONObject();
        jo.put("token", t);
        jo.put("refreshToken", refreshToken);
        return ResponseUtil.success(jo);
    }
}
