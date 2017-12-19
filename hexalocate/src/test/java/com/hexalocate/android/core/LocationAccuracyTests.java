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

import com.google.android.gms.location.LocationRequest;

import org.junit.Assert;
import org.junit.Test;

public class LocationAccuracyTests {

    @Test
    public void testLocationAccuracyStringValues() {
        Assert.assertEquals(LocationAccuracy.HIGH.toString(), "high");
        Assert.assertEquals(LocationAccuracy.LOW.toString(), "low");
        Assert.assertEquals(LocationAccuracy.MEDIUM.toString(), "medium");
        Assert.assertEquals(LocationAccuracy.NO_POWER.toString(), "no_power");
    }

    @Test
    public void testLocationRequestAccuracy() {
        Assert.assertEquals(LocationAccuracy.HIGH.getLocationRequestAccuracy(), LocationRequest.PRIORITY_HIGH_ACCURACY);
        Assert.assertEquals(LocationAccuracy.MEDIUM.getLocationRequestAccuracy(), LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        Assert.assertEquals(LocationAccuracy.LOW.getLocationRequestAccuracy(), LocationRequest.PRIORITY_LOW_POWER);
        Assert.assertEquals(LocationAccuracy.NO_POWER.getLocationRequestAccuracy(), LocationRequest.PRIORITY_NO_POWER);
    }

    @Test
    public void testLocationAccuracyValueOf() {
        Assert.assertEquals(LocationAccuracy.valueOf("HIGH"), LocationAccuracy.HIGH);
        Assert.assertEquals(LocationAccuracy.valueOf("LOW"), LocationAccuracy.LOW);
        Assert.assertEquals(LocationAccuracy.valueOf("MEDIUM"), LocationAccuracy.MEDIUM);
        Assert.assertEquals(LocationAccuracy.valueOf("NO_POWER"), LocationAccuracy.NO_POWER);
    }

    @Test
    public void testLocationAccuracyValues() {
        Assert.assertEquals(LocationAccuracy.values(), LocationAccuracy.values());
    }
}
