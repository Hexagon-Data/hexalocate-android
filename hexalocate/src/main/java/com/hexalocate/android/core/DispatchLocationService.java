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

import android.database.sqlite.SQLiteOpenHelper;

import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.GcmTaskService;
import com.google.android.gms.gcm.TaskParams;

import java.util.HashMap;

final public class DispatchLocationService extends GcmTaskService {

    @Override
    public int onRunTask(TaskParams taskParams) {
        SQLiteOpenHelper helper = new DatabaseHelper(this);
        LocationDataSource dataSource = new LocationDatabase(helper);

        String url = taskParams.getExtras().getString(Constants.URL_KEY);
        HashMap<String, String> headers = Utils.hashMapFromString(taskParams.getExtras().getString(Constants.HEADER_KEY));

        HttpClient httpClient = new HttpClientImpl();

        LocationDispatcher dispatcher = new LocationDispatcher();
        dispatcher.postLocations(httpClient, url, headers, dataSource);

        return GcmNetworkManager.RESULT_SUCCESS;
    }
}
