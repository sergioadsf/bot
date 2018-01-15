package br.com.sergio.bot.model.weather;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.sergio.bot.model.DTO;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Forecast implements DTO {

	@JsonProperty("cod")
	private Integer code;

	@JsonProperty("message")
	private Double msgCode;

	@JsonProperty("cnt")
	private Integer cnt;

	@JsonProperty("city")
	private City city;

	@JsonProperty("list")
	private List<ForecastDetail> listForecast;

	public Forecast() {
	}

	public List<ForecastDetail> getListForecast() {
		return listForecast;
	}

	public void setListForecast(List<ForecastDetail> listForecast) {
		this.listForecast = listForecast;
	}

	public Integer getCode() {
		return code;
	}

	public Double getMsgCode() {
		return msgCode;
	}

	public Integer getCnt() {
		return cnt;
	}

	public City getCity() {
		return city;
	}

}
