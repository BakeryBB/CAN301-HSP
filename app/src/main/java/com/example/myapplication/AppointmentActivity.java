package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
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

        // set the selection of doctor spinner
        Spinner docterSpinner = findViewById(R.id.doctorSpinner);
        ArrayAdapter<CharSequence> doctor_adapter = ArrayAdapter.createFromResource(this,
                R.array.department1_doctor, android.R.layout.simple_spinner_item);
        docterSpinner.setAdapter(doctor_adapter);

        // set the selection of different departments
        ListView departmentListView = findViewById(R.id.departmentList);
        String[] departments = getResources().getStringArray(R.array.department_name);
        ArrayAdapter<String> department_adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, departments);
        departmentListView.setAdapter(department_adapter);

        // match different doctors when choosing different departments
        Button submitButton = findViewById(R.id.submitReservationButton);
        Spinner doctorSpinner = findViewById(R.id.doctorSpinner);
        DatePicker datePicker = findViewById(R.id.datePicker);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the selected items
                final String[] selectedDepartment = {""};
                departmentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        selectedDepartment[0] = parent.getItemAtPosition(position).toString();
                        // Now 'selectedDepartment' holds the value of the selected item
                    }
                    });
                String selectedDate = datePicker.getDayOfMonth() + "/" + (datePicker.getMonth() + 1) + "/" + datePicker.getYear();
                String selectedTime = timeSpinner.getSelectedItem().toString();
                String selectedDoctor = doctorSpinner.getSelectedItem().toString();

                // Store the selections in a string
                String reservationDetails = "Department: " + selectedDepartment[0] +
                        ", Date: " + selectedDate +
                        ", Time: " + selectedTime +
                        ", Doctor: " + selectedDoctor;


                Toast toast1 = Toast.makeText(getApplicationContext(), reservationDetails, Toast.LENGTH_LONG);
                toast1.show();
            }
        });



        DatabaseHelper db = new DatabaseHelper(this);
        db.addDepartmentDoctor(new Department_doctor("SAT", "Bakery"));
        db.addDepartmentDoctor(new Department_doctor("IBSS", "DADDY_YSY"));
//        db.addDepartmentDoctor(new Department_doctor("ING", "cnm"));
        Department_doctor dd = db.getDepartmentDoctor(4);
        int i=db.delDepartmentDoctor(6);


        Toast toast = Toast.makeText(getApplicationContext(), Integer.toString(i), Toast.LENGTH_LONG);

        toast.show();
//    }
//        int User_ID ;
//        String User_Name ;


    }
}