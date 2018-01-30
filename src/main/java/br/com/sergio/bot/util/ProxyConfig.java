package br.com.sergio.bot.util;

import java.net.InetSocketAddress;
import java.net.Proxy;

public class ProxyConfig {

	private static final String proxyUrl = "w_proxy";

	private static final String proxyPort = "w_proxy_port";
	
	private static final String proxy = "HTTP_PROXY";

	public static final int USERNAME = 0;
	
	public static final int PASSWORD = 1;
	
	public static final int URL = 2;
	
	public static final int PORT = 3;

	public static final String getProxyUrl() {
		return System.getenv(proxyUrl);
	}

	public static final int getProxyPort() {
		String value = System.getenv(proxyPort);
		return value == null ? 0 : Integer.valueOf(value);
	}

	public static final String[] getProxy() {
		String value = System.getenv(proxy);
		if(value == null)
			return null;
		
		value = value.substring("http://".length()).replace("@", ":");
		return value.split(":");
	}

	public static Proxy get() {
		String urlProxy = getProxyUrl();
		if (urlProxy != null)
			return new Proxy(Proxy.Type.HTTP, new InetSocketAddress(urlProxy, getProxyPort()));

		return null;
	}

}
