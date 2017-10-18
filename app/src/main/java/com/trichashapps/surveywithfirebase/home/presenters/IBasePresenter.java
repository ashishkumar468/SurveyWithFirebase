package com.trichashapps.surveywithfirebase.home.presenters;

/**
 * Created by Ashish on 09/10/17.
 */

public interface IBasePresenter<T> {

    void attachView(T view);

    void detachView();
}
