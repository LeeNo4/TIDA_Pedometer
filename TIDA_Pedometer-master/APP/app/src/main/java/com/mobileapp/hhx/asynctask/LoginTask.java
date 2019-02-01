package com.mobileapp.hhx.asynctask;

import android.os.Message;
import android.os.Handler;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by hhx on 2017/4/16.
 */
@SuppressWarnings("deprecation")

public class LoginTask extends Thread {
    private String username, password;
    private Handler handler;

    public LoginTask(String username, String password, Handler handler){
        super();
        this.username = username;
        this.password = password;
        this.handler = handler;
    }

    @Override
    public void run() {
        String url = "http://localhost:8080/Pedometer/LoginServlet?username="
                + username + "&&password=" + password;
        System.out.println("name:" + username + ",pswd:" + password);
        HttpClient client = new DefaultHttpClient();
        HttpPost request = new HttpPost(url);
        Message message = this.handler.obtainMessage();

        try {
            HttpResponse response = client.execute(request);
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                String responseText = EntityUtils.toString(response.getEntity());
                message.arg1 = 1;
                message.obj = responseText;
                System.out.println(responseText);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            message.arg1 = 0;
            System.out.println("-----------");
        } catch (IOException e) {
            e.printStackTrace();
            message.arg1 = 0;
        }
        handler.sendMessage(message);
    }
}
