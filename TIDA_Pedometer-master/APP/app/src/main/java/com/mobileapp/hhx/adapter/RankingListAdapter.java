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
import com.mobileapp.hhx.entity.TodayRecord;
import com.mobileapp.hhx.entity.User;
import com.mobileapp.hhx.pedometer.R;

import org.apache.http.conn.scheme.HostNameResolver;

import java.util.List;

/**
 * Created by hhx on 2017/4/25.
 */

public class RankingListAdapter extends BaseAdapter {
    private List<TodayRecord> dataList;
    private Handler handler;
    private Context context;
    private LayoutInflater layoutInflater;
    private ImageView photoView;
    private TextView nameView, stepsView, caloriesView;
    private String responseText = "";
    private int userid;
    private String path = "";

    public RankingListAdapter(Context context, List<TodayRecord> dataList) {
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
    public TodayRecord getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = layoutInflater.inflate(R.layout.layout_ranking, null);
        TodayRecord tr = dataList.get(position);

        photoView = (ImageView) view.findViewById(R.id.rankPhoto);
        nameView = (TextView) view.findViewById(R.id.rankName);
        stepsView = (TextView) view.findViewById(R.id.rankSteps);
        caloriesView = (TextView) view.findViewById(R.id.rankCalories);

        userid = tr.getUserid();
        handler = new Handler(new FindUserByIdTaskCallBack());
        new FindUserByIdTask(userid, handler).start();

        Gson gson = new Gson();
        User user = gson.fromJson(responseText, User.class);
        new ImageLoadTask(photoView).execute(path + user.getPhoto());
        nameView.setText(user.getUsername());
        stepsView.setText(tr.getSteps());
        caloriesView.setText(tr.getCalories() + "");

        return view;
    }

    private class FindUserByIdTaskCallBack implements Handler.Callback {

        @Override
        public boolean handleMessage(Message message) {
            if (message.arg1 == 1) {
                responseText = (String) message.obj;
            }
            return true;
        }
    }
}
