package com.thirdparty.response;

import com.thirdparty.dto.FinancierDto;

/**
 * @author Nikhil.Tirmanwar
 *
 */
public class FinancierResponse extends BaseResponse {

	private FinancierDto financierDetails;

	public FinancierDto getFinancierDetails() {
		return financierDetails;
	}

	public void setFinancierDetails(FinancierDto financierDto) {
		this.financierDetails = financierDto;
	}

	@Override
	public String toString() {
		return "FinancierResponse [financierDto=" + financierDetails + "]";
	}

}
