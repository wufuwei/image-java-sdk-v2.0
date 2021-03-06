package com.qcloud.image.exception;

import com.qcloud.image.http.ResponseBodyKey;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * 封装cos异常
 * @author chengwu
 *
 */
public abstract class AbstractImageException extends Exception {

    private static final long serialVersionUID = 7547532865194837136L;
    
    private ImageExceptionType type;

    public AbstractImageException(String message, Throwable cause, ImageExceptionType type) {
        super(message, cause);
        this.type = type;
    }

    public AbstractImageException(ImageExceptionType type, String message) {
        super(message);
        this.type = type;
    }

    public ImageExceptionType getType() {
        return type;
    }
    
    @Override
    public String toString() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        printStackTrace(new PrintStream(out));
        String str = new String(out.toByteArray());
        
        JSONObject responseObj = new JSONObject();
        responseObj.put(ResponseBodyKey.CODE, type.getErrorCode());
        responseObj.put(ResponseBodyKey.MESSAGE, getMessage());
        responseObj.put("StackTrace", str);
        return responseObj.toString();
    }
    
}
