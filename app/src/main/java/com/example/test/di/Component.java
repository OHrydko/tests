package com.example.test.di;

import android.app.Application;

import com.example.test.network.ApiService;

import dagger.BindsInstance;


@dagger.Component(modules = {RetrofitModule.class})
public interface Component {
    ApiService getApi();

    @dagger.Component.Builder
    interface Builder {
        Component build();

        Builder retrofitModule(RetrofitModule retrofitModule);

        @BindsInstance
        Builder application(Application application);

    }
}
