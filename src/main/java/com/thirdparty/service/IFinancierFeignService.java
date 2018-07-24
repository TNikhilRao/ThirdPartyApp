package com.thirdparty.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.thirdparty.dto.FinancierDto;

/**
 * @author Nikhil.Tirmanwar
 *
 */
@FeignClient(name = "financierClient", url = "${financier.endpoints.host}${financier.endpoints.contextroot}")
public interface IFinancierFeignService {

	@RequestMapping(method = RequestMethod.GET, value = "${financier.endpoints.retrieve}")
	public FinancierDto getFinancier(@PathVariable("{id}") String financierId);

}
