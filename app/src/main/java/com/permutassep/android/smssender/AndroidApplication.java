package com.permutassep.android.smssender;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseInstallation;

public class AndroidApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        this.setupParseCom();
    }

    private void setupParseCom() {
        Parse.initialize(this, BuildConfig.PARSE_COM_APPLICATION_ID, BuildConfig.PARSE_COM_CLIENT_KEY);
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }
}
