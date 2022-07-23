package com.saikat.contactroom;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.saikat.contactroom.adapter.RecylerViewAdapter;
import com.saikat.contactroom.model.Contact;
import com.saikat.contactroom.model.ContactViewModel;

import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements RecylerViewAdapter.OnContactClickListener {
    public static final int NEW_CONTACT_ACTIVITY_REQUEST_CODE = 1;
    public static final String CONTACT_ID = "contact_id";
    private ContactViewModel contactViewModel;
    private RecyclerView recyclerView;
    private RecylerViewAdapter recylerViewAdapter;
    private LiveData<List<Contact>> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        contactViewModel = new ViewModelProvider.AndroidViewModelFactory(MainActivity.this
                .getApplication())
                .create(ContactViewModel.class);

        contactViewModel.getAllContacts().observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> contacts) {

                //Set up adapter
                recylerViewAdapter = new RecylerViewAdapter(contacts, MainActivity.this, MainActivity.this);
                recyclerView.setAdapter(recylerViewAdapter);
            }
        });

        FloatingActionButton fab = findViewById(R.id.add_contact_fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, NewContact.class);
            startActivityForResult(intent, NEW_CONTACT_ACTIVITY_REQUEST_CODE);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_CONTACT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            assert data != null;
            String name = data.getStringExtra(NewContact.NAME_REPLY);
            String occupation = data.getStringExtra(NewContact.OCCUPATION_REPLY);
            Contact contact = new Contact(name, occupation);
            ContactViewModel.insert(contact);
            //Log.d("MainTAG", "onActivityResult: Name: "+ data.getStringExtra(NewContact.NAME_REPLY) + " " + "Occupation: "+ data.getStringExtra(NewContact.OCCUPATION_REPLY));
        }
    }

    @Override
    public void onContactClick(int position) {

        Contact contact = Objects.requireNonNull(contactViewModel.allContacts.getValue()).get(position);
        Log.d("ONCONTACTCLICK", "onContactClick: " + contact.getName());
        //startActivity(new Intent(MainActivity.this, NewContact.class));

        Intent intent = new Intent(MainActivity.this, NewContact.class);
        intent.putExtra(CONTACT_ID, contact.getId());
        startActivity(intent);
    }
}