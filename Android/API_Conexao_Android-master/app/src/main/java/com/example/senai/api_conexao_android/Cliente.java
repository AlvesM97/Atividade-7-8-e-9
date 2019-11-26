package com.example.senai.api_conexao_android;

public class Cliente {
    public static final int LIMIT_CPF = 11;
    public static final int LIMIT_NOME = 255;
    public static final int LIMIT_ENDERECO = 255;
    public static final int LIMIT_TELEFONE = 20;
    private String cpf, nome, endereco, telefone, valor;


    public Cliente(String cpf, String nome, String endereco, String telefone, String valor) {
        if(cpf.length() > LIMIT_CPF) nome = nome.substring(0,LIMIT_CPF);
        if(nome.length() > LIMIT_NOME) nome = nome.substring(0,LIMIT_NOME);
        if(endereco.length() > LIMIT_ENDERECO) nome = nome.substring(0,LIMIT_ENDERECO);
        if(telefone.length() > LIMIT_TELEFONE) nome = nome.substring(0,LIMIT_TELEFONE);
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefone = telefone;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getValor() {
        return valor;
    }
}
