package com.valmaraz.mvp.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.valmaraz.mvp.R;
import com.valmaraz.mvp.model.entity.City;
import com.valmaraz.mvp.view.holder.MainHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor on 13/04/2016.
 * http://www.valmaraz.com
 */
public class MainAdapter extends RecyclerView.Adapter<MainHolder> {

    private List<City> data;

    public MainAdapter() {
        data = new ArrayList<>();
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
        City city = data.get(position);
        holder.mTextView.setText(city.name + " " + city.main.temp + "C");
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setData(@NonNull List<City> data) {
        this.data = data;
        notifyDataSetChanged();
    }
}
