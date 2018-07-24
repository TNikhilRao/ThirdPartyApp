package com.thirdparty.util;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Servlet filter that reads the txId and appCode headers and makes them
 * available to the application through the {@link RequestTxContext}.
 *
 * If the txId header is not set, a new txId is generated.
 */

@Component
public class RequestTxFilter extends OncePerRequestFilter {

	@Autowired
	private RequestTxContext requestTransactionContext;

	public RequestTxFilter(RequestTxContext requestTransactionContext) {
		this.requestTransactionContext = requestTransactionContext;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;

		String reqId = httpRequest.getHeader("request-id");

		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.addHeader("response-id", reqId);

		// MDC.put("reqId", reqId);

		requestTransactionContext.setTxId(reqId);

		try {
			filterChain.doFilter(request, response);
		} finally {
			// MDC.remove("reqId");

			requestTransactionContext.clear();
		}
	}
}