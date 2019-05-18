package com.rdave.kamwaliapplication.Model;

import java.util.List;

public class PackageCount {


    /**
     * Success : true
     * Message : Success
     * Data : [{"TCreditViewCount":"30","TDebitViewCount":"2","TCreditPostCount":"20","TDebitPostCount":"9","RViewCount":"28","RPostCount":"11"}]
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
         * TCreditViewCount : 30
         * TDebitViewCount : 2
         * TCreditPostCount : 20
         * TDebitPostCount : 9
         * RViewCount : 28
         * RPostCount : 11
         */

        private String TCreditViewCount;
        private String TDebitViewCount;
        private String TCreditPostCount;
        private String TDebitPostCount;
        private String RViewCount;
        private String RPostCount;

        public String getTCreditViewCount() {
            return TCreditViewCount;
        }

        public void setTCreditViewCount(String TCreditViewCount) {
            this.TCreditViewCount = TCreditViewCount;
        }

        public String getTDebitViewCount() {
            return TDebitViewCount;
        }

        public void setTDebitViewCount(String TDebitViewCount) {
            this.TDebitViewCount = TDebitViewCount;
        }

        public String getTCreditPostCount() {
            return TCreditPostCount;
        }

        public void setTCreditPostCount(String TCreditPostCount) {
            this.TCreditPostCount = TCreditPostCount;
        }

        public String getTDebitPostCount() {
            return TDebitPostCount;
        }

        public void setTDebitPostCount(String TDebitPostCount) {
            this.TDebitPostCount = TDebitPostCount;
        }

        public String getRViewCount() {
            return RViewCount;
        }

        public void setRViewCount(String RViewCount) {
            this.RViewCount = RViewCount;
        }

        public String getRPostCount() {
            return RPostCount;
        }

        public void setRPostCount(String RPostCount) {
            this.RPostCount = RPostCount;
        }
    }
}
