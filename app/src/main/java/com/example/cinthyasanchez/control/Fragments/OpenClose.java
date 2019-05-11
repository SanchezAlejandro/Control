package com.example.cinthyasanchez.control.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cinthyasanchez.control.AyudaControl;
import com.example.cinthyasanchez.control.LocalDictionary;
import com.example.cinthyasanchez.control.LocalGeneral;
import com.example.cinthyasanchez.control.LocalStorage;
import com.example.cinthyasanchez.control.R;
import com.example.cinthyasanchez.control.WebViewActivity;
import com.example.cinthyasanchez.control.WebViewActivityCerrar;

public class OpenClose extends Fragment implements View.OnClickListener {

    public Button abre;
    public RelativeLayout imagenCerrado, todoAC;
    WebView u;
    ImageView candado;
    TextView text;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_open_close, container, false);

        todoAC = view.findViewById(R.id.todoAbreCierra);
        abre = view.findViewById(R.id.botonAbrirCerrar);
        imagenCerrado = view.findViewById(R.id.relativeLayoutCerrado);
        u = view.findViewById(R.id.web);
        candado = view.findViewById(R.id.imagencerrado);
        text = view.findViewById(R.id.textViewCerrado);

        abre.setOnClickListener(this);
        abre.setBackgroundResource(LocalGeneral.SetBackgroundButtons(4));

        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_salir:
                LocalGeneral localGeneral = new LocalGeneral(getActivity());
                localGeneral.muestraDialog();
                break;
            case R.id.help:
                startActivity(new Intent(getContext(), AyudaControl.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.botonAbrirCerrar:
                if (abre.getText().toString().equalsIgnoreCase("ABRIR")) {
                    abre.setText("CERRAR");
                    candado.setImageResource(R.drawable.abierto);
                    text.setText("Abierto");
                    startActivity(new Intent(getContext(), WebViewActivity.class));
                } else {
                    abre.setText("ABRIR");
                    candado.setImageResource(R.drawable.cerrado);
                    text.setText("Cerrado");
                    startActivity(new Intent(getContext(), WebViewActivityCerrar.class));
                }
                break;
        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
