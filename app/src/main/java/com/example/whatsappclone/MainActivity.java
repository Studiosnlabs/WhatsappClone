package com.example.whatsappclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class MainActivity extends AppCompatActivity {

Boolean loginModeActive = false;

public void redirectIfLoggedIn(){

    if (ParseUser.getCurrentUser()!=null){
        Intent intent= new Intent(getApplicationContext(),UserListActivity.class);
        startActivity(intent);
    }
}

public void toggleLoginMode(View view){

    Button LoginSignup= findViewById(R.id.SignUpButt);
    TextView LoginToggle = findViewById(R.id.ToggleLoginModeTextView);

    if (loginModeActive){

        loginModeActive=false;
        LoginSignup.setText("Sign up");
        LoginToggle.setText("Or Login here");

    }else {
        loginModeActive=true;
        LoginSignup.setText("Log In");
        LoginToggle.setText("Or Sign up here");
    }




}





    public void signupLogin(View view){

        EditText UsernameEditText = findViewById(R.id.UserName);
        EditText passwordEditText = findViewById(R.id.UserPassword);

        if (loginModeActive){
            ParseUser.logInInBackground(UsernameEditText.getText().toString(), passwordEditText.getText().toString(), new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    if (e == null){
                        redirectIfLoggedIn();
                        Log.i("Login Info:","Login Successful");
                    }
                    else {
                        Log.i("Login Info","failed"+ e.toString());

                        String message= e.getMessage();
                        if (message.toLowerCase().contains("java")){
                            message=e.getMessage().substring(e.getMessage().indexOf(" "));
                        }

                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();

                    }
                }
            });

        } else {



            ParseUser user = new ParseUser();
            user.setUsername(UsernameEditText.getText().toString());
            user.setPassword(passwordEditText.getText().toString());

            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        Log.i("Sign up Feed:", "Signup Successful");
                        redirectIfLoggedIn();

                    } else {
                        Log.i("Sign up Feed:", "Signup failed" + e.toString());


                        String message= e.getMessage();
                        if (message.toLowerCase().contains("java")){
                            message=e.getMessage().substring(e.getMessage().indexOf(" "));
                        }

                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        redirectIfLoggedIn();




        ParseInstallation.getCurrentInstallation().saveInBackground();
        ParseAnalytics.trackAppOpenedInBackground(getIntent());





    }
}