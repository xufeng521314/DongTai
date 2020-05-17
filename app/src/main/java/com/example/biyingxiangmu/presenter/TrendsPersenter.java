package com.example.biyingxiangmu.presenter;


import com.example.biyingxiangmu.base.BasePresenter;
import com.example.biyingxiangmu.bean.PublishTrendsBean;
import com.example.biyingxiangmu.common.CommonSubscriber;
import com.example.biyingxiangmu.constract.TrendsStract;
import com.example.biyingxiangmu.model.HttpManager;
import com.example.biyingxiangmu.utils.RxUtils;

public class TrendsPersenter extends BasePresenter<TrendsStract.View> implements TrendsStract.Persenter {
    @Override
    public void sendTrends(String content, String resources) {
        addSubscribe(HttpManager.getInstance().getChatApi().sendTrends(content,resources)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<PublishTrendsBean>(mView) {
                    @Override
                    public void onNext(PublishTrendsBean result) {
                        mView.sendTrendsReturn(result);
                    }
                }));
    }
}
