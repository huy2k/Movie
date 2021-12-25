package com.example.appp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    ConnectionChecker checker = new ConnectionChecker();
    public static boolean paneView = false;
//    Button btnLi ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (checker.checkConnection(this)) {
            setContentView(R.layout.activity_main);
        } else {
            Message.message(this, "Please check your internet connection");
            setContentView(R.layout.no_connection);
        }
//        btnLi   = findViewById(R.id.btnLogins);
//        btnLi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent( MainActivity.this, Login_Activity.class);
//                startActivity(intent);
//            }
//        });
        if (findViewById(R.id.twoPane) == null) {
            paneView = false;
        } else {
            paneView = true;
        }


    }
}