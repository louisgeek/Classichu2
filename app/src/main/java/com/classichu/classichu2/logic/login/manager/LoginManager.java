package com.classichu.classichu2.logic.login.manager;

import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.classichu.classichu2.R;
import com.classichu.classichu2.api.ApiServiceFactory;
import com.classichu.classichu2.app.AppInfoDataManager;
import com.classichu.classichu2.bean.BS_BaseBean;
import com.classichu.classichu2.custom.ClassicApplication;
import com.classichu.classichu2.logic.login.bean.UserLoginBean;
import com.classichu.classichu2.rxjava.RxHttpResultObserver;
import com.classichu.classichu2.rxjava.RxTransformerSchedulers;
import com.classichu.classichu2.tool.DateTool;
import com.classichu.classichu2.tool.ToastTool;
import com.classichu.dialogview.manager.DialogManager;
import com.classichu.dialogview.ui.ClassicDialogFragment;
import com.google.gson.Gson;
import com.jakewharton.rxbinding2.view.RxView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by Classichu on 2017/11/6.
 */

public class LoginManager {

    private static ClassicDialogFragment mCustomLoginAgainDialogFragment;
    public static void showLoginAgainDialog(final FragmentActivity fragmentActivity, final LoginAgainBack back) {
        //
        View view = LayoutInflater.from(fragmentActivity).inflate(R.layout.content_login, null);
        final View id_btn_login = view.findViewById(R.id.id_btn_login);
        final EditText id_et_username = (EditText) view.findViewById(R.id.id_et_username);
        final EditText id_et_password = (EditText) view.findViewById(R.id.id_et_password);
        RxView.clicks(id_btn_login)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {

                        //隐藏键盘
                        //KeyBoardTool.hideKeyboard(id_btn_login);
                        String username = id_et_username.getText().toString();
                        String password = id_et_password.getText().toString();
                        if (TextUtils.isEmpty(username)) {
                            ToastTool.showCenter(R.string.login_username_hint);
                            return;
                        }
                        String jgid= AppInfoDataManager.getInstance().getJGID();
                        ApiServiceFactory.getInstance().getUserApi().userLogin(username, password, jgid)
                                .compose(RxTransformerSchedulers.<BS_BaseBean<UserLoginBean>>observable_io_main()) //compose配合进行线程调度
                                .subscribe(new RxHttpResultObserver<BS_BaseBean<UserLoginBean>>(){

                                    @Override
                                    protected void onStart(Disposable disposable) {
                                        DialogManager.showCustomLoadingDialog(fragmentActivity);
                                        back.addDisposable(disposable);
                                    }

                                    @Override
                                    public void onSuccess(BS_BaseBean<UserLoginBean> userLoginBeanBS_baseBean) {
                                        updateUserLoginBean(userLoginBeanBS_baseBean.getData());
                                        back.onLoginSuccess();
                                    }

                                    @Override
                                    public void onFailure(String msg) {
                                        ToastTool.showCenter(msg);
                                    }

                                    @Override
                                    public void onFinish() {
                                        DialogManager.hideLoadingDialog();
                                    }
                                });
                        // RxView.enabled(id_btn_login_admin).accept(false);
                    }
                });

        if (mCustomLoginAgainDialogFragment != null) {
            mCustomLoginAgainDialogFragment.dismissAllowingStateLoss();
            mCustomLoginAgainDialogFragment = null;
        }
        mCustomLoginAgainDialogFragment = new ClassicDialogFragment.Builder(fragmentActivity)
                .setCancelable(true)
                .setTitle(R.string.login_login_again)
                .setContentView(view).build();
        mCustomLoginAgainDialogFragment.show(fragmentActivity.getSupportFragmentManager(), "showLoginAgainDialog");
    }


    public static void updateUserLoginBean(UserLoginBean userLoginBean) {
        //
        AppInfoDataManager.getInstance().setUserLoginBean(userLoginBean);

        UserLoginBean.LonginUserBean user = userLoginBean.getLonginUser();
        AppInfoDataManager.getInstance().getAuthBean().Account = user.getYHDM();
        AppInfoDataManager.getInstance().getAuthBean().Name = user.getYHXM();
        AppInfoDataManager.getInstance().getAuthBean().PWD = "";
        AppInfoDataManager.getInstance().getAuthBean().MAC = "";
        AppInfoDataManager.getInstance().getAuthBean().IP = "";
        AppInfoDataManager.getInstance().getAuthBean().JGID = user.getJGID();
       /* String data = "";
        try {
            data = JsonUtil.toJson(authVo);
            data = "Scan" + data;
            data = URLEncoder.encode(data, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        application.authorizationString = data;
         saveUser(user);*/

        List<UserLoginBean.AreasBean> aList = userLoginBean.getAreas();
        Vector<UserLoginBean.AreasBean> areasBeanVector = new Vector<>(aList);
        AppInfoDataManager.getInstance().setAreasBeanVector(areasBeanVector);

        UserLoginBean.TimeVoBean timeVo = userLoginBean.getTimeVo();
        String webTime = timeVo.getTime();
        Log.i("zfq", "loginSuccess: webTime:" + webTime);
        AppInfoDataManager.getInstance().setBetweenTime(DateTool.getBetween(webTime));
/*
        application.JSESSIONID = userlogin.getData().getSessionId();*/

        ClassicApplication classicApplication = ClassicApplication.getInstance();
        Gson gson = new Gson();
        String authorization = "Basic" + gson.toJson(AppInfoDataManager.getInstance().getAuthBean());
        try {
            authorization = URLEncoder.encode(authorization, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String sessionId = userLoginBean.getSessionId();
        Log.i("zfq", "loginSuccess: sessionId:" + sessionId);
        classicApplication.getHeadersMap().put("Authorization", authorization);
        //
        //原生请求 用"JSESSIONID"   这里OkHttp使用"Cookie"
        // classicApplication.getHeadersMap().put("JSESSIONID",sessionId);
        //Set-Cookie是响应里header  Cookie是请求里header
        classicApplication.getHeadersMap().put("Cookie", "SESSION=" + sessionId);
    }

    public interface   LoginAgainBack{
       void addDisposable(Disposable disposable);
       void onLoginSuccess();

    }
}
