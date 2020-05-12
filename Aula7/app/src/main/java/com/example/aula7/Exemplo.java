package com.example.aula7;

import android.Manifest;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Exemplo extends AppCompatActivity {
    private EditText txtCEP;
    private TextView txtLogradouro;
    private TextView txtComplemento;
    private TextView txtBairro;
    private TextView txtLocalidade;
    private TextView txtUf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemplo);


        txtCEP = (EditText) findViewById(R.id.txtCEP);
        txtLogradouro = (TextView) findViewById(R.id.txtLogradouro);
        txtComplemento = (TextView) findViewById(R.id.txtComplemento);
        txtBairro = (TextView) findViewById(R.id.txtBairro);
        txtLocalidade = (TextView) findViewById(R.id.txtLocalidade);
        txtUf = (TextView) findViewById(R.id.txtUf);

    }

    public void onClickBuscar(View view) {
        String cep = txtCEP.getText().toString();
        HttpAsyncTask task = new HttpAsyncTask();
        task.execute(cep);
        /*
        try {
            String result = task.get();
            JSONObject obj = new JSONObject(result);
            txtLogradouro.setText(obj.getString("logradouro"));
            txtComplemento.setText(obj.getString("complemento"));
            txtBairro.setText(obj.getString("bairro"));
            txtLocalidade.setText(obj.getString("localidade"));
            txtUf.setText(obj.getString("uf"));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }*/
    }

    public class HttpAsyncTask extends AsyncTask<String, Void, String> {
        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(Exemplo.this);
            dialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL("https://viacep.com.br/ws/"+strings[0]+"/json");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                int status = con.getResponseCode();
                if (status == 200) {
                    InputStream is = new BufferedInputStream(con.getInputStream());
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    StringBuilder sb = new StringBuilder();
                    String str = "";

                    while ((str = br.readLine()) != null) {
                        sb.append(str);
                    }
                    con.disconnect();
                    return sb.toString();
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            dialog.dismiss();

            if (result != null) {
                try {
                    JSONObject obj = new JSONObject(result);
                    txtLogradouro.setText(obj.getString("logradouro"));
                    txtComplemento.setText(obj.getString("complemento"));
                    txtBairro.setText(obj.getString("bairro"));
                    txtLocalidade.setText(obj.getString("localidade"));
                    txtUf.setText(obj.getString("uf"));
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
