package cn.yyx.msuser.exctption;

public class SecurityException extends RuntimeException {
    public SecurityException(Throwable throwable){
        super(throwable);
    }

    public SecurityException(String message){
        super(message);
    }
}
