package com.turbidwater.geocapabilities.functions;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.turbidwater.geocapabilities.GeoCapabilitiesContext;

public class SetProximityAlertFunction implements FREFunction {
	
	public static final String LAT = "com.turbidwater.geocapabilities.LAT";
	public static final String LON = "com.turbidwater.geocapabilities.LON";
	
	private static final String TAG = "GeoCapabilities";
	
	private GeoCapabilitiesContext geoContext;
	
	@Override
	public FREObject call( FREContext context, FREObject[] args ) {
		
		Context appContext = context.getActivity().getApplicationContext();
		//If the geoContext hasn't been set up yet, go ahead and do that. Only need to do it once
		if( geoContext == null )
		{
			geoContext = (GeoCapabilitiesContext) context;
		}
		
		//Parameter buckets
		double lat = 0;
		double lon = 0;
		float radius = 10;
		long expiration = 1000;
		
		//Pull the parameters out of FREObject array and put in buckets
		try 
	    {
			lat = args[0].getAsDouble();
	        lon = args[1].getAsDouble();
	        radius = args[2].getAsInt();
	        expiration = args[3].getAsInt();
	    } 
	    catch (Exception e) 
	    {
	    	Log.d( TAG, "Failed to pull parameters out: " + e.getMessage() );
	    }
		
		//Create the intent to send
		Intent proximityIntent = new Intent();
		proximityIntent.setAction( GeoCapabilitiesContext.PROXIMITY_ALERT );
		proximityIntent.putExtra( LAT, lat );
		proximityIntent.putExtra( LON, lon );
		
		//This should theoretically be heard by the broadcast responder on the GeoCapabilitiesContext
		PendingIntent pendingProximityIntent = PendingIntent.getBroadcast( appContext, 0, proximityIntent, PendingIntent.FLAG_ONE_SHOT );
		try
		{
			geoContext.locationManager.addProximityAlert( lat, lon, radius, expiration, pendingProximityIntent );
		}
		catch( Error e )
		{
			Log.d( TAG, e.getMessage() );
		}
		
		return null;
	}
}
