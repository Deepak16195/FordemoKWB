package com.rdave.kamwaliapplication.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.rdave.kamwaliapplication.Model.EmpPackStatus;
import com.rdave.kamwaliapplication.R;
import com.rdave.kamwaliapplication.Utility.Utils;
import com.rdave.kamwaliapplication.avenues.lib.utility.AvenuesParams;
import com.rdave.kamwaliapplication.avenues.lib.utility.Constants;
import com.rdave.kamwaliapplication.avenues.lib.utility.RSAUtility;
import com.rdave.kamwaliapplication.avenues.lib.utility.ServiceHandler;
import com.rdave.kamwaliapplication.avenues.lib.utility.ServiceUtility;
import com.rdave.kamwaliapplication.remote.ApiUtils;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EncodingUtils;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CCAvenueWebViewAcitivty extends Activity {
	private ProgressDialog dialog;
	Intent mainIntent;
	String encVal;
	public String ahtml;
	public static String mFirstName;
	public static String mLastName;
	public static String mDOB;
	public static String mEmail;
	public static String mCity;
	public static String mPin;
	public static String mState;
	public static String mDivision;
	public static String mBranch; 
	public static String mLicense;
	public static String mDesignation;
	public static String rdeviceID;
	public static String rMobile;
	public static String rBrand;
	public static String rModel;
	public static String rVersion;
	public static String OrdNo; 
	
	public static String productId;
	public static String amount;
	public static String rateID;
	public static String mOrdNo;
	
	public static String CardNo;
	public static String Bank;
	public static String TrackNo;
	public String status;
	
	public static String bothType;
	public static String mobType;
	public static String PType;
	
	public static String Msg;
	
	public static StringBuffer params;
	
	//Read User Info from Server Variable

	
		
	
	
	
	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_webview);
		mainIntent = getIntent();
		status=null;
/*
		rMobile=getIntent().getStringExtra("Mobile_No").toString();
		mFirstName=getIntent().getStringExtra("First_Name").toString();
		mLastName=getIntent().getStringExtra("Last_Name").toString();
		mDOB=getIntent().getStringExtra("DOB").toString();
		mEmail=getIntent().getStringExtra("Email").toString();
		mCity=getIntent().getStringExtra("City").toString();
		mPin=getIntent().getStringExtra("Pin").toString();
		mState=getIntent().getStringExtra("State").toString();
*/



