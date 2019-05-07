package com.example.cinthyasanchez.control;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class RecuperarCuenta extends AppCompatActivity implements View.OnClickListener{

    EditText correoR;
    Button enviarCorreo;
    String correoDeLaApp = "controlDePuerta@gmail.com";
    String contraseñaDeLaApp = "control1234";

    javax.mail.Session sesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_recuperar_cuenta);
        setContentView(R.layout.layout_app_bar);
        overridePendingTransition(R.anim.left, R.anim.left_off);

        //android.support.v7.widget.Toolbar bar = (android.support.v7.widget.Toolbar) findViewById(R.id.bar);
        //setSupportActionBar(bar);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /*bar.setTitle("Recuperar cuenta");

        correoR = findViewById(R.id.editTextCorreo);
        enviarCorreo = findViewById(R.id.ButtonEnviarCorreo);

        enviarCorreo.setOnClickListener(this);

        ((AppCompatActivity) Objects.requireNonNull(RecuperarCuenta.this)).setSupportActionBar(bar);
        Objects.requireNonNull(((AppCompatActivity) RecuperarCuenta.this).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(((AppCompatActivity) RecuperarCuenta.this).getSupportActionBar()).setDisplayShowHomeEnabled(true);
        bar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.right,R.anim.right_off);
            }
        });*/
    }

    public void muestraDialogErrorCorreo() {
        Dialog dialog = null;
        dialog = new Dialog(this,R.style.Theme_Dialog_Translucent);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_error_de_correo);

        final Dialog finalDialog = dialog;
        ((TextView) dialog.findViewById(R.id.text_ok)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finalDialog.dismiss();
            }
        });

        dialog.show();
    }

    public void muestraDialogCorreoEnviado() {
        Dialog dialog = null;
        dialog = new Dialog(this,R.style.Theme_Dialog_Translucent);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_revisar_correo);

        final Dialog finalDialog = dialog;
        ((TextView) dialog.findViewById(R.id.text_ok_revisar)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finalDialog.dismiss();
                finish();
            }
        });

        dialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_salir:
                LocalGeneral localGeneral = new LocalGeneral(this);
                localGeneral.muestraDialog(true);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ButtonEnviarCorreo:
                SharedPreferences preferencias = getSharedPreferences("misPreferencias", Context.MODE_PRIVATE);
                String correoGuardado = preferencias.getString("correo", "correo@gmail.com");
                String usuarioGuardado = preferencias.getString("usuario", "usuario");
                String contraseniaGuardada = preferencias.getString("contrasenia", "contrasenia");
                String correoIngresado = correoR.getText().toString();

                if (correoGuardado.equalsIgnoreCase(correoIngresado)){
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);

                    Properties properties = new Properties();
                    properties.put("mail.smtp.host","smtp.googlemail.com");
                    properties.put("mail.smtp.socketFactory.port","465");
                    properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
                    properties.put("mail.smtp.auth","true");
                    properties.put("mail.smtp.port","465");

                    try {
                        sesion = javax.mail.Session.getDefaultInstance(properties, new Authenticator() {
                            @Override
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(correoDeLaApp, contraseñaDeLaApp);
                            }
                        });

                        String contenido = "Hola " + usuarioGuardado + ", gracias por seguir usando nuestro servicio. Aquí tienes tu contraseña: " + contraseniaGuardada;

                        if(sesion!=null){
                            Message mensaje = new MimeMessage(sesion);
                            mensaje.setFrom(new InternetAddress(contraseñaDeLaApp));
                            mensaje.setSubject("Control de Puerta");
                            ((MimeMessage) mensaje).setRecipients(Message.RecipientType.TO, InternetAddress.parse(correoIngresado));
                            mensaje.setContent(contenido, "text/html; charset=utf-8");
                            Transport.send(mensaje);
                            Toast.makeText(this, "Correo enviado", Toast.LENGTH_SHORT).show();
                            muestraDialogCorreoEnviado();
                        }

                    } catch (Exception e){

                    }
                } else {
                    muestraDialogErrorCorreo();
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.finish();
        overridePendingTransition(R.anim.right,R.anim.right_off);
    }
}
