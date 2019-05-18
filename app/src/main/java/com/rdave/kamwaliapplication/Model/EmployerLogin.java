package com.rdave.kamwaliapplication.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EmployerLogin {

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
        @SerializedName("Password")
        private String Password;
        @Expose
        @SerializedName("GSTNo")
        private String GSTNo;
        @Expose
        @SerializedName("CompanyName")
        private String CompanyName;
        @Expose
        @SerializedName("PinCode")
        private String PinCode;
        @Expose
        @SerializedName("City")
        private String City;
        @Expose
        @SerializedName("State")
        private String State;
        @Expose
        @SerializedName("Address")
        private String Address;
        @Expose
        @SerializedName("EmailId")
        private String EmailId;
        @Expose
        @SerializedName("MobileNo")
        private String MobileNo;
        @Expose
        @SerializedName("EmployerName")
        private String EmployerName;
        @Expose
        @SerializedName("EmployerId")
        private int EmployerId;

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

        public String getPassword() {
            return Password;
        }

        public void setPassword(String Password) {
            this.Password = Password;
        }

        public String getGSTNo() {
            return GSTNo;
        }

        public void setGSTNo(String GSTNo) {
            this.GSTNo = GSTNo;
        }

        public String getCompanyName() {
            return CompanyName;
        }

        public void setCompanyName(String CompanyName) {
            this.CompanyName = CompanyName;
        }

        public String getPinCode() {
            return PinCode;
        }

        public void setPinCode(String PinCode) {
            this.PinCode = PinCode;
        }

        public String getCity() {
            return City;
        }

        public void setCity(String City) {
            this.City = City;
        }

        public String getState() {
            return State;
        }

        public void setState(String State) {
            this.State = State;
        }

        public String getAddress() {
            return Address;
        }

        public void setAddress(String Address) {
            this.Address = Address;
        }

        public String getEmailId() {
            return EmailId;
        }

        public void setEmailId(String EmailId) {
            this.EmailId = EmailId;
        }

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

        public int getEmployerId() {
            return EmployerId;
        }

        public void setEmployerId(int EmployerId) {
            this.EmployerId = EmployerId;
        }
    }
}
