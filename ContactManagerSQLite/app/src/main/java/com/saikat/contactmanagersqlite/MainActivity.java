package com.saikat.contactmanagersqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.saikat.contactmanagersqlite.data.DatabaseHandler;
import com.saikat.contactmanagersqlite.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<String> contactString;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listview);
        contactString = new ArrayList<>();

        DatabaseHandler db = new DatabaseHandler(MainActivity.this);

       // Log.d("Count", "onCreate: " + db.getCount());

        //create contact object first
        Contact jeremy = new Contact();
        jeremy.setName("Jeremy");
        jeremy.setPhoneNumber("98956630");

        Contact json = new Contact();
        json.setName("Json");
        json.setPhoneNumber("0214763585");

        //Get 1 Contact
        //Contact c = db.getContact(2);
        //Log.d("Main", "onCreate: "+c.getName() + ", "+ c.getPhoneNumber());
//        c.setName("Karim");
//        c.setPhoneNumber("223456");
//        int updateRow = db.updateContact(c);
//        Log.d("RowId", "onCreate: " + updateRow);

        //delete contact
        //db.deleteContact(c);

        //db.addContact(json);

//        db.addContact(new Contact("James", "213595"));
//        db.addContact(new Contact("Greg", "896571"));
//        db.addContact(new Contact("Nayem", "0215973"));
//        db.addContact(new Contact("karim", "023699713"));
//        db.addContact(new Contact("Jabbar", "029721353"));
//        db.addContact(new Contact("Rafik", "1392813812"));

        List<Contact> contactList = db.getAllContacts();
        for(Contact contact: contactList){
            Log.d("MainActivity", "onCreate: " +contact.getName());
            contactString.add(contact.getName());
        }

        //creating array adapter
        arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                contactString
        );

        //add to our listview
        listView.setAdapter(arrayAdapter);

        //attach eventlistener to listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("List", "onItemClick: "+ contactString.get(i));
            }
        });

    }
}