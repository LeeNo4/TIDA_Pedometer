package com.mobileapp.hhx.asynctask;

import android.os.Handler;
import android.os.Message;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by hhx on 2017/4/23.
 */

@SuppressWarnings("deprecation")
public class GetTodayRecordTask extends Thread {
    private int userid;
    private int type;//1-自己的记录 2-好友排行 3-全国排行
    private Handler handler;

    public GetTodayRecordTask(int userid, int type, Handler handler){
        super();
        this.userid = userid;
        this.type = type;
        this.handler = handler;
    }

    @Override
    public void run() {
        String url = "http://localhost:8080/Pedometer/GetAllTodayRecordServlet?userid=" + userid + "&&type=" + type;
        HttpClient client = new DefaultHttpClient();
        HttpPost request = new HttpPost(url);
        Message message = this.handler.obtainMessage();

        try {
            HttpResponse response = client.execute(request);
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                String responseText = EntityUtils.toString(response.getEntity());
                message.arg1 = 1;
                message.obj = responseText;
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            message.arg1 = 0;
        } catch (IOException e) {
            e.printStackTrace();
            message.arg1 = 0;
        }
        handler.sendMessage(message);
    }
}
