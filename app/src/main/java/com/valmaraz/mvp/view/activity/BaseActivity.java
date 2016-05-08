package com.valmaraz.mvp.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.valmaraz.mvp.Navigator;

/**
 * Created by Víctor Almaraz on 16/04/2016.
 * http://www.valmaraz.com
 */
public class BaseActivity extends AppCompatActivity {

    protected Navigator navigator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navigator = new Navigator();
    }
}
