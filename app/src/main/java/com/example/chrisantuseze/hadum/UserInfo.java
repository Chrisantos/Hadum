package com.example.chrisantuseze.hadum;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by CHRISANTUS EZE on 11/11/2017.
 */

public class UserInfo {
    private static final String PREF_NAME = "userinfo";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_DEPARTMENT = "department";
    private static final String KEY_REGNO = "regno";
    private static final String KEY_LEVEL = "level";
    private static final String KEY_WEBSITE = "website";
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context context;

    public UserInfo(Context context) {
        this.context = context;
        prefs = context.getSharedPreferences(PREF_NAME, context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void setKeyName(String name){
        editor.putString(KEY_NAME, name);
        editor.apply();
    }

    public void setKeyEmail(String email){
        editor.putString(KEY_EMAIL, email);
        editor.apply();
    }
    public void setKeyRegno(String regno){
        editor.putString(KEY_REGNO, regno);
        editor.apply();
    }
    public void setKeyDepartment(String department){
        editor.putString(KEY_DEPARTMENT, department);
        editor.apply();
    }
    public void setKeyLevel(String level){
        editor.putString(KEY_LEVEL, level);
        editor.apply();
    }
    public void setKeyWebsite(String website){
        editor.putString(KEY_WEBSITE, website);
        editor.apply();
    }

    public void clearUserInfo(){
        editor.clear();
        editor.commit();
    }

    public String getKeyName(){return prefs.getString(KEY_NAME, "");}

    public String getKeyEmail(){return prefs.getString(KEY_EMAIL, "");}

    public String getKeyRegno(){return prefs.getString(KEY_REGNO, "");}

    public String getKeyDepartment(){return prefs.getString(KEY_DEPARTMENT, "");}

    public String getKeyLevel(){return prefs.getString(KEY_LEVEL, "");}

    public String getKeyWebsite(){return prefs.getString(KEY_WEBSITE, "");}

}
