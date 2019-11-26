package com.example.senai.api_conexao_android;

import android.content.Context;
import android.util.Log;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class BuscarController extends AbstractController implements IEnviadorTextoSimplesPHP {
    public BuscarController(Context appContext) {
        super(appContext);
    }

    @Override
    public void enviarParaPHP(String texto) {
        HashMap<String,String> hm = new HashMap();
        hm.put("comando", "buscarNome");
        hm.put("valor", texto);
        AppModelSingleton.getInstance().
                registrarCallback(this);
        AppModelSingleton.getInstance().
                enviarRequisicao(this.getAppContext(),hm);
    }

    @Override
    public void eventoRetornouOk(Object response) {
        String resposta = String.valueOf(response);
        Log.d(this.getClass().toString(), "Evento Retornou!" + resposta);
        JSONArray jsonArray;
        Cliente c = null;
        ArrayList<Cliente> clientes = new ArrayList<>();
        try{
            jsonArray = new JSONArray(resposta);
            for (int i = 0;  i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String cpf = jsonObject.getString("cpf");
                String nome = jsonObject.getString("nome");
                String endereco = jsonObject.getString("endereco");
                String telefone = jsonObject.getString("telefone");
                String valor = jsonObject.getString("valor");
                c = new Cliente(cpf, nome, endereco, telefone, valor);
                clientes.add(c);
            }
        }catch(JSONException e){
            Log.d(this.getClass().toString(), "Erro de parser JSON!" + e.toString());
        }

        this.getmListenerView().eventoRetornouOk(clientes);
    }

    @Override
    public void eventoRetornouErro(VolleyError error) {
        Log.d(this.getClass().toString(), "Evento Retornou erro!" + error.toString());
        this.getmListenerView().eventoRetornouErro(error);
    }



}