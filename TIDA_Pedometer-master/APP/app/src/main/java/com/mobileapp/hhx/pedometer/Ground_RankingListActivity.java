package com.mobileapp.hhx.pedometer;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mobileapp.hhx.adapter.RankingListAdapter;
import com.mobileapp.hhx.app.MyApp;
import com.mobileapp.hhx.asynctask.GetTodayRecordTask;
import com.mobileapp.hhx.entity.TodayRecord;
import com.mobileapp.hhx.entity.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Ground_RankingListActivity extends AppCompatActivity {
    private Handler handler;
    private String responseText = "";
    private User user;
    private int type = 0, userid = 0;
    private List<TodayRecord> dataList;
    private ListView lstRanking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ground__ranking_list);

        lstRanking = (ListView) findViewById(R.id.lstRanking);
        dataList = new ArrayList<TodayRecord>();
        MyApp app = (MyApp) getApplicationContext();
        user = app.getUser();
        userid = user.getUserid();
        handler = new Handler(new GetTodayRecordCallBack());
        new GetTodayRecordTask(userid, type, handler).start();

        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonElement el = parser.parse(responseText);
        JsonArray jsonArray = null;
        if(el.isJsonArray())
            jsonArray = el.getAsJsonArray();
        Iterator it = jsonArray.iterator();
        while(it.hasNext()) {
            JsonElement e = (JsonElement) it.next();
            TodayRecord tr = gson.fromJson(e, TodayRecord.class);
            dataList.add(tr);
        }

        RankingListAdapter rla = new RankingListAdapter(this, dataList);
        lstRanking.setAdapter(rla);
        rla.notifyDataSetChanged();
    }

    private class GetTodayRecordCallBack implements Handler.Callback{

        @Override
        public boolean handleMessage(Message msg) {
            if(msg.arg1 == 1){
                responseText = (String) msg.obj;
            }
            return true;
        }
    }
}


