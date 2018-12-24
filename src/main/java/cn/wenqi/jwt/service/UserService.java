package cn.wenqi.jwt.service;

import cn.wenqi.jwt.utils.Constant;
import cn.wenqi.jwt.utils.JwtUtils;
import cn.wenqi.jwt.utils.ResponseUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserService {
	

	@Autowired
	private JwtUtils jwt;

	public ResponseEntity<String> login(User user){
		try {
			if(!"admin".equals(user.getAccount()) || !"123456".equals(user.getPwd())){
				return ResponseUtil.exception("账号或者密码错误");
			}
			user.setRoleId(1L);
			String subject = JwtUtils.generalSubject(user);
			String token = jwt.createJWT(Constant.JWT_ID, subject, Constant.JWT_TTL);
			String refreshToken = jwt.createJWT(Constant.JWT_ID, subject, Constant.JWT_REFRESH_TTL);
			JSONObject jo = new JSONObject();
			jo.put("token", token);
			jo.put("refreshToken", refreshToken);
			return ResponseUtil.success(jo);
		} catch (Exception e) {
			log.error("错误：{}",e.getMessage());
			return ResponseUtil.unKonwException();
		}
	}

	@Async
	public void syncMethod() throws InterruptedException {
		log.info("进入异步方法");
		Thread.sleep(3000);
		log.info("结束异步方法");
	}
}
