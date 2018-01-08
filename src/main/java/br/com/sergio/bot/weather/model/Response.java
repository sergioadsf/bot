package br.com.sergio.bot.weather.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response<T extends Object> implements DTO {

	@JsonProperty("success")
	private boolean success;

	@JsonProperty("message")
	private String message;

	@JsonProperty("response")
	private T response;

	@JsonCreator
	public Response(@JsonProperty("success") Boolean success, @JsonProperty("message") String message, @JsonProperty("response") T response) {
		super();
		this.success = success;
		this.message = message;
		this.response = response;
	}

	public boolean isSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}

	public T getResponse() {
		return response;
	}

}
