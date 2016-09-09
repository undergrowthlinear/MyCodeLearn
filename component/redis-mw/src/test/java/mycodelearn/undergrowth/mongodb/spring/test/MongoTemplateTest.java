package mycodelearn.undergrowth.mongodb.spring.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import mycodelearn.undergrowth.mongodb.spring.mongo.Customer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-mongo.xml")
public class MongoTemplateTest {


    @Autowired private MongoTemplate mongo;


    @Test
    public void test(){
        Customer c = new Customer();
        c.setFirstName("wu");
        c.setLastName("wei");
        mongo.insert(c); 
    }
}