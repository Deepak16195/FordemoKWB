package com.rdave.kamwaliapplication.Model;

import java.util.List;

public class EmployerPayment {

    /**
     * Success : true
     * Message : Success
     * Data : [{"RefNo":"1","PackageName":"Test","Amount":500,"GSTPer":18,"TaxAmount":90,"Total":590,"BuyDate":"2019-05-04","Status":"Paid"}]
     * Status : 200
     */

    private boolean Success;
    private String Message;
    private int Status;
    private List<DataBean> Data;

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean Success) {
        this.Success = Success;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * RefNo : 1
         * PackageName : Test
         * Amount : 500
         * GSTPer : 18
         * TaxAmount : 90
         * Total : 590
         * BuyDate : 2019-05-04
         * Status : Paid
         */

        private String RefNo;
        private String PackageName;
        private double Amount;
        private double GSTPer;
        private double TaxAmount;
        private double Total;
        private String BuyDate;
        private String Status;

        public String getRefNo() {
            return RefNo;
        }

        public void setRefNo(String RefNo) {
            this.RefNo = RefNo;
        }

        public String getPackageName() {
            return PackageName;
        }

        public void setPackageName(String PackageName) {
            this.PackageName = PackageName;
        }

        public double getAmount() {
            return Amount;
        }

        public void setAmount(double Amount) {
            this.Amount = Amount;
        }

        public double getGSTPer() {
            return GSTPer;
        }

        public void setGSTPer(double GSTPer) {
            this.GSTPer = GSTPer;
        }

        public double getTaxAmount() {
            return TaxAmount;
        }

        public void setTaxAmount(double TaxAmount) {
            this.TaxAmount = TaxAmount;
        }

        public double getTotal() {
            return Total;
        }

        public void setTotal(double Total) {
            this.Total = Total;
        }

        public String getBuyDate() {
            return BuyDate;
        }

        public void setBuyDate(String BuyDate) {
            this.BuyDate = BuyDate;
        }

        public String getStatus() {
            return Status;
        }

        public void setStatus(String Status) {
            this.Status = Status;
        }
    }
}
