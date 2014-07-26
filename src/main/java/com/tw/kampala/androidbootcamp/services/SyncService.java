package com.tw.kampala.androidbootcamp.services;

import android.app.NotificationManager;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import com.google.common.io.ByteStreams;
import com.tw.kampala.androidbootcamp.R;
import com.tw.kampala.androidbootcamp.models.Item;
import com.tw.kampala.androidbootcamp.models.ItemIds;
import com.tw.kampala.androidbootcamp.services.api.ItemAPI;
import retrofit.RestAdapter;
import retrofit.client.Response;
import roboguice.service.RoboIntentService;

import javax.inject.Inject;

public class SyncService extends RoboIntentService {

    public static final int NOTIFICATION_ID = 1001;
    @Inject
    NotificationManager notificationManager;

    public SyncService() {
        super("AyoyoService");
    }

    @Override
    protected void onHandleIntent(Intent intent){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                                                .setSmallIcon(R.drawable.ic_launcher)
                                                .setContentText("Syncing...")
                                                .setContentTitle("Syncing...")
                                                .setProgress(0, 0, true);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://sync-server.herokuapp.com")
                .build();

        ItemAPI itemAPI = restAdapter.create(ItemAPI.class);
        int counter = 0;

        ItemIds itemIds = itemAPI.getItemIds();
        builder.setProgress(itemIds.getIds().size(), 0, false);
        notificationManager.notify(NOTIFICATION_ID, builder.build());

        try {
            for (String itemId : itemIds.getIds()) {
                Item item = itemAPI.getItem(itemId);
                builder.setProgress(itemIds.getIds().size(), ++counter, false);
                notificationManager.notify(NOTIFICATION_ID, builder.build());

                Response avatar = itemAPI.getAvatar(itemId);
                byte[] response = ByteStreams.toByteArray(avatar.getBody().in());
            }

            builder.setContentText("").setContentTitle("Sync Complete");
            notificationManager.notify(NOTIFICATION_ID, builder.build());
        }
        catch (Exception e){
            builder.setContentText("Sync failed!");
            notificationManager.notify(NOTIFICATION_ID, builder.build());
        }

        stopSelf();

    }

}
