package br.com.sergio.bot.service;

public interface IConnectService {

	public String get(String url) throws Exception;

	public String post(String url, String params) throws Exception;

	public String get(String url, String cityName) throws Exception;

}
