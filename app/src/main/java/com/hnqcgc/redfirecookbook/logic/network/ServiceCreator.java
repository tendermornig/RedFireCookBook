package com.hnqcgc.redfirecookbook.logic.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceCreator {

    private static ServiceCreator serviceCreator;

    private ServiceCreator(){}

    public static ServiceCreator getInstance() {
        if (serviceCreator == null) {
            serviceCreator = new ServiceCreator();
        }
        return serviceCreator;
    }

    private static final String BASE_URL = "https://liyuankun.cn/api/";

    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public <T> T create(Class<T> serviceClass) {
        return retrofit.create(serviceClass);
    }

}
