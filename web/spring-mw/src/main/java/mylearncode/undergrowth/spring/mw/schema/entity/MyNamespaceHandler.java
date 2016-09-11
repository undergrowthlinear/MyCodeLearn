package mylearncode.undergrowth.spring.mw.schema.entity;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class MyNamespaceHandler extends NamespaceHandlerSupport {  
    public void init() {  
        registerBeanDefinitionParser("people", new PeopleBeanDefinitionParser());  
    }  
} 
