package com.valmaraz.mvp.view;

import android.app.Application;

import com.valmaraz.mvp.Environment;

/**
 * Created by Victor on 19/04/2016.
 * http://www.valmaraz.com
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Environment.configure();
    }
}
