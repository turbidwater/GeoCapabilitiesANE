package com.turbidwater.geocapabilities.functions;

import android.location.LocationManager;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.adobe.fre.FREWrongThreadException;
import com.turbidwater.geocapabilities.GeoCapabilitiesContext;

public class GPSEnabledFunction implements FREFunction 
{

	//-----------------------------------------------------------
	//  CONTROL
	//-----------------------------------------------------------
	@Override
	public FREObject call( FREContext context, FREObject[] args ) 
	{
		//Get the location manager
		GeoCapabilitiesContext geoContext = (GeoCapabilitiesContext) context;
		LocationManager locationManager = geoContext.locationManager;
		
		//Check to see if the GPS Provider is enabled
		Boolean gpsEnabled = locationManager.isProviderEnabled( LocationManager.GPS_PROVIDER ); 
		FREObject gpsEnabledObject = null;
		
		//Prep the data for return
		try
		{ 
			gpsEnabledObject = FREObject.newObject( gpsEnabled ); 
		}
		catch( FREWrongThreadException e )
		{
			
		}
		
		return gpsEnabledObject;
	}

}
