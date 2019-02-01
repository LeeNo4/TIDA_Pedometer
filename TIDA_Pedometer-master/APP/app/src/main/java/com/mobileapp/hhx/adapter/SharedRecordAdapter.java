package com.mobileapp.hhx.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mobileapp.hhx.asynctask.FindUserByIdTask;
import com.mobileapp.hhx.asynctask.ImageLoadTask;
import com.mobileapp.hhx.entity.SharedRecord;
import com.mobileapp.hhx.entity.User;
import com.mobileapp.hhx.pedometer.R;

import java.util.List;

/**
 * Created by hhx on 2017/4/24.
 */

public class SharedRecordAdapter extends BaseAdapter {
    private ImageView userPhotoView, srPhotoView;
    private TextView nameView, remarkView, stepsView, caloriesView, active_minutesView, timeView;
    private LayoutInflater layoutInflater;
    private Context context;
    private List<SharedRecord> dataList;
    private String path = "", responseText = "";
    private Handler handler;
    private int userid;

    public SharedRecordAdapter(Context context, List<SharedRecord> dataList){
        super();
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.dataList = dataList;

    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public SharedRecord getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = layoutInflater.inflate(R.layout.layout_sharedrecord, null);
        SharedRecord sr = dataList.get(position);
        userPhotoView = (ImageView) view.findViewById(R.id.srUserPhoto);
        srPhotoView = (ImageView) view.findViewById(R.id.srPhoto);
        nameView = (TextView) view.findViewById(R.id.srUserName);
        remarkView = (TextView) view.findViewById(R.id.srRemark);
        stepsView = (TextView) view.findViewById(R.id.srSteps);
        caloriesView = (TextView) view.findViewById(R.id.srCalories);
        active_minutesView = (TextView) view.findViewById(R.id.srActive_minutes);
        timeView = (TextView) view.findViewById(R.id.srTime);

        userid = sr.getUserid();
        handler = new Handler(new FindUserByIdTaskCallBack());
        new FindUserByIdTask(userid, handler).start();

        Gson gson = new Gson();
        User user = gson.fromJson(responseText, User.class);
        new ImageLoadTask(userPhotoView).execute(path + user.getPhoto());
        new ImageLoadTask(srPhotoView).execute(path + sr.getPhoto());
        nameView.setText(user.getUsername());
        remarkView.setText(sr.getRemark());
        stepsView.setText(sr.getSteps());
        caloriesView.setText(sr.getCalories() + "");
        active_minutesView.setText(sr.getMinutes() + "");
        timeView.setText(sr.getTime());
        return view;
    }

    private class FindUserByIdTaskCallBack implements Handler.Callback{

        @Override
        public boolean handleMessage(Message message) {
            if(message.arg1 == 1){
                responseText = (String) message.obj;
            }
            return true;
        }
    }
}
