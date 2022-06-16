package com.saikat.sprefs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String MESSAGE_ID = "messages_prefs";
    private EditText enterMessage;
    private Button saveButton;
    private TextView showMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enterMessage = findViewById(R.id.message_editText);
        showMessage = findViewById(R.id.show_message);
        saveButton = findViewById(R.id.save_button);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = enterMessage.getText().toString().trim();
                SharedPreferences sharedPreferences = getSharedPreferences(MESSAGE_ID, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("message", message);

                editor.apply(); // saving to desk;
            }
        });

        SharedPreferences getPreferences = getSharedPreferences(MESSAGE_ID, MODE_PRIVATE);
        String value = getPreferences.getString("message", "Not Yet");

        showMessage.setText(value);
    }
}