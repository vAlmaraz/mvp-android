package com.valmaraz.mvp.view;

/**
 * Created by Víctor Almaraz on 17/04/2016.
 * http://www.valmaraz.com
 */
public interface LoadingView extends BaseView {
    void showLoading();
    void hideLoading();
    void showRetry();
    void hideRetry();
}
