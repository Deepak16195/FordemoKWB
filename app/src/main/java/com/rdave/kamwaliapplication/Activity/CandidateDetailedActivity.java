package com.rdave.kamwaliapplication.Activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rdave.kamwaliapplication.Adapter.PackagesAdapter;
import com.rdave.kamwaliapplication.Model.CandidateDeatailed;
import com.rdave.kamwaliapplication.Model.DeductPackView;
import com.rdave.kamwaliapplication.R;
import com.rdave.kamwaliapplication.Utility.SharePrefarence;
import com.rdave.kamwaliapplication.Utility.Utils;
import com.rdave.kamwaliapplication.remote.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CandidateDetailedActivity extends AppCompatActivity {
    String cadigateid;

    TextView nametv,categorytv,subcategorytv,agetv,gendertv,maritialStv,maxEdutvr,religiontv,sSkilltv,workingStv,jobTtv,expSalary,locationtv,Updatetv,txtContact,txtEmail;
    ImageView candidateimg,imagePhone,imageEmail;
    Button btnContact;
    String Logined = "0";
    String LoginedAs = "0";
    PackagesAdapter pAdapter;
    String UserID = "0";
    String EmailID="";
    List<PackagesContent.DataEntity> pContent = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_detailed);
        nametv = (TextView) findViewById(R.id.idName);
        categorytv = (TextView) findViewById(R.id.idCategory);
        subcategorytv = (TextView)findViewById(R.id.idsubcategory);
        agetv = (TextView)findViewById(R.id.idage);
        gendertv =(TextView) findViewById(R.id.idgender);
        maritialStv =(TextView) findViewById(R.id.idmaritialstatus);
        maxEdutvr =(TextView) findViewById(R.id.idmaxedu);
        religiontv = (TextView)findViewById(R.id.idreligion);
        sSkilltv = (TextView)findViewById(R.id.idSSkill);
        workingStv = (TextView)findViewById(R.id.idWs);
        jobTtv =(TextView) findViewById(R.id.idJobtype);
        expSalary =(TextView) findViewById(R.id.idExpectedsalary);
        locationtv = (TextView)findViewById(R.id.idlocation);
        Updatetv = (TextView)findViewById(R.id.updateon);
        candidateimg =(ImageView) findViewById(R.id.idCimg);
        btnContact = (Button) findViewById(R.id.btcontact);
        imagePhone=(ImageView)findViewById(R.id.imagePhone);
        imageEmail=(ImageView)findViewById(R.id.imageEmail);
        txtContact=(TextView)findViewById(R.id.txtContact);
        txtEmail=(TextView)findViewById(R.id.txtEmail);

        imagePhone.setVisibility(View.GONE);
        imageEmail.setVisibility(View.GONE);

        txtContact.setVisibility(View.GONE);
        txtEmail.setVisibility(View.GONE);



        txtContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagePhone.performClick();
            }
        });


        txtEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtEmail.getText().length()>0)
                {
                    imageEmail.performClick();
                }
            }
        });

        imagePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(txtContact.getText().length()>0)
                {
                    Uri u = Uri.parse("tel:" + txtContact.getText().toString());

                    // Create the intent and set the data for the
                    // intent as the phone number.
                    Intent i = new Intent(Intent.ACTION_DIAL, u);

                    try
                    {
                        // Launch the Phone app's dialer with a phone
                        // number to dial a call.
                        startActivity(i);
                    }
                    catch (SecurityException s)
                    {
                        // show() method display the toast with
                        // exception message.

                        Utils.customMessage(CandidateDetailedActivity.this, s.getMessage());

                    }
                }


            }
        });

        imageEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtEmail.getText().length()>0)
                {



                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto",EmailID, null));
                //emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                //emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
                startActivity(Intent.createChooser(emailIntent, "Send Email..."));
                }
                else{
                    Utils.customMessage(CandidateDetailedActivity.this,"Email ID Not Found");
                }

            }
        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitle("Candidate Detailed");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        LoginedAs = SharePrefarence.getmInstance(CandidateDetailedActivity.this).getLoginAs();
        Logined = SharePrefarence.getmInstance(CandidateDetailedActivity.this).getLogined();
        UserID= SharePrefarence.getmInstance(CandidateDetailedActivity.this).getUser_Id();

        if(getIntent().getStringExtra("ID")!=null)
        {
            cadigateid = getIntent().getExtras().getString("ID");
        }
        else
        {
            Utils.customMessage(CandidateDetailedActivity.this,"Something went wrong");
            finish();
            return;

        }


        if (cadigateid.isEmpty()){
            Toast.makeText(CandidateDetailedActivity.this, "CLICK ON ITEM", Toast.LENGTH_SHORT).show();
        }
        else{
            CandidateDetail(cadigateid);
        }
        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!UserID.equalsIgnoreCase("0"))
                {


                    if(getIntent().getStringExtra("View")!=null)
                    {
                        imagePhone.setVisibility(View.VISIBLE);
                        imageEmail.setVisibility(View.VISIBLE);

                        txtContact.setVisibility(View.VISIBLE);
                        txtEmail.setVisibility(View.VISIBLE);
                    }
                    else{
                        final AlertDialog.Builder builder = new AlertDialog.Builder(CandidateDetailedActivity.this);
                        builder.setTitle("View Profile");
                        builder.setMessage("By proceeding further you'll utilize 1 view from your account. Do you wish to proeed further?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                DeductPackVCount(UserID,cadigateid);

                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();

                            }
                        });

                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }




                }else
                {

                }




            }
        });
    }
    private void CandidateDetail(String Candidateid) {
        Utils.showProgressDialog(CandidateDetailedActivity.this);
        ApiUtils.getAPIService().Candidatedetail(Candidateid).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CandidateDeatailed>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(CandidateDetailedActivity.this, e.getMessage().toString());
                    }

                    @Override
                    public void onNext(CandidateDeatailed cdetail) {
                        if (cdetail.getSuccess()) {
                            if (!cdetail.getMessage().equalsIgnoreCase("Server Error!")) {
                                if (cdetail.getMessage().equalsIgnoreCase("Success")) {

                                    nametv.setText(cdetail.getData().get(0).getFullName().toString());
                                    categorytv.setText(cdetail.getData().get(0).getCategory().toString());
                                    subcategorytv.setText(cdetail.getData().get(0).getSubcategory().toString());
                                    agetv.setText(cdetail.getData().get(0).getAge()+"");
                                    gendertv.setText(cdetail.getData().get(0).getGender().toString());
                                    maritialStv.setText(cdetail.getData().get(0).getMaritalStatus().toString());
                                    maxEdutvr.setText(cdetail.getData().get(0).getMaximumEducation().toString());
                                    religiontv.setText(cdetail.getData().get(0).getReligion().toString());
                                    sSkilltv.setText(cdetail.getData().get(0).getServiceSkill().toString());
                                    workingStv.setText(cdetail.getData().get(0).getWorkingSince().toString());
                                    jobTtv.setText(cdetail.getData().get(0).getJobTime().toString());
                                    expSalary.setText(cdetail.getData().get(0).getExpectedSalary()+"");
                                    locationtv.setText(cdetail.getData().get(0).getServiceLocation().toString());
                                    Updatetv.setText(cdetail.getData().get(0).getCreated().toString());

                                    txtContact.setText(cdetail.getData().get(0).getMobileNo());
                                    if(cdetail.getData().get(0).getEmailId()!=null)
                                    {
                                        txtEmail.setText(cdetail.getData().get(0).getEmailId());
                                        EmailID=cdetail.getData().get(0).getEmailId();
                                    }else
                                    {
                                        txtEmail.setText("");
                                        EmailID="";
                                    }


//                                   candidateimg.setImageResource(cdetail.getData().get(0).getImage());getImag e
                                } else {
                                    //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                                    Utils.custoAlert(CandidateDetailedActivity.this, cdetail.getMessage());

                                }
                            }
                        }
                    }
                });
    }
    private void DeductPackVCount(String EmplrId,String CandId) {
        Utils.showProgressDialog(CandidateDetailedActivity.this);
        ApiUtils.getAPIService().DPVCount(EmplrId,CandId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DeductPackView>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(CandidateDetailedActivity.this, e.getMessage().toString());
                    }

                    @Override
                    public void onNext(DeductPackView vdeduct) {
                        Utils.hideProgressDialog();
                        if (vdeduct.getSuccess()) {
                            if (!vdeduct.getMessage().equalsIgnoreCase("Server Error!")) {
                                if (vdeduct.getMessage().equalsIgnoreCase("Not Exit")) {


                                      LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                View row = inflater.inflate(R.layout.activity_packages, null);
                final Dialog dialog = new Dialog(row.getContext());
                dialog.setContentView(row);
                dialog.setCancelable(true);


                final RecyclerView precyclerview = (RecyclerView) row.findViewById(R.id.package_reclerlist);
                precyclerview.setLayoutManager(new LinearLayoutManager(CandidateDetailedActivity.this, LinearLayoutManager.HORIZONTAL, false));
                                    final TextView        txtHeading = (TextView)  row.findViewById(R.id.txtHeading);
                                  final TextView txtDesciption = (TextView) row. findViewById(R.id.txt_Desciption);
                                    txtDesciption.setText("To View Candidate Profile Buy a Package, From Package It will Deduct the View Count From Package Balance? Click on Buy Now To View Candidate Profile");

                    Utils.showProgressDialog(CandidateDetailedActivity.this);
                    ApiUtils.getAPIService().getPackages().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Subscriber<PackagesContent>() {
                                @Override
                                public void onCompleted() {
                                    Utils.hideProgressDialog();

                                }

                                @Override
                                public void onError(Throwable e) {
                                    Utils.hideProgressDialog();
                                    Utils.customMessage(CandidateDetailedActivity.this, e.getMessage().toString());
                                }

                                @Override
                                public void onNext(PackagesContent packagesModel) {
                                    if (packagesModel.getSuccess()) {

                                        if (!packagesModel.getMessage().equalsIgnoreCase("Server Error!")) {
                                            if (packagesModel.getMessage().equalsIgnoreCase("Success")) {
                                                pContent = packagesModel.getData();
                                                pAdapter = new PackagesAdapter(CandidateDetailedActivity.this, pContent);
                                                precyclerview.setAdapter(pAdapter);

                                            } else {


                                                Utils.custoAlert(CandidateDetailedActivity.this, packagesModel.getMessage());
                                            }
                                        }
                                    }
                                    Utils.hideProgressDialog();
                                }
                            });


                                    DisplayMetrics displayMetrics = new DisplayMetrics();
                                    getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                                    int displayWidth = displayMetrics.widthPixels;
                                    int displayHeight = displayMetrics.heightPixels;
                                    WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                                    layoutParams.copyFrom(dialog.getWindow().getAttributes());
                                    // Set the alert dialog window width and height
                                    // Set alert dialog width equal to screen width 90%
                                    // int dialogWindowWidth = (int) (displayWidth * 0.9f);
                                    // Set alert dialog height equal to screen height 90%
                                    // int dialogWindowHeight = (int) (displayHeight * 0.9f);
                                    // Set alert dialog width equal to screen width 70%
                                    int dialogWindowWidth = (int) (displayWidth * 0.81f);
                                    // Set alert dialog height equal to screen height 70%
                                    //int dialogWindowHeight = (int) (displayHeight * 0.5f);
                                    // Set the width and height for the layout parameters
                                    // This will bet the width and height of alert dialog
                                    layoutParams.width = dialogWindowWidth;
                                    //  layoutParams.height = dialogWindowHeight;
                                    // Apply the newly created layout parameters to the alert dialog window
                                    dialog.getWindow().setAttributes(layoutParams);

                    dialog.show();

//                                   candidateimg.setImageResource(cdetail.getData().get(0).getImage());getImag e
                                } else {
                                    imagePhone.setVisibility(View.VISIBLE);
                                    imageEmail.setVisibility(View.VISIBLE);
                                    txtContact.setVisibility(View.VISIBLE);
                                    txtEmail.setVisibility(View.VISIBLE);
                                    //Toast.makeText(CandidateDetailedActivity.this, vdeduct.getMessage(), Toast.LENGTH_SHORT).show();
                                    //Utils.custoAlert(CandidateDetailedActivity.this, vdeduct.getMessage());


                                }
                            }
                        }
                    }
                });
    }


}
