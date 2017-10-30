package com.classichu.classichu2.logic.patient;

import com.classichu.classichu2.base.IModel;
import com.classichu.classichu2.base.IView;

/**
 * Created by Classichu on 2017/5/27.
 */

public interface PatientContract {


    interface View<D> extends IView {
        void setupData(D d);
        void setupPatientInfoDetail(D d);
    }

    interface Model extends IModel {

    }

    interface Presenter{
       void gainData();
       void gainPatientInfoDetail();
    }
}
