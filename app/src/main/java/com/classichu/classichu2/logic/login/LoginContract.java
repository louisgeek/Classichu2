package com.classichu.classichu2.logic.login;

import com.classichu.classichu2.base.IModel;
import com.classichu.classichu2.base.IView;
import com.classichu.classichu2.logic.login.bean.AgencyBeanWrapper;
import com.classichu.classichu2.logic.login.bean.UserLoginBean;

/**
 * Created by Classichu on 2017/9/30.
 */

public class LoginContract {
    interface View extends IView {
        void loginSuccess(UserLoginBean userLoginBean);
        void showAgencyDialog(AgencyBeanWrapper agencyBeanWrapper);
    }

    interface Model extends IModel {

    }


    interface Presenter {

        void getAgency(String username);

        void doLogin(String username, String password, String jgid);

        void doLoginAdmin();

        void doLoginFromOtherApp();

        void doLoginWithBarcode();

        void doParseBarCode();

        void startUpdateService();

        void startMqttService();


    }
}
