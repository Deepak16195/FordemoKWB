package com.rdave.kamwaliapplication.Activity;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.rdave.kamwaliapplication.Adapter.CityAdapter;
import com.rdave.kamwaliapplication.Adapter.PincodeAdapter;
import com.rdave.kamwaliapplication.Adapter.StateAdapter;
import com.rdave.kamwaliapplication.Model.AgentLogin;
import com.rdave.kamwaliapplication.Model.EmployerLogin;
import com.rdave.kamwaliapplication.Model.GetCity;
import com.rdave.kamwaliapplication.Model.GetPincode;
import com.rdave.kamwaliapplication.Model.GetState;
import com.rdave.kamwaliapplication.Model.UpdateAgent;
import com.rdave.kamwaliapplication.Model.UpdateEmployer;
import com.rdave.kamwaliapplication.R;
import com.rdave.kamwaliapplication.Utility.SharePrefarence;
import com.rdave.kamwaliapplication.Utility.Utils;
import com.rdave.kamwaliapplication.remote.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UpdateProfileActivity extends AppCompatActivity {

    Button btnUpdate;
    TextInputEditText Nameid, Mobileid, Emailid, Addressid,
             CompanyNameid, GSTNoid;

    String Logined = "0";
    String LoginedAs = "0";
    String eName, eMobile, eEmail, eAddress, eState, eCity, ePinCode, eCompanyName, eGSTNo;
    AutoCompleteTextView Stateid;
    AutoCompleteTextView Cityid;
    AutoCompleteTextView PinCodeid;
    List<GetState.DataEntity> gtstate = new ArrayList<>();
    List<GetCity.DataEntity> gtcity = new ArrayList<>();
    List<GetPincode.DataEntity> gtpincode = new ArrayList<>();
    StateAdapter adapter;
    CityAdapter adapter2;
    PincodeAdapter adapter3;
    String MobileNo="",Password="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u_profile);

        btnUpdate = (Button) findViewById(R.id.update);
        Nameid = (TextInputEditText) findViewById(R.id.etName);
        Mobileid = (TextInputEditText) findViewById(R.id.etMobile);
        Emailid = (TextInputEditText) findViewById(R.id.etEmail);
        Addressid = (TextInputEditText) findViewById(R.id.etAdress);

        PinCodeid = (AutoCompleteTextView) findViewById(R.id.etPincode);
        CompanyNameid = (TextInputEditText) findViewById(R.id.etCompanyname);
        GSTNoid = (TextInputEditText) findViewById(R.id.etGstnum);
        LoginedAs = SharePrefarence.getmInstance(UpdateProfileActivity.this).getLoginAs();
        Logined = SharePrefarence.getmInstance(UpdateProfileActivity.this).getLogined();
        MobileNo = SharePrefarence.getmInstance(UpdateProfileActivity.this).getMobile();
        Password = SharePrefarence.getmInstance(UpdateProfileActivity.this).getPassword();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitle("Profile");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        Cityid = (AutoCompleteTextView) findViewById(R.id.etCity);
//        Cityid.setAdapter(adapter2);
//        GetCity("0");
        Stateid = (AutoCompleteTextView) findViewById(R.id.etState);
        // Stateid.setAdapter(adapter);
        if (LoginedAs.equalsIgnoreCase("Agent")) {
            loadAgentProfile(MobileNo,Password);
        }
        else if (LoginedAs.equalsIgnoreCase("Employer")) {
            loadEmployerProfile(MobileNo,Password);
        }
        else{
            Utils.customMessage(UpdateProfileActivity.this,"Your Account Details Not Found In Our Records");
        }


        Stateid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos,
                                    long id) {
                String Data = gtstate.get(pos).getState();
                GetCity(Data);

            }
        });
        Cityid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                String Data = gtstate.get(pos).getState();
                String cData = gtcity.get(pos).getCity();
                GetPincode(Data,cData);

            }
        });


        btnUpdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                eName = Nameid.getText().toString();
                eMobile = Mobileid.getText().toString();
                eEmail = Emailid.getText().toString();
                eAddress = Addressid.getText().toString();
