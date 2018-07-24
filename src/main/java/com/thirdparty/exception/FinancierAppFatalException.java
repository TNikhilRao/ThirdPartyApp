
package com.thirdparty.exception;

/**
 * @author Nikhil.Tirmanwar
 *
 */
public class FinancierAppFatalException extends BaseException {
	private static final long serialVersionUID = 1L;

	/**
	 * @param errorCode
	 * @param status
	 * @param message
	 */
	public FinancierAppFatalException(String errorCode, String status, String message) {
		super("FinancierAppFatalException", errorCode, status, message);
	}

}
