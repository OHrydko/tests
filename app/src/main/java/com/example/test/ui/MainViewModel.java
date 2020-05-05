package com.example.test.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.test.R;
import com.example.test.model.Response;
import com.example.test.repository.CallbackResponse;
import com.example.test.repository.Repository;

import java.util.Objects;

public class MainViewModel extends ViewModel implements CallbackResponse {
    private FragmentActivity activity;
    public MutableLiveData<Boolean> visibilityOfProgress = new MutableLiveData<>(true);
    private SharedPreferences sharedPref;

    @SuppressLint("CheckResult")
    void init(FragmentActivity activity) {
        sharedPref = activity.getSharedPreferences(
                "USER", Context.MODE_PRIVATE);
        this.activity = activity;
        if (sharedPref.getBoolean("first", true)) {
            Repository repository = new Repository();
            repository.getData(this);
        } else {
            boolean isWeb = sharedPref.getBoolean("isWeb", true);
            loadFragment(isWeb);
        }
    }


    private void loadFragment(boolean isWeb) {
        visibilityOfProgress.setValue(false);

        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout, (isWeb) ? new WebViewFragment() : new GameFragment())
                .commit();
    }

    public void change() {
        Repository repository = new Repository();
        repository.getData(this);
        visibilityOfProgress.postValue(true);
    }

    @Override
    public void response(Response data) {

        sharedPref.edit().putBoolean("first", false).apply();
        sharedPref.edit().putBoolean("isWeb", data.isSuccess()).apply();
        loadFragment(data.isSuccess());
    }


    @Override
    public void error(Throwable throwable) {
        visibilityOfProgress.setValue(false);
        Log.d("throwable", Objects.requireNonNull(throwable.getMessage()));
    }
}
