package com.example.biyingxiangmu.constract;

import com.example.biyingxiangmu.base.IBaseView;
import com.example.biyingxiangmu.base.IPresenter;
import com.example.biyingxiangmu.bean.DetailsUpdateBean;
import com.example.biyingxiangmu.bean.UserDetailsBean;

import java.util.Map;

public class OwnConstract {

    public interface DetailsView extends IBaseView {
        void getDetailsReturn(UserDetailsBean result);

        //更新用户详情信息的返回
        void updateDetailsReturn(DetailsUpdateBean result);
    }

    public interface DetailsPresenter extends IPresenter<DetailsView> {
        void getDetails();

        //更新用户信息
        void updateDetails(Map<String,String> map);
    }

}
