package com.rdave.kamwaliapplication.Activity;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rdave.kamwaliapplication.Model.ChangePassword;
import com.rdave.kamwaliapplication.R;
import com.rdave.kamwaliapplication.Utility.SharePrefarence;
import com.rdave.kamwaliapplication.Utility.Utils;
import com.rdave.kamwaliapplication.remote.ApiUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ChangepassActivity extends AppCompatActivity {

    protected Toolbar toolbar;
    protected TextInputEditText eioldpass;
    protected TextInputEditText eiNewpass;
    protected TextInputEditText eiConfirmpass;
    protected Button submit;
    String LoginedAs = "0";
    String UserID = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_changepass);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("Change Password");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initView();

        UserID= SharePrefarence.getmInstance(ChangepassActivity.this).getUser_Id();
        LoginedAs = SharePrefarence.getmInstance(ChangepassActivity.this).getLoginAs();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValidation()) {
                    if (LoginedAs.equalsIgnoreCase("Candidate") && !UserID.equalsIgnoreCase("0")) {
                        forgetPassword("C", UserID, eioldpass.getText().toString(), eiNewpass.getText().toString());
                    }
                    if (LoginedAs.equalsIgnoreCase("Employer") && !UserID.equalsIgnoreCase("0")) {
                        forgetPassword("E", UserID, eioldpass.getText().toString(), eiNewpass.getText().toString());
                    }
                    if (LoginedAs.equalsIgnoreCase("Agent") && !UserID.equalsIgnoreCase("0")) {
                        forgetPassword("A",UserID,eioldpass.getText().toString(),eiNewpass.getText().toString());

                    }
                }
            }
        });
    }

    private void initView() {
        eioldpass = (TextInputEditText) findViewById(R.id.eioldpass);
        eiNewpass = (TextInputEditText) findViewById(R.id.eiNewpass);
        eiConfirmpass = (TextInputEditText) findViewById(R.id.eiConfirmpass);
        submit = (Button) findViewById(R.id.submitbtn);
    }



    private boolean checkValidation() {

        if (isEmpty(eioldpass)) {
            eioldpass.requestFocus();
            eioldpass.setError("Enter Old Password");
            return false;
        }
        if (isEmpty(eiNewpass)) {
            eiNewpass.requestFocus();
            eiNewpass.setError("Enter New Password");
            return false;
        }
        if (isEmpty(eiConfirmpass)) {
            eiConfirmpass.requestFocus();
            eiConfirmpass.setError("Confirm New Password");
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

            eiNewpass.setError("Password length should be above 5");
            eiConfirmpass.setError("Password not matched");


        }


//        pattern = Pattern.compile(PASSWORD_PATTERN);
//        matcher = pattern.matcher(password);


        return pcheck;

    }
    public boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() <= 0;
    }

    private void forgetPassword(String Type, String Userid, String OldPassword, String
            NewPassword) {
        Utils.showProgressDialog(ChangepassActivity.this);
        ApiUtils.getAPIService().chngPass(Type, Userid, OldPassword, NewPassword).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ChangePassword>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(ChangepassActivity.this, e.getMessage().toString());
                    }

                    @Override
                    public void onNext(ChangePassword fgtPass) {

                        if (fgtPass.getSuccess()) {
                            if (!fgtPass.getMessage().equalsIgnoreCase("Server Error!")) {
                                if (fgtPass.getMessage().equalsIgnoreCase("Changed")) {

                                     Toast.makeText(ChangepassActivity.this, "Password change", Toast.LENGTH_SHORT).show();

                                } else {
                                    Toast.makeText(ChangepassActivity.this, "Does not password", Toast.LENGTH_SHORT).show();
                                    Utils.custoAlert(ChangepassActivity.this, fgtPass.getMessage());
                                }
                            }
                        }
                    }
                });
    }



}
