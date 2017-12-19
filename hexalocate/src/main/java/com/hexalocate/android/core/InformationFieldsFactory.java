package com.hexalocate.android.core;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Build;
import android.telephony.TelephonyManager;

final class InformationFieldsFactory {

    private Context context;
    private HexaLocate.Configuration configuration;

    private String manufacturer;
    private String model;
    private String operatingSystem;
    private String isCharging;

    private String carrierName;
    private String wifiSsid;
    private String wifiBssid;
    private String connectionType;
    private LocationProvider locationProvider;
    private LocationContext locationContext;

    private static final String BASE_NAME = "Android";

    private InformationFieldsFactory(Context context, HexaLocate.Configuration configuration) {

        this.configuration = configuration;
        this.context = context;

        updateDeviceInfo();
        updateCarrierName();
        updateWifiInfo();
        updateConnectionType();
        updateLocationProvider();
        updateLocationContext();

    }

    public static InformationFields collectInformationFields(Context context, HexaLocate.Configuration configuration) {

        InformationFieldsFactory informationFieldsFactory = new InformationFieldsFactory(context, configuration);

        return InformationFields.from(informationFieldsFactory.manufacturer, informationFieldsFactory.model, informationFieldsFactory.isCharging,
                informationFieldsFactory.operatingSystem, informationFieldsFactory.carrierName, informationFieldsFactory.wifiSsid,
                informationFieldsFactory.wifiBssid, informationFieldsFactory.connectionType, informationFieldsFactory.locationProvider.getValue(), informationFieldsFactory.locationContext.getValue());

    }

    public static InformationFields getInformationFields(String deviceManufacturer, String deviceModel, String chargingState,
                                                         String operatingSystem, String carrierName, String wifiSSID,
                                                         String wifiBSSID, String connectionType, String locationMethod, String locationContext) {

        return InformationFields.from(deviceManufacturer, deviceModel, chargingState,
                operatingSystem, carrierName, wifiSSID,
                wifiBSSID, connectionType, locationMethod, locationContext);
    }

    private void updateDeviceInfo() {

        this.manufacturer = Build.MANUFACTURER;
        this.model = Build.MODEL;
        this.model = Build.MODEL;
        this.operatingSystem = BASE_NAME + " " + Build.VERSION.RELEASE;
        Intent batteryIntent = context.registerReceiver(
                null,
                new IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        );
        this.isCharging = String.valueOf(isDeviceCharging(batteryIntent));
    }

    private void updateLocationContext() {
        locationContext = LocationContext.getLocationContext();
    }

    private void updateLocationProvider() {
        locationProvider = LocationProvider.getLocationProvider(context);
    }

    private boolean isDeviceCharging(Intent batteryIntent) {
        int status = batteryIntent.getIntExtra(BatteryManager.EXTRA_STATUS, 0);
        return status == BatteryManager.BATTERY_STATUS_CHARGING;
    }

    private void updateCarrierName() {
        TelephonyManager telephonyManager = (TelephonyManager) context.getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
        carrierName = telephonyManager.getNetworkOperatorName();
    }

    private void updateWifiInfo() {
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();

        wifiSsid = wifiInfo.getSSID();
        wifiBssid = wifiInfo.getBSSID();
    }

    private void updateConnectionType() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean connected = connectivityManager.getActiveNetworkInfo().isConnected();

        if (!connected) {
            connectionType = "none";
            return;
        }

        int type = connectivityManager.getActiveNetworkInfo().getType();

        switch (type) {
            case ConnectivityManager.TYPE_WIFI:
                connectionType = "wifi";
                break;
            case ConnectivityManager.TYPE_MOBILE:
                connectionType = "cellular";
                break;
            default:
                connectionType = "unknown";
        }
    }
}
