
![OpenLocate](http://imageshack.com/a/img922/4800/Pihgqn.png)

We’re building an open SDK to collect location data (like GPS) from phones, for apps like yours

## Purpose

### Why is this project useful?

OpenLocate is supported by developers, non-profits, trade groups, and industry for the following reasons:

* Collecting location data in a battery efficient manner that does not adversely affect mobile application performance is non-trivial. OpenLocate enables everyone in the community to benefit from shared knowledge around how to do this well.
* Creates standards and best practices for location collection.
* Developers have full transparency on how OpenLocate location collection works.
* Location data collected via OpenLocate is solely controlled by the developer.

### What can I do with location data?

Mobile application developers can use location data collected via OpenLocate to:

* Enhance their mobile application using context about the user’s location.
* Receive data about the Points of Interest a device has visited by enabling integrations with 3rd party APIs such as Google Places or Foursquare Venues
* Send location data to partners of OpenLocate via integrations listed here.

### Who is supporting OpenLocate?

OpenLocate is supported by mobile app developers, non-profit trade groups, academia, and leading companies across GIS, logistics, marketing, and more.

## Requirements

- Android - Min SDK version 19

## Permissions

Openlocate uses the following permissions:

* ACCESS_COARSE_LOCATION - Required to access approximate location.
* ACCESS_FINE_LOCATION - Required to access precise location.
* INTERNET - Required to open network sockets.
* ACCESS_WIFI_STATE - Required to access information about Wi-Fi networks.

## How OpenLocate Works

OpenLocate initialises a  background service alongside your application. This background service collects  and transmits location updates.

The location updates rely on Google Play Services' `Fused Location Provider`. The location collection interval is set at a default of 3 minutes. Actual  location updates received can be more frequent than this however, as OpenLocate will receive passive fixes (location updates triggered by other applications) if there are any.

In order to minimize battery usage and network traffic to your server, the location updates are not transmitted immediately, but rather batched locally for sending at a defined interval. The default transmission interval is one hour. Once successfully transmitted, the location updates are no longer stored on the device.

## Installation

### Adding to your project

Add the below line to your app's `build.gradle`:

```groovy
repositories {
    maven {
        url "https://s3-us-west-2.amazonaws.com/openlocate-android/"
    }
}
```

Add the below line to your app's `build.gradle` inside the `dependencies` section:
    
```groovy
compile 'com.openlocate:openlocate:1.+'
```

## Usage

### Optional configuration

You can change the following parameters:
- `OpenLocate.getInstance().setTransmissionInterval(long milliseconds)` specifies the interval at which location records are transmitted from the device. The default is 1 hour. Setting a smaller interval will result in more frequent transmission but higher battery usage.
- `OpenLocate.getInstance().setLocationInterval(long milliseconds)` specifies the maximum interval before which a location update is requested from the device. The default is 3 minutes.
- `OpenLocate.getInstance().setAccuracy(LocationAccuracy)` specifies the accuracy of location updates as described in the [Fused Location Provider API](https://developers.google.com/android/reference/com/google/android/gms/location/LocationRequest). For example:
  * PRIORITY_HIGH_ACCURACY: this will return the finest location available e.g. more likely to use GPS
  * PRIORITY_BALANCED_POWER_ACCURACY: returns approximately block-level accuracy (~100m)

### Initialization
Configure where the SDK should send data to by building the configuration with appropriate URL and headers. Supply the configuration to the `initialize` method.  Initialize OpenLocate in the `Application`

```java
import android.app.Application;
import com.openlocate.android.core.OpenLocate;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        OpenLocate.Configuration config = new OpenLocate.Configuration.Builder(this, BuildConfig.URL)
            .setHeaders(<Your Headers>)
            .build();
        
        OpenLocate.initialize(config);
    }
}
```

#### For example, to send data to SafeGraph:

```java
import android.app.Application;
import com.openlocate.android.core.OpenLocate;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        
        String url = "https://api.safegraph.com/v1/provider/<UUID>/devicelocation"
        
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer <TOKEN>");

        OpenLocate.Configuration config = new OpenLocate.Configuration.Builder(this, url)
            .setHeaders(headers)
            .build();
        
        OpenLocate.initialize(config);
    }
}
```

### Start tracking:
Activity should be passed to method below. Library will request permission for you.

```java
 
OpenLocate.getInstance().startTracking(activity);
```
`startTracking` method can be called only once: library will restart tracking on next initialization.

### Stop tracking of location

To stop location tracking, call the `stopTracking` method on `OpenLocate`. Get the instance by calling `getInstance`.

```java
OpenLocate.getInstance().stopTracking()
```

### Fields collected by the SDK

The following fields are collected by the SDK to be sent to a private or public API:

1. `latitude` - Latitude of the device
2. `longitude` - Longitude of the device
3. `utc_timestamp` - Timestamp of the recorded location in epoch
4. `horizontal_accuracy` - The accuracy of the location being recorded
5. `id_type` - 'aaid' for identifying android advertising type
6. `ad_id` - Advertising identifier
7. `ad_opt_out` - Flag that indicates whether user has enabled "[limit ad tracking](https://developers.google.com/android/reference/com/google/android/gms/ads/identifier/AdvertisingIdClient.Info#isLimitAdTrackingEnabled())" (1: enabled; 0: not enabled)
8. `course` - Bearing in degrees.
9. `speed` - Speed in meters/second over ground.
10. `altitude` - Altitude in meters above the WGS 84 reference ellipsoid.

##### (Optional)

11. `is_charging` - Indicates whether phone is charging or not
12. `device_manufacturer` - Manufacturer of device
13. `device_model` - Model of devise
14. `os` - Operating system installed on Device.
15. `location_method` - Method of location collected i.e "wifi", "cellular", "fused", "gps"
16. `location_context` -  Indicates whether the location was collected when the application was foregrounded or backgrounded on the device.
17. `carrier_name` - Name of the mobile network carrier
18. `connection_type` - Collects devices's network connection type
19. `wifi_ssid` - Collects wifi_ssid
20. `wifi_bssid` - Collects wifi_bssid

### Configuring fields for collection

Optional field collection can be disabled while building the configuration object before passing it to the `initialize` method.

#### For example
```java
OpenLocate.Configuration configuration = new OpenLocate.Configuration.Builder(context, BuildConfig.URL)
                    .setHeaders(getHeader())
                    .withoutDeviceManufacturer()
                    .withoutDeviceModel()
                    .withoutChargingInfo()
                    .withoutOperatingSystem()
                    .withoutCarrierName()
                    .withoutConnectionType()
                    .withoutWifiInfo()
                    .withoutLocationMethod()
                    .withoutLocationContext()
                    .build();
```

### Using user's location to query 3rd party Places APIs

To use user's current location, obtain the location by calling `getCurrentLocation` method on OpenLocate. Get the instance by calling `getInstance`. Use the fields collected by SDK to send to 3rd party APIs.

#### For example, to obtain user location:

```java
OpenLocate openLocate = OpenLocate.getInstance();

openLocate.getCurrentLocation(new OpenLocateLocationCallback() {
    @Override
    public void onLocationFetch(OpenLocateLocation location) {
        //Use location object to obtain fields and pass it to 3rd Party API
    }

    @Override
    public void onError(Error error) {
       //error
    }
});
```

#### For example, to query Google Places API using location:

Google Places API: https://developers.google.com/places/web-service/search

```java

private Map<String, String> getQueryMapGoogle(OpenLocateLocation location ) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("location", String.valueOf(location.getLocation().getLatitude()) + "," + String.valueOf(location.getLocation().getLongitude()) );
        queryMap.put("radius", "500");
        queryMap.put("type", "restaurant");
        queryMap.put("keyword", "south");
        queryMap.put("key", -YOUR GOOGLE PLACES API KEY-);
        return queryMap;
}

public void fetchGooglePlaces(OpenLocateLocation hexaLocateLocation, final SafeGraphPlaceCallback callback) {

        GooglePlaceClient safeGraphPlaceClient = GooglePlaceClientGenerator.createClient(GooglePlaceClient.class);
        Call<GooglePlaceBody> call=safeGraphPlaceClient.getNearByPlaces(getQueryMapGoogle(hexaLocateLocation));

        call.enqueue(new Callback<GooglePlaceBody>() {
            @Override
            public void onResponse(Call<GooglePlaceBody> call, Response<GooglePlaceBody> response) {
                if(response.isSuccessful()) {
                     //TODO Do something with place.
                }
            }

            @Override
            public void onFailure(Call<GooglePlaceBody> call, Throwable t) {
                    //Error
            }
        });
}

```

#### For example, to query Safegraph Places API using location:

SafeGraph Places API: https://partners.safegraph.com/places

```java

private Map<String, String> getQueryMap(OpenLocateLocation location) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("advertising_id", location.getAdvertisingInfo().getId());
        queryMap.put("advertising_id_type", "aaid");
        queryMap.put("latitude", String.valueOf(location.getLocation().getLatitude()));
        queryMap.put("longitude", String.valueOf(location.getLocation().getLongitude()));
        queryMap.put("horizontal_accuracy", String.valueOf(location.getLocation().getHorizontalAccuracy()));
        return queryMap;
 }

 private void fetchNearbyPlaces() {
    // These classes can be found in the example app in this repo
     SafeGraphPlaceClient safeGraphPlaceClient = ClientGenerator.createClient(SafeGraphPlaceClient.class);
     Call<SafeGraphPlaceBody> call = safeGraphPlaceClient.getAllPlaces(getQueryMap(hexaLocateLocation));

     call.enqueue(new Callback<SafeGraphPlaceBody>() {
         @Override
         public void onResponse(Call<SafeGraphPlaceBody> call, Response<SafeGraphPlaceBody> response) {

             if (response.isSuccessful()) {
                 List<SafeGraphPlace> places = response.body().getPlaceList();
                 //TODO Do something with places
             }
         }

         @Override
         public void onFailure(Call<SafeGraphPlaceBody> call, Throwable t) {
             //error
         }
     });
 }

```

Similarly, OpenLocate SDK can be used to query additional APIs such as Facebook Places Graph or any other 3rd party places API.

- Facebook Places API - https://developers.facebook.com/docs/places/

#### Note

ClientGenerator is created using Retrofit and its implementation code can found in example code.

### Sample Request Body

This is a sample request body sent by the SDK. 
```json
[
  {
    "latitude": 37.773972,
    "longitude": -122.431297,
    "horizontal_accuracy": "23.670000076293945",
    "utc_timestamp": 1508369672,
    "course": "0.0",
    "speed": "0.0",
    "altitude": 0,
    "ad_id": "f109e57e-a02e-41d3-b7c4-c906d1b92331",
    "ad_opt_out": false,
    "id_type": "aaid",
    "device_manufacturer": "motorola",
    "device_model": "Moto G (5S) Plus",
    "is_charging": true,
    "os_version": "Android 7.1.1",
    "carrier_name": "T Mobile",
    "wifi_ssid": "\"Jungle\"",
    "wifi_bssid": "10:fe:ed:8d:b5:7c",
    "connection_type": "wifi",
    "location_method": "gps",
    "location_context": "bground"
  },
  {
    "latitude": 37.773972,
    "longitude": -122.431297,
    "horizontal_accuracy": "23.670000076293945",
    "utc_timestamp": 1508369683,
    "course": "0.0",
    "speed": "0.0",
    "altitude": 0,
    "ad_id": "f109e57e-a02e-41d3-b7c4-c906d1b92331",
    "ad_opt_out": false,
    "id_type": "aaid",
    "device_manufacturer": "motorola",
    "device_model": "Moto G (5S) Plus",
    "is_charging": true,
    "os_version": "Android 7.1.1",
    "carrier_name": "T Mobile",
    "wifi_ssid": "\"Jungle\"",
    "wifi_bssid": "10:fe:ed:8d:b5:7c",
    "connection_type": "wifi",
    "location_method": "gps",
    "location_context": "bground"
  }
]
```

If you want to have the SDK send data to your own AWS s3 environment for example, look into setting up an [Kinesis firehose](https://aws.amazon.com/kinesis/firehose/) according to the SDK request above.

## Location Permission Opt-In Best Practices

OpenLocate requires users to accept the Android's Location Permission in order to work correctly. It is therefore important to understand when and how to prompt for the location permission in order to maximize opt-in rates from users. OpenLocate takes care of prompting the location permission atomically for you when the `startTracking()` method is invoked. OpenLocate also takes care of remembering this started state across app launches, so you only need to invoke `startTracking()` once. You must decide  the optimal time to invoke `startTracking()` within your app however. Below are several articles that explain the different approaches that can be taken. Ensure you choose one that fits your app’s needs:
- https://medium.com/product-breakdown/5-ways-to-ask-users-for-ios-permissions-a8e199cc83ad
- https://www.doronkatz.com/articles/the-right-way-to-ask-users-for-ios-permissions-medium-1

## Communication

- If you **need help**, post a question to the [discussion forum](https://groups.google.com/a/openlocate.org/d/forum/openlocate), or tag a question with 'OpenLocate' on [Stack Overflow](https://stackoverflow.com).
- If you **found a bug**, open an issue.
- If you **have a feature request**, open an issue.
- If you **want to contribute**, submit a pull request.

## License

This project is licensed under the MIT License - see the LICENSE.md file for details.
# hexalocate-android
