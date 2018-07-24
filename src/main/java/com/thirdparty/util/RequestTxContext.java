package com.thirdparty.util;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

/**
 * Provides access to the unique ids for the request
 *
 * Meant to be used as a bean with request scope.
 *
 * @see RequestScope
 */
@Component
public class RequestTxContext {

	private String txId;

	public String getTxId() {
		return txId;
	}

	public void setTxId(String txId) {
		this.txId = txId;
	}

	public void clear() {
		this.txId = null;
	}

}