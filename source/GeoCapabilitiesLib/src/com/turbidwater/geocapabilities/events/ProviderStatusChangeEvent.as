package com.turbidwater.geocapabilities.events
{
	import flash.events.Event;
	
	public class ProviderStatusChangeEvent extends Event
	{
		//-----------------------------------------------------------
		//  DECLARATIONS
		//-----------------------------------------------------------
		public static const GPS_STATUS_CHANGED:String = "gpsStatusChanged";
		public static const NETWORK_STATUS_CHANGED:String = 'networkStatusChanged';
		
		public var status:int = 0;

		
		//-----------------------------------------------------------
		//  INIT
		//-----------------------------------------------------------
		public function ProviderStatusChangeEvent( type:String, status:int, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
			
			this.status = status;
		}
		
		
		//-----------------------------------------------------------
		//  CONTROL
		//-----------------------------------------------------------
		override public function clone():Event
		{
			return new ProviderStatusChangeEvent( type, status, bubbles, cancelable );
		}
	}
}