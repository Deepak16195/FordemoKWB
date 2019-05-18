package com.rdave.kamwaliapplication.Model;

import java.util.List;

public class Language {


    /**
     * Success : true
     * Message : Success
     * Data : [{"LanguageId":2,"LanguageName":"English"},{"LanguageId":1,"LanguageName":"Hindi"},{"LanguageId":4,"LanguageName":"Marathi"}]
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
         * LanguageId : 2
         * LanguageName : English
         */

        private int LanguageId;
        private String LanguageName;

        public int getLanguageId() {
            return LanguageId;
        }

        public void setLanguageId(int LanguageId) {
            this.LanguageId = LanguageId;
        }

        public String getLanguageName() {
            return LanguageName;
        }

        public void setLanguageName(String LanguageName) {
            this.LanguageName = LanguageName;
        }
    }
}
