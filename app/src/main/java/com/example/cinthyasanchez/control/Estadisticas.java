package com.example.cinthyasanchez.control;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Objects;

public class Estadisticas extends AppCompatActivity {

    RadioButton barras, histograma;
    TextView aqui;
    WebView graficas;
    RelativeLayout gBarras, gPoligono, todoE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);
        overridePendingTransition(R.anim.left, R.anim.left_off);

        Toolbar bar = (Toolbar) findViewById(R.id.bar);
        setSupportActionBar(bar);
        bar.setTitle("Estadísticas");

        barras = findViewById(R.id.radioButtonGraficaBarras);
        histograma = findViewById(R.id.radioButtonGraficaHistograma);
        gBarras = findViewById(R.id.RelativeLayoutGraficaBarras);
        gPoligono = findViewById(R.id.RelativeLayoutGraficaHistograma);
        graficas = findViewById(R.id.webViews);
        aqui = findViewById(R.id.tvaqui);
        todoE = findViewById(R.id.todoEstadisticas);
        todoE.setBackgroundColor(Integer.parseInt(LocalStorage.GetLocalData(Estadisticas.this, LocalDictionary.BACKGROUND)));

        barras.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                aqui.setVisibility(View.INVISIBLE);
                graficas.getSettings().setJavaScriptEnabled(true);
                graficas.setWebViewClient(new WebViewClient());
                graficas.loadUrl("https://analytics.zoho.com/open-view/1881418000000002070");
            }
        });
        histograma.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                aqui.setVisibility(View.INVISIBLE);
                graficas.getSettings().setJavaScriptEnabled(true);
                graficas.setWebViewClient(new WebViewClient());
                graficas.loadUrl("https://analytics.zoho.com/open-view/1881418000000002119");
            }
        });

        ((AppCompatActivity) Objects.requireNonNull(Estadisticas.this)).setSupportActionBar(bar);
        Objects.requireNonNull(((AppCompatActivity) Estadisticas.this).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(((AppCompatActivity) Estadisticas.this).getSupportActionBar()).setDisplayShowHomeEnabled(true);
        bar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.right,R.anim.right_off);
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
        super.finish();
        overridePendingTransition(R.anim.right,R.anim.right_off);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_salir:
                LocalGeneral localGeneral = new LocalGeneral(this);
                localGeneral.muestraDialog(true);
                break;
            case R.id.help:
                startActivity(new Intent(this, AyudaEstadistica.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
