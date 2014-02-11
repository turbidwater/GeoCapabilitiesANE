package com.turbidwater.geocapabilities;


import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREExtension;

public class GeoCapabilities implements FREExtension 
{
	//-----------------------------------------------------------
	//  DECLARATIONS
	//-----------------------------------------------------------
	private final static String TAG = "GeoCapabilities";
	
	protected GeoCapabilitiesContext geoContext;
	
	
	//-----------------------------------------------------------
	//  CONTROL
	//-----------------------------------------------------------
	@Override
	public FREContext createContext( String arg0 ) 
	{
		Log.d( TAG, "GeoCapabilitiesContext being created" );
		geoContext = new GeoCapabilitiesContext();
		geoContext.initializeGeoCapabilitiesContext();
		return geoContext;
	}

	@Override
	public void dispose() 
	{
		Log.d( TAG, "GeoCapabilities disposed" );
		geoContext.dispose();
		geoContext = null;
	}

	@Override
	public void initialize() 
	{
		Log.d( TAG, "GeoCapabilities initialized" );
	}

}
