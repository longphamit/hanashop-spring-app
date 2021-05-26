package com.longpc.hanashopspringapp.constant;

public class LoggerConstant {
    public static final String SPLASH=" -------------------------- ";
    public static String createMessageLog(String message,String info){
        return SPLASH+info+" "+message+SPLASH;
    }
    public static String createMessageLog(String message){
        return SPLASH+message+SPLASH;
    }
}
