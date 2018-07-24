package com.thirdparty.exception;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Nikhil.Tirmanwar
 *
 */
public class ValidationError {
    @JsonProperty
    private final String field;

    @JsonProperty
    private final String reason;

    @JsonCreator
    public ValidationError(@JsonProperty("field") String field,
                    @JsonProperty("reason") String reason) {
        this.field = field;
        this.reason = reason;
    }

    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ValidationError [field=" + field + ", reason=" + reason + "]";
	}
}