//		amount=getIntent().getStringExtra(AvenuesParams.AMOUNT).toString();
		mOrdNo=getIntent().getStringExtra(AvenuesParams.ORDER_ID).toString();
		//Msg=getIntent().getStringExtra("OMsg").toString();
		// Calling async task to get display content
		new RenderView().execute();
	}
	
	/**
	 * Async task class to get json by making HTTP call
	 * */
	private class RenderView extends AsyncTask<Void, Void, Void> {
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog
			dialog = new ProgressDialog(CCAvenueWebViewAcitivty.this);
			dialog.setMessage("Please wait...");
			dialog.setCancelable(false);
			dialog.show();
		}

		@Override
		protected Void doInBackground(Void... arg0) {
			// Creating service handler class instance
			ServiceHandler sh = new ServiceHandler();
	
			// Making a request to url and getting response
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair(AvenuesParams.ACCESS_CODE, mainIntent.getStringExtra(AvenuesParams.ACCESS_CODE)));
			params.add(new BasicNameValuePair(AvenuesParams.ORDER_ID, mainIntent.getStringExtra(AvenuesParams.ORDER_ID)));
	
			String vResponse = sh.makeServiceCall(mainIntent.getStringExtra(AvenuesParams.RSA_KEY_URL), ServiceHandler.POST, params);
			System.out.println(vResponse);
			if(!ServiceUtility.chkNull(vResponse).equals("")
					&& ServiceUtility.chkNull(vResponse).toString().indexOf("ERROR")==-1)
			{
				StringBuffer vEncVal = new StringBuffer("");
				vEncVal.append(ServiceUtility.addToPostParams(AvenuesParams.AMOUNT, mainIntent.getStringExtra(AvenuesParams.AMOUNT)));
				vEncVal.append(ServiceUtility.addToPostParams(AvenuesParams.CURRENCY, mainIntent.getStringExtra(AvenuesParams.CURRENCY)));
				vEncVal.append(ServiceUtility.addToPostParams(AvenuesParams.billing_name, mainIntent.getStringExtra(AvenuesParams.billing_name)));
				vEncVal.append(ServiceUtility.addToPostParams(AvenuesParams.billing_address, mainIntent.getStringExtra(AvenuesParams.billing_address)));
				vEncVal.append(ServiceUtility.addToPostParams(AvenuesParams.billing_zip, mainIntent.getStringExtra(AvenuesParams.billing_zip)));
				vEncVal.append(ServiceUtility.addToPostParams(AvenuesParams.billing_city, mainIntent.getStringExtra(AvenuesParams.billing_city)));
				vEncVal.append(ServiceUtility.addToPostParams(AvenuesParams.billing_state, mainIntent.getStringExtra(AvenuesParams.billing_state)));
				vEncVal.append(ServiceUtility.addToPostParams(AvenuesParams.billing_country, mainIntent.getStringExtra(AvenuesParams.billing_country)));
				vEncVal.append(ServiceUtility.addToPostParams(AvenuesParams.billing_tel, mainIntent.getStringExtra(AvenuesParams.billing_tel)));
				vEncVal.append(ServiceUtility.addToPostParams(AvenuesParams.billing_email, mainIntent.getStringExtra(AvenuesParams.billing_email)));
				encVal = RSAUtility.encrypt(vEncVal.substring(0,vEncVal.length()-1), vResponse);
			}
			
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			// Dismiss the progress dialog
			if (dialog.isShowing())
				dialog.dismiss();
			
			@SuppressWarnings("unused")
			class MyJavaScriptInterface
			{
				@JavascriptInterface
			    public void processHTML(String html)
			    {
					
					ahtml=html;
			        // process the html as needed by the app
					
					if(html!=null)
					{
						Log.e("HTML Tags Transaction :", html);
					}
					
			    	status = null;
			    	if(html.indexOf("Failure")!=-1){
			    		status = "Failed";
			    	}else if(html.indexOf("Success")!=-1){
			    		status = "Successful";
			    		
			    	}else if(html.indexOf("Aborted")!=-1){
			    		status = "Cancelled";
			    	}else{
			    		status = "Failed";
			    	}
			    	
			    	
			    	
			    	if(ahtml!=null)
			    	{

			    		TrackNo=ahtml.substring(ahtml.indexOf("tracking_id"),ahtml.indexOf("bank_ref_no")).toString();
						TrackNo=TrackNo.toLowerCase().replace("=","").replace(" ","").replace("<br>","").replace("tracking_id","");
					//need to talk dhruv sir regarding save track no. against order no.



//						if(status.equalsIgnoreCase("Successful"))
//						{
							UpdateEmpPackStatus(mOrdNo,TrackNo,status);
//						}
//						else
//						{
//							Intent intent_Main = new Intent(CCAvenueWebViewAcitivty.this,OnlineStatus.class);
//							intent_Main.putExtra("Status",status);
//							intent_Main.putExtra("TrackNo",TrackNo);
//
//							startActivity(intent_Main);
//							CCAvenueWebViewAcitivty.this.finish();
//						}

						//

			    		//new saveOrderServer(CCAvenueWebViewAcitivty.this).execute();
			    	}
			    	

			    	//Log.e("Track", TrackNo);
			    	
			    	/*Toast.makeText(getApplicationContext(), status, Toast.LENGTH_SHORT).show();
			    	Intent intent = new Intent(getApplicationContext(),CCAvenueStatusActivity.class);
					intent.putExtra("transStatus", status);
					startActivity(intent);*/
			    	
			    	
			    }
			}
			
			final WebView webview = (WebView) findViewById(R.id.webview);
			webview.getSettings().setJavaScriptEnabled(true);
			webview.addJavascriptInterface(new MyJavaScriptInterface(), "HTMLOUT");
			webview.setWebViewClient(new WebViewClient(){
				@Override  
	    	    public void onPageFinished(WebView view, String url) {
	    	        super.onPageFinished(webview, url);
	    	        if(url.indexOf("/ccavResponseHandler.aspx")!=-1){
	    	        	
	    	        	webview.loadUrl("javascript:window.HTMLOUT.processHTML('<head>'+document.getElementsByTagName('html')[0].innerHTML+'</head>');");
	    	        }
	    	    }  

	    	    @Override
	    	    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
	    	        Toast.makeText(getApplicationContext(), "Oh no! " + description, Toast.LENGTH_SHORT).show();
	    	    }
			});
			
			/* An instance of this class will be registered as a JavaScript interface */
			params = new StringBuffer();
			params.append(ServiceUtility.addToPostParams(AvenuesParams.ACCESS_CODE,mainIntent.getStringExtra(AvenuesParams.ACCESS_CODE)));
			params.append(ServiceUtility.addToPostParams(AvenuesParams.MERCHANT_ID,mainIntent.getStringExtra(AvenuesParams.MERCHANT_ID)));
			params.append(ServiceUtility.addToPostParams(AvenuesParams.ORDER_ID,mainIntent.getStringExtra(AvenuesParams.ORDER_ID)));
			params.append(ServiceUtility.addToPostParams(AvenuesParams.REDIRECT_URL,mainIntent.getStringExtra(AvenuesParams.REDIRECT_URL)));
			params.append(ServiceUtility.addToPostParams(AvenuesParams.CANCEL_URL,mainIntent.getStringExtra(AvenuesParams.CANCEL_URL)));
			if(encVal!=null)
			{
				params.append(ServiceUtility.addToPostParams(AvenuesParams.ENC_VAL,URLEncoder.encode(encVal)));
			}
			else
			{
				Utils.customMessage(CCAvenueWebViewAcitivty.this,"Please try after some time");
				finish();
			}

			
			String vPostParams = params.substring(0,params.length()-1);
			try {
				webview.postUrl(Constants.TRANS_URL, EncodingUtils.getBytes(vPostParams, "UTF-8"));
			} catch (Exception e) {
				showToast("Exception occured while opening webview.");
			}
		}
	}



	public void showToast(String msg) {
		Toast.makeText(this, "Toast: " + msg, Toast.LENGTH_LONG).show();
	}

	private void UpdateEmpPackStatus(String OrdNo,final String TrackNo,String Status) {
		Utils.showProgressDialog(CCAvenueWebViewAcitivty.this);
		ApiUtils.getAPIService().getEmpPackStatus(OrdNo,TrackNo,Status).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Subscriber<EmpPackStatus>() {
					@Override
					public void onCompleted() {
						Utils.hideProgressDialog();
					}

					@Override
					public void onError(Throwable e) {
						Utils.hideProgressDialog();
						Utils.customMessage(CCAvenueWebViewAcitivty.this, e.getMessage().toString());
					}

					@Override
					public void onNext(EmpPackStatus pckStatus) {

						if (pckStatus.getSuccess()) {
							if (!pckStatus.getMessage().equalsIgnoreCase("Server Error!")) {
								if (pckStatus.getMessage().equalsIgnoreCase("Updated")) {

									Intent intent_Main = new Intent(CCAvenueWebViewAcitivty.this,OnlineStatus.class);
									intent_Main.putExtra("First_Name", mFirstName);
									intent_Main.putExtra("Mobile_No", rMobile);
									intent_Main.putExtra("Email", mEmail);
									intent_Main.putExtra("Status",status);
									intent_Main.putExtra("TrackNo",TrackNo);
									intent_Main.putExtra("OMsg",Msg);
									startActivity(intent_Main);
									CCAvenueWebViewAcitivty.this.finish();
	                                 // Toast.makeText(LoginActivity.this, "Login Exist :(", Toast.LENGTH_SHORT).show();

								} else {
									//Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
									Utils.custoAlert(CCAvenueWebViewAcitivty.this, pckStatus.getMessage());

								}
							}
						}
					}
				});
	}

	
	/*//**-- Save OrderFROM SERVER
			class saveOrderServer extends AsyncTask<Void, Void,Integer>  {

				public  ProgressDialog progressDialog;
				private String resp="";
				public String menu_Name;
				private Context context;
				//SQLite Database Variable
				private MyDataBase readUserInfo;
				boolean result;
				
				
				
					
				public saveOrderServer(Context a_Context) {
					// TODO Auto-generated constructor stub
					context = a_Context;
								
					}
				
				public void runSave(Context a_Context)
				{
					a_Context=context;
					this.execute();
				}
					
				@Override
				protected Integer doInBackground(Void... params) {
					try {
						*//*readUserInfo=new MyDataBase(context);
						readUserInfo.open();
						result=readUserInfo.saveOrderServer(context, amount, productId, "Online Mobile", OrdNo, rateID, TrackNo, ahtml, "Bank", mOrdNo,status,mFirstName+ " "+mLastName,rMobile,mEmail,mCity,mState,PType);
						readUserInfo.close();	*//*
						
						
						
						try
						{
						SOAP_ACTION = namespace + "saveOrderServerMarketing";
						request = new SoapObject(namespace, "saveOrderServerMarketing");
						request.addProperty("rate",amount);
						request.addProperty("productID",productId);
						request.addProperty("payMode","Online Mobile");
						request.addProperty("ordNo",OrdNo);
						request.addProperty("rateMasterID",rateID);
						request.addProperty("trackNO",TrackNo);
						request.addProperty("response",ahtml);
						request.addProperty("mOrdNo",mOrdNo);
						request.addProperty("ordStatus",status);
						request.addProperty("oName",mFirstName+ " "+mLastName);
						request.addProperty("oMobile",rMobile);
						request.addProperty("oEmail",mEmail);
						request.addProperty("oCity",mCity);
						request.addProperty("oState",mState);
						request.addProperty("pType",PType);
						envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
						envelope.dotNet = true;
						envelope.setOutputSoapObject(request);
						androidHttpTransport = new HttpTransportSE(url,1000*60*24);
						androidHttpTransport.debug = true;
						
						try {
							//SOAP calling webservice
							androidHttpTransport.call(SOAP_ACTION, envelope);
							 Object response = (Object) envelope.getResponse();
				            String jsonData= response.toString();
				            if(jsonData.equalsIgnoreCase("Save"))
				            {	resp="Pass";
				            	result=true;
				            }
				            else
				             {
				            	 resp="Server";
				            	 result=false;
				            	 return null;
				             }
				        		 
				            
						} catch (Exception e) {
							 resp="Server";
							Log.e("Order Save  .. 1",e.getMessage());
							return null;
						
							}
						}
						catch(Exception e)
						{
							 resp="Server";
							Log.e("Order Save  .. 2",e.getMessage());
							return null;
						}
						
						
						
					} catch (Exception e) {
						 resp="Server";
						e.printStackTrace();
						resp = e.getMessage();
				}
					return null;
				}

				@Override
				protected void onPostExecute(Integer param) {
					super.onPostExecute(param);
					if(progressDialog != null)
					{
						progressDialog.dismiss();
					}
					
					if(result)
					{
						//Toast.makeText(context, "Your Order Has Been Proceed Successfully !!!", Toast.LENGTH_LONG).show();
						if(status.equalsIgnoreCase("Successful"))
						{
						new sendLicenseAsync(CCAvenueWebViewAcitivty.this).execute();
						}
						else
						{
							Intent intent_Main = new Intent(CCAvenueWebViewAcitivty.this,OnlineStatus.class);
							Bundle intent_MainBundle =ActivityOptions.makeCustomAnimation(CCAvenueWebViewAcitivty.this,R.anim.slide_in_left,R.anim.slide_out_left).toBundle();
							intent_Main.putExtra("First_Name", mFirstName);
							intent_Main.putExtra("Last_Name", mLastName);
							intent_Main.putExtra("DOB", mDOB);
							intent_Main.putExtra("Device_ID", rdeviceID);
							intent_Main.putExtra("Mobile_No", rMobile);
							intent_Main.putExtra("Email", mEmail);
							intent_Main.putExtra("City", mCity);
							intent_Main.putExtra("Pin", mPin);
							intent_Main.putExtra("State", mState);
							intent_Main.putExtra("Division", mDivision);
							intent_Main.putExtra("Branch", mBranch);
							intent_Main.putExtra("Designation", mDesignation);
							intent_Main.putExtra("License", "License");
							intent_Main.putExtra("Brand", rBrand);
							intent_Main.putExtra("Model", rModel);
							intent_Main.putExtra("Version", rVersion);
							intent_Main.putExtra("Status",status);
							intent_Main.putExtra("TrackNo",TrackNo);
							intent_Main.putExtra("OMsg",Msg);
							startActivity(intent_Main, intent_MainBundle);
							CCAvenueWebViewAcitivty.this.finish();
						}
					}
					else
					{
						Toast.makeText(context, "Some Problem on Server Please Contact to Zaidi Corporation !!!", Toast.LENGTH_LONG).show();
						return;
					}
				}

				@Override
				protected void onPreExecute() {
					super.onPreExecute();
					progressDialog=ProgressDialog.show(context,"Please Wait !!!","Checking product details on Server");
				}
			}
			
			
			
			//**-- Save OTP FROM ASYNC TASK...
			
			class sendLicenseAsync extends AsyncTask<Void, Void,Integer>  {

				public  ProgressDialog progressDialog;
				private String resp="";
				public String menu_Name;
				private Context context;
				//SQLite Database Variable
				private MyDataBase readUserInfo;
				private String readU;
					
				CustomerDetails customerDetail;
				private String resultOTP="";
				
					
				public sendLicenseAsync(Context a_Context) {
					// TODO Auto-generated constructor stub
					context=a_Context;
					}
				
				public void runSave(Context a_Context)
				{
					a_Context=context;
					this.execute();
				}
					
				@Override
				protected Integer doInBackground(Void... params) {
					try {*//*
						readUserInfo=new MyDataBase(context);
						readUserInfo.open();
						resultOTP=readUserInfo.SendLicenseThroughMailandMessage(context,mOrdNo);
						if(resultOTP!=null)
						{
							if(!resultOTP.isEmpty())
							{
								if(!mEmail.isEmpty())
								{
									GMailSender sender = new GMailSender(
											"noreply.bimagyan@gmail.com",
											"zaidi_corporation");

									//sender.addAttachment(Environment.getExternalStorageDirectory().getPath()+"/image.jpg");
									sender.sendMail("BimaGyan APP License Key","Dear "+ mFirstName +" "+ mLastName +",<br/>Thank you for purchasing BimaGyan! Your Tracking ID is: "+ TrackNo +" <br/><b><u>Your License Key details :-</u></b>"+ resultOTP.toString() +"<br/><br/><b><u>To activate your BimaGyan-Mobile, Follow the below steps:</u></b><br/> <br/>For any queries related to License OR Activation, Kindly email us at info@zaidicorp.in  Or call us at  022-28684456 <br/><br/>Thanks & Kind Regards,<br/>BimaGyan Team",
											"noreply.bimagyan@gmail.com",
											mEmail);
									
									sender.sendMail("BimaGyan APP License Key","Thank you for registering BimaGyan App, your License Key for activation is :"+resultOTP.toString()+". \n\nTracking ID is : "+ TrackNo +"\n\nWith Regards,\nBimaGyan Team",
											"noreply.bimagyan@gmail.com",
											mEmail);
								}
								sendSMS(rMobile, "Thank you for registering BimaGyan App, your License Key for activation is :"+resultOTP.toString()+". \nWith Regards,\nBimaGyan Team");
							}
						}
					*//*
					
						
						try
						{
						SOAP_ACTION = namespace + "sendingLicense";
						request = new SoapObject(namespace, "sendingLicense");
						
						request.addProperty("ordNo",mOrdNo);
						request.addProperty("TrackNo",TrackNo);
						request.addProperty("oEmail",mEmail);
						envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
						envelope.dotNet = true;
						envelope.setOutputSoapObject(request);
						androidHttpTransport = new HttpTransportSE(url,1000*60*24);
						androidHttpTransport.debug = true;
						
						try {
							//SOAP calling webservice
							androidHttpTransport.call(SOAP_ACTION, envelope);
							Object response = (Object) envelope.getResponse();
							String jsonData= response.toString();
							if(!jsonData.equalsIgnoreCase("Server"))
							{	resultOTP=jsonData;
							if(!resultOTP.isEmpty())
							{
								sendSMS(rMobile, "Thank you for registering BimaGyan App, your License Key for activation is :"+resultOTP.toString()+". \nWith Regards,\nBimaGyan Team");
							}

							}
							else
							{
								resultOTP=null;
								resp="Server";
								return null;
							}


						} catch (Exception e) {
							resultOTP=null;
							resp="Server";
							Log.e("Order Save  .. 1",e.getMessage());
							return null;

						}
						}
						catch(Exception e)
						{
							resultOTP=null;
							 resp="Server";
							Log.e("Order Save  .. 2",e.getMessage());
							return null;
						}
					
					
					
					
					} catch (Exception e) {
						resultOTP=null;
						e.printStackTrace();
						resp = e.getMessage();
					}
					return null;
				}

				@Override
				protected void onPostExecute(Integer param) {
					super.onPostExecute(param);
					if(progressDialog != null)
					{
						progressDialog.dismiss();
					}
					if(resultOTP!=null)
					{
						if (resultOTP.isEmpty())
						{
							Toast.makeText(context, "Some Problem In Server !!!", Toast.LENGTH_SHORT).show();
						}
						if (!resultOTP.isEmpty())
						{
						Intent intent_Main = new Intent(CCAvenueWebViewAcitivty.this,OnlineStatus.class);
						Bundle intent_MainBundle =ActivityOptions.makeCustomAnimation(CCAvenueWebViewAcitivty.this,R.anim.slide_in_left,R.anim.slide_out_left).toBundle();
						intent_Main.putExtra("First_Name", mFirstName);
						intent_Main.putExtra("Last_Name", mLastName);
						intent_Main.putExtra("DOB", mDOB);
						intent_Main.putExtra("Device_ID", rdeviceID);
						intent_Main.putExtra("Mobile_No", rMobile);
						intent_Main.putExtra("Email", mEmail);
						intent_Main.putExtra("City", mCity);
						intent_Main.putExtra("Pin", mPin);
						intent_Main.putExtra("State", mState);
						intent_Main.putExtra("Division", mDivision);
						intent_Main.putExtra("Branch", mBranch);
						intent_Main.putExtra("Designation", mDesignation);
						intent_Main.putExtra("License", "License");
						intent_Main.putExtra("Brand", rBrand);
						intent_Main.putExtra("Model", rModel);
						intent_Main.putExtra("Version", rVersion);
						intent_Main.putExtra("Status",status);
						intent_Main.putExtra("TrackNo",TrackNo);
						intent_Main.putExtra("OMsg",Msg);
						intent_Main.putExtra("Keys",resultOTP);
						startActivity(intent_Main, intent_MainBundle);
						CCAvenueWebViewAcitivty.this.finish();
						}

					}
					else
						{
						Toast.makeText(context, "Some Problem In Server !!!", Toast.LENGTH_SHORT).show();
					}
					
				}

				@Override
				protected void onPreExecute() {
					super.onPreExecute();
					progressDialog=ProgressDialog.show(context,"Please Wait !!!","Sending License Key on your Registered Mobile No and Email ID");
				}
			}

			
			public void sendSMS(String aMobile,String a_MSg)
			{
				//Your authentication key
				String authkey = "86539AcafJ6pvZyu5597885b";
				
				//Multiple mobiles numbers separated by comma
				String mobiles = aMobile;
				
				//Sender ID,While using route4 sender id should be 6 characters long.
				String senderId = "Bimagy";
				//Your message to send, Add URL encoding here.
				String message = a_MSg;
				//define route
				String route="4";

				URLConnection myURLConnection=null;
				URL myURL=null;
				BufferedReader reader=null;

				//encoding message 
				String encoded_message=URLEncoder.encode(message);

				//Send SMS API
				//String mainUrl="http://msg91.com/sendhttp.php?";
				String mainUrl="https://control.msg91.com/api/sendhttp.php?";
				//Prepare parameter string 
				StringBuilder sbPostData= new StringBuilder(mainUrl);
				sbPostData.append("authkey="+authkey); 
				sbPostData.append("&mobiles="+mobiles);
				sbPostData.append("&message="+encoded_message);
				sbPostData.append("&route="+route);
				sbPostData.append("&sender="+senderId);

				//final string
				mainUrl = sbPostData.toString();
				try
				{
				    //prepare connection
				    myURL = new URL(mainUrl);
				    myURLConnection = myURL.openConnection();
				    myURLConnection.connect();
				    reader= new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
				    
				    //reading response 
				    String response;
				    while ((response = reader.readLine()) != null) 
				    //print response 
				   // Log.d("RESPONSE", ""+response);
				    
				    //finally close connection
				    reader.close();
				} 
				catch (IOException e) 
				{ 
					e.printStackTrace();
				} 
			}*/

}
