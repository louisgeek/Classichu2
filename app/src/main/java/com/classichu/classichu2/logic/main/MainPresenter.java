package com.classichu.classichu2.logic.main;

import com.classichu.classichu2.api.ApiServiceFactory;
import com.classichu.classichu2.app.AppInfoDataManager;
import com.classichu.classichu2.base.BasePresenter;
import com.classichu.classichu2.bean.BS_BaseListBean;
import com.classichu.classichu2.logic.login.bean.UserLoginBean;
import com.classichu.classichu2.logic.main.bean.PatientListBean;
import com.classichu.classichu2.rxjava.RxHttpResultObserver;
import com.classichu.classichu2.rxjava.RxTransformerSchedulers;

import io.reactivex.disposables.Disposable;

/**
 * Created by Classichu on 2017/10/30.
 */

public class MainPresenter extends BasePresenter<MainContract.Model,MainContract.View> implements MainContract.Presenter{

    public MainPresenter(MainContract.Model model, MainContract.View view) {
        super(model, view);
    }

    @Override
    public void gainData() {

        String jgid= AppInfoDataManager.getInstance().getJGID();
        String bqid= AppInfoDataManager.getInstance().getAreaBeanId();
        UserLoginBean.AreasBean areaBean= AppInfoDataManager.getInstance().getAreaBean();
        String hsqh="";
        if (mView.isFilterMyPatients()){
            hsqh=AppInfoDataManager.getInstance().getUserLoginBean().getLonginUser().getYHID();
        }
        int filter=0;//type 默认0
        if (areaBean != null && "[isSurgery]".equals(areaBean.getYGDM())) {
            filter = 1000;//标记是 手术科室
        }
        ApiServiceFactory.getInstance().getPatientApi()
                .getPatientList(bqid,filter,-1,-1,hsqh,jgid)
                .compose(RxTransformerSchedulers.<BS_BaseListBean<PatientListBean>>observable_io_main()) //compose配合进行线程调度
                .subscribe(new RxHttpResultObserver<BS_BaseListBean<PatientListBean>>() {
                    @Override
                    protected void onStart(Disposable disposable) {
                        mView.showLoading();
                        //add 进行统一管理
                        addDisposable(disposable);
                    }

                    @Override
                    public void onSuccess(BS_BaseListBean<PatientListBean> patientListBeanBS_baseListBean) {
                        if (patientListBeanBS_baseListBean.getReType() == 0) {
                            //===
                            mView.setupData(patientListBeanBS_baseListBean.getData());
                        } else if (patientListBeanBS_baseListBean.getReType() == 100) {
                            //未登录
                            onFailure(patientListBeanBS_baseListBean.getMsg());
                        } else {
                            onFailure(patientListBeanBS_baseListBean.getMsg());
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
}
