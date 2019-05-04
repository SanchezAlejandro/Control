package com.example.cinthyasanchez.control;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Objects;

public class AbreCierra extends AppCompatActivity implements View.OnClickListener {

    Button abre;
    RelativeLayout imagenAbierto, imagenCerrado, todoAC;
    WebView u;
    ImageView ayudacontrol;
    Spinner sAC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abre_cierra);

        Toolbar bar = (Toolbar) findViewById(R.id.bar);
        setSupportActionBar(bar);
        bar.setTitle("Control de puerta");

        todoAC = findViewById(R.id.todoAbreCierra);
        abre = findViewById(R.id.botonAbrirCerrar);
        imagenAbierto = findViewById(R.id.relativeLayoutAbierto);
        imagenCerrado = findViewById(R.id.relativeLayoutCerrado);
        u = findViewById(R.id.web);
        ayudacontrol = findViewById(R.id.ayudaControl);

        ayudacontrol.setOnClickListener(this);
        abre.setOnClickListener(this);

        todoAC.setBackgroundColor(Integer.parseInt(LocalStorage.GetLocalData(AbreCierra.this, LocalDictionary.BACKGROUND)));
        abre.setBackgroundResource(Integer.parseInt(LocalStorage.GetLocalData(AbreCierra.this, LocalDictionary.BACKGROUND_BUTTONS)));

        ((AppCompatActivity) Objects.requireNonNull(AbreCierra.this)).setSupportActionBar(bar);
        Objects.requireNonNull(((AppCompatActivity) AbreCierra.this).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(((AppCompatActivity) AbreCierra.this).getSupportActionBar()).setDisplayShowHomeEnabled(true);
        bar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_otras_actividades, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public void muestraDialog() {
        Dialog dialog = null;
        dialog = new Dialog(this,R.style.Theme_Dialog_Translucent);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_cerrar_app);

        ((TextView) dialog.findViewById(R.id.text_cerrar_sesion)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finishAffinity();
            }
        });

        final Dialog finalDialog2 = dialog;
        ((TextView) dialog.findViewById(R.id.text_cancelar)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finalDialog2.dismiss();

            }
        });

        dialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_salir:
                muestraDialog();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.botonAbrirCerrar:
                if (abre.getText().toString().equalsIgnoreCase("ABRIR")) {
                    abre.setText("CERRAR");
                    imagenAbierto.setVisibility(View.VISIBLE);
                    imagenCerrado.setVisibility(View.INVISIBLE);
                    startActivity(new Intent(this, WebViewActivity.class));
                } else {
                    abre.setText("ABRIR");
                    imagenAbierto.setVisibility(View.INVISIBLE);
                    imagenCerrado.setVisibility(View.VISIBLE);
                    startActivity(new Intent(this, WebViewActivityCerrar.class));
                }
                break;
            case R.id.ayudaControl:
                startActivity(new Intent(this, AyudaControl.class));
                break;
        }
    }
}