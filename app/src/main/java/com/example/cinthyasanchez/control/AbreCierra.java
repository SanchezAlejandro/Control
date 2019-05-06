package com.example.cinthyasanchez.control;

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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Objects;

public class AbreCierra extends AppCompatActivity implements View.OnClickListener {

    Button abre;
    RelativeLayout imagenCerrado, todoAC;
    WebView u;
    ImageView candado;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abre_cierra);
        overridePendingTransition(R.anim.left, R.anim.left_off);

        Toolbar bar = (Toolbar) findViewById(R.id.bar);
        setSupportActionBar(bar);
        bar.setTitle("Control de puerta");

        todoAC = findViewById(R.id.todoAbreCierra);
        abre = findViewById(R.id.botonAbrirCerrar);
        imagenCerrado = findViewById(R.id.relativeLayoutCerrado);
        u = findViewById(R.id.web);
        candado = findViewById(R.id.imagencerrado);
        text = findViewById(R.id.textViewCerrado);

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
                startActivity(new Intent(this, AyudaControl.class));
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
                    candado.setImageResource(R.drawable.abierto);
                    text.setText("Abierto");
                    startActivity(new Intent(this, WebViewActivity.class));
                } else {
                    abre.setText("ABRIR");
                    candado.setImageResource(R.drawable.cerrado);
                    text.setText("Cerrado");
                    startActivity(new Intent(this, WebViewActivityCerrar.class));
                }
                break;
        }
    }
}