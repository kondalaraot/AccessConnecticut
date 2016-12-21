package com.accessconnecticut;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * Created by KTirumalsetty on 10/31/2016.
 */

public class NetworkManager
{
    private static final String TAG = "NetworkManager";
    private static final String DEFAULT_PARAMS_ENCODING = "UTF-8";
    private static final int MY_SOCKET_TIMEOUT_MS = 5000;
    private static NetworkManager mInstance = null;
    private Context mContext;

    //for Volley API
    public RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    private NetworkManager(Context context)
    {
        mContext=context;
        mRequestQueue = Volley.newRequestQueue(context.getApplicationContext());
        //other stuf if you need
        mImageLoader = new ImageLoader(mRequestQueue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap>
                            cache = new LruCache<String, Bitmap>(20);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });
    }

    public static synchronized NetworkManager getInstance(Context context)
    {
        if (null == mInstance)
            mInstance = new NetworkManager(context);
        return mInstance;
    }

    //this is so you don't need to pass context each time
    public static synchronized NetworkManager getInstance()
    {
        if (null == mInstance)
        {
            throw new IllegalStateException(NetworkManager.class.getSimpleName() +
                    " is not initialized, call getInstance(...) first");
        }
        return mInstance;
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            Cache cache = new DiskBasedCache(mContext.getCacheDir(), 10 * 1024 * 1024);
            Network network = new BasicNetwork(new HurlStack());
            mRequestQueue = new RequestQueue(cache, network);
            // Don't forget to start the volley request queue
            mRequestQueue.start();
        }
        return mRequestQueue;
    }



    public void postJsonRequest(String urlSuffix, JSONObject requestBody, final VolleyCallbackListener<Object> listener, final int reqCode) {

        String url = Constants.HOST_URL + urlSuffix;
        if (Constants.DEBUG) Log.d(TAG, "Rq Url " + url);
        if (Constants.DEBUG) Log.d(TAG, "Req body data " + requestBody.toString());
        JsonObjectRequestHeaders request = new JsonObjectRequestHeaders(Request.Method.POST, url, requestBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (Constants.DEBUG)
                            Log.d(TAG + ": ", "somePostRequest Response : " + response.toString());
                        if (null != response.toString())
                            listener.getResult(reqCode, response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String errorMessage ="";
                        if (Constants.DEBUG)
                            Log.e(TAG + ": ", "somePostRequest Response : " + error.toString());

                        if (null != error.networkResponse) {
                            if (Constants.DEBUG)
                                Log.e(TAG + ": ", "Error Response code: " + error.networkResponse.statusCode);

//                            listener.getErrorResult(null);
                        }
                        if (error.getClass().equals(TimeoutError.class)) {
                            // Show timeout error message
                            errorMessage = "Oops. Timeout error!";
                            /*Toast.makeText(mContext,
                                    "Oops. Timeout error!",
                                    Toast.LENGTH_LONG).show();*/
                        }else{
                            errorMessage = error.getMessage();
                        }
                        listener.getErrorResult(errorMessage);
                    }
                });
        mRequestQueue.add(request);
        request.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    public void getJsonRequest(String url, JSONObject requestBody, final VolleyCallbackListener<Object> listener, final int reqCode)
    {

        if (Constants.DEBUG) Log.d(TAG,"Req url "+url);

        JsonObjectRequestHeaders request = new JsonObjectRequestHeaders(Request.Method.GET,url, requestBody,
                new Response.Listener<JSONArray>()
                {
                    @Override
                    public void onResponse(JSONArray response)
                    {
                        if (Constants.DEBUG) Log.d(TAG + ": ", "someGetRequest Response : " + response.toString());
                        if(null != response.toString())
                            listener.getResult(reqCode,response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        if (Constants.DEBUG) Log.e(TAG + ": ", "somePostRequest Response : " + error.toString());

                        if (null != error.networkResponse)
                        {
                            if (Constants.DEBUG) Log.e(TAG + ": ", "Error Response code: " + error.networkResponse.statusCode);

//                            listener.getErrorResult(null);
                        }
                        listener.getErrorResult(null);
                    }
                });
        mRequestQueue.add(request);
        request.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }
    public void postStringRequest(String urlSuffix, JSONObject postParams, final VolleyCallbackListener<Object> listener, final int reqCode) {

        String url = Constants.HOST_URL + urlSuffix;
        if (Constants.DEBUG) Log.d(TAG, "mRequestBody--->> " + postParams.toString());

        final String mRequestBody = (postParams == null) ? null : postParams.toString();
//        String url = "http://physicianroundswcfservice.azurewebsites.net/Service1.svc/InsertPhysicianData";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("SUCCESS", response);
                if (Constants.DEBUG)
                    Log.d(TAG + ": ", "somePostRequest Response : " + response.toString());
                if (null != response.toString())
                    listener.getResult(reqCode,response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VOLLEY ERROR--", error.toString());
                if (Constants.DEBUG)
                    Log.e(TAG + ": ", "somePostRequest Response : " + error.toString());

                if (null != error.networkResponse) {
                    if (Constants.DEBUG)
                        Log.e(TAG + ": ", "Error Response code: " + error.networkResponse.statusCode);

//                            listener.getErrorResult(null);
                }
                listener.getErrorResult(null);
            }
        }) {
           /* @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }*/

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", mRequestBody, "utf-8");
                    return null;
                }
            }

            /*@Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String responseString = "";
                if (response != null) {
                    responseString = String.valueOf(response.statusCode);
                    Log.e("VOLLEY RESP--", response.toString());

                    // can get more details such as response.headers
                }
                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
            }*/
        };
        mRequestQueue.add(request);

    }


}
