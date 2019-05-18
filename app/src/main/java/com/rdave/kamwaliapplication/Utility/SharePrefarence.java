package com.rdave.kamwaliapplication.Utility;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePrefarence {
    private static SharePrefarence mInstance;
    private static Context mctx;
    private static final String SHARE_PRE_NAME = "BAI";
    private static final String CHACKLOGIN = "UserName";
    private static final String LOGINAS = "LoginAs";
    private static final String UserName = "User_Name";
    private static final String Emailid = "email_id";
    private static final String User_Id = "user_id";
    private static final String Password = "password";
    private static final String MobileNo = "mobileno";
    public SharePrefarence(Context context) {
        mctx = context;
    }
    public static SharePrefarence getmInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharePrefarence(context);
        }
        return mInstance;
    }

    public boolean SeTLogined(String  Chack) {
        SharedPreferences sharedPreferences = mctx.getSharedPreferences(SHARE_PRE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(CHACKLOGIN, Chack);
        editor.apply();
        return true;
    }


    public String getLogined() {
        SharedPreferences sharedPreferences = mctx.getSharedPreferences(SHARE_PRE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(CHACKLOGIN, "0");
    }
    public boolean setLogined(String  Chack) {
        SharedPreferences sharedPreferences = mctx.getSharedPreferences(SHARE_PRE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(CHACKLOGIN, Chack);
        editor.apply();
        return true;
    }

    public boolean SeTLoginAs(String  Chack) {
        SharedPreferences sharedPreferences = mctx.getSharedPreferences(SHARE_PRE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(LOGINAS, Chack);
        editor.apply();
        return true;
    }


    public String getLoginAs() {
        SharedPreferences sharedPreferences = mctx.getSharedPreferences(SHARE_PRE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(LOGINAS, "0");
    }
    public boolean SeTUserName(String  Chack) {
        SharedPreferences sharedPreferences = mctx.getSharedPreferences(SHARE_PRE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(UserName, Chack);
        editor.apply();
        return true;
    }


    public String getUserName() {
        SharedPreferences sharedPreferences = mctx.getSharedPreferences(SHARE_PRE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(UserName, "0");
    }
    public boolean SeTEmailid(String  Chack) {
        SharedPreferences sharedPreferences = mctx.getSharedPreferences(SHARE_PRE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Emailid, Chack);
        editor.apply();
        return true;
    }


    public String getEmailid() {
        SharedPreferences sharedPreferences = mctx.getSharedPreferences(SHARE_PRE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(Emailid, "0");
    }


    public boolean setUser_ID(String  UserID) {
        SharedPreferences sharedPreferences = mctx.getSharedPreferences(SHARE_PRE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(User_Id,   UserID);
        editor.apply();
        return true;
    }


    public String getUser_Id() {
        SharedPreferences sharedPreferences = mctx.getSharedPreferences(SHARE_PRE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(User_Id, "0");
    }

    public boolean setPassword(String  aPassword) {
        SharedPreferences sharedPreferences = mctx.getSharedPreferences(SHARE_PRE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Password,   aPassword);
        editor.apply();
        return true;
    }


    public String getPassword() {
        SharedPreferences sharedPreferences = mctx.getSharedPreferences(SHARE_PRE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(Password, "");
    }

    public boolean setMobileNo(String  aMobileNo) {
        SharedPreferences sharedPreferences = mctx.getSharedPreferences(SHARE_PRE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(MobileNo,   aMobileNo);
        editor.apply();
        return true;
    }


    public String getMobile() {
        SharedPreferences sharedPreferences = mctx.getSharedPreferences(SHARE_PRE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(MobileNo, "");
    }




}
