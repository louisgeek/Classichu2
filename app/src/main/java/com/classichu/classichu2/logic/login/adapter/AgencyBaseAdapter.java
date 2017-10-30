package com.classichu.classichu2.logic.login.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.classichu.classichu2.logic.login.bean.AgencyBean;

import java.util.List;

/**
 * Created by Classichu on 2017-7-17.
 */

public class AgencyBaseAdapter extends BaseAdapter{

    private List<AgencyBean> mAgencyBeanList;
    public AgencyBaseAdapter(List<AgencyBean> agencyBeanList) {
        this.mAgencyBeanList = agencyBeanList;
    }

    @Override
    public int getCount() {
        return mAgencyBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return mAgencyBeanList.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            convertView= LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, null);
            viewHolder=new ViewHolder();
            viewHolder.textView= (TextView) convertView.findViewById(android.R.id.text1);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(mAgencyBeanList.get(position).getJGMC());
        return convertView;
    }

    class ViewHolder{
        private TextView textView;
    }
}
