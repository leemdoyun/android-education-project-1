package com.example.samplelistpopupwindow;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import org.apache.http.Header;

import android.content.Context;

import com.begentgroup.xmlparser.XMLParser;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.MySSLSocketFactory;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

public class NetworkManager {
	private static NetworkManager instance;
	public static NetworkManager getInstance() {
		if (instance == null) {
			instance = new NetworkManager();
		}
		return instance;
	}
	
	
	AsyncHttpClient client;
	private NetworkManager() {
		
		try {
			KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
			trustStore.load(null, null);
			MySSLSocketFactory socketFactory = new MySSLSocketFactory(trustStore);
			socketFactory.setHostnameVerifier(MySSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			client = new AsyncHttpClient();
			client.setSSLSocketFactory(socketFactory);			
			client.setCookieStore(new PersistentCookieStore(MyApplication.getContext()));
			client.setTimeout(30000);
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (UnrecoverableKeyException e) {
			e.printStackTrace();
		}
	}
	
	public interface OnResultListener<T> {
		public void onSuccess(T result);
		public void onFail(int code);
	}
	
	public static final String URL_NAVER_MOVIE = "http://openapi.naver.com/search";
	private static final String API_KEY = "55f1e342c5bce1cac340ebb6032c7d9a";
	public void getNaverMovie(Context context, String query, int start, int display, final OnResultListener<NaverMovies> listener) {
		RequestParams params = new RequestParams();
		params.put("key", API_KEY);
		params.put("query", query);
		params.put("display", ""+display);
		params.put("start", ""+start);
		params.put("target", "movie");
		client.get(context, URL_NAVER_MOVIE, params, new AsyncHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				XMLParser parser = new XMLParser();
				NaverMovies movies = parser.fromXml(new ByteArrayInputStream(responseBody), "channel", NaverMovies.class);
				listener.onSuccess(movies);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers,
					byte[] responseBody, Throwable error) {
				listener.onFail(statusCode);
			}
		});
	}
	
}
