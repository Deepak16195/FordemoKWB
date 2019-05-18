package com.rdave.kamwaliapplication.Model;

import java.util.List;

public class Candidate {


    /**
     * Success : true
     * Message : Success
     * Data : [{"CandidateId":6,"FullName":"Rima","DOB":"1988-01-10T00:00:00","Age":31,"Gender":"","MobileNo":"9594162518","AlternateNumbers":"","EmailId":null,"MaritalStatus":"Unmarried","MaximumEducation":"12 th","ContactAddress":"","PermanentAddress":"","ServiceState":"M.H.","ServiceCity":"Mumbai","ServiceLocation":"Mumbai","Religion":"Other","Category":"5","Subcategory":"2","ServiceSkill":"","WorkingSince":"2016","JobTime":"8","ExpectedSalary":60000,"Image":"","Resume":"","ResumeText":"","Password":"123","AgentId":"0","Created":"2019-04-14T00:00:00","Status":"Active","Modify":"2019-04-14T00:00:00","Feedback":"","Application":"Web","CategoryName":"Driver","SubcategoryName":"Truck Driver"},{"CandidateId":5,"FullName":"Pinkee","DOB":"1988-01-10T00:00:00","Age":31,"Gender":"","MobileNo":"9594162517","AlternateNumbers":"","EmailId":"pinki@gmail.com","MaritalStatus":"Unmarried","MaximumEducation":"8 th","ContactAddress":"","PermanentAddress":"","ServiceState":"M.H.","ServiceCity":"Mumbai","ServiceLocation":"Mumbai","Religion":"Other","Category":"4","Subcategory":"3","ServiceSkill":"","WorkingSince":"2012","JobTime":"12","ExpectedSalary":15000,"Image":"","Resume":"","ResumeText":"","Password":"123","AgentId":"0","Created":"2019-04-14T00:00:00","Status":"Active","Modify":"2019-04-14T00:00:00","Feedback":"","Application":"Web","CategoryName":"Cook","SubcategoryName":"Chef"},{"CandidateId":4,"FullName":"Naveen","DOB":"1988-01-10T00:00:00","Age":31,"Gender":"","MobileNo":"9594162516","AlternateNumbers":"","EmailId":"naween@gmail.com","MaritalStatus":"Married","MaximumEducation":"10 th","ContactAddress":"","PermanentAddress":"","ServiceState":"M.H.","ServiceCity":"Thane","ServiceLocation":"Thane","Religion":"Other","Category":"4","Subcategory":"3","ServiceSkill":"","WorkingSince":"2014","JobTime":"10","ExpectedSalary":80000,"Image":"","Resume":"","ResumeText":"","Password":"changeit","AgentId":"0","Created":"2019-04-14T00:00:00","Status":"Active","Modify":"2019-04-14T00:00:00","Feedback":"","Application":"Web","CategoryName":"Cook","SubcategoryName":"Chef"},{"CandidateId":3,"FullName":"Pawan","DOB":"1988-01-10T00:00:00","Age":31,"Gender":"","MobileNo":"9594162515","AlternateNumbers":"","EmailId":"","MaritalStatus":"Married","MaximumEducation":"12 th","ContactAddress":"","PermanentAddress":"","ServiceState":"M.H.","ServiceCity":"Thane","ServiceLocation":"Thane","Religion":"Hindu","Category":"5","Subcategory":"1","ServiceSkill":"","WorkingSince":"2016","JobTime":"8","ExpectedSalary":50000,"Image":"","Resume":"","ResumeText":"","Password":"123","AgentId":"0","Created":"2019-04-14T00:00:00","Status":"Active","Modify":"2019-04-14T00:00:00","Feedback":"","Application":"Web","CategoryName":"Driver","SubcategoryName":"Car Driver"},{"CandidateId":2,"FullName":"Rahul","DOB":"1988-01-10T00:00:00","Age":31,"Gender":"","MobileNo":"9594162514","AlternateNumbers":"","EmailId":"rahul@gmail.com","MaritalStatus":"Unmarried","MaximumEducation":"5 th","ContactAddress":"","PermanentAddress":"","ServiceState":"M.H.","ServiceCity":"Mumbai","ServiceLocation":"Mumbai","Religion":"Hindu","Category":"5","Subcategory":"1","ServiceSkill":"","WorkingSince":"2015","JobTime":"12","ExpectedSalary":10000,"Image":"","Resume":"","ResumeText":"","Password":"changeit","AgentId":"0","Created":"2019-04-14T00:00:00","Status":"Active","Modify":"2019-04-14T00:00:00","Feedback":"","Application":"Web","CategoryName":"Driver","SubcategoryName":"Car Driver"},{"CandidateId":1,"FullName":"Sam","DOB":"1988-01-10T00:00:00","Age":31,"Gender":"","MobileNo":"9594162512","AlternateNumbers":"","EmailId":"gautam.shambhu72@gmail.com","MaritalStatus":"Married","MaximumEducation":"12 th","ContactAddress":"Aaaaaaaaaaa","PermanentAddress":"Paaaaaaaaaaa","ServiceState":"M.H.","ServiceCity":"Mumbai","ServiceLocation":"Mumbai","Religion":"Hindu","Category":"4","Subcategory":"3","ServiceSkill":"","WorkingSince":"2016","JobTime":"8","ExpectedSalary":20000,"Image":"","Resume":"","ResumeText":"","Password":"changeit","AgentId":"0","Created":"2019-04-14T00:00:00","Status":"Active","Modify":"2019-04-14T00:00:00","Feedback":"","Application":"Web","CategoryName":"Cook","SubcategoryName":"Chef"}]
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
         * CandidateId : 6
         * FullName : Rima
         * DOB : 1988-01-10T00:00:00
         * Age : 31
         * Gender :
         * MobileNo : 9594162518
         * AlternateNumbers :
         * EmailId : null
         * MaritalStatus : Unmarried
         * MaximumEducation : 12 th
         * ContactAddress :
         * PermanentAddress :
         * ServiceState : M.H.
         * ServiceCity : Mumbai
         * ServiceLocation : Mumbai
         * Religion : Other
         * Category : 5
         * Subcategory : 2
         * ServiceSkill :
         * WorkingSince : 2016
         * JobTime : 8
         * ExpectedSalary : 60000
         * Image :
         * Resume :
         * ResumeText :
         * Password : 123
         * AgentId : 0
         * Created : 2019-04-14T00:00:00
         * Status : Active
         * Modify : 2019-04-14T00:00:00
         * Feedback :
         * Application : Web
         * CategoryName : Driver
         * SubcategoryName : Truck Driver
         */

