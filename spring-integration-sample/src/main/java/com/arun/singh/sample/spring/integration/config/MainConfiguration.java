package com.arun.singh.sample.spring.integration.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.jms.Jms;
import org.springframework.integration.dsl.support.GenericHandler;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.jms.core.JmsTemplate;

/**
 * Created by Arun on 12/5/2015.
 */
@Configuration
@EnableIntegration
@PropertySource("basic.properties")
public class MainConfiguration {


    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfiguration.class);

    }


    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL("tcp://localhost:61616");
        return activeMQConnectionFactory;
    }

    @Bean
    public CachingConnectionFactory cachingConnectionFactory(){
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(this.activeMQConnectionFactory());
        cachingConnectionFactory.setSessionCacheSize(10);
        return cachingConnectionFactory;
    }


    @Bean
    public JmsTransactionManager jmsTransactionManager(){
        JmsTransactionManager jmsTransactionManager = new JmsTransactionManager();
        jmsTransactionManager.setConnectionFactory(this.cachingConnectionFactory());
        return jmsTransactionManager;
    }

    @Bean
    public JmsTemplate jmsTemplate(){
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(this.cachingConnectionFactory());
        jmsTemplate.setDeliveryPersistent(true);
        return jmsTemplate;
    }


    @Bean
    public GenericHandler<String> myHandle(){
        return (payload, headers) ->
        {
            System.out.println("handler " +payload);
            return payload;
        };
    }



    @Bean
    public GenericHandler<String> myHandle1(){
        return (payload, headers) ->
        {
            System.out.println("handler 1 " +payload);
            return payload;
        };
    }

    @Bean
    public IntegrationFlow process() {
        return IntegrationFlows.from(Jms.messageDriverChannelAdapter(cachingConnectionFactory()).destination("arun.test.queue1"))
                .handle(myHandle())
                .handle(myHandle1())
                .handle(h -> System.out.println(h.getPayload()))
                .get();
    }



}
