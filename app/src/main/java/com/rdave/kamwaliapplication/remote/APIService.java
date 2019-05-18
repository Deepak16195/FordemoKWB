package com.rdave.kamwaliapplication.remote;


import com.rdave.kamwaliapplication.Activity.PackagesContent;
import com.rdave.kamwaliapplication.Model.AgentLogin;
import com.rdave.kamwaliapplication.Model.AgentPayment;
import com.rdave.kamwaliapplication.Model.AppliedJob;
import com.rdave.kamwaliapplication.Model.Candidate;
import com.rdave.kamwaliapplication.Model.CandidateDeatailed;
import com.rdave.kamwaliapplication.Model.CandidateFilter;
import com.rdave.kamwaliapplication.Model.CandidateLogin;
import com.rdave.kamwaliapplication.Model.Categories;
import com.rdave.kamwaliapplication.Model.ChangePassword;
import com.rdave.kamwaliapplication.Model.DeductPackView;
import com.rdave.kamwaliapplication.Model.EmoloyerPostedJobs;
import com.rdave.kamwaliapplication.Model.EmpPackStatus;
import com.rdave.kamwaliapplication.Model.EmployerLogin;
import com.rdave.kamwaliapplication.Model.EmployerPayment;
import com.rdave.kamwaliapplication.Model.EmployerSummary;
import com.rdave.kamwaliapplication.Model.EmployerViewedCandidateModel;
import com.rdave.kamwaliapplication.Model.FSaveOtp;
import com.rdave.kamwaliapplication.Model.Filter;
import com.rdave.kamwaliapplication.Model.GetCity;
import com.rdave.kamwaliapplication.Model.GetEmpPostedJob;
import com.rdave.kamwaliapplication.Model.GetJob;
import com.rdave.kamwaliapplication.Model.GetPincode;
import com.rdave.kamwaliapplication.Model.GetState;
import com.rdave.kamwaliapplication.Model.GetSupport;
import com.rdave.kamwaliapplication.Model.JobSearch;
import com.rdave.kamwaliapplication.Model.Language;
import com.rdave.kamwaliapplication.Model.MatchOtp;
import com.rdave.kamwaliapplication.Model.PackageCount;
import com.rdave.kamwaliapplication.Model.PostCount;
import com.rdave.kamwaliapplication.Model.ResetPassword;
import com.rdave.kamwaliapplication.Model.SOtp;
import com.rdave.kamwaliapplication.Model.SaveEmpPackage;
import com.rdave.kamwaliapplication.Model.SaveSupport;
import com.rdave.kamwaliapplication.Model.SubCategory;
import com.rdave.kamwaliapplication.Model.UpdateAgent;
import com.rdave.kamwaliapplication.Model.UpdateCandidate;
import com.rdave.kamwaliapplication.Model.UpdateEmployer;

import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;


/**
 * Created by user on 07-09-2017.
 */

public interface APIService {
    @GET("api/User/GetCategories")
    Observable<Categories> getCategory();

    @GET("api/User/GetCity")
    Observable<GetCity> getCities();

    @GET("api/User/GetCandidates")
    Observable<Candidate> getCandidates();

    @GET("api/User/GetJobs")
    Observable<GetJob>getjob();

    @POST("api/User/CandidateApplyJob")
    Observable<GetJob>Applyjob(@Query("CandId") String CandID,@Query("JobId") String JobId);

    @GET("api/User/CandidateLogin")
    Observable<CandidateLogin>CheckLogin(@Query("MobileNo") String mobile, @Query("Password") String type);

    @GET("api/User/EmployerLogin")
    Observable<EmployerLogin>ECheckLogin(@Query("MobileNo") String mobile, @Query("Password") String type);

    @GET("api/User/AgentLogin")
    Observable<AgentLogin>ACheckLogin(@Query("MobileNo") String mobile, @Query("Password") String type);

