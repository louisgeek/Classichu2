package com.classichu.classichu2.logic.main;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.classichu.adapter.listener.SimpleOnRVItemTouchListener;
import com.classichu.classichu2.R;
import com.classichu.classichu2.app.AppInfoDataManager;
import com.classichu.classichu2.base.BaseActivity;
import com.classichu.classichu2.base.BaseFragment;
import com.classichu.classichu2.helper.CacheHelper;
import com.classichu.classichu2.logic.login.bean.UserLoginBean;
import com.classichu.classichu2.logic.main.adapter.AreaListAdapter;
import com.classichu.classichu2.logic.main.factory.MainAtyFragmentFactory;
import com.classichu.classichu2.tool.CacheTool;
import com.classichu.classichu2.widget.BottomNavigationViewSupport;
import com.classichu.classichu2.widget.ClassichuPopupWindow;
import com.classichu.dialogview.manager.DialogManager;
import com.fondesa.recyclerviewdivider.RecyclerViewDivider;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.List;

import butterknife.BindView;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity {

    @BindView(R.id.id_bottom_navigation_view_support)
    BottomNavigationViewSupport id_bottom_navigation_view_support;
    @BindView(R.id.id_drawer_layout)
    DrawerLayout id_drawer_layout;
    @BindView(R.id.id_navigation_view)
    NavigationView id_navigation_view;

    @Override
    protected int setupLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        configTitle("");
        initAreaSelectView();
        initNavigationView();
        initBottomNavigationView();
    }

    private MenuItem mMenuItem;

    private void initNavigationView() {
        //显示第一个页面
        switchFragment(0);

        //隐藏滚动条
        NavigationMenuView menuView = (NavigationMenuView) id_navigation_view.getChildAt(0);
        menuView.setVerticalScrollBarEnabled(false);

        View headView = id_navigation_view.getHeaderView(0);
        TextView id_tv_navigation_header_name = findById(headView, R.id.id_tv_navigation_header_name);
        id_tv_navigation_header_name.setText(AppInfoDataManager.getInstance().getUserLoginBean().getLonginUser().getYHXM());
        //设置默认选中 选中的条件是该menu的checkable为true
        //id_navigation_view.setCheckedItem(R.id.id_menu_item_patient_list);
        //设置默认选中
        if (id_navigation_view.getMenu() != null && id_navigation_view.getMenu().size() > 0) {
            mMenuItem = id_navigation_view.getMenu().getItem(0);
            mMenuItem.setCheckable(true);
            //选中的条件是该menu的checkable为true
            id_navigation_view.setCheckedItem(mMenuItem.getItemId());
        }
        id_navigation_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //同一个item直接关闭
                if (mMenuItem == item) {
                    //关闭抽屉
                    id_drawer_layout.closeDrawers();
                    return false;
                }
                switch (item.getItemId()) {
                    case R.id.id_menu_item_patient_list:
                        break;
                    case R.id.id_menu_item_daily_work:
                        break;
                    case R.id.id_menu_item_check_drug:
                        break;
                    case R.id.id_menu_item_batch_signs:
                        break;
                    case R.id.id_menu_item_specimen_collection:
                        break;
                }
                //改变标题
                configTitle(item.getTitle());
                //选中的条件是该menu的checkable为true
                item.setCheckable(true);
                //将选中设为文字选中的状态
                //item.setChecked(true);
                //
                //id_navigation_view.setCheckedItem(item.getItemId());
                //关闭抽屉
                id_drawer_layout.closeDrawers();
                //
                mMenuItem = item;
                return true;
            }
        });
    }

    private void initBottomNavigationView() {
        id_bottom_navigation_view_support.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.id_menu_item_1:
                        switchFragment(0);
                        return true;
                    case R.id.id_menu_item_2:
                        switchFragment(1);
                        return true;
                    case R.id.id_menu_item_3:
                        switchFragment(2);
                        return true;
                    case R.id.id_menu_item_4:
                        switchFragment(3);
                        return true;
                }
                return false;
            }
        });
        id_bottom_navigation_view_support.setClickedItem(R.id.id_menu_item_1);
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

    private void configTitle(CharSequence title) {
        Toolbar toolbar = findById(R.id.id_toolbar);
        setSupportActionBar(toolbar);
        // ActionBar actionBar = getSupportActionBar();
        //## actionBar.setDisplayHomeAsUpEnabled(true);
        //必须设置在setSupportActionBar(mToolbar);后才有效
        toolbar.setNavigationIcon(R.drawable.ic_menu_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id_drawer_layout.openDrawer(Gravity.START);
            }
        });
        setTitle(title);
    }

    private ClassichuPopupWindow mClassichuPopupWindow;

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
        recyclerView.setBackgroundResource(R.drawable.shape_shadow_bg);
        // ViewCompat.setElevation(recyclerView,22);
        //hideLastDivider
        RecyclerViewDivider.with(mContext).color(Color.parseColor("#FFD1CFCF")).build().addTo(recyclerView);
        recyclerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        recyclerView.addOnItemTouchListener(new SimpleOnRVItemTouchListener(recyclerView) {
            @Override
            public void onItemClick(View view, int position) {
                super.onItemClick(view, position);
                // ToastTool.show("onItemClick");
                UserLoginBean.AreasBean areasBean = areaListAdapter.getData(position);
                AppInfoDataManager.getInstance().setAreasBean(areasBean);
                //
                if (mCurrentFragment instanceof MainFragment) {
                    MainFragment mainFragment = (MainFragment) mCurrentFragment;
                    mainFragment.callAtAty_toRefreshData();
                }
                id_tv_area.setText(areasBean.getKSMC());
                //
                mClassichuPopupWindow.dismiss();
            }
        });
        recyclerView.setAdapter(areaListAdapter);
        //
        mClassichuPopupWindow = new ClassichuPopupWindow.Builder(mContext).setView(recyclerView).build();
        mClassichuPopupWindow.showCenter(view, -view.getHeight() / 5);

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
                if (mCurrentFragment instanceof MainFragment) {
                    MainFragment mainFragment = (MainFragment) mCurrentFragment;
                    mainFragment.callAtAty_switchGridOrList();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private BaseFragment mCurrentFragment;

    private void switchFragment(int position) {
        BaseFragment baseFragment = MainAtyFragmentFactory.create(position);
        showFragment(baseFragment);
    }

    /**
     * 控制 fragment 来回切换  的显示或隐藏
     *
     * @param toBaseFragment
     */
    private void showFragment(BaseFragment toBaseFragment) {
        if (toBaseFragment != mCurrentFragment) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (mCurrentFragment != null) {
                transaction.hide(mCurrentFragment);
            }
            if (!toBaseFragment.isAdded()) {
                transaction.add(R.id.id_frame_layout_content, toBaseFragment);
            }
            //
            transaction.show(toBaseFragment).commitAllowingStateLoss();
            //
            mCurrentFragment = toBaseFragment;
        }
    }
}
