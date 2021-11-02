package com.dazz.fb2insta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dazz.fb2insta.DBHandler.FireBaseHandler;

public class postActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        FireBaseHandler handler=new FireBaseHandler();
        Button send=findViewById(R.id.sendQuoteButton);
        EditText quoteText=findViewById(R.id.quoteEditText);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.pushQuote(getApplicationContext(),quoteText.getText().toString());
                quoteText.setText("");
            }
        });
//
//        handler.pushQuote(getApplicationContext(),"Hii hello how do you do handsome");
    }
}