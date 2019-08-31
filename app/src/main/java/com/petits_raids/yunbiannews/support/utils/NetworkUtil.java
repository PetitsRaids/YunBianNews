package com.petits_raids.yunbiannews.support.utils;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class NetworkUtil {

    private static OkHttpClient client = new OkHttpClient();

    public static Call askForNews(String url) {
        Request request = new Request.Builder().url(url).build();
        return client.newCall(request);
    }

//    public static Call<GuokrResult> askForGuoKr(int type) {
//        Retrofit retrofit = new Retrofit.Builder().baseUrl(GuokrApi.GUOKR_BASE)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        GuokrService service = retrofit.create(GuokrService.class);
//        return service.getGuokrResult(GuokrApi.PREDATOR);
//    }
}
