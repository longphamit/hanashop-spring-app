package com.longpc.hanashopspringapp.resource;

import com.longpc.hanashopspringapp.constant.LoggerConstant;
import com.longpc.hanashopspringapp.dto.BaseSearchParamDTO;
import com.longpc.hanashopspringapp.utils.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
public abstract class BaseResource<P,T> {
    public static final String INFO="INFO";
    public static final String WARNING= "WARNING";
    public static final String ERROR="ERROR";
    enum Message{
        SEARCH_SUCCESS,
        SEARCH_FAIL,
        CREATE_SUCCESS,
        CREATE_FAIL,
        UPDATE_SUCCESS,
        UPDATE_FAIL,
        REMOVE_SUCCESS,
        REMOVE_FAIL,
        LOGIN_BY_USERNAME_SUCCESS,
        LOGIN_BY_USERNAME_FAIL,
        LOGIN_BY_EMAIL_SUCCESS,
        LOGIN_BY_EMAIL_FAIL
    }

    protected ResponseEntity responseListDataObject(HttpStatus httpStatus, String message, List<T> data){
        return new ResponseEntity(
                new HttpResponse<T>(httpStatus.value(),httpStatus,httpStatus.getReasonPhrase().toUpperCase(),message,data),httpStatus
        );
    }
    protected ResponseEntity responseListDataString(HttpStatus httpStatus, String message, List<String>data){
        return new ResponseEntity(
                new HttpResponse<String>(httpStatus.value(),httpStatus,httpStatus.getReasonPhrase().toUpperCase(),message,data),httpStatus
        );
    }
    protected void log(P clazz,String message,String type){
        switch (type){
            case INFO:{
                LoggerFactory.getLogger(clazz.getClass()).info(LoggerConstant.createMessageLog(message));
                break;
            }
            case WARNING:{
                LoggerFactory.getLogger(clazz.getClass()).warn(LoggerConstant.createMessageLog(message));
                break;
            }
            case ERROR:{
                LoggerFactory.getLogger(clazz.getClass()).error(LoggerConstant.createMessageLog(message));
                break;
            }
        }
    }


    public abstract ResponseEntity search();
    public abstract ResponseEntity insert(T t);
    public abstract ResponseEntity update(T t);
}
