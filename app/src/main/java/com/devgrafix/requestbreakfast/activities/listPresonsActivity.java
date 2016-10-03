package com.devgrafix.requestbreakfast.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import com.devgrafix.requestbreakfast.R;
import com.devgrafix.requestbreakfast.model.Person;
import com.devgrafix.requestbreakfast.personList.PersonAdapter;

import java.util.List;

public class listPresonsActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    final Context contextListPerson = this;
    static final int EDIT_PERSON_REQUEST = 1;
    protected Person selectedPerson;
    protected int selectedPersonPosition;
    protected PersonAdapter personAdapter;

    private SwipeRefreshLayout swipeRefreshLayout;

    protected ListView listPersons;
    protected List<Person> persons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_presons);
        listPersons = (ListView) findViewById(R.id.list_persons);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        /**
         * Showing Swipe Refresh animation on activity create
         * As animation won't start on onCreate, post runnable is used
         */
        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeRefreshLayout.setRefreshing(true);
                                        displayResultList();
                                        swipeRefreshLayout.setRefreshing(false);
                                    }
                                }
        );

        registerForContextMenu(listPersons);
    }

    private void displayResultList() {
        persons = Person.getAll();
        personAdapter = new PersonAdapter(listPresonsActivity.this, persons);
        //ArrayAdapter<String> adapter= new ArrayAdapter<String>(listPresonsActivity.this, android.R.layout.simple_list_item_1, prenoms);

        listPersons.setAdapter(personAdapter);
        // listPersons.setTextFilterEnabled(true);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.list_persons) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            selectedPersonPosition = info.position;
            selectedPerson = persons.get(info.position);
            menu.setHeaderTitle(selectedPerson.getPseudo());

            String[] menuItems = getResources().getStringArray(R.array.menu_edit_delete_items);
            for (int i = 0; i < menuItems.length; i++) {
                menu.add(Menu.NONE, i, i, menuItems[i]);
            }
        }
    }

    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int menuItemId = item.getItemId();
        String[] menuItems = getResources().getStringArray(R.array.menu_edit_delete_items);
        String menuItemName = menuItems[menuItemId];
        selectedPersonPosition = info.position;
        selectedPerson = persons.get(info.position);
        Toast.makeText(getApplicationContext(), selectedPerson.toString(), Toast.LENGTH_LONG).show();
        switch (menuItemId) {
            case 0: //edit
                Intent nextIntent = new Intent(listPresonsActivity.this, editPersonActivity.class);
                nextIntent.putExtra("personId", selectedPerson.getId());
                nextIntent.putExtra("personName", selectedPerson.getPseudo());
                startActivityForResult(nextIntent, EDIT_PERSON_REQUEST);
                break;
            case 1: //delete
                askConfirmationBeforeDelete(info.position).show();
                break;
            default:
                break;
        }
        return true;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Toast.makeText(contextListPerson, "requestCode "+requestCode+ " resultCode " + resultCode +" RESULT_OK "+ RESULT_OK, Toast.LENGTH_LONG).show();
        if (requestCode == EDIT_PERSON_REQUEST) {
            String newName = data.getStringExtra("personName");
            selectedPerson.setPseudo(newName);
            Toast.makeText(contextListPerson, "The Person " + newName + " is successfully saved", Toast.LENGTH_LONG).show();
            persons.set(selectedPersonPosition, selectedPerson);
            personAdapter.notifyDataSetChanged();

        }
    }

    private AlertDialog askConfirmationBeforeDelete(final int position) {
        AlertDialog dialogBox = new AlertDialog.Builder(contextListPerson)
                //set message, title, and icon
                .setTitle("Delete")
                .setMessage("Are you sure you want to delete this item")
                        //.setIcon(R.drawable.delete)
                        //.setCancelable(true)
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //your deleting code

                        selectedPerson.delete();
                        persons.remove(position);
                        personAdapter.notifyDataSetChanged();
                        Toast.makeText(getApplicationContext(), selectedPerson.getPseudo() + " is deleted successfully", Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .create();
        return dialogBox;

    }

    @Override
    public void onRefresh() {
        // showing refresh animation before making http call
        swipeRefreshLayout.setRefreshing(true);
        //displayResultList();
        // stopping swipe refresh
        swipeRefreshLayout.setRefreshing(false);
    }
}
