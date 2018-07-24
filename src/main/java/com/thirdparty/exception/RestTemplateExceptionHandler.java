package com.thirdparty.exception;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

/**
 * @author Nikhil.Tirmanwar
 *
 */
@Component
public class RestTemplateExceptionHandler implements ResponseErrorHandler {

    private static final Logger logger = LoggerFactory.getLogger(RestTemplateExceptionHandler.class);

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {

        logger.error("Response error in hasError : {}", response.getStatusCode());
        return false;
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        logger.error("handleError called");
    }

}
