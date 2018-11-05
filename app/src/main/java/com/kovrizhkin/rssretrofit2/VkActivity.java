package com.kovrizhkin.rssretrofit2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;

public class VkActivity extends AppCompatActivity {

    private String accessToken;
    private JSONObject targetPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vk);

        VKSdk.login(this, "wall");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {

                Log.e("res", res.toString());
                accessToken = res.accessToken;
                VKRequest request = VKApi.wall().get(VKParameters.from(
                        VKApiConst.OWNER_ID, "-72495085",
                        VKApiConst.COUNT, "1"));

                request.executeWithListener(new VKRequest.VKRequestListener() {
                    @Override
                    public void onComplete(VKResponse response) {

                        JSONObject res = null;
                        try {
                            res = response.json
                                    .getJSONObject("response")
                                    .getJSONArray("items")
                                    .getJSONObject(0);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //Do complete stuff
                    }

                    @Override
                    public void onError(VKError error) {
                        //Do error stuff
                    }

                    @Override
                    public void attemptFailed(VKRequest request, int attemptNumber, int totalAttempts) {
                        //I don't really believe in progress
                    }
                });
                    // Пользователь успешно авторизовался
            }

            @Override
            public void onError(VKError error) {
                // Произошла ошибка авторизации (например, пользователь запретил авторизацию)
            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
