package com.moshx.twitterfeeds.network;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import android.app.Dialog;
import android.content.Context;
import android.os.Looper;
import android.util.Log;

import com.androidquery.AQuery;
import com.androidquery.auth.BasicHandle;
import com.androidquery.callback.AbstractAjaxCallback;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.androidquery.util.AQUtility;
import com.google.gson.Gson;
import com.moshx.twitterfeeds.utils.Constants;

public class AbstractRequest<T> implements Constants {

	public static boolean DEBUG = true;

	public enum RequestType {
		GET, POST, PUT, DELETE;
	}

	protected static final long ONE_DAY = 1000 * 60 * 60 * 24;
	protected static final long ONE_WEEK = 1000 * 60 * 60 * 24 * 7;
	protected static final long ONE_MONTH = 1000 * 60 * 60 * 24 * 30;

	private String url;
	private Map<String, Object> params = new HashMap<String, Object>();
	private Context context;
	private Class<?> classType;
	private String tag = getClass().getSimpleName();
	private long expireTime = -1;
	@SuppressWarnings("rawtypes")
	private Class mRequestClass = String.class;

	private Map<String, String> headers = new HashMap<String, String>();

	public AbstractRequest(Context context, Class<?> classType) {
		this.context = context.getApplicationContext();
		this.classType = classType;
	}

	public AbstractRequest<T> addParameter(String key, Object value) {
		this.params.put(key, value);
		return this;
	}

	public AbstractRequest<T> addParameters(Map<String, Object> parms) {

		if (parms != null && parms.size() > 0) {
			for (String key : parms.keySet()) {
				addParameter(key, parms.get(key));
			}
		}

		return this;
	}

	/**
	 * add header to request
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public AbstractRequest<T> addHeader(String key, String value) {

		this.headers.put(key, value);

		return this;
	}

	/**
	 * 
	 * @return headers map
	 */
	public Map<String, String> getHeaders() {

		return headers;
	}

	/**
	 * 
	 * @param token
	 */
	public final void addAuthTokenAsHeader(String token) {

		addHeader("Authorization", "Bearer" + token);
	}

	/**
	 * 
	 */
	public final void addJsonHeaders() {

		addHeader("Accept", "application/json");
		addHeader("Content-type", "application/json");
	}

	/**
	 * Add headers if exist to request
	 * 
	 * 
	 * 
	 */
	private void addHeaderToRequest() {

		if (headers.isEmpty()) {

			return;
		}

		for (String key : headers.keySet()) {

			callback.header(key, headers.get(key));
		}
	}

	public Map<String, Object> getParameters() {
		return params;
	}

	public String getUrl() {
		return url;
	}

	public AbstractRequest<T> setUrl(String url) {
		this.url = url;
		return this;
	}

	public Context getContext() {
		return context;
	}

	public AbstractRequest<T> setContext(Context context) {
		this.context = context;
		return this;
	}

	public void post() {
		post(null);
	}

	@SuppressWarnings("unchecked")
	public void post(Dialog d) {
		AQuery aQuery = new AQuery(context);

		aQuery.auth(new BasicHandle("demo", "spring"));

		internalOnPreExecute();

		addHeaderToRequest();
		if (d != null) {
			aQuery.progress(d);
		}
		aQuery.ajax(url, params, mRequestClass, callback);
		AbstractAjaxCallback.setGZip(false);
	}

	// *****************************************************************

	/**
	 * post data to server with json body.
	 * 
	 * <br>
	 * NOTE: Don't add Content-Type / Accept: application/json, by default they
	 * are provided from AQuery
	 */
	@SuppressWarnings("unchecked")
	public void postJson() {

		AQuery aQuery = new AQuery(context);

		internalOnPreExecute();

		addHeaderToRequest();

		JSONObject body = new JSONObject(getParameters());

		Log.d("postJson", "params: " + body);

		aQuery.post(url, body, mRequestClass, callback);

		Log.i(tag, url);
	}

	// *****************************************************************

	@SuppressWarnings("rawtypes")
	public void setRequestClass(Class c) {
		mRequestClass = c;
	}

	@SuppressWarnings("unchecked")
	public void get() {
		String url = getGetQuery();
		AQuery aQuery = new AQuery(context);

		internalOnPreExecute();

		addHeaderToRequest();

		aQuery.auth(new BasicHandle("demo", "spring"));

		if (expireTime > -1) {
			aQuery.ajax(url, mRequestClass, expireTime, callback);
		} else {
			aQuery.ajax(url, mRequestClass, callback);
		}

		Log.i(tag, url);
	}

	@SuppressWarnings("unchecked")
	public void put() {

		AQuery aQuery = new AQuery(context);

		internalOnPreExecute();

		addHeaderToRequest();

		StringEntity entity;

		try {

			String jsonString = new JSONObject(params).toString();

			jsonString = jsonString.replace("\\", "");
			Log.e("hisham", "put(): params:" + jsonString);

			entity = new StringEntity(jsonString, HTTP.UTF_8);

			aQuery.put(url, "application/json", entity, mRequestClass, callback);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			Log.e("Aquery PUT", "error: ", e);
		}

		Log.i(tag, url);
	}

	@SuppressWarnings("unchecked")
	public void delete() {

		AQuery aQuery = new AQuery(context);

		internalOnPreExecute();

		addHeaderToRequest();

		// convert JSON to String and remove backslash '\'
		String jsonString = new JSONObject(params).toString();
		jsonString = jsonString.replace("\\", "");

		Log.e("hisham", "delete(): params: " + jsonString);

		aQuery.delete(url, mRequestClass, callback);

		Log.i(tag, url);
	}

	private String getGetQuery() {

		StringBuilder queryBuilder = new StringBuilder(url);

		if (params != null && params.size() > 0) {
			queryBuilder.append("?");
			for (String key : params.keySet()) {
				try {

					Object value = params.get(key);
					if (value == null) {
						continue;
					}

					String val = URLEncoder.encode(value.toString(), "UTF-8");
					queryBuilder.append(key).append("=").append(val)
							.append("&");

				} catch (Exception e) {
				}
			}

			queryBuilder.deleteCharAt(queryBuilder.length() - 1); // remove the
																	// last &
		}

		return queryBuilder.toString();
	}

	public void onCallBack(String url, T result, AjaxStatus status) {
	}

	public long getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(long expireTime) {
		this.expireTime = expireTime;
	}

	private void publishResult(String url, T result, AjaxStatus status) {
		onCallBack(url, result, status);
	}

	protected transient AjaxCallback<String> callback = new AjaxCallback<String>() {

		@SuppressWarnings("unchecked")
		@Override
		public void callback(String url, String result, AjaxStatus status) {

			if (DEBUG) {
				Log.d(AbstractRequest.class.getSimpleName(),
						"Result:" + result + " code:" + status.getCode()
								+ " error:" + status.getError());

			}

			if (result != null) {
				if (classType == String.class) {
					publishResult(url, (T) result, status);
					return;
				}

				Gson gson = new Gson();
				// Log.i(tag, "string result " + result);
				try {
					T response = (T) gson
							.fromJson(result.toString(), classType);
					publishResult(url, response, status);
				} catch (Exception e) {
					e.printStackTrace();
					publishResult(url, null, status);
				}

			} else {
				publishResult(url, null, status);
			}
		}
	};

	protected void internalOnPreExecute() {

		if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
			onPreExecute();
		} else {
			AQUtility.post(new Runnable() {

				@Override
				public void run() {
					onPreExecute();
				}
			});
		}

	}

	public void onPreExecute() {
	};
}
