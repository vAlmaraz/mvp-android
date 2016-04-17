package com.valmaraz.mvp.view.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.valmaraz.mvp.R;
import com.valmaraz.mvp.model.entity.City;
import com.valmaraz.mvp.presenter.MainPresenter;
import com.valmaraz.mvp.view.MainView;
import com.valmaraz.mvp.view.SimpleDividerItemDecoration;
import com.valmaraz.mvp.view.UIMessage;
import com.valmaraz.mvp.view.VerticalSpaceItemDecoration;
import com.valmaraz.mvp.view.adapter.MainAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainView {

    @Bind(R.id.main_rv)
    RecyclerView recyclerView;

    private MainPresenter presenter;
    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(ContextCompat.getDrawable(this, R.drawable.line_divider)));
        adapter = new MainAdapter(getApplicationContext());
        recyclerView.setAdapter(adapter);

        if (presenter == null) {
            presenter = new MainPresenter(this);
        }

        presenter.loadWeatherList();
    }

    @Override
    public void renderWeatherList(List<City> cities) {
        if (cities != null) {
            adapter.setData(cities);
        }
    }

    @Override
    public void showTemporaryMessage(String message) {
        UIMessage.showMessage(this, message);
    }
}
