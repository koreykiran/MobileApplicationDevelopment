package com.example.mshehab.db_contacts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.mshehab.db_contacts.DB.ContactDAO;
import com.example.mshehab.db_contacts.DB.DBDataManager;

public class NewContactActivity extends AppCompatActivity {
    EditText editTextName, editTextPhone, editTextEmail;
    RadioGroup radioGroup;
    DBDataManager dm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);
        editTextName = findViewById(R.id.editTextName);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextEmail = findViewById(R.id.editTextEmail);
        dm=new DBDataManager(this);
        radioGroup = findViewById(R.id.radioGroup);

        findViewById(R.id.buttonSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString();
                String email = editTextEmail.getText().toString();
                String phone = editTextPhone.getText().toString();
                String dept = "SIS";

                if(radioGroup.getCheckedRadioButtonId() == R.id.radioButtonSIS){
                    dept = "SIS";
                } else if(radioGroup.getCheckedRadioButtonId() == R.id.radioButtonCS){
                    dept = "CS";
                } else if(radioGroup.getCheckedRadioButtonId() == R.id.radioButtonBIO){
                    dept = "BIO";
                }

                if(name.equals("")){
                    Toast.makeText(NewContactActivity.this, "Enter name", Toast.LENGTH_SHORT).show();
                } else if(email.equals("")){
                    Toast.makeText(NewContactActivity.this, "Enter email", Toast.LENGTH_SHORT).show();
                } else if(phone.equals("")){
                    Toast.makeText(NewContactActivity.this, "Enter phone", Toast.LENGTH_SHORT).show();
                } else{
                    Contact contact = new Contact(name, email, phone, dept);

                    // save new contact to the database !!
                    dm.getContactDAO().save(contact);
                    dm.close();


                    setResult(RESULT_OK);
                    finish();
                }
            }
        });

        findViewById(R.id.buttonCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
