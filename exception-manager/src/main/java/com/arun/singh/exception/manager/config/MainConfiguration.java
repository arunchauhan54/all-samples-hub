package com.arun.singh.exception.manager.config;

import com.arun.singh.exception.manager.base.ExceptionHandler;
import com.arun.singh.exception.manager.base.ExceptionManager;
import com.arun.singh.exception.manager.exception.BaseException;
import com.arun.singh.exception.manager.testing.ExceptionCreator;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.core.Pollers;
import org.springframework.integration.dsl.jms.Jms;
import org.springframework.integration.endpoint.MethodInvokingMessageSource;
import org.springframework.integration.transformer.GenericTransformer;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.messaging.MessagingException;


@Configuration
@EnableIntegration
@PropertySource("exception.properties")
@Import(ExceptionHandlingConfiguration.class)
public class MainConfiguration {

    @Autowired
    Environment environment;

    @Autowired
    ExceptionManager exceptionManager;

    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfiguration.class);

    }

    @Bean
    public GenericTransformer<MessagingException,BaseException> toBaseException() {
        return (source) ->
        {
            Throwable exception = source.getCause();
            if (!(exception instanceof BaseException)) {
                exception = new BaseException("000", new String[]{exception.getMessage()});
            }
            return (BaseException)exception;
        };
    }

    @Bean
    public DirectChannel inputChannel() {
        return new DirectChannel();
    }

    @Bean
    public DirectChannel errorChannel() {
        return new DirectChannel();
    }


    @Bean
    public MessageSource<?> integerMessageSource() {
        MethodInvokingMessageSource source = new MethodInvokingMessageSource();
        source.setObject(new ExceptionCreator());
        source.setMethodName("createException");
        return source;
    }

    @Bean
    public IntegrationFlow errorGeneratingFlow() {
        return IntegrationFlows.from(this.integerMessageSource(), c ->
                c.poller(Pollers.fixedRate(100)))
                .handle(message -> System.out.println(message))
                .get();
    }

    @Bean
    public IntegrationFlow errorHandlingFlow() {
        return IntegrationFlows.from(errorChannel())
                .transform(toBaseException())
                .handle(exceptionManager)
                .get();
    }
}
