package com.turbidwater.geocapabilities
{
	import com.turbidwater.geocapabilities.events.ProviderStatusChangeEvent;
	import com.turbidwater.geocapabilities.events.ProximityAlertEvent;
	import com.turbidwater.geocapabilities.model.GPSStatus;
	
	import flash.events.EventDispatcher;
	import flash.events.StatusEvent;
	import flash.sensors.Geolocation;

	public class GeoCapabilities extends EventDispatcher
	{
		//-----------------------------------------------------------
		//  DECLARATIONS
		//-----------------------------------------------------------
		
		
		//-----------------------------------------------------------
		//  INIT
		//-----------------------------------------------------------
		public function GeoCapabilities()
		{
			super();			
		}

		
		//-----------------------------------------------------------
		//  CONTROL
		//-----------------------------------------------------------
		public function startNetworkProviderMonitoring():void
		{
			trace( 'start monitoring called' );
		}
		
		public function stopNetworkProviderMonitoring():void
		{
			trace( 'stop monitoring called' );
		}
		
		public function addProximityAlert( lat:Number, long:Number, radius:Number, expiration:int ):void
		{
			trace( 'addProximityAlert' );
			if( expiration < 0 || expiration > 24 * 60 * 60 * 1000 )
			{
				throw( new Error( 'Expiration cannot be eternal or longer than a day' ) );
			}
		}
		
		
		//-----------------------------------------------------------
		//  EVENT HANDLERS
		//-----------------------------------------------------------
		protected function onStatus( event:StatusEvent ):void
		{
			trace( 'Status Event ' + event.code + ', ' + event.level );
			
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
			
			return gpsEnabled;
		}
		
		public static function get isNetworkEnabled():Boolean
		{
			var networkEnabled:Boolean = true;
			return networkEnabled;
		}
		
		/**
		 * Is GeoCapabilities supported?
		 * 
		 * @return	Boolean
		 */
		public static function get isSupported():Boolean
		{
			return Boolean( Geolocation.isSupported );
		}
	}
}