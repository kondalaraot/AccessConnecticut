package com.accessconnecticut;

import android.app.KeyguardManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.security.KeyStore;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

public class SplashScreenActivity extends AppCompatActivity {

    private static final int FINGERPRINT_PERMISSION_REQUEST_CODE = 111;
    /**
     * Duration of wait
     **/
    private final int SPLASH_DISPLAY_LENGTH = 2000;
    private static final String KEY_NAME = "example_key";

    private FingerprintManager fingerprintManager;
    private KeyguardManager keyguardManager;
    private KeyStore keyStore;
    private KeyGenerator keyGenerator;
    private Cipher cipher;
    private FingerprintManager.CryptoObject cryptoObject;

    Animation zoomin, zoomout; //declared as public

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        final ImageView imageView = (ImageView) findViewById(R.id.imageView);
        zoomin = AnimationUtils.loadAnimation(this, R.anim.zoomin);
//        zoomout = AnimationUtils.loadAnimation(this, R.anim.zoomout);

        imageView.setAnimation(zoomin);

//        imageView.setAnimation(zoomin);

        /*zoomin.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animation arg0) {
                imageView.startAnimation(zoomout);

            }
        });*/

        /*zoomout.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animation arg0) {
//                imageView.startAnimation(zoomin);

            }
        });*/

     /*   new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                *//* Create an Intent that will start the Menu-Activity. *//*

                Intent mainIntent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish();


            }
        }, SPLASH_DISPLAY_LENGTH);*/
    }


}
