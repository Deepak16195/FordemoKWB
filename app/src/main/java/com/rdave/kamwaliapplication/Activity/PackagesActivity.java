package com.rdave.kamwaliapplication.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rdave.kamwaliapplication.Adapter.PackagesAdapter;
import com.rdave.kamwaliapplication.R;
import com.rdave.kamwaliapplication.Utility.Utils;
import com.rdave.kamwaliapplication.remote.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PackagesActivity extends AppCompatActivity {

    protected Toolbar toolbar;
    protected LinearLayout numberPadLayout;
    protected TextView txtHeading;
    protected TextView txtDesciption;
    private RecyclerView precyclerview;
    List<PackagesContent.DataEntity> pContent = new ArrayList<>();
    public PackagesAdapter pAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_packages);
        gPackages();
        initView();

    }


    private void gPackages() {
        Utils.showProgressDialog(PackagesActivity.this);
        ApiUtils.getAPIService().getPackages().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PackagesContent>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();

                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(PackagesActivity.this, e.getMessage().toString());
                    }

                    @Override
                    public void onNext(PackagesContent packagesModel) {
                        if (packagesModel.getSuccess()) {
                            if (!packagesModel.getMessage().equalsIgnoreCase("Server Error!")) {
                                if (packagesModel.getMessage().equalsIgnoreCase("Success")) {
                                    pContent = packagesModel.getData();
                                    pAdapter = new PackagesAdapter(PackagesActivity.this, pContent);
                                    precyclerview.setAdapter(pAdapter);
                                } else {
                                    //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                                    Utils.custoAlert(PackagesActivity.this, packagesModel.getMessage());
                                }
                            }
                        }
                    }
                });


    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        numberPadLayout = (LinearLayout) findViewById(R.id.numberPadLayout);
        precyclerview = (RecyclerView) findViewById(R.id.package_reclerlist);
        precyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        numberPadLayout.setBackgroundColor(getResources().getColor(R.color.colorWhite));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setVisibility(View.VISIBLE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitle("Packages");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        txtHeading = (TextView) findViewById(R.id.txtHeading);
        txtHeading.setTextColor(getResources().getColor(R.color.colorBlack));
        txtDesciption = (TextView) findViewById(R.id.txt_Desciption);
        txtDesciption.setText("To View Candidate Profile Buy a Package, From Package It will Deduct the View Count From Package Balance? Click on Buy Now To View Candidate Profile");
        txtDesciption.setTextColor(getResources().getColor(R.color.colorBlack));
    }
}

