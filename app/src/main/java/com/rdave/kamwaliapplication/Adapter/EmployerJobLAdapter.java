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

import com.rdave.kamwaliapplication.Activity.PostJobs;
import com.rdave.kamwaliapplication.Model.EmoloyerPostedJobs;
import com.rdave.kamwaliapplication.R;

import java.util.List;

public class EmployerJobLAdapter extends RecyclerView.Adapter<EmployerJobLAdapter.JLholder> {

    Context jLContext;
    private List<EmoloyerPostedJobs.Data> jLDatta;
    String Logined = "0";
    String LoginedAs = "0";
    String Username = "0";
    String Useremail = "0";
    String UserID = "0";

    public EmployerJobLAdapter(Context jLContext, List<EmoloyerPostedJobs.Data> jLDatta) {
        this.jLContext = jLContext;
        this.jLDatta = jLDatta;
    }

    @NonNull
    @Override
    public JLholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v;
        v = LayoutInflater.from(jLContext).inflate(R.layout.employer_post_job,viewGroup,false);
        JLholder jlholder = new JLholder(v);
        return jlholder;


    }

    @Override
    public void onBindViewHolder(@NonNull JLholder jLholder, final int position) {


        jLholder.jl_category.setText(jLDatta.get(position).getCategoryName());
        jLholder.jl_name.setText(jLDatta.get(position).getSubcategoryName()+"");
        jLholder.jl_State.setText(jLDatta.get(position).getJobState()+"");
        jLholder.jl_city.setText(jLDatta.get(position).getJobCity()+"");
        jLholder.jl_location.setText(jLDatta.get(position).getJobLocation()+"");
        jLholder.jl_time.setText(jLDatta.get(position).getJobTime()+"");
        jLholder.jl_qalify.setText(jLDatta.get(position).getEducation()+"");
        jLholder.jl_language.setText(jLDatta.get(position).getLanguageName()+"");
        jLholder.jl_salary.setText(jLDatta.get(position).getGivenSalary()+"");
        jLholder.txt_upload.setText(jLDatta.get(position).getPostedDate()+"");
        jLholder.txt_Desciption.setText(jLDatta.get(position).getDescription()+"");
        jLholder.btview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), PostJobs.class);
                intent.putExtra("ID",jLDatta.get(position).getJobId()+"");
                jLContext.startActivity(intent);

            }
        });







    }

    @Override
    public int getItemCount() {
        return jLDatta.size();
    }

    public static class JLholder extends RecyclerView.ViewHolder {

        TextView jl_category,jl_name,jl_State,jl_city,jl_location,jl_time,jl_qalify,jl_language,jl_salary,txt_upload,txt_Desciption;
        Button btview;



        public JLholder(@NonNull View itemView) {
            super(itemView);

            jl_category = (TextView) itemView.findViewById(R.id.jl_category);
            jl_name = (TextView) itemView.findViewById(R.id.jl_name);
            jl_State= (TextView) itemView.findViewById(R.id.jl_State);
            jl_city = (TextView) itemView.findViewById(R.id.jl_city);

            jl_location= (TextView) itemView.findViewById(R.id.jl_location);
            jl_time= (TextView) itemView.findViewById(R.id.jl_time);
            jl_qalify= (TextView) itemView.findViewById(R.id.jl_qalify);
            jl_language= (TextView) itemView.findViewById(R.id.jl_language);
            jl_salary= (TextView) itemView.findViewById(R.id.jl_salary);
            txt_upload= (TextView) itemView.findViewById(R.id.txt_upload);
            txt_Desciption= (TextView) itemView.findViewById(R.id.txt_Desciption);
            btview= (Button) itemView.findViewById(R.id.btview);


        }
    }


}
