package com.mobileapp.hhx.pedometer;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mobileapp.hhx.Layout.CircleBar;
import com.mobileapp.hhx.config.Constant;
import com.mobileapp.hhx.service.StepService;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity  implements Handler.Callback {
    private long TIME_INTERVAL = 500;    //循环取当前时刻的步数中间的时间间隔
    //控件
    private TextView text_step;    //显示走的步数
    private TextView text_distacne; //显示走的距离
    private TextView text_calorie; //显示卡路里
    private CircleBar circleBar;
    private Messenger messenger;
    private Messenger mGetReplyMessenger = new Messenger(new Handler(this));
    private Handler delayHandler;
    private Button setnumbutton;
    private Button svm;

    //赋值数据，应该是用户的，接收来的
    double height=170;//身高
    double weight=60;//体重
    double stepdistance=height*0.45;//单位是厘米
    int goalstep=10000;

    //以bind形式开启service，故有ServiceConnection接收回调
    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            try {
                messenger = new Messenger(service);
                Message msg = Message.obtain(null, Constant.MSG_FROM_CLIENT);
                msg.replyTo = mGetReplyMessenger;
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {}
    };

    //接收从服务端回调的步数
    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case Constant.MSG_FROM_SERVER:
                //更新步数
                DecimalFormat df1 = new DecimalFormat("#####0.000");
                DecimalFormat df2 = new DecimalFormat("#####0.00");
                text_step.setText(msg.getData().getInt("step") + "");
                double distance=(msg.getData().getInt("step"))*stepdistance*0.00001;//单位是千米
                String dist = df1.format(distance);
                text_distacne.setText(dist);
                double cal=weight*distance*1.036;
                String calo=df2.format(cal);
                text_calorie.setText(calo);
                delayHandler.sendEmptyMessageDelayed(Constant.REQUEST_SERVER, TIME_INTERVAL);
                break;
            case Constant.REQUEST_SERVER:
                try {
                    Message msgl = Message.obtain(null, Constant.MSG_FROM_CLIENT);
                    msgl.replyTo = mGetReplyMessenger;
                    messenger.send(msgl);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        circleBar = (CircleBar) findViewById(R.id.circle);
        text_step = (TextView) findViewById(R.id.main_text_step);
        text_distacne=(TextView)findViewById(R.id.distance);
        text_calorie=(TextView)findViewById(R.id.calorie);
        setnumbutton = (Button) findViewById(R.id.setnumbutton);
        svm=(Button)findViewById(R.id.svm);
        circleBar.setMaxstepnumber(goalstep);
        setnumbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                circleBar.update(Integer.parseInt(text_step.getText().toString()),
                        700);
            }
        });
        svm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(MainActivity.this,SVMActivity.class);
                startActivity(intent);
            }
        });
        delayHandler = new Handler(this);


    }
    @Override
    public void onStart() {
        super.onStart();
        setupService();
    }
    /**
     * 开启服务
     */
    private void setupService() {
        Intent intent = new Intent(this, StepService.class);
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
        startService(intent);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        //取消服务绑定
        unbindService(conn);
        super.onDestroy();
    }

}

