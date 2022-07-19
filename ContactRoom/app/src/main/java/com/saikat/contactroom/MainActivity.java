package com.saikat.contactroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.saikat.contactroom.model.Contact;
import com.saikat.contactroom.model.ContactViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ContactViewModel contactViewModel;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text);
        contactViewModel = new ViewModelProvider.AndroidViewModelFactory(MainActivity.this
                .getApplication())
                .create(ContactViewModel.class);

        contactViewModel.getAllContacts().observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> contacts) {
                StringBuilder builder = new StringBuilder();
                for(Contact contact : contacts){
                    builder.append(" - ").append(contact.getName()).append(" ").append(contact.getOccupation());
                    Log.d("TAG", "onChanged: "+ contact.getName());
                }
                textView.setText(builder.toString());
            }
        });
    }
}