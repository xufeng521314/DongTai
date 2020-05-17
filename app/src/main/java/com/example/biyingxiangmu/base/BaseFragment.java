package com.example.biyingxiangmu.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment <P extends IPresenter> extends Fragment implements IBaseView{

    protected Context context;
    protected P presenter;
    Unbinder unbinder;
    protected Activity activity;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(getLayout(),null);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        activity=getActivity();
        unbinder = ButterKnife.bind(this,view);
        presenter=initPresenter();
        if (presenter!=null){
            presenter.attachView(this);
        }
        initView();
        initData();
    }

    protected abstract void initData();

    protected abstract P initPresenter();

    protected abstract void initView();

    protected abstract int getLayout();

    @Override
    public void showToast(String str) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter!=null){
            presenter.detachView();
        }
        if(unbinder != null){
            unbinder.unbind();
        }
    }
}
