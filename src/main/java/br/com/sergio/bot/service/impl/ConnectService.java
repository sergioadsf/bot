package br.com.sergio.bot.service.impl;

import java.io.IOException;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
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
		HttpClient client = new HttpClient();

		HttpMethod method = new GetMethod(url);
		return execute(client, method);
	}

	private String execute(HttpClient client, HttpMethod method) throws IOException, HttpException {
		addProxy(client);
		int statusCode = client.executeMethod(method);
		if (statusCode != HttpStatus.SC_OK) {
			System.err.println("Method failed: " + method.getStatusLine());
		}

		byte[] responseBody = method.getResponseBody();

		return new String(responseBody);
	}

	@Override
	public final String post(String url, String params) throws Exception {
		PostMethod method = new PostMethod(url);
		HttpClient client = new HttpClient();

		method.setRequestEntity(new StringRequestEntity(params, "application/json", "UTF-8"));
		return execute(client, method);
	}

	private final void addProxy(HttpClient client) throws IOException {
		String[] proxy = ProxyConfig.getProxy();
		if (proxy != null) {
			HostConfiguration config = client.getHostConfiguration();
			Integer port = Integer.valueOf(proxy[ProxyConfig.PORT]);
			String url = proxy[ProxyConfig.URL];
			config.setProxy(url, port);
			
			String username = proxy[ProxyConfig.USERNAME];
	        String password = proxy[ProxyConfig.PASSWORD];
	        Credentials credentials = new UsernamePasswordCredentials(username, password);
	        AuthScope authScope = new AuthScope(url, port);

			client.getState().setProxyCredentials(authScope, credentials);
		}

	}

//	private final String inputStreamToString(InputStream is) throws Exception {
//		ByteArrayOutputStream result = new ByteArrayOutputStream();
//		byte[] buffer = new byte[1024];
//		int length;
//		while ((length = is.read(buffer)) != -1) {
//			result.write(buffer, 0, length);
//		}
//
//		return result.toString("UTF-8");
//	}

}
