package com.rdave.kamwaliapplication.Model;

import java.util.List;

public class SubCategory {

    /**
     * Success : true
     * Message : Success
     * Data : [{"SubcategoryId":3,"CategName":"4","SubcategoryName":"Chef"}]
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
         * SubcategoryId : 3
         * CategName : 4
         * SubcategoryName : Chef
         */

        private int SubcategoryId;
        private String CategName;
        private String SubcategoryName;

        public int getSubcategoryId() {
            return SubcategoryId;
        }

        public void setSubcategoryId(int SubcategoryId) {
            this.SubcategoryId = SubcategoryId;
        }

        public String getCategName() {
            return CategName;
        }

        public void setCategName(String CategName) {
            this.CategName = CategName;
        }

        public String getSubcategoryName() {
            return SubcategoryName;
        }

        public void setSubcategoryName(String SubcategoryName) {
            this.SubcategoryName = SubcategoryName;
        }
    }
}
