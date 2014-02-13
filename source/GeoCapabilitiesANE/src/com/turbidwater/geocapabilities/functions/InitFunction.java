package com.turbidwater.geocapabilities.functions;

import android.content.Context;
import android.content.IntentFilter;
import android.location.LocationManager;
import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.turbidwater.geocapabilities.GeoCapabilitiesContext;
import com.turbidwater.geocapabilities.receivers.ProximityAlertReceiver;

public class InitFunction implements FREFunction {

	private static final String TAG = "GeoCapabilities";
	
	@Override
	public FREObject call( FREContext context, FREObject[] args) {
		//Get the location manager
		Context appContext = context.getActivity().getApplicationContext();
		GeoCapabilitiesContext geoContext = (GeoCapabilitiesContext) context;
		geoContext.locationManager = (LocationManager) appContext.getSystemService( Context.LOCATION_SERVICE );
		geoContext.locationManager.addGpsStatusListener( geoContext );
		Log.d( TAG, "Set up location listeners " );
		
		//Set up broadcast receiver for proximity alerts
		IntentFilter filter = new IntentFilter( GeoCapabilitiesContext.PROXIMITY_ALERT ); 
		geoContext.proximityAlertReceiver = new ProximityAlertReceiver( geoContext );
		appContext.registerReceiver( geoContext.proximityAlertReceiver, filter );
		Log.d( TAG, "Set up proximity alert listeners " );
		
		return null;
	}

}
