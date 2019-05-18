package com.rdave.kamwaliapplication.Activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.rdave.kamwaliapplication.Adapter.CategoriesAdapter;
import com.rdave.kamwaliapplication.Adapter.CityAdapter;
import com.rdave.kamwaliapplication.Adapter.JobLAdapter;
import com.rdave.kamwaliapplication.Adapter.PackagesAdapter;
import com.rdave.kamwaliapplication.Model.Categories;
import com.rdave.kamwaliapplication.Model.GetCity;
import com.rdave.kamwaliapplication.Model.GetJob;
import com.rdave.kamwaliapplication.Model.PostCount;
import com.rdave.kamwaliapplication.R;
import com.rdave.kamwaliapplication.Utility.SharePrefarence;
import com.rdave.kamwaliapplication.Utility.Utils;
import com.rdave.kamwaliapplication.remote.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
//    private static final String TAG = "HomeActivity";

    private List<GetJob.DataEntity> jContent = new ArrayList<>();
    private List<PostCount.DataBean> postCount = new ArrayList<>();
    private RecyclerView jrecyclerview;
    private Button bJob;
    List<Categories.DataBean> mCategories = new ArrayList<>();
    private AutoCompleteTextView gCategory, gLocation;
    private ImageButton btnProfile;
    private Button btnPost, btnSearch;
    JobLAdapter getJobAdapter;
    CityAdapter cityAdapter;
    List<GetCity.DataEntity> mGetCity = new ArrayList<>();
    String Logined = "0";
    String LoginedAs = "0";
    String Username = "0";
    String Useremail = "0";
    String UserID = "0";
    DrawerLayout drawer;
    NavigationView navigationView;
    TextView hdUsername, hdEmailid;
    String Category;
    String Location;
    AlertDialog.Builder builder;
    AlertDialog dialog;
    List<PackagesContent.DataEntity> pContent = new ArrayList<>();
    PackagesAdapter pAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnPost = (Button) findViewById(R.id.btpost);
        btnSearch = (Button) findViewById(R.id.btSearch);
        gCategory = (AutoCompleteTextView) findViewById(R.id.txtCategory);
        gLocation = (AutoCompleteTextView) findViewById(R.id.srchlocation);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerview = navigationView.getHeaderView(0);
        hdUsername = (TextView) headerview.findViewById(R.id.usernameid);
        hdEmailid = (TextView) headerview.findViewById(R.id.useremailid);
        btnProfile = (ImageButton) headerview.findViewById(R.id.etProfile);

        LoginedAs = SharePrefarence.getmInstance(HomeActivity.this).getLoginAs();
        Logined = SharePrefarence.getmInstance(HomeActivity.this).getLogined();
        Username = SharePrefarence.getmInstance(HomeActivity.this).getUserName();
        Useremail = SharePrefarence.getmInstance(HomeActivity.this).getEmailid();
        UserID = SharePrefarence.getmInstance(HomeActivity.this).getUser_Id();

