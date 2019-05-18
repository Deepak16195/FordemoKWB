package com.rdave.kamwaliapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.rdave.kamwaliapplication.Model.SaveSupport;
import com.rdave.kamwaliapplication.R;
import com.rdave.kamwaliapplication.Utility.SharePrefarence;
import com.rdave.kamwaliapplication.Utility.Utils;
import com.rdave.kamwaliapplication.remote.ApiUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SupportActivity extends AppCompatActivity {

    protected TextInputEditText txtName;
    protected Button fsubmit;
    String LoginedAs = "0";
    String UserID = "0";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_support);

        UserID= SharePrefarence.getmInstance(SupportActivity.this).getUser_Id();
        LoginedAs = SharePrefarence.getmInstance(SupportActivity.this).getLoginAs();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitle("Support");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                Intent i = new Intent(SupportActivity.this, HomeActivity.class);
                startActivity(i);
            }
        });
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        initView();

        fsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LoginedAs.equalsIgnoreCase("Candidate") && !UserID.equalsIgnoreCase("0")) {
                    sSupport("C", UserID,fsubmit.getText().toString());
                }
                if (LoginedAs.equalsIgnoreCase("Employer") && !UserID.equalsIgnoreCase("0")) {
                    sSupport("E", UserID, fsubmit.getText().toString());
                }
                if (LoginedAs.equalsIgnoreCase("Agent") && !UserID.equalsIgnoreCase("0")) {
                    sSupport("A",UserID,fsubmit.getText().toString());
                }

            }
        });


    }

    private void initView() {
        txtName = (TextInputEditText) findViewById(R.id.txt_Name);
        fsubmit = (Button) findViewById(R.id.fsubmit);
    }
    private void sSupport(String Type, String Userid,String Meesage) {
        Utils.showProgressDialog(SupportActivity.this);
        ApiUtils.getAPIService().saveSupport(Type, Userid,Meesage).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SaveSupport>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(SupportActivity.this, e.getMessage().toString());
                    }

                    @Override
                    public void onNext(SaveSupport ssuport) {

                        if (ssuport.getSuccess()) {
                            if (!ssuport.getMessage().equalsIgnoreCase("Server Error!")) {
                                if (ssuport.getMessage().equalsIgnoreCase("Save")) {

                                    Toast.makeText(SupportActivity.this, "Feedback Saved", Toast.LENGTH_SHORT).show();

                                } else {
//                                    Toast.makeText(ChangepassActivity.this, "Does not password", Toast.LENGTH_SHORT).show();
                                    Utils.custoAlert(SupportActivity.this, ssuport.getMessage());
                                }
                            }
                        }
                    }
                });
    }


}
