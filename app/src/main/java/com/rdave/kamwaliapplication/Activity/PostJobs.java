package com.rdave.kamwaliapplication.Activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.rdave.kamwaliapplication.Adapter.CategoriesAdapter;
import com.rdave.kamwaliapplication.Adapter.CityAdapter;
import com.rdave.kamwaliapplication.Adapter.LanguageAdapter;
import com.rdave.kamwaliapplication.Adapter.PackagesAdapter;
import com.rdave.kamwaliapplication.Adapter.StateAdapter;
import com.rdave.kamwaliapplication.Adapter.SubCatAdapter;
import com.rdave.kamwaliapplication.Model.Categories;
import com.rdave.kamwaliapplication.Model.GetCity;
import com.rdave.kamwaliapplication.Model.GetEmpPostedJob;
import com.rdave.kamwaliapplication.Model.GetState;
import com.rdave.kamwaliapplication.Model.Language;
import com.rdave.kamwaliapplication.Model.PostCount;
import com.rdave.kamwaliapplication.Model.SubCategory;
import com.rdave.kamwaliapplication.R;
import com.rdave.kamwaliapplication.Utility.SharePrefarence;
import com.rdave.kamwaliapplication.Utility.Utils;
import com.rdave.kamwaliapplication.remote.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PostJobs extends AppCompatActivity {

    protected Toolbar toolbar;
    protected AutoCompleteTextView txtCatName;
    protected AutoCompleteTextView txtSubName;
    protected AutoCompleteTextView txtState;
    protected AutoCompleteTextView txtCity;
    protected TextInputEditText txtLocation;
    protected TextInputEditText txtJobType;
    protected TextInputEditText txtEducation;
    protected AutoCompleteTextView txtLanugage;
    protected TextInputEditText txtSalary;
    protected TextInputEditText txtDesciption;
    CheckBox chkStatus;
    protected Button submit;
    String Logined = "0";
    String LoginedAs = "0";
    String UserID="0";
    List<GetState.DataEntity> gtstate = new ArrayList<>();
    List<GetCity.DataEntity> gtcity = new ArrayList<>();
    List<SubCategory.DataBean> mSubCat = new ArrayList<>();
    StateAdapter adapter;
    CityAdapter adapter2;
    SubCatAdapter subCatAdapter;
   List<Categories.DataBean> mCategories = new ArrayList<>();
    List<Language.DataBean> mLanguage = new ArrayList<>();
    List<PackagesContent.DataEntity> pContent = new ArrayList<>();
    PackagesAdapter pAdapter;
    private List<PostCount.DataBean> postCount = new ArrayList<>();
    String Employerid;
    String JobID;
    String strStatus="Active";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.postjobs);
        initView();

        LoginedAs = SharePrefarence.getmInstance(PostJobs.this).getLoginAs();
        Logined = SharePrefarence.getmInstance(PostJobs.this).getLogined();
        UserID= SharePrefarence.getmInstance(PostJobs.this).getUser_Id();

        if(getIntent().getStringExtra("ID")!=null) {
            JobID = getIntent().getExtras().getString("ID");
            getEmployerjobId(JobID);
        }
        else
        {
            getCagtegory();
        }

        chkStatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    chkStatus.setChecked(true);
                    chkStatus.setText("Active");
                    strStatus="Active";
                }
                else{
                    chkStatus.setChecked(false);
                    chkStatus.setText("Deactive");
                    strStatus="Deactive";
                }
            }
        });



        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        txtState.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos,
                                    long id) {
                String Data = gtstate.get(pos).getState();
                GetCity(Data);

            }
        });

        txtCatName.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos,
                                    long id) {
                String Data = mCategories.get(pos).getCategoryName();
                getSubCategory(Data);

            }
        });



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Logined.equalsIgnoreCase("1"))
                {

                    if (LoginedAs.equalsIgnoreCase("Employer")) {

                        if(getIntent().getStringExtra("ID")!=null) {
                            updatePostJobs(JobID,UserID,txtCatName.getText().toString(),txtSubName.getText().toString(),txtState.getText().toString(),txtCity.getText().toString(),txtLocation.getText().toString(),txtJobType.getText().toString(),txtEducation.getText().toString(),txtLanugage.getText().toString(),txtSalary.getText().toString(),txtDesciption.getText().toString(),strStatus);
                        }else {
                            getPostCount(UserID);
                        }

                    }

                }

            }
        });
    }


    private void getPostCount(String EmployeeID) {
        Utils.showProgressDialog(PostJobs.this);
        ApiUtils.getAPIService().getPostCount(EmployeeID).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PostCount>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(PostJobs.this, e.getMessage().toString());
                    }

                    @Override
                    public void onNext(PostCount currencyModel) {
                        if (currencyModel.isSuccess()) {
                            if (!currencyModel.getMessage().equalsIgnoreCase("Server Error!")) {
                                if (currencyModel.getMessage().equalsIgnoreCase("Success")) {
                                    postCount = currencyModel.getData();
                                    if(postCount.get(0).getPostCount()>0)
                                    {
                                        postJobs(UserID,txtCatName.getText().toString(),txtSubName.getText().toString(),txtState.getText().toString(),txtCity.getText().toString(),txtLocation.getText().toString(),txtJobType.getText().toString(),txtEducation.getText().toString(),txtLanugage.getText().toString(),txtSalary.getText().toString(),txtDesciption.getText().toString());
                                    }
                                    else{
                                        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                                        View row = inflater.inflate(R.layout.activity_packages, null);
                                        final Dialog dialog = new Dialog(row.getContext());
                                        dialog.setContentView(row);
                                        dialog.setCancelable(true);

                                        final RecyclerView precyclerview = (RecyclerView) row.findViewById(R.id.package_reclerlist);
                                        precyclerview.setLayoutManager(new LinearLayoutManager(PostJobs.this, LinearLayoutManager.HORIZONTAL, false));




                                        Utils.showProgressDialog(PostJobs.this);
                                        ApiUtils.getAPIService().getPackages().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                                                .subscribe(new Subscriber<PackagesContent>() {
                                                    @Override
                                                    public void onCompleted() {
                                                        Utils.hideProgressDialog();

                                                    }

                                                    @Override
                                                    public void onError(Throwable e) {
                                                        Utils.hideProgressDialog();
                                                        Utils.customMessage(PostJobs.this, e.getMessage().toString());
                                                    }

                                                    @Override
                                                    public void onNext(PackagesContent packagesModel) {
                                                        if (packagesModel.getSuccess()) {
                                                            if (!packagesModel.getMessage().equalsIgnoreCase("Server Error!")) {
                                                                if (packagesModel.getMessage().equalsIgnoreCase("Success")) {
                                                                    pContent = packagesModel.getData();
                                                                    pAdapter = new PackagesAdapter(PostJobs.this, pContent);
                                                                    precyclerview.setAdapter(pAdapter);

                                                                } else {


                                                                    Utils.custoAlert(PostJobs.this, packagesModel.getMessage());
                                                                }
                                                            }
                                                        }
                                                    }
                                                });




                                    }
                                } else {
                                    //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                                    Utils.custoAlert(PostJobs.this, currencyModel.getMessage());
                                }
                            }
                        }
                    }
                });
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        txtCatName = (AutoCompleteTextView) findViewById(R.id.txt_CatName);
        txtSubName = (AutoCompleteTextView) findViewById(R.id.txt_SubName);
        txtState = (AutoCompleteTextView) findViewById(R.id.txt_State);
        txtCity = (AutoCompleteTextView) findViewById(R.id.txt_City);
        txtLocation = (TextInputEditText) findViewById(R.id.txt_Location);
        txtJobType = (TextInputEditText) findViewById(R.id.txt_JobType);
        txtEducation = (TextInputEditText) findViewById(R.id.txt_Education);
        txtLanugage = (AutoCompleteTextView) findViewById(R.id.txt_Lanugage);
        txtSalary = (TextInputEditText) findViewById(R.id.txt_Salary);
        txtDesciption = (TextInputEditText) findViewById(R.id.txt_Desciption);
        chkStatus=(CheckBox)findViewById(R.id.chkStatus);
        submit = (Button) findViewById(R.id.submit);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setTitle("Post Job");
    }

    private void getLanguage() {

        Utils.showProgressDialog(PostJobs.this);
        ApiUtils.getAPIService().getLanguage().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Language>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();


                        /*custOutstandingAdapter = new AreaOutstandingAdapter(AreaWiseOutstanding.this, mAreaModel);
                        recyclerView.setAdapter(custOutstandingAdapter);
                        txtTotal.setText(numberFormat.format(Amount) + "");
                        pdf=new AreaPDF(AreaWiseOutstanding.this, mAreaModel, "Area Wise Outstanding");*/
                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(PostJobs.this, e.getMessage().toString());
                    }

                    @Override
                    public void onNext(Language currencyModel) {
                        if (currencyModel.isSuccess()) {
                            if (!currencyModel.getMessage().equalsIgnoreCase("Server Error!")) {
                                if (currencyModel.getMessage().equalsIgnoreCase("Success")) {
                                    mLanguage = currencyModel.getData();

                                    LanguageAdapter langadapter = new LanguageAdapter(PostJobs.this, mLanguage);
                                    txtLanugage.setAdapter(langadapter);

                                } else {
                                    //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                                    Utils.custoAlert(PostJobs.this, currencyModel.getMessage());

                                }
                            } else {
                                //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                                Utils.custoAlert(PostJobs.this, currencyModel.getMessage());
                            }
                        } else {
                            //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                            Utils.custoAlert(PostJobs.this, currencyModel.getMessage());
                        }
                    }
                });
    }

    private void getCagtegory() {

        Utils.showProgressDialog(PostJobs.this);
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
                        Utils.customMessage(PostJobs.this, e.getMessage().toString());
                    }

                    @Override
                    public void onNext(Categories currencyModel) {
                        if (currencyModel.isSuccess()) {
                            if (!currencyModel.getMessage().equalsIgnoreCase("Server Error!")) {
                                if (currencyModel.getMessage().equalsIgnoreCase("Success")) {
                                    mCategories = currencyModel.getData();

                                    CategoriesAdapter adapter = new CategoriesAdapter(PostJobs.this, mCategories);
                                    txtCatName.setAdapter(adapter);

                                } else {
                                    //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                                    Utils.custoAlert(PostJobs.this, currencyModel.getMessage());

                                }
                            } else {
                                //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                                Utils.custoAlert(PostJobs.this, currencyModel.getMessage());
                            }
                        } else {
                            //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                            Utils.custoAlert(PostJobs.this, currencyModel.getMessage());
                        }
                    }
                });
    }

    private void GetState() {
        Utils.showProgressDialog(PostJobs.this);
        ApiUtils.getAPIService().getState().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetState>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();
                        getLanguage();

                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(PostJobs.this, e.getMessage().toString());
                    }

                    @Override
                    public void onNext(GetState currencyModel) {
                        if (currencyModel.getSuccess()) {
                            if (!currencyModel.getMessage().equalsIgnoreCase("Server Error!")) {
                                if (currencyModel.getMessage().equalsIgnoreCase("Success")) {
                                    gtstate = currencyModel.getData();
                                    adapter = new StateAdapter(PostJobs.this, gtstate);
                                    txtState.setAdapter(adapter);
//                                    gtstate = currencyModel.getData();
//                                    adapter = new SpinnerAdapter(UpdateProfileActivity.this,gtstate);
//                                    Stateid.setAdapter(adapter);
                                } else {
                                    //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                                    Utils.custoAlert(PostJobs.this, currencyModel.getMessage());
                                }
                            }
                        }
                    }
                });

    }




    private void GetCity(String State) {
        Utils.showProgressDialog(PostJobs.this);
        ApiUtils.getAPIService().getCity(State).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetCity>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();

                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(PostJobs.this, e.getMessage().toString());
                    }

                    @Override
                    public void onNext(GetCity gCity) {
                        if (gCity.getSuccess()) {
                            if (!gCity.getMessage().equalsIgnoreCase("Server Error!")) {
                                if (gCity.getMessage().equalsIgnoreCase("Success")) {
                                    gtcity = gCity.getData();
                                    adapter2 = new CityAdapter(PostJobs.this, gtcity);
                                    txtCity.setAdapter(adapter2);

                                } else {
                                    //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                                    Utils.custoAlert(PostJobs.this, gCity.getMessage());
                                }
                            }
                        }
                    }
                });

    }


    private void getSubCategory(String CatName) {
        Utils.showProgressDialog(PostJobs.this);
        ApiUtils.getAPIService().getSubCategory(CatName).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SubCategory>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();

                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(PostJobs.this, e.getMessage().toString());
                    }

                    @Override
                    public void onNext(SubCategory gCity) {
                        if (gCity.isSuccess()) {
                            if (!gCity.getMessage().equalsIgnoreCase("Server Error!")) {
                                if (gCity.getMessage().equalsIgnoreCase("Success")) {
                                    mSubCat = gCity.getData();
                                    subCatAdapter = new SubCatAdapter(PostJobs.this, mSubCat);
                                    txtSubName.setAdapter(subCatAdapter);

                                } else {
                                    //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                                    Utils.custoAlert(PostJobs.this, gCity.getMessage());
                                }
                            }
                        }
                    }
                });

    }

    private void postJobs(String aEmplrId,String aCategory,String aSubcategory,String aJobState,String aJobCity,String aJobLocation,String aJobTime,String aEducation,String aLanguage,String aGivenSalary,String aDescription)
    {
        Utils.showProgressDialog(PostJobs.this);
        ApiUtils.getAPIService().PostJobs(aEmplrId,aCategory,aSubcategory,aJobState,aJobCity,aJobLocation,aJobTime,aEducation,aLanguage,aGivenSalary,aDescription).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PackagesContent>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(PostJobs.this, e.getMessage().toString());
                    }

                    @Override
                    public void onNext(PackagesContent currencyModel) {
                        if (currencyModel.getSuccess()) {
                            if (!currencyModel.getMessage().equalsIgnoreCase("Server Error!")) {
                                if (currencyModel.getMessage().equalsIgnoreCase("Exit")) {
                                    Utils.customMessage(PostJobs.this,"Job Posted Successfully");
                                    finish();
                                } else {
                                    //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                                    Utils.custoAlert(PostJobs.this, currencyModel.getMessage());
                                }
                            }
                        }
                    }
                });
    }



    private void updatePostJobs(String aJobID,String aEmplrId,String aCategory,String aSubcategory,String aJobState,String aJobCity,String aJobLocation,String aJobTime,String aEducation,String aLanguage,String aGivenSalary,String aDescription,String aStatus)
    {
        Utils.showProgressDialog(PostJobs.this);
        ApiUtils.getAPIService().UpdatePostJobs(aJobID,aEmplrId,aCategory,aSubcategory,aJobState,aJobCity,aJobLocation,aJobTime,aEducation,aLanguage,aGivenSalary,aDescription,aStatus).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PackagesContent>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(PostJobs.this, e.getMessage().toString());
                    }

                    @Override
                    public void onNext(PackagesContent currencyModel) {
                        if (currencyModel.getSuccess()) {
                            if (!currencyModel.getMessage().equalsIgnoreCase("Server Error!")) {
                                if (currencyModel.getMessage().equalsIgnoreCase("Exit")) {
                                    Utils.customMessage(PostJobs.this,"Job Updated Successfully");
                                    finish();
                                } else {
                                    //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                                    Utils.custoAlert(PostJobs.this, currencyModel.getMessage());
                                }
                            }
                        }
                    }
                });
    }

    private void getEmployerjobId(String eJobid)
    {
        Utils.showProgressDialog(PostJobs.this);
        ApiUtils.getAPIService().getJobDetails(eJobid).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetEmpPostedJob>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(PostJobs.this, e.getMessage().toString());
                    }

                    @Override
                    public void onNext(GetEmpPostedJob currencyModel) {
                        if (currencyModel.getSuccess()) {
                            if (!currencyModel.getMessage().equalsIgnoreCase("Server Error!")) {
                                if (currencyModel.getMessage().equalsIgnoreCase("Success")) {
if(currencyModel.getData().size()>0)
{
    txtCatName.setText(currencyModel.getData().get(0).getCategoryName()+"");
    txtSubName.setText(currencyModel.getData().get(0).getSubcategoryName()+"");
    txtState.setText(currencyModel.getData().get(0).getJobState()+"");
    txtCity.setText(currencyModel.getData().get(0).getJobCity()+"");
    txtLocation.setText(currencyModel.getData().get(0).getJobLocation()+"");
    txtJobType.setText(currencyModel.getData().get(0).getJobTime()+"");
    txtEducation.setText(currencyModel.getData().get(0).getEducation()+"");
    txtLanugage.setText(currencyModel.getData().get(0).getLanguageName()+"");
    txtSalary.setText(currencyModel.getData().get(0).getGivenSalary()+"");
    txtDesciption.setText(currencyModel.getData().get(0).getDescription()+"");
    strStatus=currencyModel.getData().get(0).getStatus();
    if(currencyModel.getData().get(0).getStatus().equalsIgnoreCase("Active"))
    {

        chkStatus.setChecked(true);
        chkStatus.setText("Active");
    }
    else
    {
        chkStatus.setChecked(false);
        chkStatus.setText("Deactive");
    }
}



                                } else {
                                    //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                                    Utils.custoAlert(PostJobs.this, currencyModel.getMessage());
                                }
                            }
                        }
                    }
                });
    }


}
