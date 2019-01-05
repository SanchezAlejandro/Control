package com.example.cinthyasanchez.control;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

public class AyudaPP extends AppCompatActivity {

    RelativeLayout todoAPP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda_pp);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        todoAPP = findViewById(R.id.rlAyudaDePP);
        fondoColor();
    }

    @Override
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
        todoAPP.setBackgroundColor(cb);
    }
}
