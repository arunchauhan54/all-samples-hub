package com.arun.singh.exception.manager.testing;

import com.arun.singh.exception.manager.exception.BaseException;


public class ExceptionCreator {

    public BaseException createException(){
        throw new BaseException("101",new String[]{" Arun "});
    }
}
