package com.example.cinthyasanchez.control;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
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
    RelativeLayout color, login, bienvenida, mensajeBotones, mensajeAjustes, mensajelogin, registrarse, consejos, salir;
    LinearLayout botones, abre, estadistica, okRegistrarse, sigBienvenida, sigBotones, sigAjustes, sigSalir;
    Button okColor, iniciar, registrarme;
    TextView saludoDeBienvenida, olvide;
    ImageView aiuda;

    EditText usuarioRegistro, contraseniaRegistro, editCorreo, usuariologin, contraseniaLogin;

    int m = 1;
    int b = 1;

    @SuppressLint("ResourceType")
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

        SharedPreferences preferencias = getSharedPreferences("misPreferencias", Context.MODE_PRIVATE);
        int indiceSpinner = preferencias.getInt("color", 4);
        spin.setSelection(indiceSpinner);

        int indiceFondoBoton = preferencias.getInt("colorBotones", 4);
        spinBotones.setSelection(indiceFondoBoton);

        int inicio = preferencias.getInt("primera", 0);
        inicio = inicio+1;

        SharedPreferences.Editor editorR = preferencias.edit();
        editorR.putInt("primera", inicio);
        editorR.commit();


        int primeraVez = preferencias.getInt("primera", 1);
        if(primeraVez>1){
            String usuarioSaludo = preferencias.getString("usuario", "usuario");

            bienvenida.setVisibility(View.INVISIBLE);
            login.setVisibility(View.VISIBLE);
            saludoDeBienvenida.setText("Hola "+ usuarioSaludo +", por favor inicia sesión para continuar.");
        } else {

        }
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.opciones,menu);
        //getMenuInflater().inflate(R.menu.activity_menu_lateral_pp, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case R.id.salir:
                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
                dialogo1.setTitle("Espera");
                dialogo1.setMessage("¿Está seguro de salir de la aplicación?");
                dialogo1.setCancelable(false);
                dialogo1.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        finish();
                    }
                });
                dialogo1.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {

                    }
                });
                dialogo1.show();
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
                //MenuLateral.opcion=1;
                Intent i = new Intent(this, AbreCierra.class);
                startActivity(i);
                break;
            case R.id.llEst:
                //MenuLateral.opcion=2;
                Intent i2 = new Intent(this, Estadisticas.class);
                startActivity(i2);
                break;
            case R.id.ayudaPP:
                //MenuLateral.opcion=4;
                Intent i4 = new Intent(getApplicationContext(), AyudaPP.class);
                startActivity(i4);
                break;
            case R.id.buttonOkColor:
                //color.setVisibility(View.INVISIBLE);

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
                SharedPreferences preferencias = getSharedPreferences("misPreferencias", Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = preferencias.edit();
                editor.putInt("color", m);
                editor.putInt("colorBotones", b);
                editor.commit();
                break;
            case R.id.ButtonIniciarSesion:
                SharedPreferences preferenciasL = getSharedPreferences("misPreferencias", Context.MODE_PRIVATE);
                String usuarioShare = preferenciasL.getString("usuario", "usuario");
                String contraseñiaShare = preferenciasL.getString("contrasenia", "contrasenia");

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

                                SharedPreferences preferenciasR = getSharedPreferences("misPreferencias", Context.MODE_PRIVATE);

                                SharedPreferences.Editor editorR = preferenciasR.edit();
                                editorR.putString("usuario", usuarioReg);
                                editorR.putString("contrasenia", contraseniaReg);
                                editorR.putString("correo", correoReg);
                                editorR.commit();
                            }
                        }
                    }
                }
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
        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
        dialogo1.setTitle("Espera");
        dialogo1.setMessage("¿Está seguro de salir de la aplicación?");
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                finish();
            }
        });
        dialogo1.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {

            }
        });
        dialogo1.show();
    }

    @SuppressLint("ResourceType")
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spinnerColores:
                String item = parent.getItemAtPosition(position).toString();
                int i = obtenerPosicionItem(spin, item);
                View contenedor = view.getRootView();

                SharedPreferences preferencias = getSharedPreferences("misPreferencias", Context.MODE_PRIVATE);

                m = i;

                SharedPreferences.Editor editor = preferencias.edit();
                editor.putInt("color", m);
                editor.commit();

                switch (i) {
                    case 0:
                        i = Color.parseColor(getResources().getString(R.color.amarilloPastel));
                        break;
                    case 1:
                        i = Color.parseColor(getResources().getString(R.color.ambarPastel));
                        break;
                    case 2:
                        i = Color.parseColor(getResources().getString(R.color.azulPastel));
                        break;
                    case 3:
                        i = Color.parseColor(getResources().getString(R.color.azulGris));
                        break;
                    case 4:
                        i = Color.parseColor(getResources().getString(R.color.blanco));
                        break;
                    case 5:
                        i = Color.parseColor(getResources().getString(R.color.gris));
                        break;
                    case 6:
                        i = Color.parseColor(getResources().getString(R.color.indigoPastel));
                        break;
                    case 7:
                        i = Color.parseColor(getResources().getString(R.color.limaPastel));
                        break;
                    case 8:
                        i = Color.parseColor(getResources().getString(R.color.purpuraPastel));
                        break;
                    case 9:
                        i = Color.parseColor(getResources().getString(R.color.rosaPastel));
                        break;
                    case 10:
                        i = Color.parseColor(getResources().getString(R.color.verdeAzuladoPastel));
                        break;
                    case 11:
                        i = Color.parseColor(getResources().getString(R.color.verdePastel));
                        break;
                    case 12:
                        i = Color.parseColor(getResources().getString(R.color.verdePastelDos));
                        break;
                }
                contenedor.setBackgroundColor(i);
                break;

            case R.id.spinnerColoresBotones:
                String itemBotones = parent.getItemAtPosition(position).toString();
                int iBotones=obtenerPosicionItem(spinBotones, itemBotones);

                SharedPreferences preferenciasB = getSharedPreferences("misPreferencias", Context.MODE_PRIVATE);

                b = iBotones;

                SharedPreferences.Editor editorB = preferenciasB.edit();
                editorB.putInt("colorBotones", b);
                editorB.commit();

                switch (iBotones) {
                    case 0:
                        abre.setBackgroundResource(R.drawable.ripple_amarillo);
                        estadistica.setBackgroundResource(R.drawable.ripple_amarillo);
                        //ayuda.setBackgroundResource(R.drawable.ripple_amarillo);
                        okColor.setBackgroundResource(R.drawable.ripple_amarillo);
                        break;
                    case 1:
                        abre.setBackgroundResource(R.drawable.ripple_ambar);
                        estadistica.setBackgroundResource(R.drawable.ripple_ambar);
                        //ayuda.setBackgroundResource(R.drawable.ripple_ambar);
                        okColor.setBackgroundResource(R.drawable.ripple_ambar);
                        break;
                    case 2:
                        abre.setBackgroundResource(R.drawable.ripple_azul);
                        estadistica.setBackgroundResource(R.drawable.ripple_azul);
                        //ayuda.setBackgroundResource(R.drawable.ripple_azul);
                        okColor.setBackgroundResource(R.drawable.ripple_azul);
                        break;
                    case 3:
                        abre.setBackgroundResource(R.drawable.ripple_azul_gris);
                        estadistica.setBackgroundResource(R.drawable.ripple_azul_gris);
                        //ayuda.setBackgroundResource(R.drawable.ripple_azul_gris);
                        okColor.setBackgroundResource(R.drawable.ripple_azul_gris);
                        break;
                    case 4:
                        abre.setBackgroundResource(R.drawable.ripple);
                        estadistica.setBackgroundResource(R.drawable.ripple);
                        //ayuda.setBackgroundResource(R.drawable.ripple);
                        okColor.setBackgroundResource(R.drawable.ripple);
                        break;
                    case 5:
                        abre.setBackgroundResource(R.drawable.ripple_gris);
                        estadistica.setBackgroundResource(R.drawable.ripple_gris);
                        ///ayuda.setBackgroundResource(R.drawable.ripple_gris);
                        okColor.setBackgroundResource(R.drawable.ripple_gris);
                        break;
                    case 6:
                        abre.setBackgroundResource(R.drawable.ripple_indigo);
                        estadistica.setBackgroundResource(R.drawable.ripple_indigo);
                        //ayuda.setBackgroundResource(R.drawable.ripple_indigo);
                        okColor.setBackgroundResource(R.drawable.ripple_indigo);
                        break;
                    case 7:
                        abre.setBackgroundResource(R.drawable.ripple_lima);
                        estadistica.setBackgroundResource(R.drawable.ripple_lima);
                        //ayuda.setBackgroundResource(R.drawable.ripple_lima);
                        okColor.setBackgroundResource(R.drawable.ripple_lima);
                        break;
                    case 8:
                        abre.setBackgroundResource(R.drawable.ripple_purpura);
                        estadistica.setBackgroundResource(R.drawable.ripple_purpura);
                        //ayuda.setBackgroundResource(R.drawable.ripple_purpura);
                        okColor.setBackgroundResource(R.drawable.ripple_purpura);
                        break;
                    case 9:
                        abre.setBackgroundResource(R.drawable.ripple_rosa);
                        estadistica.setBackgroundResource(R.drawable.ripple_rosa);
                        //ayuda.setBackgroundResource(R.drawable.ripple_rosa);
                        okColor.setBackgroundResource(R.drawable.ripple_rosa);
                        break;
                    case 10:
                        abre.setBackgroundResource(R.drawable.ripple_verdeazul);
                        estadistica.setBackgroundResource(R.drawable.ripple_verdeazul);
                        //ayuda.setBackgroundResource(R.drawable.ripple_verdeazul);
                        okColor.setBackgroundResource(R.drawable.ripple_verdeazul);
                        break;
                    case 11:
                        abre.setBackgroundResource(R.drawable.ripple_verdementa);
                        estadistica.setBackgroundResource(R.drawable.ripple_verdementa);
                        //ayuda.setBackgroundResource(R.drawable.ripple_verdementa);
                        okColor.setBackgroundResource(R.drawable.ripple_verdementa);
                        break;
                    case 12:
                        abre.setBackgroundResource(R.drawable.ripple_verde);
                        estadistica.setBackgroundResource(R.drawable.ripple_verde);
                        //ayuda.setBackgroundResource(R.drawable.ripple_verde);
                        okColor.setBackgroundResource(R.drawable.ripple_verde);
                        break;
                }

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
