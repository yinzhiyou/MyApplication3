package com.bwie.yinzhiyou.yinzhiyou20181221;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.yinzhiyou.yinzhiyou20181221.view.CumotView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mTextSearch;
    private ImageView mImageSeach;
    private CumotView mSearchOne;
    private CumotView mSearchTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {


        mTextSearch = (EditText) findViewById(R.id.text_search);
        mImageSeach = (ImageView) findViewById(R.id.image_seach);
        mImageSeach.setOnClickListener(this);
        mSearchOne = (CumotView) findViewById(R.id.search_one);
        mSearchTwo = (CumotView) findViewById(R.id.search_two);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.image_seach:
                TextView textView=new TextView(MainActivity.this);
                textView.setText(mTextSearch.getText());
                mSearchOne.addView(textView);

                Intent intent=new Intent(MainActivity.this,ShowActivity.class);

                startActivity(intent);
                break;
        }
    }
}
