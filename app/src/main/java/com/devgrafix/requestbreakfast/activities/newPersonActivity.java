package com.devgrafix.requestbreakfast.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.devgrafix.requestbreakfast.R;
import com.devgrafix.requestbreakfast.model.Person;

public class newPersonActivity extends AppCompatActivity {

    protected Button btnSave;
    protected TextView txtPersonName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_person);

        setActionBar("Add New Person");
        txtPersonName = (TextView)findViewById(R.id.txt_person_name);
        btnSave = (Button)findViewById(R.id.btn_save_person);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //PersonManager personManager = new PersonManager(getApplicationContext());
                String personName = txtPersonName.getText().toString();
                //personManager.add(personName);
                Person person = new Person();
                person.setPseudo(personName);
                person.save();
                Toast.makeText(getApplicationContext(), "The Person "+personName+" is successfully saved", Toast.LENGTH_LONG).show();
            }
        });
    }
    protected void setActionBar(String title){
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle(title);
    }
}
