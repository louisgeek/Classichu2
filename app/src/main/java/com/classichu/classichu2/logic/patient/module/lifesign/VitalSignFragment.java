package com.classichu.classichu2.logic.patient.module.lifesign;


import android.os.Bundle;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.classichu.classichu2.R;
import com.classichu.classichu2.base.BaseMvpFragment;
import com.classichu.classichu2.logic.login.bean.UserLoginBean;
import com.classichu.classichu2.logic.login.manager.LoginManager;
import com.classichu.classichu2.logic.patient.module.lifesign.bean.VitalSignTimePointBean;
import com.classichu.classichu2.logic.patient.module.lifesign.bean.VitalSignTypeItemBean;
import com.classichu.classichu2.tool.ToastTool;
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
        setHasOptionsMenu(true);//设置当前fragment有菜单
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


        toRefreshData();
    }

    @Override
    protected int[] configFloatMenuItemDrawables() {
        return new int[]{R.drawable.ic_chevron_right_black_24dp,
                R.drawable.ic_close_black_24dp, R.drawable.ic_add_circle_outline_black_24dp};
    }

    @Override
    protected boolean onFloatMenuClick(View view, int resid) {
        switch (resid) {
            case R.drawable.ic_chevron_right_black_24dp:
                ToastTool.show("ic_chevron_right_black_24dp");
                break;
            case R.drawable.ic_close_black_24dp:
                ToastTool.show("ic_close_black_24dp");
                break;
        }
        return true;
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //onCreate中需加 setHasOptionsMenu(true);
        menu.clear();//不清除菜单 会追加菜单
        inflater.inflate(R.menu.menu_vitalsign, menu);//menu.clear()清除菜单，如果存在同id的菜单 点击事件正好已经在aty中处理了
        super.onCreateOptionsMenu(menu, inflater);
    }

    /**
     * activity总是比fragment先得到item事件,只有activity不处理老会把事件传给fragment
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.id_menu_classic_ok:
               // ToastTool.show("id_menu_classic_ok");
                toSaveData();
                break;
            //menu.clear()清除菜单，如果存在同id的菜单 点击事件正好已经在aty中处理了
           /* case R.id.id_menu_patient:
                ToastTool.show("id_menu_patient");
                break;*/

        }
        return super.onOptionsItemSelected(item);
    }

    private void toSaveData() {
     //   VitalSignViewFactory.buildTimePointView();
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

    private  VitalSignViewFactory mVitalSignViewFactory=new VitalSignViewFactory();
    @Override
    public void setupData(Pair<List<VitalSignTimePointBean>, List<VitalSignTypeItemBean>> listListPair) {

        LinearLayout linearLayout = findById(R.id.idxxx);


        List<VitalSignTimePointBean> vitalSignTimePointBeanList = listListPair.first;

        mVitalSignViewFactory.buildTimePointView(linearLayout, vitalSignTimePointBeanList);

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
        mVitalSignViewFactory.buildVitalSignTypeItemView(linearLayout, vitalSignTypeItemBeanList, true, null);

    }


    @Override
    protected VitalSignPresenter setupPresenter() {
        return new VitalSignPresenter(null, this);
    }


}
