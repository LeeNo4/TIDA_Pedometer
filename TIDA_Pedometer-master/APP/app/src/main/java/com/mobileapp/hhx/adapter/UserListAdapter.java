package com.mobileapp.hhx.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobileapp.hhx.asynctask.ImageLoadTask;
import com.mobileapp.hhx.entity.User;
import com.mobileapp.hhx.pedometer.R;
import com.mobileapp.hhx.pedometer.UserIndexActivity;

import java.util.List;

/**
 * Created by hhx on 2017/4/20.
 */

public class UserListAdapter extends BaseAdapter {
    private ImageView photoView;
    private TextView nameView, remarkView;
    private Button btnFriends;
    private LayoutInflater layoutInflater;
    private Context context;
    private List<User> dataList;
    private User user;
    private String path = "";

    public UserListAdapter(Context context, List<User> dataList) {
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
    public User getItem(int i) {
        return dataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        user = dataList.get(i);
        photoView = (ImageView) view.findViewById(R.id.listPhoto);
        nameView = (TextView) view.findViewById(R.id.listName);
        remarkView = (TextView) view.findViewById(R.id.listRemark);
        btnFriends = (Button) view.findViewById(R.id.btnFriends);
        new ImageLoadTask(photoView).execute(path + user.getPhoto());
        nameView.setText(user.getUsername());
        remarkView.setText(user.getRemark());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int userid = user.getUserid();
                Intent intent = new Intent(context, UserIndexActivity.class);
                intent.putExtra("userid", userid);
                context.startActivity(intent);
            }
        });

        btnFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }
}
