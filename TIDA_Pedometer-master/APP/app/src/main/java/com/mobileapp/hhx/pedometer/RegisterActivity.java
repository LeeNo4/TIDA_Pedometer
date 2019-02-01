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
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mobileapp.hhx.app.MyApp;
import com.mobileapp.hhx.asynctask.CheckNameTask;
import com.mobileapp.hhx.asynctask.RegisterTask;
import com.mobileapp.hhx.entity.User;

public class RegisterActivity extends AppCompatActivity {
    private String username = "", password = "", gender = "", height = "", weight = "";
    private Handler handler1, handler2;
    private String responseText1, responseText2;
    private User user;
    private SharedPreferences sp;
    private EditText edtUsername, edtPassword, edtCheckPswd, edtHeight, edtWeight;
    private CheckBox cbxMan, cbxWoman;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtUsername = (EditText) findViewById(R.id.rgsUsername);
        edtPassword = (EditText) findViewById(R.id.rgsPassword);
        edtCheckPswd = (EditText) findViewById(R.id.rgsCheckPswd);
        edtHeight = (EditText) findViewById(R.id.rgsHeight);
        edtWeight = (EditText) findViewById(R.id.rgsWeight);
        cbxMan = (CheckBox) findViewById(R.id.manCkbx);
        cbxWoman = (CheckBox) findViewById(R.id.womanCkbx);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cbxMan.isChecked()) {
                    cbxWoman.setChecked(false);
                    gender = "1";
                }
                if(cbxWoman.isChecked()) {
                    cbxMan.setChecked(false);
                    gender = "2";
                }

                username = edtUsername.getText().toString();
                password = edtPassword.getText().toString();
                String tmp = edtCheckPswd.getText().toString();
                height = edtHeight.getText().toString();
                weight = edtWeight.getText().toString();

                if("".equals(username) || "".equals(password) || "".equals(tmp) || "".equals(gender) || "".equals(height) || "".equals(weight)){
                    Toast.makeText(getApplicationContext(), "请填写完整的注册信息", Toast.LENGTH_SHORT).show();
                }else{
                    handler1 = new Handler(new CheckNameTaskCallBack());
                    new CheckNameTask(username, handler1).start();

                    if ("existed".equals(responseText1)) {
                        Toast.makeText(getApplicationContext(), "用户名已被他人注册，请重新输入", Toast.LENGTH_SHORT).show();
                    }else if (!tmp.equals(password)) {
                        Toast.makeText(getApplicationContext(), "您输入的两次密码不一致", Toast.LENGTH_SHORT).show();
                    }else if ("not existed".equals(responseText1) && tmp.equals(password)) {
                        handler2 = new Handler(new RegisterTaskCallBack());
                        new RegisterTask(username, password, gender, height, weight, handler2).start();
                        Toast.makeText(getApplicationContext(), "注册成功！", Toast.LENGTH_SHORT).show();

                        Gson gson = new Gson();
                        user = gson.fromJson(responseText2, User.class);
                        MyApp app = (MyApp) getApplicationContext();
                        app.setUser(user);

                        sp = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
                        Editor editor = sp.edit();
                        editor.putString("user", responseText2);
                        editor.commit();

                        Intent intent = new Intent(RegisterActivity.this, IndexActivity.class);
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

    private class RegisterTaskCallBack implements Callback {
        @Override
        public boolean handleMessage(Message message) {
            if(message.arg1 == 1) {
                responseText2 = (String)message.obj;
            }
            return true;
        }
    }
}
