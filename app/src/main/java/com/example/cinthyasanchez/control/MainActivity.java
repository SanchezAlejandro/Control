package com.example.cinthyasanchez.control;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private Toolbar bar;
    Spinner spin, spinBotones;
    RelativeLayout color, login, registrarse, consejos;
    LinearLayout bienvenida, mensajeBotones, mensajeAjustes, mensajelogin, botones, salir, abre, estadistica, okRegistrarse, sigBienvenida, sigBotones, sigAjustes, sigSalir;
    Button okColor, iniciar, registrarme;
    TextView saludoDeBienvenida, olvide;
    ImageView aiuda;

    EditText usuarioRegistro, contraseniaRegistro, editCorreo, usuariologin, contraseniaLogin;

    int m = 1;
    int b = 1;

    @SuppressLint({"ResourceType", "ApplySharedPref"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        abre = findViewById(R.id.llCon);
        estadistica = findViewById(R.id.llEst);
        //ayuda = findViewById(R.id.llAyu);
        botones = findViewById(R.id.LinearLayoutBotones);
        color = findViewById(R.id.relativeLayoutColores);
        login = findViewById(R.id.relativeLayoutLogin);
        bienvenida = findViewById(R.id.RelativeLayoutMensajeDeBienvenida);
        mensajeBotones = findViewById(R.id.RelativeLayoutMensajeDeBotones);
        mensajeAjustes = findViewById(R.id.RelativeLayoutMensajeDeAjustes);
        mensajelogin = findViewById(R.id.RelativeLayoutMensajeDeLogin);
        registrarse = findViewById(R.id.relativeLayoutRegistrarse);
        consejos = findViewById(R.id.rlConsejos);
        salir = findViewById(R.id.RelativeLayoutMensajeDeSalir);
        usuarioRegistro = findViewById(R.id.editTextUsuarioRegistro);
        contraseniaRegistro = findViewById(R.id.editTextContraseñaRegistro);
        editCorreo = findViewById(R.id.editTextCorreoRegistro);
        usuariologin = findViewById(R.id.editTextUsuario);
        contraseniaLogin = findViewById(R.id.editTextContraseña);
        saludoDeBienvenida = findViewById(R.id.textViewBienvenido);
        aiuda = findViewById(R.id.ayudaPP);
        olvide = findViewById(R.id.TextViewOlvideContrasenia);

        abre.setOnClickListener(this);
        estadistica.setOnClickListener(this);
        //ayuda.setOnClickListener(this);
        aiuda.setOnClickListener(this);
        olvide.setOnClickListener(this);

        bar = findViewById(R.id.bar);
        setSupportActionBar(bar);
        bar.setVisibility(View.INVISIBLE);

        okColor = findViewById(R.id.buttonOkColor);
        iniciar = findViewById(R.id.ButtonIniciarSesion);
        okRegistrarse = findViewById(R.id.ButtonOkeiMensajeDeLogin);
        sigBotones = findViewById(R.id.ButtonSiguienteMensajeDeBotones);
        sigAjustes = findViewById(R.id.ButtonSiguienteMensajeDeAjustes);
        sigBienvenida = findViewById(R.id.ButtonSiguienteMensajeDeBienvenida);
        registrarme = findViewById(R.id.ButtonRegistro);
        sigSalir = findViewById(R.id.ButtonSiguienteMensajeDeSalir);

        okColor.setOnClickListener(this);
        iniciar.setOnClickListener(this);
        okRegistrarse.setOnClickListener(this);
        sigBienvenida.setOnClickListener(this);
        sigAjustes.setOnClickListener(this);
        sigBotones.setOnClickListener(this);
        registrarme.setOnClickListener(this);
        sigSalir.setOnClickListener(this);

        spin = findViewById(R.id.spinnerColores);
        spinBotones = findViewById(R.id.spinnerColoresBotones);

        spin.setOnItemSelectedListener(this);
        spinBotones.setOnItemSelectedListener(this);

        String[] colores = {"Amarillo pastel","Ambar","Azul pastel", "Azul gris","Blanco","Gris","Índigo pastel","Lima pastel","Purpura Pastel","Rosa pastel","Verde azulado","Verde menta","Verde pastel"};
        spin.setAdapter(new ArrayAdapter<String>(this, R.layout.spinneritem, colores));
        spinBotones.setAdapter(new ArrayAdapter<String>(this,R.layout.spinneritem, colores));

        Boolean isFirstRun = getSharedPreferences("LOGIN_FILE", MODE_PRIVATE).getBoolean("First_Time", true);

        if (isFirstRun) {
            spin.setSelection(4);
            spinBotones.setSelection(4);
        } else {
            spin.setSelection(Integer.parseInt(LocalStorage.GetLocalData(MainActivity.this, LocalDictionary.INDEX_SPIN_BACKGROUN)));
            spinBotones.setSelection(Integer.parseInt(LocalStorage.GetLocalData(MainActivity.this, LocalDictionary.INDEX_SPIN_BACKGROUN_BUTTON)));
            bienvenida.setVisibility(View.INVISIBLE);
            login.setVisibility(View.VISIBLE);
            saludoDeBienvenida.setText("Hola "+ LocalStorage.GetLocalData(MainActivity.this, LocalDictionary.USER) +", por favor inicia sesión para continuar.");
        }
        getSharedPreferences("LOGIN_FILE", MODE_PRIVATE).edit().putBoolean("First_Time", false).commit();
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.opciones,menu);
        return true;
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
                finish();
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

    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case R.id.salir:
                muestraDialog();
                break;
            case R.id.ajustes:

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    // get the center for the clipping circle
                    int cx = color.getWidth() / 2;
                    int cy = color.getHeight() / 2;

                    // get the final radius for the clipping circle
                    float finalRadius = (float) Math.hypot(cx, cy);

                    // create the animator for this view (the start radius is zero)
                    Animator anim = ViewAnimationUtils.createCircularReveal(color, cx, cy, 0f, finalRadius);

                    // make the view visible and start the animation
                    color.setVisibility(View.VISIBLE);
                    anim.start();
                } else {
                    // set the view to visible without a circular reveal animation below Lollipop
                    color.setVisibility(View.VISIBLE);
                }
                botones.setVisibility(View.INVISIBLE);
                break;
        }
        return true;
    }

    @SuppressLint("ResourceType")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.llCon:
                Intent i = new Intent(this, AbreCierra.class);
                startActivity(i);
                break;
            case R.id.llEst:
                Intent i2 = new Intent(this, Estadisticas.class);
                startActivity(i2);
                break;
            case R.id.ayudaPP:
                Intent i4 = new Intent(getApplicationContext(), AyudaPP.class);
                startActivity(i4);
                break;
            case R.id.buttonOkColor:

                if (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) {
                    // get the center for the clipping circle
                    int cx = color.getWidth() / 2;
                    int cy = color.getHeight() / 2;

                    // get the initial radius for the clipping circle
                    float initialRadius = (float) Math.hypot(cx, cy);

                    // create the animation (the final radius is zero)
                    Animator anim = ViewAnimationUtils.createCircularReveal(color, cx, cy, initialRadius, 0f);

                    // make the view invisible when the animation is done
                    anim.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            color.setVisibility(View.INVISIBLE);
                        }
                    });

                    // start the animation
                    anim.start();
                } else {
                    // set the view to visible without a circular reveal animation below Lollipop
                    color.setVisibility(View.INVISIBLE);
                }

                botones.setVisibility(View.VISIBLE);
                //LocalStorage.SetLocalData(MainActivity.this, LocalDictionary.BACKGROUND, String. valueOf(m));
                //LocalStorage.SetLocalData(MainActivity.this, LocalDictionary.BACKGROUND_BUTTONS, String. valueOf(b));
                break;
            case R.id.ButtonIniciarSesion:
                String usuarioShare = LocalStorage.GetLocalData(MainActivity.this, LocalDictionary.USER);
                String contraseñiaShare = LocalStorage.GetLocalData(MainActivity.this, LocalDictionary.PASSWORD);

                String usuarioEditLogin = usuariologin.getText().toString();
                String contraseniaEditLogin = contraseniaLogin.getText().toString();

                if(!usuarioEditLogin.equalsIgnoreCase(usuarioShare)&&contraseniaEditLogin.equalsIgnoreCase(contraseñiaShare)){
                    usuariologin.setError("Usuario no encontrado");
                } else {
                    if(!contraseniaEditLogin.equalsIgnoreCase(contraseñiaShare)&&usuarioEditLogin.equalsIgnoreCase(usuarioShare)){
                        contraseniaLogin.setError("Contraseña no valida");
                    } else {
                        if(!usuarioEditLogin.equalsIgnoreCase(usuarioShare)&&!contraseniaEditLogin.equalsIgnoreCase(contraseñiaShare)){
                            usuariologin.setError("Usuario no encontrado");
                            contraseniaLogin.setError("Contraseña no valida");
                        } else {
                            botones.setVisibility(View.VISIBLE);
                            login.setVisibility(View.INVISIBLE);
                            bar.setVisibility(View.VISIBLE);
                        }
                    }
                }
                InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(contraseniaLogin.getWindowToken(), 0);
                break;
            case R.id.ButtonRegistro:
                String usuarioReg = usuarioRegistro.getText().toString();
                String contraseniaReg = contraseniaRegistro.getText().toString();
                String correoReg = editCorreo.getText().toString();

                if(usuarioReg.isEmpty()){
                    usuarioRegistro.setError("Vacio");
                } else {
                    if(contraseniaReg.isEmpty()){
                        contraseniaRegistro.setError("Vacio");
                    } else {
                        if (correoReg.isEmpty()) {
                        editCorreo.setError("Vacio");
                        } else {
                            boolean respuesta = validacionDeCorreo(correoReg);

                            if(respuesta==false){
                                editCorreo.setError("Correo no valido");
                            }else {
                                botones.setVisibility(View.VISIBLE);
                                login.setVisibility(View.INVISIBLE);
                                registrarse.setVisibility(View.INVISIBLE);
                                bar.setVisibility(View.VISIBLE);
                                mensajeBotones.setVisibility(View.VISIBLE);
                                consejos.setVisibility(View.VISIBLE);
                                LocalStorage.SetLocalData(MainActivity.this, LocalDictionary.USER, usuarioReg);
                                LocalStorage.SetLocalData(MainActivity.this, LocalDictionary.PASSWORD, contraseniaReg);
                                LocalStorage.SetLocalData(MainActivity.this, LocalDictionary.EMAIL, correoReg);
                            }
                        }
                    }
                }
                InputMethodManager inputMethodManagerR = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManagerR.hideSoftInputFromWindow(contraseniaRegistro.getWindowToken(), 0);
                break;
            case R.id.ButtonOkeiMensajeDeLogin:
                mensajelogin.setVisibility(View.INVISIBLE);
                registrarse.setVisibility(View.VISIBLE);
                bar.setVisibility(View.INVISIBLE);
                break;
            case R.id.ButtonSiguienteMensajeDeBienvenida:
                bienvenida.setVisibility(View.INVISIBLE);
                mensajelogin.setVisibility(View.VISIBLE);
                break;
            case R.id.ButtonSiguienteMensajeDeBotones:
                mensajeBotones.setVisibility(View.INVISIBLE);
                salir.setVisibility(View.VISIBLE);
                break;
            case R.id.ButtonSiguienteMensajeDeSalir:
                salir.setVisibility(View.INVISIBLE);
                mensajeAjustes.setVisibility(View.VISIBLE);
                break;
            case R.id.ButtonSiguienteMensajeDeAjustes:
                mensajeAjustes.setVisibility(View.INVISIBLE);
                consejos.setVisibility(View.INVISIBLE);
                break;
            case R.id.TextViewOlvideContrasenia:
                Intent iOMC = new Intent(MainActivity.this, RecuperarCuenta.class);
                startActivity(iOMC);
                break;
        }
    }

    public static boolean validacionDeCorreo(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    @Override
    public void onBackPressed(){
        muestraDialog();
    }

    @SuppressLint("ResourceType")
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spinnerColores:
                String item = parent.getItemAtPosition(position).toString();
                int i = obtenerPosicionItem(spin, item);
                View contenedor = view.getRootView();

                int back = LocalStorage.SetBackground(MainActivity.this, i);
                LocalStorage.SetLocalData(MainActivity.this, LocalDictionary.INDEX_SPIN_BACKGROUN, String.valueOf(i));
                LocalStorage.SetLocalData(MainActivity.this, LocalDictionary.BACKGROUND, String.valueOf(back));
                contenedor.setBackgroundColor(back);
                break;

            case R.id.spinnerColoresBotones:
                String itemBotones = parent.getItemAtPosition(position).toString();
                int iBotones=obtenerPosicionItem(spinBotones, itemBotones);

                int color = LocalStorage.SetBackgroundButtons(iBotones);
                LocalStorage.SetLocalData(MainActivity.this, LocalDictionary.INDEX_SPIN_BACKGROUN_BUTTON, String.valueOf(iBotones));
                LocalStorage.SetLocalData(MainActivity.this, LocalDictionary.BACKGROUND_BUTTONS, String.valueOf(color));
                abre.setBackgroundResource(color);
                estadistica.setBackgroundResource(color);
                okColor.setBackgroundResource(color);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
}
