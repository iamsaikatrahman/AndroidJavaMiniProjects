package com.saikat.contactroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.saikat.contactroom.model.Contact;
import com.saikat.contactroom.model.ContactViewModel;

public class NewContact extends AppCompatActivity {
    public static final String NAME_REPLY = "name_reply";
    public static final String OCCUPATION_REPLY = "occupation_reply";
    private EditText enterName;
    private EditText enterOccupation;
    private Button saveInfoButton, updateButton, deleteButton;
    private ContactViewModel contactViewModel;
    private int contactId = 0;
    private Boolean isEdit = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        enterName = findViewById(R.id.enter_name);
        enterOccupation = findViewById(R.id.enter_occupation);
        saveInfoButton = findViewById(R.id.save_button);
        updateButton = findViewById(R.id.update_button);
        deleteButton = findViewById(R.id.delete_button);


        contactViewModel = new ViewModelProvider.AndroidViewModelFactory(NewContact.this
                .getApplication())
                .create(ContactViewModel.class);


        if(getIntent().hasExtra(MainActivity.CONTACT_ID)){
            contactId = getIntent().getIntExtra(MainActivity.CONTACT_ID, 0);
            contactViewModel.get(contactId).observe(this, contact -> {
                if(contact != null){
                    enterName.setText(contact.getName());
                    enterOccupation.setText(contact.getOccupation());
                }

            });
            isEdit = true;
        }

        saveInfoButton.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (!TextUtils.isEmpty(enterName.getText()) && !TextUtils.isEmpty(enterOccupation.getText())) {
                String name = enterName.getText().toString();
                String occupation = enterOccupation.getText().toString();
                Contact contact = new Contact(enterName.getText().toString(), enterOccupation.getText().toString());
                //ContactViewModel.insert(contact);
                replyIntent.putExtra(NAME_REPLY, name);
                replyIntent.putExtra(OCCUPATION_REPLY, occupation);
                setResult(RESULT_OK, replyIntent);
            } else {
                //Toast.makeText(this, R.string.empty, Toast.LENGTH_SHORT).show();
                setResult(RESULT_CANCELED, replyIntent);
            }
            finish();
        });



        updateButton.setOnClickListener(view -> {
            int id = contactId;
            String name = enterName.getText().toString().trim();
            String occupation = enterOccupation.getText().toString().trim();

            if(TextUtils.isEmpty(name) || TextUtils.isEmpty(occupation)){
                Snackbar.make(enterName, R.string.empty, Snackbar.LENGTH_SHORT).show();
            } else{
                Contact contact = new Contact();
                contact.setId(id);
                contact.setName(name);
                contact.setOccupation(occupation);
                ContactViewModel.updateContact(contact);
                finish();
            }

        });

        if(isEdit){
            saveInfoButton.setVisibility(View.GONE);
        } else{
            updateButton.setVisibility(View.GONE);
            deleteButton.setVisibility(View.GONE);
        }
    }
}