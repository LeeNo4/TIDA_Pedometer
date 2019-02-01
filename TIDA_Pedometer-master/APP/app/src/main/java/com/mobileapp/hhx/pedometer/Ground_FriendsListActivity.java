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
import com.mobileapp.hhx.app.MyApp;
import com.mobileapp.hhx.asynctask.GetFriendListTask;
import com.mobileapp.hhx.entity.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Ground_FriendsListActivity extends AppCompatActivity {
    private Handler handler;
    private String responseText;
    private User user;
    private int userid, type;
    private List<User> list;
    private ListView lstUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ground__friends_list);

        lstUsers = (ListView) findViewById(R.id.lstUsers);

        MyApp app = (MyApp)getApplicationContext();
        user = app.getUser();
        userid = user.getUserid();
        list = new ArrayList<User>();

        //按钮响应事件
        list.clear();
        handler = new Handler(new GetFriendListTaskCallBack());
        new GetFriendListTask(userid, type, handler).start();//type为1时是好友列表，为3是陌生人列表

        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonElement el = parser.parse(responseText);
        JsonArray jsonArray = null;
        if(el.isJsonArray())
            jsonArray = el.getAsJsonArray();
        Iterator it = jsonArray.iterator();
        while(it.hasNext()){
            JsonElement e = (JsonElement)it.next();
            User u =  gson.fromJson(e, User.class);
            list.add(u);
        }
    }

    private class GetFriendListTaskCallBack implements Callback{

        @Override
        public boolean handleMessage(Message message) {
            if(message.arg1 == 1){
                responseText = (String)message.obj;
            }
            return true;
        }
    }
}
