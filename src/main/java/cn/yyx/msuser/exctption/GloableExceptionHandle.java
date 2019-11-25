package cn.yyx.msuser.exctption;

import cn.yyx.msuser.utils.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GloableExceptionHandle {
    @ExceptionHandler(SecurityException.class)
    public ResponseEntity<Result> resultResponseEntity(SecurityException exception){
        Result result = new Result(exception.getMessage(), 401);
       return  new ResponseEntity<>(result,HttpStatus.UNAUTHORIZED);
    }
}
