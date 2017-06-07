package cn.wenqi.jwt;

import cn.wenqi.jwt.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author wenqi
 */
@Controller
@SpringBootApplication
public class JwtApplication extends WebMvcConfigurerAdapter {

    @Autowired
    private TokenInterceptor tokenInterceptor;

    public static void main(String[] args) {
        SpringApplication.run(JwtApplication.class,args);
    }

    @RequestMapping("/")
    public String index(){
        return "/index";
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(tokenInterceptor).excludePathPatterns("/login","/token/**","/*");
    }
}
