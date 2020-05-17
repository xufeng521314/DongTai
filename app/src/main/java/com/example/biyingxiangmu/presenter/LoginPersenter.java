package com.example.biyingxiangmu.presenter;


import com.example.biyingxiangmu.base.BasePresenter;
import com.example.biyingxiangmu.bean.UserInfoBean;
import com.example.biyingxiangmu.common.CommonSubscriber;
import com.example.biyingxiangmu.constract.LoginConstract;
import com.example.biyingxiangmu.model.HttpManager;
import com.example.biyingxiangmu.utils.RxUtils;

public class LoginPersenter extends BasePresenter<LoginConstract.View> implements LoginConstract.Presenter {

    @Override
    public void login(String username, String password) {
        addSubscribe(HttpManager.getInstance().getChatApi().login(username,password)
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new CommonSubscriber<UserInfoBean>(mView) {
            @Override
            public void onNext(UserInfoBean userInfoBean) {
                mView.loginReturn(userInfoBean);
            }
        }));
    }
}
