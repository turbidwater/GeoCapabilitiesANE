package com.turbidwater.geocapabilities
{
	
	import com.turbidwater.geocapabilities.events.ProviderStatusChangeEvent;
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