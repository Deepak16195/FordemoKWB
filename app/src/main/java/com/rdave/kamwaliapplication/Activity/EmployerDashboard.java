package com.rdave.kamwaliapplication.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.rdave.kamwaliapplication.Adapter.StateAdapter;
import com.rdave.kamwaliapplication.Model.GetState;
import com.rdave.kamwaliapplication.Model.PackageCount;
import com.rdave.kamwaliapplication.R;
import com.rdave.kamwaliapplication.Utility.SharePrefarence;
import com.rdave.kamwaliapplication.Utility.Utils;
import com.rdave.kamwaliapplication.remote.ApiUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class EmployerDashboard extends AppCompatActivity {

    protected Toolbar toolbar;
    protected TextView txtTotal;
    protected TextView txtUsed1;
    protected TextView txtBalance;
    protected TextView txtTotal1;
    protected TextView txtUsed;
    protected TextView txtBalance1;

    String Logined = "0";
    String LoginedAs = "0";
    String UserID = "0";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.employer_dashboard);
        initView();
        LoginedAs = SharePrefarence.getmInstance(EmployerDashboard.this).getLoginAs();
        Logined = SharePrefarence.getmInstance(EmployerDashboard.this).getLogined();
        UserID = SharePrefarence.getmInstance(EmployerDashboard.this).getUser_Id();

        if(Logined.equalsIgnoreCase("1"))
        {
            if (LoginedAs.equalsIgnoreCase("Employer")) {
                getCount(UserID);
            }
        }

    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        txtTotal = (TextView) findViewById(R.id.txtTotal);
        txtUsed1 = (TextView) findViewById(R.id.txtUsed1);
        txtBalance = (TextView) findViewById(R.id.txtBalance);
        txtTotal1 = (TextView) findViewById(R.id.txtTotal1);
        txtUsed = (TextView) findViewById(R.id.txtUsed);
        txtBalance1 = (TextView) findViewById(R.id.txtBalance1);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setTitle("Dashbaord");
    }



    private void getCount(String aEmployerID) {
        Utils.showProgressDialog(EmployerDashboard.this);
        ApiUtils.getAPIService().getPackageCount(aEmployerID).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PackageCount>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();

                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(EmployerDashboard.this, e.getMessage().toString());
                    }

                    @Override
                    public void onNext(PackageCount currencyModel) {
                        if (currencyModel.isSuccess()) {
                            if (!currencyModel.getMessage().equalsIgnoreCase("Server Error!")) {
                                if (currencyModel.getMessage().equalsIgnoreCase("Success")) {

                                    if(currencyModel.getData()!=null)
                                    {
                                        txtTotal.setText(currencyModel.getData().get(0).getTCreditViewCount());
                                        txtUsed.setText(currencyModel.getData().get(0).getTDebitViewCount());
                                        txtBalance.setText(currencyModel.getData().get(0).getRViewCount());

                                        txtTotal1.setText(currencyModel.getData().get(0).getTCreditPostCount());
                                        txtUsed1.setText(currencyModel.getData().get(0).getTDebitPostCount());
                                        txtBalance1.setText(currencyModel.getData().get(0).getRPostCount());
                                    }


//                                    gtstate = currencyModel.getData();
//                                    adapter = new SpinnerAdapter(UpdateProfileActivity.this,gtstate);
//                                    Stateid.setAdapter(adapter);
                                } else {
                                    //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                                    Utils.custoAlert(EmployerDashboard.this, currencyModel.getMessage());
                                }
                            }
                        }
                    }
                });

    }



}
