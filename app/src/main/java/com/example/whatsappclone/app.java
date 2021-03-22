package com.example.whatsappclone;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class app extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build()
        );
/*
        ParseObject object = new ParseObject("TestObject");
        object.put("myNumber", "123");
        object.put("myString", "Kerl");


      object.saveInBackground(new SaveCallback() {
          @Override
          public void done(ParseException e) {
              if (e==null){
                  Log.i("Parse Result","Succesful");

              }
              else {
                  Log.i("Parse Result","Failed"+e.toString());
              }
          }
      });*/


        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(defaultACL,true);
    }
}
