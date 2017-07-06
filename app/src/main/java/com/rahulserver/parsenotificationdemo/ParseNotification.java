package com.rahulserver.parsenotificationdemo;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.SaveCallback;

/**
 * Created by Rahulserver on 4/18/2015.
 */
public class ParseNotification extends Application {
    private static final String TAG = "ParseNotification";

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "App Id", "ClientKey");
//        ParseObject.registerSubclass(Article.class);

        ParseInstallation.getCurrentInstallation().saveInBackground();
    }
}
