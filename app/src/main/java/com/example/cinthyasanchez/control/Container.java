package com.example.cinthyasanchez.control;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.example.cinthyasanchez.control.Fragments.Estadistics;
import com.example.cinthyasanchez.control.Fragments.OpenClose;

public class Container extends AppCompatActivity implements OpenClose.OnFragmentInteractionListener, Estadistics.OnFragmentInteractionListener, View.OnClickListener, AdapterView.OnItemSelectedListener {

    private SectionsPagerAdapter sectionsPagerAdapter;
    private ViewPager viewPager;
    TabLayout tabLayout;
    RelativeLayout color, consejos;
    Spinner spin, spinBotones;
    Button okColor;
    LinearLayout mensajeBotones, mensajeAjustes, sigBotones, sigAjustes;
    OpenClose openClose;
    Estadistics estadistics;

    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return openClose;
                case 1:
                    return estadistics;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conteiner);
        overridePendingTransition(R.anim.left, R.anim.left_off);

        Toolbar bar = (Toolbar) findViewById(R.id.bar);
        setSupportActionBar(bar);
        bar.setTitle("Control de Acceso");
        openClose = new OpenClose();
        estadistics = new Estadistics();

        sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.viewpager_fragment_home);
        viewPager.setAdapter(sectionsPagerAdapter);
        tabLayout = findViewById(R.id.tabs);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        color = findViewById(R.id.relativeLayoutColores);
        okColor = findViewById(R.id.buttonOkColor);
        okColor.setOnClickListener(this);
        spin = findViewById(R.id.spinnerColores);
        spinBotones = findViewById(R.id.spinnerColoresBotones);
        mensajeBotones = findViewById(R.id.RelativeLayoutMensajeDeBotones);
        mensajeAjustes = findViewById(R.id.RelativeLayoutMensajeDeAjustes);
        consejos = findViewById(R.id.rlConsejos);
        sigBotones = findViewById(R.id.ButtonSiguienteMensajeDeBotones);
        sigAjustes = findViewById(R.id.ButtonSiguienteMensajeDeAjustes);

        String valor = getIntent().getStringExtra("init");
        if (valor.equalsIgnoreCase("0")) {
            consejos.setVisibility(View.VISIBLE);
            mensajeBotones.setVisibility(View.VISIBLE);
        }

        sigBotones.setOnClickListener(this);
        sigAjustes.setOnClickListener(this);
        spin.setOnItemSelectedListener(this);
        spinBotones.setOnItemSelectedListener(this);

        String[] colores = {"Amarillo pastel","Ambar","Azul pastel", "Azul gris","Blanco","Gris","Índigo pastel","Lima pastel","Purpura Pastel","Rosa pastel","Verde azulado","Verde menta","Verde pastel"};
        spin.setAdapter(new ArrayAdapter<String>(this, R.layout.spinneritem, colores));
        spinBotones.setAdapter(new ArrayAdapter<String>(this,R.layout.spinneritem, colores));

        Boolean isFirstRun = getSharedPreferences("DICTIONARY_FILE", MODE_PRIVATE).getBoolean("First_Time", true);

        if (isFirstRun) {
            spin.setSelection(4);
            spinBotones.setSelection(4);
        } else {
            spin.setSelection(Integer.parseInt(LocalStorage.GetLocalData(Container.this, LocalDictionary.INDEX_SPIN_BACKGROUN)));
            spinBotones.setSelection(Integer.parseInt(LocalStorage.GetLocalData(Container.this, LocalDictionary.INDEX_SPIN_BACKGROUN_BUTTON)));
        }
        getSharedPreferences("DICTIONARY_FILE", MODE_PRIVATE).edit().putBoolean("First_Time", false).commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonOkColor:
                color.setVisibility(View.INVISIBLE);
                break;
            case R.id.ButtonSiguienteMensajeDeBotones:
                mensajeBotones.setVisibility(View.INVISIBLE);
                mensajeAjustes.setVisibility(View.VISIBLE);
                break;
            case R.id.ButtonSiguienteMensajeDeAjustes:
                mensajeAjustes.setVisibility(View.INVISIBLE);
                consejos.setVisibility(View.INVISIBLE);
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        switch (adapterView.getId()) {
            case R.id.spinnerColores:
                String item = adapterView.getItemAtPosition(position).toString();
                int i = obtenerPosicionItem(spin, item);
                View contenedor = view.getRootView();

                int back = LocalGeneral.SetBackground(Container.this, i);
                LocalStorage.SetLocalData(Container.this, LocalDictionary.INDEX_SPIN_BACKGROUN, String.valueOf(i));
                LocalStorage.SetLocalData(Container.this, LocalDictionary.BACKGROUND, String.valueOf(back));
                openClose.todoAC.setBackgroundColor(back);
                estadistics.todoE.setBackgroundColor(back);
                contenedor.setBackgroundColor(back);
                break;

            case R.id.spinnerColoresBotones:
                String itemBotones = adapterView.getItemAtPosition(position).toString();
                int iBotones=obtenerPosicionItem(spinBotones, itemBotones);

                int color = LocalGeneral.SetBackgroundButtons(iBotones);
                LocalStorage.SetLocalData(Container.this, LocalDictionary.INDEX_SPIN_BACKGROUN_BUTTON, String.valueOf(iBotones));
                LocalStorage.SetLocalData(Container.this, LocalDictionary.BACKGROUND_BUTTONS, String.valueOf(color));
                okColor.setBackgroundResource(color);
                openClose.abre.setBackgroundResource(color);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public static int obtenerPosicionItem(Spinner spinner, String color) {

        int posicion = 0;

        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(color)) {
                posicion = i;
            }
        }
        return posicion;
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.opciones,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case R.id.salir:
                LocalGeneral localGeneral = new LocalGeneral(this);
                localGeneral.muestraDialog();
                break;
            case R.id.help:
                int index = viewPager.getCurrentItem();
                if (index == 0) {
                    startActivity(new Intent(getApplicationContext(), AyudaControl.class));
                } else {
                    startActivity(new Intent(getApplicationContext(), AyudaEstadistica.class));
                }
                break;
            case R.id.ajustes:
                color.setVisibility(View.VISIBLE);
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed(){
        moveTaskToBack(true);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
