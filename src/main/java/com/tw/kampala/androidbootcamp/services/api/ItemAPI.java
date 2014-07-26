package com.tw.kampala.androidbootcamp.services.api;

import com.tw.kampala.androidbootcamp.models.Item;
import com.tw.kampala.androidbootcamp.models.ItemIds;
import retrofit.http.GET;
import retrofit.http.Path;

public interface ItemAPI {

    @GET("/items")
    public ItemIds getItemIds();

    @GET("/items/{id}")
    public Item getItem(@Path("id") String id);

}
