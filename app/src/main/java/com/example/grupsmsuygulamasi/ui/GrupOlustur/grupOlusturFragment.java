package com.example.grupsmsuygulamasi.ui.GrupOlustur;

import static android.app.Activity.RESULT_OK;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.grupsmsuygulamasi.Message;
import com.example.grupsmsuygulamasi.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageException;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;


public class grupOlusturFragment extends Fragment {
    EditText grupAdi,grupAciklamasi;
    ImageView grupSimgesi;
    Button grupOlusturButonu;
    RecyclerView gruplar;

    FirebaseAuth mAuth;
    FirebaseFirestore mStore;
    FirebaseStorage mStorage;


    Uri filePath;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_grup_olustur, container, false);
        grupAdi=view.findViewById(R.id.grupAdiId);
        grupAciklamasi=view.findViewById(R.id.grupAciklamaId);
        grupSimgesi=view.findViewById(R.id.grupSimgeId);
        grupOlusturButonu=view.findViewById(R.id.grupOlustur_Id);
        gruplar=view.findViewById(R.id.gruplarId);
        mAuth=FirebaseAuth.getInstance();
        mStorage=FirebaseStorage.getInstance();
        mStore=FirebaseFirestore.getInstance();
        ActivityResultLauncher<Intent> activityResultLauncher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result-> {
            if(result.getResultCode()==RESULT_OK){
                filePath=result.getData().getData();
                grupSimgesi.setImageURI(filePath);
            }
        });
        grupSimgesi.setOnClickListener(v->{
            Intent intent=new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            activityResultLauncher.launch(intent);
        });
        grupOlusturButonu.setOnClickListener(v->{
            String isim=grupAdi.getText().toString();
            String aciklama=grupAciklamasi.getText().toString();
            if (isim.isEmpty()){
                Message.mesaj("Lütfen Grup Adı Girin");
            }
            if (filePath!=null){
                StorageReference storageReference=mStorage.getReference().child("Fotoğraflar"+ UUID.randomUUID().toString());
                storageReference.putFile(filePath).addOnSuccessListener(taskSnapshot -> {
                    storageReference.getDownloadUrl().addOnSuccessListener(uri ->{
                        String fotografUrl=uri.toString();
                        Message.mesaj("Fotoğraf Yüklendi");
                        grupOlusturma(isim,aciklama,fotografUrl);
                    } );
                });
            }
            else {
                grupOlusturma(isim,aciklama,null);
            }
        });

        return view;
    }
    private void grupOlusturma(String isim,String aciklama, String fotografUrl){
        String userId=mAuth.getCurrentUser().getUid();
        mStore.collection("/users/"+userId +"/gruplar").add(new HashMap<String,Object>() {
            {
                put("grupAdi", isim);
                put("grupAciklamasi", aciklama);
                put("grupResmi", fotografUrl);
                put("numaralar", new ArrayList<String>());
            }
        }).addOnSuccessListener(documentReference -> {
            Message.mesaj("Grup Oluşturuldu");
        }).addOnFailureListener(e -> {
            Message.mesaj("Grup Oluşturulamadı");
        });


    }
}