package com.classichu.classichu2.logic.patient.module.lifesign;

import com.classichu.classichu2.base.IModel;
import com.classichu.classichu2.base.IView;

/**
 * Created by Classichu on 2017/5/27.
 */

public interface VitalSignContract {


    interface View<D> extends IView {
        void setupData(D d);
    /*    void showMoreDataLoading();
        void showMoreDataLoadComplete();
        void showMoreDataLoadNormal();*/
    }

    interface Model extends IModel {

    }

    interface Presenter{
       void gainData();
    }
}
