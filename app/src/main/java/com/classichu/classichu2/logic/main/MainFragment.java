package com.classichu.classichu2.logic.main;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.classichu.adapter.listener.SimpleOnRVItemTouchListener;
import com.classichu.adapter.widget.ClassicEmptyView;
import com.classichu.classichu2.R;
import com.classichu.classichu2.base.BaseMvpFragment;
import com.classichu.classichu2.logic.main.adapter.MainAdapter;
import com.classichu.classichu2.logic.main.bean.PatientListBean;
import com.classichu.classichu2.tool.ToastTool;
import com.fondesa.recyclerviewdivider.RecyclerViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends BaseMvpFragment<MainPresenter> implements MainContract.View<List<PatientListBean>> {

    @BindView(R.id.id_recycler_view)
    RecyclerView id_recycler_view;
    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
       /* args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);*/
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
          /*  mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);*/
        }
    }


    @Override
    protected int setupLayoutResId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initView(View rootLayout, Bundle savedInstanceState) {
        initRecyclerViewAndAdapter();
        toRefreshData();
    }


    private MainAdapter mAdapter;


    private void initRecyclerViewAndAdapter() {

        List<PatientListBean> dataList = new ArrayList<>();
        /**
         *设置Adapter
         */
        mAdapter = new MainAdapter(mContext, dataList, R.layout.item_list_normal);
        //config
        id_recycler_view.setLayoutManager(new LinearLayoutManager(mContext));
        id_recycler_view.setHasFixedSize(true);
        id_recycler_view.setItemAnimator(new DefaultItemAnimator());
        //hideLastDivider
        RecyclerViewDivider.with(mContext).hideLastDivider().build().addTo(id_recycler_view);
        //
        ClassicEmptyView classicEmptyView = new ClassicEmptyView(mContext);
        classicEmptyView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        classicEmptyView.setOnEmptyViewClickListener(new ClassicEmptyView.OnEmptyViewClickListener() {
            @Override
            public void onClickEmptyView(View view) {
                super.onClickEmptyView(view);
                toRefreshData();
            }
        });
        mAdapter.setEmptyView(classicEmptyView);
        /**
         *RecyclerView设置Adapter
         */
        id_recycler_view.setAdapter(mAdapter);
        id_recycler_view.addOnItemTouchListener(new SimpleOnRVItemTouchListener(id_recycler_view){
            @Override
            public void onItemClick(View view, int position) {
                super.onItemClick(view, position);
                ToastTool.show("onItemClick");
            }

            @Override
            public void onItemLongClick(View view, int position) {
                super.onItemLongClick(view, position);
                ToastTool.show("onItemLongClick");
            }
        });

    /*    id_recycler_view.setOnTouchListener(new OnRecyclerViewTouchListener() {
            @Override
            public void onScrollUp() {

            }

            @Override
            public void onScrollDown() {
                if (ViewTool.isReachedBottom(id_recycler_view) &&
                        !mClassicRVHeaderFooterAdapter.isDataLoading() &&
                        !mClassicRVHeaderFooterAdapter.isLoadComplete()
                        ) {
                    toLoadMoreData();
                }
            }
        });*/


    }


    @Override
    protected MainPresenter setupPresenter() {
        return new MainPresenter(null, this);
    }

    @Override
    public void showLoading() {
        showSwipeRefreshLayout();
    }

    @Override
    public void hideLoading() {
        hideSwipeRefreshLayout();
    }

    @Override
    public void showMessage(String msg) {
        showSnack(msg);
    }

    @Override
    protected int configSwipeRefreshLayoutResId() {
        return R.id.id_swipe_refresh_layout;
    }

    @Override
    protected void toRefreshData() {
        mPresenter.gainData();
    }
    public void callAtAty_toRefreshData() {
        toRefreshData();
    }

    @Override
    public void setupData(List<PatientListBean> patientListBeen) {
        mAdapter.setEmptyViewVisibility();
        mAdapter.refreshDataList(patientListBeen);
    }

    private boolean isFilterMyPatients;
    @Override
    public boolean isFilterMyPatients() {
        return isFilterMyPatients;
    }


    public void callAtAty_switchGridOrList() {
        switchGridOrList();
    }
    private void switchGridOrList() {
        if (id_recycler_view == null) {
            return;
        }
        if (id_recycler_view.getLayoutManager() instanceof GridLayoutManager) {
            id_recycler_view.setLayoutManager(new LinearLayoutManager(mContext));
        }/*  else if(mRecyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        }*/else {
            id_recycler_view.setLayoutManager(new GridLayoutManager(mContext, 2));
            //让合并单元格的设置生效
            mAdapter.callAfterChangeGridLayoutManager(id_recycler_view);
        }
        // mClassicRVHeaderFooterAdapter.notifyItemChanged(mClassicRVHeaderFooterAdapter.getItemCount()-1);
        //重新设置 让合并单元格的设置生效 (此方式很卡)
        //  mRecyclerView.setAdapter(mClassicRVHeaderFooterAdapter);
        //  mClassicRVHeaderFooterAdapter.notifyDataSetChanged();
    }

}
