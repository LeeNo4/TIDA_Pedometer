package com.mobileapp.hhx.pedometer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mobileapp.hhx.app.MyApp;
import com.mobileapp.hhx.entity.User;

//显示等待时间
public class WelcomeActivity extends AppCompatActivity {
    private SharedPreferences sp;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
//                User user = new User(1, "whatislove", "111", "18202663199", "photo.jpg", 1, 173, 70.5f,
//                8, 13.4f, 234.6f, 12342, "沉迷运动，日渐消瘦");
                sp = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
//                String userStr = "{'userid':1,'username':'11','password':'111','gender':0,'height':173,'weight':70.5,'activeDays':6,'activeHours':11.4,'calories':23.5,'steps':0,'remark':129947857875878}";

                String userStr = sp.getString("User", "");
                if("".equals(userStr)){
                    Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Gson gson = new Gson();
                    User user = gson.fromJson(userStr, User.class);

                    MyApp app = (MyApp)getApplicationContext();
                    app.setUser(user);
                    Intent intent = new Intent(WelcomeActivity.this, HomepageActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };

        new Thread(new WaitThreeSecondsThread()).start();
        Toast.makeText(getApplicationContext(), "欢迎登录，3s后自动跳转", Toast.LENGTH_SHORT).show();
    }

    private class WaitThreeSecondsThread implements Runnable{

        @Override
        public void run() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            handler.sendEmptyMessage(1);
        }
    }
}
