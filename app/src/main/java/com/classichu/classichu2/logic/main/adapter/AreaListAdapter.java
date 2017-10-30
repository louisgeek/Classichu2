package com.classichu.classichu2.logic.main.adapter;

import android.widget.TextView;

import com.classichu.adapter.recyclerview.ClassicRecyclerViewAdapter;
import com.classichu.adapter.recyclerview.ClassicRecyclerViewHolder;
import com.classichu.classichu2.R;
import com.classichu.classichu2.logic.login.bean.UserLoginBean;
import com.classichu.classichu2.tool.EmptyTool;

import java.util.List;

/**
 * Created by louisgeek on 2017/8/10.
 */

public class AreaListAdapter extends ClassicRecyclerViewAdapter<UserLoginBean.AreasBean> {


    public AreaListAdapter(List<UserLoginBean.AreasBean> mDataList, int mItemLayoutId) {
        super(mDataList, mItemLayoutId);
    }

    @Override
    public void findBindView(int pos, ClassicRecyclerViewHolder classicRecyclerViewHolder) {
        if (!EmptyTool.isEmpty(mDataList)) {
            TextView id_tv_item_title = classicRecyclerViewHolder.findBindItemView(R.id.id_tv_item_title);
            id_tv_item_title.setText(mDataList.get(pos).getKSMC());
        }
    }

}
