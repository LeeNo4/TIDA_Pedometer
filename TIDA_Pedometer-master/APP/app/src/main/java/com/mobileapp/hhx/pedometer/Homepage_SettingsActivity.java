package com.mobileapp.hhx.pedometer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mobileapp.hhx.app.MyApp;
import com.mobileapp.hhx.asynctask.CheckNameTask;
import com.mobileapp.hhx.asynctask.ImageLoadTask;
import com.mobileapp.hhx.asynctask.ModifyInfoTask;
import com.mobileapp.hhx.entity.User;

import java.util.HashMap;
import java.util.Map;

public class Homepage_SettingsActivity extends AppCompatActivity {
    private Handler handler1, handler2;
    private Map<String, String> infos;
    private User user;
    private int userid;
    private String username, phone_no, photo, height, weight, remark;
    private String responseText1 = "", responseText2;
    private String path = "";
    private SharedPreferences sp;
    private ImageView imgPhoto;
    private EditText edtName, edtPhone, edtHeight, edtWeight, edtRemark;
    private Button btnSave;
    private MyApp app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage__settings);

        imgPhoto = (ImageView) findViewById(R.id.modifyPhoto);
        edtName = (EditText) findViewById(R.id.modifyName);
        edtPhone = (EditText) findViewById(R.id.modifyPhone);
        edtHeight = (EditText) findViewById(R.id.modifyHeight);
        edtWeight = (EditText) findViewById(R.id.modifyWeight);
        edtRemark = (EditText) findViewById(R.id.modifyRemark);
        btnSave = (Button) findViewById(R.id.btnSaveInfo);

        app = (MyApp)getApplicationContext();
        user = app.getUser();

        //new ImageLoadTask(imgPhoto).execute(path + user.getPhoto());
        edtName.setText(user.getUsername().toCharArray(), 0, user.getUsername().length());
        edtPhone.setText(user.getPhone().toCharArray(), 0, user.getPhone().length());
//        edtHeight.setText((user.getHeight()+"").toCharArray(), 0, (user.getPhone()+"").length());
//        edtWeight.setText((user.getWeight()+"").toCharArray(), 0, (user.getWeight()+"").length());
//        edtRemark.setText((user.getRemark()).toCharArray(), 0, (user.getRemark()).length());

        imgPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = edtName.getText().toString();
                phone_no = edtPhone.getText().toString();
                height = edtHeight.getText().toString();
                weight = edtWeight.getText().toString();
                remark = edtRemark.getText().toString();

                if("".equals(username) || "".equals(phone_no) || "".equals(height) || "".equals(weight) || "".equals(remark)){
                    Toast.makeText(getApplicationContext(), "请填写完整的个人信息", Toast.LENGTH_SHORT).show();
                }else {
                    if(!username.equals(user.getUsername())) {
                        handler1 = new Handler(new CheckNameTaskCallBack());
                        new CheckNameTask(username, handler1).start();
                    }

                    if ("existed".equals(responseText1)) {
                        Toast.makeText(getApplicationContext(), "用户名已被注册，请重新输入！", Toast.LENGTH_SHORT).show();
                    } else if ("not existed".equals(responseText1) || "".equals(responseText1)) {
                        infos = new HashMap<String, String>();
                        infos.put("user_name", username);
                        if (!phone_no.equals(user.getPhone())) infos.put("phone_no", phone_no);
                        if (!photo.equals(user.getPhoto())) infos.put("photo", photo);
                        if (!height.equals(user.getHeight())) infos.put("height", height);
                        if (!weight.equals(user.getWeight())) infos.put("weight", weight);
                        if (!remark.equals(user.getRemark())) infos.put("remark", remark);

                        if (!infos.isEmpty()) {
                            handler2 = new Handler(new ModifyInfoTaskCallBack());
                            new ModifyInfoTask(userid, infos, handler2).start();

                            Gson gson = new Gson();
                            user = gson.fromJson(responseText2, User.class);

                            sp = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
                            Editor editor = sp.edit();
                            editor.putString("user", responseText2);
                            editor.commit();

                            app.setUser(user);

                            Toast.makeText(getApplicationContext(), "修改个人信息成功！", Toast.LENGTH_SHORT).show();
                        }
                        Intent intent = new Intent(Homepage_SettingsActivity.this, HomepageActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });

    }

    private class CheckNameTaskCallBack implements Callback {
        @Override
        public boolean handleMessage(Message message) {
            if(message.arg1 == 1) {
                responseText1 = (String)message.obj;
            }
            return true;
        }
    }

    private class ModifyInfoTaskCallBack implements Callback {
        @Override
        public boolean handleMessage(Message message) {
            if(message.arg1 == 1) {
                responseText2 = (String)message.obj;
            }
            return true;
        }
    }
}
