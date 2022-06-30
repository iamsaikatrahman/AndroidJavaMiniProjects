package com.saikat.workmangerexample;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class MyWorker extends Worker {
    public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
//        Data retrievedData = getInputData();
//        String desc = retrievedData.getString("input data");
        displayNotification("Demo Task", "This is demo task");
//        Data outputData = new Data.Builder().putString("output_data", "This is output data").build();
        return Result.success();
    }

    private void displayNotification(String task, String desc) {
        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("saikat", "workManager", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(desc);
            notificationManager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "saikat")
                .setContentTitle(task)
                .setContentText(desc)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setSmallIcon(R.mipmap.ic_launcher);

        notificationManager.notify(1, builder.build());
    }
}
