package com.example.biyingxiangmu.presenter;


import com.example.biyingxiangmu.base.BasePresenter;
import com.example.biyingxiangmu.bean.TrendsBean;
import com.example.biyingxiangmu.common.CommonSubscriber;
import com.example.biyingxiangmu.constract.TrendsStract;
import com.example.biyingxiangmu.model.HttpManager;
import com.example.biyingxiangmu.utils.RxUtils;

/**
 * 获取动态列表的操作
 */
public class TrendsPagerPersenter extends BasePresenter<TrendsStract.TrendsListView> implements TrendsStract.TrendsListPersenter {

    @Override
    public void queryTrends(int page, int size, int trendsid) {
        addSubscribe(HttpManager.getInstance().getChatApi().queryTrends(page,size,trendsid)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<TrendsBean>(mView) {
                    @Override
                    public void onNext(TrendsBean result) {
                        mView.queryTrendsReturn(result);
                    }
                }));
    }

}
