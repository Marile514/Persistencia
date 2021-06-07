package com.example.persistencia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private Button btnGrabar, btnLeer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inits();
        events();
    }

    private void buttonActions(View boton){
        if(boton.getId() == R.id.btn1){
            try {
                OutputStreamWriter bx = new OutputStreamWriter(openFileOutput("usuarios.txt", Activity.MODE_APPEND));

                bx.write("usuario|direccion|clave|fecha nacimiento\n");
                bx.write("Luchito|pasaje alelí|329064|22/06/1999\n");
                for(int x = 0; x <= 10; ++x){
                    bx.write("usuario" + x + "|direccion" + x + "|clave" + x + "|fecha" + x + "\n");
                }
                bx.close();
                Toast.makeText(MainActivity.this, "Archivo guardado", Toast.LENGTH_SHORT).show();
            }catch (Exception ex){
                Log.e("TAG_", ex.toString());
            }

        }else if(boton.getId() == R.id.btn2){
            try{
                InputStreamReader vsi = new InputStreamReader(openFileInput("usuarios.txt"));

                BufferedReader br = new BufferedReader(vsi);

                String linea;
                do{
                    linea = br.readLine();
                    if(linea != null)
                        Log.d("TAG_", linea);
                }while(linea != null);
                br.close();
                vsi.close();
            }catch (Exception ex){
                Log.e("TAG_", ex.toString());
            }
        }
    }

    private void inits(){
        btnGrabar = findViewById(R.id.btn1);
        btnLeer = findViewById(R.id.btn2);
    }

    private void events(){
        btnGrabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonActions(v);
            }
        });

        btnLeer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonActions(v);
            }
        });
    }
}