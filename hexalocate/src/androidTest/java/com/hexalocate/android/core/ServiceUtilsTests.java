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

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class ServiceUtilsTests {

    Context context;

    @Before
    public void setUp() {
        context = InstrumentationRegistry.getTargetContext();
    }

    @Test
    public void testIsServiceNotRunning() {
        // Given
        Intent intent = new Intent(context, LocationService.class);

        intent.putExtra(Constants.URL_KEY, "http://httpbin.org/post");
        intent.putExtra(Constants.ADVERTISING_ID_KEY, "1234");
        intent.putExtra(Constants.LIMITED_AD_TRACKING_ENABLED_KEY, true);

        intent.putExtra(Constants.LOCATION_ACCURACY_KEY, Constants.DEFAULT_LOCATION_ACCURACY);
        intent.putExtra(Constants.LOCATION_INTERVAL_KEY, Constants.DEFAULT_LOCATION_INTERVAL_SEC);
        intent.putExtra(Constants.TRANSMISSION_INTERVAL_KEY, Constants.DEFAULT_TRANSMISSION_INTERVAL_SEC);

        // When
        boolean isRunning = ServiceUtils.isServiceRunning(LocationService.class, context);

        // Then
        assertFalse(isRunning);
    }

    @Test
    public void testServiceConstructor() {
        ServiceUtils utils = new ServiceUtils();
        assertNotNull(utils);
    }
}
