package com.example.grupsmsuygulamasi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class KayitEkrani extends AppCompatActivity {
    FirebaseAuth mAuth;
    Button uyeOlButonu;
    EditText EditTextTextEmailAddress;
    EditText EditTextTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit_ekrani);
        uyeOlButonu=(Button) findViewById(R.id.uyeOlButonu);
        EditTextTextEmailAddress=(EditText) findViewById(R.id.editTextTextEmailAddress);
        EditTextTextPassword=(EditText) findViewById((R.id.editTextTextPassword));
        mAuth=mAuth.getInstance();


        uyeOlButonu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uyeOlClick();
            }
        });

        findViewById(R.id.girisYapButonu).setOnClickListener(view -> {
            startActivity(new Intent(KayitEkrani.this,girisYapEkrani.class));
        });


    }


    private void uyeOlClick() {
        String eMail=EditTextTextEmailAddress.getText().toString();
        String password=EditTextTextPassword.getText().toString();
        if (eMail.isEmpty()){
            Message.mesaj("Bu alan boş bırakılamaz. Lütfen mail adresinizi girin. !!!");
        }
        if (password.isEmpty()){
            Message.mesaj("Bu alan boş bırakılamaz. Lütfen şifrenizi girin. !!!");
        }
        mAuth.createUserWithEmailAndPassword(eMail, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Message.mesaj("ÜYELİK BAŞARILI...");
                            startActivity(new Intent(KayitEkrani.this,MainActivity.class));
                        }
                        else {
                            Message.mesaj("E posta adresi kullanılıyor. Lütfen yeni bir e posta adresi seçin.");
                        }
                    }
                });

    }
}