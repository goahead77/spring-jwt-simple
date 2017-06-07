package cn.wenqi.jwt.interceptor;

import cn.wenqi.jwt.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.util.Date;

/**
 * @author wenqi
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtils jwtUtils;

    private static final Log log= LogFactory.getLog(TokenInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String token=request.getParameter("token");
        if(StringUtils.isEmpty(token)) {
            throw new IllegalAccessException("token为空！");
        }
        Claims claims;
        try {
            claims = jwtUtils.parseJWT(token);
        }catch (Exception e){
            throw new IllegalAccessException("token不合法");
        }
        String subject=claims.getSubject();
        log.info("subject:"+subject);
        Date exp=claims.getExpiration();
        if(exp.before(new Date())) {
            log.warn("token过期！");
            throw new IllegalAccessException("token过期");
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
