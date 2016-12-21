package com.accessconnecticut;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends BaseAppCompatActivity implements View.OnClickListener{

    private ImageView imageViewFindMyReps;
    private TextView textViewFindMyReps;
    private ImageView imageViewTheBill;
    private TextView textView;
    private ImageView imageViewFacts;
    private ImageView imageViewPosState;
    private ImageView imageViewfaceBook;
    private ImageView imageViewTwitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
//        tv.setText(readTxt("position_statement"));
//          tv.setText(FileUtil.fromHtml(getResources().getString(R.string.position_statement).toString()));

    }


    private void findViews() {
        imageViewFindMyReps = (ImageView)findViewById( R.id.imageViewFindMyReps );
        textViewFindMyReps = (TextView)findViewById( R.id.textViewFindMyReps );
        imageViewTheBill = (ImageView)findViewById( R.id.imageViewTheBill );
        textView = (TextView)findViewById( R.id.textView );
        imageViewFacts = (ImageView)findViewById( R.id.imageViewFacts );
        imageViewPosState = (ImageView)findViewById( R.id.imageViewPosState );
        imageViewfaceBook = (ImageView)findViewById( R.id.imageViewfaceBook );
        imageViewTwitter = (ImageView)findViewById( R.id.imageViewTwitter );

        imageViewFindMyReps.setOnClickListener(this);
        imageViewTheBill.setOnClickListener(this);
        imageViewFacts.setOnClickListener(this);
        imageViewPosState.setOnClickListener(this);

        imageViewfaceBook.setOnClickListener(this);
        imageViewTwitter.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.imageViewFindMyReps){
            startActivity(new Intent(this,FindMyRepsActivity.class));
        }else if (id == R.id.imageViewFacts){

        }else if (id == R.id.imageViewTheBill){

        }else if (id == R.id.imageViewPosState){

        }else if (id == R.id.imageViewfaceBook){

        }else if (id == R.id.imageViewTwitter){

        }

    }
}
