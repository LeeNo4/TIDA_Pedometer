package com.mobileapp.hhx.pedometer;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mobileapp.hhx.adapter.ApplicationAdapter;
import com.mobileapp.hhx.app.MyApp;
import com.mobileapp.hhx.asynctask.GetFriendListTask;
import com.mobileapp.hhx.entity.FriendApplication;
import com.mobileapp.hhx.entity.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Homepage_ApplicationActivity extends AppCompatActivity {
    private Handler handler;
    private String responseText;
    private User user;
    private int userid, type;
    private List<FriendApplication> list;
    private ListView lstApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage__application);

        MyApp app = (MyApp)getApplicationContext();
        user = app.getUser();
        userid = user.getUserid();
        type = 2;
        list = new ArrayList<FriendApplication>();
        lstApplication = (ListView) findViewById(R.id.lstApplication);

        handler = new Handler(new GetApplicationTaskCallBack());
        new GetFriendListTask(userid, type, handler).start();

        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonElement el = parser.parse(responseText);
        JsonArray jsonArray = null;
        if(el.isJsonArray())
            jsonArray = el.getAsJsonArray();
        Iterator it = jsonArray.iterator();
        while(it.hasNext()){
            JsonElement e = (JsonElement)it.next();
            FriendApplication f =  gson.fromJson(e, FriendApplication.class);
            list.add(f);
        }

        ApplicationAdapter appAdapter = new ApplicationAdapter(this, list);
        lstApplication.setAdapter(appAdapter);
        appAdapter.notifyDataSetChanged();
    }

    private class GetApplicationTaskCallBack implements Callback{

        @Override
        public boolean handleMessage(Message message) {
            if(message.arg1 == 1){
                responseText = (String)message.obj;
            }
            return true;
        }
    }
}
