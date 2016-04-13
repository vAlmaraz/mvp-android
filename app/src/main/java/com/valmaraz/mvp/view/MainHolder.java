package com.valmaraz.mvp.view;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

/**
 * Created by Victor on 13/04/2016.
 * http://www.valmaraz.com
 */
public class MainHolder extends RecyclerView.ViewHolder {

    public TextView mTextView;

    public MainHolder(TextView v) {
        super(v);
        mTextView = v;
    }
}
