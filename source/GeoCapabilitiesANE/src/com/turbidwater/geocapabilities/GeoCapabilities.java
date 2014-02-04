package com.turbidwater.geocapabilities;


import com.adobe.fre.FREContext;
import com.adobe.fre.FREExtension;

public class GeoCapabilities implements FREExtension {

	@Override
	public FREContext createContext(String arg0) {
		return new GeoCapabilitiesContext();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub

	}

}
