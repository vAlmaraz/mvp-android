package com.valmaraz.mvp.view;

import android.content.Context;

/**
 * Created by Víctor Almaraz on 16/04/2016.
 * http://www.valmaraz.com
 */
public interface BaseView {
    Context getContext();
    void showTemporaryMessage(String message);
}
