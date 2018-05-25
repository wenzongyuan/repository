package com.wzy.shiro.exception;

public class ShiroException extends Exception{

    /**
	 * 
	 */
	private static final long serialVersionUID = -4926220956613808930L;

	public ShiroException(String errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public ShiroException(String errorCode) {
        super(errorCode);
    }

    public ShiroException() {
        super();
    }

    public String getErrorCode() {
        return super.getMessage();
    }
}
