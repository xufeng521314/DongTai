package com.example.biyingxiangmu.base;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity <P extends IPresenter> extends AppCompatActivity implements IBaseView{

    protected P presenter;
    Unbinder unbinder;
    protected Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        context=this;
        unbinder = ButterKnife.bind(this);
        initView();
        presenter=initPresenter();
        if (presenter!=null){
            presenter.attachView(this);
        }
        initData();
    }

    protected abstract void initData();

    protected abstract P initPresenter();

    protected abstract void initView();

    protected abstract int getLayout();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter!=null){
            presenter.detachView();
        }
        if(unbinder != null){
            unbinder.unbind();
        }
    }
}
