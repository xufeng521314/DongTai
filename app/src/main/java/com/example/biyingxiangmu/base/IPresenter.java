package com.example.biyingxiangmu.base;

public interface IPresenter <V extends IBaseView>{

    void attachView(V view);

    void detachView();

}
