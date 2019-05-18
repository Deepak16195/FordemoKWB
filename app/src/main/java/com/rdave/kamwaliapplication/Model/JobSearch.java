package com.rdave.kamwaliapplication.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JobSearch {

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

    public static class DataEntity {
        @Expose
        @SerializedName("LanguageName")
        private String LanguageName;
        @Expose
        @SerializedName("SubcategoryName")
        private String SubcategoryName;
        @Expose
        @SerializedName("CategoryName")
        private String CategoryName;
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
        @SerializedName("Language")
        private String Language;
        @Expose
        @SerializedName("Education")
        private String Education;
        @Expose
        @SerializedName("JobTime")
        private String JobTime;
        @Expose
        @SerializedName("JobLocation")
        private String JobLocation;
        @Expose
        @SerializedName("JobCity")
        private String JobCity;
        @Expose
        @SerializedName("JobState")
        private String JobState;
        @Expose
        @SerializedName("Subcategory")
        private String Subcategory;
        @Expose
        @SerializedName("Category")
        private String Category;
        @Expose
        @SerializedName("EmplrId")
        private String EmplrId;
        @Expose
        @SerializedName("JobId")
        private int JobId;

        public String getLanguageName() {
            return LanguageName;
        }

        public void setLanguageName(String LanguageName) {
            this.LanguageName = LanguageName;
        }

        public String getSubcategoryName() {
            return SubcategoryName;
        }

        public void setSubcategoryName(String SubcategoryName) {
            this.SubcategoryName = SubcategoryName;
        }

        public String getCategoryName() {
            return CategoryName;
        }

        public void setCategoryName(String CategoryName) {
            this.CategoryName = CategoryName;
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

        public String getLanguage() {
            return Language;
        }

        public void setLanguage(String Language) {
            this.Language = Language;
        }

        public String getEducation() {
            return Education;
        }

        public void setEducation(String Education) {
            this.Education = Education;
        }

        public String getJobTime() {
            return JobTime;
        }

        public void setJobTime(String JobTime) {
            this.JobTime = JobTime;
        }

        public String getJobLocation() {
            return JobLocation;
        }

        public void setJobLocation(String JobLocation) {
            this.JobLocation = JobLocation;
        }

        public String getJobCity() {
            return JobCity;
        }

        public void setJobCity(String JobCity) {
            this.JobCity = JobCity;
        }

        public String getJobState() {
            return JobState;
        }

        public void setJobState(String JobState) {
            this.JobState = JobState;
        }

        public String getSubcategory() {
            return Subcategory;
        }

        public void setSubcategory(String Subcategory) {
            this.Subcategory = Subcategory;
        }

        public String getCategory() {
            return Category;
        }

        public void setCategory(String Category) {
            this.Category = Category;
        }

        public String getEmplrId() {
            return EmplrId;
        }

        public void setEmplrId(String EmplrId) {
            this.EmplrId = EmplrId;
        }

        public int getJobId() {
            return JobId;
        }

        public void setJobId(int JobId) {
            this.JobId = JobId;
        }
    }
}
