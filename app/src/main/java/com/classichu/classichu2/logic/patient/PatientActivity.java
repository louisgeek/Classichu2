package com.classichu.classichu2.logic.patient;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.classichu.classichu2.R;
import com.classichu.classichu2.base.BaseActivity;
import com.classichu.classichu2.base.BaseFragment;
import com.classichu.classichu2.logic.patient.factory.PatientAtyFragmentFactory;

import butterknife.BindView;


public class PatientActivity extends BaseActivity {
    //    public static final int
    @BindView(R.id.id_drawer_layout)
    DrawerLayout id_drawer_layout;
    @BindView(R.id.id_layout_content)
    LinearLayout id_layout_content;
    @BindView(R.id.id_navigation_view)
    NavigationView id_navigation_view;

    @Override
    protected int setupLayoutResId() {
        return R.layout.activity_patient;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        configTitle("用户信息");
        initNavigationView();
    }

    private void configTitle(CharSequence title) {
        Toolbar toolbar = findById(R.id.id_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        //必须设置在setSupportActionBar(mToolbar);后才有效
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setTitle(title);
    }

    private MenuItem mMenuItem;

    private void initNavigationView() {
        //隐藏滚动条
        NavigationMenuView menuView = (NavigationMenuView) id_navigation_view.getChildAt(0);
        menuView.setVerticalScrollBarEnabled(false);
        /**
         * appbar 统一设置的时候  特殊修改位置
         * 移除原有app bar， 放置到新的位置
         */
       /* ViewGroup contentViewRootLayout = (ViewGroup) getContentViewRootLayout();
        contentViewRootLayout.removeView(toolbar);
        id_layout_content.addView(toolbar, 0);*/


        //显示第一个页面
        switchFragment(R.id.id_menu_item_patient_info);


        View headView = id_navigation_view.getHeaderView(0);
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
                //切换
                switchFragment(item.getItemId());

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

    private BaseFragment mCurrentFragment;

    private void switchFragment(int itemId) {
        BaseFragment baseFragment = PatientAtyFragmentFactory.create(itemId);
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_patient, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.id_menu_patient:
                id_drawer_layout.openDrawer(Gravity.END);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
