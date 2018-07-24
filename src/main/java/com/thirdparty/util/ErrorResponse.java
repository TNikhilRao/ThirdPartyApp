package com.thirdparty.util;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.google.common.collect.Lists;
import com.thirdparty.exception.ValidationError;

// Represents error information related to an operation
/**
 * @author Nikhil.Tirmanwar
 *
 */
@JsonInclude(Include.NON_EMPTY)
public class ErrorResponse {

	@JsonProperty
	@JsonDeserialize(using = ParseDeserializer.class)
	private LocalDateTime timestamp;

	@JsonIgnore
	private HttpStatus httpStatus;

	@JsonProperty
	private String status;

	@JsonProperty
	private String category;

	@JsonProperty
	private String code;

	@JsonProperty
	private String exception;

	@JsonProperty
	private String message;

	@JsonProperty
	private String path;

	@JsonProperty
	private String source;

	@JsonProperty
	private List<ValidationError> validationErrors;

	@JsonProperty
	private ErrorResponse causedBy;

	public ErrorResponse() {

	}

	public ErrorResponse(LocalDateTime timestamp, ErrorResponseType errorResponseParam, String exception, String message, String path, String source) {
		if (errorResponseParam == null) {
			throw new IllegalArgumentException("ErrorResponseParam can't be null. Needed it to populate error response");
		}

		this.timestamp = timestamp != null ? timestamp : LocalDateTime.now();
		this.httpStatus = errorResponseParam.getStatus();
		this.status = httpStatus.toString();
		this.category = errorResponseParam.getCategory();
		this.code = errorResponseParam.getCode().toString();
		this.exception = exception;
		this.message = message;
		this.path = path;
		this.source = source;
	}

	@JsonIgnore
	public void setValidationErrors(List<ValidationError> errors) {
		this.validationErrors = errors;
	}

	public void setErrors(List<FieldError> errors) {
		this.validationErrors = errors.stream().map(e -> new ValidationError(e.getField(), e.getDefaultMessage())).collect(Collectors.toList());
	}

	public void setMessageNotReadableErrors(HttpMessageNotReadableException ex) {
		if (ex.getMostSpecificCause() instanceof InvalidFormatException) {
			final InvalidFormatException invalidFormatException = (InvalidFormatException) ex.getMostSpecificCause();
			final String field = invalidFormatException.getPath().stream().map(e -> e.getFieldName()).collect(Collectors.joining("."));
			this.validationErrors = Lists.newArrayList(new ValidationError(field, invalidFormatException.getMessage()));
		}
	}

	@JsonIgnore
	public void setCausedBy(ErrorResponse causedBy) {
		this.causedBy = causedBy;
	}

	public String getTimestamp() {
		return timestamp != null ? DateTimeFormatter.ofPattern("YYYY-MMM-dd HH:mm:ss.SSS").format(timestamp) : "UNKNOWN";
	}

	@JsonIgnore
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	@JsonIgnore
	public String getStatus() {
		return status;
	}

	@JsonIgnore
	public String getCategory() {
		return category;
	}

	@JsonIgnore
	public String getCode() {
		return code;
	}

	@JsonIgnore
	public String getException() {
		return exception;
	}

	@JsonIgnore
	public String getMessage() {
		return message;
	}

	@JsonIgnore
	public String getPath() {
		return path;
	}

	@JsonIgnore
	public String getSource() {
		return source;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public List<ValidationError> getValidationErrors() {
		return validationErrors;
	}

	public ErrorResponse getCausedBy() {
		return causedBy;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ErrorResponse [timestamp=" + timestamp + ", httpStatus=" + httpStatus + ", status=" + status + ", category=" + category + ", code=" + code
				+ ", exception=" + exception + ", message=" + message + ", path=" + path + ", source=" + source + ", validationErrors=" + validationErrors
				+ ", causedBy=" + causedBy + "]";
	}
}

/**
 * Helper to serialize/deserialize LocalDateTime
 */
class ParseDeserializer extends StdDeserializer<LocalDateTime> {
	public ParseDeserializer() {
		super(LocalDateTime.class);
	}

	@Override
	public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MMM-dd HH:mm:ss.SSS").withResolverStyle(ResolverStyle.LENIENT);
		return LocalDateTime.parse(p.getValueAsString(), formatter);
	}
}