package com.rdave.kamwaliapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.rdave.kamwaliapplication.Model.FSaveOtp;
import com.rdave.kamwaliapplication.Model.MatchOtp;
import com.rdave.kamwaliapplication.R;
import com.rdave.kamwaliapplication.Utility.Utils;
import com.rdave.kamwaliapplication.remote.ApiUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ForgetpassActivity extends AppCompatActivity {

    protected TextInputEditText txtMobile;
    protected TextInputLayout usrContact;
    protected  Toolbar toolbar;
    protected Button gtotp;
    AlertDialog.Builder builder;
    AlertDialog dialog;
    PinView pinView;
    String str1;
    String otp;
    String num;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_forgetpass);
        initView();



        if(getIntent().getStringExtra("value")!=null)
        {
            str1=getIntent().getStringExtra("value");
            toolbar.setTitle("Forgot Password as " + getIntent().getStringExtra("value").toString());
        }
        num = txtMobile.getText().toString();

        gtotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (str1.equalsIgnoreCase("Candidate")) {
                    SaveOtp(txtMobile.getText().toString(), "C");
                }
                if (str1.equalsIgnoreCase("Employer")) {
                    SaveOtp(txtMobile.getText().toString(), "E");
                }
                if (str1.equalsIgnoreCase("Agent")) {
                    SaveOtp(txtMobile.getText().toString(), "A");
                }

            }
        });


    }


    private void initView() {
        txtMobile = (TextInputEditText) findViewById(R.id.usr_Mobile);
        toolbar  = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        gtotp = (Button) findViewById(R.id.gtotp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ForgetpassActivity.this, LoginActivity.class);
                startActivity(i);
                finishAffinity();
            }
        });
        toolbar.setTitle("Forgot Password");
    }

    private void MatchgOtp( String mobile, String type,String otpnumber) {
        Utils.showProgressDialog(ForgetpassActivity.this);
        ApiUtils.getAPIService().GetOtp(type,mobile,otpnumber).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MatchOtp>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(ForgetpassActivity.this, e.getMessage().toString());
                    }

                    @Override
                    public void onNext(MatchOtp matchOtp) {
                        Utils.hideProgressDialog();
                        if (matchOtp.getMessage().equalsIgnoreCase("Exit")) {
                            dialog.dismiss();

                                Intent m = new Intent(ForgetpassActivity.this,ResetPassActivity.class);
                                m.putExtra("value",str1);
                                m.putExtra("vnum",num);
                                startActivity(m);

                        }else
                        {
                            Utils.customMessage(ForgetpassActivity.this,"Invalid OTP");
                            //Toast.makeText(ForgetpassActivity.this, "Invalid OTP", Toast.LENGTH_SHORT).show();
                        }

                        }
                });
    }
    private void SaveOtp( String Mobile, String type) {
        Utils.showProgressDialog(ForgetpassActivity.this);
        ApiUtils.getAPIService().getSaveotp(  type,Mobile ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<FSaveOtp>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(ForgetpassActivity.this,e.getMessage());

                    }

                    @Override
                    public void onNext(FSaveOtp sOtp) {
                        Utils.hideProgressDialog();
                        if (sOtp.getMessage().equalsIgnoreCase("Save")) {
                            open_dialog();
                        }
                        else
                        {
                            Toast.makeText(ForgetpassActivity.this, "This number is not exist", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
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
                String UNumber = txtMobile.getText().toString();
                if (str1.equalsIgnoreCase("Candidate")) {
                    MatchgOtp(UNumber, "C", otp);
                }
                if (str1.equalsIgnoreCase("Employer")) {
                    MatchgOtp(UNumber, "E", otp);
                }
                if (str1.equalsIgnoreCase("Agent")) {
                    MatchgOtp(UNumber, "A", otp);
                }

            }
        });


        builder.setView(row);
        dialog = builder.create();
        dialog.show();
    }


}
