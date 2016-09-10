package com.devgrafix.requestbreakfast.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.devgrafix.requestbreakfast.managers.PersonManager;
import com.devgrafix.requestbreakfast.R;
import com.devgrafix.requestbreakfast.model.Person;
import com.devgrafix.requestbreakfast.personList.PersonAdapter;

import java.util.List;

public class listPresonsActivity extends AppCompatActivity {

    protected ListView listPersons;
    String[] prenoms = new String[]{
            "Antoine", "Benoit", "Cyril", "David", "Eloise", "Florent",
            "Gerard", "Hugo", "Ingrid", "Jonathan", "Kevin", "Logan",
            "Mathieu", "Noemie", "Olivia", "Philippe", "Quentin", "Romain",
            "Sophie", "Tristan", "Ulric", "Vincent", "Willy", "Xavier",
            "Yann", "Zoé"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_presons);
        listPersons = (ListView)findViewById(R.id.list_persons);

        displayResultList();
    }

    private void displayResultList() {


        PersonManager personManager = new PersonManager(getApplicationContext());
        List<Person> results = personManager.findAll();
        PersonAdapter personAdapter = new PersonAdapter(listPresonsActivity.this, results);
    //ArrayAdapter<String> adapter= new ArrayAdapter<String>(listPresonsActivity.this, android.R.layout.simple_list_item_1, prenoms);

        listPersons.setAdapter(personAdapter);
       // listPersons.setTextFilterEnabled(true);

    }

}
