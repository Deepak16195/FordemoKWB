package com.rdave.kamwaliapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.rdave.kamwaliapplication.Model.AgentLogin;
import com.rdave.kamwaliapplication.Model.CandidateLogin;
import com.rdave.kamwaliapplication.Model.EmployerLogin;
import com.rdave.kamwaliapplication.Model.MatchOtp;
import com.rdave.kamwaliapplication.Model.SOtp;
import com.rdave.kamwaliapplication.R;
import com.rdave.kamwaliapplication.Utility.Utils;
import com.rdave.kamwaliapplication.remote.ApiUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SignUp extends AppCompatActivity {

    protected TextInputEditText logEmailText;
    protected TextInputEditText logPassText;
    protected TextView forgetPass;
    protected Button loginBtn;
    protected TextView signin;
    protected Toolbar toolbar;
    protected TextInputEditText rgName, rgNum, rgEmail, rgPass, rgRepass, rgAgntId, rgApplication;
    String str1;
    Button btnSubmit;
    String otp;
    String UName;
    String UNumber;
    String UEmail;
    String UPassword;
    AlertDialog.Builder builder;
    AlertDialog dialog;
    PinView pinView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_signup);

        initView();

        if(getIntent().getStringExtra("V1")!=null)
        {
            str1=getIntent().getStringExtra("V1");
            toolbar.setTitle("Sign Up as " + getIntent().getStringExtra("V1").toString());
        }


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                 UName     = rgName.getText().toString();
                 UNumber   = rgNum.getText().toString();
                 UEmail     = rgEmail.getText().toString();
                 UPassword = rgPass.getText().toString();
                if (checkValidation() && isValidMobile(rgNum.getText().toString()) && isEmail(rgEmail.getText().toString()) && isValidPassword(rgPass.getText().toString())) {
                    if (rgRepass.getText().toString().equalsIgnoreCase(rgPass.getText().toString())) {
                        if (str1.equalsIgnoreCase("Candidate")) {
                                SaveOtp(UNumber, "C");
                        }
                        if (str1.equalsIgnoreCase("Employer")) {
                            SaveOtp(UNumber, "E");
                        }
                        if (str1.equalsIgnoreCase("Agent")) {
                            SaveOtp(UNumber, "A");
                        }

                    } else {
                        rgRepass.setError("Password Not Matched");
                    }

                }


            }
        });


    }

    private void initView() {
        rgName = (TextInputEditText) findViewById(R.id.txt_Name);
        rgNum = (TextInputEditText) findViewById(R.id.txt_Mobile);
        rgEmail = (TextInputEditText) findViewById(R.id.log_email_text);
        rgPass = (TextInputEditText) findViewById(R.id.pass_text);
        rgRepass = (TextInputEditText) findViewById(R.id.txt_Repass);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("Sign Up");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                Intent bck = new Intent(SignUp.this, SignupAsActivity.class);
                startActivity(bck);
            }
        });
        btnSubmit = (Button) findViewById(R.id.btotp);
    }

    private void SaveCandigate(String Name, String Mobile, String Email, String Password, String AgentId, String
        Application) {
        Utils.showProgressDialog(SignUp.this);
        ApiUtils.getAPIService().SCandigate(Name, Mobile, Email, Password, AgentId, Application).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CandidateLogin>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(SignUp.this, e.getMessage().toString());
                    }

                    @Override
                    public void onNext(CandidateLogin cData) {

                        if (cData.getSuccess()) {
                            if (!cData.getMessage().equalsIgnoreCase("Server Error!")) {
                                if (cData.getMessage().equalsIgnoreCase("Save")) {
                                  /*  SharePrefarence.getmInstance(SignUp.this).SeTLoginAs(UName);
                                    SharePrefarence.getmInstance(SignUp.this).SeTLoginAs(UEmail);
                                    SharePrefarence.getmInstance(SignUp.this).SeTLogined("1");*/

                            /*        SharePrefarence.getmInstance(SignUp.this).SeTLogined("1");
                                    SharePrefarence.getmInstance(SignUp.this).SeTLoginAs("Candidate");
                                    SharePrefarence.getmInstance(SignUp.this).SeTUserName(cData.getData().get(0).getFullName());
                                    SharePrefarence.getmInstance(SignUp.this).SeTEmailid(cData.getData().get(0).getEmailId());
                                    SharePrefarence.getmInstance(SignUp.this).setUser_ID(String.valueOf(cData.getData().get(0).getCandidateId()));
                                    SharePrefarence.getmInstance(SignUp.this).setMobileNo(cData.getData().get(0).getMobileNo());
                                    SharePrefarence.getmInstance(SignUp.this).setPassword(cData.getData().get(0).getPassword());
*/
                                    Intent mIntent = new Intent(SignUp.this, LoginActivity.class);
                                    startActivity(mIntent);
                                    finish();
                                    // Toast.makeText(SignUp.this, "User Alredy Exist :(", Toast.LENGTH_SHORT).show();;

                                } else {
                                   // Toast.makeText(SignUp.this, "User Alredy Exist :(", Toast.LENGTH_SHORT).show();
                                    Utils.custoAlert(SignUp.this, cData.getMessage());
                                }
                            }
                        }
                    }
                });
    }

    private void SaveEmployer(String Name, String Mobile, String Email, String Password, String
        Application) {
        Utils.showProgressDialog(SignUp.this);
        ApiUtils.getAPIService().SEmployer(Name, Mobile, Email, Password, Application).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<EmployerLogin>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(SignUp.this, e.getMessage().toString());
                    }

                    @Override
                    public void onNext(EmployerLogin saveData) {
                        if (saveData.getSuccess()) {
                            if (!saveData.getMessage().equalsIgnoreCase("Server Error!")) {
                                if (saveData.getMessage().equalsIgnoreCase("Save")) {
                                   /* SharePrefarence.getmInstance(SignUp.this).SeTLoginAs(UName);
                                    SharePrefarence.getmInstance(SignUp.this).SeTLoginAs(UEmail);
                                    SharePrefarence.getmInstance(SignUp.this).SeTLogined("1");*/


                             /*       SharePrefarence.getmInstance(SignUp.this).SeTLogined("1");
                                    SharePrefarence.getmInstance(SignUp.this).SeTLoginAs("Candidate");
                                    SharePrefarence.getmInstance(SignUp.this).SeTUserName(saveData.getData().get(0).getEmployerName());
                                    SharePrefarence.getmInstance(SignUp.this).SeTEmailid(saveData.getData().get(0).getEmailId());
                                    SharePrefarence.getmInstance(SignUp.this).setUser_ID(String.valueOf(saveData.getData().get(0).getEmployerId()));
                                    SharePrefarence.getmInstance(SignUp.this).setMobileNo(saveData.getData().get(0).getMobileNo());
                                    SharePrefarence.getmInstance(SignUp.this).setPassword(saveData.getData().get(0).getPassword());*/

                                    Intent mIntent = new Intent(SignUp.this, LoginActivity.class);
                                    startActivity(mIntent);
                                    finish();
                                    // Toast.makeText(LoginActivity.this, "Login Exist :(", Toast.LENGTH_SHORT).show();

                                } else {
                                    //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                                    Utils.custoAlert(SignUp.this, saveData.getMessage());
                                }
                            }
                        }
                    }
                });
    }

    private void SaveAgent(String Name, String Mobile, String Email, String Password, String
        Application) {
        Utils.showProgressDialog(SignUp.this);
        ApiUtils.getAPIService().SAgent(Name, Mobile, Email, Password, Application).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AgentLogin>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(SignUp.this, e.getMessage().toString());
                    }

                    @Override
                    public void onNext(AgentLogin saveData) {

                        if (saveData.getSuccess()) {
                            if (!saveData.getMessage().equalsIgnoreCase("Server Error!")) {
                                if (saveData.getMessage().equalsIgnoreCase("Save")) {
                                /*    SharePrefarence.getmInstance(SignUp.this).SeTLoginAs(UName);
                                    SharePrefarence.getmInstance(SignUp.this).SeTLoginAs(UEmail);
                                    SharePrefarence.getmInstance(SignUp.this).SeTLogined("1");*/

                                  /*  SharePrefarence.getmInstance(SignUp.this).SeTLogined("1");
                                    SharePrefarence.getmInstance(SignUp.this).SeTLoginAs("Candidate");
                                    SharePrefarence.getmInstance(SignUp.this).SeTUserName(saveData.getData().get(0).getAgentName());
                                    SharePrefarence.getmInstance(SignUp.this).SeTEmailid(saveData.getData().get(0).getEmailId());
                                    SharePrefarence.getmInstance(SignUp.this).setUser_ID(String.valueOf(saveData.getData().get(0).getAgentId()));
                                    SharePrefarence.getmInstance(SignUp.this).setMobileNo(saveData.getData().get(0).getMobileNo());
                                    SharePrefarence.getmInstance(SignUp.this).setPassword(saveData.getData().get(0).getPassword());*/

                                    Intent mIntent = new Intent(SignUp.this, LoginActivity.class);
                                    startActivity(mIntent);
                                    finish();
                                    // Toast.makeText(LoginActivity.this, "Login Exist :(", Toast.LENGTH_SHORT).show();

                                } else {
                                    //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                                    Utils.custoAlert(SignUp.this, saveData.getMessage());
                                }
                            }
                        }
                    }
                });
    }

    private void SaveOtp( String Mobile, String type) {
        Utils.showProgressDialog(SignUp.this);
        ApiUtils.getAPIService().SendOtp( Mobile, type ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SOtp>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(SignUp.this,e.getMessage());
                    }

                    @Override
                    public void onNext(SOtp sOtp) {
                        Utils.hideProgressDialog();
                        if (sOtp.getMessage().equalsIgnoreCase("Save")) {
                            open_dialog();
                        }
                        else
                        {
                            Toast.makeText(SignUp.this, "This number is already exist", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
    private void MatchgOtp( String mobile, String type,String otpnumber) {
        Utils.showProgressDialog(SignUp.this);
        ApiUtils.getAPIService().GetOtp(type,mobile,otpnumber).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MatchOtp>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(SignUp.this, e.getMessage().toString());
                    }

                    @Override
                    public void onNext(MatchOtp matchOtp) {
                        Utils.hideProgressDialog();
                        if (matchOtp.getMessage().equalsIgnoreCase("Exit")) {
                            dialog.dismiss();
                            if (str1.equalsIgnoreCase("Candidate")) {
                                SaveCandigate(UName, UNumber, UEmail, UPassword, "0", "android");
                            }
                            if (str1.equalsIgnoreCase("Employer")) {
                                SaveEmployer(UName, UNumber, UEmail, UPassword, "android");
                            }
                            if (str1.equalsIgnoreCase("Agent")) {
                                SaveAgent(UName, UNumber, UEmail, UPassword, "android");
                            }
                        }else
                        {
                            Toast.makeText(SignUp.this, "Otp Not Matched try Again", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }


    private boolean checkValidation() {
        if (isEmpty(rgName)) {
            rgName.requestFocus();
            rgName.setError("Name Can't be Empty!");
            return false;
        }
        if (isEmpty(rgNum)) {
            rgNum.requestFocus();
            rgNum.setError("Num Can't be Empty!");
            return false;
        }
//        if (isEmpty(rgEmail)) {
//            rgEmail.requestFocus();
//            rgEmail.setError("Email Adress cant be Empty");
//            return false;
//        }
        if (isEmpty(rgPass)) {
            rgPass.requestFocus();
            rgPass.setError("Password cant be Empty");
            return false;
        }
        if (isEmpty(rgPass)) {
            rgPass.requestFocus();
            rgPass.setError("Enter Password");
            return false;
        }
        if (isEmpty(rgRepass)) {
            rgRepass.requestFocus();
            rgRepass.setError("Retype Password");
            return false;
        }
        return true;
    }

    private boolean isEmail(String email) {

        boolean check = false;
        final String Email_PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(Email_PATTERN, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if (matcher.matches() || rgEmail.length() <= 0) {
            return true;
        } else {
            rgEmail.setError("Enter Valid Email Adress");
            return false;
        }


    }

    public boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() <= 0;
    }


    private boolean isValidMobile(String phone) {
        boolean check = false;
        if (!Pattern.matches("[a-zA-Z]+", phone)) {
            if (phone.length() < 10 || phone.length() > 10) {
                // if(phone.length() != 10) {
                check = false;
                rgNum.setError("Not Valid Number");
            } else {
                check = true;
            }
        } else {
            check = false;
        }
        return check;
    }

    public boolean isValidPassword(final String password) {

//        Pattern pattern;
//        Matcher matcher;
        boolean pcheck = false;

        if (password != null && password.length() >= 5) {
            pcheck = true;

        } else {

            rgPass.setError("Password length should be above 5");
            rgRepass.setError("Password not matched");


        }


//        pattern = Pattern.compile(PASSWORD_PATTERN);
//        matcher = pattern.matcher(password);


        return pcheck;

    }


    public void open_dialog() {
        builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(getBaseContext().LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.otp_layout, null);
        pinView = (PinView) row.findViewById(R.id.et_otpNumber);
        Button donebtn = (Button) row.findViewById(R.id.idDone);
        donebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otp = pinView.getText().toString();
                String UNumber = rgNum.getText().toString();
                if (str1.equalsIgnoreCase("Candidate")) {
                    MatchgOtp(UNumber, "C",otp);
                }
                if (str1.equalsIgnoreCase("Employer")) {
                    MatchgOtp(UNumber, "E", otp);
                }
                if (str1.equalsIgnoreCase("Agent")) {
                    MatchgOtp(UNumber, "A",otp);
                }

            }
        });


        builder.setView(row);
         dialog = builder.create();
        dialog.show();
    }

}
