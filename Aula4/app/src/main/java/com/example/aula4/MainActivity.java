package com.example.aula4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public final int SELECIONAR_CONTATOS = 1, TIRAR_FOTO = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS,
                Manifest.permission.CALL_PHONE, Manifest.permission.CAMERA}, 1);

        Button botaoContatos = (Button) findViewById(R.id.botaoContatos);
        botaoContatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.android.contacts/contacts");
                Intent intent = new Intent(Intent.ACTION_PICK, uri);
                startActivityForResult(intent, SELECIONAR_CONTATOS);
            }
        });

        Button botaoWeb = (Button) findViewById(R.id.botaoWeb);
        botaoWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.unisc.br");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        Button botaoCall = (Button) findViewById(R.id.botaoCall);
        botaoCall.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("tel: 98765432");
                Intent intent = new Intent(Intent.ACTION_CALL, uri);
                startActivity(intent);
            }
        });

        Button botaoMaps1 = (Button) findViewById(R.id.botaoMaps1);
        botaoMaps1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uriGeo = Uri.parse("geo:0,0?q=Unisc,Santa+Cruz+do+Sul");
                Intent intent = new Intent(Intent.ACTION_VIEW, uriGeo);
                startActivity(intent);
            }
        });

        Button botaoMaps2 = (Button) findViewById(R.id.botaoMaps2);
        botaoMaps2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String coordenada = "geo:-29.697549,-52.435784";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(coordenada));
                startActivity(intent);
            }
        });

        Button botaoMaps3 = (Button) findViewById(R.id.botaoMaps3);
        botaoMaps3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String partida = "-29.710183, -52.431101";
                String destino = "-29.697549, -52.435784";
                String url = "https://maps.google.com/maps?f=d&saddr=" + partida + "&daddr=" + destino + "&hl=pt";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });

        Button botaoPic = (Button) findViewById(R.id.botaoPic);
        botaoPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, TIRAR_FOTO);
            }
        });
    }

    protected void onActivityResult(int codigo, int resultado, Intent intent) {
        super.onActivityResult(codigo, resultado, intent);

        if (codigo == SELECIONAR_CONTATOS && resultado == RESULT_OK) {
            Uri uri = intent.getData();

            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            cursor.moveToNext();
            int nameCol = cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            int idCol = cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone._ID);
            String nome = cursor.getString(nameCol);
            String id = cursor.getString(idCol);
            cursor.close();

            Cursor telefones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id, null, null);
            telefones.moveToNext();
            String numTelefone = telefones.getString(telefones.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));
            telefones.close();

            TextView tvInfo = (TextView) findViewById(R.id.textInfoContato);
            tvInfo.setText(String.format("Nome: %s | ID: %s\nTelefone: %s", nome, id, numTelefone));
        }
        else if (codigo == SELECIONAR_CONTATOS && resultado == RESULT_CANCELED){
            TextView tvInfo = (TextView) findViewById(R.id.textInfoContato);
            tvInfo.setText(null);
        }
        else if (codigo == TIRAR_FOTO && resultado == RESULT_OK) {
            Bundle extras = intent.getExtras();
            Bitmap imagemBmp = (Bitmap) extras.get("data");
            ImageView imagePic = (ImageView) findViewById(R.id.imagemPicIt);
            imagePic.setImageBitmap(imagemBmp);
        }
        else if (codigo == TIRAR_FOTO && resultado == RESULT_CANCELED){
            ImageView imagePic = (ImageView) findViewById(R.id.imagemPicIt);
            imagePic.setImageBitmap(null);
        }
    }

}
