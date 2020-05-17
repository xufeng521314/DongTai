package com.example.biyingxiangmu.base;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BasePresenter<V extends IBaseView> implements IPresenter<V> {

    protected V mView;
    //对View层进行弱引用化处理
    protected WeakReference<V> weakReference;

    protected CompositeDisposable compositeDisposable;

    @Override
    public void attachView(V view) {
        weakReference=new WeakReference<>(view);
        mView=weakReference.get();
    }

    //解绑观察者与被观察者
    protected void unSubscribe(){
        if (compositeDisposable!=null){
            compositeDisposable.clear();
        }
    }

    //添加观察者与被观察者
    protected void addSubscribe(Disposable disposable){
        if (compositeDisposable==null)compositeDisposable=new CompositeDisposable();
        compositeDisposable.add(disposable);
    }

    @Override
    public void detachView() {
        this.mView=null;
            unSubscribe();
    }
}
