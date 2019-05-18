package com.rdave.kamwaliapplication.Activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import com.rdave.kamwaliapplication.Adapter.CRAdapter;
import com.rdave.kamwaliapplication.Adapter.CategoriesAdapter;
import com.rdave.kamwaliapplication.Adapter.CityAdapter;
import com.rdave.kamwaliapplication.Adapter.PackagesAdapter;
import com.rdave.kamwaliapplication.Adapter.SubCatAdapter;
import com.rdave.kamwaliapplication.AdapterContent.CL_Content;
import com.rdave.kamwaliapplication.Model.Candidate;
import com.rdave.kamwaliapplication.Model.CandidateFilter;
import com.rdave.kamwaliapplication.Model.Categories;
import com.rdave.kamwaliapplication.Model.GetCity;
import com.rdave.kamwaliapplication.Model.SubCategory;
import com.rdave.kamwaliapplication.R;
import com.rdave.kamwaliapplication.Utility.Utils;
import com.rdave.kamwaliapplication.remote.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class C_listActivity extends AppCompatActivity {

    ImageButton backBtn;
    Button viewBtn;
    private List<CL_Content> content;
    private RecyclerView recyclerview;
    List<Candidate.DataBean> mCandidate=new ArrayList<>();
    private AutoCompleteTextView txtCandigate;
    public CRAdapter cListAdapter;
    String candicate,location;
    protected AutoCompleteTextView flcatgry;
    protected AutoCompleteTextView flsbcategry;
    protected AutoCompleteTextView fllocation;
    protected Spinner fljobtype;
    protected Spinner flsalary;
    protected Button flReset;
    protected Button flJob;
    List<Categories.DataBean> mCategories = new ArrayList<>();
    List<SubCategory.DataBean> mSubCat = new ArrayList<>();
    ImageView filter;
    BottomSheetBehavior bottomSheetBehavior;
    SubCatAdapter subCatAdapter;
    List<GetCity.DataEntity> mGetCity=new ArrayList<>();
    CityAdapter cityAdapter;
    private ArrayAdapter<CharSequence> arrayjbType;
    private ArrayAdapter<CharSequence> arraysalary;
    String jobType,salary;
   List<PackagesContent.DataEntity> pContent = new ArrayList<>();
    public PackagesAdapter pAdapter;
    BottomSheetDialog bottomSheetDialog;





    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_list);

        filter = (ImageView) findViewById(R.id.clfilter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitle("View Candidate");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                           }
        });
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        candicate = getIntent().getExtras().getString("V1");
        location = getIntent().getExtras().getString("V2");

        recyclerview = (RecyclerView) findViewById(R.id.h_recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        cListAdapter = new CRAdapter(this, mCandidate);
        //jrecyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));
        recyclerview.setAdapter(cListAdapter);
        if(candicate.isEmpty() && location.isEmpty()){

       // recyclerview.scrollToPosition(0);

        cList();


    }
    else {
            getCategory_Location(candicate,location);

        }

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(v);
            }
        });

        createBottomSheetDialog();





    }
