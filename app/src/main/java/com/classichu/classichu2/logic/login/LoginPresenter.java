package com.classichu.classichu2.logic.login;

import android.util.Log;

import com.classichu.classichu2.api.ApiServiceFactory;
import com.classichu.classichu2.base.BasePresenter;
import com.classichu.classichu2.bean.BS_BaseBean;
import com.classichu.classichu2.bean.BS_BaseListBean;
import com.classichu.classichu2.logic.login.bean.AgencyBean;
import com.classichu.classichu2.logic.login.bean.AgencyBeanWrapper;
import com.classichu.classichu2.logic.login.bean.UserLoginBean;
import com.classichu.classichu2.rxjava.RxHttpResultObserver;
import com.classichu.classichu2.rxjava.RxTransformerSchedulers;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by Classichu on 2017/9/30.
 */

public class LoginPresenter extends BasePresenter<LoginContract.Model, LoginContract.View> implements LoginContract.Presenter {

    public LoginPresenter(LoginContract.Model model, LoginContract.View view) {
        super(model, view);
    }


    @Override
    public void getAgency(String username) {
        ApiServiceFactory.getInstance().getUserApi().getAgency(username)
                .compose(RxTransformerSchedulers.<BS_BaseListBean<AgencyBean>>observable_io_main()) //compose配合进行线程调度
                .subscribe(new RxHttpResultObserver<BS_BaseListBean<AgencyBean>>() {
                    @Override
                    protected void onStart(Disposable disposable) {
                        mView.showLoading();
                        //add 进行统一管理
                        addDisposable(disposable);
                    }

                    @Override
                    public void onSuccess(BS_BaseListBean<AgencyBean> agencyBS_baseListBean) {
                        Log.i("zfq", "onSuccess: ");
                        List<AgencyBean> agencyBeanList = agencyBS_baseListBean.getData();
                        if (agencyBeanList != null && agencyBeanList.size() > 0) {
                            AgencyBeanWrapper agencyBeanWrapper = new AgencyBeanWrapper(agencyBeanList);
                            mView.showAgencyDialog(agencyBeanWrapper);
                        } else {
                            //机构数据size为0
                            onFailure("用户不存在");
                        }
                    }

                    @Override
                    public void onFailure(String msg) {
                        mView.showMessage(msg);
                    }

                    @Override
                    public void onFinish() {
                        mView.hideLoading();
                    }
                });
    }

    @Override
    public void doLogin(String username, String password, String jgid) {
        ApiServiceFactory.getInstance().getUserApi().userLogin(username, password, jgid)
                .compose(RxTransformerSchedulers.<BS_BaseBean<UserLoginBean>>observable_io_main()) //compose配合进行线程调度
                .subscribe(new RxHttpResultObserver<BS_BaseBean<UserLoginBean>>() {
                    @Override
                    protected void onStart(Disposable disposable) {
                        mView.showLoading();
                        //add 进行统一管理
                        addDisposable(disposable);
                    }

                    @Override
                    public void onSuccess(BS_BaseBean<UserLoginBean> userLoginBeanBS_baseBean) {
                        mView.loginSuccess(userLoginBeanBS_baseBean.getData());
                    }

                    @Override
                    public void onFailure(String msg) {
                        mView.showMessage(msg);
                    }

                    @Override
                    public void onFinish() {
                        mView.hideLoading();
                    }
                });
    }

    @Override
    public void doLoginAdmin() {

    }

    @Override
    public void doLoginFromOtherApp() {

    }

    @Override
    public void doLoginWithBarcode() {

    }

    @Override
    public void doParseBarCode() {

    }

    @Override
    public void startUpdateService() {

    }

    @Override
    public void startMqttService() {

    }
}
