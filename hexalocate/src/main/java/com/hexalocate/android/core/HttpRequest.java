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

import java.util.Map;

final class HttpRequest {
    private HttpMethodType methodType;
    private String url;
    private String params;
    private HttpClientCallback successCallback;
    private HttpClientCallback failureCallback;
    private Map<String, String> additionalHeaders;

    private HttpRequest(HttpMethodType methodType, String url, String params, Map<String, String> additionalHeaders, HttpClientCallback successCallback, HttpClientCallback failureCallback) {
        this.methodType = methodType;
        this.url = url;
        this.params = params;
        this.additionalHeaders = additionalHeaders;
        this.successCallback = successCallback;
        this.failureCallback = failureCallback;
    }

    HttpMethodType getMethodType() {
        return methodType;
    }

    String getUrl() {
        return url;
    }

    String getParams() {
        return params;
    }

    HttpClientCallback getSuccessCallback() {
        return successCallback;
    }

    HttpClientCallback getFailureCallback() {
        return failureCallback;
    }

    Map<String, String> getAdditionalHeaders() {
        return additionalHeaders;
    }

    boolean isValidForPost() {
        return getMethodType() == HttpMethodType.POST && getParams() != null;
    }

    static class Builder {
        private HttpMethodType methodType;
        private String url;
        private String params;
        private Map<String, String> additionalHeaders;
        private HttpClientCallback successCallback;
        private HttpClientCallback failureCallback;

        Builder setMethodType(HttpMethodType methodType) {
            this.methodType = methodType;
            return this;
        }

        Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        Builder setParams(String params) {
            this.params = params;
            return this;
        }

        Builder setSuccessCallback(HttpClientCallback successCallback) {
            this.successCallback = successCallback;
            return this;
        }

        Builder setFailureCallback(HttpClientCallback failureCallback) {
            this.failureCallback = failureCallback;
            return this;
        }

        Builder setAdditionalHeaders(Map<String, String> additionalHeaders) {
            this.additionalHeaders = additionalHeaders;
            return this;
        }

        HttpRequest build() {
            return new HttpRequest(methodType, url, params, additionalHeaders, successCallback, failureCallback);
        }
    }

}
