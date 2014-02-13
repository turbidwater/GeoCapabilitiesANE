package com.turbidwater.geocapabilities.events
{
	import flash.events.Event;
	
	public class ProximityAlertEvent extends Event
	{
		//-----------------------------------------------------------
		//  DECLARATIONS
		//-----------------------------------------------------------
		public static const PROXIMITY_ALERT:String = "proximityAlert";
		
		public var lat:Number;
		public var long:Number;
		
		
		//-----------------------------------------------------------
		//  INIT
		//-----------------------------------------------------------
		public function ProximityAlertEvent(type:String, lat:Number, long:Number, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
			
			this.lat = lat;
			this.long = long
		}
		
		
		//-----------------------------------------------------------
		//  CONTROL
		//-----------------------------------------------------------
		override public function clone():Event
		{
			return new ProximityAlertEvent( type, lat, long, bubbles, cancelable );
		}
	}
}