package com.turbidwater.geocapabilities.functions;

import android.location.LocationManager;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.turbidwater.geocapabilities.GeoCapabilitiesContext;

public class StartNetworkProviderMonitoringFunction implements FREFunction {

	@Override
	public FREObject call( FREContext context, FREObject[] args ) 
	{
		GeoCapabilitiesContext geoContext = (GeoCapabilitiesContext) context;
		if( !geoContext.listening )
		{
			geoContext.locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 20000, 10000, geoContext );
			geoContext.listening = true;
		}
		return null;
	}

}
