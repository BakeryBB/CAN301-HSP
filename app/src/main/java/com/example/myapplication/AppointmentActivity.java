package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class AppointmentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appoint);

        // set the selection of time spinner
        Spinner timeSpinner = findViewById(R.id.timeSpinner);
        ArrayAdapter<CharSequence> time_adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_time_am, android.R.layout.simple_spinner_item);
        timeSpinner.setAdapter(time_adapter);

        // set the selection of different departments
        ListView departmentListView = findViewById(R.id.departmentList);
        String[] departments = getResources().getStringArray(R.array.department_name);
        ArrayAdapter<String> department_adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, departments);
        departmentListView.setAdapter(department_adapter);

        // match different doctors when choosing different departments
        Spinner doctorSpinner = findViewById(R.id.doctorSpinner);


        DatabaseHelper db = new DatabaseHelper(this);
//        db.addDepartmentDoctor(new Department_doctor("SAT", "Bakery"));
//        db.addDepartmentDoctor(new Department_doctor("IBSS", "DADDY_YSY"));
//        db.addDepartmentDoctor(new Department_doctor("ING", "cnm"));
//        Department_doctor dd = db.getDepartmentDoctor(4);
//        int i=db.delDepartmentDoctor(6);


//        Toast toast = Toast.makeText(getApplicationContext(), Integer.toString(i), Toast.LENGTH_LONG);
//
//        toast.show();

        db.addUserReservation(new User_Reservation("210105", "由凇屹", "2023-11-19",
                "14:00-15:00", "SAT", "Sam Smith"));
        db.addUserReservation(new User_Reservation("210105", "由凇屹", "2023-11-19",
                "14:00-15:00", "SAT", "Sam Smith"));
        db.addUserReservation(new User_Reservation("210105", "由凇屹", "20sh23-11-19",
                "14:00-15:00", "SAT", "Sam Smith"));
        User_Reservation ur = db.getUserReservation(1);
        Toast toast=Toast.makeText(getApplicationContext(), ur.getDepartment()+ur.getDoctor(), Toast.LENGTH_LONG);
        toast.show();


    }
}