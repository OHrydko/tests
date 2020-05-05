package com.example.test.repository;

import android.annotation.SuppressLint;

import com.example.test.App;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Repository {

    @SuppressLint("CheckResult")
    public void getData(CallbackResponse callbackResponse) {
        App.getComponent().getApi().getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callbackResponse::response,
                        callbackResponse::error);
    }
}
