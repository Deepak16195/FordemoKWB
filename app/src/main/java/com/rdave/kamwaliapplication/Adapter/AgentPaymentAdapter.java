package com.rdave.kamwaliapplication.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rdave.kamwaliapplication.Model.AgentPayment;
import com.rdave.kamwaliapplication.R;

import java.util.List;

public class AgentPaymentAdapter extends RecyclerView.Adapter<AgentPaymentAdapter.JLholder> {

    Context jLContext;
    private List<AgentPayment.Data> jLDatta;
    String Logined = "0";
    String LoginedAs = "0";
    String Username = "0";
    String Useremail = "0";
    String UserID = "0";

    public AgentPaymentAdapter(Context jLContext, List<AgentPayment.Data> jLDatta) {
        this.jLContext = jLContext;
        this.jLDatta = jLDatta;
    }

    @NonNull
    @Override
    public JLholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v;
        v = LayoutInflater.from(jLContext).inflate(R.layout.agent_payment_history_row,viewGroup,false);
        JLholder jlholder = new JLholder(v);
        return jlholder;


    }

    @Override
    public void onBindViewHolder(@NonNull JLholder jLholder, final int position) {

        jLholder.txtID.setText(jLDatta.get(position).getTransactionId()+"");
        jLholder.txtDate.setText(jLDatta.get(position).getTransactionDate()+"");
        jLholder.txtDrAmt.setText(jLDatta.get(position).getDebitAmount()+"");
        jLholder.txtCrAmt.setText(jLDatta.get(position).getCreditAmount()+"");
        jLholder.txtRemark.setText(jLDatta.get(position).getRemarks()+"");
    }

    @Override
    public int getItemCount() {
        return jLDatta.size();
    }

    public static class JLholder extends RecyclerView.ViewHolder {

        TextView txtID,txtDate,txtDrAmt,txtCrAmt,txtRemark;




        public JLholder(@NonNull View itemView) {
            super(itemView);

            txtID = (TextView) itemView.findViewById(R.id.txtID);
            txtDate = (TextView) itemView.findViewById(R.id.txtDate);
            txtDrAmt= (TextView) itemView.findViewById(R.id.txtDrAmt);
            txtCrAmt = (TextView) itemView.findViewById(R.id.txtCrAmt);
            txtRemark = (TextView) itemView.findViewById(R.id.txtRemark);




        }
    }


}
