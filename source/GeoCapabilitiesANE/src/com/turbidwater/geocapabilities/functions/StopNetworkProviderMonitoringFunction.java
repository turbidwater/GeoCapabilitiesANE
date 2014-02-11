package com.turbidwater.geocapabilities.functions;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.turbidwater.geocapabilities.GeoCapabilitiesContext;

public class StopNetworkProviderMonitoringFunction implements FREFunction {

	@Override
	public FREObject call( FREContext context, FREObject[] args ) 
	{
		GeoCapabilitiesContext geoContext = (GeoCapabilitiesContext) context;
		if( geoContext.listening )
		{
			geoContext.locationManager.removeUpdates( geoContext );
			geoContext.listening = false;
		}
		return null;
	}

}
