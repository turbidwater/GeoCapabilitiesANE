package com.turbidwater.geocapabilities
{
	
	import flash.external.ExtensionContext;
	import flash.sensors.Geolocation;

	public class GeoCapabilities
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
			throw( new Error( "Don't instantiate GeoCapabilities" ) );
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