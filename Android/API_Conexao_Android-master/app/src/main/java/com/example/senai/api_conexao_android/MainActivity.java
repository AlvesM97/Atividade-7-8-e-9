package com.example.senai.api_conexao_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void irParaTeste(View view) {
        Intent i = new Intent(this, TesteActivity.class);
        startActivity(i);
    }

    public void irParaEnvio(View view) {
        Intent i = new Intent(this, InserirActivity.class);
        startActivity(i);
    }

    public void irParaRecebimento(View view) {
        Intent i = new Intent(this, BuscarActivity.class);
        startActivity(i);
    }
}
