package com.arun.singh.exception.client.config;

import com.arun.singh.exception.client.testing.ExceptionCreator;
import org.springframework.context.annotation.*;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.core.Pollers;
import org.springframework.integration.endpoint.MethodInvokingMessageSource;


@Configuration
@EnableIntegration
@PropertySource("exception.properties")
@Import(ExceptionHandlingConfiguration.class)
public class MainClientConfiguration {

    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainClientConfiguration.class);

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
                c.poller(Pollers.fixedRate(10000)))
                .handle(message -> System.out.println(message))
                .get();
    }

}
