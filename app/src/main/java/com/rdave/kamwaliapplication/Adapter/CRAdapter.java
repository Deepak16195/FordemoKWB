package com.rdave.kamwaliapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.rdave.kamwaliapplication.Activity.CandidateDetailedActivity;
import com.rdave.kamwaliapplication.Activity.LoginActivity;
import com.rdave.kamwaliapplication.Activity.PackagesContent;
import com.rdave.kamwaliapplication.Model.Candidate;
import com.rdave.kamwaliapplication.R;
import com.rdave.kamwaliapplication.Utility.SharePrefarence;
import com.rdave.kamwaliapplication.Utility.Utils;

import java.util.ArrayList;
import java.util.List;

public class CRAdapter extends RecyclerView.Adapter<CRAdapter.Hholder> {

    public Context context;
    PackagesAdapter pAdapter;
    List<Candidate.DataBean> datta;
    List<PackagesContent.DataEntity> pContent = new ArrayList<>();
    String Logined = "0";
    String LoginedAs = "0";
    String UserID = "0";

    public CRAdapter(Context context, List<Candidate.DataBean> datta) {
        this.context = context;
        this.datta = datta;
    }

    @NonNull
    @Override
    public Hholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v;
        v = LayoutInflater.from(context).inflate(R.layout.cl_content,viewGroup,false);
        Hholder holder = new Hholder(v);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull Hholder hholder, final int position) {

       // hholder.hImage.setImageResource(datta.get(position).getImage());
        hholder.tName.setText(datta.get(position).getFullName());
        hholder.tProfile.setText(datta.get(position).getCategoryName());
        hholder.tExprience.setText(datta.get(position).getWorkingSince());
        hholder.tLocation.setText(datta.get(position).getServiceCity());
        hholder.txt_upload.setText(datta.get(position).getModify()+"");


        hholder.btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LoginedAs = SharePrefarence.getmInstance(context).getLoginAs();
                Logined = SharePrefarence.getmInstance(context).getLogined();

                if(Logined.equalsIgnoreCase("1"))
                {
                    if (LoginedAs.equalsIgnoreCase("Employer")) {
                        Intent intent = new Intent (v.getContext(), CandidateDetailedActivity.class);
                        intent.putExtra("ID",datta.get(position).getCandidateId()+"");
                        context.startActivity(intent);
                    }
                    else{
                        Utils.customMessage(context, "You Are Not Eligible For This Feature");
                    }

                }
                else
                {
                    if(Logined.equalsIgnoreCase("0")) {
                        Intent intent = new Intent(v.getContext(), LoginActivity.class);
                        context.startActivity(intent);
                        ((AppCompatActivity) context).finish();
                    }
                }

            /*    LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
                View row = inflater.inflate(R.layout.activity_packages, null);
                final Dialog dialog = new Dialog(row.getContext());
                dialog.setContentView(row);
                dialog.setCancelable(true);

                final RecyclerView precyclerview = (RecyclerView) row.findViewById(R.id.package_reclerlist);
                precyclerview.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));




                    Utils.showProgressDialog(context);
                    ApiUtils.getAPIService().getPackages().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Subscriber<PackagesContent>() {
                                @Override
                                public void onCompleted() {
                                    Utils.hideProgressDialog();

                                }

                                @Override
                                public void onError(Throwable e) {
                                    Utils.hideProgressDialog();
                                    Utils.customMessage(context, e.getMessage().toString());
                                }

                                @Override
                                public void onNext(PackagesContent packagesModel) {
                                    if (packagesModel.getSuccess()) {
                                        if (!packagesModel.getMessage().equalsIgnoreCase("Server Error!")) {
                                            if (packagesModel.getMessage().equalsIgnoreCase("Success")) {
                                                pContent = packagesModel.getData();
                                                pAdapter = new PackagesAdapter(context, pContent);
                                                precyclerview.setAdapter(pAdapter);

                                            } else {

                                                Utils.custoAlert(context, packagesModel.getMessage());
                                            }
                                        }
                                    }
                                }
                            });




                    dialog.show();*/

            }
        });

    }

    @Override
    public int getItemCount() {
        return datta.size();
    }

    public  class Hholder extends RecyclerView.ViewHolder {


        ImageView hImage;
        Button btnView;
        TextView tName,tProfile,tExprience,tLocation,txt_upload;
        public Hholder(@NonNull View itemView) {
            super(itemView);

            tName = (TextView) itemView.findViewById(R.id.hname);
            tProfile = (TextView) itemView.findViewById(R.id.tvprofile);
            tExprience = (TextView) itemView.findViewById(R.id.tvexp);
            tLocation = (TextView) itemView.findViewById(R.id.location);
            hImage = (ImageView) itemView.findViewById(R.id.himage);
            btnView=(Button) itemView.findViewById(R.id.btview);
            txt_upload= (TextView) itemView.findViewById(R.id.txt_upload);



        }
    }
}
