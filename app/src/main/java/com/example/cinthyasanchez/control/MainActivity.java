package com.example.cinthyasanchez.control;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.transition.Explode;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
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
    RelativeLayout color, login, registrarse, consejos, check, checkRegistrer;
    LinearLayout bienvenida, mensajeBotones, mensajeAjustes, mensajelogin, botones, abre, estadistica, okRegistrarse, sigBienvenida, sigBotones, sigAjustes;
    Button okColor, iniciar, registrarme;
    TextView saludoDeBienvenida, olvide;
    ImageView icControl;
    EditText usuarioRegistro, contraseniaRegistro, editCorreo, usuariologin, contraseniaLogin;
    Animation slide_up, shake, shakeEt;
    Boolean log = false;

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
        usuarioRegistro = findViewById(R.id.editTextUsuarioRegistro);
        contraseniaRegistro = findViewById(R.id.editTextContraseñaRegistro);
        editCorreo = findViewById(R.id.editTextCorreoRegistro);
        usuariologin = findViewById(R.id.editTextUsuario);
        contraseniaLogin = findViewById(R.id.editTextContraseña);
        saludoDeBienvenida = findViewById(R.id.textViewBienvenido);
        olvide = findViewById(R.id.TextViewOlvideContrasenia);
        slide_up = AnimationUtils.loadAnimation(this, R.anim.slide_down);
        shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        shakeEt = AnimationUtils.loadAnimation(this, R.anim.shake_two);
        icControl = findViewById(R.id.BotonAbre);
        check = findViewById(R.id.relativelayout_check);
        checkRegistrer = findViewById(R.id.relativelayout_check_registrer);

        abre.setOnClickListener(this);
        estadistica.setOnClickListener(this);
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

        okColor.setOnClickListener(this);
        iniciar.setOnClickListener(this);
        okRegistrarse.setOnClickListener(this);
        sigBienvenida.setOnClickListener(this);
        sigAjustes.setOnClickListener(this);
        sigBotones.setOnClickListener(this);
        registrarme.setOnClickListener(this);

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

    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case R.id.salir:
                LocalGeneral localGeneral = new LocalGeneral(this);
                localGeneral.muestraDialog(true);
                break;
            case R.id.help:
                startActivity(new Intent(getApplicationContext(), AyudaPP.class));
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
                startActivity(new Intent(this, AbreCierra.class));
                /*View sharedView = icControl;
                String transitionName = "Control de puerta";

                ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, sharedView, transitionName);
                startActivity(i, transitionActivityOptions.toBundle());*/
                break;
            case R.id.llEst:
                startActivity(new Intent(this, Estadisticas.class));
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
                    usuariologin.setAnimation(shakeEt);
                    iniciar.setAnimation(shake);
                } else {
                    if(!contraseniaEditLogin.equalsIgnoreCase(contraseñiaShare)&&usuarioEditLogin.equalsIgnoreCase(usuarioShare)){
                        contraseniaLogin.setError("Contraseña no valida");
                        contraseniaLogin.setAnimation(shakeEt);
                        iniciar.setAnimation(shake);
                    } else {
                        if(!usuarioEditLogin.equalsIgnoreCase(usuarioShare)&&!contraseniaEditLogin.equalsIgnoreCase(contraseñiaShare)){
                            usuariologin.setError("Usuario no encontrado");
                            contraseniaLogin.setError("Contraseña no valida");
                            usuariologin.setAnimation(shakeEt);
                            contraseniaLogin.setAnimation(shakeEt);
                            iniciar.setAnimation(shake);
                        } else {
                            //iniciar.setText("");
                            //v.startAnimation(slide_up);
                            //getWindow().setExitTransition(new Explode());

                            /*login.setAnimation(slide_up);
                            login.setVisibility(View.INVISIBLE);
                            botones.setVisibility(View.VISIBLE);
                            bar.setVisibility(View.VISIBLE);*/

                            //expandView();

                            /*int cx = (login.getLeft() + login.getRight()) / 2;
                            int cy = login.getTop();
                            int finalRadius = Math.max(login.getWidth(), login.getHeight());

                            Animator anim = ViewAnimationUtils.createCircularReveal(login, cx, cy, 0, finalRadius);
                            login.setBackgroundColor(R.color.colorAccent);
                            anim.start();*/

                            /*int cx = (iniciar.getLeft() + iniciar.getRight()) / 2;
                            int cy = (iniciar.getTop() + iniciar.getBottom()) / 2;
                            int finalRadius = Math.max(iniciar.getWidth(), iniciar.getHeight());

                            Animator anim = ViewAnimationUtils.createCircularReveal(login, cx, cy, 0, finalRadius);
                            anim.addListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    login.setVisibility(View.INVISIBLE);
                                    botones.setVisibility(View.VISIBLE);
                                    bar.setVisibility(View.VISIBLE);
                                }
                            });
                            anim.start();*/

                            //iniciar.setLayoutParams(new LinearLayout.LayoutParams(50, 50));

                            /*LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
                            params.width = 100;
                            params.height = 100;
                            params.topMargin = 35;
                            params.gravity = Gravity.CENTER_HORIZONTAL;
                            iniciar.setLayoutParams(params);*/

                            iniciar.setVisibility(View.INVISIBLE);
                            check.setVisibility(View.VISIBLE);
                            log = true;

                            int cx = (iniciar.getLeft() + iniciar.getRight()) / 2;
                            int cy = (iniciar.getTop() + iniciar.getBottom()) / 2;
                            int finalRadius = Math.max(iniciar.getWidth(), iniciar.getHeight());
                            Animator anim = ViewAnimationUtils.createCircularReveal(login, cx, cy, 0, finalRadius);
                            anim.addListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    login.setVisibility(View.INVISIBLE);
                                    botones.setVisibility(View.VISIBLE);
                                    bar.setVisibility(View.VISIBLE);
                                }
                            });
                            anim.start();
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
                    usuarioRegistro.setAnimation(shakeEt);
                } else {
                    if(contraseniaReg.isEmpty()){
                        contraseniaRegistro.setError("Vacio");
                        contraseniaRegistro.setAnimation(shakeEt);
                    } else {
                        if (correoReg.isEmpty()) {
                        editCorreo.setError("Vacio");
                        editCorreo.setAnimation(shakeEt);
                        } else {
                            boolean respuesta = validacionDeCorreo(correoReg);

                            if(respuesta==false){
                                editCorreo.setError("Correo no valido");
                                editCorreo.setAnimation(shakeEt);
                            }else {
                                /*botones.setVisibility(View.VISIBLE);
                                login.setVisibility(View.INVISIBLE);
                                registrarse.setVisibility(View.INVISIBLE);
                                bar.setVisibility(View.VISIBLE);
                                mensajeBotones.setVisibility(View.VISIBLE);
                                consejos.setVisibility(View.VISIBLE);*/
                                LocalStorage.SetLocalData(MainActivity.this, LocalDictionary.USER, usuarioReg);
                                LocalStorage.SetLocalData(MainActivity.this, LocalDictionary.PASSWORD, contraseniaReg);
                                LocalStorage.SetLocalData(MainActivity.this, LocalDictionary.EMAIL, correoReg);

                                registrarme.setVisibility(View.INVISIBLE);
                                checkRegistrer.setVisibility(View.VISIBLE);

                                int cx = (registrarme.getLeft() + registrarme.getRight()) / 2;
                                int cy = (registrarme.getTop() + registrarme.getBottom()) / 2;
                                int finalRadius = Math.max(registrarme.getWidth(), registrarme.getHeight());
                                Animator anim = ViewAnimationUtils.createCircularReveal(registrarse, cx, cy, 0, finalRadius);
                                anim.addListener(new AnimatorListenerAdapter() {
                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        login.setVisibility(View.INVISIBLE);
                                        botones.setVisibility(View.VISIBLE);
                                        registrarse.setVisibility(View.INVISIBLE);
                                        bar.setVisibility(View.VISIBLE);
                                        mensajeBotones.setVisibility(View.VISIBLE);
                                        consejos.setVisibility(View.VISIBLE);
                                    }
                                });
                                anim.start();
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



    private void expandView() {
        Display display = this.getWindowManager().getDefaultDisplay();
        int maxWidth = display.getWidth();
        int maxHeight = display.getHeight();
        ExpandCollapseViewAnimation animation = new ExpandCollapseViewAnimation(iniciar, maxWidth, maxHeight, true);
        animation.setDuration(500);
        animation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }
        });
        iniciar.startAnimation(animation);
        iniciar.invalidate();
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
        LocalGeneral localGeneral = new LocalGeneral(this);
        localGeneral.muestraDialog(true);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spinnerColores:
                String item = parent.getItemAtPosition(position).toString();
                int i = obtenerPosicionItem(spin, item);
                View contenedor = view.getRootView();

                int back = LocalGeneral.SetBackground(MainActivity.this, i);
                LocalStorage.SetLocalData(MainActivity.this, LocalDictionary.INDEX_SPIN_BACKGROUN, String.valueOf(i));
                LocalStorage.SetLocalData(MainActivity.this, LocalDictionary.BACKGROUND, String.valueOf(back));
                contenedor.setBackgroundColor(back);
                break;

            case R.id.spinnerColoresBotones:
                String itemBotones = parent.getItemAtPosition(position).toString();
                int iBotones=obtenerPosicionItem(spinBotones, itemBotones);

                int color = LocalGeneral.SetBackgroundButtons(iBotones);
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

    public class ExpandCollapseViewAnimation extends Animation {
        int targetWidth;
        int targetHeight;
        int initialWidth;
        int initialHeight;
        boolean expand;
        View view;

        public ExpandCollapseViewAnimation(View view, int targetWidth, int targetHeight ,boolean expand) {
            this.view = view;
            this.targetWidth = targetWidth;
            this.targetHeight = targetHeight;
            this.initialWidth = view.getWidth();
            this.initialHeight = view.getHeight();
            this.expand = expand;
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            int newWidth, newHeight;
            if (expand) {
                newWidth = this.initialWidth
                        + (int) ((this.targetWidth - this.initialWidth) * interpolatedTime);
                newHeight = this.initialHeight
                        + (int) ((this.targetHeight - this.initialHeight) * interpolatedTime);
            } else {
                newWidth = this.initialWidth
                        - (int) ((this.initialWidth - this.targetWidth) * interpolatedTime);
                newHeight = this.initialHeight
                        - (int) ((this.initialHeight - this.targetHeight) * interpolatedTime);
            }

            view.getLayoutParams().width = newWidth;
            view.getLayoutParams().height = newHeight;
            view.requestLayout();
        }

        @Override
        public void initialize(int width, int height, int parentWidth,
                               int parentHeight) {
            super.initialize(width, height, parentWidth, parentHeight);
        }

        @Override
        public boolean willChangeBounds() {
            return true;
        }

    }
}
