package com.classichu.classichu2.logic.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.classichu.adapter.recyclerview.ClassicRVHeaderFooterAdapter;
import com.classichu.adapter.recyclerview.ClassicRVHeaderFooterViewHolder;
import com.classichu.classichu2.R;
import com.classichu.classichu2.logic.main.bean.PatientListBean;
import com.classichu.classichu2.tool.EmptyTool;

import java.util.List;

/**
 * Created by Classichu on 2017/5/27.
 */

public class MainAdapter extends ClassicRVHeaderFooterAdapter<PatientListBean> {

    public MainAdapter(Context mContext, List<PatientListBean> mDataList, int mItemLayoutId) {
        super(mContext, mItemLayoutId);
    }

    @Override
    public RVHeaderFooterAdapterDelegate setupDelegate() {
        return new RVHeaderFooterAdapterDelegate() {
            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int pos) {

            }

            @Override
            public int getItemViewType(int pos) {
                return 0;
            }
        };
    }

    @Override
    public void findBindView(int pos, ClassicRVHeaderFooterViewHolder classicRVHeaderFooterViewHolder) {
        if (!EmptyTool.isEmpty(mDataList)) {
            TextView tv1 = classicRVHeaderFooterViewHolder.findBindItemView(R.id.id_tv_item_title);
            tv1.setText(mDataList.get(pos).getBRXM());
        }
    }
}
