package com.valmaraz.mvp.view.activity;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.valmaraz.mvp.R;
import com.valmaraz.mvp.model.entity.City;
import com.valmaraz.mvp.presenter.WeatherListPresenter;
import com.valmaraz.mvp.view.WeatherListView;
import com.valmaraz.mvp.view.adapter.MainAdapter;
import com.valmaraz.mvp.view.component.SimpleDividerItemDecoration;
import com.valmaraz.mvp.view.component.UIMessage;
import com.valmaraz.mvp.view.listener.OnRecyclerViewClickListener;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Victor on 13/04/2016.
 * http://www.valmaraz.com
 */
public class WeatherListActivity extends BaseActivity implements WeatherListView, OnRecyclerViewClickListener {

    @Bind(R.id.act_weatherlist_cl)
    CoordinatorLayout coordinatorLayout;
    @Bind(R.id.act_weatherlist_pb)
    ProgressBar progressBar;
    @Bind(R.id.act_weatherlist_bt_retry)
    Button btRetry;
    @Bind(R.id.act_weatherlist_rv)
    RecyclerView recyclerView;

    private WeatherListPresenter presenter;
    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weatherlist);
        ButterKnife.bind(this);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(ContextCompat.getDrawable(this, R.drawable.line_divider)));
        adapter = new MainAdapter(getApplicationContext(), this);
        recyclerView.setAdapter(adapter);

        if (presenter == null) {
            presenter = new WeatherListPresenter(this);
        }

        presenter.initialize();
    }

    @OnClick(R.id.act_weatherlist_bt_retry)
    public void onClick(View v) {
        presenter.initialize();
    }

    @Override
    public void onRecyclerViewClick(Object clickedObject) {
        City city = (City) clickedObject;
        navigator.navigateToWeatherDetails(this, city);
    }

    // =============================================================================
    // VIEW IMPLEMENTATION
    // =============================================================================

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showRetry() {
        btRetry.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRetry() {
        btRetry.setVisibility(View.GONE);
    }

    @Override
    public void showTemporaryMessage(String message) {
        UIMessage.showMessage(coordinatorLayout, message);
    }

    @Override
    public void renderWeatherList(List<City> cities) {
        if (cities != null) {
            adapter.setData(cities);
        }
    }
}