//
//        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
//                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);
//        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));
//        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
//            @Override
//            public void onPlaceSelected(Place place) {
//                // TODO: Get info about the selected place.
////                Log.i(TAG, "Place: " + place.getName() + ", " + place.getId());
//                Log.i(TAG, "Place Selected: " + place.getName());
//                gLocation.setText(getString(R.string.places_autocomplete_label, place
//                        .getName(), place.getAddress(), place.getPhoneNumber(), place
//                        .getWebsiteUri(), place.getRating(), place.getId()));
////                if (!TextUtils.isEmpty(place.getAttributions())) {
////                    attributionsTextView.setText(Html.fromHtml(place.getAttributions().toString()));
//            }
//
//            @Override
//            public void onError(@NonNull Status status) {
//                Log.e(TAG, "onError: Status = " + status.toString());
//                Toast.makeText(HomeActivity.this, "Place selection failed: " + status.getStatusMessage(),
//                        Toast.LENGTH_SHORT).show();
//
//
//
//            }
//
//        });


        if(LoginedAs.equalsIgnoreCase("0"))
        {
            btnProfile.setVisibility(View.GONE);
        }
        else{
            btnProfile.setVisibility(View.VISIBLE);
        }

        if (Logined.equalsIgnoreCase("1"))
        {
            if (LoginedAs.equalsIgnoreCase("Candidate")) {
                hideagentlogin();
                hdUsername.setText(Username);
                hdEmailid.setText(Useremail);
                hidepricing();
                hidelogin();
                NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                Menu nav_Menu = navigationView.getMenu();
                nav_Menu.findItem(R.id.nav_Dashboard).setVisible(false);
                nav_Menu.setGroupVisible(R.id.nav4,false);
                nav_Menu.setGroupVisible(R.id.nav6,false);
                nav_Menu.setGroupVisible(R.id.nav5,true);

            }
            if (LoginedAs.equalsIgnoreCase("Employer")) {
                hideagentlogin();
                hidelogin();
                hdUsername.setText(Username);
                hdEmailid.setText(Useremail);
                NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                Menu nav_Menu = navigationView.getMenu();
                nav_Menu.findItem(R.id.nav_Dashboard).setVisible(true);
                nav_Menu.setGroupVisible(R.id.nav4,true);
                nav_Menu.setGroupVisible(R.id.nav6,false);
                nav_Menu.setGroupVisible(R.id.nav5,false);
            }
            if (LoginedAs.equalsIgnoreCase("Agent")) {
                hdUsername.setText(Username);
                hdEmailid.setText(Useremail);
                hidepricing();
                hidelogin();
                NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                Menu nav_Menu = navigationView.getMenu();
                nav_Menu.findItem(R.id.nav_Dashboard).setVisible(false);
                nav_Menu.setGroupVisible(R.id.nav4,false);
                nav_Menu.setGroupVisible(R.id.nav6,true);
                nav_Menu.setGroupVisible(R.id.nav5,false);
            }
            if (LoginedAs.equalsIgnoreCase("0")) {


                NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                Menu nav_Menu = navigationView.getMenu();
                nav_Menu.findItem(R.id.nav_changepass).setVisible(false);
                nav_Menu.findItem(R.id.nav_signout).setVisible(false);
                nav_Menu.findItem(R.id.nav_Dashboard).setVisible(false);
                nav_Menu.setGroupVisible(R.id.nav4,false);
                nav_Menu.setGroupVisible(R.id.nav6,false);
                nav_Menu.setGroupVisible(R.id.nav5,false);
            }
        }
        else
        {

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            Menu nav_Menu = navigationView.getMenu();
            nav_Menu.findItem(R.id.nav_changepass).setVisible(false);
            nav_Menu.findItem(R.id.nav_signout).setVisible(false);
            nav_Menu.findItem(R.id.nav_Dashboard).setVisible(false);
            nav_Menu.setGroupVisible(R.id.nav4,false);
            nav_Menu.setGroupVisible(R.id.nav6,false);
            nav_Menu.setGroupVisible(R.id.nav5,false);
        }


        gCategory = (AutoCompleteTextView) findViewById(R.id.txtCategory);

        bJob = (Button) findViewById(R.id.job_list);
        bJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Logined.equalsIgnoreCase("1")) {

                    if (LoginedAs.equalsIgnoreCase("Candidate")) {


                        Intent j = new Intent(HomeActivity.this, JobActivity.class);
                        startActivity(j);
                    } else

                        Utils.customMessage(HomeActivity.this, "You Are Not Eligible For This Feature");
                }
                else{
                    Intent l = new Intent(HomeActivity.this, LoginActivity.class);
                    l.putExtra("UType","Candidate");
                    startActivity(l);
                }

            }

        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Category = gCategory.getText().toString();
                Location = gLocation.getText().toString();

                if (Logined.equalsIgnoreCase("1")){


                if (LoginedAs.equalsIgnoreCase("Candidate")) {

                    Utils.customMessage(HomeActivity.this, "You Are Not Eligible For This Feature");
                }
               else if (LoginedAs.equalsIgnoreCase("Agent")) {

                    Utils.customMessage(HomeActivity.this, "You Are Not Eligible For This Feature");
                }
                else {
                    Intent can = new Intent(HomeActivity.this, C_listActivity.class);
                    can.putExtra("V1", Category);
                    can.putExtra("V2", Location);
                    startActivity(can);
                }
                }
                else {
                    Intent log = new Intent(HomeActivity.this, LoginActivity.class);
                    log.putExtra("UType","Employer");
                    startActivity(log);

                }

            }
        });
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Logined.equalsIgnoreCase("1"))
                { if (LoginedAs.equalsIgnoreCase("Employer")) {

                    getPostCount(UserID);
                } else {
                    Toast.makeText(HomeActivity.this, "You Are Not Eligible For This Feature", Toast.LENGTH_SHORT).show();

                }

                }else{

                    Intent intent = new Intent (v.getContext(), LoginActivity.class);
                    intent.putExtra("UType","Employer");
                    startActivity(intent);

                }

            }
        });
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(LoginedAs.equalsIgnoreCase("Candidate")){

                    Intent c = new Intent(HomeActivity.this,CProfileActivity.class);
                    startActivity(c);

                }
                else{
                Intent ap = new Intent(HomeActivity.this, UpdateProfileActivity.class);
                startActivity(ap);
            }
            }
        });



        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        getCagtegory();

        jrecyclerview = (RecyclerView) findViewById(R.id.job_recyclerviewlist);
        getJobAdapter = new JobLAdapter(this, jContent);
        jrecyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        jrecyclerview.scrollToPosition(0);
        jrecyclerview.setNestedScrollingEnabled(false);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_home);
        setSupportActionBar(toolbar);


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.colorRed));
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            final AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
            builder.setTitle("Exit");
            builder.setMessage("Do you want to Exit?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    HomeActivity.super.onBackPressed();

                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();

                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();

        }

    }


    private void getJob() {
        Utils.showProgressDialog(HomeActivity.this);
        ApiUtils.getAPIService().getjob().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetJob>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(HomeActivity.this, e.getMessage().toString());
                    }

                    @Override
                    public void onNext(GetJob currencyModel) {
                        if (currencyModel.getSuccess()) {
                            if (!currencyModel.getMessage().equalsIgnoreCase("Server Error!")) {
                                if (currencyModel.getMessage().equalsIgnoreCase("Success")) {
                                    jContent = currencyModel.getData();
                                    getJobAdapter = new JobLAdapter(HomeActivity.this, jContent);
                                    jrecyclerview.setAdapter(getJobAdapter);
                                } else {
                                    //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                                    Utils.custoAlert(HomeActivity.this, currencyModel.getMessage());
                                }
                            }
                        }
                    }
                });
    }


    private void getPostCount(String EmployeeID) {
        Utils.showProgressDialog(HomeActivity.this);
        ApiUtils.getAPIService().getPostCount(EmployeeID).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PostCount>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(HomeActivity.this, e.getMessage().toString());
                    }

                    @Override
                    public void onNext(PostCount currencyModel) {
                        if (currencyModel.isSuccess()) {
                            if (!currencyModel.getMessage().equalsIgnoreCase("Server Error!")) {
                                if (currencyModel.getMessage().equalsIgnoreCase("Success")) {
                                    postCount = currencyModel.getData();
                                   if(postCount.get(0).getPostCount()>0)
                                   {
                                       Intent can = new Intent(HomeActivity.this, PostJobs.class);
                                       startActivity(can);
                                   }
                                   else{
                                       Utils.hideProgressDialog();
                                       LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                                       View row = inflater.inflate(R.layout.activity_packages, null);
                                       final Dialog dialog = new Dialog(row.getContext());
                                       dialog.setContentView(row);
                                       dialog.setCancelable(true);
                                       DisplayMetrics displayMetrics = new DisplayMetrics();
                                       getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                                       int displayWidth = displayMetrics.widthPixels;
                                       int displayHeight = displayMetrics.heightPixels;
                                       WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                                       layoutParams.copyFrom(dialog.getWindow().getAttributes());
                                       int dialogWindowWidth = (int) (displayWidth * 0.81f);
                                       layoutParams.width = dialogWindowWidth;
                                       dialog.getWindow().setAttributes(layoutParams);



                                       final RecyclerView precyclerview = (RecyclerView) row.findViewById(R.id.package_reclerlist);
                                       precyclerview.setLayoutManager(new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.HORIZONTAL, false));




                                       Utils.showProgressDialog(HomeActivity.this);
                                       ApiUtils.getAPIService().getPackages().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                                               .subscribe(new Subscriber<PackagesContent>() {
                                                   @Override
                                                   public void onCompleted() {
                                                       Utils.hideProgressDialog();

                                                   }

                                                   @Override
                                                   public void onError(Throwable e) {
                                                       Utils.hideProgressDialog();
                                                       Utils.customMessage(HomeActivity.this, e.getMessage().toString());
                                                   }

                                                   @Override
                                                   public void onNext(PackagesContent packagesModel) {
                                                       if (packagesModel.getSuccess()) {
                                                           if (!packagesModel.getMessage().equalsIgnoreCase("Server Error!")) {
                                                               if (packagesModel.getMessage().equalsIgnoreCase("Success")) {
                                                                   pContent = packagesModel.getData();
                                                                   pAdapter = new PackagesAdapter(HomeActivity.this, pContent);
                                                                   precyclerview.setAdapter(pAdapter);
                                                                   dialog.show();
                                                               } else {


                                                                   Utils.custoAlert(HomeActivity.this, packagesModel.getMessage());
                                                               }
                                                           }
                                                       }
                                                   }
                                               });




                                   }
                                } else {
                                    //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                                    Utils.custoAlert(HomeActivity.this, currencyModel.getMessage());
                                }
                            }
                        }
                    }
                });
    }


    private void displaySelectedScreen(int id) {
        Fragment fragment = null;

        switch (id) {
            case R.id.nav_login:
                Intent a = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(a);
                break;
            case R.id.nav_Dashboard:
                Intent dashboard = new Intent(HomeActivity.this, EmployerDashboard.class);
                startActivity(dashboard);
                break;
            case R.id.nav_pricing:
                Intent b = new Intent(HomeActivity.this, PackagesActivity.class);
                startActivity(b);
                break;
            case R.id.nav_rcandi:


                if (Logined.equalsIgnoreCase("1"))
                {

                    if (LoginedAs.equalsIgnoreCase("Agent")) {
                        Intent login = new Intent(HomeActivity.this, CProfileActivity.class);
                        login.putExtra("From","Agent");
                        startActivity(login);
                    }
                }else
                {
                    Intent login = new Intent(HomeActivity.this, LoginActivity.class);
                    login.putExtra("UType","Agent");
                    startActivity(login);
                }
                break;
            case R.id.nav_aboutus:
                Intent d = new Intent(HomeActivity.this, AboutusActivity.class);
                startActivity(d);
                break;
            case R.id.nav_Support:
                Intent e = new Intent(HomeActivity.this, SupportActivity.class);
                startActivity(e);
                break;
            case R.id.nav_ppolicy:
                Intent f = new Intent(HomeActivity.this, PPolicyActivity.class);
                startActivity(f);
                break;
            case R.id.nav_t_c:
                Intent g = new Intent(HomeActivity.this, TCActivity.class);
                startActivity(g);
                break;

            case R.id.nav_AppliedJob:
                Intent appliedjob = new Intent(HomeActivity.this, EmployerAppliedJob.class);
                startActivity(appliedjob);
                break;
            case R.id.nav_CandidateAppliedJob:
                Intent candidateappliedjob = new Intent(HomeActivity.this, CandidateAppliedJob.class);
                startActivity(candidateappliedjob);
                break;
            case R.id.nav_CandidateAgentList:
                Intent candidatelist = new Intent(HomeActivity.this, AgentCandidateList.class);
                startActivity(candidatelist);
                break;
            case R.id.nav_agentPaymentHistory:
                Intent paymentHistory = new Intent(HomeActivity.this, AgentPaymentHistory.class);
                startActivity(paymentHistory);
                break;
            case R.id.nav_PostJobs:
                Intent postjob = new Intent(HomeActivity.this, EmployerPostJob.class);
                startActivity(postjob);
                break;
            case R.id.nav_ViewedCandidate:
                Intent viewedCandidate = new Intent(HomeActivity.this, EmployerViewedCandidate.class);
                startActivity(viewedCandidate);
                break;
            case R.id.nav_Summary:
                Intent summary = new Intent(HomeActivity.this, EmployerAccountSummary.class);
                startActivity(summary);
                break;

            case R.id.nav_Payment:
                Intent payment = new Intent(HomeActivity.this, EmployerPaymentHistory.class);
                startActivity(payment);
                break;

            case R.id.nav_changepass:
                if(Logined.equalsIgnoreCase("1")) {
                    Intent cpass = new Intent(HomeActivity.this, ChangepassActivity.class);
                    startActivity(cpass);
                }
                break;



            case R.id.nav_signout:
                final AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                builder.setTitle("Sign Out");
                builder.setMessage("Do you want to sign out?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        SharePrefarence.getmInstance(HomeActivity.this).SeTLoginAs("0");
                        SharePrefarence.getmInstance(HomeActivity.this).setLogined("0");
                        SharePrefarence.getmInstance(HomeActivity.this).setUser_ID("0");
                        SharePrefarence.getmInstance(HomeActivity.this).setPassword("0");
                        SharePrefarence.getmInstance(HomeActivity.this).setMobileNo("0");
                        finish();
                        moveTaskToBack(true);
                        Intent l = new Intent(HomeActivity.this, LoginActivity.class);
                        startActivity(l);

                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

                break;

        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_home, fragment);
            ft.commit();
        }

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);


    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        displaySelectedScreen(id);
        return true;
    }


  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/


    private void getCagtegory() {

        Utils.showProgressDialog(HomeActivity.this);
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
                        Utils.customMessage(HomeActivity.this, e.getMessage().toString());
                    }

                    @Override
                    public void onNext(Categories currencyModel) {
                        if (currencyModel.isSuccess()) {
                            if (!currencyModel.getMessage().equalsIgnoreCase("Server Error!")) {
                                if (currencyModel.getMessage().equalsIgnoreCase("Success")) {
                                    mCategories = currencyModel.getData();

                                    CategoriesAdapter adapter = new CategoriesAdapter(HomeActivity.this, mCategories);
                                    gCategory.setAdapter(adapter);

                                } else {
                                    //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                                    Utils.custoAlert(HomeActivity.this, currencyModel.getMessage());

                                }
                            } else {
                                //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                                Utils.custoAlert(HomeActivity.this, currencyModel.getMessage());
                            }
                        } else {
                            //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                            Utils.custoAlert(HomeActivity.this, currencyModel.getMessage());
                        }
                    }
                });
    }


    private void getCities() {

        Utils.showProgressDialog(HomeActivity.this);
        ApiUtils.getAPIService().getCities().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetCity>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();
                        getJob();
                        /*custOutstandingAdapter = new AreaOutstandingAdapter(AreaWiseOutstanding.this, mAreaModel);
                        recyclerView.setAdapter(custOutstandingAdapter);
                        txtTotal.setText(numberFormat.format(Amount) + "");
                        pdf=new AreaPDF(AreaWiseOutstanding.this, mAreaModel, "Area Wise Outstanding");*/
                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(HomeActivity.this, e.getMessage().toString());
                    }

                    @Override
                    public void onNext(GetCity currencyModel) {
                        if (currencyModel.getSuccess()) {
                            if (!currencyModel.getMessage().equalsIgnoreCase("Server Error!")) {
                                if (currencyModel.getMessage().equalsIgnoreCase("Success")) {
                                    mGetCity = currencyModel.getData();

                                   cityAdapter = new CityAdapter(HomeActivity.this, mGetCity);
                                    gLocation.setAdapter(cityAdapter);

                                } else {
                                    //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                                    Utils.custoAlert(HomeActivity.this, currencyModel.getMessage());

                                }
                            } else {
                                //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                                Utils.custoAlert(HomeActivity.this, currencyModel.getMessage());
                            }
                        } else {
                            //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                            Utils.custoAlert(HomeActivity.this, currencyModel.getMessage());
                        }
                    }
                });
    }


    private void hidelogin() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.nav_login).setVisible(false);
    }

    private void hidesignout() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.nav_signout).setVisible(false);
    }

    private void hideagentlogin() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.nav_rcandi).setVisible(false);

    }

    private void hidepricing() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.nav_pricing).setVisible(false);
    }

//    public void open_dialog() {
//        builder = new AlertDialog.Builder(this);
//        LayoutInflater inflater = (LayoutInflater) this.getSystemService(getBaseContext().LAYOUT_INFLATER_SERVICE);
//        View row = inflater.inflate(R.layout.activity_packages, null);
//
//
//
//        builder.setView(row);
//        dialog = builder.create();
//        dialog.show();
//    }

}




