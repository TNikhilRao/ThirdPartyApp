package com.thirdparty.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import feign.Response;
import feign.codec.ErrorDecoder;

/**
 * @author Nikhil.Tirmanwar
 *
 */
public class GlobalErrorDecoder implements ErrorDecoder {

	private final Logger logger = LoggerFactory.getLogger(ErrorDecoder.class);

	@Override
	public Exception decode(String methodKey, Response response) {

		logger.debug("Decoding error response for " + methodKey + ", status=" + response.status());

		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setHttpStatus(HttpStatus.valueOf(response.status()));
		errorResponse.setStatus(HttpStatus.valueOf(response.status()).toString());
		errorResponse.setMessage(response.reason());
		errorResponse.setSource(methodKey);

		return new DownstreamException("Downstream service error", errorResponse);
	}
}