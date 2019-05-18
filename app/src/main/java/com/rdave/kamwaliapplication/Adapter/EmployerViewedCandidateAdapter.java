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

import com.rdave.kamwaliapplication.Activity.CandidateDetailedActivity;
import com.rdave.kamwaliapplication.Model.EmployerViewedCandidateModel;
import com.rdave.kamwaliapplication.R;

import java.util.List;

public class EmployerViewedCandidateAdapter extends RecyclerView.Adapter<EmployerViewedCandidateAdapter.JLholder> {

    Context jLContext;
    private List<EmployerViewedCandidateModel.Data> jLDatta;
    String Logined = "0";
    String LoginedAs = "0";
    String Username = "0";
    String Useremail = "0";
    String UserID = "0";

    public EmployerViewedCandidateAdapter(Context jLContext, List<EmployerViewedCandidateModel.Data> jLDatta) {
        this.jLContext = jLContext;
        this.jLDatta = jLDatta;
    }

    @NonNull
    @Override
    public JLholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v;
        v = LayoutInflater.from(jLContext).inflate(R.layout.empcandidate_content,viewGroup,false);
        JLholder jlholder = new JLholder(v);
        return jlholder;


    }

    @Override
    public void onBindViewHolder(@NonNull final JLholder jLholder, final int position) {



        jLholder.hname.setText(jLDatta.get(position).getFullName());
        jLholder.gender.setText(jLDatta.get(position).getGender()+"");
        jLholder.categoryNm.setText(jLDatta.get(position).getCategoryName()+"");
        jLholder.subCategorynm.setText(jLDatta.get(position).getSubcategoryName()+"");
        jLholder.num.setText(jLDatta.get(position).getMobileNo()+"");
        jLholder.txt_upload.setText("Viewed On "+jLDatta.get(position).getViewedDate()+"");



        jLholder.btview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), CandidateDetailedActivity.class);
                intent.putExtra("View","Emp");
                intent.putExtra("ID",jLDatta.get(jLholder.getAdapterPosition()).getCandId()+"");
                jLContext.startActivity(intent);

            }
        });







    }

    @Override
    public int getItemCount() {
        return jLDatta.size();
    }

    public static class JLholder extends RecyclerView.ViewHolder {

        TextView hname,gender,categoryNm,subCategorynm,num,txt_upload;
        Button btview;



        public JLholder(@NonNull View itemView) {
            super(itemView);

            hname = (TextView) itemView.findViewById(R.id.hname);
            gender = (TextView) itemView.findViewById(R.id.gender);
            categoryNm= (TextView) itemView.findViewById(R.id.categoryNm);
            subCategorynm = (TextView) itemView.findViewById(R.id.subCategorynm);

            num= (TextView) itemView.findViewById(R.id.num);
            txt_upload= (TextView) itemView.findViewById(R.id.txt_upload);

            btview= (Button) itemView.findViewById(R.id.btview);


        }
    }


}
