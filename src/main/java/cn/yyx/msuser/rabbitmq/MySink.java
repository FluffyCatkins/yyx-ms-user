package cn.yyx.msuser.rabbitmq;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface MySink {
    /**
     * Input channel name.
     */
    String MYINPUT = "myInput";

    /**
     * @return input channel.
     */
    @Input(MySink.MYINPUT)
    SubscribableChannel input();
}
