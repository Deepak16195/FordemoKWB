package com.rdave.kamwaliapplication.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rdave.kamwaliapplication.AdapterContent.J10list_Content;
import com.rdave.kamwaliapplication.R;

import java.util.List;

public class JAdapter extends RecyclerView.Adapter<JAdapter.Jholder> {

    Context jContext;
    List<J10list_Content> jDatta;

    public JAdapter(Context jContext, List<J10list_Content> jDatta) {
        this.jContext = jContext;
        this.jDatta = jDatta;
    }

    @NonNull
    @Override
    public Jholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v;
        v = LayoutInflater.from(jContext).inflate(R.layout.j10_content,viewGroup,false);
        Jholder holder = new Jholder(v);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull Jholder jholder, int position) {
      // jholder.jbImage.setImageResource(jDatta.get(position).getjImage());
       jholder.jbCategry.setText(jDatta.get(position).getjCategory());
       /*jholder.jbState.setText(jDatta.get(position).getjState());
       jholder.jbCity.setText(jDatta.get(position).getjCity());
       jholder.jbLandmark.setText(jDatta.get(position).getjLandmark());
       jholder.jbQualify.setText(jDatta.get(position).getjQualification());*/
       jholder.jbName.setText(jDatta.get(position).getjName());
       jholder.jbTime.setText(jDatta.get(position).getjTime());
       jholder.jbSalary.setText(jDatta.get(position).getjSalary());

    }

    @Override
    public int getItemCount() {
        return jDatta.size();
    }

    public static class Jholder extends RecyclerView.ViewHolder {
        TextView jbCategry,jbState,jbCity,jbLandmark,jbTime,jbQualify,jbName,jbSalary;
        ImageView jbImage;

        public Jholder(@NonNull View itemView) {
            super(itemView);

            jbCategry =(TextView) itemView.findViewById(R.id.job_category);
            /*jbState =(TextView) itemView.findViewById(R.id.state);
            jbCity =(TextView) itemView.findViewById(R.id.city);
            jbLandmark =(TextView) itemView.findViewById(R.id.landmark);
            jbQualify =(TextView) itemView.findViewById(R.id.job_qalify);*/
            jbTime =(TextView) itemView.findViewById(R.id.job_type);
            jbName =(TextView) itemView.findViewById(R.id.c_name);
            jbSalary =(TextView) itemView.findViewById(R.id.job_salary);
            jbImage=(ImageView) itemView.findViewById(R.id.job_image);


        }
    }
}
