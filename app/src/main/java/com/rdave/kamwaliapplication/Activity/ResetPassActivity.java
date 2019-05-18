package com.rdave.kamwaliapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rdave.kamwaliapplication.Model.ResetPassword;
import com.rdave.kamwaliapplication.R;
import com.rdave.kamwaliapplication.Utility.Utils;
import com.rdave.kamwaliapplication.remote.ApiUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ResetPassActivity extends AppCompatActivity {

    protected Toolbar toolbar;
    protected TextInputEditText eifnewpass;
    protected TextInputEditText eifConfirmpass;
    protected Button fSave;
    String str1;
    String str2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_resetpass);
        initView();


        if(getIntent().getStringExtra("value")!=null && getIntent().getStringExtra("vnum")!=null)
        {
            str1=getIntent().getStringExtra("value");
            str2=getIntent().getStringExtra("vnum");
            toolbar.setTitle("Reset Password as " + getIntent().getStringExtra("value").toString());
        }

        fSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValidation()) {
                    if (eifnewpass.getText().toString().equalsIgnoreCase(eifConfirmpass.getText().toString())) {
                        if (str1.equalsIgnoreCase("Candidate") && !str2.equalsIgnoreCase("0")) {
                            ResetPassword("C", str2, eifnewpass.getText().toString());
                        }
                        if (str1.equalsIgnoreCase("Employer") && !str2.equalsIgnoreCase("0")) {
                            ResetPassword("E", str2, eifnewpass.getText().toString());
                        }
                        if (str1.equalsIgnoreCase("Agent") && !str2.equalsIgnoreCase("0")) {
                            ResetPassword("A", str2, eifnewpass.getText().toString());

                        }

                    } else {
                        eifConfirmpass.setError("Password Not Matched");

                    }
                }
            }
        });

    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        eifnewpass = (TextInputEditText) findViewById(R.id.eifnewpass);
        eifConfirmpass = (TextInputEditText) findViewById(R.id.eifConfirmpass);
        fSave = (Button) findViewById(R.id.fSave);
        toolbar  = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ResetPassActivity.this, LoginActivity.class);
                startActivity(i);
                finishAffinity();
            }
        });
        toolbar.setTitle("Reset Password");
    }
    private void ResetPassword(String Type, String MobileNo, String NewPassword) {
        Utils.showProgressDialog(ResetPassActivity.this);
        ApiUtils.getAPIService().resetPass(Type, MobileNo, NewPassword).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResetPassword>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(ResetPassActivity.this, e.getMessage().toString());
                    }

                    @Override
                    public void onNext(ResetPassword rePass) {

                        if (rePass.getSuccess()) {
                            if (!rePass.getMessage().equalsIgnoreCase("Server Error!")) {
                                if (rePass.getMessage().equalsIgnoreCase("Updated")) {

                                  Utils.customMessage(ResetPassActivity.this,"Password Changed Successfully");
                                  Intent mIntent=new Intent(ResetPassActivity.this,LoginActivity.class);
                                  startActivity(mIntent);
                                  finishAffinity();

                                } else {

                                    Utils.customMessage(ResetPassActivity.this,"Some thing went wrong");

                                }
                            }
                        }
                    }
                });
    }
    private boolean checkValidation() {


        if (isEmpty(eifnewpass)) {
            eifnewpass.requestFocus();
            eifnewpass.setError("Enter New Password");
            return false;
        }
        if (isEmpty(eifConfirmpass)) {
            eifConfirmpass.requestFocus();
            eifConfirmpass.setError("Confirm New Password");
            return false;
        }


        return true;
    }
    public boolean isValidPassword(final String password) {

//        Pattern pattern;
//        Matcher matcher;
        boolean pcheck = false;

        if (password != null && password.length() >= 5) {
            pcheck = true;

        } else {

            eifnewpass.setError("Password length should be above 5");
            eifConfirmpass.setError("Password not matched");


        }


//        pattern = Pattern.compile(PASSWORD_PATTERN);
//        matcher = pattern.matcher(password);


        return pcheck;

    }
    public boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() <= 0;
    }


}
