package com.rdave.kamwaliapplication.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rdave.kamwaliapplication.Model.EmployerPayment;
import com.rdave.kamwaliapplication.R;

import java.util.List;

public class EmployerPaymentAdapter extends RecyclerView.Adapter<EmployerPaymentAdapter.JLholder> {

    Context jLContext;
    private List<EmployerPayment.DataBean> jLDatta;
    String Logined = "0";
    String LoginedAs = "0";
    String Username = "0";
    String Useremail = "0";
    String UserID = "0";

    public EmployerPaymentAdapter(Context jLContext, List<EmployerPayment.DataBean> jLDatta) {
        this.jLContext = jLContext;
        this.jLDatta = jLDatta;
    }

    @NonNull
    @Override
    public JLholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v;
        v = LayoutInflater.from(jLContext).inflate(R.layout.employer_payment_history_row,viewGroup,false);
        JLholder jlholder = new JLholder(v);
        return jlholder;


    }

    @Override
    public void onBindViewHolder(@NonNull JLholder jLholder, final int position) {




        jLholder.txtRefNo.setText(jLDatta.get(position).getRefNo()+"");
        jLholder.txtPackageName.setText(jLDatta.get(position).getPackageName()+"");
        jLholder.txtAmount.setText(jLDatta.get(position).getAmount()+"");
        jLholder.txtGSTPer.setText(jLDatta.get(position).getGSTPer()+"");
        jLholder.txtTaxAmount.setText(jLDatta.get(position).getTaxAmount()+"");
        jLholder.txtTotal.setText(jLDatta.get(position).getTotal()+"");
        jLholder.txtBuyDate.setText(jLDatta.get(position).getBuyDate()+"");
        jLholder.txtStatus.setText(jLDatta.get(position).getStatus()+"");
    }

    @Override
    public int getItemCount() {
        return jLDatta.size();
    }

    public static class JLholder extends RecyclerView.ViewHolder {

        TextView txtRefNo,txtPackageName,txtAmount,txtGSTPer,txtTaxAmount,txtTotal,txtBuyDate,txtStatus;




        public JLholder(@NonNull View itemView) {
            super(itemView);

            txtRefNo = (TextView) itemView.findViewById(R.id.txtRefNo);
            txtPackageName = (TextView) itemView.findViewById(R.id.txtPackageName);
            txtAmount= (TextView) itemView.findViewById(R.id.txtAmount);
            txtGSTPer = (TextView) itemView.findViewById(R.id.txtGSTPer);
            txtTaxAmount = (TextView) itemView.findViewById(R.id.txtTaxAmount);
            txtTotal = (TextView) itemView.findViewById(R.id.txtTotal);
            txtBuyDate = (TextView) itemView.findViewById(R.id.txtBuyDate);
            txtStatus = (TextView) itemView.findViewById(R.id.txtStatus);




        }
    }


}
