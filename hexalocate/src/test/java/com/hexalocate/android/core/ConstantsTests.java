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

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ConstantsTests {

    @Test
    public void testConstantValues() {
        assertEquals(3*60, Constants.DEFAULT_LOCATION_INTERVAL_SEC);
        assertEquals(60*60, Constants.DEFAULT_TRANSMISSION_INTERVAL_SEC);
        assertEquals(LocationAccuracy.HIGH, Constants.DEFAULT_LOCATION_ACCURACY);

        assertEquals("url", Constants.URL_KEY);
        assertEquals("header", Constants.HEADER_KEY);
        assertEquals("location_accuracy", Constants.LOCATION_ACCURACY_KEY);
        assertEquals("location_interval", Constants.LOCATION_INTERVAL_KEY);
        assertEquals("transmission_interval", Constants.TRANSMISSION_INTERVAL_KEY);
        assertEquals("advertising_id", Constants.ADVERTISING_ID_KEY);
        assertEquals("limited_ad_tracking_enabled", Constants.LIMITED_AD_TRACKING_ENABLED_KEY);
        assertEquals(-1, Constants.DEFAULT_PORT);
        assertEquals("com.openlocate.android.core.Constants.LocationIntervalChanged", Constants.LOCATION_INTERVAL_CHANGED);
        assertEquals("com.openlocate.android.core.Constants.LocationAccuracyChanged", Constants.LOCATION_ACCURACY_CHANGED);
        assertEquals("com.openlocate.android.core.Constants.TransmissionIntervalChanged", Constants.TRANSMISSION_INTERVAL_CHANGED);
    }

    @Test
    public void testConstantsConstructor() {
        Constants constants = new Constants();
        assertNotNull(constants);
    }
}
