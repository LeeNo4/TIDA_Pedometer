package com.mobileapp.hhx.pedometer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.mobileapp.hhx.app.MyApp;
import com.mobileapp.hhx.entity.User;

public class IndexActivity extends AppCompatActivity {
    private TextView mTextMessage;
    private BottomNavigationView mNavigationView;
    private User user;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        MyApp app = (MyApp) getApplicationContext();
        user = app.getUser();

        mTextMessage = (TextView) findViewById(R.id.text);
        mNavigationView = (BottomNavigationView) findViewById(R.id.navigation);

        mNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        mTextMessage.setText(item.getTitle().toString().toUpperCase());
                        return true;
                    }
                });
    }

}
