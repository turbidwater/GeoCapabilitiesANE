package com.turbidwater.geocapabilities.functions;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.adobe.fre.FREWrongThreadException;

public class NetworkEnabledFunction implements FREFunction {

	@Override
	public FREObject call( FREContext context, FREObject[] args ) 
	{
		Boolean networkEnabled = true;
		
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
