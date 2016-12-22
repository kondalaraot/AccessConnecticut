package com.accessconnecticut;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class RepsDetailsActivity extends AppCompatActivity {

    Legislators legislators;
    private NetworkImageViewRounded mNetworkImageView;
    private TextView mTvDemocraticDistrict;
    private TextView mTvLabelDistrictAddress;
    private LinearLayout mLlOfcAddress;
    private TextView mTvOfficeAddress;
    private TextView mTvPhoneOffice;
    private TextView mTvLabelOfficeAddress;
    private TextView mTvDistrictAddress;
    private TextView mTvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reps_details);
        legislators= (Legislators) getIntent().getSerializableExtra("LEGISLATOR_OBJ");
        setTitle(legislators.getFull_name());
        findViews();
        populateDetails();

    }

    private void populateDetails() {
         String capitolAddress = "";
         String capitolEmail = "";
         String disctrictAddress = "";

        mNetworkImageView.setDefaultImageResId(R.drawable.connecticut_seal_120x120);
        mNetworkImageView.setImageUrl(legislators.getPhoto_url(),NetworkManager.getInstance(this).getImageLoader());
        mTvDemocraticDistrict.setText(legislators.getParty()+" - "+"Representative - "+" District "+legislators.getDistrict());
        mTvPhoneOffice.setText(legislators.getOffice_phone());

        List<Office> offices = legislators.getOffices();
        for (Office office : offices) {
            if(office.getName().equalsIgnoreCase("Capitol Office")){
                capitolAddress = office.getAddress();
                capitolEmail = office.getEmail();
            }
            if(office.getName().equalsIgnoreCase("District Office")){
                disctrictAddress = office.getAddress();
            }

        }
        mTvEmail.setText(capitolEmail);
        mTvOfficeAddress.setText(capitolAddress);
        mTvDistrictAddress.setText(disctrictAddress);


    }

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2016-12-22 15:22:55 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        mNetworkImageView = (NetworkImageViewRounded)findViewById( R.id.network_image_view );
        mTvDemocraticDistrict = (TextView)findViewById( R.id.tv_democratic_district );
        mTvLabelDistrictAddress = (TextView)findViewById( R.id.tv_label_district_address );
        mLlOfcAddress = (LinearLayout)findViewById( R.id.ll_ofc_address );
        mTvOfficeAddress = (TextView)findViewById( R.id.tv_office_address );
        mTvPhoneOffice = (TextView)findViewById( R.id.tv_phone_office );
        mTvLabelOfficeAddress = (TextView)findViewById( R.id.tv_label_office_address );
        mTvDistrictAddress = (TextView)findViewById( R.id.tv_district_address );
        mTvEmail = (TextView)findViewById( R.id.tv_email );
    }
}
