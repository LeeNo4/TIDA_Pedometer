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
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mobileapp.hhx.app.MyApp;
import com.mobileapp.hhx.asynctask.LoginTask;
import com.mobileapp.hhx.entity.User;

public class LoginActivity extends AppCompatActivity {

    private EditText edtUsername, edtPassword;
    private CheckBox ckbLogin;
    private Button btnLogin;
    private Handler handler;
    private String username = "", password = "", userStr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        edtUsername = (EditText) findViewById(R.id.edtUsername);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        ckbLogin = (CheckBox) findViewById(R.id.loginCkbx);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = edtUsername.getText().toString();
                password = edtPassword.getText().toString();

                if("".equals(username) || "".equals(password)){
                    Toast.makeText(getApplicationContext(), "请输入用户名或密码！", Toast.LENGTH_SHORT).show();
                }else {
                    handler = new Handler(new LoginTaskCallBack());
                    new LoginTask(username, password, handler).start();
                }
            }
        });
    }

    private class LoginTaskCallBack implements Callback {

        @Override
        public boolean handleMessage(Message message) {
            if(message.arg1 == 1) {
                userStr = (String) message.obj;
                Gson gson = new Gson();
                User user = gson.fromJson(userStr, User.class);
                if(user != null) {
                    MyApp app = (MyApp) getApplicationContext();
                    app.setUser(user);

                    if (ckbLogin.isChecked()) {
                        SharedPreferences sp = getSharedPreferences("user", Context.MODE_PRIVATE);
                        Editor editor = sp.edit();
                        editor.putString("UserInfo", userStr);
                        editor.commit();
                    }

                    Intent intent = new Intent(LoginActivity.this, IndexActivity.class);
                    startActivity(intent);
                    finish();
                }else
                    Toast.makeText(getApplicationContext(), "用户名或密码错误，登陆失败！", Toast.LENGTH_SHORT).show();
            }
            return true;
        }
    }
}
