package com.classichu.classichu2.logic.patient;

import android.text.TextUtils;

import com.classichu.classichu2.api.ApiServiceFactory;
import com.classichu.classichu2.app.AppInfoDataManager;
import com.classichu.classichu2.base.BasePresenter;
import com.classichu.classichu2.bean.BS_BaseBean;
import com.classichu.classichu2.logic.patient.bean.PatientInfoBean;
import com.classichu.classichu2.rxjava.RxHttpResultObserver;
import com.classichu.classichu2.rxjava.RxTransformerSchedulers;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by Classichu on 2017/5/27.
 */

public class PatientPresenter extends BasePresenter<PatientContract.Model, PatientContract.View> implements PatientContract.Presenter {


    public PatientPresenter(PatientContract.Model model, PatientContract.View view) {
        super(model, view);
    }

    @Override
    public void gainData() {

        List<String> stringList = new ArrayList<>();
        stringList.add(String.format("%s%s", "病人姓名：", AppInfoDataManager.getInstance().getPatientListBean().getBRXM()));
        stringList.add(String.format("%s%s", "住院号码：", AppInfoDataManager.getInstance().getPatientListBean().getZYHM()));
        stringList.add(String.format("%s%s", "病人性别：", AppInfoDataManager.getInstance().getPatientListBean().getBRXB() == 1 ? "男" : "女"));
        if (!TextUtils.isEmpty(AppInfoDataManager.getInstance().getPatientListBean().getRYRQ()) && AppInfoDataManager.getInstance().getPatientListBean().getRYRQ().contains("T")) {
            String ryrq = AppInfoDataManager.getInstance().getPatientListBean().getRYRQ().split("T")[0];
            stringList.add(String.format("%s%s", "入院日期：", ryrq));
        }
        stringList.add(String.format("%s%s", "病人床号：", AppInfoDataManager.getInstance().getPatientListBean().getBRCH()));

        mView.hideLoading();//隐藏手动下拉显示的加载
        mView.showMoreDataLoadNormal();//隐藏曾加载完成的
        mView.setupData(stringList);

    }


    @Override
    public void gainPatientInfoDetail() {
        String zhy = AppInfoDataManager.getInstance().getZyh();
        String jgid = AppInfoDataManager.getInstance().getJGID();
        ApiServiceFactory.getInstance().getPatientApi().getPatientInfoDetail(zhy, jgid)
                .compose(RxTransformerSchedulers.<BS_BaseBean<PatientInfoBean>>observable_io_main()) //compose配合进行线程调度
                .subscribe(new RxHttpResultObserver<BS_BaseBean<PatientInfoBean>>() {
                    @Override
                    protected void onStart(Disposable disposable) {
                        //##mView.showLoading();
                        mView.showMoreDataLoading();
                        //add 进行统一管理
                        addDisposable(disposable);
                    }

                    @Override
                    public void onSuccess(BS_BaseBean<PatientInfoBean> patientInfoBeanBS_baseBean) {
                        if (patientInfoBeanBS_baseBean.getReType() == 0) {
                            //===
                            List<String> stringList = new ArrayList<>();
                            PatientInfoBean.PatientBean patientBean = patientInfoBeanBS_baseBean.getData().getPatient();
                       /*     stringList.add(String.format("%s%s","病人姓名：",patientBean.getBRXM()));
                            stringList.add(String.format("%s%s","住院号码：",patientBean.getZYHM()));
                            stringList.add(String.format("%s%s","病人性别：",patientBean.getBRXB()==1 ? "男" : "女"));
                            if (!TextUtils.isEmpty(patientBean.getRYRQ())&&patientBean.getRYRQ().contains("T")){
                                String ryrq=patientBean.getRYRQ().split("T")[0];
                                stringList.add(String.format("%s%s","入院日期：",ryrq));
                            }
                            stringList.add(String.format("%s%s","病人床号：",patientBean.getBRCH()));*/
                            stringList.add(String.format("%s%s", "病人科室：", patientBean.getKSMC()));
                            stringList.add(String.format("%s%s", "主治医生：", patientBean.getYSMC()));
                            stringList.add(String.format("%s%s", "费用性质：", patientBean.getXZMC()));
                            stringList.add(String.format("%s%s", "病人床号：", patientBean.getBRCH()));
                            stringList.add(String.format("%s%s", "病人床号：", patientBean.getBRCH()));
                            stringList.add(String.format("%s%s", "病人床号：", patientBean.getBRCH()));

                            if (patientInfoBeanBS_baseBean.getData().getExpenseTotal() != null) {
                                PatientInfoBean.ExpenseTotalBean expenseTotalBean = patientInfoBeanBS_baseBean.getData().getExpenseTotal();
                                stringList.add(String.format("%s%s", "总费用：", expenseTotalBean.getZJJE()));
                                stringList.add(String.format("%s%s", "自负金额：", expenseTotalBean.getZFJE()));
                                stringList.add(String.format("%s%s", "交款金额：", expenseTotalBean.getJKJE()));
                                stringList.add(String.format("%s%s", "费用余额：", expenseTotalBean.getFYYE()));
                            }

                            //=====
                                /*if (data.size() == 0) {
            //所有数据加载完毕
            mClassicRVHeaderFooterAdapter.showFooterViewLoadComplete();
        } else {
            //一次加载完成
            mClassicRVHeaderFooterAdapter.turnNextPageNum();
            mClassicRVHeaderFooterAdapter.showFooterViewNormal();
        }*/

                            mView.setupPatientInfoDetail(stringList);
                        } else if (patientInfoBeanBS_baseBean.getReType() == 100) {
                            //未登录
                            onFailure(patientInfoBeanBS_baseBean.getMsg());
                        } else {
                            onFailure(patientInfoBeanBS_baseBean.getMsg());
                        }

                    }

                    @Override
                    public void onFailure(String msg) {
                        mView.showMessage(msg);
                        mView.showMoreDataLoadNormal();
                    }

                    @Override
                    public void onFinish() {
                        //## mView.hideLoading();
                        mView.showMoreDataLoadComplete();
                    }
                });


    }
}
