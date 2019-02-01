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
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mobileapp.hhx.app.MyApp;
import com.mobileapp.hhx.asynctask.ModifyPswdTask;
import com.mobileapp.hhx.entity.User;

import java.util.Map;

public class Homepage_ModifyPswdActivity extends AppCompatActivity {
    private SharedPreferences sp;
    private User user;
    private Handler handler;
    private int userid;
    private String oldPassword, newPassword1, newPassword2;
    private String responseText;
    private TextView txtView1, txtView2, txtView3;
    private EditText edtOldPswd, edtNewPswd1, edtNewPswd2;
    private Button btnCommit, btnCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage__modify_pswd);

        txtView1 = (TextView) findViewById(R.id.txtViewTmp1);
        txtView2 = (TextView) findViewById(R.id.txtViewTmp2);
        txtView3 = (TextView) findViewById(R.id.txtViewTmp3);
        edtOldPswd = (EditText) findViewById(R.id.edtCheckPswd);
        edtNewPswd1 = (EditText) findViewById(R.id.edtNewPswd);
        edtNewPswd2 = (EditText) findViewById(R.id.edtCheckNewPswd);
        btnCheck = (Button) findViewById(R.id.btnCheckPswd);
        btnCommit = (Button) findViewById(R.id.btnSavePswd);

        txtView2.setVisibility(View.GONE);
        txtView3.setVisibility(View.GONE);
        edtNewPswd1.setVisibility(View.GONE);
        edtNewPswd2.setVisibility(View.GONE);
        btnCommit.setVisibility(View.GONE);

        MyApp app = (MyApp)getApplicationContext();
        user = app.getUser();
        userid = user.getUserid();
        oldPassword = user.getPassword();

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if("".equals(edtOldPswd.getText())){
                    Toast.makeText(getApplicationContext(), "请输入您的原密码！", Toast.LENGTH_SHORT).show();
                }else if(!oldPassword.equals(edtOldPswd.getText())){
                    Toast.makeText(getApplicationContext(), "您输入的密码错误！", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "密码验证成功！", Toast.LENGTH_SHORT).show();
                    edtOldPswd.setVisibility(View.GONE);
                    txtView1.setVisibility(View.GONE);
                    btnCheck.setVisibility(View.GONE);

                    edtNewPswd1.setVisibility(View.VISIBLE);
                    edtNewPswd2.setVisibility(View.VISIBLE);
                    txtView2.setVisibility(View.VISIBLE);
                    txtView3.setVisibility(View.VISIBLE);
                    btnCommit.setVisibility(View.VISIBLE);
                }
            }
        });

        btnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newPassword1 = edtNewPswd1.getText().toString();
                newPassword2 = edtNewPswd2.getText().toString();
                if("".equals(newPassword1) || "".equals(newPassword2)){
                    Toast.makeText(getApplicationContext(), "请输入新密码！", Toast.LENGTH_SHORT).show();
                }else if(!newPassword1.equals(newPassword2)){
                    Toast.makeText(getApplicationContext(), "输入的两次密码不一致！", Toast.LENGTH_SHORT).show();
                }else {
                    handler = new Handler(new ModifyPswdTaskCallBack());
                    new ModifyPswdTask(userid, newPassword1, handler).start();

                    Gson gson = new Gson();
                    user = gson.fromJson(responseText, User.class);
                    Toast.makeText(getApplicationContext(), "成功修改密码！", Toast.LENGTH_SHORT).show();

                    sp = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
                    Editor editor = sp.edit();
                    editor.putString("user", responseText);
                    editor.commit();

                    Intent intent = new Intent(Homepage_ModifyPswdActivity.this, HomepageActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        app.setUser(user);
    }

    private class ModifyPswdTaskCallBack implements Callback {
        @Override
        public boolean handleMessage(Message message) {
            if(message.arg1 == 1) {
                responseText = (String)message.obj;
            }
            return true;
        }
    }
}