        private int CandidateId;
        private String FullName;
        private String DOB;
        private int Age;
        private String Gender;
        private String MobileNo;
        private String AlternateNumbers;
        private String EmailId;
        private String MaritalStatus;
        private String MaximumEducation;
        private String ContactAddress;
        private String PermanentAddress;
        private String ServiceState;
        private String ServiceCity;
        private String ServiceLocation;
        private String Religion;
        private String Category;
        private String Subcategory;
        private String ServiceSkill;
        private String WorkingSince;
        private String JobTime;
        private double   ExpectedSalary;
        private String Image;
        private String Resume;
        private String ResumeText;
        private String Password;
        private String AgentId;
        private String Created;
        private String Status;
        private String Modify;
        private String Feedback;
        private String Application;
        private String CategoryName;
        private String SubcategoryName;

        public int getCandidateId() {
            return CandidateId;
        }

        public void setCandidateId(int CandidateId) {
            this.CandidateId = CandidateId;
        }

        public String getFullName() {
            return FullName;
        }

        public void setFullName(String FullName) {
            this.FullName = FullName;
        }

        public String getDOB() {
            return DOB;
        }

        public void setDOB(String DOB) {
            this.DOB = DOB;
        }

        public int getAge() {
            return Age;
        }

        public void setAge(int Age) {
            this.Age = Age;
        }

        public String getGender() {
            return Gender;
        }

        public void setGender(String Gender) {
            this.Gender = Gender;
        }

        public String getMobileNo() {
            return MobileNo;
        }

        public void setMobileNo(String MobileNo) {
            this.MobileNo = MobileNo;
        }

        public String getAlternateNumbers() {
            return AlternateNumbers;
        }

