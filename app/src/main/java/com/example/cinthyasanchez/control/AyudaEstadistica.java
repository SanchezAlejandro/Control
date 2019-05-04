package com.example.cinthyasanchez.control;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Objects;

public class AyudaEstadistica extends AppCompatActivity {

    RelativeLayout rltodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda_estadistica);

        Toolbar bar = (Toolbar) findViewById(R.id.bar);
        setSupportActionBar(bar);
        bar.setTitle("Ayuda / Estadísticas");

        rltodo = findViewById(R.id.rlAyudaDeEstadisticas);
        rltodo.setBackgroundColor(Integer.parseInt(LocalStorage.GetLocalData(AyudaEstadistica.this, LocalDictionary.BACKGROUND)));

        ((AppCompatActivity) Objects.requireNonNull(AyudaEstadistica.this)).setSupportActionBar(bar);
        Objects.requireNonNull(((AppCompatActivity) AyudaEstadistica.this).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(((AppCompatActivity) AyudaEstadistica.this).getSupportActionBar()).setDisplayShowHomeEnabled(true);
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
}
