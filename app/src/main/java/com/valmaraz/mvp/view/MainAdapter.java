package com.valmaraz.mvp.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.valmaraz.mvp.R;

import java.util.List;

/**
 * Created by Victor on 13/04/2016.
 * http://www.valmaraz.com
 */
public class MainAdapter extends RecyclerView.Adapter<MainHolder> {

    private List<String> data;

    public MainAdapter(List<String> data) {
        this.data = data;
    }

    @Override
    public MainHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_main, parent, false);
        MainHolder vh = new MainHolder((TextView) v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MainHolder holder, int position) {
        holder.mTextView.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
