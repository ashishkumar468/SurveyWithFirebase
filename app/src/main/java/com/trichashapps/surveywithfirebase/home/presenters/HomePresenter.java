package com.trichashapps.surveywithfirebase.home.presenters;

import com.trichashapps.surveywithfirebase.home.presenters.interfaces.IHomeView;

/**
 * Created by Ashish on 09/10/17.
 */

public class HomePresenter extends BasePresenter {
    private IHomeView view;

    @Override
    public void attachView(IHomeView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }
}
