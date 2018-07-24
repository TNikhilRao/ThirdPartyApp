package com.thirdparty.exception;

/**
 * @author Nikhil.Tirmanwar
 *
 */
public class ConnectorException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param exception
	 * @param errorCode
	 * @param status
	 * @param message
	 */
	public ConnectorException(String errorCode, String status, String message) {
		super("ConnectorException", errorCode, status, message);
	}

}
