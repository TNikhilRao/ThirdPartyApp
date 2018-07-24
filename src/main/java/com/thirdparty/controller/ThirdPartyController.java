package com.thirdparty.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thirdparty.dto.FinancierDto;
import com.thirdparty.dto.LesseeDto;
import com.thirdparty.response.FinancierResponse;
import com.thirdparty.response.LesseeResponse;
import com.thirdparty.service.IFinancierConnector;
import com.thirdparty.service.IFinancierFeignService;
import com.thirdparty.service.ILesseeFeignService;
import com.thirdparty.util.ResultCode;

/**
 * @author Nikhil.Tirmanwar
 *
 */
@RestController
@RequestMapping(value = "/thirdparty")
public class ThirdPartyController {

	Logger logger = LoggerFactory.getLogger(ThirdPartyController.class);

	@Autowired
	IFinancierFeignService financierFeignService;

	@Autowired
	ILesseeFeignService lesseeFeignService;

	@Autowired
	IFinancierConnector financierConnector;

	@GetMapping(value = "/financier/{id}/")
	public ResponseEntity<FinancierResponse> getFinancier(@PathVariable("id") String financierId) {

		logger.debug("ThirdPartyController getFinancier Started ::");
		FinancierDto dto = new FinancierDto();

		dto = financierConnector.getFinancierDto(financierId);
		FinancierResponse response = new FinancierResponse();

		if (dto != null) {
			response.setCode(ResultCode.SUCCESS.getCode());
			response.setStatus(ResultCode.SUCCESS.getStatus());
			response.setMessage(ResultCode.SUCCESS.getMessage());
			response.setFinancierDetails(dto);
		} else {
			response.setCode(ResultCode.FAILURE_FINANCIER_NOT_FOUND.getCode());
			response.setStatus(ResultCode.FAILURE_FINANCIER_NOT_FOUND.getStatus());
			response.setMessage(ResultCode.FAILURE_FINANCIER_NOT_FOUND.getMessage());

		}

		System.out.println("ThirdParty Controller getFinancier response ::" + dto);
		return new ResponseEntity<FinancierResponse>(response, HttpStatus.OK);
	}

	@GetMapping(value = "/lessee/{id}/")
	public ResponseEntity<LesseeResponse> getLessee(@PathVariable("id") String lesseeId) {

		logger.debug("ThirdPartyController getLessee Started ::");
		LesseeDto dto = new LesseeDto();

		dto = lesseeFeignService.getLessee(lesseeId);
		LesseeResponse response = new LesseeResponse();

		if (dto != null) {
			response.setCode(ResultCode.SUCCESS.getCode());
			response.setStatus(ResultCode.SUCCESS.getStatus());
			response.setMessage(ResultCode.SUCCESS.getMessage());
			response.setLesseeDetails(dto);
		} else {
			response.setCode(ResultCode.FAILURE_FINANCIER_NOT_FOUND.getCode());
			response.setStatus(ResultCode.FAILURE_FINANCIER_NOT_FOUND.getStatus());
			response.setMessage(ResultCode.FAILURE_FINANCIER_NOT_FOUND.getMessage());

		}

		System.out.println("ThirdParty Controller getFinancier response ::" + dto);
		return new ResponseEntity<LesseeResponse>(response, HttpStatus.OK);
	}

}
