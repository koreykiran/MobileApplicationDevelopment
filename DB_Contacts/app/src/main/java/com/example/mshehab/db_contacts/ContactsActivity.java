package com.example.mshehab.db_contacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.mshehab.db_contacts.DB.DBDataManager;

import java.util.ArrayList;
import java.util.List;

public class ContactsActivity extends AppCompatActivity {
    ListView listView;
    ContactsAdapter contactsAdapter;
    ArrayList<Contact> contacts = new ArrayList<>();
    EditText editTextFilter;
    DBDataManager dbDataManager;
    final String TAG = "demo";
    final int CREATE_USER_RES = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        dbDataManager = new DBDataManager(this);

        Log.d(TAG, "onCreate: " + dbDataManager.getContactDAO().getAll());

        contacts=dbDataManager.getContactDAO().getAll();
        listView = findViewById(R.id.listView);
        contactsAdapter = new ContactsAdapter(this,R.layout.contact_item, contacts);
        listView.setAdapter(contactsAdapter);


        editTextFilter = findViewById(R.id.editTextFilter);

        findViewById(R.id.buttonCreate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContactsActivity.this, NewContactActivity.class);
                startActivityForResult(intent, CREATE_USER_RES);
            }
        });

        findViewById(R.id.buttonFilter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //refresh the list with filtered results

                contactsAdapter.clear();
                contactsAdapter.addAll(dbDataManager.getContactDAO().getFiltered(editTextFilter.getText().toString()));
                contactsAdapter.notifyDataSetChanged();


            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //delete item and refresh list !!
                Contact c = contactsAdapter.getItem(position);
                dbDataManager.getContactDAO().delete(c);
                contactsAdapter.remove(c);
                contactsAdapter.notifyDataSetChanged();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                Contact contact = contactsAdapter.getItem(position);
                dbDataManager.getContactDAO().save(contact);
                contactsAdapter.clear();
                contactsAdapter.addAll(dbDataManager.getContactDAO().getAll());
                contactsAdapter.notifyDataSetChanged();



                //contactsAdapter.clear();
                //contactsAdapter.addAll(dbDataManager.getContactDAO().getAll());
                //contactsAdapter.notifyDataSetChanged();
                return true;
            }
        });




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CREATE_USER_RES && resultCode == RESULT_OK){
            //refresh the list!!
            //List<Contact> tempContacts = dbDataManager.getContactDAO().getAll();
            //contacts.clear();
            //contacts.addAll(tempContacts);


            contactsAdapter.clear();
            contactsAdapter.addAll(dbDataManager.getContactDAO().getAll());
            contactsAdapter.notifyDataSetChanged();
        }

    }
}
