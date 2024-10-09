package com.example.myfirstapp;


import android.content.Context;
import android.content.SharedPreferences;

public class Shared {

    Context context;

    private  static Shared shareData;

    public static final String USER_NAME = "username";
    public static final String USER_EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String CONFIRM_PASSWORD = "Confirm password";

    SharedPreferences sharedPreferences;

    public Shared(Context context){
        this.context = context;

        if(sharedPreferences == null){
            sharedPreferences = context.getSharedPreferences("SigmaML",Context.MODE_PRIVATE );
        }
    }

    // data ko save kra rhe
    public void saveStringData(String key , String Value){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, Value);
        editor.commit();
    }

    // data ko get kr rhe
    public String getStringData(String key){
        return sharedPreferences.getString(key, "");
    }

    public static Shared getInstance(Context context){

        if(shareData == null){
            shareData = new Shared(context);
        }

        return shareData;
    }

}