package cn.wenqi.jwt.exception;

import cn.wenqi.jwt.utils.ResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author wenqi
 */
@ControllerAdvice
public class ExHandle {

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<String> doEx(Throwable e){
        return ResponseUtil.exception(e.getMessage());
    }
}
