package com.hexalocate.example;

import android.app.Application;

import com.hexalocate.android.core.HexaLocate;

import java.util.HashMap;

public class ExampleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        HashMap<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer <TOKEN>");

        HexaLocate.Configuration configuration = new HexaLocate.Configuration.Builder(this, "https://api.safegraph.com/v1/provider/<UUID>/devicelocation")
                .setHeaders(headers)
                .withoutDeviceManufacturer()
                .withoutDeviceModel()
                .build();

        HexaLocate.initialize(configuration);
    }
}
