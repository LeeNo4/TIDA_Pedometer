package com.mobileapp.hhx.app;

import android.app.Application;

import com.mobileapp.hhx.entity.Messages;
import com.mobileapp.hhx.entity.SharedRecord;
import com.mobileapp.hhx.entity.User;

import java.util.List;

/**
 * Created by hhx on 2017/4/18.
 */

public class MyApp extends Application {
    private User user;
    private Messages message;
    private SharedRecord sr;
    private List<SharedRecord> listSR;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Messages getMessage() {
        return message;
    }

    public void setMessage(Messages message) {
        this.message = message;
    }

    public SharedRecord getSr() {
        return sr;
    }

    public void setSr(SharedRecord sr) {
        this.sr = sr;
    }

    public List<SharedRecord> getListSR() {
        return listSR;
    }

    public void setListSR(List<SharedRecord> listSR) {
        this.listSR = listSR;
    }
}
