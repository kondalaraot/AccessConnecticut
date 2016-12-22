package com.accessconnecticut;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindMyRepsActivity extends BaseAppCompatActivity {

    private static final String TAG = "FindMyRepsActivity";

    private double latitude;
    private double longitude;
    EditText edSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_my_reps);
        edSearch = (EditText) findViewById(R.id.ed_search);

        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    getLocationFromZip();
                    return true;
                }
                return false;
            }
        });

    }

    private void getLocationFromZip(){
        final Geocoder geocoder = new Geocoder(this);
        final String zipCode = edSearch.getText().toString();
//        final String zipCode = "06112";
        try {
            List<Address> addresses = geocoder.getFromLocationName(zipCode, 10);
            if (addresses != null && !addresses.isEmpty()) {
                Address address = addresses.get(0);
                Log.d(TAG,"getCountryCode "+address.getCountryCode());
                Log.d(TAG,"getCountryCode "+address.getCountryName());
                Log.d(TAG,"getLatitude "+address.getLatitude());
                Log.d(TAG,"getLongitude "+address.getLongitude());
                if(address.getCountryCode().equals("US")){

                }
                latitude = address.getLatitude();
                longitude = address.getLongitude();
                // Use the address as needed
                String message = String.format("Latitude: %f, Longitude: %f",
                        address.getLatitude(), address.getLongitude());
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();

                if(NetworkUtil.isNetworkAvailable(this)){
                    showProgress("Please wait...");
                    getRepsVolley();
                }else{
//            showAlert("Please check your network connection..");
                    showAlert("Please check your network connection..");
                }

            } else {
                // Display appropriate message when Geocoder services are not available
                Toast.makeText(this, "Unable to geocode zipcode", Toast.LENGTH_LONG).show();
            }
        } catch (IOException e) {
            // handle exception
            e.printStackTrace();
        }
    }

    private void getRepsVolley(){
        Uri builtUri = Uri.parse(Constants.HOST_URL+Constants.API_GET_LEGISLATORS)
                .buildUpon()
                .appendQueryParameter("lat", String.valueOf(latitude))
                .appendQueryParameter("long", String.valueOf(longitude))
                .build();
        String getRepsURL = builtUri.toString();
//        url = "http://httpbin.org/post";
        StringRequest getRequest = new StringRequest(Request.Method.GET, getRepsURL,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                        dismissProgress();
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            parseRespData(jsonArray);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("name", "Alif");
                params.put("domain", "http://itsalif.info");

                return super.getParams();
            }
        };
        RequestQueue queue=NetworkManager.getInstance(this).getRequestQueue();
        queue.add(getRequest);
//        NetworkManager.getInstance(this).getJsonRequest(getRepsURL,null,this,0);

    }

    private void parseRespData(JSONArray response ) {
        try {
//            JSONArray response = (JSONArray) object;
            dismissProgress();
//            Log.d(TAG,"response.toString()" + response.toString());
//            JSONArray patientsArray = response.getJSONArray("Content");

            if(response !=null && response.length()>0){
                Gson gson = new Gson();
                Type listType = new TypeToken<List<Legislators>>(){}.getType();
                ArrayList<Legislators> legislatorses = gson.fromJson(response.toString(), listType);
                Intent intent = new Intent(this,RepsListActivity.class);
                intent.putExtra("LEGISLATORS_LIST_OBJ",legislatorses);
                startActivity(intent);

            }else{
                showAlert("No records found..");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
