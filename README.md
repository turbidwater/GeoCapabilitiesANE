# GeoCapabilities ANE
GeoCapabilities is an AIR Native Extension designed to expose more granular information on whether GPS is enabled or disabled. Geolocation API only tells if access
has been denied to location information. GeoCapabilities tells whether GPS is turned on or not, or if Network-based location has been denied. Note: putting your phone
in Airplane Mode does NOT disabled network location detection as far as the Android LocationManager API is concerned. It only tells if location data via wi-fi or network
has been disabled in Settings > Location.

This extension is Android only (with very limited desktop simulator support). There is no intention of supporting iOS.

## Notes
GPS status events are dispatched only after an application attempts to use the GPS. 
GPS status events can also be dispatched when Network location providers (wi-fi & towers) are disabled or enabled
Network status events will only be dispatched after calling startNetworkProviderMonitoring. Once you don't need it, make sure to call stopNetworkProviderMonitoring to prevent unnecessary battery drain
Toggling location access for the Network is very noisy. Several events are dispatched while the user approves access. The last one is what matters.

## Projects
* **GeoCapabilitiesLib**
Flex Library project that contains the AS3 API for the ANE.
* **GeoCapabilitiesDefault**
Flex Library project. Default implementation of API that should allow this ANE to work on desktop simulators. Should, but might not at the moment
* **GeoCapabilitiesANE**
Android project in Java used to generate the jar for the ANE
* **GeoCapabilitiesTest**
Flex Project. Sample implementation project.
* **GeoCapabilitiesTestAndroid**
Android Project in Java used for debugging the Java side of things. In Devices panel in Eclipse, after running AIR app in debug, select the app identifier and
click the green debug icon to enable breakpoints.

## Build the ANE
To build the ANE, 
1. go to the build directory in console 
2. put in the following command:
adt -package -target ane GeoCapabilities.ane extension.xml -swc GeoCapabilitiesLib.swc -platform Android-ARM -C android . -platform default -C default .
3. copy the GeoCapabilities.ane into the libs folder of GeoCapabilitiesTest

This assumes you have adt installed. If you don't, follow the instructions in this tutorial, which is how I got my start:
http://www.adobe.com/devnet/air/articles/building-ane-ios-android-pt1.html
Part 5 of that article tells how to structure your build files for the ANE.
