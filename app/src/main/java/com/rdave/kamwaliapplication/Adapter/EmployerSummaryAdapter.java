package com.rdave.kamwaliapplication.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rdave.kamwaliapplication.Model.EmployerSummary;
import com.rdave.kamwaliapplication.R;

import java.util.List;

public class EmployerSummaryAdapter extends RecyclerView.Adapter<EmployerSummaryAdapter.JLholder> {

    Context jLContext;
    private List<EmployerSummary.DataBean> jLDatta;
    String Logined = "0";
    String LoginedAs = "0";
    String Username = "0";
    String Useremail = "0";
    String UserID = "0";

    public EmployerSummaryAdapter(Context jLContext, List<EmployerSummary.DataBean> jLDatta) {
        this.jLContext = jLContext;
        this.jLDatta = jLDatta;
    }

    @NonNull
    @Override
    public JLholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v;
        v = LayoutInflater.from(jLContext).inflate(R.layout.employer_account_summary_row,viewGroup,false);
        JLholder jlholder = new JLholder(v);
        return jlholder;


    }

    @Override
    public void onBindViewHolder(@NonNull JLholder jLholder, final int position) {
        jLholder.txtViewCount.setText(jLDatta.get(position).getViewCount()+"");
        jLholder.txtPostCount.setText(jLDatta.get(position).getPostCount()+"");

        jLholder.txtType.setText(jLDatta.get(position).getType()+"");
        jLholder.txtCountDate.setText(jLDatta.get(position).getCountDate()+"");
    }

    @Override
    public int getItemCount() {
        return jLDatta.size();
    }

    public static class JLholder extends RecyclerView.ViewHolder {

        TextView txtViewCount,txtPostCount,txtType,txtCountDate;




        public JLholder(@NonNull View itemView) {
            super(itemView);

            txtViewCount = (TextView) itemView.findViewById(R.id.txtViewCount);
            txtPostCount = (TextView) itemView.findViewById(R.id.txtPostCount);
            txtType= (TextView) itemView.findViewById(R.id.txtType);
            txtCountDate = (TextView) itemView.findViewById(R.id.txtCountDate);




        }
    }


}
