package com.example.grupsmsuygulamasi;
import android.content.Context;
import android.widget.Toast;

public class Message {
    public static Context context;
    public static void mesaj(String msj){
        Toast.makeText(context,msj,Toast.LENGTH_SHORT).show();
    }
}
