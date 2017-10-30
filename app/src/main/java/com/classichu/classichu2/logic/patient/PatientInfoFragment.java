package com.classichu.classichu2.logic.patient;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.classichu.adapter.recyclerview.ClassicRVHeaderFooterAdapter;
import com.classichu.adapter.widget.ClassicEmptyView;
import com.classichu.classichu2.R;
import com.classichu.classichu2.base.BaseMvpFragment;
import com.classichu.classichu2.logic.patient.adapter.PatientAdapter;
import com.classichu.classichu2.tool.ToastTool;
import com.fondesa.recyclerviewdivider.RecyclerViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PatientInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PatientInfoFragment extends BaseMvpFragment<PatientPresenter> implements PatientContract.View<List<String>> {


    public PatientInfoFragment() {
        // Required empty public constructor
    }

    public static PatientInfoFragment newInstance(String param1, String param2) {
        PatientInfoFragment fragment = new PatientInfoFragment();
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
           /* mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);*/
        }
    }


    @Override
    protected int setupLayoutResId() {
        return R.layout.fragment_patient_info;
    }

    @Override
    protected void initView(View rootLayout, Bundle savedInstanceState) {
        initRecyclerViewAndAdapter();
        toRefreshData();
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
        ToastTool.show(msg);
    }

    @Override
    public void setupData(List<String> stringList) {
        mAdapter.refreshDataList(stringList);
        id_recycler_view.setVisibility(View.VISIBLE);
    }


    @Override
    protected PatientPresenter setupPresenter() {
        return new PatientPresenter(null, this);
    }


    @Override
    protected void toRefreshData() {
        mPresenter.gainData();
    }

    protected void toLoadPatientInfoDetail() {
        mPresenter.gainPatientInfoDetail();
    }

    private ClassicRVHeaderFooterAdapter mAdapter;
    @BindView(R.id.id_recycler_view)
    RecyclerView id_recycler_view;

    private void initRecyclerViewAndAdapter() {

        List<String> stringList = new ArrayList<>();
        mAdapter = new PatientAdapter(mContext, stringList, R.layout.item_list_classic);

        //config
        id_recycler_view.setLayoutManager(new LinearLayoutManager(mContext));
        id_recycler_view.setHasFixedSize(true);
        id_recycler_view.setItemAnimator(new DefaultItemAnimator());
        //hideLastDivider
        RecyclerViewDivider.with(mContext).hideLastDivider().build().addTo(id_recycler_view);


        ClassicEmptyView classicEmptyView = new ClassicEmptyView(getContext());
        classicEmptyView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        classicEmptyView.setOnEmptyViewClickListener(new ClassicEmptyView.OnEmptyViewClickListener() {
            @Override
            public void onClickTextView(View view) {
                super.onClickTextView(view);
                toRefreshData();
            }

            @Override
            public void onClickImageView(View view) {
                super.onClickImageView(view);
                toRefreshData();
            }

            @Override
            public void onClickEmptyView(View view) {
                super.onClickEmptyView(view);
                toRefreshData();
            }
        });
        mAdapter.setEmptyView(classicEmptyView);
        mAdapter.setOnItemClickListener(new ClassicRVHeaderFooterAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                super.onItemClick(itemView, position);
                ToastTool.showCenter("sda" + position);
                // startAty(PatientActivity.class);
            }
        });


        id_recycler_view.setAdapter(mAdapter);
        id_recycler_view.setVisibility(View.GONE);//初始化 不显示
    }


    @Override
    public void setupPatientInfoDetail(List<String> stringList) {
        mAdapter.addDataAtEnd(stringList);

    }


}
