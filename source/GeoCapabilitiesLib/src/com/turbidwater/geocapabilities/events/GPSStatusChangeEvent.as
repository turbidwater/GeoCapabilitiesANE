package com.turbidwater.geocapabilities.events
{
	import flash.events.Event;
	
	public class GPSStatusChangeEvent extends Event
	{
		//-----------------------------------------------------------
		//  DECLARATIONS
		//-----------------------------------------------------------
		public static const GPS_STATUS_CHANGED:String = "gpsStatusChanged";
		
		public var status:int = 0;

		
		//-----------------------------------------------------------
		//  INIT
		//-----------------------------------------------------------
		public function GPSStatusChangeEvent( type:String, status:int, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
			
			this.status = status;
		}
		
		
		//-----------------------------------------------------------
		//  CONTROL
		//-----------------------------------------------------------
		override public function clone():Event
		{
			return new GPSStatusChangeEvent( type, status, bubbles, cancelable );
		}
	}
}