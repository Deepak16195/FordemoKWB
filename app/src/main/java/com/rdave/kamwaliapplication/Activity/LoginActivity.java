package com.rdave.kamwaliapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.rdave.kamwaliapplication.Model.AgentLogin;
import com.rdave.kamwaliapplication.Model.CandidateLogin;
import com.rdave.kamwaliapplication.Model.EmployerLogin;
import com.rdave.kamwaliapplication.R;
import com.rdave.kamwaliapplication.Utility.SharePrefarence;
import com.rdave.kamwaliapplication.Utility.Utils;
import com.rdave.kamwaliapplication.remote.ApiUtils;

import java.util.regex.Pattern;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {

    protected TextInputEditText lognumText;
    protected TextInputEditText logPassText;
    protected TextView forgetPass;
    protected Button loginBtn;
    protected TextView signin, viewSignup;
    Spinner sp;
    int Pos;

    String Posname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_login);


        sp = (Spinner) findViewById(R.id.spinner);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("Log In");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(i);
                finishAffinity();
            }
        });
        initView();

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, SignupAsActivity.class);
                startActivity(i);
                finishAffinity();
            }
        });
        viewSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this,SignupAsActivity.class);
                startActivity(i);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValidation() && isValidMobile(lognumText.getText().toString()) && isValidPassword(logPassText.getText().toString())) {
                    Posname = sp.getSelectedItem().toString();
                    Pos = sp.getSelectedItemPosition();
                    if (Pos == 0) {
                        EmployerLogin (lognumText.getText().toString(), logPassText.getText().toString());
                    }
                    if (Pos == 1) {
                        CandidateLogin(lognumText.getText().toString(), logPassText.getText().toString());
                    }
                    if (Pos == 2) {
                        AgentLogin(lognumText.getText().toString(), logPassText.getText().toString());
                    }
                }

            }
        });

        forgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Posname = sp.getSelectedItem().toString();
               Intent fp = new Intent(LoginActivity.this, ForgetpassActivity.class);
               fp.putExtra("value",Posname);
               startActivity(fp);
            }
        });


         if(getIntent().getStringExtra("UType")!=null)
        {
            if(getIntent().getStringExtra("UType").equalsIgnoreCase("Candidate"))
            {
                sp.setSelection(1);
            }
            else if(getIntent().getStringExtra("UType").equalsIgnoreCase("Employer"))
            {
                sp.setSelection(0);
            }
            else if(getIntent().getStringExtra("UType").equalsIgnoreCase("Agent"))
            {
                sp.setSelection(2);
            }
        }



    }

    private void initView() {
        lognumText = (TextInputEditText) findViewById(R.id.idphone);
        logPassText = (TextInputEditText) findViewById(R.id.log_pass_text);
        forgetPass = (TextView) findViewById(R.id.forget_pass);
        loginBtn = (Button) findViewById(R.id.login_btn);
        signin = (TextView) findViewById(R.id.signin);
        viewSignup = (TextView) findViewById(R.id.viewSignup);
    }

    private boolean checkValidation() {
        if (isEmpty(lognumText)) {
            lognumText.requestFocus();
            lognumText.setError("Enter number");
            return false;
        }
        return true;
    }
    public boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() <= 0;
    }




    private void CandidateLogin(String Mobile, String Password) {
        Utils.showProgressDialog(LoginActivity.this);
        ApiUtils.getAPIService().CheckLogin(Mobile, Password).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CandidateLogin>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(LoginActivity.this, e.getMessage().toString());
                    }

                    @Override
                    public void onNext(CandidateLogin cLogin) {

                        if (cLogin.getSuccess()) {
                            if (!cLogin.getMessage().equalsIgnoreCase("Server Error!")) {
                                if (cLogin.getMessage().equalsIgnoreCase("Exit")) {

                                    SharePrefarence.getmInstance(LoginActivity.this).SeTLogined("1");
                                    SharePrefarence.getmInstance(LoginActivity.this).SeTLoginAs(Posname);
                                    SharePrefarence.getmInstance(LoginActivity.this).SeTUserName(cLogin.getData().get(0).getFullName());
                                    SharePrefarence.getmInstance(LoginActivity.this).SeTEmailid(cLogin.getData().get(0).getEmailId());
                                    SharePrefarence.getmInstance(LoginActivity.this).setUser_ID(String.valueOf(cLogin.getData().get(0).getCandidateId()));
                                    SharePrefarence.getmInstance(LoginActivity.this).setMobileNo(cLogin.getData().get(0).getMobileNo());
                                    SharePrefarence.getmInstance(LoginActivity.this).setPassword(cLogin.getData().get(0).getPassword());
                                    Intent mIntent = new Intent(LoginActivity.this, HomeActivity.class);
                                    startActivity(mIntent);
                                    finish();
                                    // Toast.makeText(LoginActivity.this, "Login Exist :(", Toast.LENGTH_SHORT).show();

                                } else {
                                    //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                                    Utils.custoAlert(LoginActivity.this, cLogin.getMessage());
                                    SharePrefarence.getmInstance(LoginActivity.this).SeTLogined("0");
                                }
                            }
                        }
                    }
                });
    }

    private void EmployerLogin(String Mobile, String Password) {
        Utils.showProgressDialog(LoginActivity.this);
        ApiUtils.getAPIService().ECheckLogin(Mobile, Password).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<EmployerLogin>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(LoginActivity.this, e.getMessage().toString());
                    }

                    @Override
                    public void onNext(EmployerLogin eLogin) {

                        if (eLogin.getSuccess()) {
                            if (!eLogin.getMessage().equalsIgnoreCase("Server Error!")) {
                                if (eLogin.getMessage().equalsIgnoreCase("Exit")) {
                                    SharePrefarence.getmInstance(LoginActivity.this).SeTLogined("1");
                                    SharePrefarence.getmInstance(LoginActivity.this).SeTLoginAs(Posname);
                                    SharePrefarence.getmInstance(LoginActivity.this).SeTUserName(eLogin.getData().get(0).getEmployerName());
                                    SharePrefarence.getmInstance(LoginActivity.this).SeTEmailid(eLogin.getData().get(0).getEmailId());
                                    SharePrefarence.getmInstance(LoginActivity.this).setUser_ID(String.valueOf(eLogin.getData().get(0).getEmployerId()));
                                    SharePrefarence.getmInstance(LoginActivity.this).setMobileNo(eLogin.getData().get(0).getMobileNo());
                                    SharePrefarence.getmInstance(LoginActivity.this).setPassword(eLogin.getData().get(0).getPassword());
                                    Intent mIntent = new Intent(LoginActivity.this, HomeActivity.class);
                                    startActivity(mIntent);
                                    finish();
                                    // Toast.makeText(LoginActivity.this, "Login Exist :(", Toast.LENGTH_SHORT).show();

                                } else {
                                    //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                                    Utils.custoAlert(LoginActivity.this, eLogin.getMessage());
                                    SharePrefarence.getmInstance(LoginActivity.this).SeTLogined("0");
                                }
                            }
                        }
                    }
                });
    }

    private void AgentLogin(String Mobile, String Password) {
        Utils.showProgressDialog(LoginActivity.this);
        ApiUtils.getAPIService().ACheckLogin(Mobile, Password).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AgentLogin>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(LoginActivity.this, e.getMessage().toString());
                    }

                    @Override
                    public void onNext(AgentLogin aLogin) {

                        if (aLogin.getSuccess()) {
                            if (!aLogin.getMessage().equalsIgnoreCase("Server Error!")) {
                                if (aLogin.getMessage().equalsIgnoreCase("Exit")) {

                                    SharePrefarence.getmInstance(LoginActivity.this).SeTLogined("1");
                                    SharePrefarence.getmInstance(LoginActivity.this).SeTLoginAs(Posname);
                                    SharePrefarence.getmInstance(LoginActivity.this).SeTUserName(aLogin.getData().get(0).getAgentName());
                                    SharePrefarence.getmInstance(LoginActivity.this).SeTEmailid(aLogin.getData().get(0).getEmailId());
                                    SharePrefarence.getmInstance(LoginActivity.this).setUser_ID(String.valueOf(aLogin.getData().get(0).getAgentId()));
                                    SharePrefarence.getmInstance(LoginActivity.this).setMobileNo(aLogin.getData().get(0).getMobileNo());
                                    SharePrefarence.getmInstance(LoginActivity.this).setPassword(aLogin.getData().get(0).getPassword());
                                    Intent mIntent = new Intent(LoginActivity.this, HomeActivity.class);
                                    startActivity(mIntent);
                                    finish();
                                    // Toast.makeText(LoginActivity.this, "Login Exist :(", Toast.LENGTH_SHORT).show();

                                } else {
                                    //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                                    Utils.custoAlert(LoginActivity.this, aLogin.getMessage());
                                    SharePrefarence.getmInstance(LoginActivity.this).SeTLogined("0");
                                }
                            }
                        }
                    }
                });
    }


    private boolean isValidMobile(String phone) {
        boolean check = false;
        if (!Pattern.matches("[a-zA-Z]+", phone)) {
            if (phone.length() < 10 || phone.length() > 10) {
                // if(phone.length() != 10) {
                check = false;
                lognumText.setError("Not Valid Number");
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

//        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        if (password != null && password.length() >= 5) {
            pcheck = true;

        } else {

            logPassText.setError("Password length should be above 5");


        }


//        pattern = Pattern.compile(PASSWORD_PATTERN);
//        matcher = pattern.matcher(password);


        return pcheck;

    }


}
