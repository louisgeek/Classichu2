package com.classichu.classichu2.logic.patient.module.lifesign;

import android.util.Pair;

import com.classichu.classichu2.api.ApiServiceFactory;
import com.classichu.classichu2.app.AppInfoDataManager;
import com.classichu.classichu2.base.BasePresenter;
import com.classichu.classichu2.bean.BS_BaseListBean;
import com.classichu.classichu2.logic.patient.module.lifesign.bean.VitalSignTimePointBean;
import com.classichu.classichu2.logic.patient.module.lifesign.bean.VitalSignTypeItemBean;
import com.classichu.classichu2.rxjava.RxHttpResultObserver;
import com.classichu.classichu2.rxjava.RxTransformerSchedulers;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;


public class VitalSignPresenter extends BasePresenter<VitalSignContract.Model, VitalSignContract.View> implements VitalSignContract.Presenter {


    public VitalSignPresenter(VitalSignContract.Model model, VitalSignContract.View view) {
        super(model, view);
    }



    @Override
    public void gainData() {

        String zyh = AppInfoDataManager.getInstance().getZyh();
        String bqid = AppInfoDataManager.getInstance().getAreaBeanId();
        String jgid = AppInfoDataManager.getInstance().getJGID();

        Observable<BS_BaseListBean<VitalSignTimePointBean>> observable_getTimePointList = ApiServiceFactory.getInstance()
                .getPatientApi().getTimePointList(bqid, jgid);
        Observable<BS_BaseListBean<VitalSignTypeItemBean>> observable_getLifeSignTypeItemList = ApiServiceFactory.getInstance()
                .getPatientApi().getLifeSignTypeItemList(zyh, bqid, jgid);
        Observable.zip(observable_getTimePointList, observable_getLifeSignTypeItemList,
                new BiFunction<BS_BaseListBean<VitalSignTimePointBean>, BS_BaseListBean<VitalSignTypeItemBean>,
                        Pair<BS_BaseListBean<VitalSignTimePointBean>, BS_BaseListBean<VitalSignTypeItemBean>>>() {
                    @Override
                    public Pair<BS_BaseListBean<VitalSignTimePointBean>, BS_BaseListBean<VitalSignTypeItemBean>> apply(@NonNull BS_BaseListBean<VitalSignTimePointBean> vitalSignTimePoint,
                                                                                                                       @NonNull BS_BaseListBean<VitalSignTypeItemBean> vitalSignTypeItem) throws Exception {
                        return Pair.create(vitalSignTimePoint, vitalSignTypeItem);
                    }
                }).compose(RxTransformerSchedulers.<Pair<BS_BaseListBean<VitalSignTimePointBean>, BS_BaseListBean<VitalSignTypeItemBean>>>observable_io_main()) //compose配合进行线程调度
                .subscribe(new RxHttpResultObserver<Pair<BS_BaseListBean<VitalSignTimePointBean>, BS_BaseListBean<VitalSignTypeItemBean>>>() {
                    @Override
                    protected void onStart(Disposable disposable) {
                       mView.showLoading();
                        //      mView.showMoreDataLoading();
                        //add 进行统一管理
                        addDisposable(disposable);
                    }

                    @Override
                    public void onSuccess(Pair<BS_BaseListBean<VitalSignTimePointBean>, BS_BaseListBean<VitalSignTypeItemBean>> bs_BaseListBean) {
                        if (bs_BaseListBean.first.getReType() == 0 && bs_BaseListBean.second.getReType() == 0) {
                            //===
                            List<VitalSignTimePointBean> vitalSignTimePointBeanList = bs_BaseListBean.first.getData();
                            List<VitalSignTypeItemBean> vitalSignTypeItemBeanList = bs_BaseListBean.second.getData();

                            Pair<List<VitalSignTimePointBean>,List<VitalSignTypeItemBean>> dataPair=Pair.create(vitalSignTimePointBeanList,vitalSignTypeItemBeanList);
                            mView.setupData(dataPair);

                        } else if (bs_BaseListBean.first.getReType() == 100||bs_BaseListBean.second.getReType() == 100) {
                            //未登录
                            mView.showLoginAgainView();
                        } else {
                            onFailure(bs_BaseListBean.first.getMsg()+"\n"+bs_BaseListBean.second.getMsg());
                        }

                    }

                    @Override
                    public void onFailure(String msg) {
                        mView.showMessage(msg);
                        //  mView.showMoreDataLoadNormal();
                    }

                    @Override
                    public void onFinish() {
                        mView.hideLoading();
                        // mView.showMoreDataLoadComplete();
                    }
                });
        ;


    }

}
