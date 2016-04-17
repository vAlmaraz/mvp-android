package com.valmaraz.mvp;

/**
 * Created by Víctor Almaraz on 16/04/2016.
 * http://www.valmaraz.com
 */
public class Config {

    private enum Environment {
        PRODUCTION,
        PREPRODUCTION,
        DEVELOP
    }

    private static final Environment environment = Environment.DEVELOP;

    public boolean showLog = true;
    public boolean logRequests = true;
    public boolean useSnackbar = true;

    public String apiUrl = "http://api.openweathermap.org";
    public String apiBaseUrl = "http://api.openweathermap.org/data/2.5/";
    public String apiKey = "dc6ac0462871834b4e77f8b3e5f98d63";

    private static Config instance = null;

    protected Config(Environment environment) {
        if (environment == Environment.PRODUCTION) {
            showLog = false;
            logRequests = false;
            useSnackbar = true;
            apiUrl = "http://api.openweathermap.org";
            apiBaseUrl = "http://api.openweathermap.org/data/2.5/";
            apiKey = "dc6ac0462871834b4e77f8b3e5f98d63";
        } else if (environment == Environment.PREPRODUCTION) {
            showLog = true;
            logRequests = true;
            useSnackbar = true;
            apiUrl = "http://api.openweathermap.org";
            apiBaseUrl = "http://api.openweathermap.org/data/2.5/";
            apiKey = "dc6ac0462871834b4e77f8b3e5f98d63";
        } else {
            showLog = true;
            logRequests = true;
            useSnackbar = true;
            apiUrl = "http://api.openweathermap.org";
            apiBaseUrl = "http://api.openweathermap.org/data/2.5/";
            apiKey = "dc6ac0462871834b4e77f8b3e5f98d63";
        }
    }

    public static Config getInstance() {
        if(instance == null) {
            instance = new Config(Config.environment);
        }
        return instance;
    }
}