        public void setAlternateNumbers(String AlternateNumbers) {
            this.AlternateNumbers = AlternateNumbers;
        }

        public String getEmailId() {
            return EmailId;
        }

        public void setEmailId(String EmailId) {
            this.EmailId = EmailId;
        }

        public String getMaritalStatus() {
            return MaritalStatus;
        }

        public void setMaritalStatus(String MaritalStatus) {
            this.MaritalStatus = MaritalStatus;
        }

        public String getMaximumEducation() {
            return MaximumEducation;
        }

        public void setMaximumEducation(String MaximumEducation) {
            this.MaximumEducation = MaximumEducation;
        }

        public String getContactAddress() {
            return ContactAddress;
        }

        public void setContactAddress(String ContactAddress) {
            this.ContactAddress = ContactAddress;
        }

        public String getPermanentAddress() {
            return PermanentAddress;
        }

        public void setPermanentAddress(String PermanentAddress) {
            this.PermanentAddress = PermanentAddress;
        }

        public String getServiceState() {
            return ServiceState;
        }

        public void setServiceState(String ServiceState) {
            this.ServiceState = ServiceState;
        }

        public String getServiceCity() {
            return ServiceCity;
        }

        public void setServiceCity(String ServiceCity) {
            this.ServiceCity = ServiceCity;
        }

        public String getServiceLocation() {
            return ServiceLocation;
        }

        public void setServiceLocation(String ServiceLocation) {
            this.ServiceLocation = ServiceLocation;
        }

        public String getReligion() {
            return Religion;
        }

        public void setReligion(String Religion) {
            this.Religion = Religion;
        }

        public String getCategory() {
            return Category;
        }

        public void setCategory(String Category) {
            this.Category = Category;
        }

        public String getSubcategory() {
            return Subcategory;
        }

        public void setSubcategory(String Subcategory) {
            this.Subcategory = Subcategory;
        }

        public String getServiceSkill() {
            return ServiceSkill;
        }

        public void setServiceSkill(String ServiceSkill) {
            this.ServiceSkill = ServiceSkill;
        }

        public String getWorkingSince() {
            return WorkingSince;
        }

        public void setWorkingSince(String WorkingSince) {
            this.WorkingSince = WorkingSince;
        }

        public String getJobTime() {
            return JobTime;
        }

        public void setJobTime(String JobTime) {
            this.JobTime = JobTime;
        }

        public double getExpectedSalary() {
            return ExpectedSalary;
        }

        public void setExpectedSalary(double ExpectedSalary) {
            this.ExpectedSalary = ExpectedSalary;
        }

        public String getImage() {
            return Image;
        }

        public void setImage(String Image) {
            this.Image = Image;
        }

        public String getResume() {
            return Resume;
        }

        public void setResume(String Resume) {
            this.Resume = Resume;
        }

        public String getResumeText() {
            return ResumeText;
        }

        public void setResumeText(String ResumeText) {
            this.ResumeText = ResumeText;
        }

        public String getPassword() {
            return Password;
        }

        public void setPassword(String Password) {
            this.Password = Password;
        }

        public String getAgentId() {
            return AgentId;
        }

        public void setAgentId(String AgentId) {
            this.AgentId = AgentId;
        }

        public String getCreated() {
            return Created;
        }

        public void setCreated(String Created) {
            this.Created = Created;
        }

        public String getStatus() {
            return Status;
        }

        public void setStatus(String Status) {
            this.Status = Status;
        }

        public String getModify() {
            return Modify;
        }

        public void setModify(String Modify) {
            this.Modify = Modify;
        }

        public String getFeedback() {
            return Feedback;
        }

        public void setFeedback(String Feedback) {
            this.Feedback = Feedback;
        }

        public String getApplication() {
            return Application;
        }

        public void setApplication(String Application) {
            this.Application = Application;
        }

        public String getCategoryName() {
            return CategoryName;
        }

        public void setCategoryName(String CategoryName) {
            this.CategoryName = CategoryName;
        }

        public String getSubcategoryName() {
            return SubcategoryName;
        }

        public void setSubcategoryName(String SubcategoryName) {
            this.SubcategoryName = SubcategoryName;
        }
    }
}
