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

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

final class LocationDispatcher {

    private static final String TAG = LocationDispatcher.class.getSimpleName();
    private static final String LOCATIONS_KEY = "locations";

    void postLocations(HttpClient httpClient, String url, HashMap<String, String> headers, final LocationDataSource dataSource) {
        final List<HexaLocateLocation> locations = dataSource.popAll();

        if (locations == null || locations.isEmpty()) {
            Log.i(TAG, "Attempted to post locations, but found none to post.");
            return;
        }

        httpClient.post(
                url,
                getLocationsParam(locations).toString(),
                headers,
                new HttpClientCallback() {
                    @Override
                    public void onCompletion(HttpRequest request, HttpResponse response) {
                        Log.i(TAG, "Successfully posted locations");
                        dataSource.close();
                    }
                }, new HttpClientCallback() {
                    @Override
                    public void onCompletion(HttpRequest request, HttpResponse response) {
                        dataSource.addAll(locations);
                        dataSource.close();
                        Log.e(TAG, "Fail to post location");
                    }
                }
        );
    }

    private JSONObject getLocationsParam(List<HexaLocateLocation> locationsToPost) {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for (HexaLocateLocation location : locationsToPost) {
            jsonArray.put(location.getJson());
        }

        try {
            jsonObject.put(LOCATIONS_KEY, jsonArray);
        } catch (JSONException e) {
            Log.e(TAG, "JSON exception while posting locations " + e.getMessage());
        }

        return jsonObject;
    }
}
