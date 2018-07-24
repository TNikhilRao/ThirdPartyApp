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

import com.thirdparty.dto.LesseeDto;
import com.thirdparty.exception.ConnectorException;
import com.thirdparty.exception.FinancierAppFatalException;
import com.thirdparty.service.ILesseeConnectorService;
import com.thirdparty.util.ResultCode;

@Service
public class LesseeConnectorServiceImpl implements ILesseeConnectorService {

	@Autowired
	RestTemplate restTemplate;

	@Value("${lessee.api.baseurl}")
	private String lesseeBaseUrl;

	@Value("${lessee.endpoints.retrieve}")
	private String getLesseeEndpoint;

	private static final Logger logger = LoggerFactory.getLogger(LesseeConnectorServiceImpl.class);

	@Override
	public LesseeDto getLesseeDto(String lesseeId) {
		ResponseEntity<LesseeDto> responeEntityDto = null;
		LesseeDto dto = new LesseeDto();
		try {
			responeEntityDto = restTemplate.exchange(lesseeBaseUrl + getLesseeEndpoint, HttpMethod.GET, null,
					LesseeDto.class, lesseeId);
		} catch (ResourceAccessException resourceAccessException) {
			logger.error("Error connecting lessee-api to get Lessee profile, URL : {} ,\n Exception is  : {} ",
					lesseeBaseUrl + getLesseeEndpoint, resourceAccessException);
			ResultCode error = ResultCode.FAILURE_CONNECT_LESSEE_APP;
			throw new ConnectorException(error.getCode(), error.getStatus(), error.getMessage());
		}

		if (responeEntityDto.getStatusCode() == HttpStatus.CREATED
				|| responeEntityDto.getStatusCode() == HttpStatus.OK) {
			if (responeEntityDto.getBody() != null) {
				dto = responeEntityDto.getBody();
			}
		} else {
			ResultCode error = ResultCode.FAILURE_LESSEE_NOT_FOUND;
			throw new FinancierAppFatalException(error.getCode(), error.getStatus(), error.getMessage());
		}
		return dto;
	}

}
