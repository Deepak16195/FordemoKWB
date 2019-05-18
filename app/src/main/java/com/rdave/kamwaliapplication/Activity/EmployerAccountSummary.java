package com.rdave.kamwaliapplication.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;

import com.rdave.kamwaliapplication.Adapter.EmployerSummaryAdapter;
import com.rdave.kamwaliapplication.Model.EmployerSummary;
import com.rdave.kamwaliapplication.R;
import com.rdave.kamwaliapplication.Utility.SharePrefarence;
import com.rdave.kamwaliapplication.Utility.Utils;
import com.rdave.kamwaliapplication.remote.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class EmployerAccountSummary extends AppCompatActivity {

    protected Toolbar toolbar;
    protected RecyclerView recyclerView;
    List<EmployerSummary.DataBean> employerSummaryList=new ArrayList<>();
    EmployerSummaryAdapter employerSummaryAdapter;
    String Logined = "0";
    String LoginedAs = "0";
    String Username = "0";
    String Useremail = "0";
    String UserID = "0";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.employer_account_summary);
        initView();

        LoginedAs = SharePrefarence.getmInstance(EmployerAccountSummary.this).getLoginAs();
        Logined = SharePrefarence.getmInstance(EmployerAccountSummary.this).getLogined();
        Username = SharePrefarence.getmInstance(EmployerAccountSummary.this).getUserName();
        Useremail = SharePrefarence.getmInstance(EmployerAccountSummary.this).getEmailid();
        UserID = SharePrefarence.getmInstance(EmployerAccountSummary.this).getUser_Id();

        if (Logined.equalsIgnoreCase("1"))
        {
            if (LoginedAs.equalsIgnoreCase("Candidate")) {
                Utils.customMessage(EmployerAccountSummary.this,"You Are Not Eligible For This Feature");
                finish();

            }
            if (LoginedAs.equalsIgnoreCase("Employer")) {
                getEmployerSummary(UserID);

            }
            if (LoginedAs.equalsIgnoreCase("Agent")) {
                Utils.customMessage(EmployerAccountSummary.this,"You Are Not Eligible For This Feature");
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
        toolbar.setTitle("Account Summary");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }

    private void getEmployerSummary(String UserID) {
        Utils.showProgressDialog(EmployerAccountSummary.this);
        ApiUtils.getAPIService().getEmployerSummary(UserID).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<EmployerSummary>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();

                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(EmployerAccountSummary.this, e.getMessage().toString());
                    }

                    @Override
                    public void onNext(EmployerSummary currencyModel) {
                        if (currencyModel.isSuccess()) {
                            if (!currencyModel.getMessage().equalsIgnoreCase("Server Error!")) {
                                if (currencyModel.getMessage().equalsIgnoreCase("Success")) {
                                    employerSummaryList=currencyModel.getData();


                                    employerSummaryAdapter = new EmployerSummaryAdapter(EmployerAccountSummary.this, employerSummaryList);
                                    recyclerView.setAdapter(employerSummaryAdapter);
                                } else {
                                    Utils.custoAlert(EmployerAccountSummary.this, currencyModel.getMessage());
                                }
                            }
                        }
                    }
                });

    }

}