    @POST("api/User/SaveCandidate")
    Observable<CandidateLogin>SCandigate(@Query("FullName")String name, @Query("MobileNo") String mobile, @Query("EmailId")String email, @Query("Password") String type, @Query("AgentId")String agentid, @Query("Application")String application);

    @POST("api/User/SaveEmployer")
    Observable<EmployerLogin>SEmployer(@Query("EmployerName")String name, @Query("MobileNo") String mobile, @Query("EmailId")String email, @Query("Password") String type, @Query("Application")String application);

    @POST(" api/User/SaveAgent")
    Observable<AgentLogin>SAgent(@Query("AgentName")String name, @Query("MobileNo") String mobile, @Query("EmailId")String email, @Query("Password") String type, @Query("Application")String application);

    @POST("api/User/SaveOTP")
    Observable<SOtp>SendOtp(@Query("MobileNo") String mobile, @Query("UserType")String type);

    @POST("api/User/MatchOTP")
    Observable<MatchOtp>GetOtp(@Query("UserType")String type,@Query("MobileNo")String mobile,@Query("OTPNumber")String otp);

    @GET("api/User/GetCandidatesOnCategoryLocation")
    Observable<Candidate> Searchcandidate(@Query("Category")String category, @Query("Location")String location);

    @GET("api/User/GetCandidateOnCandidateId")
    Observable<CandidateDeatailed>Candidatedetail(@Query("CandidateId")String candidateid);

    @GET("api/User/GetJobsOnCategoryLocation")
    Observable<JobSearch>Searchjob(@Query("Category")String category, @Query("Location")String location);

    @POST("api/User/UpdateEmployer")
    Observable<UpdateEmployer>UEmployer(@Query("EmployerId")String employerid,@Query("EmployerName")String name, @Query("MobileNo") String mobile, @Query("EmailId")String email,@Query("Address")String address,@Query("State")String state,@Query("City")String city,@Query("PinCode")String pincode,@Query("CompanyName")String companyname, @Query("GSTNo") String gst);

    @POST("api/User/UpdateAgent")
    Observable<UpdateAgent>UAgent(@Query("EmployerId")String employerid, @Query("EmployerName")String name, @Query("MobileNo") String mobile, @Query("EmailId")String email, @Query("Address")String address, @Query("State")String state, @Query("City")String city, @Query("PinCode")String pincode, @Query("CompanyName")String companyname, @Query("GSTNo") String gst);

    @POST("api/User/UpdateCandidate")
    Observable<UpdateCandidate>UCandidate(@Field("CandidateId") String candidateid, @Field("FullName")String name, @Field("DOB") String dob, @Field("Gender")String gender, @Field("MobileNo")String mobilenum, @Field("AlternateNumbers")String altnum, @Field("EmailId")String emailid, @Field("MaritalStatus")String married, @Field("MaximumEducation")String maxedu, @Field("ContactAddress") String contactadress,@Field("PermanentAddress") String permanentadrss,@Field("ServiceState") String servicestate,@Field("ServiceCity") String servicecity,@Field("ServiceLocation") String servicelocation,@Field("Religion") String religion,@Field("Category") String category,@Field("Subcategory") String subcategory,@Field("ServiceSkill") String serviceskill,@Field("WorkingSince") String wrkingsince,@Field("JobTime") String jobtime,@Field("ExpectedSalary") String expectedsalry,@Field("ResumeText") String resumetext);

    @POST("api/User/DeductEmployerPackageViewCount")
    Observable<DeductPackView>DPVCount(@Query("EmplrId") String emplrid,@Query("CandId") String CandId);

    @GET("api/User/GetState")
    Observable<GetState> getState();

    @GET("api/User/GetCityOnState")
    Observable<GetCity> getCity(@Query("State")String state);

    @GET("api/User/GetAgentOnMobileAndPassword")
    Observable<AgentLogin> getAgentProfile(@Query("MobileNo")String MobileNo,@Query("Password")String Password);


