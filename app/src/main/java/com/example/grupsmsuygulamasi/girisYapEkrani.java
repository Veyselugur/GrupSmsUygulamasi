package com.example.grupsmsuygulamasi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class girisYapEkrani extends AppCompatActivity {
    FirebaseAuth mAuth;
    Button girisYapButonu;
    EditText EditTextTextEmailAddress;
    EditText EditTextTextPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris_yap_ekrani);
        girisYapButonu = (Button) findViewById(R.id.girisYapButon);
        EditTextTextEmailAddress = (EditText) findViewById(R.id.editTextTextEmailAddress);
        EditTextTextPassword = (EditText) findViewById((R.id.editTextTextPassword));
        mAuth = mAuth.getInstance();
        girisYapButonu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                girisYapClick();
            }
        });
        findViewById(R.id.uyeOlButon).setOnClickListener(view -> {
            startActivity(new Intent(girisYapEkrani.this, KayitEkrani.class));
        });
    }

    private void girisYapClick() {
        String eMail = EditTextTextEmailAddress.getText().toString();
        String password = EditTextTextPassword.getText().toString();
        if (eMail.isEmpty()) {
            Message.mesaj("Bu alan boş bırakılamaz. Lütfen mail adresinizi girin. !!!");
        } else if (password.isEmpty()) {
            Message.mesaj("Bu alan boş bırakılamaz. Lütfen şifrenizi girin. !!!");
        }
        mAuth.signInWithEmailAndPassword(eMail, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Message.mesaj("GİRİŞ İŞLEMİ BAŞARILI");
                            startActivity(new Intent(girisYapEkrani.this,MainActivity.class));
                        } else {
                           Message.mesaj("GİRİŞ İŞLEMİ BAŞARISIZ!!!, LÜTFEN YENİDEN DENEYİN.");
                        }

                    }
                });
    }
}
