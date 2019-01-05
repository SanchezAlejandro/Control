package com.example.cinthyasanchez.control;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Estadisticas extends AppCompatActivity implements View.OnClickListener{

    RadioButton barras, histograma;
    TextView aqui;
    WebView graficas;
    RelativeLayout gBarras, gPoligono, todoE;
    ImageView ayudaEst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        barras = findViewById(R.id.radioButtonGraficaBarras);
        histograma = findViewById(R.id.radioButtonGraficaHistograma);
        gBarras = findViewById(R.id.RelativeLayoutGraficaBarras);
        gPoligono = findViewById(R.id.RelativeLayoutGraficaHistograma);
        ayudaEst = findViewById(R.id.ayudaEstadisticas);
        graficas = findViewById(R.id.webViews);
        aqui = findViewById(R.id.tvaqui);
        todoE = findViewById(R.id.todoEstadisticas);

        fondoColor();

        ayudaEst.setOnClickListener(this);
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
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lateral, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_regresar:
                finish();
                break;
            case R.id.action_salir:
                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
                dialogo1.setTitle("Espera");
                dialogo1.setMessage("¿Está seguro de salir de la aplicación?");
                dialogo1.setCancelable(false);
                dialogo1.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        finishAffinity();
                    }
                });
                dialogo1.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {

                    }
                });
                dialogo1.show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ayudaEstadisticas:
                Intent iae = new Intent(this, AyudaEstadistica.class);
                startActivity(iae);
                break;
        }
    }

    @SuppressLint("ResourceType")
    public void fondoColor() {
        SharedPreferences preferencias = getSharedPreferences("misPreferencias", Context.MODE_PRIVATE);
        int cb = preferencias.getInt("color", 4);

        switch (cb) {
            case 0:
                cb = Color.parseColor(getResources().getString(R.color.amarilloPastel));
                break;
            case 1:
                cb = Color.parseColor(getResources().getString(R.color.ambarPastel));
                break;
            case 2:
                cb = Color.parseColor(getResources().getString(R.color.azulPastel));
                break;
            case 3:
                cb = Color.parseColor(getResources().getString(R.color.azulGris));
                break;
            case 4:
                cb = Color.parseColor(getResources().getString(R.color.blanco));
                break;
            case 5:
                cb = Color.parseColor(getResources().getString(R.color.gris));
                break;
            case 6:
                cb = Color.parseColor(getResources().getString(R.color.indigoPastel));
                break;
            case 7:
                cb = Color.parseColor(getResources().getString(R.color.limaPastel));
                break;
            case 8:
                cb = Color.parseColor(getResources().getString(R.color.purpuraPastel));
                break;
            case 9:
                cb = Color.parseColor(getResources().getString(R.color.rosaPastel));
                break;
            case 10:
                cb = Color.parseColor(getResources().getString(R.color.verdeAzuladoPastel));
                break;
            case 11:
                cb = Color.parseColor(getResources().getString(R.color.verdePastelDos));
                break;
            case 12:
                cb = Color.parseColor(getResources().getString(R.color.verdePastel));
                break;
        }
        todoE.setBackgroundColor(cb);
    }
}
