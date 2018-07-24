package com.thirdparty.exception;

/**
 * @author Nikhil.Tirmanwar
 *
 */
public class BaseException extends RuntimeException {

	/** serialVersionUID */
	private static final long serialVersionUID = 8675007456900089437L;

	public final String errorCode;
	public final String status;
	public final String message;

	/**
	 * Constructor to call super class constructor and initialize errorCode
	 * 
	 * @param errorCode
	 */
	public BaseException(String exception, String errorCode, String status, String message) {
		super(exception);
		this.errorCode = errorCode;
		this.status = status;
		this.message = message;
	}

	public String getErrorCode() {
		return this.errorCode;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
}
