package com.rdave.kamwaliapplication.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;

import com.rdave.kamwaliapplication.Adapter.EmployerPaymentAdapter;
import com.rdave.kamwaliapplication.Model.EmployerPayment;
import com.rdave.kamwaliapplication.R;
import com.rdave.kamwaliapplication.Utility.SharePrefarence;
import com.rdave.kamwaliapplication.Utility.Utils;
import com.rdave.kamwaliapplication.remote.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class EmployerPaymentHistory extends AppCompatActivity {

    protected Toolbar toolbar;
    protected RecyclerView recyclerView;
    List<EmployerPayment.DataBean> employerSummaryList=new ArrayList<>();
    EmployerPaymentAdapter employerSummaryAdapter;
    String Logined = "0";
    String LoginedAs = "0";
    String Username = "0";
    String Useremail = "0";
    String UserID = "0";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.employer_payment_history);
        initView();

        LoginedAs = SharePrefarence.getmInstance(EmployerPaymentHistory.this).getLoginAs();
        Logined = SharePrefarence.getmInstance(EmployerPaymentHistory.this).getLogined();
        Username = SharePrefarence.getmInstance(EmployerPaymentHistory.this).getUserName();
        Useremail = SharePrefarence.getmInstance(EmployerPaymentHistory.this).getEmailid();
        UserID = SharePrefarence.getmInstance(EmployerPaymentHistory.this).getUser_Id();

        if (Logined.equalsIgnoreCase("1"))
        {
            if (LoginedAs.equalsIgnoreCase("Candidate")) {
                Utils.customMessage(EmployerPaymentHistory.this,"You Are Not Eligible For This Feature");
                finish();

            }
            if (LoginedAs.equalsIgnoreCase("Employer")) {
                getEmployerPayment(UserID);

            }
            if (LoginedAs.equalsIgnoreCase("Agent")) {
                Utils.customMessage(EmployerPaymentHistory.this,"You Are Not Eligible For This Feature");
                finish();
            }
        }
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitle("Payment History");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }

    private void getEmployerPayment(String UserID) {
        Utils.showProgressDialog(EmployerPaymentHistory.this);
        ApiUtils.getAPIService().getEmployerPaymentHistory(UserID).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<EmployerPayment>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();

                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(EmployerPaymentHistory.this, e.getMessage().toString());
                    }

                    @Override
                    public void onNext(EmployerPayment currencyModel) {
                        if (currencyModel.isSuccess()) {
                            if (!currencyModel.getMessage().equalsIgnoreCase("Server Error!")) {
                                if (currencyModel.getMessage().equalsIgnoreCase("Success")) {
                                    employerSummaryList=currencyModel.getData();


                                    employerSummaryAdapter = new EmployerPaymentAdapter(EmployerPaymentHistory.this, employerSummaryList);
                                    recyclerView.setAdapter(employerSummaryAdapter);
                                } else {
                                    Utils.custoAlert(EmployerPaymentHistory.this, currencyModel.getMessage());
                                }
                            }
                        }
                    }
                });

    }

}
