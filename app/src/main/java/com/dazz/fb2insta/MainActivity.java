package com.dazz.fb2insta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dazz.fb2insta.DBHandler.FireBaseHandler;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private String email="",pass="";
    public static final String RC_SIGN_IN="com.dazz.fb2insta.signInToken";
    FireBaseHandler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler=new FireBaseHandler();
//        handler.pushQuote("hii " +Math.random());
        if(FireBaseHandler.curUser!=null){
            signIn();
        }
        Button SignINBut=findViewById(R.id.sign_in_Button);
        SignINBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText e=findViewById(R.id.emailOfUser);
                EditText p=findViewById(R.id.passwordOfUser);
                email=e.getText().toString();
                pass=p.getText().toString();
                signIn();
            }
        });
        Button RegisterButton=findViewById(R.id.Register_Button);
        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText e=findViewById(R.id.emailOfUser);
                EditText p=findViewById(R.id.passwordOfUser);
                email=e.getText().toString();
                pass=p.getText().toString();
                Register(email,pass);
            }
        });
    }
    public void Register(String Email,String Passw){
    handler.auth.createUserWithEmailAndPassword(Email,Passw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if(!task.isSuccessful()){
                Toast.makeText(getApplicationContext(),"Error:"+task.getException().toString(),Toast.LENGTH_LONG).show();
                return;
            }
            FireBaseHandler.curUser =task.getResult().getUser();
            signIn();
        }
    });
    }
    public void signIn(){

        if(FireBaseHandler.curUser ==null) {
            handler.auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"Error:"+task.getException().toString(),Toast.LENGTH_LONG).show();
                        return;
                    }
                    FireBaseHandler.curUser =task.getResult().getUser();
                    Intent i=new Intent(getApplicationContext(),postActivity.class);
                    startActivity(i);
                    finish();

                }
            });
        }else{
            Intent i=new Intent(getApplicationContext(),postActivity.class);
            startActivity(i);
            finish();
        }
    }



}