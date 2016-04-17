package com.valmaraz.mvp.view.component;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Víctor Almaraz on 17/04/2016.
 * http://www.valmaraz.com
 */
public class ImageLoader {

    public static void loadImage(Context context, String url, ImageView iv) {
        Picasso.with(context).load(url).into(iv);
    }
}
