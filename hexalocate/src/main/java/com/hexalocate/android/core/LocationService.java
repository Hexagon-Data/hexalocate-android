/*
 * Copyright (c) 2017 OpenLocate
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.hexalocate.android.core;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.util.Log;

public class LocationService extends Service {

    LocationServiceHelper helper;
    public static final String TAG = LocationService.class.getSimpleName();

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.helper = new LocationServiceHelper(this);
        helper.onCreate();
        Log.d(TAG, "Inside onCreate of Location service ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        helper.onDestroy();
        Log.d(TAG, "Inside onDestroy of Location service ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        helper.onStartCommand(intent);
        Log.d(TAG, "Inside onStartCommand of Location service ");
        return START_REDELIVER_INTENT;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        helper.onTaskRemoved(rootIntent);
    }

    static boolean isLocationEnabled(Context context) throws IllegalStateException {
        try {
            int locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);
            return locationMode != Settings.Secure.LOCATION_MODE_OFF;
        } catch (Settings.SettingNotFoundException exception) {
            throw new IllegalStateException(exception);
        }
    }

    static boolean hasLocationPermission(Context context) {
        return (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED);
    }
}
