package mycodelearn.undergrowth.mongodb.spring.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class AppConfig {
    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
    	JedisConnectionFactory connectionFactory=new JedisConnectionFactory();
    	connectionFactory.setHostName("192.168.126.129");
    	connectionFactory.setPort(6379);
    	connectionFactory.setPassword("");
        return connectionFactory;
    }

    @Bean
    RedisTemplate< String, Object > redisTemplate() {
        final RedisTemplate< String, Object > template =  new RedisTemplate< String, Object >();
        template.setConnectionFactory( jedisConnectionFactory() );
        template.setKeySerializer( new StringRedisSerializer() );
        template.setHashValueSerializer( new GenericToStringSerializer< Object >( Object.class ) );
        template.setValueSerializer( new GenericToStringSerializer< Object >( Object.class ) );
        return template;
    }

    @Bean
    MessageListenerAdapter messageListener() {
        return new MessageListenerAdapter( new RedisMessageListener() );
    }
    
    @Bean
    MessageListenerAdapter messageListenerOther() {
        return new MessageListenerAdapter( new RedisMessageListenerOther() );
    }

    @Bean
    RedisMessageListenerContainer redisContainer() {
        final RedisMessageListenerContainer container = new RedisMessageListenerContainer();

        container.setConnectionFactory( jedisConnectionFactory() );
        container.addMessageListener( messageListener(), topic() );
        container.addMessageListener( messageListenerOther(), topic() );

        return container;
    }
 
    @Bean
    IRedisPublisher redisPublisher() {
        return new RedisPublisherImpl( redisTemplate(), topic() );
    }

    @Bean
    ChannelTopic topic() {
        return new ChannelTopic( "pubsub:queue");
    }
}