//                eState = Stateid.getText().toString();
//                eCity = Cityid.getText().toString();
                ePinCode = PinCodeid.getText().toString();
                eCompanyName = PinCodeid.getText().toString();
                eGSTNo = GSTNoid.getText().toString();

                if (LoginedAs.equalsIgnoreCase("Employer")) {

                    EmployerProfile("0", eName, eMobile, eEmail, eAddress, eState, eCity, ePinCode, eCompanyName, eGSTNo);

                }
                if (LoginedAs.equalsIgnoreCase("Agent")) {

                    AgentProfile("0", eName, eMobile, eEmail, eAddress, eState, eCity, ePinCode, eCompanyName, eGSTNo);

                }
            }
        });

    }

    private void loadEmployerProfile(String aMobile,String aPassword)
    {
        Utils.showProgressDialog(UpdateProfileActivity.this);
        ApiUtils.getAPIService().getEmployerProfile(aMobile,aPassword).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<EmployerLogin>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(UpdateProfileActivity.this, e.getMessage().toString());
                    }

                    @Override
                    public void onNext(EmployerLogin eProfile) {

                        if (eProfile.getSuccess()) {
                            if (!eProfile.getMessage().equalsIgnoreCase("Server Error!")) {
                                if (eProfile.getMessage().equalsIgnoreCase("Success")) {
                                    Nameid.setText(eProfile.getData().get(0).getEmployerName());
                                    Mobileid.setText(eProfile.getData().get(0).getMobileNo());
                                    Emailid.setText(eProfile.getData().get(0).getEmailId());
                                    Addressid.setText(eProfile.getData().get(0).getAddress());
                                    GSTNoid.setText(eProfile.getData().get(0).getGSTNo());
                                    CompanyNameid.setText(eProfile.getData().get(0).getCompanyName());
                                    Stateid.setText(eProfile.getData().get(0).getState());
                                    Cityid.setText(eProfile.getData().get(0).getCity());
                                    PinCodeid.setText(eProfile.getData().get(0).getPinCode());

                                } else {
                                    // Toast.makeText(SignUp.this, "User Alredy Exist :(", Toast.LENGTH_SHORT).show();
                                    Utils.custoAlert(UpdateProfileActivity.this, eProfile.getMessage());
                                }
                            }
                        }
                    }
                });
    }


    private void loadAgentProfile(String aMobile,String aPassword)
    {
        Utils.showProgressDialog(UpdateProfileActivity.this);
        ApiUtils.getAPIService().getAgentProfile(aMobile,aPassword).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AgentLogin>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();
                        GetState();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(UpdateProfileActivity.this, e.getMessage().toString());
                    }

                    @Override
                    public void onNext(AgentLogin eProfile) {

                        if (eProfile.getSuccess()) {
                            if (!eProfile.getMessage().equalsIgnoreCase("Server Error!")) {
                                if (eProfile.getMessage().equalsIgnoreCase("Success")) {
                                    Nameid.setText(eProfile.getData().get(0).getAgentName());
                                    Mobileid.setText(eProfile.getData().get(0).getMobileNo());
                                    Emailid.setText(eProfile.getData().get(0).getEmailId());
                                    Addressid.setText(eProfile.getData().get(0).getAddress());
                                    GSTNoid.setText(eProfile.getData().get(0).getGSTNo());
                                    CompanyNameid.setText(eProfile.getData().get(0).getCompanyName());
                                    Stateid.setText(eProfile.getData().get(0).getState());
                                    Cityid.setText(eProfile.getData().get(0).getCity());
                                    PinCodeid.setText(eProfile.getData().get(0).getPinCode());

                                } else {
                                    // Toast.makeText(SignUp.this, "User Alredy Exist :(", Toast.LENGTH_SHORT).show();
                                    Utils.custoAlert(UpdateProfileActivity.this, eProfile.getMessage());
                                }
                            }
                        }
                    }
                });
    }

    private void EmployerProfile(String employerid, String Name, String Mobile, String Email, String Address, String State, String
            City, String PinCode, String CompanyName, String GSTNo) {
        Utils.showProgressDialog(UpdateProfileActivity.this);
        ApiUtils.getAPIService().UEmployer(employerid, Name, Mobile, Email, Address, State, City, PinCode, CompanyName, GSTNo).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UpdateEmployer>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();
                        GetState();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(UpdateProfileActivity.this, e.getMessage().toString());
                    }

                    @Override
                    public void onNext(UpdateEmployer eProfile) {

                        if (eProfile.getSuccess()) {
                            if (!eProfile.getMessage().equalsIgnoreCase("Server Error!")) {
                                if (eProfile.getMessage().equalsIgnoreCase("Update")) {

                                    Utils.customMessage(UpdateProfileActivity.this,"Profile Update Successfully");
                                    finish();
//                                    SharePrefarence.getmInstance(UpdateProfileActivity.this).SeTLogined("1");

                                    // Toast.makeText(SignUp.this, "User Alredy Exist :(", Toast.LENGTH_SHORT).show();;

                                } else {
                                    // Toast.makeText(SignUp.this, "User Alredy Exist :(", Toast.LENGTH_SHORT).show();
                                    Utils.custoAlert(UpdateProfileActivity.this, eProfile.getMessage());
                                }
                            }
                        }
                    }
                });
    }

    private void AgentProfile(String agentid, String Name, String Mobile, String
            Email, String Address, String State, String
                                      City, String PinCode, String CompanyName, String GSTNo) {
        Utils.showProgressDialog(UpdateProfileActivity.this);
        ApiUtils.getAPIService().UAgent(agentid, Name, Mobile, Email, Address, State, City, PinCode, CompanyName, GSTNo).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UpdateAgent>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(UpdateProfileActivity.this, e.getMessage().toString());
                    }

                    @Override
                    public void onNext(UpdateAgent aProfile) {

                        if (aProfile.getSuccess()) {
                            if (!aProfile.getMessage().equalsIgnoreCase("Server Error!")) {
                                if (aProfile.getMessage().equalsIgnoreCase("Update")) {
                                    Utils.customMessage(UpdateProfileActivity.this,"Profile Update Successfully");
                                    finish();
//                                    SharePrefarence.getmInstance(UpdateProfileActivity.this).SeTLogined("1");

                                    // Toast.makeText(SignUp.this, "User Alredy Exist :(", Toast.LENGTH_SHORT).show();;

                                } else {
                                    // Toast.makeText(SignUp.this, "User Alredy Exist :(", Toast.LENGTH_SHORT).show();
                                    Utils.custoAlert(UpdateProfileActivity.this, aProfile.getMessage());
                                }
                            }
                        }
                    }
                });
    }

    private void GetState() {
        Utils.showProgressDialog(UpdateProfileActivity.this);
        ApiUtils.getAPIService().getState().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetState>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();

                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(UpdateProfileActivity.this, e.getMessage().toString());
                    }

                    @Override
                    public void onNext(GetState currencyModel) {
                        if (currencyModel.getSuccess()) {
                            if (!currencyModel.getMessage().equalsIgnoreCase("Server Error!")) {
                                if (currencyModel.getMessage().equalsIgnoreCase("Success")) {
                                    gtstate = currencyModel.getData();
                                    adapter = new StateAdapter(UpdateProfileActivity.this, gtstate);
                                    Stateid.setAdapter(adapter);

                                } else {
                                    //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                                    Utils.custoAlert(UpdateProfileActivity.this, currencyModel.getMessage());
                                }
                            }
                        }
                    }
                });

    }

    private void GetCity(String State) {
        Utils.showProgressDialog(UpdateProfileActivity.this);
        ApiUtils.getAPIService().getCity(State).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetCity>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();

                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(UpdateProfileActivity.this, e.getMessage().toString());
                    }

                    @Override
                    public void onNext(GetCity gCity) {
                        if (gCity.getSuccess()) {
                            if (!gCity.getMessage().equalsIgnoreCase("Server Error!")) {
                                if (gCity.getMessage().equalsIgnoreCase("Success")) {
                                    gtcity = gCity.getData();
                                    adapter2 = new CityAdapter(UpdateProfileActivity.this, gtcity);
                                    Cityid.setAdapter(adapter2);

                                } else {
                                    //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                                    Utils.custoAlert(UpdateProfileActivity.this, gCity.getMessage());
                                }
                            }
                        }
                    }
                });

    }
    private void GetPincode(String State,String City ) {
        Utils.showProgressDialog(UpdateProfileActivity.this);
        ApiUtils.getAPIService().getPincpode(State,City).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetPincode>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();

                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(UpdateProfileActivity.this, e.getMessage().toString());
                    }

                    @Override
                    public void onNext(GetPincode gPincode) {
                        if (gPincode.getSuccess()) {
                            if (!gPincode.getMessage().equalsIgnoreCase("Server Error!")) {
                                if (gPincode.getMessage().equalsIgnoreCase("Success")) {
                                    gtpincode = gPincode.getData();
                                    adapter3 = new PincodeAdapter(UpdateProfileActivity.this, gtpincode);
                                    PinCodeid.setAdapter(adapter3);

                                } else {
                                    //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                                    Utils.custoAlert(UpdateProfileActivity.this, gPincode.getMessage());
                                }
                            }
                        }
                    }
                });

    }



