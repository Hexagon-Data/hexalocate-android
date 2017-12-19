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

import com.google.android.gms.ads.identifier.AdvertisingIdClient;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class FetchAdvertisingInfoTaskTests {

    @Test
    public void testFetchAdvertisingInfoTask() {

        // Given
        final Object syncObject = new Object();

        FetchAdvertisingInfoTaskCallback callback = new FetchAdvertisingInfoTaskCallback() {
            @Override
            public void onAdvertisingInfoTaskExecute(AdvertisingIdClient.Info advertisingInfo) {
                assertNotNull(advertisingInfo);
                synchronized (syncObject) {
                    syncObject.notify();
                }
            }
        };

        FetchAdvertisingInfoTask task = new FetchAdvertisingInfoTask(InstrumentationRegistry.getTargetContext(), callback);

        // When
        task.execute();

        // Then
        synchronized (syncObject) {
            try {
                syncObject.wait();
            } catch (InterruptedException e) {
                assertTrue(false);
            }
        }
    }

}
