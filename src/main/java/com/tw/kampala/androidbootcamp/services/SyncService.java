package com.tw.kampala.androidbootcamp.services;

import android.app.IntentService;
import android.content.Intent;

public class SyncService extends IntentService{

    public SyncService() {
        super("AyoyoService");
    }

    @Override
    protected void onHandleIntent(Intent intent){

        android.util.Log.d("Myapp", "SyncService called");
        stopSelf();
    }
}
