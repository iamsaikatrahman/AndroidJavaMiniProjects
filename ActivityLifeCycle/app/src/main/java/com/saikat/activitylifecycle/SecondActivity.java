package com.saikat.activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    private TextView receivedtextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Bundle extra = getIntent().getExtras();
        receivedtextView  = findViewById(R.id.received_textview);
        if(extra != null){
            receivedtextView.setText(extra.getString("guess"));
            Log.d("Name extra", "onCreate: "+ extra.getString("name"));
            Log.d("Name extra2", "onCreate: "+ extra.getString("age"));

        }
        receivedtextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                intent.putExtra("message_back", "From second activity");
                setResult(RESULT_OK, intent);
                finish();
            }
        });
//        if(getIntent().getStringExtra("guess") != null){
//            Log.d("stuff", " "+getIntent().getStringExtra("name"));
//           receivedtextView.setText(getIntent().getStringExtra("guess"));
//        }
    }
}