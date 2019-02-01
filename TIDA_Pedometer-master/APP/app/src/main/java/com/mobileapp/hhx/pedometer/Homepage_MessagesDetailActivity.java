package com.mobileapp.hhx.pedometer;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mobileapp.hhx.app.MyApp;
import com.mobileapp.hhx.asynctask.ReadMessageTask;
import com.mobileapp.hhx.entity.Messages;
import com.mobileapp.hhx.entity.User;

public class Homepage_MessagesDetailActivity extends AppCompatActivity {
    private Handler handler;
    private Messages msg;
    private User user;
    private int userid, messageid;
    private String responseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage__message_detail);

        MyApp app = (MyApp)getApplicationContext();
        user = app.getUser();
        msg = app.getMessage();
        userid = user.getUserid();
        messageid = msg.getMessage_id();

        handler = new Handler(new ReadMessageTaskCallBack());
        new ReadMessageTask(userid, messageid, handler).start();
    }

    private class ReadMessageTaskCallBack implements Callback{

        @Override
        public boolean handleMessage(Message message) {
            if(message.arg1 == 1){
                responseText = (String)message.obj;
            }
            return true;
        }
    }
}
