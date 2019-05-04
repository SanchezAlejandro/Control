package com.example.cinthyasanchez.control;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;

import static android.content.Context.MODE_PRIVATE;

public class LocalStorage {

    public static String GetLocalData(Context context, String id) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(LocalDictionary.DICTIONARY_FILE, MODE_PRIVATE);
        return sharedPreferences.getString(id, null);
    }

    public static void SetLocalData(Context context, String id, String value) {
        SharedPreferences.Editor shpEditor = context.getSharedPreferences(LocalDictionary.DICTIONARY_FILE, MODE_PRIVATE).edit();
        shpEditor.putString(id, value);
        shpEditor.apply();
    }

    @SuppressLint("ResourceType")
    public static int SetBackground (Context context, int i) {
        int color = 0;
        switch (i) {
            case 0:
                color = Color.parseColor(context.getResources().getString(R.color.amarilloPastel));
                break;
            case 1:
                color = Color.parseColor(context.getResources().getString(R.color.ambarPastel));
                break;
            case 2:
                color = Color.parseColor(context.getResources().getString(R.color.azulPastel));
                break;
            case 3:
                color = Color.parseColor(context.getResources().getString(R.color.azulGris));
                break;
            case 4:
                color = Color.parseColor(context.getResources().getString(R.color.blanco));
                break;
            case 5:
                color = Color.parseColor(context.getResources().getString(R.color.gris));
                break;
            case 6:
                color = Color.parseColor(context.getResources().getString(R.color.indigoPastel));
                break;
            case 7:
                color = Color.parseColor(context.getResources().getString(R.color.limaPastel));
                break;
            case 8:
                color = Color.parseColor(context.getResources().getString(R.color.purpuraPastel));
                break;
            case 9:
                color = Color.parseColor(context.getResources().getString(R.color.rosaPastel));
                break;
            case 10:
                color = Color.parseColor(context.getResources().getString(R.color.verdeAzuladoPastel));
                break;
            case 11:
                color = Color.parseColor(context.getResources().getString(R.color.verdePastel));
                break;
            case 12:
                color = Color.parseColor(context.getResources().getString(R.color.verdePastelDos));
                break;
        }

        return color;
    }

    public static int SetBackgroundButtons (int iBotones) {
        int drawable = 0;
        switch (iBotones) {
            case 0:
                drawable = R.drawable.ripple_amarillo;
                break;
            case 1:
                drawable = R.drawable.ripple_ambar;
                break;
            case 2:
                drawable = R.drawable.ripple_azul;
                break;
            case 3:
                drawable = R.drawable.ripple_azul_gris;
                break;
            case 4:
                drawable = R.drawable.ripple;
                break;
            case 5:
                drawable = R.drawable.ripple_gris;
                break;
            case 6:
                drawable = R.drawable.ripple_indigo;
                break;
            case 7:
                drawable = R.drawable.ripple_lima;
                break;
            case 8:
                drawable = R.drawable.ripple_purpura;
                break;
            case 9:
                drawable = R.drawable.ripple_rosa;
                break;
            case 10:
                drawable = R.drawable.ripple_verdeazul;
                break;
            case 11:
                drawable = R.drawable.ripple_verdementa;
                break;
            case 12:
                drawable = R.drawable.ripple_verde;
                break;
        }

        return drawable;
    }
}
