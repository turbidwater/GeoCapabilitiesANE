package com.turbidwater.geocapabilities;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.location.GpsStatus.Listener;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.turbidwater.geocapabilities.functions.GPSEnabledFunction;
import com.turbidwater.geocapabilities.functions.NetworkEnabledFunction;

public class GeoCapabilitiesContext extends FREContext implements Listener, LocationListener 
{
	//-----------------------------------------------------------
	//  DECLARATIONS
	//-----------------------------------------------------------
	private final static String TAG = "GeoCapabilitieContext"; 
	public LocationManager locationManager; 
	
	
	//-----------------------------------------------------------
	//  INIT METHODS
	//-----------------------------------------------------------
	public void init() 
	{
		//Get the location manager
		Context appContext = getActivity().getApplicationContext();
		locationManager = (LocationManager) appContext.getSystemService( Context.LOCATION_SERVICE );
		locationManager.addGpsStatusListener( this );
	}

	@Override
	public void dispose() 
	{
		//Clean up location manager
		locationManager.removeGpsStatusListener( this );
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
	    functions.put("isGPSEnabled", new GPSEnabledFunction() );
	    functions.put("isNetworkEnabled", new NetworkEnabledFunction() );
	    
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
	}
	
	public void onLocationChanged( Location location) 
	{
		//Do nothing
	}
	
	public void onProviderDisabled( String provider ) 
	{
		String status = "passiveEnabledChange";
		if( provider == LocationManager.GPS_PROVIDER ) 
		{
			status = "gpsEnabledChange";
		}
		else if( provider == LocationManager.NETWORK_PROVIDER )
		{
			status = "networkEnabledChange";
		}
		dispatchStatusEventAsync( status, "0" );
	}
	
	public void onProviderEnabled( String provider ) 
	{
		String status = "passiveEnabledChange";
		if( provider == LocationManager.GPS_PROVIDER ) 
		{
			status = "gpsEnabledChange";
		}
		else if( provider == LocationManager.NETWORK_PROVIDER )
		{
			status = "networkEnabledChange";
		}
		dispatchStatusEventAsync( status, "0" );
	}
	
	public void onStatusChanged(String provider, int status, Bundle extras) 
	{
		dispatchStatusEventAsync("providerStatusChange", provider + ":" + Integer.toString( status ) );
	}
}