    @GET("api/User/GetEmployerOnMobileAndPassword")
    Observable<EmployerLogin> getEmployerProfile(@Query("MobileNo")String MobileNo,@Query("Password")String Password);

    @GET("api/User/GetCandidateOnMobileAndPassword")
    Observable<CandidateLogin> getCandidateProfile(@Query("MobileNo")String MobileNo,@Query("Password")String Password);

    @GET("api/User/GetPinCodeOnStateAndCity")
    Observable<GetPincode> getPincpode(@Query("State")String state,@Query("City")String city);

    @GET("api/User/GetPackages")
    Observable<PackagesContent> getPackages();

    @GET("api/User/GetSubcategoryOnCategory")
    Observable<SubCategory> getSubCategory(@Query("Category")String Category);

    @GET("api/User/GetLanguage")
    Observable<Language> getLanguage();

    @GET("api/User/GetTotalPostCountOfEmployer")
    Observable<PostCount> getPostCount(@Query("EmplrId")String EmplrId);

    @GET("api/User/GetTotalPackageCount")
    Observable<PackageCount> getPackageCount(@Query("EmplrId")String EmplrId);


    @POST("api/User/EmployerPostJob")
    Observable<PackagesContent> PostJobs(@Query("EmplrId")String EmplrId,@Query("Category")String Category,@Query("Subcategory")String Subcategory,@Query("JobState")String JobState,@Query("JobCity")String JobCity
                                        ,@Query("JobLocation")String JobLocation,@Query("JobTime")String JobTime,@Query("Education")String Education,@Query("Language")String Language,@Query("GivenSalary")String GivenSalary
            ,@Query("Description")String Description);





    @POST("api/User/SaveCandidateByAgent")
    Observable<UpdateCandidate>SaveCandidate( @Query("FullName")String name, @Query("DOB") String dob, @Query("Gender")String gender, @Query("MobileNo")String mobilenum, @Query("AlternateNumbers")String altnum, @Query("EmailId")String emailid, @Query("MaritalStatus")String married, @Query("MaximumEducation")String maxedu, @Query("ContactAddress") String contactadress,@Query("PermanentAddress") String permanentadrss,@Query("ServiceState") String servicestate,@Query("ServiceCity") String servicecity,@Query("ServiceLocation") String servicelocation,@Query("Religion") String religion,@Query("Category") String category,@Query("Subcategory") String subcategory,@Query("ServiceSkill") String serviceskill,@Query("WorkingSince") String wrkingsince,@Query("JobTime") String jobtime,@Query("ExpectedSalary") String expectedsalry,@Query("Image") String Image,@Query("Resume") String Resume,@Query("ResumeText") String resumetext,@Query("AgentId") String AgentId,@Query("Application") String Application);

    @GET("api/User/GetAppliedJob")
    Observable<AppliedJob>getAppliedJob(@Query("EmplrId") String EmplrId);


    @GET("api/User/GetPostedJobByEmployer")
    Observable<EmoloyerPostedJobs>getEmployerPostJob(@Query("EmplrId") String EmplrId);

    @GET("api/User/GetEmployerAccountSummary")
    Observable<EmployerSummary>getEmployerSummary(@Query("EmplrId") String EmplrId);

    @GET("api/User/GetEmployerPaymentHistory")
    Observable<EmployerPayment>getEmployerPaymentHistory(@Query("EmplrId") String EmplrId);

    @POST("api/User/UserChangePassword")
    Observable<ChangePassword> chngPass(@Query("Type")String type, @Query("UserId") String userid, @Query("OldPassword")String oldpassword, @Query("NewPassword")String newpassword);

    @GET("api/User/GetJobsOnCategorySubcategoryLocationSalary")
    Observable<Filter> getFilter(@Query("Category")String category, @Query("Subcategory")String Subcategory,@Query("Location")String location,@Query("JobType")String jobtype,@Query("Salary")String salary);

