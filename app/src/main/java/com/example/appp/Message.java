package com.example.appp;

import android.content.Context;
import android.widget.Toast;


public class Message {
    //responsible for displaying toasts to the user
    public static void message(Context context , String message)
    {
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }
}
