package com.rdave.kamwaliapplication.Activity;

import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.rdave.kamwaliapplication.Adapter.CategoriesAdapter;
import com.rdave.kamwaliapplication.Adapter.CityAdapter;
import com.rdave.kamwaliapplication.Adapter.JobLAdapter;
import com.rdave.kamwaliapplication.Adapter.SubCatAdapter;
import com.rdave.kamwaliapplication.AdapterContent.J10list_Content;
import com.rdave.kamwaliapplication.Model.Categories;
import com.rdave.kamwaliapplication.Model.Filter;
import com.rdave.kamwaliapplication.Model.GetCity;
import com.rdave.kamwaliapplication.Model.GetJob;
import com.rdave.kamwaliapplication.Model.JobSearch;
import com.rdave.kamwaliapplication.Model.SubCategory;
import com.rdave.kamwaliapplication.R;
import com.rdave.kamwaliapplication.Utility.Utils;
import com.rdave.kamwaliapplication.remote.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class JobActivity extends AppCompatActivity {

    protected AutoCompleteTextView flcatgry;
    protected AutoCompleteTextView flsbcategry;
    protected AutoCompleteTextView fllocation;
    protected Spinner fljobtype;
    protected Spinner flsalary;
    protected Button flReset;
    protected Button flJob;
    private List<J10list_Content> jLContent;
    private RecyclerView jLrecyclerview;
    List<Categories.DataBean> mCategories = new ArrayList<>();
    List<SubCategory.DataBean> mSubCat = new ArrayList<>();
    List<GetJob.DataEntity> getJob = new ArrayList<>();
    private AutoCompleteTextView txtGetJob;
    public JobLAdapter getJobAdapter;
    BottomSheetDialog bottomSheetDialog;
    ImageView filter;
    BottomSheetBehavior bottomSheetBehavior;
    SubCatAdapter subCatAdapter;
    List<GetCity.DataEntity> mGetCity=new ArrayList<>();
    CityAdapter cityAdapter;
    private ArrayAdapter<CharSequence> arrayjbType;
    private ArrayAdapter<CharSequence> arraysalary;
    String jobType,salary;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_job);

        filter = (ImageView) findViewById(R.id.idFilter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitle("Job List");
        jLrecyclerview = (RecyclerView) findViewById(R.id.jL_Rlist);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(v);


            }
        });

        createBottomSheetDialog();

        jLrecyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        // jLrecyclerview.scrollToPosition(0);
        // jLrecyclerview.setNestedScrollingEnabled(false);
        getJob();



    }

    private void getJob() {
        Utils.showProgressDialog(JobActivity.this);
        ApiUtils.getAPIService().getjob().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetJob>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();
                        getCategory();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(JobActivity.this, e.getMessage().toString());
                    }

                    @Override
                    public void onNext(GetJob currencyModel) {
                        if (currencyModel.getSuccess()) {
                            if (!currencyModel.getMessage().equalsIgnoreCase("Server Error!")) {
                                if (currencyModel.getMessage().equalsIgnoreCase("Success")) {
                                    getJob = currencyModel.getData();
                                    getJobAdapter = new JobLAdapter(JobActivity.this, getJob);
                                    jLrecyclerview.setAdapter(getJobAdapter);
                                } else {
                                    //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                                    Utils.custoAlert(JobActivity.this, currencyModel.getMessage());
                                }
                            }
                        }
                    }
                });
    }

    private void getJobsearch(String Category, String Location) {
        Utils.showProgressDialog(JobActivity.this);
        ApiUtils.getAPIService().Searchjob(Category, Location).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<JobSearch>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(JobActivity.this, e.getMessage().toString());
                    }

                    @Override
                    public void onNext(JobSearch srchJob) {

                        if (srchJob.getSuccess()) {
                            if (!srchJob.getMessage().equalsIgnoreCase("Server Error!")) {
                                if (srchJob.getMessage().equalsIgnoreCase("Save")) {

                                    // Toast.makeText(SignUp.this, "User Alredy Exist :(", Toast.LENGTH_SHORT).show();
                                } else {
                                    // Toast.makeText(SignUp.this, "User Alredy Exist :(", Toast.LENGTH_SHORT).show();
                                    Utils.custoAlert(JobActivity.this, srchJob.getMessage());
                                }
                            }
                        }
                    }
                });
    }

    private void filterJob(String Category, String Subcategory, String Location, String JobType, String Salary) {
        Utils.showProgressDialog(JobActivity.this);
        ApiUtils.getAPIService().getFilter(Category, Subcategory, Location, JobType, Salary).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Filter>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(JobActivity.this, e.getMessage().toString());
                    }

                    @Override
                    public void onNext(Filter jFilter) {

                        if (jFilter.getSuccess()) {
                            if (!jFilter.getMessage().equalsIgnoreCase("Server Error!")) {
                                if (jFilter.getMessage().equalsIgnoreCase("Success")) {

                                    bottomSheetDialog.dismiss();
                                } else {
                                    // Toast.makeText(SignUp.this, "User Alredy Exist :(", Toast.LENGTH_SHORT).show();
                                    Utils.custoAlert(JobActivity.this, jFilter.getMessage());
                                }
                            }
                        }
                    }
                });
    }

    private void createBottomSheetDialog() {
        if (bottomSheetDialog == null) {
            View view = LayoutInflater.from(this).inflate(R.layout.filter_list, null);
            bottomSheetDialog = new BottomSheetDialog(this);
            bottomSheetDialog.setContentView(view);
            View parent = (View) view.getParent();
            parent.setFitsSystemWindows(true);
            bottomSheetBehavior = BottomSheetBehavior.from(parent);
            view.measure(0, 0);
            bottomSheetBehavior.setPeekHeight(view.getMeasuredHeight());

            flcatgry = (AutoCompleteTextView) view.findViewById(R.id.flcatgry);
            flsbcategry = (AutoCompleteTextView) view.findViewById(R.id.flsbcategry);
            fllocation = (AutoCompleteTextView) view.findViewById(R.id.fllocation);
            fljobtype = (Spinner) view.findViewById(R.id.fljobtype);
            flsalary = (Spinner) view.findViewById(R.id.flsalary);
            flReset = (Button) view.findViewById(R.id.flReset);
            flJob = (Button) view.findViewById(R.id.flJob);

            fljobtype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    jobType=adapterView.getItemAtPosition(i).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            flsalary.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    salary=adapterView.getItemAtPosition(i).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });



            flcatgry.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View arg1, int pos,
                                        long id) {
                    String Data = mCategories.get(pos).getCategoryName();
                    getSubCategory(Data);

                }
            });


            flsbcategry.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {


                }
            });

            flJob.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                    if(flcatgry.getText().length()==0)
                    {
                        Utils.customMessage(JobActivity.this,"Category Can't Be Blank");
                        flcatgry.requestFocus();
                        return;
                    }
                    if(flsbcategry.getText().length()==0)
                    {
                        Utils.customMessage(JobActivity.this,"Sub Category Can't Be Blank");
                        flsbcategry.requestFocus();
                        return;
                    }
                    if(fllocation.getText().length()==0)
                    {
                        Utils.customMessage(JobActivity.this,"Sub Category Can't Be Blank");
                        fllocation.requestFocus();
                        return;
                    }
                    if(jobType.equalsIgnoreCase("Select Gender"))
                    {
                        Utils.customMessage(JobActivity.this,"Please Select Gender");
                        return;
                    }
                    if(salary.equalsIgnoreCase("Select Gender"))
                    {
                        Utils.customMessage(JobActivity.this,"Please Select Gender");
                        return;
                    }


                    filterJob(flcatgry.getText().toString(),flsbcategry.getText().toString(),fllocation.getText().toString(),jobType,salary);



                }
            });


            arrayjbType = ArrayAdapter.createFromResource(JobActivity.this, R.array.jobType, android.R.layout.simple_spinner_item);
            arrayjbType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            fljobtype.setAdapter(arrayjbType);

            arraysalary = ArrayAdapter.createFromResource(JobActivity.this, R.array.salary, android.R.layout.simple_spinner_item);
            arraysalary.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            flsalary.setAdapter(arraysalary);


        }

    }

    public void showDialog(View view) {
        bottomSheetDialog.show();


    }







    private void getCategory() {

        Utils.showProgressDialog(JobActivity.this);
        ApiUtils.getAPIService().getCategory().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Categories>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();
                        getCities();



                        /*custOutstandingAdapter = new AreaOutstandingAdapter(AreaWiseOutstanding.this, mAreaModel);
                        recyclerView.setAdapter(custOutstandingAdapter);
                        txtTotal.setText(numberFormat.format(Amount) + "");
                        pdf=new AreaPDF(AreaWiseOutstanding.this, mAreaModel, "Area Wise Outstanding");*/
                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(JobActivity.this, e.getMessage().toString());
                    }

                    @Override
                    public void onNext(Categories currencyModel) {
                        if (currencyModel.isSuccess()) {
                            if (!currencyModel.getMessage().equalsIgnoreCase("Server Error!")) {
                                if (currencyModel.getMessage().equalsIgnoreCase("Success")) {
                                    mCategories = currencyModel.getData();
                                    CategoriesAdapter adapter = new CategoriesAdapter(JobActivity.this, mCategories);
                                    flcatgry.setAdapter(adapter);

                                } else {
                                    //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                                    Utils.custoAlert(JobActivity.this, currencyModel.getMessage());

                                }
                            } else {
                                //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                                Utils.custoAlert(JobActivity.this, currencyModel.getMessage());
                            }
                        } else {
                            //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                            Utils.custoAlert(JobActivity.this, currencyModel.getMessage());
                        }
                    }
                });
    }

            private void getSubCategory(String CatName) {
                Utils.showProgressDialog(JobActivity.this);
                ApiUtils.getAPIService().getSubCategory(CatName).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<SubCategory>() {
                            @Override
                            public void onCompleted() {
                                Utils.hideProgressDialog();

                            }

                            @Override
                            public void onError(Throwable e) {
                                Utils.hideProgressDialog();
                                Utils.customMessage(JobActivity.this, e.getMessage().toString());
                            }

                            @Override
                            public void onNext(SubCategory gCity) {
                                if (gCity.isSuccess()) {
                                    if (!gCity.getMessage().equalsIgnoreCase("Server Error!")) {
                                        if (gCity.getMessage().equalsIgnoreCase("Success")) {
                                            mSubCat = gCity.getData();
                                            subCatAdapter = new SubCatAdapter(JobActivity.this, mSubCat);
                                            flsbcategry.setAdapter(subCatAdapter);

                                        } else {
                                            //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                                            Utils.custoAlert(JobActivity.this, gCity.getMessage());
                                        }
                                    }
                                }
                            }
                        });

            }
    private void getCities() {

        Utils.showProgressDialog(JobActivity.this);
        ApiUtils.getAPIService().getCities().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetCity>() {
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
                        Utils.customMessage(JobActivity.this, e.getMessage().toString());
                    }

                    @Override
                    public void onNext(GetCity currencyModel) {
                        if (currencyModel.getSuccess()) {
                            if (!currencyModel.getMessage().equalsIgnoreCase("Server Error!")) {
                                if (currencyModel.getMessage().equalsIgnoreCase("Success")) {
                                    mGetCity = currencyModel.getData();

                                    cityAdapter = new CityAdapter(JobActivity.this, mGetCity);
                                    fllocation.setAdapter(cityAdapter);

                                } else {
                                    //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                                    Utils.custoAlert(JobActivity.this, currencyModel.getMessage());

                                }
                            } else {
                                //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                                Utils.custoAlert(JobActivity.this, currencyModel.getMessage());
                            }
                        } else {
                            //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                            Utils.custoAlert(JobActivity.this, currencyModel.getMessage());
                        }
                    }
                });
    }


        }
