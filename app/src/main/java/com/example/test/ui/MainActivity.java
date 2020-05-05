package com.example.test.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.test.R;
import com.example.test.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this,
                R.layout.activity_main);
        MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.init(this);
        binding.setLifecycleOwner(this);
        setSupportActionBar(binding.toolbar);
        binding.setViewModel(viewModel);
        binding.getRoot();
    }
}
