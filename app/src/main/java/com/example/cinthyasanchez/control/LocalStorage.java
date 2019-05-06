package com.example.cinthyasanchez.control;

import android.content.Context;
import android.content.SharedPreferences;

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
}
