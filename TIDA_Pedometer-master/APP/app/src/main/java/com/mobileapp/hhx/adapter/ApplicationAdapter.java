package com.mobileapp.hhx.adapter;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mobileapp.hhx.asynctask.DealWithApplicationTask;
import com.mobileapp.hhx.asynctask.FindUserByIdTask;
import com.mobileapp.hhx.asynctask.ImageLoadTask;
import com.mobileapp.hhx.entity.FriendApplication;
import com.mobileapp.hhx.entity.User;
import com.mobileapp.hhx.pedometer.R;
import com.mobileapp.hhx.pedometer.UserIndexActivity;

import java.util.List;

/**
 * Created by hhx on 2017/4/20.
 */

public class ApplicationAdapter extends BaseAdapter {
    private ImageView photoView;
    private TextView nameView, timeView, remarkView, stateView;
    private Button btnAccept, btnIgnore;
    private LayoutInflater layoutInflater;
    private Context context;
    private List<FriendApplication> dataList;
    private String path = "", responseText = "";
    private Handler handler;
    private int userid, applier_id;

    public ApplicationAdapter(Context context, List<FriendApplication> dataList) {
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
    public FriendApplication getItem(int i) {
        return dataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        int a = i;
        view = layoutInflater.inflate(R.layout.layout_application, null);
        FriendApplication fr = dataList.get(i);

        photoView = (ImageView) view.findViewById(R.id.appPhoto);
        nameView = (TextView) view.findViewById(R.id.appName);
        timeView = (TextView) view.findViewById(R.id.appTime);
        remarkView = (TextView) view.findViewById(R.id.appRemark);
        stateView = (TextView) view.findViewById(R.id.appState);
        btnAccept = (Button) view.findViewById(R.id.appBtnAccept);
        btnIgnore = (Button) view.findViewById(R.id.appBtnIgnore);

        userid = fr.getUserid();
        applier_id = fr.getApplier_id();
        handler = new Handler(new FindUserByIdTaskCallBack());
        new FindUserByIdTask(applier_id, handler).start();

        Gson gson = new Gson();
        User applier = gson.fromJson(responseText, User.class);

        new ImageLoadTask(photoView).execute(path + applier.getPhoto());
        nameView.setText(applier.getUsername());
        timeView.setText(fr.getTime());
        remarkView.setText(fr.getRemark());
        stateView.setText(fr.getState());

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Handler handler_1 = new Handler(new DealWithApplicationCallBack());
                new DealWithApplicationTask(userid, applier_id, 2, handler_1).start();
                if("true".equals(responseText))
                    Toast.makeText(context, "添加好友成功！", Toast.LENGTH_SHORT).show();
                btnAccept.setClickable(false);
                btnIgnore.setClickable(false);
            }
        });

        btnIgnore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Handler handler_1 = new Handler(new DealWithApplicationCallBack());
                new DealWithApplicationTask(userid, applier_id, 4, handler_1).start();
                if("true".equals(responseText))
                    Toast.makeText(context, "已忽略好友请求！", Toast.LENGTH_SHORT).show();
                btnAccept.setClickable(false);
                btnIgnore.setClickable(false);
            }
        });

        photoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UserIndexActivity.class);
                intent.putExtra("userid", applier_id);
                context.startActivity(intent);
            }
        });

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

    private class DealWithApplicationCallBack implements Handler.Callback{

        @Override
        public boolean handleMessage(Message message) {
            if(message.arg1 == 1){
                responseText = (String) message.obj;
            }
            return true;
        }
    }
}


