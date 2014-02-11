package com.turbidwater.geocapabilities.functions;

import android.content.Context;
import android.location.LocationManager;
import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.turbidwater.geocapabilities.GeoCapabilitiesContext;

public class InitFunction implements FREFunction {

	private static final String TAG = "GeoCapabilities";
	
	@Override
	public FREObject call( FREContext context, FREObject[] args) {
		//Get the location manager
		Context appContext = context.getActivity().getApplicationContext();
		Log.d( TAG, "made app context " );
		GeoCapabilitiesContext geoContext = (GeoCapabilitiesContext) context;
		geoContext.locationManager = (LocationManager) appContext.getSystemService( Context.LOCATION_SERVICE );
		Log.d( TAG, "made location manager " );
		geoContext.locationManager.addGpsStatusListener( geoContext );
		Log.d( TAG, "add GPS listener " );
		
		return null;
	}

}
