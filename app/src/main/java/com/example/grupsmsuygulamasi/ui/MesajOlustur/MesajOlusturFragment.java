package com.example.grupsmsuygulamasi.ui.MesajOlustur;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.grupsmsuygulamasi.Message;
import com.example.grupsmsuygulamasi.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class MesajOlusturFragment extends Fragment {
    FirebaseAuth mAuth;
    FirebaseFirestore mStore;

    EditText mesajadi,mesajaciklaması;
    Button mesajOlusturButonu;
    RecyclerView mesajlarRecylerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_mesaj_olustur, container, false);
        mesajadi=view.findViewById(R.id.mesajAdiId);
        mesajaciklaması=view.findViewById(R.id.mesajIcerikId);
        mesajOlusturButonu=view.findViewById(R.id.mesajOlustur_Id);
        mesajlarRecylerView=view.findViewById(R.id.mesajlarId);
        mAuth=FirebaseAuth.getInstance();
        mStore=FirebaseFirestore.getInstance();

        mesajOlusturButonu.setOnClickListener(v->{
            String mesajAdiText=mesajadi.getText().toString();
            String mesajAciklamasiText=mesajaciklaması.getText().toString();
            if (mesajAdiText.isEmpty()){
                Message.mesaj("Mesaj Adı Giriniz.");
                return;
            }
            if (mesajAciklamasiText.isEmpty()){
                Message.mesaj("Mesaj yazınız.");
                return;
            }
            mesajOlustur(mesajAdiText,mesajAciklamasiText);
        });
        return view;
    }
    private void mesajOlustur(String mesajAdiText,String mesajText){
        String userId=mAuth.getCurrentUser().getUid();
        mStore.collection("/users/"+userId+"/messages").add(new HashMap<String ,String>(){{
            put("mesajAdi",mesajAdiText);
            put("mesaj",mesajText);
        }})
                .addOnSuccessListener(documentReference -> {
                    Message.mesaj("Mesaj Oluşturuldu.");
                })
                .addOnFailureListener(e -> {
                    Message.mesaj("Mesaj Oluşturulamadı");
                });
    }
}