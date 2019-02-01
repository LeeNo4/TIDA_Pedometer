package com.mobileapp.hhx.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobileapp.hhx.app.MyApp;
import com.mobileapp.hhx.entity.Messages;
import com.mobileapp.hhx.pedometer.Homepage_MessagesDetailActivity;
import com.mobileapp.hhx.pedometer.R;

import java.util.List;

/**
 * Created by hhx on 2017/4/20.
 */

public class MessageListAdapter extends BaseAdapter {
    private TextView contView, timeView;
    private LayoutInflater layoutInflater;
    private Context context;
    private List<Messages> dataList;
    private String path = "";
    private Messages msg;

    public MessageListAdapter(Context context, List<Messages>dataList){
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
    public Messages getItem(int i) {
        return dataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        msg = dataList.get(i);

        contView = (TextView) view.findViewById(R.id.msgCont);
        timeView = (TextView) view.findViewById(R.id.msgTime);
        contView.setText(msg.getContent().substring(0, 15) + "...");
        timeView.setText(msg.getDate());

        contView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyApp app = (MyApp)context.getApplicationContext();
                app.setMessage(msg);
                Intent intent = new Intent(context, Homepage_MessagesDetailActivity.class);
                context.startActivity(intent);
            }
        });
        return view;
    }
}
