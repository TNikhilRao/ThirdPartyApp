package com.thirdparty.response;

import com.thirdparty.dto.LesseeDto;

/**
 * @author Nikhil.Tirmanwar
 *
 */
public class LesseeResponse extends BaseResponse {

	private LesseeDto lesseeDetails;

	public LesseeDto getLesseeDetails() {
		return lesseeDetails;
	}

	public void setLesseeDetails(LesseeDto lesseeDetails) {
		this.lesseeDetails = lesseeDetails;
	}

}
