package com.thirdparty.service;

import org.springframework.stereotype.Service;

import com.thirdparty.dto.FinancierDto;


/**
 * @author Nikhil.Tirmanwar
 *
 */
@Service
public interface IFinancierConnectorService {

	public FinancierDto getFinancierDto(String financierId);

}
