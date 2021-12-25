package com.example.appp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_Activity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button login_btn,register_btn;
    private EditText email_text,pass_text;
    private TextView forget_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);

        mAuth = FirebaseAuth.getInstance();


        email_text =  findViewById(R.id.email_log_id);
        pass_text =  findViewById(R.id.pass_log_id);
        login_btn = findViewById(R.id.log_btn);
        register_btn = findViewById(R.id.register_id);
        forget_pass =  findViewById(R.id.forget_id);



        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Email = email_text.getText().toString();
                String passWord = pass_text.getText().toString();

                if (!TextUtils.isEmpty(Email) && !TextUtils.isEmpty(passWord)) {
                    Login(Email, passWord);
                } else {
                    email_text.setError("Required");
                    pass_text.setError("Required");
                }

            }
        });

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent( Login_Activity.this ,RegisterActivity.class);
//                intent.putExtra("type","register");
//                startActivity(intent);
                Intent intent = new Intent(Login_Activity.this, RegisterActivity.class);
                intent.putExtra("type","register");
                startActivity(intent);

            }
        });


        forget_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Login_Activity.this,Reset_password.class);
                startActivity(intent);
            }
        });
    }


    public void Login(String Email, String passWord) {
        mAuth.signInWithEmailAndPassword(Email, passWord)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(Login_Activity.this, "Done",
                                    Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(Login_Activity.this, MainActivity.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(Login_Activity.this, "sign Failed.",
                                    Toast.LENGTH_SHORT).show();
                            email_text.setError("Invalid");
                            pass_text.setError("Invalid");
                        }
                    }
                });
    }

}
