package com.rdave.kamwaliapplication.Activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;

import com.rdave.kamwaliapplication.Adapter.CategoriesAdapter;
import com.rdave.kamwaliapplication.Adapter.CityAdapter;
import com.rdave.kamwaliapplication.Adapter.PincodeAdapter;
import com.rdave.kamwaliapplication.Adapter.StateAdapter;
import com.rdave.kamwaliapplication.Adapter.SubCatAdapter;
import com.rdave.kamwaliapplication.Model.CandidateLogin;
import com.rdave.kamwaliapplication.Model.Categories;
import com.rdave.kamwaliapplication.Model.GetCity;
import com.rdave.kamwaliapplication.Model.GetPincode;
import com.rdave.kamwaliapplication.Model.GetState;
import com.rdave.kamwaliapplication.Model.SubCategory;
import com.rdave.kamwaliapplication.Model.UpdateCandidate;
import com.rdave.kamwaliapplication.R;
import com.rdave.kamwaliapplication.Utility.SharePrefarence;
import com.rdave.kamwaliapplication.Utility.Utils;
import com.rdave.kamwaliapplication.remote.ApiUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CProfileActivity extends AppCompatActivity {


    protected Toolbar toolbar;
    protected TextInputEditText cEtName;
    protected TextInputEditText cEtDob;
    protected Spinner cEtGender;
    protected TextInputEditText cEtMobile;
    protected TextInputEditText cEtAlternateNum;
    protected Spinner cEtMarital;
    protected TextInputEditText cEtMedu;
    protected TextInputEditText cEtCAdress;
    protected TextInputEditText cEtPAdress;
    protected AutoCompleteTextView cEtSState;
    protected AutoCompleteTextView cEtSCity;
    protected TextInputEditText cEtSLocation;
    protected TextInputEditText cEtReligion;
    protected AutoCompleteTextView cEtCategory;
    protected AutoCompleteTextView cEtSbCategory;
    protected TextInputEditText cEtServiceSkill;
    protected TextInputEditText cEtWorkSince;
    protected TextInputEditText cEtJob;
    protected TextInputEditText cEtExpSlry;
    protected TextInputEditText cEtResume;
    protected TextInputEditText cEtEmailid;
    protected TextInputEditText log_pass_text;

    protected Button update;
    String Logined = "0";
    String LoginedAs = "0";
    String id="0";

    Calendar dCurrentDate;
    int day, month, year;
    String Marital,Gender;
    List<GetState.DataEntity> gtstate = new ArrayList<>();
    List<GetCity.DataEntity> gtcity = new ArrayList<>();
    List<GetPincode.DataEntity> gtpincode = new ArrayList<>();
    StateAdapter adapter;
    CityAdapter adapter2;
    PincodeAdapter adapter3;
    String MobileNo="",Password="",UserID="";
    List<Categories.DataBean> mCategories = new ArrayList<>();
    List<SubCategory.DataBean> mSubCat = new ArrayList<>();
    SubCatAdapter subCatAdapter;

    private ArrayAdapter<CharSequence> arrayMarital;
    private ArrayAdapter<CharSequence> arrayGender;

    String from="";
    int pos=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_cprofile);
        initView();


        cEtGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Gender=adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        cEtMarital.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Marital=adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        cEtSState.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos,
                                    long id) {
                String Data = gtstate.get(pos).getState();
                GetCity(Data);

            }
        });

        cEtCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos,
                                    long id) {
                String Data = mCategories.get(pos).getCategoryName();
                getSubCategory(Data);


            }
        });

        if(getIntent().getStringExtra("From")==null)
        {
            if(MobileNo.length()==10) {
                loadCandidateProfile(MobileNo, Password);
            }

        }
        else if(getIntent().getStringExtra("From")!=null && getIntent().getStringExtra("From").equalsIgnoreCase("Agent"))
        {
            if(getIntent().getStringExtra("Pos")!=null)
            {


                    loadCandidateAgentProfile(Integer.parseInt(getIntent().getStringExtra("Pos").toString()));


            }
            else{
                getCagtegory();
            }


        }
        else{
            getCagtegory();
        }

   /*     cEtSCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                String Data = gtstate.get(pos).getState();
                String cData = gtcity.get(pos).getCity();
                GetPincode(Data,cData);

            }
        });*/



        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (LoginedAs.equalsIgnoreCase("Candidate")) {

                    if(Marital.equalsIgnoreCase("Select Marital Status"))
                    {
                        Utils.customMessage(CProfileActivity.this,"Please Select Marital Status");
                        return;
                    }

                    if(Gender.equalsIgnoreCase("Select Gender"))
                    {
                        Utils.customMessage(CProfileActivity.this,"Please Select Gender");
                        return;
                    }

                    if(cEtName.getText().length()==0)
                    {
                        Utils.customMessage(CProfileActivity.this,"Full Name Can't Be Blank");
                        cEtName.requestFocus();
                        return;
                    }

                    if(cEtMobile.getText().length()==0)
                    {
                        Utils.customMessage(CProfileActivity.this,"Mobile No Can't Be Blank");
                        cEtMobile.requestFocus();
                        return;
                    }

                    if(cEtSState.getText().length()==0)
                    {
                        Utils.customMessage(CProfileActivity.this,"State Can't Be Blank");
                        cEtSState.requestFocus();
                        return;
                    }

                    if(cEtSCity.getText().length()==0)
                    {
                        Utils.customMessage(CProfileActivity.this,"City Can't Be Blank");
                        cEtSCity.requestFocus();
                        return;
                    }

                    if(cEtCategory.getText().length()==0)
                    {
                        Utils.customMessage(CProfileActivity.this,"Category Can't Be Blank");
                        cEtCategory.requestFocus();
                        return;
                    }
                    if(cEtSbCategory.getText().length()==0)
                    {
                        Utils.customMessage(CProfileActivity.this,"Sub Category Can't Be Blank");
                        cEtSbCategory.requestFocus();
                        return;
                    }

                    if(cEtWorkSince.getText().length()==0)
                    {
                        Utils.customMessage(CProfileActivity.this,"Working Since Can't Be Blank");
                        cEtWorkSince.requestFocus();
                        return;
                    }

                    if(cEtJob.getText().length()==0)
                    {
                        Utils.customMessage(CProfileActivity.this,"Job Time Can't Be Blank");
                        cEtJob.requestFocus();
                        return;
                    }

                    CandidateProfile(id,cEtName.getText().toString(),cEtDob.getText().toString(),Gender,cEtMobile.getText().toString(),cEtAlternateNum.getText().toString(),cEtEmailid.getText().toString(),Marital,cEtMedu.getText().toString(),cEtCAdress.getText().toString(),cEtPAdress.getText().toString(),cEtSState.getText().toString(),cEtSCity.getText().toString(),cEtSLocation.getText().toString(),cEtReligion.getText().toString(),cEtCategory.getText().toString(),cEtSbCategory.getText().toString(),cEtServiceSkill.getText().toString(),cEtWorkSince.getText().toString(),cEtJob.getText().toString(),cEtExpSlry.getText().toString(),cEtResume.getText().toString());



                }

                else if(LoginedAs.equalsIgnoreCase("Agent"))
                {
                    if(cEtName.getText().length()==0)
                    {
                        Utils.customMessage(CProfileActivity.this,"Full Name Can't Be Blank");
                        cEtName.requestFocus();
                        return;
                    }

                    if(cEtMobile.getText().length()==0)
                    {
                        Utils.customMessage(CProfileActivity.this,"Mobile No Can't Be Blank");
                        cEtMobile.requestFocus();
                        return;
                    }

                    if(cEtSState.getText().length()==0)
                    {
                        Utils.customMessage(CProfileActivity.this,"State Can't Be Blank");
                        cEtSState.requestFocus();
                        return;
                    }

                    if(cEtSCity.getText().length()==0)
                    {
                        Utils.customMessage(CProfileActivity.this,"City Can't Be Blank");
                        cEtSCity.requestFocus();
                        return;
                    }

                    if(cEtCategory.getText().length()==0)
                    {
                        Utils.customMessage(CProfileActivity.this,"Category Can't Be Blank");
                        cEtCategory.requestFocus();
                        return;
                    }
                    if(cEtSbCategory.getText().length()==0)
                    {
                        Utils.customMessage(CProfileActivity.this,"Sub Category Can't Be Blank");
                        cEtSbCategory.requestFocus();
                        return;
                    }

                    if(cEtWorkSince.getText().length()==0)
                    {
                        Utils.customMessage(CProfileActivity.this,"Working Since Can't Be Blank");
                        cEtWorkSince.requestFocus();
                        return;
                    }

                    if(cEtJob.getText().length()==0)
                    {
                        Utils.customMessage(CProfileActivity.this,"Job Time Can't Be Blank");
                        cEtJob.requestFocus();
                        return;
                    }

                    if(Marital.equalsIgnoreCase("Select Marital Status"))
                    {
                        Utils.customMessage(CProfileActivity.this,"Please Select Marital Status");
                        return;
                    }

                    if(Gender.equalsIgnoreCase("Select Gender"))
                    {
                        Utils.customMessage(CProfileActivity.this,"Please Select Gender");
                        return;
                    }

                    if(getIntent().getStringExtra("Action")!=null && getIntent().getStringExtra("Action").equalsIgnoreCase("Update")  )
                    {
                        UpdateAgentCandidate(id,cEtName.getText().toString(),cEtDob.getText().toString(),Gender,cEtMobile.getText().toString(),cEtAlternateNum.getText().toString(),cEtEmailid.getText().toString(),Marital,cEtMedu.getText().toString(),cEtCAdress.getText().toString(),cEtPAdress.getText().toString(),cEtSState.getText().toString(),cEtSCity.getText().toString(),cEtSLocation.getText().toString(),cEtReligion.getText().toString(),cEtCategory.getText().toString(),cEtSbCategory.getText().toString(),cEtServiceSkill.getText().toString(),cEtWorkSince.getText().toString(),cEtJob.getText().toString(),cEtExpSlry.getText().toString(),cEtResume.getText().toString(),log_pass_text.getText().toString());
                    }
                    else
                    {
                        SaveCandidate(cEtName.getText().toString(),cEtDob.getText().toString(),Gender,cEtMobile.getText().toString(),cEtAlternateNum.getText().toString(),cEtEmailid.getText().toString(),Marital,cEtMedu.getText().toString(),cEtCAdress.getText().toString(),cEtPAdress.getText().toString(),cEtSState.getText().toString(),cEtSCity.getText().toString(),cEtSLocation.getText().toString(),cEtReligion.getText().toString(),cEtCategory.getText().toString(),cEtSbCategory.getText().toString(),cEtServiceSkill.getText().toString(),cEtWorkSince.getText().toString(),cEtJob.getText().toString(),cEtExpSlry.getText().toString(),cEtResume.getText().toString(),UserID);
                    }
                    }



            }
        });

        cEtDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(CProfileActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        monthOfYear = monthOfYear + 1;
                        cEtDob.setText(dayOfMonth + "/" + monthOfYear + "/" + year);

                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        cEtName = (TextInputEditText) findViewById(R.id.cEtName);
        cEtDob = (TextInputEditText) findViewById(R.id.cEtDob);
        cEtGender = (Spinner) findViewById(R.id.cEtGender);
        cEtMobile = (TextInputEditText) findViewById(R.id.cEtMobile);
        cEtAlternateNum = (TextInputEditText) findViewById(R.id.cEtAlternateNum);
        cEtMarital = (Spinner) findViewById(R.id.cEtMarital);
        cEtMedu = (TextInputEditText) findViewById(R.id.cEtMedu);
        cEtCAdress = (TextInputEditText) findViewById(R.id.cEtCAdress);
        cEtPAdress = (TextInputEditText) findViewById(R.id.cEtPAdress);
        cEtSState = (AutoCompleteTextView) findViewById(R.id.cEtSState);
        cEtSCity = (AutoCompleteTextView) findViewById(R.id.cEtSCity);
        cEtSLocation = (TextInputEditText) findViewById(R.id.cEtSLocation);
        cEtReligion = (TextInputEditText) findViewById(R.id.cEtReligion);
        cEtCategory = (AutoCompleteTextView) findViewById(R.id.cEtCategory);
        cEtSbCategory = (AutoCompleteTextView) findViewById(R.id.cEtSbCategory);
        cEtServiceSkill = (TextInputEditText) findViewById(R.id.cEtServiceSkill);
        cEtWorkSince = (TextInputEditText) findViewById(R.id.cEtWorkSince);
        cEtJob = (TextInputEditText) findViewById(R.id.cEtJob);
        cEtExpSlry = (TextInputEditText) findViewById(R.id.cEtExpSlry);
        cEtResume = (TextInputEditText) findViewById(R.id.cEtResume);
        cEtEmailid= (TextInputEditText) findViewById(R.id.cEtEmailid);
        log_pass_text= (TextInputEditText) findViewById(R.id.log_pass_text);
        update = (Button) findViewById(R.id.update);

        arrayMarital = ArrayAdapter.createFromResource(CProfileActivity.this, R.array.MaritalStatus, android.R.layout.simple_spinner_item);
        arrayMarital.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cEtMarital.setAdapter(arrayMarital);

        arrayGender = ArrayAdapter.createFromResource(CProfileActivity.this, R.array.GenderSpinner, android.R.layout.simple_spinner_item);
        arrayGender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cEtGender.setAdapter(arrayGender);

        Logined = SharePrefarence.getmInstance(CProfileActivity.this).getLogined();
        LoginedAs = SharePrefarence.getmInstance(CProfileActivity.this).getLoginAs();
        MobileNo = SharePrefarence.getmInstance(CProfileActivity.this).getMobile();
        Password = SharePrefarence.getmInstance(CProfileActivity.this).getPassword();
        UserID = SharePrefarence.getmInstance(CProfileActivity.this).getUser_Id();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitle("Profile");
        if(getIntent().getStringExtra("From")!=null)
        {
            if(getIntent().getStringExtra("From").equalsIgnoreCase("Agent"))
            {
                toolbar.setTitle("Add a Candidate");
                update.setText("Save Candidate");
            }

        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);



        dCurrentDate = Calendar.getInstance();
        day = dCurrentDate.get(Calendar.DAY_OF_MONTH);
        month = dCurrentDate.get(Calendar.MONTH);
        year = dCurrentDate.get(Calendar.YEAR);

        month = month + 1;
        cEtDob.setText(day + "/" + month + "/" + year);




    }

    private void getCagtegory() {

        Utils.showProgressDialog(CProfileActivity.this);
        ApiUtils.getAPIService().getCategory().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Categories>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();
                        GetState();


                        /*custOutstandingAdapter = new AreaOutstandingAdapter(AreaWiseOutstanding.this, mAreaModel);
                        recyclerView.setAdapter(custOutstandingAdapter);
                        txtTotal.setText(numberFormat.format(Amount) + "");
                        pdf=new AreaPDF(AreaWiseOutstanding.this, mAreaModel, "Area Wise Outstanding");*/
                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(CProfileActivity.this, e.getMessage().toString());
                    }

                    @Override
                    public void onNext(Categories currencyModel) {
                        if (currencyModel.isSuccess()) {
                            if (!currencyModel.getMessage().equalsIgnoreCase("Server Error!")) {
                                if (currencyModel.getMessage().equalsIgnoreCase("Success")) {
                                    mCategories = currencyModel.getData();
                                    CategoriesAdapter adapter = new CategoriesAdapter(CProfileActivity.this, mCategories);
                                    cEtCategory.setAdapter(adapter);

                                } else {
                                    //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                                    Utils.custoAlert(CProfileActivity.this, currencyModel.getMessage());

                                }
                            } else {
                                //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                                Utils.custoAlert(CProfileActivity.this, currencyModel.getMessage());
                            }
                        } else {
                            //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                            Utils.custoAlert(CProfileActivity.this, currencyModel.getMessage());
                        }
                    }
                });
    }

    private void getSubCategory(String CatName) {
        Utils.showProgressDialog(CProfileActivity.this);
        ApiUtils.getAPIService().getSubCategory(CatName).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SubCategory>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();

                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(CProfileActivity.this, e.getMessage().toString());
                    }

                    @Override
                    public void onNext(SubCategory gCity) {
                        if (gCity.isSuccess()) {
                            if (!gCity.getMessage().equalsIgnoreCase("Server Error!")) {
                                if (gCity.getMessage().equalsIgnoreCase("Success")) {
                                    mSubCat = gCity.getData();
                                    subCatAdapter = new SubCatAdapter(CProfileActivity.this, mSubCat);
                                    cEtSbCategory.setAdapter(subCatAdapter);

                                } else {
                                    //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                                    Utils.custoAlert(CProfileActivity.this, gCity.getMessage());
                                }
                            }
                        }
                    }
                });

    }


    private void CandidateProfile (String CandidateId, String FullName, String DOB, String
            Gender, String MobileNo, String AlternateNumbers, String
            EmailId, String MaritalStatus, String MaximumEducation, String ContactAddress,String PermanentAddress, String ServiceState, String ServiceCity, String
            ServiceLocation, String Religion, String Category, String
            Subcategory, String ServiceSkill, String WorkingSince, String JobTime,String ExpectedSalary,String ResumeText){
            Utils.showProgressDialog(CProfileActivity.this);
            ApiUtils.getAPIService().UCandidate(CandidateId, FullName, DOB, Gender, MobileNo, AlternateNumbers, EmailId, MaritalStatus, MaximumEducation, ContactAddress,PermanentAddress,ServiceState,ServiceCity,ServiceLocation,Religion,Category,Subcategory,ServiceSkill,WorkingSince,JobTime,ExpectedSalary,ResumeText).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UpdateCandidate>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(CProfileActivity.this, e.getMessage().toString());
                    }

                    @Override
                    public void onNext(UpdateCandidate cProfile) {

                        if (cProfile.getSuccess()) {
                            if (!cProfile.getMessage().equalsIgnoreCase("Server Error!")) {
                                if (cProfile.getMessage().equalsIgnoreCase("Update")) {

                                    Utils.customMessage(CProfileActivity.this,"Profile Update Successfully");
                                    finish();
//                                    SharePrefarence.getmInstance(UpdateProfileActivity.this).SeTLogined("1");

                                    // Toast.makeText(SignUp.this, "User Alredy Exist :(", Toast.LENGTH_SHORT).show();;

                                } else {
                                    // Toast.makeText(SignUp.this, "User Alredy Exist :(", Toast.LENGTH_SHORT).show();
                                    Utils.custoAlert(CProfileActivity.this, cProfile.getMessage());
                                }
                            }
                        }
                    }
                });
    }


    private void SaveCandidate (String FullName, String DOB, String
            Gender, String MobileNo, String AlternateNumbers, String
                                           EmailId, String MaritalStatus, String MaximumEducation, String ContactAddress,String PermanentAddress, String ServiceState, String ServiceCity, String
                                           ServiceLocation, String Religion, String Category, String
                                           Subcategory, String ServiceSkill, String WorkingSince, String JobTime,String ExpectedSalary,String ResumeText,String userID){
        Utils.showProgressDialog(CProfileActivity.this);
        ApiUtils.getAPIService().SaveCandidate( FullName, DOB, Gender, MobileNo, AlternateNumbers, EmailId, MaritalStatus, MaximumEducation, ContactAddress,PermanentAddress,ServiceState,ServiceCity,ServiceLocation,Religion,Category,Subcategory,ServiceSkill,WorkingSince,JobTime,ExpectedSalary,"image","resume",ResumeText,userID,"Android").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UpdateCandidate>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(CProfileActivity.this, e.getMessage().toString());
                    }

                    @Override
                    public void onNext(UpdateCandidate cProfile) {

                        if (cProfile.getSuccess()) {
                            if (!cProfile.getMessage().equalsIgnoreCase("Server Error!")) {
                                if (cProfile.getMessage().equalsIgnoreCase("Save")) {

                                    Utils.customMessage(CProfileActivity.this,"Profile Update Successfully");
                                    finish();
//                                    SharePrefarence.getmInstance(UpdateProfileActivity.this).SeTLogined("1");

                                    // Toast.makeText(SignUp.this, "User Alredy Exist :(", Toast.LENGTH_SHORT).show();;

                                }
                                else  if (cProfile.getMessage().equalsIgnoreCase("Exit")) {
                                    Utils.customMessage(CProfileActivity.this,"Mobile No Already Exist, In Our Records");
                                    return;
                                }
                                else {
                                    // Toast.makeText(SignUp.this, "User Alredy Exist :(", Toast.LENGTH_SHORT).show();
                                    Utils.custoAlert(CProfileActivity.this, cProfile.getMessage());
                                }
                            }
                        }
                    }
                });
    }


    private void UpdateAgentCandidate (String Candid,String FullName, String DOB, String
            Gender, String MobileNo, String AlternateNumbers, String
                                        EmailId, String MaritalStatus, String MaximumEducation, String ContactAddress,String PermanentAddress, String ServiceState, String ServiceCity, String
                                        ServiceLocation, String Religion, String Category, String
                                        Subcategory, String ServiceSkill, String WorkingSince, String JobTime,String ExpectedSalary,String ResumeText,String Password){
        Utils.showProgressDialog(CProfileActivity.this);
        ApiUtils.getAPIService().UpdateCandidateData( Candid,FullName, DOB, Gender, MobileNo, AlternateNumbers, EmailId, MaritalStatus, MaximumEducation, ContactAddress,PermanentAddress,ServiceState,ServiceCity,ServiceLocation,Religion,Category,Subcategory,ServiceSkill,WorkingSince,JobTime,ExpectedSalary,"image","resume",ResumeText,Password).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UpdateCandidate>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(CProfileActivity.this, e.getMessage().toString());
                    }

                    @Override
                    public void onNext(UpdateCandidate cProfile) {

                        if (cProfile.getSuccess()) {
                            if (!cProfile.getMessage().equalsIgnoreCase("Server Error!")) {
                                if (cProfile.getMessage().equalsIgnoreCase("Save")) {

                                    Utils.customMessage(CProfileActivity.this,"Profile Update Successfully");
                                    finish();
//                                    SharePrefarence.getmInstance(UpdateProfileActivity.this).SeTLogined("1");

                                    // Toast.makeText(SignUp.this, "User Alredy Exist :(", Toast.LENGTH_SHORT).show();;

                                }
                                else  if (cProfile.getMessage().equalsIgnoreCase("Exit")) {
                                    Utils.customMessage(CProfileActivity.this,"Mobile No Already Exist, In Our Records");
                                    return;
                                }
                                else {
                                    // Toast.makeText(SignUp.this, "User Alredy Exist :(", Toast.LENGTH_SHORT).show();
                                    Utils.custoAlert(CProfileActivity.this, cProfile.getMessage());
                                }
                            }
                        }
                    }
                });
    }


    private void loadCandidateProfile(String aMobile,String aPassword)
    {
        Utils.showProgressDialog(CProfileActivity.this);
        ApiUtils.getAPIService().getCandidateProfile(aMobile,aPassword).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CandidateLogin>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();
                        GetState();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(CProfileActivity.this, e.getMessage().toString());
                    }

                    @Override
                    public void onNext(CandidateLogin eProfile) {

                        if (eProfile.getSuccess()) {
                            if (!eProfile.getMessage().equalsIgnoreCase("Server Error!")) {
                                if (eProfile.getMessage().equalsIgnoreCase("Success")) {
                                    id=eProfile.getData().get(0).getCandidateId()+"";
                                    cEtName.setText(eProfile.getData().get(0).getFullName());
                                    cEtDob.setText(eProfile.getData().get(0).getDOB());
                                    try
                                    {
                                        cEtGender.setSelection(arrayGender.getPosition(eProfile.getData().get(0).getGender()));
                                    }
                                    catch (Exception ex)
                                    {

                                    }

                                    cEtMobile.setText(eProfile.getData().get(0).getMobileNo());
                                    cEtAlternateNum.setText(eProfile.getData().get(0).getAlternateNumbers());
                                    try
                                    {
                                        cEtMarital.setSelection(arrayGender.getPosition(eProfile.getData().get(0).getMaritalStatus()));
                                    }
                                    catch (Exception ex)
                                    {

                                    }

                                    cEtMedu .setText(eProfile.getData().get(0).getMaximumEducation());
                                    cEtCAdress.setText(eProfile.getData().get(0).getContactAddress());
                                    cEtPAdress.setText(eProfile.getData().get(0).getPermanentAddress());

                                    try
                                    {
                                        cEtSState .setText(eProfile.getData().get(0).getServiceState());
                                        cEtSCity.setText(eProfile.getData().get(0).getServiceCity());
                                        cEtCategory .setText(eProfile.getData().get(0).getCategoryName());
                                        cEtServiceSkill .setText(eProfile.getData().get(0).getServiceSkill());
                                    }
                                    catch (Exception ex)
                                    {

                                    }


                                    cEtSLocation .setText(eProfile.getData().get(0).getServiceLocation());
                                    cEtReligion.setText(eProfile.getData().get(0).getReligion());

                                    cEtSbCategory .setText(eProfile.getData().get(0).getSubcategoryName());

                                    cEtWorkSince.setText(eProfile.getData().get(0).getWorkingSince());
                                    cEtJob.setText(eProfile.getData().get(0).getJobTime());
                                    cEtExpSlry.setText(eProfile.getData().get(0).getExpectedSalary());
                                    cEtResume.setText(eProfile.getData().get(0).getResume());
                                    cEtEmailid.setText(eProfile.getData().get(0).getEmailId());





                                } else {
                                    // Toast.makeText(SignUp.this, "User Alredy Exist :(", Toast.LENGTH_SHORT).show();
                                    Utils.custoAlert(CProfileActivity.this, eProfile.getMessage());
                                }
                            }
                        }
                    }
                });
    }



    private void loadCandidateAgentProfile(int pos)
    {
        if(AgentCandidateList.mCandidate!=null)
        {
            if(AgentCandidateList.mCandidate.size()>0)
            {
                id=AgentCandidateList.mCandidate.get(pos).getCandidateId()+"";
                cEtName.setText(AgentCandidateList.mCandidate.get(pos).getFullName()+"");
                cEtDob.setText(AgentCandidateList.mCandidate.get(pos).getDOB()+"");
                try
                {
                    cEtGender.setSelection(arrayGender.getPosition(AgentCandidateList.mCandidate.get(pos).getGender()));
                }
                catch (Exception ex)
                {

                }

                cEtMobile.setText(AgentCandidateList.mCandidate.get(pos).getMobileNo()+"");
                cEtAlternateNum.setText(AgentCandidateList.mCandidate.get(pos).getAlternateNumbers()+"");
                try
                {
                    cEtMarital.setSelection(arrayGender.getPosition(AgentCandidateList.mCandidate.get(pos).getMaritalStatus()));
                }
                catch (Exception ex)
                {

                }

                cEtMedu .setText(AgentCandidateList.mCandidate.get(pos).getMaximumEducation()+"");
                cEtCAdress.setText(AgentCandidateList.mCandidate.get(pos).getContactAddress()+"");
                cEtPAdress.setText(AgentCandidateList.mCandidate.get(pos).getPermanentAddress()+"");

                try
                {
                    cEtSState .setText(AgentCandidateList.mCandidate.get(pos).getServiceState()+"");
                    cEtSCity.setText(AgentCandidateList.mCandidate.get(pos).getServiceCity()+"");
                    cEtCategory .setText(AgentCandidateList.mCandidate.get(pos).getCategoryName()+"");
                    cEtServiceSkill .setText(AgentCandidateList.mCandidate.get(pos).getServiceSkill()+"");
                }
                catch (Exception ex)
                {

                }


                cEtSLocation .setText(AgentCandidateList.mCandidate.get(pos).getServiceLocation()+"");
                cEtReligion.setText(AgentCandidateList.mCandidate.get(pos).getReligion()+"");

                cEtSbCategory .setText(AgentCandidateList.mCandidate.get(pos).getSubcategoryName()+"");

                cEtWorkSince.setText(AgentCandidateList.mCandidate.get(pos).getWorkingSince()+"");
                cEtJob.setText(AgentCandidateList.mCandidate.get(pos).getJobTime()+"");
                cEtExpSlry.setText(AgentCandidateList.mCandidate.get(pos).getExpectedSalary()+"");
                cEtResume.setText(AgentCandidateList.mCandidate.get(pos).getResume()+"");
                cEtEmailid.setText(AgentCandidateList.mCandidate.get(pos).getEmailId()+"");
                log_pass_text.setText(AgentCandidateList.mCandidate.get(pos).getPassword()+"");
                log_pass_text.setVisibility(View.VISIBLE);
            }
        }

    }


    private void GetState() {
        Utils.showProgressDialog(CProfileActivity.this);
        ApiUtils.getAPIService().getState().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetState>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();


                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(CProfileActivity.this, e.getMessage().toString());
                    }

                    @Override
                    public void onNext(GetState currencyModel) {
                        if (currencyModel.getSuccess()) {
                            if (!currencyModel.getMessage().equalsIgnoreCase("Server Error!")) {
                                if (currencyModel.getMessage().equalsIgnoreCase("Success")) {
                                    gtstate = currencyModel.getData();
                                    adapter = new StateAdapter(CProfileActivity.this, gtstate);
                                    cEtSState.setAdapter(adapter);
//                                    gtstate = currencyModel.getData();
//                                    adapter = new SpinnerAdapter(UpdateProfileActivity.this,gtstate);
//                                    Stateid.setAdapter(adapter);
                                } else {
                                    //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                                    Utils.custoAlert(CProfileActivity.this, currencyModel.getMessage());
                                }
                            }
                        }
                    }
                });

    }

    private void GetCity(String State) {
        Utils.showProgressDialog(CProfileActivity.this);
        ApiUtils.getAPIService().getCity(State).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetCity>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();

                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(CProfileActivity.this, e.getMessage().toString());
                    }

                    @Override
                    public void onNext(GetCity gCity) {
                        if (gCity.getSuccess()) {
                            if (!gCity.getMessage().equalsIgnoreCase("Server Error!")) {
                                if (gCity.getMessage().equalsIgnoreCase("Success")) {
                                    gtcity = gCity.getData();
                                    adapter2 = new CityAdapter(CProfileActivity.this, gtcity);
                                    cEtSCity.setAdapter(adapter2);

                                } else {
                                    //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                                    Utils.custoAlert(CProfileActivity.this, gCity.getMessage());
                                }
                            }
                        }
                    }
                });

    }
  /*  private void GetPincode(String State,String City ) {
        Utils.showProgressDialog(CProfileActivity.this);
        ApiUtils.getAPIService().getPincpode(State,City).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetPincode>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();

                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(CProfileActivity.this, e.getMessage().toString());
                    }

                    @Override
                    public void onNext(GetPincode gPincode) {
                        if (gPincode.getSuccess()) {
                            if (!gPincode.getMessage().equalsIgnoreCase("Server Error!")) {
                                if (gPincode.getMessage().equalsIgnoreCase("Success")) {
                                    gtpincode = gPincode.getData();
                                    adapter3 = new PincodeAdapter(CProfileActivity.this, gtpincode);
                                    .setAdapter(adapter3);

                                } else {
                                    //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                                    Utils.custoAlert(CProfileActivity.this, gPincode.getMessage());
                                }
                            }
                        }
                    }
                });

    }*/

