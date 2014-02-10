package com.turbidwater.geocapabilities
{
	import com.turbidwater.geocapabilities.events.GPSStatusChangeEvent;
	
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
		//  EVENT HANDLERS
		//-----------------------------------------------------------
		protected function onStatus( event:StatusEvent ):void
		{
			trace( 'Status Event ' + event.code + ', ' + event.level );
			
			switch( event.code )
			{
				case GPSStatusChangeEvent.GPS_STATUS_CHANGED: 
				{
					dispatchEvent( new GPSStatusChangeEvent( GPSStatusChangeEvent.GPS_STATUS_CHANGED, int( event.level ) ) );
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