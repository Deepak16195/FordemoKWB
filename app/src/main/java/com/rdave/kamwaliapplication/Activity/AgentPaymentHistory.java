package com.rdave.kamwaliapplication.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;

import com.rdave.kamwaliapplication.Adapter.AgentPaymentAdapter;
import com.rdave.kamwaliapplication.Model.AgentPayment;
import com.rdave.kamwaliapplication.R;
import com.rdave.kamwaliapplication.Utility.SharePrefarence;
import com.rdave.kamwaliapplication.Utility.Utils;
import com.rdave.kamwaliapplication.remote.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AgentPaymentHistory extends AppCompatActivity {

    protected Toolbar toolbar;
    protected RecyclerView recyclerView;
    List<AgentPayment.Data> employerSummaryList=new ArrayList<>();
    AgentPaymentAdapter employerSummaryAdapter;
    String Logined = "0";
    String LoginedAs = "0";
    String Username = "0";
    String Useremail = "0";
    String UserID = "0";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.agent_payment_history);
        initView();

        LoginedAs = SharePrefarence.getmInstance(AgentPaymentHistory.this).getLoginAs();
        Logined = SharePrefarence.getmInstance(AgentPaymentHistory.this).getLogined();
        Username = SharePrefarence.getmInstance(AgentPaymentHistory.this).getUserName();
        Useremail = SharePrefarence.getmInstance(AgentPaymentHistory.this).getEmailid();
        UserID = SharePrefarence.getmInstance(AgentPaymentHistory.this).getUser_Id();

        if (Logined.equalsIgnoreCase("1"))
        {
            if (LoginedAs.equalsIgnoreCase("Candidate")) {
                Utils.customMessage(AgentPaymentHistory.this,"You Are Not Eligible For This Feature");
                finish();

            }
            if (LoginedAs.equalsIgnoreCase("Employer")) {

                Utils.customMessage(AgentPaymentHistory.this,"You Are Not Eligible For This Feature");
                finish();
            }
            if (LoginedAs.equalsIgnoreCase("Agent")) {
                getAgentPayment(UserID);

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

    private void getAgentPayment(String UserID) {
        Utils.showProgressDialog(AgentPaymentHistory.this);
        ApiUtils.getAPIService().getAgentPaymentHistory(UserID).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AgentPayment>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();

                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(AgentPaymentHistory.this, e.getMessage().toString());
                    }

                    @Override
                    public void onNext(AgentPayment currencyModel) {
                        if (currencyModel.getSuccess()) {
                            if (!currencyModel.getMessage().equalsIgnoreCase("Server Error!")) {
                                if (currencyModel.getMessage().equalsIgnoreCase("Success")) {
                                    employerSummaryList=currencyModel.getData();


                                    employerSummaryAdapter = new AgentPaymentAdapter(AgentPaymentHistory.this, employerSummaryList);
                                    recyclerView.setAdapter(employerSummaryAdapter);
                                } else {
                                    Utils.custoAlert(AgentPaymentHistory.this, currencyModel.getMessage());
                                }
                            }
                        }
                    }
                });

    }

}
