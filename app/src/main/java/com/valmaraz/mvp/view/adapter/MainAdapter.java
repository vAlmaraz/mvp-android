package com.valmaraz.mvp.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.valmaraz.mvp.model.entity.City;
import com.valmaraz.mvp.view.listener.OnHolderClickListener;
import com.valmaraz.mvp.view.listener.OnRecyclerViewClickListener;
import com.valmaraz.mvp.view.holder.CityHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor on 13/04/2016.
 * http://www.valmaraz.com
 */
public class MainAdapter extends RecyclerView.Adapter<CityHolder> implements OnHolderClickListener {

    private Context context;
    private OnRecyclerViewClickListener clickListener;
    private List<City> data;

    public MainAdapter(Context context, OnRecyclerViewClickListener clickListener) {
        this.context = context;
        this.clickListener = clickListener;
        data = new ArrayList<>();
    }

    @Override
    public CityHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(CityHolder.getLayoutId(), parent, false);
        CityHolder vh = new CityHolder(v, this);
        return vh;
    }

    @Override
    public void onBindViewHolder(CityHolder holder, int position) {
        City city = data.get(position);
        holder.render(context, position, city);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onClick(int holderPosition) {
        clickListener.onRecyclerViewClick(data.get(holderPosition));
    }

    public void setData(@NonNull List<City> data) {
        this.data = data;
        notifyDataSetChanged();
    }
}
