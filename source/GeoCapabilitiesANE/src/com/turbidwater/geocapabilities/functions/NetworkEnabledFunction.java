package com.turbidwater.geocapabilities.functions;

import android.location.LocationManager;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.adobe.fre.FREWrongThreadException;
import com.turbidwater.geocapabilities.GeoCapabilitiesContext;

public class NetworkEnabledFunction implements FREFunction {

	@Override
	public FREObject call( FREContext context, FREObject[] args ) 
	{
		//Get the location manager
		GeoCapabilitiesContext geoContext = (GeoCapabilitiesContext) context;
		LocationManager locationManager = geoContext.locationManager;
		
		//Check to see if the GPS Provider is enabled
		Boolean networkEnabled = locationManager.isProviderEnabled( LocationManager.NETWORK_PROVIDER ); 
		
		FREObject networkEnabledObject = null; 
		try
		{ 
			networkEnabledObject = FREObject.newObject( networkEnabled ); 
		}
		catch( FREWrongThreadException e )
		{
			
		}
		
		return networkEnabledObject;
	}

}
