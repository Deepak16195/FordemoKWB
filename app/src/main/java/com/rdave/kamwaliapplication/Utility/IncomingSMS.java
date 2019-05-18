package com.rdave.kamwaliapplication.Utility;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;



public class IncomingSMS extends BroadcastReceiver
{

	@Override
	public void onReceive(Context context, Intent intent)
	{

		final Bundle bundle = intent.getExtras();
		try {
			if (bundle != null) 
			{
				final Object[] pdusObj = (Object[]) bundle.get("pdus");
				for (int i = 0; i < pdusObj .length; i++) 
				{
					SmsMessage currentMessage = SmsMessage.createFromPdu((byte[])                                                                                                    pdusObj[i]);
					String phoneNumber = currentMessage.getDisplayOriginatingAddress();
					String senderNum = phoneNumber ;
					String newSender=senderNum.subSequence(3, 9).toString();
					String message = currentMessage .getDisplayMessageBody();
					try
					{ 
						if (newSender.equalsIgnoreCase("mdsoft"))
						{

							/*CustomerLogin Sms = new CustomerLogin();
							Sms.recivedSms(message);*/




						}
					}
					catch(Exception e){
						Log.e("SMS Read", e.getMessage());

					}

				}
			}

		} catch (Exception e)
		{
			Log.e("SMS Read", e.getMessage());
		}
	}

}
