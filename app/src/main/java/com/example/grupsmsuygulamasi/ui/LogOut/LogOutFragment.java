package com.example.grupsmsuygulamasi.ui.LogOut;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.grupsmsuygulamasi.GisirEkrani;
import com.example.grupsmsuygulamasi.KayitEkrani;
import com.example.grupsmsuygulamasi.R;
import com.example.grupsmsuygulamasi.girisYapEkrani;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LogOutFragment extends Fragment {

    FirebaseAuth auth;
    FirebaseUser user;
    Button out;
    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        auth=FirebaseAuth.getInstance();
        View view=inflater.inflate(R.layout.fragment_log_out,container,false);
        out=view.findViewById(R.id.cikis);
        user=auth.getCurrentUser();

        if(user==null){
            Intent intent=new Intent(getActivity(), girisYapEkrani.class);
            startActivity(intent);
            getActivity().finish();
        }
        else {
            textView.setText(user.getEmail());
        }
        out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
}