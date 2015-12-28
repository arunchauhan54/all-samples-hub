package com.arun.singh.exception.manager.config;

import com.arun.singh.exception.manager.base.ExceptionManager;
import com.arun.singh.exception.manager.exception.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.transformer.GenericTransformer;
import org.springframework.messaging.MessagingException;


@Configuration
@EnableIntegration
@PropertySource("exception.properties")
public class ExceptionFlowConfiguration {

    @Autowired
    ExceptionManager exceptionManager;

    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ExceptionFlowConfiguration.class);

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
    public DirectChannel errorChannel() {
        return new DirectChannel();
    }

    @Bean
    public IntegrationFlow errorHandlingFlow() {
        return IntegrationFlows.from(errorChannel())
                .transform(toBaseException())
                .handle(exceptionManager)
                .get();
    }

}
