package com.rdave.kamwaliapplication.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;

import com.rdave.kamwaliapplication.Adapter.EmployerViewedCandidateAdapter;
import com.rdave.kamwaliapplication.Model.EmployerViewedCandidateModel;
import com.rdave.kamwaliapplication.R;
import com.rdave.kamwaliapplication.Utility.SharePrefarence;
import com.rdave.kamwaliapplication.Utility.Utils;
import com.rdave.kamwaliapplication.remote.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class EmployerViewedCandidate extends AppCompatActivity {

    protected Toolbar toolbar;
    protected RecyclerView hRecyclerview;
List<EmployerViewedCandidateModel.Data> mAppliedJobs=new ArrayList<>();
EmployerViewedCandidateAdapter appliedJobLAdapter;
    String Logined = "0";
    String LoginedAs = "0";
    String Username = "0";
    String Useremail = "0";
    String UserID = "0";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.employer_list);
        initView();
        LoginedAs = SharePrefarence.getmInstance(EmployerViewedCandidate.this).getLoginAs();
        Logined = SharePrefarence.getmInstance(EmployerViewedCandidate.this).getLogined();
        Username = SharePrefarence.getmInstance(EmployerViewedCandidate.this).getUserName();
        Useremail = SharePrefarence.getmInstance(EmployerViewedCandidate.this).getEmailid();
        UserID = SharePrefarence.getmInstance(EmployerViewedCandidate.this).getUser_Id();

        if (Logined.equalsIgnoreCase("1"))
        {
            if (LoginedAs.equalsIgnoreCase("Candidate")) {
                Utils.customMessage(EmployerViewedCandidate.this,"You Are Not Eligible For This Feature");
                finish();

            }
            if (LoginedAs.equalsIgnoreCase("Employer")) {
                getViewedCandidate(UserID);

            }
            if (LoginedAs.equalsIgnoreCase("Agent")) {
                Utils.customMessage(EmployerViewedCandidate.this,"You Are Not Eligible For This Feature");
                finish();
            }
        }

    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        hRecyclerview = (RecyclerView) findViewById(R.id.h_recyclerview);
        hRecyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitle("Viewed Candidate");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }


    private void getViewedCandidate(String UserID) {
        Utils.showProgressDialog(EmployerViewedCandidate.this);
        ApiUtils.getAPIService().getEmployerViewCandidate(UserID).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<EmployerViewedCandidateModel>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();

                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(EmployerViewedCandidate.this, e.getMessage().toString());
                    }

                    @Override
                    public void onNext(EmployerViewedCandidateModel currencyModel) {
                        if (currencyModel.getSuccess()) {
                            if (!currencyModel.getMessage().equalsIgnoreCase("Server Error!")) {
                                if (currencyModel.getMessage().equalsIgnoreCase("Success")) {
                                    mAppliedJobs=currencyModel.getData();
                                    appliedJobLAdapter = new EmployerViewedCandidateAdapter(EmployerViewedCandidate.this, mAppliedJobs);
                                    hRecyclerview.setAdapter(appliedJobLAdapter);
                                } else {
                                    Utils.custoAlert(EmployerViewedCandidate.this, currencyModel.getMessage());
                                }
                            }
                        }
                    }
                });

    }


}
