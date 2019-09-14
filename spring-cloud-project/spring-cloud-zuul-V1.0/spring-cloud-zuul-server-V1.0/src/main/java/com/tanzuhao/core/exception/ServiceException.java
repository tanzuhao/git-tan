package com.tanzuhao.core.exception;
/**
 * Service层异常
 * @author tanzuhao
 * @date: 2019年9月9日 下午5:25:48
 */
public class ServiceException extends BaseException {
    private static final long serialVersionUID = 6058294324031642376L;

    public ServiceException() {}

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String code, String message) {
        super(code, message);
    }

}