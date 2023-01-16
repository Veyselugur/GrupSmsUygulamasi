package com.example.grupsmsuygulamasi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class GisirEkrani extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gisir_ekrani);
        Message.context=getApplicationContext();

        findViewById(R.id.uyeOlId).setOnClickListener(view-> {
            startActivity(new Intent(GisirEkrani.this,KayitEkrani.class));

        });
        findViewById(R.id.girisYapId).setOnClickListener(view -> {
            startActivity(new Intent(GisirEkrani.this,girisYapEkrani.class));
        });



    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}