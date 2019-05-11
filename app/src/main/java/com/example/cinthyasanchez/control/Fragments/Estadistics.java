package com.example.cinthyasanchez.control.Fragments;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cinthyasanchez.control.LocalDictionary;
import com.example.cinthyasanchez.control.LocalStorage;
import com.example.cinthyasanchez.control.R;

public class Estadistics extends Fragment {

    RadioButton barras, histograma;
    TextView aqui;
    WebView graficas;
    public RelativeLayout gBarras, gPoligono, todoE;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_estadistics, container, false);

        barras = view.findViewById(R.id.radioButtonGraficaBarras);
        histograma = view.findViewById(R.id.radioButtonGraficaHistograma);
        gBarras = view.findViewById(R.id.RelativeLayoutGraficaBarras);
        gPoligono = view.findViewById(R.id.RelativeLayoutGraficaHistograma);
        graficas = view.findViewById(R.id.webViews);
        aqui = view.findViewById(R.id.tvaqui);
        todoE = view.findViewById(R.id.todoEstadisticas);
        //todoE.setBackgroundColor(Integer.parseInt(LocalStorage.GetLocalData(getContext(), LocalDictionary.BACKGROUND)));

        barras.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                aqui.setVisibility(View.INVISIBLE);
                graficas.getSettings().setJavaScriptEnabled(true);
                graficas.setWebViewClient(new WebViewClient());
                graficas.loadUrl("https://analytics.zoho.com/open-view/1881418000000002070");
            }
        });
        histograma.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                aqui.setVisibility(View.INVISIBLE);
                graficas.getSettings().setJavaScriptEnabled(true);
                graficas.setWebViewClient(new WebViewClient());
                graficas.loadUrl("https://analytics.zoho.com/open-view/1881418000000002119");
            }
        });

        return view;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
