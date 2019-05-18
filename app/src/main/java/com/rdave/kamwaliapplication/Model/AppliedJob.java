package com.rdave.kamwaliapplication.Model;

import java.util.List;

public class AppliedJob  {


    /**
     * Success : true
     * Message : Success
     * Data : [{"FullName":"Sam","MobileNo":"9594162512","CategoryName":"Driver","SubcategoryName":"Car Driver","Description":"Hindu Swaraj Hotel Required Hindu Cook.","PostedDate":"2019-05-05","ApplyDate":"2019-05-05"},{"FullName":"Sam","MobileNo":"9594162512","CategoryName":"Driver","SubcategoryName":"Car Driver","Description":"XYz","PostedDate":"2019-05-05","ApplyDate":"2019-05-05"}]
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
         * FullName : Sam
         * MobileNo : 9594162512
         * CategoryName : Driver
         * SubcategoryName : Car Driver
         * Description : Hindu Swaraj Hotel Required Hindu Cook.
         * PostedDate : 2019-05-05
         * ApplyDate : 2019-05-05
         */

        private String FullName;
        private String MobileNo;
        private String CategoryName;
        private String SubcategoryName;
        private String Description;
        private String PostedDate;
        private String ApplyDate;

        public String getFullName() {
            return FullName;
        }

        public void setFullName(String FullName) {
            this.FullName = FullName;
        }

        public String getMobileNo() {
            return MobileNo;
        }

        public void setMobileNo(String MobileNo) {
            this.MobileNo = MobileNo;
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

        public String getDescription() {
            return Description;
        }

        public void setDescription(String Description) {
            this.Description = Description;
        }

        public String getPostedDate() {
            return PostedDate;
        }

        public void setPostedDate(String PostedDate) {
            this.PostedDate = PostedDate;
        }

        public String getApplyDate() {
            return ApplyDate;
        }

        public void setApplyDate(String ApplyDate) {
            this.ApplyDate = ApplyDate;
        }
    }
}
