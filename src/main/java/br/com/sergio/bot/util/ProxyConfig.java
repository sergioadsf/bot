package br.com.sergio.bot.util;

import java.net.InetSocketAddress;
import java.net.Proxy;

public class ProxyConfig {

	private static final String proxyUrl = "w_proxy";

	private static final String proxyPort = "w_proxy_port";

	public static final String getProxyUrl() {
		return System.getenv(proxyUrl);
	}

	public static final int getProxyPort() {
		String value = System.getenv(proxyPort);
		return value == null ? 0 : Integer.valueOf(value);
	}

	public static Proxy get() {
		String urlProxy = getProxyUrl();
		if (urlProxy != null)
			return new Proxy(Proxy.Type.HTTP, new InetSocketAddress(urlProxy, getProxyPort()));

		return null;
	}

}
