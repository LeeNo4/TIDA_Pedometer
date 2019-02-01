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
public class SendMessageTask extends Thread {
    private int manager_id;
    private String content;
    private String photos;
    private Handler handler;

    public SendMessageTask(int manager_id, String content, String photos, Handler handler){
        super();
        this.manager_id = manager_id;
        this.content = content;
        this.photos = photos;
        this.handler = handler;
    }

    @Override
    public void run() {
        String url = "http://localhost:8080/Pedometer/SendMessageServlet?manager_id=" + manager_id + "&&content=" + content + "&&photos=" + photos;
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
