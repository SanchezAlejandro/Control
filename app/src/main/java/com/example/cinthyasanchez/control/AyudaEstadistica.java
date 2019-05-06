package com.example.cinthyasanchez.control;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Objects;

public class AyudaEstadistica extends AppCompatActivity {

    RelativeLayout rltodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda_estadistica);
        overridePendingTransition(R.anim.left, R.anim.left_off);

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
                overridePendingTransition(R.anim.right,R.anim.right_off);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.opciones_logout, menu);
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
            case R.id.salir:
                LocalGeneral localGeneral = new LocalGeneral(this);
                localGeneral.muestraDialog(true);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
