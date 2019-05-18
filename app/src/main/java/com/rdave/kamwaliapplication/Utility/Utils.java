package com.rdave.kamwaliapplication.Utility;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.Window;
import android.widget.Toast;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by RDave on 15/09/2017.
 */

public class Utils {
    private static ProgressDialog progressDialog;
    //Payment Gateway Variable
    public static final String avenue_AccessCode="AVXY85GE34BC50YXCB";
    public static final String avenue_MerchantId="142306";
    public static final String avenue_WorkingKey="567F3D3912330A5E131F839BB1A5DD79";
    public static final String avenue_Currency="INR";
    public static final String avenue_RedirectUrl="http://payment.kaamwalijobs.com/ccavResponseHandler.aspx";
    public static final String avenue_CancelUrl="http://payment.kaamwalijobs.com/ccavResponseHandler.aspx";
    public static final String avenue_RSAKeyUrl="http://payment.kaamwalijobs.com/GetRSA.aspx";
    public static final String avenue_Amount="2700.00";
//    public static final String avenue_AccessCode="*******";
//    public static final String avenue_MerchantId="****";
//    public static final String avenue_WorkingKey="************";
//    public static final String avenue_Currency="INR";
//    public static final String avenue_RedirectUrl="http://payment.bimagyan.in/Apps/ccavResponseHandler.aspx";
//    public static final String avenue_CancelUrl="http://payment.bimagyan.in/Apps/ccav ResponseHandler.aspx";
//    public static final String avenue_RSAKeyUrl="http://payment.bimagyan.in/Apps/GetRSA.aspx";
//    public static final String avenue_Amount="2700.00";

    public static Toast customMessage(Context ctx, String message) {

        Toast toast = Toast.makeText(ctx, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        return toast;

    }

    public static AlertDialog custoAlert(final Context ctx, String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setMessage(message);
        builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            dialog.cancel();
                //((AppCompatActivity)ctx).finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.setCancelable(false);
        return dialog;
    }




    public static Integer checkInteger(String aValue)
    {
        int amt=0;
        if(aValue.length()>0)
        {
            amt = Integer.parseInt(aValue);
        }
        else
        {
            amt=0;
        }
        return amt;
    }

    public static double checkDouble(String aValue)
    {
        double amt=0;
        if(aValue.length()>0)
        {
            amt = Double.parseDouble(aValue);
        }
        else
        {
            amt=0;
        }
        return amt;
    }



    public static boolean isBlank(String aValue)
    {
        boolean isBlank=false;
        if(aValue.length()>0)
        {
            isBlank=true;
        }
        else
        {
            isBlank=false;
        }
        return isBlank;
    }





    public static boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    public static String getMacAddress(Context mContext)
    {
        WifiManager wifiManager = (WifiManager)mContext.getSystemService(mContext.WIFI_SERVICE);
        WifiInfo wInfo = wifiManager.getConnectionInfo();
        String macAddress = wInfo.getMacAddress();
        return macAddress;
    }

    public static String getMacAddr() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(String.format("%02X:",b));
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {
        }
        return "02:00:00:00:00:00";
    }

    public static String checkMacAddress(Context mContext)
    {
        String MacAddress="";
        WifiManager wifiManager = (WifiManager) mContext.getSystemService(mContext.WIFI_SERVICE);
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >= android.os.Build.VERSION_CODES.M) {
            MacAddress=	Utils.getMacAddr();
        }
        else
        {
            MacAddress=	Utils.getMacAddress(mContext);
        }
        if(MacAddress==null) {
            wifiManager.setWifiEnabled(true);
            if (currentapiVersion >= android.os.Build.VERSION_CODES.M) {
                MacAddress=	Utils.getMacAddr();
            }
            else
            {
                MacAddress=	Utils.getMacAddress(mContext);
            }

        }
        return MacAddress;
    }



    public static void showProgressDialog(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progressDialog.setMessage("Please wait...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public static void hideProgressDialog() {
        if(progressDialog!=null)
        {
            progressDialog.dismiss();
        }
        }


    public static void showProgressDialogwithMessage(Context context, String aMessage) {
        progressDialog = new ProgressDialog(context);
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progressDialog.setMessage(aMessage);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }






}
