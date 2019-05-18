package com.rdave.kamwaliapplication.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rdave.kamwaliapplication.Model.AppliedJob;
import com.rdave.kamwaliapplication.R;

import java.util.List;

public class AppliedJobLAdapter extends RecyclerView.Adapter<AppliedJobLAdapter.JLholder> {

    Context jLContext;
    private List<AppliedJob.DataBean> jLDatta;
    String Logined = "0";
    String LoginedAs = "0";
    String Username = "0";
    String Useremail = "0";
    String UserID = "0";

    public AppliedJobLAdapter(Context jLContext, List<AppliedJob.DataBean> jLDatta) {
        this.jLContext = jLContext;
        this.jLDatta = jLDatta;
    }

    @NonNull
    @Override
    public JLholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v;
        v = LayoutInflater.from(jLContext).inflate(R.layout.employer_appliedjob,viewGroup,false);
        JLholder jlholder = new JLholder(v);
        return jlholder;


    }

    @Override
    public void onBindViewHolder(@NonNull JLholder jLholder, final int position) {

        jLholder.jlCategry.setText(jLDatta.get(position).getCategoryName()+"");
        jLholder.jlName.setText(jLDatta.get(position).getFullName()+"");
        jLholder.jl_mobileno.setText(jLDatta.get(position).getMobileNo()+"");
        jLholder.jl_SubCate.setText(jLDatta.get(position).getSubcategoryName()+"");
        jLholder.jl_postDate.setText(jLDatta.get(position).getPostedDate()+"");
        jLholder.jl_ApplyDate.setText(jLDatta.get(position).getApplyDate()+"");
        jLholder.txt_Desciption.setText(jLDatta.get(position).getDescription()+"");





    }

    @Override
    public int getItemCount() {
        return jLDatta.size();
    }

    public static class JLholder extends RecyclerView.ViewHolder {

        TextView jlCategry,jl_mobileno,jl_SubCate,jl_postDate,jl_ApplyDate,txt_Desciption,jlName;
        ImageView jlImage;


        public JLholder(@NonNull View itemView) {
            super(itemView);

            jlCategry = (TextView) itemView.findViewById(R.id.jl_category);
            jlName = (TextView) itemView.findViewById(R.id.jl_name);
            jl_mobileno= (TextView) itemView.findViewById(R.id.jl_mobileno);
            jl_SubCate = (TextView) itemView.findViewById(R.id.jl_SubCate);
            jlImage = (ImageView) itemView.findViewById(R.id.jl_image);
            jl_postDate= (TextView) itemView.findViewById(R.id.jl_postDate);
            txt_Desciption= (TextView) itemView.findViewById(R.id.txt_Desciption);
          /*  jlState = (TextView) itemView.findViewById(R.id.jl_state);

            jlLandmark = (TextView) itemView.findViewById(R.id.jl_landmark);
            jlTime = (TextView) itemView.findViewById(R.id.jl_type);*/
            //jlQualify = (TextView) itemView.findViewById(R.id.jl_qalify);

            jl_ApplyDate= (TextView) itemView.findViewById(R.id.jl_ApplyDate);


        }
    }


}
