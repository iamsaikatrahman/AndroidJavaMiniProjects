package com.saikat.tracklocation;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.os.IBinder;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.saikat.tracklocation.Util.DatabaseHelper;
import com.saikat.tracklocation.Util.Prefs;
import com.saikat.tracklocation.model.LatLong;

import java.util.ArrayList;

public class LocationService extends Service {
    public static ArrayList<LatLng> locationArrayList = new ArrayList<LatLng>();
    public static final int PRIORITY_HIGH_ACCURACY = 100;

    FusedLocationProviderClient fusedLocationClient;
    LocationRequest locationRequest;
    LocationCallback locationCallback;

    private void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationClient.requestLocationUpdates(locationRequest,
                locationCallback,
                Looper.getMainLooper());
    }

    protected void createLocationRequest() {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(3000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        new Notification();

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

//        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) createNotificationChanel();
//        else startForeground(
//                1,
//                new Notification()
//        );

//        locationRequest = LocationRequest.create();
//        locationRequest.setInterval(30000);
////      locationRequest.setInterval(60000);
//        locationRequest.setFastestInterval(3000);
//        locationRequest.setMaxWaitTime(5000);
//        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        locationRequest = LocationRequest.create();
        locationRequest.setInterval(3000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setMaxWaitTime(5000);
        locationRequest.setPriority(PRIORITY_HIGH_ACCURACY);

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                Location location = locationResult.getLastLocation();
                LatLong latLong = new LatLong(
                        Double.toString(location.getLatitude()),
                        Double.toString(location.getLongitude())
                );

                Prefs.getInstance(getApplicationContext()).userCurrentLocation(latLong);
                DatabaseHelper db = new DatabaseHelper(LocationService.this);
                LatLong outputlatlon = Prefs.getInstance(LocationService.this).getLatLong();
                db.addUserLocation(
//                        String.valueOf(outputlatlon.getUserLagtude()).trim(),
//                        String.valueOf(outputlatlon.getUserLongittude()).trim()
                        Double.toString(location.getLatitude()).trim(),
                        Double.toString(location.getLongitude()).trim()
                );

                Toast.makeText(getApplicationContext(),
                        "Lat: " + Double.toString(location.getLatitude()) + '\n' +
                                "Long: " + Double.toString(location.getLongitude()), Toast.LENGTH_LONG).show();


            }
        };
        startLocationUpdates();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChanel() {
        String notificationChannelId = "Location channel id";
        String channelName = "Background Service";

        NotificationChannel chan = new NotificationChannel(
                notificationChannelId,
                channelName,
                NotificationManager.IMPORTANCE_NONE
        );
        chan.setLightColor(Color.BLUE);
        chan.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        NotificationManager manager = getSystemService(NotificationManager.class);

        manager.createNotificationChannel(chan);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, notificationChannelId);

        Notification notification = notificationBuilder.setOngoing(true)
                .setContentTitle("Location updates:")
                .setPriority(NotificationManager.IMPORTANCE_MIN)
                .setCategory(Notification.CATEGORY_SERVICE)
                .build();
        startForeground(2, notification);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        fusedLocationClient.removeLocationUpdates(locationCallback);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
