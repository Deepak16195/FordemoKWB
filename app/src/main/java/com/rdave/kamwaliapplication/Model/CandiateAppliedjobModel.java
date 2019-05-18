package com.rdave.kamwaliapplication.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CandiateAppliedjobModel {

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
        @SerializedName("MobileNo")
        private String MobileNo;
        @Expose
        @SerializedName("EmployerName")
        private String EmployerName;
        @Expose
        @SerializedName("ApplyDate")
        private String ApplyDate;
        @Expose
        @SerializedName("PostedDate")
        private String PostedDate;
        @Expose
        @SerializedName("Description")
        private String Description;
        @Expose
        @SerializedName("GivenSalary")
        private int GivenSalary;
        @Expose
        @SerializedName("JobTime")
        private String JobTime;
        @Expose
        @SerializedName("JobCity")
        private String JobCity;
        @Expose
        @SerializedName("CategoryName")
        private String CategoryName;

        public String getMobileNo() {
            return MobileNo;
        }

        public void setMobileNo(String MobileNo) {
            this.MobileNo = MobileNo;
        }

        public String getEmployerName() {
            return EmployerName;
        }

        public void setEmployerName(String EmployerName) {
            this.EmployerName = EmployerName;
        }

        public String getApplyDate() {
            return ApplyDate;
        }

        public void setApplyDate(String ApplyDate) {
            this.ApplyDate = ApplyDate;
        }

        public String getPostedDate() {
            return PostedDate;
        }

        public void setPostedDate(String PostedDate) {
            this.PostedDate = PostedDate;
        }

        public String getDescription() {
            return Description;
        }

        public void setDescription(String Description) {
            this.Description = Description;
        }

        public int getGivenSalary() {
            return GivenSalary;
        }

        public void setGivenSalary(int GivenSalary) {
            this.GivenSalary = GivenSalary;
        }

        public String getJobTime() {
            return JobTime;
        }

        public void setJobTime(String JobTime) {
            this.JobTime = JobTime;
        }

        public String getJobCity() {
            return JobCity;
        }

        public void setJobCity(String JobCity) {
            this.JobCity = JobCity;
        }

        public String getCategoryName() {
            return CategoryName;
        }

        public void setCategoryName(String CategoryName) {
            this.CategoryName = CategoryName;
        }
    }
}
