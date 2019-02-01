package com.mobileapp.hhx.pedometer;

import android.content.Intent;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mobileapp.hhx.app.MyApp;
import com.mobileapp.hhx.asynctask.GetMessageTask;
import com.mobileapp.hhx.entity.User;
import com.mobileapp.hhx.entity.Messages;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Homepage_MessagesActivity extends AppCompatActivity {
    private TextView txtUnread, txtRead, txtApps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage__messages);

        txtUnread = (TextView) findViewById(R.id.txtUnreadMsg);
        txtRead = (TextView) findViewById(R.id.txtReadMsg);
        txtApps = (TextView) findViewById(R.id.txtApplication);

        txtUnread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homepage_MessagesActivity.this, Homepage_MessagesListActivity.class);
                intent.putExtra("isRead", "u");
                startActivity(intent);
            }
        });

        txtRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homepage_MessagesActivity.this, Homepage_MessagesListActivity.class);
                intent.putExtra("isRead", "r");
                startActivity(intent);
            }
        });

        txtApps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homepage_MessagesActivity.this, Homepage_ApplicationActivity.class);
                startActivity(intent);
            }
        });
    }


}
