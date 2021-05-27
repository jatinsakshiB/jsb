package com.jsb.jsblibrary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.provider.Settings;
import android.text.Html;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Base64;
import android.widget.TextView;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;

public class Jsb {



    public static SharedPreferences getSharedPreferences(Context activity){
        return activity.getSharedPreferences("com.pufferfish.ilu", Context.MODE_PRIVATE);
    }



    public static final SimpleDateFormat dateFormatDateTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    public static final SimpleDateFormat dateFormatDate = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat dateFormatTime = new SimpleDateFormat("hh:mm:ss");


    public static String getAndroidId(Context activity){
      return Settings.Secure.getString(activity.getContentResolver(), Settings.Secure.ANDROID_ID);
    }


    public static void logout(Activity activity, Class c){
        getSharedPreferences(activity).edit().clear().apply();
        Intent intent = new Intent(activity, c);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        activity.startActivity(intent);
        activity.finish();
    }


    public static void logout(Context activity){
        getSharedPreferences(activity).edit().clear().apply();
    }


    public static void saveValue(Context activity, String key, String value){
        getSharedPreferences(activity).edit().putString(key, value).apply();
    }

    public static void saveValue(Context activity, String key, int value){
        getSharedPreferences(activity).edit().putInt(key, value).apply();
    }

    public static void saveValue(Context activity, String key, boolean value){
        getSharedPreferences(activity).edit().putBoolean(key, value).apply();
    }

    public static void saveValue(Context activity, String key, float value){
        getSharedPreferences(activity).edit().putFloat(key, value).apply();
    }

    public static void saveValue(Context activity, String key, long value){
        getSharedPreferences(activity).edit().putLong(key, value).apply();
    }

    public static int getStringResourceIdByName(Context context, String aString) {
        return context.getResources().getIdentifier(aString, "string", context.getPackageName());
    }
    public static String getStringResourceByName(Context context, String aString) {
        return context.getResources().getString(context.getResources().getIdentifier(aString, "string", context.getPackageName()));
    }


    public static String stringToBase64(String text){
        byte[] data = text.getBytes(StandardCharsets.UTF_8);
        return Base64.encodeToString(data, Base64.DEFAULT);
    }

    public static String base64ToString(String base64) {
        byte[] data = Base64.decode(base64, Base64.DEFAULT);
        return new String(data, StandardCharsets.UTF_8);
    }

    public static String stringToReverse(String s){
        StringBuffer buffer = new StringBuffer(s);
        buffer.reverse();
        return buffer.toString();
    }

    public static void setHtmlTextview(TextView textview, String htmlText){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textview.setText(Html.fromHtml(htmlText, Html.FROM_HTML_MODE_COMPACT));
        } else {
            textview.setText(Html.fromHtml(htmlText));
        }
    }

    public static SpannableStringBuilder changeTextColor(String text, int s, int e, int color){
        SpannableStringBuilder ssb = new SpannableStringBuilder(text);
        ssb.setSpan(new ForegroundColorSpan(color), s, e, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ssb;
    }

}
