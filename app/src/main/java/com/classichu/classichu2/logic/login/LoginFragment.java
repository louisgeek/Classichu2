package com.classichu.classichu2.logic.login;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.classichu.classichu2.R;
import com.classichu.classichu2.base.BaseMvpFragment;
import com.classichu.classichu2.logic.login.adapter.AgencyBaseAdapter;
import com.classichu.classichu2.logic.login.bean.AgencyBean;
import com.classichu.classichu2.logic.login.bean.AgencyBeanWrapper;
import com.classichu.classichu2.logic.login.bean.UserLoginBean;
import com.classichu.classichu2.logic.login.manager.LoginManager;
import com.classichu.classichu2.logic.main.MainActivity;
import com.classichu.classichu2.logic.setting.SettingActivity;
import com.classichu.classichu2.tool.KeyBoardTool;
import com.classichu.classichu2.tool.SharedPreferencesTool;
import com.classichu.classichu2.tool.ToastTool;
import com.classichu.dialogview.manager.DialogManager;
import com.classichu.dialogview.ui.ClassicDialogFragment;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

public class LoginFragment extends BaseMvpFragment<LoginPresenter> implements LoginContract.View {
    private String SP_LAST_USER = "SP_LAST_USER";
    private String SP_LAST_ADMIN_USER = "SP_LAST_ADMIN_USER";

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
    /*    args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);*/
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
           /* mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);*/
        }
    }

    @Override
    protected int setupLayoutResId() {
        return R.layout.fragment_login;
    }

    @BindView(R.id.id_et_username)
    EditText id_et_username;
    @BindView(R.id.id_et_password)
    EditText id_et_password;
    @BindView(R.id.id_btn_login)
    Button id_btn_login;
    @BindView(R.id.id_tv_admin)
    TextView id_tv_admin;

    @Override
    protected void initView(View rootLayout, Bundle savedInstanceState) {


        RxView.clicks(id_btn_login)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        //隐藏键盘
                        KeyBoardTool.hideKeyboard(id_btn_login);

                        String username = id_et_username.getText().toString();
                        String password = id_et_password.getText().toString();
                        if (TextUtils.isEmpty(username)) {
                            showSnack(R.string.login_username_hint);
                            return;
                        }
                        /*if (TextUtils.isEmpty(password)) {
                            showSnack(R.string.login_password_hint);
                            return;
                        }*/

                      //  RxView.enabled(id_btn_login).accept(false);

                        SharedPreferencesTool.put(SP_LAST_USER, username);
                        //获取机构
                        mPresenter.getAgency(!TextUtils.isEmpty(username) ? username : null);


                    }
                });


        RxView.clicks(id_tv_admin)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {

                        showLoginAdminDialog();
                    }
                });


    }

    @Override
    protected void loginAgainSuccess(UserLoginBean userLoginBean) {

    }

    private ClassicDialogFragment mCustomLoginAdminDialogFragment;

    private void showLoginAdminDialog() {
        //
        View view = LayoutInflater.from(mActivity).inflate(R.layout.content_login, null);
        final View id_btn_login_admin = findById(view, R.id.id_btn_login);
        final EditText id_et_username_admin = findById(view, R.id.id_et_username);
        final EditText id_et_password_admin = findById(view, R.id.id_et_password);
        RxView.clicks(id_btn_login_admin)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {

                        //隐藏键盘
                        //KeyBoardTool.hideKeyboard(id_btn_login);

                        String username = id_et_username_admin.getText().toString();
                        String password = id_et_password_admin.getText().toString();
                        if (TextUtils.isEmpty(username)) {
                            ToastTool.showCenter(R.string.login_username_hint);
                            return;
                        }
                       // RxView.enabled(id_btn_login_admin).accept(false);
                        toSettingAty(username);
                    }
                });

        if (mCustomLoginAdminDialogFragment != null) {
            mCustomLoginAdminDialogFragment.dismissAllowingStateLoss();
            mCustomLoginAdminDialogFragment = null;
        }
        mCustomLoginAdminDialogFragment = new ClassicDialogFragment.Builder(mActivity)
                .setCancelable(true)
                .setTitle(R.string.login_admin_login)
                .setContentView(view).build();
        mCustomLoginAdminDialogFragment.show(getChildFragmentManager(), "showLoginAdminDialog");
    }

    private void toSettingAty(String username) {
        SharedPreferencesTool.put(SP_LAST_ADMIN_USER, username);

        if (mCustomLoginAdminDialogFragment != null) {
            mCustomLoginAdminDialogFragment.dismissAllowingStateLoss();
            mCustomLoginAdminDialogFragment = null;
        }
        startAty(SettingActivity.class);
    }


    @Override
    public void showLoading() {
        DialogManager.showCustomLoadingDialog(getActivity());
    }

    @Override
    public void hideLoading() {
        DialogManager.hideLoadingDialog();
    }

    @Override
    public void showMessage(String msg) {
        showSnack(msg);
    }

    @Override
    public void showLoginAgainView() {

    }


    @Override
    public void loginSuccess(UserLoginBean userLoginBean) {

        LoginManager.updateUserLoginBean(userLoginBean);

        Intent intent = new Intent(mContext,
                MainActivity.class);
        startActivity(intent);

        // startMqtt();
        // startSettingService();
    }

    @Override
    public void showAgencyDialog(AgencyBeanWrapper agencyBeanWrapper) {
        List<AgencyBean> agencyBeanList = agencyBeanWrapper.getAgencyBeanList();
        if (agencyBeanList.size() > 1) {
            showSelectDialog(agencyBeanList);
        } else {
            String jgid = agencyBeanList.get(0).getJGID();
            goOnDoLogin(jgid);

        }
    }

    private void goOnDoLogin(String jgid) {
        mPresenter.doLogin(id_et_username.getText().toString(), id_et_password.getText().toString(),jgid);
    }

    private ClassicDialogFragment mCustomSelectDialogFragment;

    private void showSelectDialog(final List<AgencyBean> agencyBeanList) {
        //
        ListView listView = new ListView(mContext);
        listView.setAdapter(new AgencyBaseAdapter(agencyBeanList));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //
                mCustomSelectDialogFragment.dismissAllowingStateLoss();
                //
                String jgid=agencyBeanList.get(position).getJGID();
                goOnDoLogin(jgid);
            }
        });
        mCustomSelectDialogFragment = new ClassicDialogFragment.Builder(getActivity())
                .setTitle("机构选择")
                .setContentView(listView)
                .build();
        mCustomSelectDialogFragment.show(getChildFragmentManager(), "mCustomDialogFragment");
    }

    @Override
    protected LoginPresenter setupPresenter() {
        return new LoginPresenter(null, this);
    }
}
