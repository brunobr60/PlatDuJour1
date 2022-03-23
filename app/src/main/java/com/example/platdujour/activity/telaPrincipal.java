package com.example.platdujour.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.platdujour.R;

public class telaPrincipal extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);
    }

    public void atE(View view) {
        Intent intent = new Intent(telaPrincipal.this, MapaLoc.class);
        startActivity(intent);
    }

}