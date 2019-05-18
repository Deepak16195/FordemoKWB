package com.rdave.kamwaliapplication.Activity;

//import com.sun.mail.handlers.text_xml;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.rdave.kamwaliapplication.R;
import com.rdave.kamwaliapplication.Utility.SharePrefarence;

public class OnlineStatus extends Activity implements OnClickListener{

	Button btnProceed;
	TextView txtUserName;
	TextView txtMobile;
	TextView txt_SuccMessage;
	String mStatus;
	String mTrackNo;
	String Username,UserNumber;
	TextView txtTransMsg;



	TextView txtCongratulations;


	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.onlinestatus);
		Username = SharePrefarence.getmInstance(OnlineStatus.this).getUserName();
		UserNumber = SharePrefarence.getmInstance(OnlineStatus.this).getMobile();
		

		
		txtUserName=(TextView)findViewById(R.id.txt_UserName);
		txtMobile=(TextView)findViewById(R.id.txt_UserMobile);
		txt_SuccMessage=(TextView)findViewById(R.id.txt_SuccMessage);
		txtTransMsg=(TextView)findViewById(R.id.txtTransMsg);

		txtCongratulations=(TextView)findViewById(R.id.txtCongratulations);
			txtMobile.setText(UserNumber);
		txtUserName.setText(Username);
		if(getIntent().getStringExtra("Status")!=null)
		{
			mStatus=getIntent().getStringExtra("Status").toString();
			if(mStatus.equalsIgnoreCase("Successful"))
			{
				txtCongratulations.setText("Congratulations !!!");

			}
			else
			{
				txtCongratulations.setText("Sorry !!!");

			}
		}
		
		
		
		if(getIntent().getStringExtra("TrackNo")!=null)
		{
			mTrackNo=getIntent().getStringExtra("TrackNo").toString();
		}
		String msg=null;
		if((mStatus!=null)&&(mTrackNo!=null))
		{
		msg="Your online transaction is <font color=\"Red\"> "+mStatus.toString()+".</font><br/>The Tracking ID is: "+mTrackNo +"";
		//txtTransMsg.setText(Html.fromHtml(msg), TextView.BufferType.SPANNABLE);
		}
		if(msg!=null)
		{
			txtTransMsg.setText(Html.fromHtml(msg), TextView.BufferType.SPANNABLE);
		}




		
		

		
		btnProceed=(Button)findViewById(R.id.btnSuccessfully_Close);
		
		if(btnProceed!=null)
		{
			btnProceed.setOnClickListener(this);
		}
		

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.btnSuccessfully_Close)
		{
			Intent intent_Main = new Intent(OnlineStatus.this,HomeActivity.class);
			startActivity(intent_Main);
			finishAffinity();
		}
		

	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		OnlineStatus.this.finish();
	}

}
