package com.rdave.kamwaliapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.rdave.kamwaliapplication.Activity.CCAvenueWebViewAcitivty;
import com.rdave.kamwaliapplication.Activity.LoginActivity;
import com.rdave.kamwaliapplication.Activity.PackagesContent;
import com.rdave.kamwaliapplication.Model.SaveEmpPackage;
import com.rdave.kamwaliapplication.R;
import com.rdave.kamwaliapplication.Utility.SharePrefarence;
import com.rdave.kamwaliapplication.Utility.Utils;
import com.rdave.kamwaliapplication.avenues.lib.utility.AvenuesParams;
import com.rdave.kamwaliapplication.avenues.lib.utility.ServiceUtility;
import com.rdave.kamwaliapplication.remote.ApiUtils;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PackagesAdapter extends RecyclerView.Adapter<PackagesAdapter.Pkholder> {

    public Context pContext;
    List<PackagesContent.DataEntity> pdatta;
    String UserID = "0";
    String Logined = "0";
    String LoginedAs = "0";
    String UserName = "0";
    String UserEmail = "0";
    String UserNumber = "0";

    public PackagesAdapter(Context pContext, List<PackagesContent.DataEntity> pdatta) {
        this.pContext = pContext;
        this.pdatta = pdatta;
    }

    @NonNull
    @Override
    public Pkholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v;
        v = LayoutInflater.from(pContext).inflate(R.layout.packages_content,viewGroup,false);
        Pkholder pholder = new Pkholder(v);
        return pholder;

    }

    @Override
    public void onBindViewHolder(@NonNull Pkholder pkholder, final int position) {
        pkholder.tvTitle.setText(pdatta.get(position).getPackageName());
        pkholder.tvView.setText(pdatta.get(position).getTotalViewCount()+"");
        pkholder.tvPost.setText(pdatta.get(position).getTotalPostCount()+"");
        pkholder.tvDiscript.setText(pdatta.get(position).getDescription());
        pkholder.tvValid.setText(pdatta.get(position).getValidFor()+"");
        pkholder.tvRupees.setText(pdatta.get(position).getPrice()+"");
        pkholder.btnbuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserID = SharePrefarence.getmInstance(pContext).getUser_Id();
                LoginedAs = SharePrefarence.getmInstance(pContext).getLoginAs();
                Logined = SharePrefarence.getmInstance(pContext).getLogined();



                if (Logined.equalsIgnoreCase("1"))
                {
                    if (LoginedAs.equalsIgnoreCase("Employer")) {
                        saveEmpPackd(UserID,pdatta.get(position).getPackageId()+"",pdatta.get(position).getPrice()+"");
                    }
                    if (LoginedAs.equalsIgnoreCase("Candidate")) {
                        Utils.customMessage(pContext,"You Are Not Eligible For This Feature");
                    }
                    if (LoginedAs.equalsIgnoreCase("Agent")) {
                        Utils.customMessage(pContext,"You Are Not Eligible For This Feature");
                    }


                }

                else{
                    Intent log = new Intent(v.getContext(), LoginActivity.class);
//                        log.putExtra("cand",);
                    log.putExtra("UType","Employer");
                    pContext.startActivity(log);

                }



            }
        });


    }



    @Override
    public int getItemCount() {
        return pdatta.size();
    }

    public static class Pkholder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvView, tvPost, tvDiscript, tvValid, tvRupees;
        Button btnbuy;

        public Pkholder(@NonNull View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvView = (TextView) itemView.findViewById(R.id.tv_view);
            tvPost = (TextView) itemView.findViewById(R.id.tv_Post);
            tvDiscript = (TextView) itemView.findViewById(R.id.tv_discript);
            tvValid = (TextView) itemView.findViewById(R.id.tv_Valid);
            tvRupees = (TextView) itemView.findViewById(R.id.tv_Rs);
            btnbuy = (Button) itemView.findViewById(R.id.buynow);


        }
    }
        private void saveEmpPackd(String EmplrId,String PkgId,final String aPrice) {
            Utils.showProgressDialog(pContext);
            ApiUtils.getAPIService().saveEmpPackage(EmplrId,PkgId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<SaveEmpPackage>() {
                        @Override
                        public void onCompleted() {
                            Utils.hideProgressDialog();
                        }

                        @Override
                        public void onError(Throwable e) {
                            Utils.hideProgressDialog();
                            Utils.customMessage(pContext, e.getMessage().toString());
                        }

                        @Override
                        public void onNext(SaveEmpPackage sPck) {
                            if (sPck.getSuccess()) {
                                if (!sPck.getMessage().equalsIgnoreCase("Server Error!")) {

                                    UserName = SharePrefarence.getmInstance(pContext).getUserName();
                                    UserEmail = SharePrefarence.getmInstance(pContext).getEmailid();
                                    UserNumber = SharePrefarence.getmInstance(pContext).getMobile();

                                                    String vAccessCode = ServiceUtility.chkNull(Utils.avenue_AccessCode).toString().trim();
                String vMerchantId = ServiceUtility.chkNull(Utils.avenue_MerchantId).toString().trim();
                String vCurrency = ServiceUtility.chkNull(Utils.avenue_Currency).toString().trim();
                String vAmount = ServiceUtility.chkNull(aPrice).toString().trim();
                //String vAmount = "1";
                if (!vAccessCode.equals("") && !vMerchantId.equals("") && !vCurrency.equals("") && !vAmount.equals("")) {
                    Intent intent = new Intent(pContext, CCAvenueWebViewAcitivty.class);
                    intent.putExtra(AvenuesParams.ACCESS_CODE, ServiceUtility.chkNull(vAccessCode).toString().trim()+"");
                    intent.putExtra(AvenuesParams.MERCHANT_ID, ServiceUtility.chkNull(vMerchantId).toString().trim()+"");
                    intent.putExtra(AvenuesParams.ORDER_ID, ServiceUtility.chkNull(sPck.getMessage()).toString().trim()+"");
                    intent.putExtra(AvenuesParams.CURRENCY, ServiceUtility.chkNull(vCurrency).toString().trim()+"");
                    intent.putExtra(AvenuesParams.AMOUNT, ServiceUtility.chkNull(vAmount).toString().trim()+"");
                    intent.putExtra(AvenuesParams.billing_name, ServiceUtility.chkNull(UserName).toString().trim() + " " + ServiceUtility.chkNull("").toString().trim());
                    intent.putExtra(AvenuesParams.billing_address, ServiceUtility.chkNull("Mumbai").toString().trim()+"");
                    intent.putExtra(AvenuesParams.billing_zip, ServiceUtility.chkNull("400101").toString().trim()+"");
                    intent.putExtra(AvenuesParams.billing_city, ServiceUtility.chkNull("Mumbai").toString().trim());
                    intent.putExtra(AvenuesParams.billing_state, ServiceUtility.chkNull("Maharashtra").toString().trim());
                    intent.putExtra(AvenuesParams.billing_country, "India");
                    intent.putExtra(AvenuesParams.billing_tel, ServiceUtility.chkNull(UserNumber).toString().trim()+"");
                    intent.putExtra(AvenuesParams.billing_email, ServiceUtility.chkNull(UserEmail).toString().trim());
                    intent.putExtra(AvenuesParams.REDIRECT_URL, ServiceUtility.chkNull(Utils.avenue_RedirectUrl).toString().trim());
                    intent.putExtra(AvenuesParams.CANCEL_URL, ServiceUtility.chkNull(Utils.avenue_CancelUrl).toString().trim());
                    intent.putExtra(AvenuesParams.RSA_KEY_URL, ServiceUtility.chkNull(Utils.avenue_RSAKeyUrl).toString().trim());

                    intent.putExtra("OrgOrder", sPck.getMessage()+"");



                    pContext.startActivity(intent);
                }




                                }else {
                                        //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                                        Utils.custoAlert(pContext, sPck.getMessage());
                                    }

                            }
                        }

                    });
        }

    }

