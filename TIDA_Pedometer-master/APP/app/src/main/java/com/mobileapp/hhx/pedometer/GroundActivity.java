package com.mobileapp.hhx.pedometer;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuBuilder;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mobileapp.hhx.adapter.SharedRecordAdapter;
import com.mobileapp.hhx.app.MyApp;
import com.mobileapp.hhx.asynctask.GetSharedRecordListTask;
import com.mobileapp.hhx.entity.SharedRecord;
import com.mobileapp.hhx.entity.User;

import java.util.Iterator;
import java.util.List;

public class GroundActivity extends AppCompatActivity {
    private int userid, type = 1;
    private Handler handler;
    private String responseText = "";
    private User user;
    private List<SharedRecord> dataList;
    private ListView lstSharedRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ground);

        lstSharedRecord = (ListView) findViewById(R.id.lstSharedRecord);
        MyApp app = (MyApp) getApplicationContext();
        user = app.getUser();
        userid = user.getUserid();
        handler = new Handler(new GetSharedRecordTaskCallBack());
        new GetSharedRecordListTask(userid, type, handler).start();

        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonElement el = parser.parse(responseText);
        JsonArray jsonArray = null;
        if(el.isJsonArray())
            jsonArray = el.getAsJsonArray();
        Iterator it = jsonArray.iterator();
        while(it.hasNext()) {
            JsonElement e = (JsonElement) it.next();
            SharedRecord sr = gson.fromJson(e, SharedRecord.class);
            dataList.add(sr);
        }

        SharedRecordAdapter sra = new SharedRecordAdapter(this, dataList);
        lstSharedRecord.setAdapter(sra);
        sra.notifyDataSetChanged();
    }

    private class GetSharedRecordTaskCallBack implements Handler.Callback{

        @Override
        public boolean handleMessage(Message msg) {
            if(msg.arg1 == 1){
                responseText = (String)msg.obj;
            }
            return true;
        }
    }
}
