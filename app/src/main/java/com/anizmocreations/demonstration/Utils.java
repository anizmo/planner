package com.anizmocreations.demonstration;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static final String KEY_PREFS = "TASK_LIST";

    /**
     *
     *
     * @param num
     * @param denom
     * @return
     */
    public static int getPercentageFromTwoNumbers(int num, int denom) {
        return (int) ((float) num * 100 / (float) denom);
    }


    public static <T> void setList(String key, List<T> list, Activity activity) {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);

        editor.putString(key, json);
        editor.apply();
    }

    public static List<Task> getList(Activity activity){
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        List<Task> arrayItems = new ArrayList<>();
        String serializedObject = sharedPref.getString(KEY_PREFS, null);
        if (serializedObject != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Task>>(){}.getType();
            arrayItems = gson.fromJson(serializedObject, type);
        }

        return arrayItems;
    }


}
