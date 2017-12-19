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

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HttpRequestTests {

    @Test
    public void testHttpRequestBuilder() {
        // Given
        Map<String, String> additionalHeaders = new HashMap<>();
        additionalHeaders.put("key", "value");

        HttpClientCallback callback = new HttpClientCallback() {
            @Override
            public void onCompletion(HttpRequest request, HttpResponse response) {

            }
        };



        HttpRequest request = new HttpRequest.Builder()
                .setUrl("/test/")
                .setMethodType(HttpMethodType.POST)
                .setParams("Hi")
                .setAdditionalHeaders(additionalHeaders)
                .setSuccessCallback(callback)
                .setFailureCallback(callback)
                .build();

        // Then
        assertEquals("/test/", request.getUrl());
        assertEquals("Hi", request.getParams());
        assertEquals(HttpMethodType.POST, request.getMethodType());
        assertEquals(additionalHeaders, request.getAdditionalHeaders());
        assertEquals(callback, request.getSuccessCallback());
        assertEquals(callback, request.getFailureCallback());
        assertTrue(request.isValidForPost());
    }

    @Test
    public void testHttpRequestInvalidForPost() {
        // Given
        Map<String, String> additionalHeaders = new HashMap<>();
        additionalHeaders.put("key", "value");

        HttpClientCallback callback = new HttpClientCallback() {
            @Override
            public void onCompletion(HttpRequest request, HttpResponse response) {

            }
        };



        HttpRequest request = new HttpRequest.Builder()
                .setUrl("/test/")
                .setMethodType(HttpMethodType.POST)
                .setAdditionalHeaders(additionalHeaders)
                .setSuccessCallback(callback)
                .setFailureCallback(callback)
                .build();

        // Then
        assertFalse(request.isValidForPost());
    }

    @Test
    public void testHttpRequestInvalidForPostWithNoMethodType() {
        // Given
        Map<String, String> additionalHeaders = new HashMap<>();
        additionalHeaders.put("key", "value");

        HttpClientCallback callback = new HttpClientCallback() {
            @Override
            public void onCompletion(HttpRequest request, HttpResponse response) {

            }
        };



        HttpRequest request = new HttpRequest.Builder()
                .setUrl("/test/")
                .setParams("hi")
                .setAdditionalHeaders(additionalHeaders)
                .setSuccessCallback(callback)
                .setFailureCallback(callback)
                .build();

        // Then
        assertFalse(request.isValidForPost());
    }
}
