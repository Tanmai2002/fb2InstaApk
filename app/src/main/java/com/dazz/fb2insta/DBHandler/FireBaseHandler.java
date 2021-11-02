package com.dazz.fb2insta.DBHandler;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class FireBaseHandler {
    FirebaseDatabase db;
    DatabaseReference ref;
    public static FirebaseUser curUser;
    public FirebaseAuth auth;
    public FireBaseHandler(){
        db=FirebaseDatabase.getInstance();
        ref=db.getReference();
        auth=FirebaseAuth.getInstance();
        curUser=auth.getCurrentUser();



    }

    public void pushQuote(Context c,String quote){
        DatabaseReference r=ref.child("quotes").push();
        HashMap<String,String> keyPair=new HashMap<String,String>();
        keyPair.put("quote",quote);
        keyPair.put("published","NO");
                r.setValue(keyPair).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(!task.isSuccessful()){
                    Toast.makeText(c,task.getException().toString(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
