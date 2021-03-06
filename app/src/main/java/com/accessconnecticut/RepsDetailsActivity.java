package com.accessconnecticut;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
    private TextView mTvEmailDistrict;
    private TextView mTvPhoneDistrict;
    private TextView mTvLabelSearch;

    String mEmailCC = "info@accessconnecticut.org";
    String mEmailSubject = "Please support Adoptee Rights Bill!";

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
         String capitolPhone = "";
         String disctrictAddress = "";
         String disctrictEmail = "";
         String disctrictPhone = "";

        mNetworkImageView.setDefaultImageResId(R.drawable.default_user);
        mNetworkImageView.setImageUrl(legislators.getPhoto_url(),NetworkManager.getInstance(this).getImageLoader());
        mTvDemocraticDistrict.setText(legislators.getParty()+" - "+"Representative - "+" District "+legislators.getDistrict());

        List<Office> offices = legislators.getOffices();
        for (Office office : offices) {
            if(office.getName().equalsIgnoreCase("Capitol Office")){
                capitolAddress = office.getAddress();
                capitolEmail = office.getEmail();
                capitolPhone = office.getPhone();
            }
            if(office.getName().equalsIgnoreCase("District Office")){
                disctrictAddress = office.getAddress();
                disctrictEmail = office.getEmail();
                disctrictPhone = office.getPhone();
            }

        }
        mTvEmail.setText(capitolEmail);
        final String finalCapitolEmail = capitolEmail;
        mTvEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String body = getResources().getString(R.string.email_body);
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/html");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[] { finalCapitolEmail });
                intent.putExtra(Intent.EXTRA_SUBJECT, mEmailSubject);
                intent.putExtra(Intent.EXTRA_TEXT, body);
                intent.putExtra(Intent.EXTRA_CC, new String[] { mEmailCC });
                startActivity(Intent.createChooser(intent, "Send Email"));
            }
        });

        mTvOfficeAddress.setText(capitolAddress);
        mTvPhoneOffice.setText(capitolPhone);

        mTvDistrictAddress.setText(disctrictAddress);
        mTvEmailDistrict.setText(disctrictEmail);
        final String finalDisctrictEmail = disctrictEmail;
        mTvEmailDistrict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String body = getResources().getString(R.string.email_body);
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/html");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[] { finalDisctrictEmail });
                intent.putExtra(Intent.EXTRA_SUBJECT, mEmailSubject);
                intent.putExtra(Intent.EXTRA_TEXT, body);
                intent.putExtra(Intent.EXTRA_CC, new String[] { mEmailCC });

                startActivity(Intent.createChooser(intent, "Send Email"));
            }
        });
        mTvPhoneDistrict.setText(disctrictPhone);


    }

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2016-12-22 15:22:55 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        mNetworkImageView = (NetworkImageViewRounded)findViewById( R.id.network_image_view );
//        mTvLabelSearch = (TextView)findViewById( R.id.tv_label_search );
        mTvDemocraticDistrict = (TextView)findViewById( R.id.tv_democratic_district );
        mTvLabelDistrictAddress = (TextView)findViewById( R.id.tv_label_district_address );
        mLlOfcAddress = (LinearLayout)findViewById( R.id.ll_ofc_address );
        mTvOfficeAddress = (TextView)findViewById( R.id.tv_office_address );
        mTvPhoneOffice = (TextView)findViewById( R.id.tv_phone_office );
        mTvLabelOfficeAddress = (TextView)findViewById( R.id.tv_label_office_address );
        mTvDistrictAddress = (TextView)findViewById( R.id.tv_district_address );
        mTvEmail = (TextView)findViewById( R.id.tv_email );
        mTvPhoneDistrict = (TextView)findViewById( R.id.tv_phone_district );
        mTvEmailDistrict = (TextView)findViewById( R.id.tv_email_district );


    }
}
