package com.example.cinthyasanchez.control;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import static javax.mail.internet.InternetAddress.*;

public class RecuperarCuenta extends AppCompatActivity implements View.OnClickListener{

    EditText correoR;
    Button enviarCorreo, okEnviado;
    RelativeLayout mensajeEnviado;

    String correoDeLaApp = "controlDePuerta@gmail.com";
    String contraseñaDeLaApp = "control1234";
    //String yo = "ppjando66@gmail.com";
    //String contenido = "esta es tu contraseña";

    javax.mail.Session sesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_cuenta);

        correoR = findViewById(R.id.editTextCorreo);
        enviarCorreo = findViewById(R.id.ButtonEnviarCorreo);
        mensajeEnviado = findViewById(R.id.RelativeLayoutMensajeDeEnviado);
        okEnviado = findViewById(R.id.BotonOkRevisar);

        enviarCorreo.setOnClickListener(this);
        okEnviado.setOnClickListener(this);
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
                            mensajeEnviado.setVisibility(View.VISIBLE);
                        }

                    } catch (Exception e){

                    }
                } else {
                    AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
                    dialogo1.setTitle("Ups!");
                    dialogo1.setMessage("El correo que ingresaste no es el de tu cuenta, por favor ingresa el correo nuevamente");
                    dialogo1.setCancelable(false);
                    dialogo1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogo1, int id) {
                            correoR.setText("");
                            correoR.requestFocus();
                        }
                    });
                    dialogo1.show();
                }
                break;
            case R.id.BotonOkRevisar:
                mensajeEnviado.setVisibility(View.INVISIBLE);
                finish();
                break;
        }
    }
}