//     @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.filter, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_filter) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//
//
//    }

    private void cList() {
        Utils.showProgressDialog(C_listActivity.this);
        ApiUtils.getAPIService().getCandidates().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Candidate>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();
                        getCagtegory();

                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(C_listActivity.this, e.getMessage().toString());
                    }

                    @Override
                    public void onNext(Candidate currencyModel) {
                        if (currencyModel.isSuccess()) {
                            if (!currencyModel.getMessage().equalsIgnoreCase("Server Error!")) {
                                if (currencyModel.getMessage().equalsIgnoreCase("Success")) {
                                    mCandidate = currencyModel.getData();
                                    cListAdapter = new CRAdapter(C_listActivity.this, mCandidate);
                                    recyclerview.setAdapter(cListAdapter);
                                } else {
                                    //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                                    Utils.custoAlert(C_listActivity.this, currencyModel.getMessage());
                                }
                            }
                        }
                    }
                });

    }
    private void getCategory_Location(String Category, String Location) {
        Utils.showProgressDialog(C_listActivity.this);
        ApiUtils.getAPIService().Searchcandidate(Category, Location).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Candidate>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(C_listActivity.this, e.getMessage().toString());
                    }

                    @Override
                    public void onNext(Candidate srchData) {

                        if (srchData.isSuccess()) {
                            if (!srchData.getMessage().equalsIgnoreCase("Server Error!")) {
                                if (srchData.getMessage().equalsIgnoreCase("Success")) {

                                    mCandidate = srchData.getData();
                                    cListAdapter = new CRAdapter(C_listActivity.this, mCandidate);
                                    recyclerview.setAdapter(cListAdapter);

                                    // Toast.makeText(SignUp.this, "User Alredy Exist :(", Toast.LENGTH_SHORT).show();;

                                } else {
                                    // Toast.makeText(SignUp.this, "User Alredy Exist :(", Toast.LENGTH_SHORT).show();
                                    Utils.custoAlert(C_listActivity.this, srchData.getMessage());
                                }
                            }
                        }
                    }
                });
    }

    private void filterC(String Category, String Subcategory, String Location, String JobType, String Salary) {
        Utils.showProgressDialog(C_listActivity.this);
        ApiUtils.getAPIService().getCFilter(Category, Subcategory, Location, JobType, Salary).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CandidateFilter>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(C_listActivity.this, e.getMessage().toString());
                    }

                    @Override
                    public void onNext(CandidateFilter cFilter) {

                        if (cFilter.getSuccess()) {
                            if (!cFilter.getMessage().equalsIgnoreCase("Server Error!")) {
                                if (cFilter.getMessage().equalsIgnoreCase("Success")) {

                                    bottomSheetDialog.dismiss();
                                } else {
                                    // Toast.makeText(SignUp.this, "User Alredy Exist :(", Toast.LENGTH_SHORT).show();
                                    Utils.custoAlert(C_listActivity.this, cFilter.getMessage());
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

            flReset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   flcatgry.getText().clear();
                    flsbcategry.getText().clear();
                    fllocation.getText().clear();
                    fljobtype.setSelection(0);
                    flsalary.setSelection(0);

                }
            });

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
                        Utils.customMessage(C_listActivity.this,"Category Can't Be Blank");
                        flcatgry.requestFocus();
                        return;
                    }
                    if(flsbcategry.getText().length()==0)
                    {
                        Utils.customMessage(C_listActivity.this,"Sub Category Can't Be Blank");
                        flsbcategry.requestFocus();
                        return;
                    }
                    if(fllocation.getText().length()==0)
                    {
                        Utils.customMessage(C_listActivity.this,"Sub Category Can't Be Blank");
                        fllocation.requestFocus();
                        return;
                    }
                    if(jobType.equalsIgnoreCase("Select Gender"))
                    {
                        Utils.customMessage(C_listActivity.this,"Please Select Gender");
                        return;
                    }
                    if(salary.equalsIgnoreCase("Select Gender"))
                    {
                        Utils.customMessage(C_listActivity.this,"Please Select Gender");
                        return;
                    }


                    filterC(flcatgry.getText().toString(),flsbcategry.getText().toString(),fllocation.getText().toString(),jobType,salary);



                }
            });


            arrayjbType = ArrayAdapter.createFromResource(C_listActivity.this, R.array.jobType, android.R.layout.simple_spinner_item);
            arrayjbType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            fljobtype.setAdapter(arrayjbType);

            arraysalary = ArrayAdapter.createFromResource(C_listActivity.this, R.array.salary, android.R.layout.simple_spinner_item);
            arraysalary.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            flsalary.setAdapter(arraysalary);


        }

    }

    public void showDialog(View view) {
        bottomSheetDialog.show();


    }







    private void getCagtegory() {

        Utils.showProgressDialog(C_listActivity.this);
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
                        Utils.customMessage(C_listActivity.this, e.getMessage().toString());
                    }

                    @Override
                    public void onNext(Categories currencyModel) {
                        if (currencyModel.isSuccess()) {
                            if (!currencyModel.getMessage().equalsIgnoreCase("Server Error!")) {
                                if (currencyModel.getMessage().equalsIgnoreCase("Success")) {
                                    mCategories = currencyModel.getData();
                                    CategoriesAdapter adapter = new CategoriesAdapter(C_listActivity.this, mCategories);
                                    flcatgry.setAdapter(adapter);

                                } else {
                                    //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                                    Utils.custoAlert(C_listActivity.this, currencyModel.getMessage());

                                }
                            } else {
                                //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                                Utils.custoAlert(C_listActivity.this, currencyModel.getMessage());
                            }
                        } else {
                            //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                            Utils.custoAlert(C_listActivity.this, currencyModel.getMessage());
                        }
                    }
                });
    }

    private void getSubCategory(String CatName) {
        Utils.showProgressDialog(C_listActivity.this);
        ApiUtils.getAPIService().getSubCategory(CatName).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SubCategory>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();

                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(C_listActivity.this, e.getMessage().toString());
                    }

                    @Override
                    public void onNext(SubCategory gCity) {
                        if (gCity.isSuccess()) {
                            if (!gCity.getMessage().equalsIgnoreCase("Server Error!")) {
                                if (gCity.getMessage().equalsIgnoreCase("Success")) {
                                    mSubCat = gCity.getData();
                                    subCatAdapter = new SubCatAdapter(C_listActivity.this, mSubCat);
                                    flsbcategry.setAdapter(subCatAdapter);

                                } else {
                                    //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                                    Utils.custoAlert(C_listActivity.this, gCity.getMessage());
                                }
                            }
                        }
                    }
                });

    }
    private void getCities() {

        Utils.showProgressDialog(C_listActivity.this);
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
                        Utils.customMessage(C_listActivity.this, e.getMessage().toString());
                    }

                    @Override
                    public void onNext(GetCity currencyModel) {
                        if (currencyModel.getSuccess()) {
                            if (!currencyModel.getMessage().equalsIgnoreCase("Server Error!")) {
                                if (currencyModel.getMessage().equalsIgnoreCase("Success")) {
                                    mGetCity = currencyModel.getData();

                                    cityAdapter = new CityAdapter(C_listActivity.this, mGetCity);
                                    fllocation.setAdapter(cityAdapter);

                                } else {
                                    //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                                    Utils.custoAlert(C_listActivity.this, currencyModel.getMessage());

                                }
                            } else {
                                //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                                Utils.custoAlert(C_listActivity.this, currencyModel.getMessage());
                            }
                        } else {
                            //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                            Utils.custoAlert(C_listActivity.this, currencyModel.getMessage());
                        }
                    }
                });
    }


//                    @Override
//                    public void onNext(PackagesContent packagesModel) {
//                        if (packagesModel.getSuccess()) {
//                            if (!packagesModel.getMessage().equalsIgnoreCase("Server Error!")) {
//                                if (packagesModel.getMessage().equalsIgnoreCase("Success")) {
//                                    pContent = packagesModel.getData();
//                                    pAdapter = new PackagesAdapter(C_listActivity.this, pContent);
//                                    precyclerview.setAdapter(pAdapter);
//                                } else {
//                                    //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
//                                    Utils.custoAlert(C_listActivity.this, packagesModel.getMessage());
//                                }
//                            }
//                        }
//                    }
//                });
//
//    }
    }
