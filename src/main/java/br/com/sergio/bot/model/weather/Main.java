package br.com.sergio.bot.model.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.sergio.bot.model.DTO;

@SuppressWarnings("serial")
public class Main implements DTO {

	@JsonProperty("temp_min")
	private Double minTemperature;

	@JsonProperty("temp_max")
	private Double maxTemperature;

	@JsonProperty("pressure")
	private Double pressure;

	@JsonProperty("sea_level")
	private Double seaLevel;

	@JsonProperty("temp")
	private Double temperature;

	@JsonProperty("humidity")
	private Double humidity;

	@JsonProperty("grnd_level")
	private Double grnd_level;

	@JsonProperty("temp_kf")
	private Double temp_kf;

	public Main() {
	}

	public Double getMinTemperature() {
		return minTemperature;
	}

	public Double getMaxTemperature() {
		return maxTemperature;
	}

	public Double getPressure() {
		return pressure;
	}

	public Double getSeaLevel() {
		return seaLevel;
	}

	public Double getTemperature() {
		return temperature;
	}

	public Double getHumidity() {
		return humidity;
	}

	public Double getGrnd_level() {
		return grnd_level;
	}

	public Double getTemp_kf() {
		return temp_kf;
	}

}
