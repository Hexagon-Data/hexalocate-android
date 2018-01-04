
## Installation

### Adding to your project

Check that jCenter is in your project level `build.gradle`. If not, Add the below line:

```groovy
repositories {
    jcenter()
}
```

Add the below line to your app's `build.gradle` inside the `dependencies` section:
    
```groovy
compile 'io.hexagondata:hexalocate:0.0.1'
```

## Usage

### Initialization
Configure SDK by building the configuration with CLIENT_ID and APP_ID provided by Hexagon Data. Supply the configuration to the `initialize` method.  Initialize HexaLocate in the `Application`

```java
import android.app.Application;
import com.hexalocate.android.core.HexaLocate;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        HexaLocate.Configuration configuration = new HexaLocate.Configuration.Builder(this, CLIENT_ID, APP_ID).build();
        HexaLocate.initialize(configuration);
    }
}
```

### Start tracking:
Activity should be passed to method below. Library will request permission for you.

```java
 HexaLocate.getInstance().startTracking(activity);
```
`startTracking` method can be called only once: library will restart tracking on next initialization.

### Stop tracking of location

To stop location tracking, call the `stopTracking` method on `HexaLocate`. Get the instance by calling `getInstance`.

```java
HexaLocate.getInstance().stopTracking()
```

## Location Permission Opt-In Best Practices

HexaLocate requires users to accept the Android's Location Permission in order to work correctly. It is therefore important to understand when and how to prompt for the location permission in order to maximize opt-in rates from users. HexaLocate takes care of prompting the location permission atomically for you when the `startTracking()` method is invoked. HexaLocate also takes care of remembering this started state across app launches, so you only need to invoke `startTracking()` once. You must decide  the optimal time to invoke `startTracking()` within your app however. Below are several articles that explain the different approaches that can be taken. Ensure you choose one that fits your app’s needs:
- https://medium.com/product-breakdown/5-ways-to-ask-users-for-ios-permissions-a8e199cc83ad
- https://www.doronkatz.com/articles/the-right-way-to-ask-users-for-ios-permissions-medium-1

## Communication

- If you **found a bug**, open an issue.
- If you **have a feature request**, open an issue.
- If you **want to contribute**, submit a pull request.

## License

This project is licensed under the MIT License - see the LICENSE.md file for details.