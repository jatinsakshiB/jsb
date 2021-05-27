package com.jsb.jsblibrary;

import android.app.Activity;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Jsbhttp{

    OkHttpClient httpClient = new OkHttpClient();
    String url;
    RequestBody requestBody;
    Activity activity;


    public static Jsbhttp create(Activity activity, String fileName, RequestBody requestBody){
        return new Jsbhttp(activity, fileName, requestBody);
    }

    public Jsbhttp(Activity activity, String fileName, RequestBody requestBody){
        url = activity.getString(R.string.baseApiUrl)+fileName;
        this.requestBody = requestBody;
        this.activity = activity;
    }

    public void execute(ServerResponse serverResponse){
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                activity.runOnUiThread(() -> serverResponse.onError(e));
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()){
                    String body = response.body().string();
                    try {
                        JSONObject jsonObject = new JSONObject(body);
                        activity.runOnUiThread(() -> {
                            try {
                                serverResponse.onResponse(jsonObject);
                            } catch (JSONException e) {
                                serverResponse.onError(e);
                            }
                        });
                    } catch (JSONException e) {
                        activity.runOnUiThread(() -> serverResponse.onError(e));
                    }
                }else {
                    activity.runOnUiThread(() -> serverResponse.onError(new Exception(response.message())));
                }
            }
        });
    }


    public interface ServerResponse {
        void onError(Exception e);
        void onResponse(JSONObject jsonObject) throws JSONException;
    }


}
