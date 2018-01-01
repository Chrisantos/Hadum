package com.example.chrisantuseze.hadum.Assistant.Result;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by CHRISANTUS EZE on 30/12/2017.
 */

public class ResultSharedPreference  {
    private static final String KEY_CGPA = "cgpa";
    private static final String KEY_TOTAL_UNITS = "units";
    private static final String KEY_TOTAL_AGGREGATE = "aggregate";
    private static final String PREF_NAME = "calculator";

    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context context;

    public ResultSharedPreference(Context context) {
        this.context = context;
        prefs = context.getSharedPreferences(PREF_NAME, context.MODE_PRIVATE);
        editor = prefs.edit();
    }
    public void setKeyCgpa(String cgpa){
        editor.putString(KEY_CGPA, cgpa);
        editor.apply();
    }
    public void setKeyTotalUnits(String units){
        editor.putString(KEY_TOTAL_UNITS, units);
        editor.apply();
    }
    public void setKeyTotalAggregate(String aggregate){
        editor.putString(KEY_TOTAL_AGGREGATE, aggregate);
        editor.apply();
    }
    public String getKeyCgpa(){
        return prefs.getString(KEY_CGPA, null);
    }
    public String getKeyTotalUnits(){
        return prefs.getString(KEY_TOTAL_UNITS, null);
    }
    public String getKeyTotalAggregate(){
        return prefs.getString(KEY_TOTAL_AGGREGATE, null);
    }
}
