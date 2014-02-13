package com.turbidwater.geocapabilities.receivers;

import com.adobe.fre.FREContext;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ProximityAlertReceiver extends BroadcastReceiver {

	public static final String PROXIMITY_ALERT = "com.turbidwater.geocapabilities.PROXIMITY_ALERT";
	public static final String LAT = "com.turbidwater.geocapabilities.LAT";
	public static final String LON = "com.turbidwater.geocapabilities.LON";
	
	protected FREContext freContext;
	
	public ProximityAlertReceiver( FREContext context )
	{
		freContext = context;
	}
	
	@Override
	public void onReceive( Context context, Intent intent ) {
		
		String lat = Double.toString( intent.getDoubleExtra(LAT, 0) );
		String lon = Double.toString( intent.getDoubleExtra(LON, 0) );
		
		freContext.dispatchStatusEventAsync( "proximityAlert", lat + "," + lon  );

	}

}
