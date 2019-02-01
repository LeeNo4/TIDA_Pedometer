package com.mobileapp.hhx.pedometer;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mobileapp.hhx.adapter.SharedRecordAdapter;
import com.mobileapp.hhx.app.MyApp;
import com.mobileapp.hhx.asynctask.FindUserByIdTask;
import com.mobileapp.hhx.entity.SharedRecord;
import com.mobileapp.hhx.entity.User;

import org.w3c.dom.Text;

import java.util.List;

//加入总步数、总卡路里等，并判断是否为自己的好友，从而显示好友申请按钮
public class UserIndexActivity extends AppCompatActivity {
    private int userid;
    private User user;
    private Handler handler;
    private String responseText;
    private ImageView photoView;
    private TextView nameView, remarkView, stepsView, timeView, caloriesView;
    private ListView lstShareRecord;
    private List<SharedRecord> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_index);

        Intent intent = this.getIntent();
        String idStr = intent.getStringExtra("userid");
        userid = Integer.parseInt(idStr);
        handler = new Handler(new FindUserByIdTaskCallBack());
        new FindUserByIdTask(userid, handler).start();

        Gson gson = new Gson();
        user = gson.fromJson(responseText, User.class);

        photoView = (ImageView) findViewById(R.id.indexPhoto);
        nameView = (TextView) findViewById(R.id.indexName);
        remarkView =(TextView) findViewById(R.id.indexRemark);
        stepsView = (TextView) findViewById(R.id.indexSteps);
        timeView = (TextView) findViewById(R.id.indexTime);
        caloriesView = (TextView) findViewById(R.id.indexCalories);
        lstShareRecord = (ListView) findViewById(R.id.lstSharedRecord);

        MyApp app = (MyApp)getApplicationContext();
        user = app.getUser();
        nameView.setText(user.getUsername());
        remarkView.setText(user.getRemark().substring(0, 10) + "...");
        stepsView.setText(user.getSteps());
        timeView.setText(user.getActiveHours() + "");
        caloriesView.setText(user.getCalories() + "");

        SharedRecordAdapter sra = new SharedRecordAdapter(this, dataList);
        lstShareRecord.setAdapter(sra);
        sra.notifyDataSetChanged();
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
