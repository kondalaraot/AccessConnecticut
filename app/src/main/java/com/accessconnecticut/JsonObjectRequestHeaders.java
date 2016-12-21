package com.accessconnecticut;

import com.android.volley.AuthFailureError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class JsonObjectRequestHeaders extends JsonObjectRequest {

	String mRequestBody;
	private static final String DEFAULT_PARAMS_ENCODING = "UTF-8";
	
	public JsonObjectRequestHeaders(int method, String url,
									JSONObject jsonRequest, Listener listener,
									ErrorListener errorListener) {
		super(method, url, jsonRequest, listener, errorListener);
//		mRequestBody = jsonRequest.toString();
	}

	@Override
	public Map<String, String> getHeaders() throws AuthFailureError {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/x-www-form-urlencoded; charset=" + DEFAULT_PARAMS_ENCODING);
//		headers.put("Accept", "application/json");
		return super.getHeaders();

	}

	@Override
	protected Map<String, String> getParams() throws AuthFailureError {
		/*Map<String,String> params = new HashMap<String, String>();
		params.put("Name", "NORMAN YU-NENG KUO");
		params.put("AccessKey", "94074448-d04b-4660-89d5-894be2d799b0");*/
		return super.getParams();
	}

	@Override
	public String getBodyContentType() {
//		return super.getBodyContentType();
		return "application/x-www-form-urlencoded; charset=" + DEFAULT_PARAMS_ENCODING;
	}

	//In your extended request class
	@Override
	protected VolleyError parseNetworkError(VolleyError volleyError){
	        if(volleyError.networkResponse != null && volleyError.networkResponse.data != null){
	                VolleyError error = new VolleyError(new String(volleyError.networkResponse.data));
	                volleyError = error;
	            }

	        return volleyError;
	    }
	

}
