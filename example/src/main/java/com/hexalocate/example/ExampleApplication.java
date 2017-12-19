package com.hexalocate.example;

import android.app.Application;

import com.hexalocate.android.core.HexaLocate;

import java.util.HashMap;

public class ExampleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        HexaLocate.Configuration configuration = new HexaLocate.Configuration.Builder(this, "CKrNMFr1eI","XZYxnZJoJJ").build();
        HexaLocate.initialize(configuration);
    }
}
