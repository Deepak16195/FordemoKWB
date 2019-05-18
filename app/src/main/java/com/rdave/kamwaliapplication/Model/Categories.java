package com.rdave.kamwaliapplication.Model;

import java.util.List;

public class Categories {


    /**
     * Success : true
     * Message : Success
     * Data : [{"CategoryId":2,"CategoryName":"Baby Sitter"},{"CategoryId":4,"CategoryName":"Cook"},{"CategoryId":5,"CategoryName":"Driver"},{"CategoryId":1,"CategoryName":"Maid"},{"CategoryId":3,"CategoryName":"Patient Care"}]
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
         * CategoryId : 2
         * CategoryName : Baby Sitter
         */

        private int CategoryId;
        private String CategoryName;

        public int getCategoryId() {
            return CategoryId;
        }

        public void setCategoryId(int CategoryId) {
            this.CategoryId = CategoryId;
        }

        public String getCategoryName() {
            return CategoryName;
        }

        public void setCategoryName(String CategoryName) {
            this.CategoryName = CategoryName;
        }
    }
}
