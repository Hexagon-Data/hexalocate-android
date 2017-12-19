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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HttpResponseTests {
    @Test
    public void testHttpResponseBuilder() {
        // Given
        Error error = new Error();
        HttpResponse response = new HttpResponse.Builder()
                .setStatusCode(200)
                .setError(error)
                .build();

        // Then
        assertEquals(200, response.getStatusCode());
        assertEquals(error, response.getError());
    }

    @Test
    public void testHttpResponseIsSuccess() {
        // Given
        HttpResponse response = new HttpResponse.Builder()
                .setStatusCode(200)
                .build();

        // Then
        assertTrue(response.isSuccess());
    }

    @Test
    public void testHttpResponseIsFailure() {
        // Given
        HttpResponse response = new HttpResponse.Builder()
                .setStatusCode(301)
                .build();

        // Then
        assertFalse(response.isSuccess());
    }
}
