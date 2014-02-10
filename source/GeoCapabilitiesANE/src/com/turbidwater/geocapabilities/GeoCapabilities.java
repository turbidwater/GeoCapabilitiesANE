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
	
	protected GeoCapabilitiesContext context;
	
	
	//-----------------------------------------------------------
	//  CONTROL
	//-----------------------------------------------------------
	@Override
	public FREContext createContext(String arg0) {
		context = new GeoCapabilitiesContext();
		context.init();
		return context;
	}

	@Override
	public void dispose() {
		Log.d( TAG, "GeoCapabilities disposed" );
		context.dispose();
		context = null;
	}

	@Override
	public void initialize() {
		Log.d( TAG, "GeoCapabilities initialized" );
	}

}
