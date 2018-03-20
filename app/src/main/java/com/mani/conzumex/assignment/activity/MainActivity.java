package com.mani.conzumex.assignment.activity;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mani.conzumex.assignment.R;
import com.mani.conzumex.assignment.adapters.ActivityAdapter;
import com.mani.conzumex.assignment.modal.ActivityData;
import com.mani.conzumex.assignment.sqlite.SqliteHelper;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    FloatingActionButton addTask;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<ActivityData> tdd = new ArrayList<>();
    SqliteHelper mysqlite;
    SwipeRefreshLayout swipeRefreshLayout;
    Button setStartTime,setEndTime,setStartDate,setEndDate;
    private int mYear, mMonth, mDay, mHour, mMinute;
    String startDate,startTime,endDate,endTime;
    SqliteHelper mySqliteHelper;
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mySqliteHelper = new SqliteHelper(this);
        setContentView(R.layout.activity_main);
        //get firebase auth instance
        auth = FirebaseAuth.getInstance();

        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_s);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        addTask = (FloatingActionButton) findViewById(R.id.imageButton);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        adapter = new ActivityAdapter(tdd, getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.accent), getResources().getColor(R.color.divider));
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                updateCardView();
            }
        });
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.custom_dailog);
                dialog.show();
                setStartTime=(Button)dialog.findViewById(R.id.buttonSetStartTime);
                setEndTime=(Button)dialog.findViewById(R.id.buttonSetEndTime);
                setStartDate=(Button)dialog.findViewById(R.id.buttonSetStartDate);
                setEndDate=(Button)dialog.findViewById(R.id.buttonSetEndDate);
                Button save = (Button) dialog.findViewById(R.id.btn_save);
                Button cancel = (Button) dialog.findViewById(R.id.btn_cancel);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                setStartDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Get Current Date
                        final Calendar c = Calendar.getInstance();
                        mYear = c.get(Calendar.YEAR);
                        mMonth = c.get(Calendar.MONTH);
                        mDay = c.get(Calendar.DAY_OF_MONTH);


                        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                                new DatePickerDialog.OnDateSetListener() {

                                    @Override
                                    public void onDateSet(DatePicker view, int year,
                                                          int monthOfYear, int dayOfMonth) {

                                        //txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                       // Toast.makeText(MainActivity.this,dayOfMonth + "-" + (monthOfYear + 1) + "-" + year,Toast.LENGTH_LONG).show();
                                        startDate=dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                                    }
                                }, mYear, mMonth, mDay);
                        datePickerDialog.show();

                    }
                });
                setStartTime.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Get Current Time
                        final Calendar c = Calendar.getInstance();
                        mHour = c.get(Calendar.HOUR_OF_DAY);
                        mMinute = c.get(Calendar.MINUTE);

                        // Launch Time Picker Dialog
                        TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,
                                new TimePickerDialog.OnTimeSetListener() {

                                    @Override
                                    public void onTimeSet(TimePicker view, int hourOfDay,
                                                          int minute) {
                                        startTime=hourOfDay + ":" + minute;

                                    }
                                }, mHour, mMinute, false);
                        timePickerDialog.show();

                    }
                });
                setEndDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Get Current Date
                        final Calendar c = Calendar.getInstance();
                        mYear = c.get(Calendar.YEAR);
                        mMonth = c.get(Calendar.MONTH);
                        mDay = c.get(Calendar.DAY_OF_MONTH);


                        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                                new DatePickerDialog.OnDateSetListener() {

                                    @Override
                                    public void onDateSet(DatePicker view, int year,
                                                          int monthOfYear, int dayOfMonth) {

                                        //txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                    //    Toast.makeText(MainActivity.this,dayOfMonth + "-" + (monthOfYear + 1) + "-" + year,Toast.LENGTH_LONG).show();
                                        endDate=dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                                    }
                                }, mYear, mMonth, mDay);
                        datePickerDialog.show();

                    }
                });
                setEndTime.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Get Current Time
                        final Calendar c = Calendar.getInstance();
                        mHour = c.get(Calendar.HOUR_OF_DAY);
                        mMinute = c.get(Calendar.MINUTE);

                        // Launch Time Picker Dialog
                        TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,
                                new TimePickerDialog.OnTimeSetListener() {

                                    @Override
                                    public void onTimeSet(TimePicker view, int hourOfDay,
                                                          int minute) {
                                     //   Toast.makeText(MainActivity.this,hourOfDay + ":" + minute,Toast.LENGTH_LONG).show();
                                        endTime=hourOfDay + ":" + minute;
                                    }
                                }, mHour, mMinute, false);
                        timePickerDialog.show();

                    }
                });
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EditText todoText = (EditText) dialog.findViewById(R.id.input_task_desc);
                        if (todoText.getText().length() >= 2) {
                            RadioGroup proritySelection = (RadioGroup) dialog.findViewById(R.id.toDoRG);
                            String RadioSelection = new String();
                            if (proritySelection.getCheckedRadioButtonId() != -1) {
                                int id = proritySelection.getCheckedRadioButtonId();
                                View radiobutton = proritySelection.findViewById(id);
                                int radioId = proritySelection.indexOfChild(radiobutton);
                                RadioButton btn = (RadioButton) proritySelection.getChildAt(radioId);
                                RadioSelection = (String) btn.getText();
                            }
                            boolean isEmpty = startTime == null || startTime.trim().length() == 0;
                            boolean isEmpty2 = endTime == null || endTime.trim().length() == 0;
                            boolean isEmpty3 = startDate == null || startDate.trim().length() == 0;

                            boolean isEmpty4 = endDate == null || endDate.trim().length() == 0;
                            if(isEmpty || isEmpty2  || isEmpty3 || isEmpty4){
                                Toast.makeText(MainActivity.this,"Please Select All Time and Dates",Toast.LENGTH_LONG).show();
                                return;
                            }
                                ContentValues contentValues = new ContentValues();
                                contentValues.put("ActivityName", todoText.getText().toString());
                                contentValues.put("ActivityIntensity", RadioSelection);
                                contentValues.put("ActivityStartTimeDate", startTime + " " + startDate);
                                contentValues.put("ActivityEndTimeDate", endTime + " " + endDate);

                            mysqlite = new SqliteHelper(getApplicationContext());
                            Boolean b = mysqlite.insertInto(contentValues);
                            if (b) {
                                dialog.hide();
                                updateCardView();
                                startTime=null;
                                endDate=null;
                                startDate=null;
                                endTime=null;
                            } else {
                                Toast.makeText(getApplicationContext(), "Some thing went wrong", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(getApplicationContext(), "Please enter Activity Name", Toast.LENGTH_SHORT).show();
                        }


                    }
                });

            }
        });
    }
    public void updateCardView() {
        swipeRefreshLayout.setRefreshing(true);
        mysqlite = new SqliteHelper(getApplicationContext());
        Cursor result = mysqlite.selectAllData();
        if (result.getCount() == 0) {
            tdd.clear();
            adapter.notifyDataSetChanged();
            Toast.makeText(getApplicationContext(), "No Activities Added", Toast.LENGTH_SHORT).show();
        } else {
            tdd.clear();
            adapter.notifyDataSetChanged();
            while (result.moveToNext()) {
                ActivityData tddObj = new ActivityData();
                tddObj.setActivityID(result.getInt(0));
                tddObj.setActivityName(result.getString(1));
                tddObj.setActivityIntensity(result.getString(2));
                tddObj.setActivityStartTimeDate(result.getString(3));
                tddObj.setActivityEndTimeDate(result.getString(4));
                tdd.add(tddObj);
            }
            adapter.notifyDataSetChanged();
        }
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        updateCardView();
    }
    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }

}
