package com.mobileapp.hhx.pedometer;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mobileapp.hhx.adapter.MessageListAdapter;
import com.mobileapp.hhx.app.MyApp;
import com.mobileapp.hhx.asynctask.GetMessageTask;
import com.mobileapp.hhx.entity.Messages;
import com.mobileapp.hhx.entity.User;

import java.util.Iterator;
import java.util.List;

public class Homepage_MessagesListActivity extends AppCompatActivity {
    private Handler handler;
    private ListView lstMessage;
    private int type;
    private User user;
    private String responseText;
    private List<Messages> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage__message_list);

        Intent intent = this.getIntent();
        String typeStr = intent.getStringExtra("isRead");
        if("r".equals(typeStr)) type = 1;
        if("u".equals(typeStr)) type = 0;

        MyApp app = (MyApp) getApplicationContext();
        user = app.getUser();
        handler = new Handler(new GetMessageTaskCallBack());
        new GetMessageTask(user.getUserid(), type, handler).start();

        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonElement el = parser.parse(responseText);
        JsonArray jsonArray = null;
        if(el.isJsonArray())
            jsonArray = el.getAsJsonArray();
        Iterator it = jsonArray.iterator();
        while(it.hasNext()){
            JsonElement e = (JsonElement)it.next();
            Messages msg =  gson.fromJson(e, Messages.class);
            list.add(msg);
        }

        lstMessage = (ListView) findViewById(R.id.lstMessage);
        MessageListAdapter mla = new MessageListAdapter(this, list);
        lstMessage.setAdapter(mla);
        mla.notifyDataSetChanged();
    }

    private class GetMessageTaskCallBack implements Handler.Callback{

        @Override
        public boolean handleMessage(Message message) {
            if(message.arg1 == 1){
                responseText = (String)message.obj;
            }
            return true;
        }
    }
}
