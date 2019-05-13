package com.caoliyuan.travelGuide.util;

public class ServiceException extends Exception {

    public ServiceException() {
        super();
    }
    public ServiceException(String message){
        super(message);
    }
    public ServiceException(Throwable throwable){
        super(throwable);
    }
    public ServiceException(String message ,Throwable throwable){
        super(message, throwable);
    }
}