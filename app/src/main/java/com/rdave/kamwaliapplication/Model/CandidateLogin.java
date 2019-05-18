package com.rdave.kamwaliapplication.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public  class CandidateLogin {

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
        @SerializedName("SubcategoryName")
        private String SubcategoryName;
        @Expose
        @SerializedName("CategoryName")
        private String CategoryName;
        @Expose
        @SerializedName("Application")
        private String Application;
        @Expose
        @SerializedName("Feedback")
        private String Feedback;
        @Expose
        @SerializedName("Modify")
        private String Modify;
        @Expose
        @SerializedName("Status")
        private String Status;
        @Expose
        @SerializedName("Created")
        private String Created;
        @Expose
        @SerializedName("AgentId")
        private String AgentId;
        @Expose
        @SerializedName("Password")
        private String Password;
        @Expose
        @SerializedName("ResumeText")
        private String ResumeText;
        @Expose
        @SerializedName("Resume")
        private String Resume;
        @Expose
        @SerializedName("Image")
        private String Image;
        @Expose
        @SerializedName("ExpectedSalary")
        private int ExpectedSalary;
        @Expose
        @SerializedName("JobTime")
        private String JobTime;
        @Expose
        @SerializedName("WorkingSince")
        private String WorkingSince;
        @Expose
        @SerializedName("ServiceSkill")
        private String ServiceSkill;
        @Expose
        @SerializedName("Subcategory")
        private String Subcategory;
        @Expose
        @SerializedName("Category")
        private String Category;
        @Expose
        @SerializedName("Religion")
        private String Religion;
        @Expose
        @SerializedName("ServiceLocation")
        private String ServiceLocation;
        @Expose
        @SerializedName("ServiceCity")
        private String ServiceCity;
        @Expose
        @SerializedName("ServiceState")
        private String ServiceState;
        @Expose
        @SerializedName("PermanentAddress")
        private String PermanentAddress;
        @Expose
        @SerializedName("ContactAddress")
        private String ContactAddress;
        @Expose
        @SerializedName("MaximumEducation")
        private String MaximumEducation;
        @Expose
        @SerializedName("MaritalStatus")
        private String MaritalStatus;
        @Expose
        @SerializedName("EmailId")
        private String EmailId;
        @Expose
        @SerializedName("AlternateNumbers")
        private String AlternateNumbers;
        @Expose
        @SerializedName("MobileNo")
        private String MobileNo;
        @Expose
        @SerializedName("Gender")
        private String Gender;
        @Expose
        @SerializedName("Age")
        private int Age;
        @Expose
        @SerializedName("DOB")
        private String DOB;
        @Expose
        @SerializedName("FullName")
        private String FullName;
        @Expose
        @SerializedName("CandidateId")
        private int CandidateId;

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

        public String getApplication() {
            return Application;
        }

        public void setApplication(String Application) {
            this.Application = Application;
        }

        public String getFeedback() {
            return Feedback;
        }

        public void setFeedback(String Feedback) {
            this.Feedback = Feedback;
        }

        public String getModify() {
            return Modify;
        }

        public void setModify(String Modify) {
            this.Modify = Modify;
        }

        public String getStatus() {
            return Status;
        }

        public void setStatus(String Status) {
            this.Status = Status;
        }

        public String getCreated() {
            return Created;
        }

        public void setCreated(String Created) {
            this.Created = Created;
        }

        public String getAgentId() {
            return AgentId;
        }

        public void setAgentId(String AgentId) {
            this.AgentId = AgentId;
        }

        public String getPassword() {
            return Password;
        }

        public void setPassword(String Password) {
            this.Password = Password;
        }

        public String getResumeText() {
            return ResumeText;
        }

        public void setResumeText(String ResumeText) {
            this.ResumeText = ResumeText;
        }

        public String getResume() {
            return Resume;
        }

        public void setResume(String Resume) {
            this.Resume = Resume;
        }

        public String getImage() {
            return Image;
        }

        public void setImage(String Image) {
            this.Image = Image;
        }

        public int getExpectedSalary() {
            return ExpectedSalary;
        }

        public void setExpectedSalary(int ExpectedSalary) {
            this.ExpectedSalary = ExpectedSalary;
        }

        public String getJobTime() {
            return JobTime;
        }

        public void setJobTime(String JobTime) {
            this.JobTime = JobTime;
        }

        public String getWorkingSince() {
            return WorkingSince;
        }

        public void setWorkingSince(String WorkingSince) {
            this.WorkingSince = WorkingSince;
        }

        public String getServiceSkill() {
            return ServiceSkill;
        }

        public void setServiceSkill(String ServiceSkill) {
            this.ServiceSkill = ServiceSkill;
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

        public String getReligion() {
            return Religion;
        }

        public void setReligion(String Religion) {
            this.Religion = Religion;
        }

        public String getServiceLocation() {
            return ServiceLocation;
        }

        public void setServiceLocation(String ServiceLocation) {
            this.ServiceLocation = ServiceLocation;
        }

        public String getServiceCity() {
            return ServiceCity;
        }

        public void setServiceCity(String ServiceCity) {
            this.ServiceCity = ServiceCity;
        }

        public String getServiceState() {
            return ServiceState;
        }

        public void setServiceState(String ServiceState) {
            this.ServiceState = ServiceState;
        }

        public String getPermanentAddress() {
            return PermanentAddress;
        }

        public void setPermanentAddress(String PermanentAddress) {
            this.PermanentAddress = PermanentAddress;
        }

        public String getContactAddress() {
            return ContactAddress;
        }

        public void setContactAddress(String ContactAddress) {
            this.ContactAddress = ContactAddress;
        }

        public String getMaximumEducation() {
            return MaximumEducation;
        }

        public void setMaximumEducation(String MaximumEducation) {
            this.MaximumEducation = MaximumEducation;
        }

        public String getMaritalStatus() {
            return MaritalStatus;
        }

        public void setMaritalStatus(String MaritalStatus) {
            this.MaritalStatus = MaritalStatus;
        }

        public String getEmailId() {
            return EmailId;
        }

        public void setEmailId(String EmailId) {
            this.EmailId = EmailId;
        }

        public String getAlternateNumbers() {
            return AlternateNumbers;
        }

        public void setAlternateNumbers(String AlternateNumbers) {
            this.AlternateNumbers = AlternateNumbers;
        }

        public String getMobileNo() {
            return MobileNo;
        }

        public void setMobileNo(String MobileNo) {
            this.MobileNo = MobileNo;
        }

        public String getGender() {
            return Gender;
        }

        public void setGender(String Gender) {
            this.Gender = Gender;
        }

        public int getAge() {
            return Age;
        }

        public void setAge(int Age) {
            this.Age = Age;
        }

        public String getDOB() {
            return DOB;
        }

        public void setDOB(String DOB) {
            this.DOB = DOB;
        }

        public String getFullName() {
            return FullName;
        }

        public void setFullName(String FullName) {
            this.FullName = FullName;
        }

        public int getCandidateId() {
            return CandidateId;
        }

        public void setCandidateId(int CandidateId) {
            this.CandidateId = CandidateId;
        }
    }
}
