package mycodelearn.undergrowth.mongodb.spring.redis;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

public class RedisMessageListener implements MessageListener {
    @Override
    public void onMessage( final Message message, final byte[] pattern ) {
        System.out.println( "Message received: " + message.toString() );
    }
}
