package com.petits_raids.yunbiannews.support.utils;

import com.petits_raids.yunbiannews.api.GuokrApi;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class NetworkUtil {

    private static OkHttpClient client = new OkHttpClient();

    public static Call askForNews(String url) {
        Request request = new Request.Builder().url(url).build();
        return client.newCall(request);
    }

    public static Call askForGuoKr() {
        Request request = new Request.Builder().url(GuokrApi.GUOKR_HOT).build();
        return client.newCall(request);
    }

//    interface GuokrService {
//        @GET("article.json")
//        retrofit2.Call<GuokrResult> getGuokr(@Query("retrieve_type") String channel, @Query("channel_key") String type);
//    }

//    public static String Http() {
//        HttpURLConnection connection = null;
//        BufferedReader reader = null;
//        StringBuilder builder = null;
//        try {
//            URL url = new URL(GuokrApi.GUOKR_HOT);
//            connection = (HttpURLConnection) url.openConnection();
//            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            String var;
//            builder = new StringBuilder();
//            while ((var = reader.readLine()) != null) {
//                builder.append(var);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (connection != null) {
//                connection.disconnect();
//            }
//            try {
//                if (reader != null) {
//                    reader.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return builder.toString();
//    }
}
