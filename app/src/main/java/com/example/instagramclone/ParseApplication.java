package com.example.instagramclone;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Register your parse models
        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("bZ85o8ntsvmJ5ayjm5pyeFEUG6o2hS2rByIluJs8")
                .clientKey("ZPr0hIiSmbCieO71W54RP4toEDktmkbHKpXR3HoR")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
