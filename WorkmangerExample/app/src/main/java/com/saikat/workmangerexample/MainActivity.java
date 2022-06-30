package com.saikat.workmangerexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private Button btndowork, btnCancel;
    private TextView tvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btndowork = findViewById(R.id.btnDoWork);
        tvStatus = findViewById(R.id.tvStatus);
        btnCancel = findViewById(R.id.btnCancel);

        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();

//        //======================== OneTimeWorkRequest =============================//
//        OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(MyWorker.class).build();
//
//        btndowork.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                WorkManager.getInstance(MainActivity.this).enqueue(request);
//
//            }
//        });
//
//        WorkManager.getInstance(this).getWorkInfoByIdLiveData(request.getId())
//                .observe(this, new Observer<WorkInfo>() {
//                    @Override
//                    public void onChanged(WorkInfo workInfo) {
//                        String status = workInfo.getState().name();
//                        tvStatus.setText(status + "\n");
//                    }
//                });
//
//        //======================== OneTimeWorkRequest =============================//


        //======================== PeriodicWorkRequest =============================//

        final PeriodicWorkRequest request =
                new PeriodicWorkRequest.Builder(MyWorker.class, 15, TimeUnit.MINUTES)
                        .setConstraints(constraints)
                        .build();

        btndowork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WorkManager.getInstance(MainActivity.this).enqueue(request);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WorkManager.getInstance(MainActivity.this).cancelWorkById(request.getId());
            }
        });

        WorkManager.getInstance(this).getWorkInfoByIdLiveData(request.getId())
                .observe(this, new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(WorkInfo workInfo) {
                        String status = workInfo.getState().name();
                        tvStatus.setText(status + "\n");
                        if(workInfo != null){
                            if(workInfo.getState().isFinished()){
                                Data retrievedData = workInfo.getOutputData();
                                String outputData  = retrievedData.getString("output_data");
                                tvStatus.setText(outputData + "\n");
                            }
                        }
                    }
                });

        //======================== PeriodicWorkRequest =============================//

    }
}