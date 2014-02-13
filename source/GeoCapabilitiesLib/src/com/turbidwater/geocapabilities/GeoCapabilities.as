package com.turbidwater.geocapabilities
{
	
	import com.turbidwater.geocapabilities.events.ProviderStatusChangeEvent;
	import com.turbidwater.geocapabilities.events.ProximityAlertEvent;
	import com.turbidwater.geocapabilities.model.GPSStatus;
	
	import flash.events.EventDispatcher;
	import flash.events.StatusEvent;
	import flash.external.ExtensionContext;
	import flash.sensors.Geolocation;

	public class GeoCapabilities extends EventDispatcher
	{
		//-----------------------------------------------------------
		//  DECLARATIONS
		//-----------------------------------------------------------
		protected static var extensionContext:ExtensionContext = ExtensionContext.createExtensionContext( 'com.turbidwater.geocapabilities', '' );

		
		//-----------------------------------------------------------
		//  INIT
		//-----------------------------------------------------------
		public function GeoCapabilities()
		{
			super();
			
			extensionContext.addEventListener( StatusEvent.STATUS, onStatus );
			extensionContext.call( 'init' );
		}

		
		//-----------------------------------------------------------
		//  CONTROl
		//-----------------------------------------------------------
		public function startNetworkProviderMonitoring():void
		{
			extensionContext.call( 'startMonitoringNetworkProvider' );
		}
		
		public function stopNetworkProviderMonitoring():void
		{
			extensionContext.call( 'stopMonitoringNetworkProvider' );
		}
		
		/**
		 * Adds a proximity alert using the LocationManager.addProximityAlert method in Java. It is
		 * intentionally limited in its range to avoid performance issues from unlimited alerts
		 * 
		 * @param	lat (Number) latitude of point to trigger alert
		 * @param	long (Number) longitude of point to trigger alert
		 * @param	radius (Number) how close to the point to get before triggering alert
		 * @param	expiration (int) the number of milliseconds for this watch to last
		 * @return	void
		 */
		public function addProximityAlert( lat:Number, long:Number, radius:Number, expiration:int ):void
		{
			if( expiration < 0 || expiration > 24 * 60 * 60 * 1000 )
			{
				throw( new Error( 'For performance concerns, expiration cannot be eternal or longer than a day' ) );
			}
			extensionContext.call( 'addProximityAlert', lat, long, radius, expiration );
		}
		
		
		//-----------------------------------------------------------
		//  EVENT HANDLERS
		//-----------------------------------------------------------
		protected function onStatus( event:StatusEvent ):void
		{
			switch( event.code )
			{
				case ProviderStatusChangeEvent.GPS_STATUS_CHANGED: 
				{
					//Weed out the overwhelming flood of useless status events
					if( int( event.level ) != GPSStatus.GPS_STATUS )
					{
						dispatchEvent( new ProviderStatusChangeEvent( ProviderStatusChangeEvent.GPS_STATUS_CHANGED, int( event.level ) ) );
					}
					break;
				}
				case ProviderStatusChangeEvent.NETWORK_STATUS_CHANGED: 
				{
					dispatchEvent( new ProviderStatusChangeEvent( ProviderStatusChangeEvent.NETWORK_STATUS_CHANGED, int( event.level ) ) );
					break;
				}
				case ProximityAlertEvent.PROXIMITY_ALERT: 
				{
					var latLong:Array = event.level.split( "," );
					dispatchEvent( new ProximityAlertEvent( ProximityAlertEvent.PROXIMITY_ALERT, Number( latLong[0] ), Number( latLong[1] ) ) );
					break;
				}
				default:
				{
					dispatchEvent( event );
				}
			}
			
		}
		
		//-----------------------------------------------------------
		//  GETTERS/SETTERS
		//-----------------------------------------------------------
		public static function get isGPSEnabled():Boolean
		{
			//Set default value
			var geo:Geolocation = new Geolocation();
			var gpsEnabled:Boolean = Geolocation.isSupported && !geo.muted;
			try
			{
				gpsEnabled = extensionContext.call( 'isGPSEnabled' );
			}
			catch( e:Error )
			{
				trace( 'ERROR on isGPSEnabled: ' + e.message );
			}
			
			return gpsEnabled;
		}
		
		public static function get isNetworkEnabled():Boolean
		{
			var networkEnabled:Boolean = true;
			try
			{
				networkEnabled = extensionContext.call( 'isNetworkEnabled' );
			}
			catch( e:Error )
			{
				trace( 'ERROR on isNetworkEnabled: ' + e.message );
			}
			
			return networkEnabled;
		}
		
		/**
		 * Is GeoCapabilities supported?
		 * 
		 * @return	Boolean
		 */
		public static function get isSupported():Boolean
		{
			return Boolean( extensionContext && Geolocation.isSupported );
		}
		
		/**
		 * Time in milliseconds it took for the first satellite to get a fix since
		 * the GPS has been enabled. Returns -1 if GPS has not started.
		 * 
		 * @return	int
		 */
		public function get timeToFirstFix():int
		{
			return extensionContext.call( 'getTimeToFirstFix' ) as int;
		}
	}
}