package com.thirdparty.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.thirdparty.dto.FinancierDto;
import com.thirdparty.exception.ConnectorException;
import com.thirdparty.exception.FinancierAppFatalException;
import com.thirdparty.service.IFinancierConnector;
import com.thirdparty.util.ResultCode;

/**
 * @author Nikhil.Tirmanwar
 *
 */
@Service
public class FinancierConnectorImpl implements IFinancierConnector {

	@Autowired
	RestTemplate restTemplate;

	@Value("${financier.api.baseurl}")
	private String financierBaseUrl;

	@Value("${financier.endpoints.retrieve}")
	private String getFinancierEndpoint;

	private static final Logger logger = LoggerFactory.getLogger(FinancierConnectorImpl.class);

	@Override
	@HystrixCommand(fallbackMethod = "getFinancierDto_Fallback")
	public FinancierDto getFinancierDto(String financierId) {

		/* ResponseEntity<FinancierResponse> financierResponeEntity = null; */
		ResponseEntity<FinancierDto> responeEntityDto = null;
		FinancierDto dto = new FinancierDto();
		try {
			responeEntityDto = restTemplate.exchange(financierBaseUrl + getFinancierEndpoint, HttpMethod.GET, null,
					FinancierDto.class, financierId);
		} catch (ResourceAccessException resourceAccessException) {
			logger.error("Error connecting financier-api to get Financier profile, URL : {} ,\n Exception is  : {} ",
					financierBaseUrl + getFinancierEndpoint, resourceAccessException);
			ResultCode error = ResultCode.FAILURE_CONNECT_FINANCIER_APP;
			throw new ConnectorException(error.getCode(), error.getStatus(), error.getMessage());
		}
		
		if (responeEntityDto.getStatusCode() == HttpStatus.CREATED
				|| responeEntityDto.getStatusCode() == HttpStatus.OK) {
			if (responeEntityDto.getBody() != null) {
				dto = responeEntityDto.getBody();
			}
		} else {
			ResultCode error = ResultCode.FAILURE_FINANCIER_NOT_FOUND;
			throw new FinancierAppFatalException(error.getCode(), error.getStatus(), error.getMessage());
		}
		return dto;

	}
	
	
	public FinancierDto getFinancierDto_Fallback(String financierId) {

		System.out.println("Financer Service is down!!! fallback route enabled...");

		return null;
	}

}
