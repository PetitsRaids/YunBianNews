package com.petits_raids.yunbiannews.api;

public class GuokrApi {

    public static final String GUOKR_BASE = "https://www.guokr.com/apis/minisite/article.json?retrieve_type=by_channel&channel_key=";

    public static final int HOT = 0;

    public static final int FRONTIER = 1;

    public static final int VISUAL = 2;

    public static String getURL(int type) {
        String url = null;
        switch (type) {
            case HOT:
                url = GUOKR_BASE + "hot";
                break;
            case FRONTIER:
                url = GUOKR_BASE + "frontier";
                break;
            case VISUAL:
                url = GUOKR_BASE + "visual";
            default:
                break;
        }
        return url;
    }
}
