package com.turbidwater.geocapabilities;

import java.util.HashMap;
import java.util.Map;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.turbidwater.geocapabilities.functions.GPSEnabledFunction;
import com.turbidwater.geocapabilities.functions.NetworkEnabledFunction;

public class GeoCapabilitiesContext extends FREContext {

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<String, FREFunction> getFunctions() {
		Map<String, FREFunction> functions = new HashMap<String, FREFunction>();
	    functions.put("isGPSEnabled", new GPSEnabledFunction() );
	    functions.put("isNetworkEnabled", new NetworkEnabledFunction() );
	    
	    return functions;
	}

}
