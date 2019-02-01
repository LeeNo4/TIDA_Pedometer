package com.mobileapp.hhx.pedometer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class HelpActivity extends AppCompatActivity {

    private Button returnSvm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        returnSvm=(Button)findViewById(R.id.returnSvm) ;
        returnSvm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(HelpActivity.this,SVMActivity.class);
                startActivity(intent);
            }
        });
    }
}
