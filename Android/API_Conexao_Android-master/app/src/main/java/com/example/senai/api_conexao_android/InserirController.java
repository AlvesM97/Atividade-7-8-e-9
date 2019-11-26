package com.example.senai.api_conexao_android;

import android.content.Context;
import android.util.Log;

import com.android.volley.VolleyError;

import java.util.HashMap;

public class InserirController extends AbstractController implements IEnviadorHashMapPHP {
    public InserirController(Context appContext) {
        super(appContext);
    }

    @Override
    public void enviarParaPHP(HashMap map) {
        AppModelSingleton.getInstance().registrarCallback(this);
        map.put("comando", "inserir");
        AppModelSingleton.getInstance().enviarRequisicao(this.getAppContext(),map);
    }

    @Override
    public void eventoRetornouOk(Object response) {
        String resposta = String.valueOf(response);
        Log.d(this.getClass().toString(), "Evento Retornou!" + resposta);
        this.getmListenerView().eventoRetornouOk(response);
    }

    @Override
    public void eventoRetornouErro(VolleyError error) {
        Log.d(this.getClass().toString(), "Evento Retornou erro!" + error.toString());
        this.getmListenerView().eventoRetornouErro(error);
    }


}
