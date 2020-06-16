package com.example.aula12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickExemplo(View view) {
        startActivity(new Intent(this, ExemploActivity.class));
    }
}

/**
 O Firebase Cloud Messagin é uma solução multiplataforma de mensagens que te deixa enviar notificações push sem se preocupar com o server code.
 Você consegue criar notificações com intuitos específicos sem precisar se preocupar com um código específico para isso.
 Para a implementação, é necessário ir para o console do Firebase, selecionar para adicionar um projeto, criar o projeto, adicionar o firebase para o
 app, registrar o app, selecionar "download google-services.json", no Android Studio, arrastar o google-services.json para a pasta "app" do projeto,
 abrir o build.gradle e adicionar os serviços e plugins relacionados, sincronizar as mudanças, instalar o app. No console do Firbase, rodar o app
 para verificar instalação, com isso feito, ir para o console e lá criar a sua notificação.

 Passo a passo em: https://www.androidauthority.com/android-push-notifications-with-firebase-cloud-messaging-925075/

 **/