package mycodelearn.undergrowth.mongodb.spring.mongo;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.DB;

public class AbstractBaseMongoTemplete  implements ApplicationContextAware {  
    protected MongoTemplate mongoTemplate;    
      
    protected DB db;  
      
    /**  
     * 设置mongoTemplate  
     * @param mongoTemplate the mongoTemplate to set  
     */    
    public void setMongoTemplate(MongoTemplate mongoTemplate) {    
        this.mongoTemplate = mongoTemplate;    
    }    
        
    public void setApplicationContext(ApplicationContext applicationContext)    
            throws BeansException {    
        //System.out.println("ApplicationContextAware开始执行setApplicationContext方法");  
        MongoTemplate mongoTemplate = applicationContext.getBean("mongoTemplate", MongoTemplate.class);    
        setMongoTemplate(mongoTemplate);    
        db=mongoTemplate.getDb();  
    }    
}  
