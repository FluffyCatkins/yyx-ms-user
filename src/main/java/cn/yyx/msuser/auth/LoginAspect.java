package cn.yyx.msuser.auth;

import cn.yyx.msuser.exctption.SecurityException;
import cn.yyx.msuser.utils.JwtOperator;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class LoginAspect {
    @Autowired
    JwtOperator jwtOperator;
    
    @Around("@annotation(cn.yyx.msuser.auth.Login)")
    public Object checkLogin(ProceedingJoinPoint joinPoint){
        try {
            HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String token = httpServletRequest.getHeader("Authorization");
            if (StringUtils.isEmpty(token)){
               throw  new SecurityException("没有token信息!");
            }
            if (!jwtOperator.validateToken(token)){
                throw new SecurityException("token不合法！");
            }
            Claims claims = jwtOperator.getClaimsFromToken(token);
            httpServletRequest.setAttribute("username",claims.get("username"));
            httpServletRequest.setAttribute("password",claims.get("password"));
            return joinPoint.proceed();
        } catch (Throwable throwable) {
           throw new SecurityException(throwable);
        }
    }
}
