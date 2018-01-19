package br.com.sergio.bot.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;

import org.springframework.stereotype.Service;

import br.com.sergio.bot.service.IConnectService;
import br.com.sergio.bot.util.ProxyConfig;

@Service
public class ConnectService implements IConnectService {

	@Override
	public String get(String url, String params) throws Exception {
		return get(url + java.net.URLEncoder.encode(params, "UTF-8"));
	}

	@Override
	public final String get(String url) throws Exception {
		URL target = new URL(url);

		HttpURLConnection connection = initConnection(target);
		connection.setRequestMethod("GET");
		connection.setDoOutput(true);
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("Accept", "application/json");

		return inputStreamToString(connection.getInputStream());
	}

	@Override
	public final String post(String url, String params) throws Exception {
		URL target = new URL(url);

		HttpURLConnection connection = initConnection(target);
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("Accept", "application/json");

		OutputStream output = connection.getOutputStream();
		output.write(params.getBytes("UTF-8"));
		output.flush();

		return inputStreamToString(connection.getInputStream());
	}

	private final HttpURLConnection initConnection(URL target) throws IOException {
		Proxy proxy = ProxyConfig.get();
		if (proxy != null) {
			return (HttpURLConnection) target.openConnection(proxy);
		}

		return (HttpURLConnection) target.openConnection();

	}

	private final String inputStreamToString(InputStream is) throws Exception {
		ByteArrayOutputStream result = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int length;
		while ((length = is.read(buffer)) != -1) {
			result.write(buffer, 0, length);
		}

		return result.toString("UTF-8");
	}

}
