package com.classichu.classichu2.login;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.classichu.classichu2.R;
import com.classichu.classichu2.base.BaseFragment;
import com.classichu.dialogview.ui.ClassicDialogFragment;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends BaseFragment {

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

    private Button id_btn_login;
    private TextView id_tv_admin;

    @Override
    protected void initView(View rootLayout, Bundle savedInstanceState) {
        id_btn_login = findById(R.id.id_btn_login);
        id_tv_admin = findById(R.id.id_tv_admin);


        RxView.clicks(id_btn_login)
                .throttleFirst(3, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        RxView.enabled(id_btn_login).accept(false);
                        Snackbar.make(mRootLayout,"login",Snackbar.LENGTH_SHORT).show();
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

    private ClassicDialogFragment classicDialogFragment;
    private void showLoginAdminDialog() {
        //
        View view = LayoutInflater.from(mActivity).inflate(R.layout.content_login, null);
        final View id_btn_login_admin = view.findViewById(R.id.id_btn_login);
        RxView.clicks(id_btn_login_admin)
                .throttleFirst(3, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        RxView.enabled(id_btn_login_admin).accept(false);
                        Snackbar.make(mRootLayout,"admin login",Snackbar.LENGTH_SHORT).show();
                    }
                });

        if (classicDialogFragment != null) {
            classicDialogFragment.dismissAllowingStateLoss();
            classicDialogFragment = null;
        }
        classicDialogFragment = new ClassicDialogFragment.Builder(mActivity).setCancelable(true).setTitle(R.string.login_admin_login)
                .setContentView(view).build();
        classicDialogFragment.show(getChildFragmentManager(), "showLoginAdminDialog");
    }

}
