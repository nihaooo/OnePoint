package com.example.bmobtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by www10 on 2017/4/5.
 */

public class LostAdapter extends BaseAdapter {

    private List<Lost> mList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private static final String TAG = "LostAdapter";

    public LostAdapter(Context context, List<Lost> list) {
        mContext = context;
        mList = list;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.list_item, null);
            viewHolder.mTvLostTitle1 = (TextView) convertView.findViewById(R.id.tv_title1);
            viewHolder.mTvLostPhone1 = (TextView) convertView.findViewById(R.id.tv_phone1);
            viewHolder.mTvLostDescribe1 = (TextView) convertView.findViewById(R.id.tv_describe1);
            viewHolder.mTvLostDate1 = (TextView) convertView.findViewById(R.id.send_time);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Lost lost = mList.get(position);
        viewHolder.mTvLostTitle1.setText(lost.getTitle());
        viewHolder.mTvLostPhone1.setText(lost.getPhone());
        viewHolder.mTvLostDescribe1.setText(lost.getDescribe());
        viewHolder.mTvLostDate1.setText(lost.getTime());
        return convertView;
    }

    public class ViewHolder {
        public TextView mTvLostTitle1;
        public TextView mTvLostPhone1;
        public TextView mTvLostDescribe1;
        public TextView mTvLostDate1;
    }
}
