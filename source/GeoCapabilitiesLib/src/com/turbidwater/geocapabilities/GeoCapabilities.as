package com.turbidwater.geocapabilities
{
	
	import com.turbidwater.geocapabilities.events.GPSStatusChangeEvent;
	
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
	}
}