package com.arun.singh.exception.manager.testing;

import com.arun.singh.exception.manager.exception.BaseException;

/**
 * Created by Arun on 12/26/2015.
 */
public class ExceptionCreator {

    public BaseException createException(){
        throw new BaseException("101",new String[]{" Test "});
    }
}