//    private boolean checkValidation() {
//        if (isEmpty(cEtName)) {
//            cEtName.requestFocus();
//            cEtName.setError("Name Can't be Empty!");
//            return false;
//        }
//        if (isEmpty(cEtMobile)) {
//            cEtMobile.requestFocus();
//            cEtMobile.setError("Num Can't be Empty!");
//            return false;
//        }
//        if (isEmpty(cEtEmailid)) {
//            cEtEmailid.requestFocus();
//            cEtEmailid.setError("Email Adress cant be Empty");
//            return false;
//        }
//        if (isEmpty(cEtMedu)) {
//            cEtMedu.requestFocus();
//            cEtMedu.setError("Password cant be Empty");
//            return false;
//        }
//        if (isEmpty(cEtCAdress)) {
//            cEtCAdress.requestFocus();
//            cEtCAdress.setError("Enter Contact Adress");
//            return false;
//        }
////        if (isEmpty(rgRepass)) {
////            rgRepass.requestFocus();
////            rgRepass.setError("Retype Password");
////            return false;
////        }
//        return true;
//    }

//    private boolean isEmail(String email) {
//
//        boolean check = false;
//        final String Email_PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
//        CharSequence inputStr = email;
//
//        Pattern pattern = Pattern.compile(Email_PATTERN, Pattern.CASE_INSENSITIVE);
//        Matcher matcher = pattern.matcher(inputStr);
//
//        if (matcher.matches()) {
//            return true;
//        } else {
//            rgEmail.setError("Enter Valid Email Adress");
//            return false;
//        }
//
//
//    }
//
//    public boolean isEmpty(EditText etText) {
//        return etText.getText().toString().trim().length() <= 0;
//    }
//
//
//    private boolean isValidMobile(String phone) {
//        boolean check = false;
//        if (!Pattern.matches("[a-zA-Z]+", phone)) {
//            if (phone.length() < 10 || phone.length() > 10) {
//                // if(phone.length() != 10) {
//                check = false;
//                rgNum.setError("Not Valid Number");
//            } else {
//                check = true;
//            }
//        } else {
//            check = false;
//        }
//        return check;
//    }
//
//    public boolean isValidPassword(final String password) {
//
////        Pattern pattern;
////        Matcher matcher;
//        boolean pcheck = false;
//
//        if (password != null && password.length() >= 5) {
//            pcheck = true;
//
//        } else {
//
//            rgPass.setError("Password length should be above 5");
//            rgRepass.setError("Password not matched");
//
//
//        }
//
//
////        pattern = Pattern.compile(PASSWORD_PATTERN);
////        matcher = pattern.matcher(password);
//
//
//        return pcheck;
//
//    }
//
//
}
