package com.valmaraz.mvp;

/**
 * Created by Victor on 19/04/2016.
 * http://www.valmaraz.com
 */
public class Environment {

    public static long CACHE_MAX_LIFETIME_IN_MILLIS = 5 * 1000;

    // Default values (PRODUCTION)
    public static boolean showLog = false;
    public static boolean useSnackbar = true;
    public static String apiUrl = "http://api.openweathermap.org";
    public static String apiBaseUrl = "http://api.openweathermap.org/data/2.5/";
    public static String apiKey = "dc6ac0462871834b4e77f8b3e5f98d63";

    private static Type type = Type.DEVELOP;

    public static void configure() {
        switch (type) {
            case DEVELOP:
                showLog = true;
                useSnackbar = true;
                apiUrl = "http://api.openweathermap.org";
                apiBaseUrl = "http://api.openweathermap.org/data/2.5/";
                apiKey = "dc6ac0462871834b4e77f8b3e5f98d63";
                break;
            case PREPRODUCTION:
                showLog = true;
                useSnackbar = true;
                apiUrl = "http://api.openweathermap.org";
                apiBaseUrl = "http://api.openweathermap.org/data/2.5/";
                apiKey = "dc6ac0462871834b4e77f8b3e5f98d63";
                break;
            case PRODUCTION:
                showLog = false;
                useSnackbar = true;
                apiUrl = "http://api.openweathermap.org";
                apiBaseUrl = "http://api.openweathermap.org/data/2.5/";
                apiKey = "dc6ac0462871834b4e77f8b3e5f98d63";
                break;
        }
    }

    public enum Type {
        DEVELOP,
        PREPRODUCTION,
        PRODUCTION
    }
}
