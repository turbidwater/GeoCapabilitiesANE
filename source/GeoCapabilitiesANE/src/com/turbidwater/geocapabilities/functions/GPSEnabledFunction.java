package com.turbidwater.geocapabilities.functions;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.adobe.fre.FREWrongThreadException;

public class GPSEnabledFunction implements FREFunction {

	@Override
	public FREObject call( FREContext context, FREObject[] args ) 
	{
		Boolean gpsEnabled = true;
		
		FREObject gpsEnabledObject = null; 
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
