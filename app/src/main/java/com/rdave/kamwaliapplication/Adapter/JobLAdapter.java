package com.rdave.kamwaliapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.rdave.kamwaliapplication.Activity.LoginActivity;
import com.rdave.kamwaliapplication.Model.GetJob;
import com.rdave.kamwaliapplication.R;
import com.rdave.kamwaliapplication.Utility.SharePrefarence;
import com.rdave.kamwaliapplication.Utility.Utils;
import com.rdave.kamwaliapplication.remote.ApiUtils;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class JobLAdapter extends RecyclerView.Adapter<JobLAdapter.JLholder> {

    Context jLContext;
    private List<GetJob.DataEntity> jLDatta;
    String Logined = "0";
    String LoginedAs = "0";
    String Username = "0";
    String Useremail = "0";
    String UserID = "0";
    String astr;


    public JobLAdapter(Context jLContext, List<GetJob.DataEntity> jLDatta) {
        this.jLContext = jLContext;
        this.jLDatta = jLDatta;
    }

    @NonNull
    @Override
    public JLholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v;
        v = LayoutInflater.from(jLContext).inflate(R.layout.j10_content,viewGroup,false);
        JLholder jlholder = new JLholder(v);
        return jlholder;


    }

    @Override
    public void onBindViewHolder(@NonNull JLholder jLholder, final int position) {

        jLholder.jlCategry.setText(jLDatta.get(position).getCategoryName());

       /* jLholder.jlState.setText(jLDatta.get(position).getJobState());

        jLholder.jlLandmark.setText(jLDatta.get(position).getJobLocation());
        jLholder.jlQualify.setText(jLDatta.get(position).getEducation());*/
       jLholder.jlName.setText(jLDatta.get(position).getSubcategoryName()+"");
        jLholder.txt_time.setText(jLDatta.get(position).getJobTime()+ " Hours");
        jLholder.jlCity.setText(jLDatta.get(position).getJobCity());
        //jLholder.jlTime.setText(jLDatta.get(position).getJobTime());
        jLholder.jlSalary.setText(jLDatta.get(position).getGivenSalary()+"");


        jLholder. idApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                LoginedAs = SharePrefarence.getmInstance(jLContext).getLoginAs();
                Logined = SharePrefarence.getmInstance(jLContext).getLogined();
                Username = SharePrefarence.getmInstance(jLContext).getUserName();
                Useremail = SharePrefarence.getmInstance(jLContext).getEmailid();
                UserID = SharePrefarence.getmInstance(jLContext).getUser_Id();

                if (Logined.equalsIgnoreCase("1"))
                {
                    if (LoginedAs.equalsIgnoreCase("Candidate")) {
                        ApplyJob(UserID,jLDatta.get(position).getJobId()+"");
                    }
                    if (LoginedAs.equalsIgnoreCase("Employer")) {
                        Utils.customMessage(jLContext,"You Are Not Eligible For This Feature");
                    }
                    if (LoginedAs.equalsIgnoreCase("Agent")) {
                        Utils.customMessage(jLContext,"You Are Not Eligible For This Feature");
                    }


                    }

                else{
                        Intent log = new Intent(v.getContext(), LoginActivity.class);
//                        log.putExtra("cand",);
                    log.putExtra("UType","Candidate");
                        jLContext.startActivity(log);

                }

        /*        final Dialog dialog = new Dialog(v.getContext());
                dialog.setContentView(R.layout.activity_packages);
                dialog.setCancelable(true);


                dialog.show();*/


            }



//            Intent intent = new Intent (v.getContext(), PackagesActivity.class);
//                jLContext.startActivity(intent);
//
//
//            }
        });
//

    }

    @Override
    public int getItemCount() {
        return jLDatta.size();
    }

    public static class JLholder extends RecyclerView.ViewHolder {

        TextView jlCategry,jlState,jlCity,jlLandmark,jlTime,jlQualify,jlName,jlSalary,txt_time;
        ImageView jlImage;
        Button idApply;


        public JLholder(@NonNull View itemView) {
            super(itemView);
            jlImage = (ImageView) itemView.findViewById(R.id.jl_image);
            jlCategry = (TextView) itemView.findViewById(R.id.job_category);
            jlCity = (TextView) itemView.findViewById(R.id.txtCity);
          /*  jlState = (TextView) itemView.findViewById(R.id.jl_state);

            jlLandmark = (TextView) itemView.findViewById(R.id.jl_landmark);
            jlTime = (TextView) itemView.findViewById(R.id.jl_type);*/
            //jlQualify = (TextView) itemView.findViewById(R.id.jl_qalify);
            jlName = (TextView) itemView.findViewById(R.id.c_name);
            txt_time= (TextView) itemView.findViewById(R.id.txt_time);
            jlSalary = (TextView) itemView.findViewById(R.id.job_salary);
            idApply = (Button) itemView.findViewById(R.id.btview);
//            astr = idApply.getText().toString();

        }
    }

    private void ApplyJob(String aCandId,String aJobID) {
        Utils.showProgressDialog(jLContext);
        ApiUtils.getAPIService().Applyjob(aCandId,aJobID).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetJob>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(jLContext, e.getMessage().toString());
                    }

                    @Override
                    public void onNext(GetJob currencyModel) {
                        if (currencyModel.getSuccess()) {
                            if (!currencyModel.getMessage().equalsIgnoreCase("Server Error!")) {
                                if (currencyModel.getMessage().equalsIgnoreCase("Save")) {
                                    Utils.customMessage(jLContext,"Request Applied Succesffuly");

                                } else {
                                    //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                                    Utils.custoAlert(jLContext, currencyModel.getMessage());
                                }
                            }
                        }
                    }
                });
    }
}
