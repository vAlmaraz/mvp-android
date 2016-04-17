package com.valmaraz.mvp.view;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Víctor Almaraz on 17/04/2016.
 * http://www.valmaraz.com
 */
public class UIMessage {

    public static void showMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
