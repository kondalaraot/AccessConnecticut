package com.accessconnecticut;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ContentActivity extends AppCompatActivity {

    TextView textView;
    String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        content = getIntent().getStringExtra("CONTENT_TYPE");

        textView = (TextView) findViewById(R.id.tv_content);

        if (content.equals(Constants.BILL)) {
            setTitle("The Bill");
            loadContent(getResources().getString(R.string.the_bill).toString());
        } else if (content.equals(Constants.FACTS)) {
            setTitle("Facts");
            loadContent(getResources().getString(R.string.facts).toString());
        } else if (content.equals(Constants.POSITION_STATEMENT)) {
            setTitle("Position Statement");
            loadContent(getResources().getString(R.string.position_statement).toString());

        }

    }

    private void loadContent(String content) {

        textView.setText(FileUtil.fromHtml(content));

    }
}
