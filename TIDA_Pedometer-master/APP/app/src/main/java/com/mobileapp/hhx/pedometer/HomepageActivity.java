package com.mobileapp.hhx.pedometer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobileapp.hhx.app.MyApp;
import com.mobileapp.hhx.asynctask.ImageLoadTask;
import com.mobileapp.hhx.entity.User;

public class HomepageActivity extends AppCompatActivity {
    private TextView nameTxtView, remarkTxtView, modifyTxtView, messageTxtView, helpTxtView;
    private ImageView imgView, moreView;
    private String path = "";
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        imgView = (ImageView) findViewById(R.id.photo);
        moreView = (ImageView) findViewById(R.id.moreInfo);
        nameTxtView = (TextView) findViewById(R.id.username);
        remarkTxtView = (TextView) findViewById(R.id.remark);
        modifyTxtView = (TextView) findViewById(R.id.txtModifyPswd);
        messageTxtView = (TextView) findViewById(R.id.txtMessages);
        helpTxtView = (TextView) findViewById(R.id.txtHelp);

        MyApp app = (MyApp) getApplicationContext();
        user = app.getUser();
        //new ImageLoadTask(imgView).execute(path + user.getPhoto());
        nameTxtView.setText(user.getUsername());
        if("".equals(user.getRemark()))
            remarkTxtView.setText("TA很懒，什么都没留下");
        else
            remarkTxtView.setText(user.getRemark().substring(0,10) + "...");

        imgView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomepageActivity.this, UserIndexActivity.class);
//                intent.putExtra("userid", user.getUserid());
                startActivity(intent);
            }
        });

        moreView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomepageActivity.this, Homepage_SettingsActivity.class);
                startActivity(intent);
            }
        });

        modifyTxtView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomepageActivity.this, Homepage_ModifyPswdActivity.class);
                startActivity(intent);
            }
        });

        messageTxtView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomepageActivity.this, Homepage_MessagesActivity.class);
                startActivity(intent);
            }
        });

        helpTxtView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomepageActivity.this, Homepage_HelpActivity.class);
                startActivity(intent);

            }
        });
    }
}
