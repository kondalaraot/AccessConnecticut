package com.accessconnecticut;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class RepsListActivity extends BaseAppCompatActivity {

    ArrayList<Legislators> legislatorses;
    ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reps_list);
        mListView = (ListView) findViewById(R.id.list_view);

        legislatorses = (ArrayList<Legislators>) getIntent().getSerializableExtra("LEGISLATORS_LIST_OBJ");
        if(legislatorses!=null){
            mListView.setAdapter(new RepsAdapter(this,legislatorses));
        }

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Legislators legislators = legislatorses.get(position);
                Intent intent = new Intent(RepsListActivity.this,RepsDetailsActivity.class);
                intent.putExtra("LEGISLATOR_OBJ",legislators);
                startActivity(intent);
            }
        });


    }
}
