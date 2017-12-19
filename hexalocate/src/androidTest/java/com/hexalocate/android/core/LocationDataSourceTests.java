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

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class LocationDataSourceTests {
    private LocationDataSource dataSource;

    @Before
    public void setUp() {
        DatabaseHelper helper = new DatabaseHelper(InstrumentationRegistry.getTargetContext());
        dataSource = new LocationDatabase(helper);
        dataSource.popAll();
    }

    private JSONObject getJson() {
        double lat = 10.403;
        double lng = 10.234;
        String accuracy = "40.43";

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put(HexaLocateLocation.Keys.LATITUDE, lat);
            jsonObject.put(HexaLocateLocation.Keys.LONGITUDE, lng);
            jsonObject.put(HexaLocateLocation.Keys.AD_OPT_OUT, true);
            jsonObject.put(HexaLocateLocation.Keys.AD_ID, "1234");
            jsonObject.put(HexaLocateLocation.Keys.HORIZONTAL_ACCURACY, accuracy);
            jsonObject.put(HexaLocateLocation.Keys.TIMESTAMP, 1234);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    private HexaLocateLocation getOpenLocateLocation() {
        return new HexaLocateLocation(getJson().toString());
    }

    @Test
    public void testAddLocation() {
        // Given
        HexaLocateLocation location = getOpenLocateLocation();

        // When
        dataSource.add(location);

        // Then
        assertEquals(1, dataSource.size());
    }

    @Test
    public void testLocationPopSize() {
        // Given
        HexaLocateLocation location = getOpenLocateLocation();
        dataSource.add(location);

        // When
        List<HexaLocateLocation> locations = dataSource.popAll();

        // Then
        assertEquals(1, locations.size());
        assertEquals(0, dataSource.size());
    }

    @Test
    public void testLocationAddAll() {
        // Given
        List<HexaLocateLocation> locations = new ArrayList<>();
        locations.add(getOpenLocateLocation());
        locations.add(getOpenLocateLocation());

        // When
        dataSource.addAll(locations);

        // Then
        assertEquals(2, dataSource.size());
    }
}
