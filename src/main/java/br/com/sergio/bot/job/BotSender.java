package br.com.sergio.bot.job;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sergio.bot.BotConfig;

@Service
public class BotSender {

	@Autowired
	private BotConfig botConfig;

	public static final String BASEURL = "https://api.telegram.org/bot";
	public static final String PATH = "sendmessage";

	public static final String CHATID_FIELD = "chat_id";
	public static final String TEXT_FIELD = "text";

	public void send(Long chatId, String message) {
		try {
			String url = BASEURL + botConfig.getToken() + "/" + PATH;
			HttpPost httppost = new HttpPost(url);
			httppost.addHeader("Content-type", "application/x-www-form-urlencoded");
			httppost.addHeader("charset", "UTF-8");

			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair(CHATID_FIELD, String.valueOf(chatId)));
			nameValuePairs.add(new BasicNameValuePair(TEXT_FIELD, message));
			nameValuePairs.add(new BasicNameValuePair("parse_mode", "markdown"));

			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));

			HttpClient httpclient = HttpClients.createDefault();
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream instream = entity.getContent();
				try {
				} finally {
					instream.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}