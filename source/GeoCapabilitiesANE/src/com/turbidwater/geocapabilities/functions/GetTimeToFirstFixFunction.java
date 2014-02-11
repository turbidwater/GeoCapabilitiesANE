package com.turbidwater.geocapabilities.functions;

import android.location.GpsStatus;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.adobe.fre.FREWrongThreadException;
import com.turbidwater.geocapabilities.GeoCapabilitiesContext;

public class GetTimeToFirstFixFunction implements FREFunction {

	@Override
	public FREObject call(FREContext context, FREObject[] args) {
		GeoCapabilitiesContext geoContext = (GeoCapabilitiesContext) context;
		GpsStatus gpsStatus = geoContext.gpsStatus;
		
		int timeToFirstFix = -1;
		if( gpsStatus != null )
		{			
			timeToFirstFix = gpsStatus.getTimeToFirstFix();
		}
		
		FREObject firstFixObject = null;
		
		//Prep the data for return
		try
		{ 
			firstFixObject = FREObject.newObject( timeToFirstFix ); 
		}
		catch( FREWrongThreadException e )
		{
			
		}
		
		return firstFixObject;
	}

}