    @GET("api/User/GetCandidatesOnCategorySubcategoryLocationSalary")
    Observable<CandidateFilter> getCFilter(@Query("Category")String category, @Query("Subcategory")String Subcategory, @Query("Location")String location, @Query("JobType")String jobtype, @Query("Salary")String salary);

    @GET("api/User/GetAgentPaymentHistory")
    Observable<AgentPayment>getAgentPaymentHistory(@Query("AgentId") String AgentID);

    @GET("api/User/GetCandidateJobHistryOnCandidateId")
    Observable<AppliedJob>getCandidateAppliedJob(@Query("CandId") String CandId);

    @GET("api/User/GetCandidatesOnAgentId")
    Observable<Candidate>getAgentCanidate(@Query("AgentId") String AgentId);


    @POST("api/User/AgentUpdateCandidateProfile")
    Observable<UpdateCandidate>UpdateCandidateData(@Query("CandidateId")String CandidateId, @Query("FullName")String name, @Query("DOB") String dob, @Query("Gender")String gender, @Query("MobileNo")String mobilenum, @Query("AlternateNumbers")String altnum, @Query("EmailId")String emailid, @Query("MaritalStatus")String married, @Query("MaximumEducation")String maxedu, @Query("ContactAddress") String contactadress,@Query("PermanentAddress") String permanentadrss,@Query("ServiceState") String servicestate,@Query("ServiceCity") String servicecity,@Query("ServiceLocation") String servicelocation,@Query("Religion") String religion,@Query("Category") String category,@Query("Subcategory") String subcategory,@Query("ServiceSkill") String serviceskill,@Query("WorkingSince") String wrkingsince,@Query("JobTime") String jobtime,@Query("ExpectedSalary") String expectedsalry,@Query("Image") String Image,@Query("Resume") String Resume,@Query("ResumeText") String resumetext,@Query("Password") String Password);



    @GET("api/User/GetPostedJobByEmployerOnJobId")
    Observable<GetEmpPostedJob> getJobDetails(@Query("JobId")String JobId);


    @POST("api/User/EmployerPostJob")
    Observable<PackagesContent> UpdatePostJobs(@Query("JobId")String JobId,@Query("EmplrId")String EmplrId,@Query("Category")String Category,@Query("Subcategory")String Subcategory,@Query("JobState")String JobState,@Query("JobCity")String JobCity
            ,@Query("JobLocation")String JobLocation,@Query("JobTime")String JobTime,@Query("Education")String Education,@Query("Language")String Language,@Query("GivenSalary")String GivenSalary
            ,@Query("Description")String Description,@Query("Status")String Status);

    @POST("api/User/SaveSupport")
    Observable<SaveSupport>saveSupport(@Query("UserType") String type, @Query("UserId")String usrid,@Query("Meesage")String msg);

    @POST("api/User/GetSupportByUser")
    Observable<GetSupport>getSupport(@Query("UserType") String type, @Query("UserId")String usrid);

    @POST("api/User/SaveOtpForForgotPassword")
    Observable<FSaveOtp>getSaveotp(@Query("UserType") String type, @Query("MobileNo")String mobilenum);

    @POST("api/User/ResetForgotPassword")
    Observable<ResetPassword> resetPass(@Query("UserType")String type, @Query("MobileNo") String number,@Query("NewPassword")String newpassword);

    @POST("api/User/SaveEmployerPackage")
    Observable<SaveEmpPackage> saveEmpPackage(@Query("EmplrId")String EmplrId,@Query("PkgId")String PkgId);

    @POST("api/User/UpdateEmployerPackageStatusAfterPayment")
    Observable<EmpPackStatus> getEmpPackStatus(@Query("OrdNo")String ordnum,@Query("TrackNo")String tracknum,@Query("Status")String status);


