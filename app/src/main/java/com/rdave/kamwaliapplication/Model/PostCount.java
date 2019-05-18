package com.rdave.kamwaliapplication.Model;

import java.util.List;

public class PostCount {

    /**
     * Success : true
     * Message : Success
     * Data : [{"PostCount":1}]
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
         * PostCount : 1
         */

        private int PostCount;

        public int getPostCount() {
            return PostCount;
        }

        public void setPostCount(int PostCount) {
            this.PostCount = PostCount;
        }
    }
}
