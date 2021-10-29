package com.dazz.fb2insta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.dazz.fb2insta.DBHandler.FireBaseHandler;

public class postActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        FireBaseHandler handler=new FireBaseHandler();

        handler.pushQuote(getApplicationContext(),"Hii hello how do you do handsome");
    }
}