    @GET("api/User/GetViewedCandiByEmployer")
    Observable<EmployerViewedCandidateModel> getEmployerViewCandidate(@Query("EmployerId")String EmployerId);



/*    @GET("api/Customer/getCustOutstanding")
    Observable<CustomerOutstandings> getCustomerOustanding(@Query("AgentID") int AgentID);

    @GET("api/Customer/getAreaWise")
    Observable<AreasModel> getAreaOutstanding(@Query("AgentID") int AgentID);

    @GET("api/Customer/getSalesMan")
    Observable<SalesManModel> getSalesManOutstanding(@Query("AgentID") int AgentID);

    @GET("api/Sales/getDailySale")
    Observable<DailySalesModel> getDailySales(@Query("AgentID") int AgentID, @Query("FromDate") String FromDate, @Query("UptoDate") String UptoDate);

    @GET("api/Sales/getCustDailySales")
    Observable<DailySalesModel> getCustDailySales(@Query("HeadID") int HeadID, @Query("AgentID") int AgentID, @Query("FromDate") String FromDate, @Query("UptoDate") String UptoDate);

    @GET("api/Sales/getCustomerWiseSale")
    Observable<CustomerWiseSaleModel> getCustomerSale(@Query("AgentID") int AgentID, @Query("FromDate") String FromDate, @Query("UptoDate") String UptoDate);

    @GET("api/Sales/getProductSale")
    Observable<ProductWiseSaleModel> getProductSale();

    @GET("api/Stock/getStockSummary")
    Observable<StockSummaryModel> getStockSummary();

    @GET("api/Master/getMasterList")
    Observable<MasterModel> getMasterList(@Query("TypeID") int Type, @Query("WorkerID") int WorkerID);

    @GET("api/Master/getProductList")
    Observable<ProductModel> getProductList();


    @GET("api/Product/getBrand")
    Observable<BrandModel> getBrands();

    @GET("api/Product/getCategory")
    Observable<CategoryModel> getCategory(@Query("brandid") int brandid);


    @GET("api/Product/getProduct")
    Observable<ProductSizeModel> getProductListRecycler(@Query("categoryid") int categoryid, @Query("brandid") int brandid, @Query("isgroup") int isgroup);


    @POST("api/OTP/OTP")
    Observable<ResultModel> OTP(@Query("mobileNo") String mobileNo, @Query("MacAddress") String MacAddress);

    @POST("api/OTP/CheckOTP")
    Observable<ResultModel> CheckOTP(@Query("mobileNo") String mobileNo, @Query("MacAddress") String MacAddress, @Query("OTPNumber") long OTPNumber);


    @GET("api/CheckUser/checkUser")
    Observable<UsersModel> checkUsers(@Query("UserName") String UserName, @Query("Password") String Password);


    @GET("api/CheckUser/checkAgentUser")
    Observable<UsersModel> checkAgentUser(@Query("UserName") String UserName, @Query("Password") String Password);


    @GET("api/Master/getColorList")
    Observable<ColorMaster> getColorList();

    @GET("api/Master/getSizeList")
    Observable<SizeMaster> getSizeList();

    @GET("api/Master/getProductMasterList")
    Observable<ProductMaster> getProductMasterList();

    @GET("api/Master/getProductColorList")
    Observable<ProductColorMaster> getProductColorList();

    @GET("api/Master/getProductSizeList")
    Observable<ProductSizeMaster> getProductSizeList();

    @GET("api/Master/getStock")
    Observable<StockDetails> getStock();

    @GET("api/Sales/getBillDetails")
    Observable<BillDetailsModel> getBillDetails(@Query("HeadCode") String HeadCode, @Query("Type") String Type);

    @GET("api/Sales/getAgentWiseBillSale")
    Observable<SaleManWiseSaleModel> getAgentWiseSale(@Query("AgentID") int AgentID, @Query("FromDate") String FromDate, @Query("UptoDate") String UptoDate);*/
}