package com.example.aula8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ExercicioActivity extends AppCompatActivity {
    private EditText textUsuario, textSenha, textUsuarioNG, textSenhaNG;
    private TextView textViewSessao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercicio);
        setViewPrincipal();
    }


    public void onClickLogin(View view) {
        String usuario = textUsuario.getText().toString();
        String senha = textSenha.getText().toString();

        if (usuario.isEmpty() || senha.isEmpty()) {
            Toast.makeText(this, "Você precisa inserir usuário e senha.", Toast.LENGTH_SHORT).show();
            return;
        }
        SharedPreferences settings = getSharedPreferences("UserInfo" , MODE_PRIVATE);
        String usuarioSettings = settings.getString("usuario", "");
        String senhaSettings = settings.getString("senha", "");
        int sessao = settings.getInt("sessao", 0);

        if (usuario.equals(usuarioSettings) && senha.equals(senhaSettings)) {
            setViewGravado();
            sessao++;
            textViewSessao.setText("SESSÃO " + sessao);
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("sessao", sessao);
            editor.apply();
            editor.commit();
        }
        else {
            setViewNaoGravado();
        }

    }

    public void onClickGravar(View view) {
        String usuario = textUsuarioNG.getText().toString();
        String senha = textSenhaNG.getText().toString();

        Log.d("TESTE", usuario + " | " + senha);
        if (usuario.isEmpty() || senha.isEmpty()) {
            Toast.makeText(this, "Você precisa inserir usuário e senha.", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences settings = getSharedPreferences("UserInfo" , MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("usuario", usuario);
        editor.putString("senha", senha);
        editor.apply();
        editor.commit();

        setViewPrincipal();
    }

    public void onClickLogout(View view) {
        SharedPreferences settings = getSharedPreferences("UserInfo" , MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("usuario", "");
        editor.putString("senha", "");
        editor.putInt("sessao", 0);
        editor.apply();
        editor.commit();

        setViewPrincipal();
    }

    public void onClickVoltar(View view) {
        setViewPrincipal();
    }

    private void setViewPrincipal() {
        setContentView(R.layout.activity_exercicio);

        textUsuario = findViewById(R.id.textUsuario);
        textSenha = findViewById(R.id.textSenha);
    }

    private void setViewNaoGravado() {
        setContentView(R.layout.exercicio_naogravado);
        textUsuarioNG = findViewById(R.id.textUsuarioNG);
        textSenhaNG = findViewById(R.id.textSenhaNG);
    }

    private void setViewGravado() {
        setContentView(R.layout.exercicio_gravado);
        textViewSessao = findViewById(R.id.textViewSessao);
    }
}
