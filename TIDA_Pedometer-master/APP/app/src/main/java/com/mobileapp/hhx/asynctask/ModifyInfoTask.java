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
import java.util.Map;

/**
 * Created by hhx on 2017/4/18.
 */

@SuppressWarnings("deprecation")
public class ModifyInfoTask extends Thread{
    private int userid;
    private Map<String, String> infos;
    private Handler handler;

    public ModifyInfoTask(int userid, Map<String, String> infos, Handler handler) {
        this.userid = userid;
        this.infos = infos;
        this.handler = handler;
    }

    @Override
    public void run() {
        String[]keys = {"user_name", "phone_no", "photo", "height", "weight", "remark"};
        String url = "http://localhost:8080/Pedometer/ModifyInfoServlet?userid=" + userid;
        for(int i = 0;i < keys.length;i++){
            if(infos.get(keys[i]) != null){
                url = url + "&&" + keys[i] + "=" + infos.get(keys[i]);
            }
        }

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
