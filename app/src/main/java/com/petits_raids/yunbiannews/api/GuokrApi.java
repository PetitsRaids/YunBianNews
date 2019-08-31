package com.petits_raids.yunbiannews.api;

public class GuokrApi {

    public static final String GUOKR_BASE = "https://apis.guokr.com/minisite/";
    public static final String PAC = "pac";
    public static final String FACT = "fact";
    public static final String PREDATOR = "predator";
    public static final String BEAUTY = "beauty";

    public static String getURL(int type) {
        switch (type) {
            case 0:
                return PAC;
            case 1:
                return FACT;
            case 2:
                return PREDATOR;
            case 3:
                return BEAUTY;
            default:
                return null;
        }
    }
}
