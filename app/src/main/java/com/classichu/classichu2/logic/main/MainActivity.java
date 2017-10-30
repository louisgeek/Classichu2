package com.classichu.classichu2.logic.main;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.classichu.adapter.recyclerview.ClassicRecyclerViewAdapter;
import com.classichu.classichu2.R;
import com.classichu.classichu2.app.AppInfoDataManager;
import com.classichu.classichu2.base.BaseActivity;
import com.classichu.classichu2.logic.login.bean.UserLoginBean;
import com.classichu.classichu2.logic.main.adapter.AreaListAdapter;
import com.fondesa.recyclerviewdivider.RecyclerViewDivider;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity {


    @Override
    protected int setupLayoutResId() {
        return R.layout.activity_main;
    }

    private MainFragment mainFragment;

    @Override
    protected void initView(Bundle savedInstanceState) {
        configTitle("");
        initAreaSelectView();

        mainFragment = MainFragment.newInstance("", "");
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.id_frame_layout, mainFragment)
                .commitAllowingStateLoss();
    }

    private void initAreaSelectView() {
        //
        id_tv_area = findById(R.id.id_tv_area);
        id_tv_area.setText(AppInfoDataManager.getInstance().getAreaBean().getKSMC());
        RxView.clicks(id_tv_area)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        showAreaSelect(id_tv_area);
                    }
                });
    }

    TextView id_tv_area;

    private void configTitle(String title) {
        Toolbar toolbar = findById(R.id.id_toolbar);
        setSupportActionBar(toolbar);
        // ActionBar actionBar = getSupportActionBar();
        setTitle(title);
    }


    private void showAreaSelect(View view) {
        //
        List<UserLoginBean.AreasBean> areasBeanList = AppInfoDataManager.getInstance().getAreasBeanVector();
            /*    List<UserLoginBean.AreasBean> areasBeanListNew=new ArrayList<UserLoginBean.AreasBean>();
                areasBeanListNew.add(areasBeanList.get(0));*/
              /*  for (int i = 0; i < 5; i++) {
                    UserLoginBean.AreasBean ab=new UserLoginBean.AreasBean();
                    ab.setKSMC("stt"+i);
                    areasBeanList.add(ab);
                }*/
        final AreaListAdapter areaListAdapter = new AreaListAdapter(areasBeanList,
                R.layout.item_list_normal);

        RecyclerView recyclerView = new RecyclerView(mContext);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setHasFixedSize(true);
        recyclerView.setBackgroundResource(R.drawable.selector_classic_popup_bg);
        //hideLastDivider
        RecyclerViewDivider.with(mContext).color(Color.parseColor("#FFD1CFCF")).build().addTo(recyclerView);
        recyclerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        recyclerView.setAdapter(areaListAdapter);
        //
        final PopupWindow popupWindow = new PopupWindow(mContext);
        popupWindow.setContentView(recyclerView);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //
        popupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.update();
        //
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        View contentView = popupWindow.getContentView();
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int contentViewWidth = contentView.getMeasuredWidth();
        //x轴处理居中
        popupWindow.showAsDropDown(view, -(contentViewWidth - view.getWidth()) / 2, -view.getHeight() / 5);
        //此法不型  y偏移不行
        // int statusBarHeight=ScreenTool.getStatusBarHeight();
        // popupWindow.showAtLocation(view,Gravity.CENTER_HORIZONTAL,0,-statusBarHeight);
        areaListAdapter.setOnItemClickListener(new ClassicRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                super.onItemClick(itemView, position);
                UserLoginBean.AreasBean areasBean = areaListAdapter.getData(position);
                AppInfoDataManager.getInstance().setAreasBean(areasBean);
                //
                if (mainFragment != null) {
                    mainFragment.callAtAty_toRefreshData();
                }
                id_tv_area.setText(areasBean.getKSMC());
                //
                popupWindow.dismiss();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.id_menu_main:
                if (mainFragment != null) {
                    mainFragment.callAtAty_switchGridOrList();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
