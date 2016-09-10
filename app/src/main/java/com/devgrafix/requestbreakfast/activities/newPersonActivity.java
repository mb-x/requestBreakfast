package com.devgrafix.requestbreakfast.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.devgrafix.requestbreakfast.managers.PersonManager;
import com.devgrafix.requestbreakfast.R;

public class newPersonActivity extends AppCompatActivity {

    protected Button btnSave;
    protected TextView txtPersonName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_person);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle("Add New Person");
        txtPersonName = (TextView)findViewById(R.id.txt_person_name);
        btnSave = (Button)findViewById(R.id.btn_save_person);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PersonManager personManager = new PersonManager(getApplicationContext());
                String personName = txtPersonName.getText().toString();
                personManager.add(personName);
                Toast.makeText(getApplicationContext(), "The Person "+personName+" is successfully saved", Toast.LENGTH_LONG).show();
            }
        });
    }
}
