package com.thirdparty.service;

import org.springframework.stereotype.Service;

import com.thirdparty.dto.FinancierDto;


/**
 * @author Nikhil.Tirmanwar
 *
 */
@Service
public interface IFinancierConnector {

	public FinancierDto getFinancierDto(String financierId);

}
