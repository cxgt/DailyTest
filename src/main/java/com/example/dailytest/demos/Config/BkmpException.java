package com.example.dailytest.demos.Config;

/**
 * @author chenxin
 * @Description
 * @date 2023/12/12 10:02
 */
public class BkmpException extends RuntimeException{
    public BkmpException(Exception message) {
        super(message.getMessage());
    }
}
