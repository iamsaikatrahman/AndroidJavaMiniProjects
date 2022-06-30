package com.saikat.contactmanagersqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.saikat.contactmanagersqlite.data.DatabaseHandler;
import com.saikat.contactmanagersqlite.model.Contact;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHandler db = new DatabaseHandler(MainActivity.this);

        Log.d("Count", "onCreate: " + db.getCount());

        //create contact object first
        Contact jeremy = new Contact();
        jeremy.setName("Jeremy");
        jeremy.setPhoneNumber("98956630");

        Contact json = new Contact();
        json.setName("Json");
        json.setPhoneNumber("0214763585");

        //Get 1 Contact
        Contact c = db.getContact(2);
        Log.d("Main", "onCreate: "+c.getName() + ", "+ c.getPhoneNumber());
//        c.setName("Karim");
//        c.setPhoneNumber("223456");
//        int updateRow = db.updateContact(c);
//        Log.d("RowId", "onCreate: " + updateRow);

        //delete contact
        db.deleteContact(c);

        //db.addContact(json);

        List<Contact> contactList = db.getAllContacts();
        for(Contact contact: contactList){
            Log.d("MainActivity", "onCreate: " +contact.getId());
        }
    }
}