package com.rdave.kamwaliapplication.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AgentPayment {

    @Expose
    @SerializedName("Status")
    private int Status;
    @Expose
    @SerializedName("Data")
    private List<Data> Data;
    @Expose
    @SerializedName("Message")
    private String Message;
    @Expose
    @SerializedName("Success")
    private boolean Success;

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public List<Data> getData() {
        return Data;
    }

    public void setData(List<Data> Data) {
        this.Data = Data;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public boolean getSuccess() {
        return Success;
    }

    public void setSuccess(boolean Success) {
        this.Success = Success;
    }

    public static class Data {
        @Expose
        @SerializedName("TransactionDate")
        private String TransactionDate;
        @Expose
        @SerializedName("Remarks")
        private String Remarks;
        @Expose
        @SerializedName("DebitAmount")
        private int DebitAmount;
        @Expose
        @SerializedName("CreditAmount")
        private int CreditAmount;
        @Expose
        @SerializedName("AgentId")
        private String AgentId;
        @Expose
        @SerializedName("TransactionId")
        private int TransactionId;

        public String getTransactionDate() {
            return TransactionDate;
        }

        public void setTransactionDate(String TransactionDate) {
            this.TransactionDate = TransactionDate;
        }

        public String getRemarks() {
            return Remarks;
        }

        public void setRemarks(String Remarks) {
            this.Remarks = Remarks;
        }

        public int getDebitAmount() {
            return DebitAmount;
        }

        public void setDebitAmount(int DebitAmount) {
            this.DebitAmount = DebitAmount;
        }

        public int getCreditAmount() {
            return CreditAmount;
        }

        public void setCreditAmount(int CreditAmount) {
            this.CreditAmount = CreditAmount;
        }

        public String getAgentId() {
            return AgentId;
        }

        public void setAgentId(String AgentId) {
            this.AgentId = AgentId;
        }

        public int getTransactionId() {
            return TransactionId;
        }

        public void setTransactionId(int TransactionId) {
            this.TransactionId = TransactionId;
        }
    }
}
