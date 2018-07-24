package com.thirdparty.dto;

import org.springframework.stereotype.Component;

/**
 * @author Nikhil.Tirmanwar
 *
 */
@Component
public class LesseeDto {

	private String id;
	private String lesseeId;
	private String lesseeName;
	private String lesseeLocation;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLesseeId() {
		return lesseeId;
	}

	public void setLesseeId(String lesseeId) {
		this.lesseeId = lesseeId;
	}

	public String getLesseeName() {
		return lesseeName;
	}

	public void setLesseeName(String lesseeName) {
		this.lesseeName = lesseeName;
	}

	public String getLesseeLocation() {
		return lesseeLocation;
	}

	public void setLesseeLocation(String lesseeLocation) {
		this.lesseeLocation = lesseeLocation;
	}

	@Override
	public String toString() {
		return "LesseeDto [id=" + id + ", lesseeId=" + lesseeId + ", lesseeName=" + lesseeName + ", lesseeLocation="
				+ lesseeLocation + "]";
	}

}
