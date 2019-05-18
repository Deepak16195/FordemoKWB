package com.rdave.kamwaliapplication.Model;

import java.util.List;

public class EmployerSummary {

    /**
     * Success : true
     * Message : Success
     * Data : [{"ViewCount":30,"PostCount":20,"Type":"C","CountDate":"2019-05-04"},{"ViewCount":1,"PostCount":0,"Type":"D","CountDate":"2019-05-04"},{"ViewCount":1,"PostCount":0,"Type":"D","CountDate":"2019-05-04"},{"ViewCount":0,"PostCount":1,"Type":"D","CountDate":"2019-05-05"},{"ViewCount":0,"PostCount":1,"Type":"D","CountDate":"2019-05-05"},{"ViewCount":0,"PostCount":1,"Type":"D","CountDate":"2019-05-05"},{"ViewCount":0,"PostCount":1,"Type":"D","CountDate":"2019-05-05"},{"ViewCount":0,"PostCount":1,"Type":"D","CountDate":"2019-05-05"},{"ViewCount":0,"PostCount":1,"Type":"D","CountDate":"2019-05-05"},{"ViewCount":0,"PostCount":1,"Type":"D","CountDate":"2019-05-05"},{"ViewCount":0,"PostCount":1,"Type":"D","CountDate":"2019-05-05"},{"ViewCount":0,"PostCount":1,"Type":"D","CountDate":"2019-05-05"}]
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
         * ViewCount : 30
         * PostCount : 20
         * Type : C
         * CountDate : 2019-05-04
         */

        private int ViewCount;
        private int PostCount;
        private String Type;
        private String CountDate;

        public int getViewCount() {
            return ViewCount;
        }

        public void setViewCount(int ViewCount) {
            this.ViewCount = ViewCount;
        }

        public int getPostCount() {
            return PostCount;
        }

        public void setPostCount(int PostCount) {
            this.PostCount = PostCount;
        }

        public String getType() {
            return Type;
        }

        public void setType(String Type) {
            this.Type = Type;
        }

        public String getCountDate() {
            return CountDate;
        }

        public void setCountDate(String CountDate) {
            this.CountDate = CountDate;
        }
    }
}
