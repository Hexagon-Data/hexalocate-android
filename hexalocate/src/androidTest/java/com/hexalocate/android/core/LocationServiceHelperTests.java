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

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class LocationServiceHelperTests {

    @Before
    public void setUp() {
    }

    @Test
    public void testOnStartCommand() {
//        // Given
//        Context context = InstrumentationRegistry.getTargetContext();
//        LocationServiceHelper helper = new LocationServiceHelper(context);
//
//        Intent intent = new Intent(context, LocationService.class);
//        HashMap<String, String> headers = new HashMap<>();
//        headers.put("Key", "Value");
//
//        intent.putExtra(Constants.URL_KEY, "http://httpbin.org/post");
//        intent.putExtra(Constants.HEADER_KEY, headers);
//
//        intent.putExtra(Constants.ADVERTISING_ID_KEY, "1234");
//        intent.putExtra(Constants.LIMITED_AD_TRACKING_ENABLED_KEY, true);
//
//        intent.putExtra(Constants.LOCATION_ACCURACY_KEY, Constants.DEFAULT_LOCATION_ACCURACY);
//        intent.putExtra(Constants.LOCATION_INTERVAL_KEY, Constants.DEFAULT_LOCATION_INTERVAL_SEC);
//        intent.putExtra(Constants.TRANSMISSION_INTERVAL_KEY, Constants.DEFAULT_TRANSMISSION_INTERVAL_SEC);
//
//        // When
//        helper.onStartCommand(intent);
//
//        // Then
//        assertEquals(helper.getAccuracy(), Constants.DEFAULT_LOCATION_ACCURACY);
//        assertEquals(helper.getUrl(), "http://httpbin.org/post");
//        assertEquals(helper.getHeaders().get("Key"), "Value");
//        assertEquals(helper.getAdvertisingInfo().getAdvertisingId(), "1234");
//        assertEquals(helper.getAdvertisingInfo().isLimitedAdTrackingEnabled(), true);
//        assertEquals(helper.getLocationRequestIntervalInSecs(), Constants.DEFAULT_LOCATION_INTERVAL_SEC);
//        assertEquals(helper.getTransmissionIntervalInSecs(), Constants.DEFAULT_TRANSMISSION_INTERVAL_SEC);
    }

    @Test
    public void testOnCreate() {
//        // Given
//        Context context = InstrumentationRegistry.getTargetContext();
//        LocationServiceHelper helper = new LocationServiceHelper(context);
//
//        // When
//        helper.onCreate();
//
//        // Then
//        assertNotNull(helper.getLocations());
//        assertNotNull(helper.getNetworkManager());
    }

    @Test
    public void testOnDestroy() {
//        // Given
//        Context context = InstrumentationRegistry.getTargetContext();
//        LocationServiceHelper helper = new LocationServiceHelper(context);
//
//        // When
//        helper.onDestroy();
//
//        // Then
//        assertNull(helper.getLocations());
//        assertNull(helper.getNetworkManager());
    }
}
