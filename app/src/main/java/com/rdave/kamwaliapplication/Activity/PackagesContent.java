package com.rdave.kamwaliapplication.Activity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PackagesContent {


    @Expose
    @SerializedName("Status")
    private int Status;
    @Expose
    @SerializedName("Data")
    private List<DataEntity> Data;
    @Expose
    @SerializedName("Message")
    private String Message;
    @Expose
    @SerializedName("Success")
    private boolean Success;
    String title;
    String tViews;
    String tPost;
    String tDiscript;
    String tValid;
    String tRupees;

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public List<DataEntity> getData() {
        return Data;
    }

    public void setData(List<DataEntity> Data) {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTViews() {
        return tViews;
    }

    public void setTViews(String tViews) {
        this.tViews = tViews;
    }

    public String getTPost() {
        return tPost;
    }

    public void setTPost(String tPost) {
        this.tPost = tPost;
    }

    public String getTDiscript() {
        return tDiscript;
    }

    public void setTDiscript(String tDiscript) {
        this.tDiscript = tDiscript;
    }

    public String getTValid() {
        return tValid;
    }

    public void setTValid(String tValid) {
        this.tValid = tValid;
    }

    public String getTRupees() {
        return tRupees;
    }

    public void setTRupees(String tRupees) {
        this.tRupees = tRupees;
    }

    public static class DataEntity {
        @Expose
        @SerializedName("ValidFor")
        private String ValidFor;
        @Expose
        @SerializedName("Description")
        private String Description;
        @Expose
        @SerializedName("Price")
        private int Price;
        @Expose
        @SerializedName("TotalPostCount")
        private int TotalPostCount;
        @Expose
        @SerializedName("TotalViewCount")
        private int TotalViewCount;
        @Expose
        @SerializedName("PackageName")
        private String PackageName;
        @Expose
        @SerializedName("PackageId")
        private int PackageId;

        public String getValidFor() {
            return ValidFor;
        }

        public void setValidFor(String ValidFor) {
            this.ValidFor = ValidFor;
        }

        public String getDescription() {
            return Description;
        }

        public void setDescription(String Description) {
            this.Description = Description;
        }

        public int getPrice() {
            return Price;
        }

        public void setPrice(int Price) {
            this.Price = Price;
        }

        public int getTotalPostCount() {
            return TotalPostCount;
        }

        public void setTotalPostCount(int TotalPostCount) {
            this.TotalPostCount = TotalPostCount;
        }

        public int getTotalViewCount() {
            return TotalViewCount;
        }

        public void setTotalViewCount(int TotalViewCount) {
            this.TotalViewCount = TotalViewCount;
        }

        public String getPackageName() {
            return PackageName;
        }

        public void setPackageName(String PackageName) {
            this.PackageName = PackageName;
        }

        public int getPackageId() {
            return PackageId;
        }

        public void setPackageId(int PackageId) {
            this.PackageId = PackageId;
        }
    }

    public PackagesContent(String title, String tViews, String tPost, String tDiscript, String tValid, String tRupees) {
        this.title = title;
        this.tViews = tViews;
        this.tPost = tPost;
        this.tDiscript = tDiscript;
        this.tValid = tValid;
        this.tRupees = tRupees;
    }

//    public String getTitle() {
//        return title;
//    }

    public String gettViews() {
        return tViews;
    }

    public String gettPost() {
        return tPost;
    }

    public String gettDiscript() {
        return tDiscript;
    }

    public String gettValid() {
        return tValid;
    }

    public String gettRupees() {
        return tRupees;
    }

//    public void setTitle(String title) {
//        this.title = title;
//    }

    public void settViews(String tViews) {
        this.tViews = tViews;
    }

    public void settPost(String tPost) {
        this.tPost = tPost;
    }

    public void settDiscript(String tDiscript) {
        this.tDiscript = tDiscript;
    }

    public void settValid(String tValid) {
        this.tValid = tValid;
    }

    public void settRupees(String tRupees) {
        this.tRupees = tRupees;
    }
}