//    private boolean checkValidation() {
//        if (isEmpty(Nameid)) {
//            Nameid.requestFocus();
//            Nameid.setError("Name Can't be Empty!");
//            return false;
//        }
//        if (isEmpty(Mobileid)) {
//            Mobileid.requestFocus();
//            Mobileid.setError("Num Can't be Empty!");
//            return false;
//        }
//        if (isEmpty(Emailid)) {
//            Emailid.requestFocus();
//            Emailid.setError("Email Adress cant be Empty");
//            return false;
//        }
//        if (isEmpty(Addressid)) {
//            Addressid.requestFocus();
//            Addressid.setError("Password cant be Empty");
//            return false;
//        }
//        if (isEmpty(rgPass)) {
//            rgPass.requestFocus();
//            rgPass.setError("Enter Password");
//            return false;
//        }
//        if (isEmpty(rgRepass)) {
//            rgRepass.requestFocus();
//            rgRepass.setError("Retype Password");
//            return false;
//        }
//        return true;
//    }
//
//    private boolean isEmail(String email) {
//
//        boolean check = false;
//        final String Email_PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
//        CharSequence inputStr = email;
//
//        Pattern pattern = Pattern.compile(Email_PATTERN, Pattern.CASE_INSENSITIVE);
//        Matcher matcher = pattern.matcher(inputStr);
//
//        if (matcher.matches()) {
//            return true;
//        } else {
//            rgEmail.setError("Enter Valid Email Adress");
//            return false;
//        }
//
//
//    }
//
//    public boolean isEmpty(EditText etText) {
//        return etText.getText().toString().trim().length() <= 0;
//    }
//
//
//    private boolean isValidMobile(String phone) {
//        boolean check = false;
//        if (!Pattern.matches("[a-zA-Z]+", phone)) {
//            if (phone.length() < 10 || phone.length() > 10) {
//                // if(phone.length() != 10) {
//                check = false;
//                rgNum.setError("Not Valid Number");
//            } else {
//                check = true;
//            }
//        } else {
//            check = false;
//        }
//        return check;
//    }
//
//    public boolean isValidPassword(final String password) {
//
////        Pattern pattern;
////        Matcher matcher;
//        boolean pcheck = false;
//
//        if (password != null && password.length() >= 5) {
//            pcheck = true;
//
//        } else {
//
//            rgPass.setError("Password length should be above 5");
//            rgRepass.setError("Password not matched");
//
//
//        }
//
//
////        pattern = Pattern.compile(PASSWORD_PATTERN);
////        matcher = pattern.matcher(password);
//
//
//        return pcheck;
//
//
//
//


}
