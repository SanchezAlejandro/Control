package com.example.cinthyasanchez.control;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.Display;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar bar;
    RelativeLayout login, registrarse, check, checkRegistrer;
    LinearLayout bienvenida, mensajelogin, okRegistrarse, sigBienvenida;
    Button iniciar, registrarme;
    TextView saludoDeBienvenida, olvide;
    EditText usuarioRegistro, contraseniaRegistro, editCorreo, usuariologin, contraseniaLogin;
    Animation slide_up, shake, shakeEt;
    Boolean log = false;
    TextInputLayout tilName, tilPassword, tilEmail, tilUserLogin, tilPasswordLogin;

    @SuppressLint({"ResourceType", "ApplySharedPref"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        overridePendingTransition(R.anim.right, R.anim.right_off);

        login = findViewById(R.id.relativeLayoutLogin);
        bienvenida = findViewById(R.id.RelativeLayoutMensajeDeBienvenida);
        mensajelogin = findViewById(R.id.RelativeLayoutMensajeDeLogin);
        registrarse = findViewById(R.id.relativeLayoutRegistrarse);
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
        check = findViewById(R.id.relativelayout_check);
        checkRegistrer = findViewById(R.id.relativelayout_check_registrer);
        tilName = findViewById(R.id.til_user);
        tilPassword = findViewById(R.id.til_password);
        tilEmail = findViewById(R.id.til_email);
        tilUserLogin = findViewById(R.id.til_user_login);
        tilPasswordLogin = findViewById(R.id.til_password_login);

        olvide.setOnClickListener(this);

        bar = findViewById(R.id.bar);
        setSupportActionBar(bar);
        bar.setVisibility(View.INVISIBLE);

        iniciar = findViewById(R.id.ButtonIniciarSesion);
        okRegistrarse = findViewById(R.id.ButtonOkeiMensajeDeLogin);
        sigBienvenida = findViewById(R.id.ButtonSiguienteMensajeDeBienvenida);
        registrarme = findViewById(R.id.ButtonRegistro);

        iniciar.setOnClickListener(this);
        okRegistrarse.setOnClickListener(this);
        sigBienvenida.setOnClickListener(this);
        registrarme.setOnClickListener(this);

        Boolean isFirstRun = getSharedPreferences("LOGIN_FILE", MODE_PRIVATE).getBoolean("init", true);

        if (isFirstRun) {

        } else {
            bienvenida.setVisibility(View.INVISIBLE);
            login.setVisibility(View.VISIBLE);
            saludoDeBienvenida.setText("Hola "+ LocalStorage.GetLocalData(MainActivity.this, LocalDictionary.USER) +", por favor inicia sesión para continuar.");
        }
        getSharedPreferences("LOGIN_FILE", MODE_PRIVATE).edit().putBoolean("init", false).commit();

        Watches();
    }

    @SuppressLint("ResourceType")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ButtonIniciarSesion:
                Validate();
                break;
            case R.id.ButtonRegistro:
                Validate();
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
            case R.id.TextViewOlvideContrasenia:
                Intent iOMC = new Intent(MainActivity.this, RecuperarCuenta.class);
                startActivity(iOMC);
                break;
        }
    }

    @SuppressLint("ApplySharedPref")
    public void Validate () {
        Boolean registrer = getSharedPreferences("DICTIONARY_FILE", MODE_PRIVATE).getBoolean("Registrer", false);

        if (!registrer) {
            String usuarioReg = usuarioRegistro.getText().toString();
            String contraseniaReg = contraseniaRegistro.getText().toString();
            String correoReg = editCorreo.getText().toString();

            if (usuarioReg.isEmpty()) {
                tilName.setError("Vacio");
                tilName.setAnimation(shakeEt);
                registrarme.setAnimation(shake);
            } else {

            }

            if (contraseniaReg.isEmpty()) {
                tilPassword.setError("Vacio");
                tilPassword.setAnimation(shakeEt);
                registrarme.setAnimation(shake);
            } else {

            }

            if (correoReg.isEmpty()) {
                tilEmail.setError("Vacio");
                tilEmail.setAnimation(shakeEt);
                registrarme.setAnimation(shake);
            } else {
                boolean respuesta = validacionDeCorreo(correoReg);

                if (respuesta == false) {
                    tilEmail.setError("Correo no valido");
                    tilEmail.setAnimation(shakeEt);
                    registrarme.setAnimation(shake);
                } else {
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
                            /*login.setVisibility(View.INVISIBLE);
                            registrarse.setVisibility(View.INVISIBLE);
                            bar.setVisibility(View.VISIBLE);*/
                        }
                    });
                    anim.start();
                    log = true;
                    getSharedPreferences("DICTIONARY_FILE", MODE_PRIVATE).edit().putBoolean("Registrer", true).commit();
                    Intent i = new Intent(MainActivity.this, Container.class);
                    i.putExtra("init", "0");
                    startActivity(i);
                }
            }
            InputMethodManager inputMethodManagerR = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManagerR.hideSoftInputFromWindow(contraseniaRegistro.getWindowToken(), 0);
        }

        if (registrer) {

            String usuarioShare = LocalStorage.GetLocalData(MainActivity.this, LocalDictionary.USER);
            String contraseñiaShare = LocalStorage.GetLocalData(MainActivity.this, LocalDictionary.PASSWORD);

            String usuarioEditLogin = tilUserLogin.getEditText().getText().toString();
            String contraseniaEditLogin = tilPasswordLogin.getEditText().getText().toString();

            if (!usuarioEditLogin.equalsIgnoreCase(usuarioShare) && contraseniaEditLogin.equalsIgnoreCase(contraseñiaShare)) {
                tilUserLogin.setError("Usuario no encontrado");
                tilUserLogin.setAnimation(shakeEt);
                iniciar.setAnimation(shake);
            } else {

            }

            if (!contraseniaEditLogin.equalsIgnoreCase(contraseñiaShare) && usuarioEditLogin.equalsIgnoreCase(usuarioShare)) {
                tilPasswordLogin.setError("Contraseña no valida");
                tilPasswordLogin.setAnimation(shakeEt);
                iniciar.setAnimation(shake);
            } else {

            }

            if (!usuarioEditLogin.equalsIgnoreCase(usuarioShare) && !contraseniaEditLogin.equalsIgnoreCase(contraseñiaShare)) {
                tilUserLogin.setError("Usuario no encontrado");
                tilPasswordLogin.setError("Contraseña no valida");
                tilUserLogin.setAnimation(shakeEt);
                tilPasswordLogin.setAnimation(shakeEt);
                iniciar.setAnimation(shake);
            } else {
                //iniciar.setText("");
                //v.startAnimation(slide_up);

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
                        /*login.setVisibility(View.INVISIBLE);
                        bar.setVisibility(View.VISIBLE);*/
                    }
                });
                anim.start();
            }
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(contraseniaLogin.getWindowToken(), 0);
            Intent i = new Intent(MainActivity.this, Container.class);
            i.putExtra("init", "1");
            startActivity(i);
        }
    }

    public void Watches () {
        Boolean registrer = getSharedPreferences("DICTIONARY_FILE", MODE_PRIVATE).getBoolean("Registrer", false);

        if (!registrer) {

            usuarioRegistro.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    tilName.setError(null);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            contraseniaRegistro.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    tilPassword.setError(null);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            editCorreo.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    tilEmail.setError(null);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }

        if (registrer) {

            usuariologin.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    tilUserLogin.setError(null);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            contraseniaLogin.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    tilPasswordLogin.setError(null);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
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

    private boolean esCorreoValido(String correo) {
        if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            tilEmail.setError("Correo electrónico inválido");
            return false;
        } else {
            tilEmail.setError(null);
        }

        return true;
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}
