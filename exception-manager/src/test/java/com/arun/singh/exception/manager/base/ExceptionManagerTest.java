package com.arun.singh.exception.manager.base;

import com.arun.singh.exception.manager.config.ExceptionFlowConfiguration;
import com.arun.singh.exception.manager.config.ExceptionHandlingConfiguration;
import com.arun.singh.exception.manager.exception.BaseException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ExceptionHandlingConfiguration.class)
public class ExceptionManagerTest {
    @Autowired
    ExceptionManager exceptionManager;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testHandle() throws Exception {
        exceptionManager.handle(new BaseException("101",new String[]{" Test "}),null);
        exceptionManager.handle(new BaseException("102",new String[]{"-0.45X"}),null);

    }
}