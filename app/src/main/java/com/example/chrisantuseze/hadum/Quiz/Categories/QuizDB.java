package com.example.chrisantuseze.hadum.Quiz.Categories;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by CHRISANTUS EZE on 11/11/2017.
 */

public class QuizDB {
    private static final String PREF_NAME = "quiz";
    private static final String KEY_SPORTS = "sports";
    private static final String KEY_POLITICS = "politics";
    private static final String KEY_GEO = "geo";
    private static final String KEY_RELIGION = "religion";
    private static final String KEY_TECH = "tech";
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context context;

    public QuizDB(Context context) {
        this.context = context;
        prefs = context.getSharedPreferences(PREF_NAME, context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void setKeySports(int sports){
        editor.putInt(KEY_SPORTS, sports);
        editor.apply();
    }

    public void setKeyPolitics(int politics){
        editor.putInt(KEY_POLITICS, politics);
        editor.apply();
    }
    public void setKeyGeo(int geo){
        editor.putInt(KEY_GEO, geo);
        editor.apply();
    }
    public void setKeyReligion(int religion){
        editor.putInt(KEY_RELIGION, religion);
        editor.apply();
    }
    public void setKeyTech(int tech){
        editor.putInt(KEY_TECH, tech);
        editor.apply();
    }

    public void clearQuizInfo(){
        editor.clear();
        editor.commit();
    }

    public int getKeySports(){return prefs.getInt(KEY_SPORTS, 0);}

    public int getKeyPolitics(){return prefs.getInt(KEY_POLITICS, 0);}

    public int getKeyGeo(){return prefs.getInt(KEY_GEO, 0);}

    public int getKeyReligion(){return prefs.getInt(KEY_RELIGION, 0);}

    public int getKeyTech(){return prefs.getInt(KEY_TECH, 0);}
}
