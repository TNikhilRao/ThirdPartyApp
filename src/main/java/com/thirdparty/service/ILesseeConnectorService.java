package com.thirdparty.service;

import org.springframework.stereotype.Service;

import com.thirdparty.dto.LesseeDto;


/**
 * @author Nikhil.Tirmanwar
 *
 */
@Service
public interface ILesseeConnectorService {

	public LesseeDto getLesseeDto(String lesseeIdId);

}
