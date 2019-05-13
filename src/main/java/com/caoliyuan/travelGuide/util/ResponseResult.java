package com.caoliyuan.travelGuide.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ResponseResult implements Serializable {

    private static final long serialVersionUID = -148117940294941578L;

    private String code;
    private String message;
    private Object obj;

    private Map<String,Object> data; //默认为hashMap,也可为对象

    public String getCode() {
        return code;
    }

    public ResponseResult() {
        this.code = IStatusMessage.SystemStatus.SUCCESS.getCode();
        this.message = IStatusMessage.SystemStatus.SUCCESS.getMessage();
    }

    public ResponseResult(IStatusMessage statusMessage){
        this.code = statusMessage.getCode();
        this.message = statusMessage.getMessage();

    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public Map<String,Object> getData() {
        return data;
    }
    public void setData(Map<String,Object> data) {
        this.data = data;
    }

    public void putData(String key,Object value){
        if( this. data == null ){
            this.data = new HashMap<String,Object>();
        }
        this.data.put(key, value);
    }

}
