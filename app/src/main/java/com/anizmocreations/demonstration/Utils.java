package com.anizmocreations.demonstration;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import com.anizmocreations.demonstration.model.Task;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility functions that are used throughout the app to perform repetitive tasks.
 */
public class Utils {

    public static final String KEY_PREFS = "TASK_LIST";

    /**
     * This method calculates percentage from two given numbers,
     *
     * @param num   the numerator, the top portion of the fraction.
     * @param denom the denominator, the number out of which the numerator is obtained.
     * @return      the percentage rounded off to the integer value.
     */
    public static int getPercentageFromTwoNumbers(int num, int denom) {
        return (int) ((float) num * 100 / (float) denom);
    }

    /**
     *
     *
     * @param list
     * @param activity
     */
    public static void setList(List<Task> list, Activity activity) {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);

        editor.putString(KEY_PREFS, json);
        editor.apply();
    }

    /**
     *
     *
     * @param activity
     * @return
     */
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
