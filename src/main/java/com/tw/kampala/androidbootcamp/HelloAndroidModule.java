package com.tw.kampala.androidbootcamp;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.tw.kampala.androidbootcamp.services.api.ItemAPI;
import retrofit.RestAdapter;

public class HelloAndroidModule extends AbstractModule {

    @Provides @Singleton
    public RestAdapter createRestAdapter() {
        return new RestAdapter.Builder()
                .setEndpoint("https://sync-server.herokuapp.com")
                .build();
    }

    @Provides @Singleton
    public ItemAPI createItemAPI(RestAdapter restAdapter){
        return restAdapter.create(ItemAPI.class);
    }

    @Override
    protected void configure() {
    }
}
