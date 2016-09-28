package com.devgrafix.requestbreakfast.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.devgrafix.requestbreakfast.R;

import com.devgrafix.requestbreakfast.model.Person;

/**
 * Created by PC-MA13 on 11/09/2016.
 */
public class editPersonActivity extends AppCompatActivity {

    protected long personId;
    protected Button btnSave;
    protected TextView txtPersonName;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_person);
        Intent intent = getIntent();
        personId = intent.getLongExtra("personId", 0);
        String personName= intent.getStringExtra("personName");

        txtPersonName = (TextView)findViewById(R.id.txt_person_name);
        txtPersonName.setText(personName);
        btnSave = (Button)findViewById(R.id.btn_save_person);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String personName = txtPersonName.getText().toString();
                Person person = Person.getOneById(personId);

                person.setPseudo(personName);
                person.save();
                Intent newIntent = new Intent();
                newIntent.putExtra("personName", person.getPseudo());
                setResult(listPresonsActivity.EDIT_PERSON_REQUEST, newIntent);
                finish();
               }
        });
    }
}
