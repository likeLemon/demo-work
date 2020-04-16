package com.lywq.demo.common.exception;

import java.io.Serializable;

/**
 * @description: 业务类异常
 * @author: lywq WED
 * @time: 2019/11/5 21:45
 */
public class ServiceException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1213855733833039552L;

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}
