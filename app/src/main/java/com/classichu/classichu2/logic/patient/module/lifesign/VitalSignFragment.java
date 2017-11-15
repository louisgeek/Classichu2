package com.classichu.classichu2.logic.patient.module.lifesign;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.LinearLayout;

import com.classichu.classichu2.R;
import com.classichu.classichu2.base.BaseMvpFragment;
import com.classichu.classichu2.helper.VectorOrImageResHelper;
import com.classichu.classichu2.logic.login.bean.UserLoginBean;
import com.classichu.classichu2.logic.login.manager.LoginManager;
import com.classichu.classichu2.logic.patient.module.lifesign.bean.VitalSignTimePointBean;
import com.classichu.classichu2.logic.patient.module.lifesign.bean.VitalSignTypeItemBean;
import com.classichu.classichu2.tool.SizeTool;
import com.classichu.classichu2.tool.floatwindowpermission.FloatWindowManager;
import com.classichu.classichu2.widget.floatball.FloatBallManager;
import com.classichu.classichu2.widget.floatball.floatball.FloatBallCfg;
import com.classichu.classichu2.widget.floatball.menu.FloatMenuCfg;
import com.classichu.classichu2.widget.floatball.menu.MenuItem;
import com.classichu.dialogview.manager.DialogManager;

import java.util.List;

import io.reactivex.disposables.Disposable;


public class VitalSignFragment extends BaseMvpFragment<VitalSignPresenter> implements VitalSignContract.View<Pair<List<VitalSignTimePointBean>, List<VitalSignTypeItemBean>>> {

    public VitalSignFragment() {
        // Required empty public constructor
    }

    public static VitalSignFragment newInstance(String param1, String param2) {
        VitalSignFragment fragment = new VitalSignFragment();
        Bundle args = new Bundle();
      /*  args.putString(ARG_PARAM1, param1);
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
        return R.layout.fragment_vital_sign;
    }

    @Override
    protected void initView(View rootLayout, Bundle savedInstanceState) {

        initFloatMenu(getActivity());

        toRefreshData();

    }

    private FloatBallManager mFloatballManager;

    private void initFloatMenu(Context context) {
        //1 初始化悬浮球配置，定义好悬浮球大小和icon的drawable
        int ballSize = SizeTool.dp2px(56);
        Drawable ballIcon = VectorOrImageResHelper.getDrawable(R.drawable.ic_add_circle_outline_black_24dp);
        FloatBallCfg ballCfg = new FloatBallCfg(ballSize, ballIcon);

        //2 需要显示悬浮菜单
        //2.1 初始化悬浮菜单配置，有菜单item的大小和菜单item的个数
        int menuSize = SizeTool.dp2px(250);
        int menuItemSize = SizeTool.dp2px(48);
        FloatMenuCfg menuCfg = new FloatMenuCfg(menuSize, menuItemSize);
        //3 生成floatballManager
        mFloatballManager = new FloatBallManager(context, ballCfg, menuCfg);
        addFloatMenuItem();

        setFloatPermission(context);
        //5 如果没有添加菜单，可以设置悬浮球点击事件
        if (mFloatballManager.getMenuItemSize() == 0) {
            mFloatballManager.setOnFloatBallClickListener(new FloatBallManager.OnFloatBallClickListener() {
                @Override
                public void onFloatBallClick() {
                    // toast("点击了悬浮球");
                }
            });
        }
        mFloatballManager.show();
    }

    private void setFloatPermission(final Context context) {
        mFloatballManager.setPermission(new FloatBallManager.IFloatBallPermission() {
            @Override
            public boolean onRequestFloatBallPermission() {
                FloatWindowManager.getInstance().applyPermission(context);
                return true;
            }

            @Override
            public boolean hasFloatBallPermission(Context context) {
                return FloatWindowManager.getInstance().checkPermission(context);
            }

        });
    }

    private void addFloatMenuItem() {
        MenuItem personItem = new MenuItem(VectorOrImageResHelper.getDrawable(R.drawable.ic_check_circle_black_24dp)) {
            @Override
            public void action() {
                mFloatballManager.closeMenu();
            }
        };
        MenuItem walletItem = new MenuItem(VectorOrImageResHelper.getDrawable(R.drawable.ic_info_black_24dp)) {
            @Override
            public void action() {
                mFloatballManager.closeMenu();
            }
        };
        mFloatballManager
                .addMenuItem(personItem)
                .addMenuItem(walletItem)
                .addMenuItem(walletItem)
                .addMenuItem(walletItem)
                .addMenuItem(walletItem)
                .addMenuItem(walletItem)
                .buildMenu();
    }


    @Override
    protected void toRefreshData() {
        mPresenter.gainData();
    }

    @Override
    protected void loginAgainSuccess(UserLoginBean userLoginBean) {
        toRefreshData();
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
        LoginManager.showLoginAgainDialog(getActivity(), new LoginManager.LoginAgainBack() {
            @Override
            public void addDisposable(Disposable disposable) {
                mPresenter.addDisposable(disposable);
            }

            @Override
            public void onLoginSuccess() {
                toRefreshData();
            }
        });
    }


    @Override
    public void setupData(Pair<List<VitalSignTimePointBean>, List<VitalSignTypeItemBean>> listListPair) {

        LinearLayout linearLayout = findById(R.id.idxxx);


        List<VitalSignTimePointBean> vitalSignTimePointBeanList = listListPair.first;

        VitalSignViewFactory.buildTimePointView(linearLayout, vitalSignTimePointBeanList);

 /*       Spinner  spinner =new Spinner(mContext);
        spinner.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

//        spinner.setBackgroundResource(R.drawable.selector_classic_text_item_underline_bg);
        //适配器
        ArrayAdapter<VitalSignTimePointBean>  arrayAdapter= new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_item, vitalSignTimePointBeanList);
        //设置样式
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        spinner.setAdapter(arrayAdapter);*/

  /*      Drawable downDrawable = VectorOrImageResHelper.getDrawable(R.drawable.ic_keyboard_arrow_down_black_24dp);
        downDrawable.setColorFilter(ContextCompat.getColor(mContext,R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
        ViewCompat.setBackground(spinner,downDrawable);*/


        List<VitalSignTypeItemBean> vitalSignTypeItemBeanList = listListPair.second;
        VitalSignViewFactory.buildVitalSignTypeItemView(linearLayout, vitalSignTypeItemBeanList, true, null);

    }


    @Override
    protected VitalSignPresenter setupPresenter() {
        return new VitalSignPresenter(null, this);
    }


    @Override
    public void onResume() {
        super.onResume();
        mFloatballManager.show();
    }

    @Override
    public void onPause() {
        super.onPause();
        mFloatballManager.hide();
    }
}
