package com.saikat.todoister;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;


import android.app.Application;
import android.os.Bundle;

import com.saikat.todoister.model.TaskViewModel;

public class MainActivity extends AppCompatActivity {
    private TaskViewModel taskViewModel;
    private RecyclerView recyclerView;
    //    private RecyclerViewAdapter recyclerViewAdapter;
    BottomSheetFragment bottomSheetFragment;
//    private ShareViewModel shareViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


}
