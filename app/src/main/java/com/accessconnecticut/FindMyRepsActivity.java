package com.accessconnecticut;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.model.LatLng;
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
    private static final int REQUEST_CODE_AUTOCOMPLETE = 1;


    private double mLatitude;
    private double mLongitude;
    EditText edSearch;
    Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_my_reps);
        edSearch = (EditText) findViewById(R.id.ed_search);
        btnSearch = (Button) findViewById(R.id.btn_search);
        TextView tvSearch = (TextView) findViewById(R.id.tv_label_search);
        tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAutocompleteActivity();

            }
        });


        edSearch.setInputType(InputType.TYPE_NULL);

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

        edSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAutocompleteActivity();
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                getLocationFromZip();
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
                mLatitude = address.getLatitude();
                mLongitude = address.getLongitude();
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

    private void openAutocompleteActivity() {
        try {
            // The autocomplete activity requires Google Play Services to be available. The intent
            // builder checks this and throws an exception if it is not the case.
            Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                    .build(this);
            startActivityForResult(intent, REQUEST_CODE_AUTOCOMPLETE);
        } catch (GooglePlayServicesRepairableException e) {
            // Indicates that Google Play Services is either not installed or not up to date. Prompt
            // the user to correct the issue.
            GoogleApiAvailability.getInstance().getErrorDialog(this, e.getConnectionStatusCode(),
                    0 /* requestCode */).show();
        } catch (GooglePlayServicesNotAvailableException e) {
            // Indicates that Google Play Services is not available and the problem is not easily
            // resolvable.
            String message = "Google Play Services is not available: " +
                    GoogleApiAvailability.getInstance().getErrorString(e.errorCode);

            Log.e(TAG, message);
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK)
            return;

        switch (requestCode) {

            case REQUEST_CODE_AUTOCOMPLETE:
                // Get the user's selected place from the Intent.
                Place place = PlaceAutocomplete.getPlace(this, data);
                Log.i(TAG, "Place Selected: " + place.getName());

                // Format the place's details and display them in the TextView.
                edSearch.setText(place.getName());
                       /* mLocation.setText(formatPlaceDetails(getResources(), place.getName(),
                                place.getId(), place.getAddress(), place.getPhoneNumber(),
                                place.getWebsiteUri()).toString());*/
                LatLng latLng = place.getLatLng();
                mLatitude = latLng.latitude;
                mLongitude = latLng.longitude;
                Log.d(TAG,"lat,long are "+mLatitude+"  "+mLongitude);
                // Display attributions if required.
                CharSequence attributions = place.getAttributions();
                if (!TextUtils.isEmpty(attributions)) {
//                            mLocation.setText(Html.fromHtml(attributions.toString()));
                    Log.d(TAG,"attributions "+attributions.toString());
                } else {
//                            mLocation.setText("");
                    Log.d(TAG,"attributions empty");
                }
                if(NetworkUtil.isNetworkAvailable(this)){
                    showProgress("Please wait...");
                    getRepsVolley();
                }else{
//            showAlert("Please check your network connection..");
                    showAlert("Please check your network connection..");
                }
                break;
            // Indicates that the activity closed before a selection was made. For example if
            // the user pressed the back button.
            case PlaceAutocomplete.RESULT_ERROR:
                Status status = PlaceAutocomplete.getStatus(this, data);
                Log.e(TAG, "Error: Status = " + status.toString());
                break;

        }


    }

    private void getRepsVolley(){
        Uri builtUri = Uri.parse(Constants.HOST_URL+Constants.API_GET_LEGISLATORS)
                .buildUpon()
                .appendQueryParameter("lat", String.valueOf(mLatitude))
                .appendQueryParameter("long", String.valueOf(mLongitude))
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
