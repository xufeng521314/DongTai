package com.example.biyingxiangmu.presenter;

import com.example.biyingxiangmu.base.BasePresenter;
import com.example.biyingxiangmu.bean.DetailsUpdateBean;
import com.example.biyingxiangmu.bean.UserDetailsBean;
import com.example.biyingxiangmu.common.CommonSubscriber;
import com.example.biyingxiangmu.constract.OwnConstract;
import com.example.biyingxiangmu.model.HttpManager;
import com.example.biyingxiangmu.utils.RxUtils;

import java.util.Map;

public class OwnPresenter extends BasePresenter<OwnConstract.DetailsView> implements OwnConstract.DetailsPresenter{
    @Override
    public void getDetails() {
        addSubscribe(HttpManager.getInstance().getChatApi().getUserDetails()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<UserDetailsBean>(mView) {
                    @Override
                    public void onNext(UserDetailsBean result) {
                        mView.getDetailsReturn(result);
                    }
                }));
    }

    @Override
    public void updateDetails(Map<String, String> map) {
        addSubscribe(HttpManager.getInstance().getChatApi().updateUserDetails(map)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<DetailsUpdateBean>(mView) {
                    @Override
                    public void onNext(DetailsUpdateBean result) {
                        mView.updateDetailsReturn(result);
                    }
                }));
    }
}
