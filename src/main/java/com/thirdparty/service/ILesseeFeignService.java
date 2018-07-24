package com.thirdparty.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.thirdparty.dto.LesseeDto;

/**
 * @author Nikhil.Tirmanwar
 *
 */
@FeignClient(name = "lesseeClient", url = "${lessee.endpoints.host}${lessee.endpoints.contextroot}")
public interface ILesseeFeignService {

	@RequestMapping(method = RequestMethod.GET, value = "${lessee.endpoints.retrieve}$")
	public LesseeDto getLessee(@PathVariable(value = "id") String lesseeId);

}
