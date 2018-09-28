package com.fisincorporated.aviationweather.workmanager;

import android.app.Notification;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.fisincorporated.aviationweather.R;
import com.fisincorporated.aviationweather.airport.AirportListDownloader;
import com.fisincorporated.aviationweather.dagger.DaggerDownloadAirportWorkerComponent;
import com.fisincorporated.aviationweather.repository.AppRepository;

import javax.inject.Inject;
import javax.inject.Named;

import androidx.work.Worker;
import okhttp3.OkHttpClient;

public class DownloadAirportsWorker extends Worker {

    private static final int NOTIFICATION_ID = 1234;

    private Context context;

    @Inject
    @Named("CHANNEL_ID")
    String CHANNEL_ID;

    @Inject
    OkHttpClient okHttpClient;

    @Inject
    AppRepository appRepository;

    @Inject
    AirportListDownloader airportListDownloader;

    private NotificationManagerCompat notificationManager;

    @NonNull
    @Override
    public Result doWork() {

        context = getApplicationContext();
        DaggerDownloadAirportWorkerComponent.builder().context(context).build().inject(this);

        notificationManager = NotificationManagerCompat.from(context);
        displayStartNotification();

        airportListDownloader.downloadAirportsToDB(okHttpClient).blockingAwait();
        boolean success = appRepository.getCountOfAirports().blockingGet() > 2000;
        displayCompletionNotification(success);

        // Indicate success or failure with your return value:
        return success ? Result.SUCCESS : Result.FAILURE;
    }

    private void displayStartNotification() {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                //.setSmallIcon(R.drawable.notification_icon)
                .setContentTitle(context.getString(R.string.app_name))
                .setContentText(context.getString(R.string.download_of_airports_started))
                .setPriority(NotificationCompat.PRIORITY_HIGH);
        displayNotification(builder.build());
    }

    private void displayCompletionNotification(boolean loadedOK) {
        Context context = getApplicationContext();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                //.setSmallIcon(R.drawable.notification_icon)
                .setContentTitle(context.getString(R.string.app_name))
                .setContentText(loadedOK ? context.getString(R.string.airport_database_loaded_ok) :
                        context.getString(R.string.airport_database_load_oops))
                .setPriority(NotificationCompat.PRIORITY_HIGH);
        displayNotification(builder.build());
    }

    private void displayNotification(Notification notification) {
        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(NOTIFICATION_ID, notification);
    }

    //TODO
    // If download failed, add intent to notification
    // Display error fragment
    // With user ok, resubmit job in 1hr (4 hrs, 1 day????)

}
