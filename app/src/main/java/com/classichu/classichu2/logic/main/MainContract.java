package com.classichu.classichu2.logic.main;

import com.classichu.classichu2.base.IModel;
import com.classichu.classichu2.base.IView;

/**
 * Created by Classichu on 2017/9/30.
 */

public class MainContract{

    interface View<D> extends IView {
        void setupData(D d);
        boolean isFilterMyPatients();
    }

    interface Model extends IModel {

    }

    interface Presenter {
        void gainData();
    }
}
