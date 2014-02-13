package com.turbidwater.geocapabilities;

import java.util.HashMap;
import java.util.Map;

import android.app.PendingIntent;
import android.content.Context;
import android.location.GpsStatus.Listener;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.turbidwater.geocapabilities.functions.GPSEnabledFunction;
import com.turbidwater.geocapabilities.functions.GetTimeToFirstFixFunction;
import com.turbidwater.geocapabilities.functions.InitFunction;
import com.turbidwater.geocapabilities.functions.NetworkEnabledFunction;
import com.turbidwater.geocapabilities.functions.SetProximityAlertFunction;
import com.turbidwater.geocapabilities.functions.StartNetworkProviderMonitoringFunction;
import com.turbidwater.geocapabilities.functions.StopNetworkProviderMonitoringFunction;
import com.turbidwater.geocapabilities.receivers.ProximityAlertReceiver;

public class GeoCapabilitiesContext extends FREContext implements Listener, LocationListener 
{
	//-----------------------------------------------------------
	//  DECLARATIONS
	//-----------------------------------------------------------
	public static final String PROXIMITY_ALERT = "com.turbidwater.geocapabilities.PROXIMITY_ALERT";
	private final static String TAG = "GeoCapabilities"; 
	
	public LocationManager locationManager; 
	public GpsStatus gpsStatus;
	public Context appContext;
	public Map<String, PendingIntent> pendingIntentMap;
	
	public Boolean listening = false;
	public ProximityAlertReceiver proximityAlertReceiver;
	
	
	//-----------------------------------------------------------
	//  INIT METHODS
	//-----------------------------------------------------------
	@Override
	public void dispose() 
	{
		//Clean up broadcast receivers
		appContext.unregisterReceiver( proximityAlertReceiver );
		
		//Clean up location manager
		locationManager.removeGpsStatusListener( this );
		if( listening )
		{
			locationManager.removeUpdates( this );
		}
		locationManager = null;
	}

	
	//-----------------------------------------------------------
	//  CONTROL
	//-----------------------------------------------------------
	@Override
	public Map<String, FREFunction> getFunctions() 
	{
		Log.d( TAG, "Getting functions " );
		Map<String, FREFunction> functions = new HashMap<String, FREFunction>();
		functions.put( "init", new InitFunction() );
	    functions.put( "isGPSEnabled", new GPSEnabledFunction() );
	    functions.put( "isNetworkEnabled", new NetworkEnabledFunction() );
	    functions.put( "startMonitoringNetworkProvider", new StartNetworkProviderMonitoringFunction() );
	    functions.put( "stopMonitoringNetworkProvider", new StopNetworkProviderMonitoringFunction() );
	    functions.put( "getTimeToFirstFix", new GetTimeToFirstFixFunction() );
	    functions.put( "addProximityAlert", new SetProximityAlertFunction() );
	    
	    return functions;
	}
	
	
	//-----------------------------------------------------------
	//  EVENT HANDLERS
	//-----------------------------------------------------------
	public void onGpsStatusChanged( int status ) 
	{
		String statusString = Integer.toString( status );
		Log.d( TAG, "GPS Status Changed: " + statusString );
		dispatchStatusEventAsync( "gpsStatusChanged", statusString );
		gpsStatus = locationManager.getGpsStatus( null );
	}
	
	public void onLocationChanged( Location location ) 
	{
		//Do nothing
	}

	public void onProviderEnabled( String provider ) 
	{
		String status = "passiveStatusChanged";
		if( provider.equals( LocationManager.GPS_PROVIDER ) ) 
		{
			status = "gpsStatusChanged";
		}
		else if( provider.equals( LocationManager.NETWORK_PROVIDER ) )
		{
			status = "networkStatusChanged";
		}
		dispatchStatusEventAsync( status, "5" );
	}
	
	public void onProviderDisabled( String provider ) 
	{
		String status = "passiveStatusChanged";
		if( provider.equals( LocationManager.GPS_PROVIDER ) ) 
		{
			status = "gpsStatusChanged";
		}
		else if( provider.equals( LocationManager.NETWORK_PROVIDER )  )
		{
			status = "networkStatusChanged";
		}
		dispatchStatusEventAsync( status, "6" );
	}
	
	
	public void onStatusChanged(String provider, int status, Bundle extras) 
	{
		String statusType = "passiveStatusChanged";
		if( provider.equals( LocationManager.GPS_PROVIDER ) ) 
		{
			statusType = "gpsStatusChanged";
		}
		else if( provider.equals( LocationManager.NETWORK_PROVIDER ) )
		{
			statusType = "networkStatusChanged";
		}
		
		dispatchStatusEventAsync( statusType, Integer.toString( status ) );
	}
}
