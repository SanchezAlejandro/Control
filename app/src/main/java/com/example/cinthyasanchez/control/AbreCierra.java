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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;

public class AbreCierra extends AppCompatActivity implements View.OnClickListener {

    Button abre, cierra;
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

        todoAC = findViewById(R.id.todoAbreCierra);

        abre = findViewById(R.id.botonAbrir);
        cierra = findViewById(R.id.botonCerrar);
        imagenAbierto = findViewById(R.id.relativeLayoutAbierto);
        imagenCerrado = findViewById(R.id.relativeLayoutCerrado);
        u = findViewById(R.id.web);
        ayudacontrol = findViewById(R.id.ayudaControl);

        ayudacontrol.setOnClickListener(this);

        abre.setOnClickListener(this);
        cierra.setOnClickListener(this);

        fondoColor();
        botonColor();

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
            case R.id.botonAbrir:
                imagenAbierto.setVisibility(View.VISIBLE);
                cierra.setVisibility(View.VISIBLE);
                imagenCerrado.setVisibility(View.INVISIBLE);
                abre.setVisibility(View.INVISIBLE);

                Intent mi = new Intent(this, WebViewActivity.class);
                startActivity(mi);

                break;
            case R.id.botonCerrar:
                imagenAbierto.setVisibility(View.INVISIBLE);
                cierra.setVisibility(View.INVISIBLE);
                imagenCerrado.setVisibility(View.VISIBLE);
                abre.setVisibility(View.VISIBLE);

                Intent miC = new Intent(this, WebViewActivityCerrar.class);
                startActivity(miC);

                break;
            case R.id.ayudaControl:
                Intent iac = new Intent(this, AyudaControl.class);
                startActivity(iac);
                break;
        }
    }

    public void botonColor() {
        SharedPreferences preferencias = getSharedPreferences("misPreferencias", Context.MODE_PRIVATE);
        int cb = preferencias.getInt("colorBotones", 4);

        int a;
        a = cb;

        SharedPreferences.Editor editor = preferencias.edit();
        editor.putInt("colorBotones", a);
        editor.commit();

        switch (cb) {
            case 0:
                abre.setBackgroundResource(R.drawable.ripple_amarillo);
                cierra.setBackgroundResource(R.drawable.ripple_amarillo);
                break;
            case 1:
                abre.setBackgroundResource(R.drawable.ripple_ambar);
                cierra.setBackgroundResource(R.drawable.ripple_ambar);
                break;
            case 2:
                abre.setBackgroundResource(R.drawable.ripple_azul);
                cierra.setBackgroundResource(R.drawable.ripple_azul);
                break;
            case 3:
                abre.setBackgroundResource(R.drawable.ripple_azul_gris);
                cierra.setBackgroundResource(R.drawable.ripple_azul_gris);
                break;
            case 4:
                abre.setBackgroundResource(R.drawable.ripple);
                cierra.setBackgroundResource(R.drawable.ripple);
                break;
            case 5:
                abre.setBackgroundResource(R.drawable.ripple_gris);
                cierra.setBackgroundResource(R.drawable.ripple_gris);
                break;
            case 6:
                abre.setBackgroundResource(R.drawable.ripple_indigo);
                cierra.setBackgroundResource(R.drawable.ripple_indigo);
                break;
            case 7:
                abre.setBackgroundResource(R.drawable.ripple_lima);
                cierra.setBackgroundResource(R.drawable.ripple_lima);
                break;
            case 8:
                abre.setBackgroundResource(R.drawable.ripple_purpura);
                cierra.setBackgroundResource(R.drawable.ripple_purpura);
                break;
            case 9:
                abre.setBackgroundResource(R.drawable.ripple_rosa);
                cierra.setBackgroundResource(R.drawable.ripple_rosa);
                break;
            case 10:
                abre.setBackgroundResource(R.drawable.ripple_verdeazul);
                cierra.setBackgroundResource(R.drawable.ripple_verdeazul);
                break;
            case 11:
                abre.setBackgroundResource(R.drawable.ripple_verdementa);
                cierra.setBackgroundResource(R.drawable.ripple_verdementa);
                break;
            case 12:
                abre.setBackgroundResource(R.drawable.ripple_verde);
                cierra.setBackgroundResource(R.drawable.ripple_verde);
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
        todoAC.setBackgroundColor(cb);
    }
}