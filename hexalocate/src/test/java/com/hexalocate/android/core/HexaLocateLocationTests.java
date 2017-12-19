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

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

public class HexaLocateLocationTests {
    private double lat = 10.40;
    private double lng = 10.234;
    private double accuracy = 40.43;
    private boolean adOptOut = true;
    private String adId = "1234";
    private long timestamp = 341;

    private JSONObject getJson() {

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put(HexaLocateLocation.Keys.LATITUDE, lat);
            jsonObject.put(HexaLocateLocation.Keys.LONGITUDE, lng);
            jsonObject.put(HexaLocateLocation.Keys.AD_OPT_OUT, adOptOut);
            jsonObject.put(HexaLocateLocation.Keys.AD_ID, adId);
            jsonObject.put(HexaLocateLocation.Keys.HORIZONTAL_ACCURACY, accuracy);
            jsonObject.put(HexaLocateLocation.Keys.TIMESTAMP, timestamp);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    @Test
    public void testKeys() {
        assertEquals("ad_id", HexaLocateLocation.Keys.AD_ID);
        assertEquals("ad_opt_out", HexaLocateLocation.Keys.AD_OPT_OUT);
        assertEquals("id_type", HexaLocateLocation.Keys.AD_TYPE);
        assertEquals("utc_timestamp", HexaLocateLocation.Keys.TIMESTAMP);
        assertEquals("horizontal_accuracy", HexaLocateLocation.Keys.HORIZONTAL_ACCURACY);
        assertEquals("longitude", HexaLocateLocation.Keys.LONGITUDE);
        assertEquals("latitude", HexaLocateLocation.Keys.LATITUDE);
    }
}
