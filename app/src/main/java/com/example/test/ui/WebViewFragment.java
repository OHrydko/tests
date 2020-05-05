package com.example.test.ui;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.test.R;
import com.example.test.databinding.FragmentWebViewBinding;


public class WebViewFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        FragmentWebViewBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_web_view,
                container, false);
        binding.setLifecycleOwner(this);
        String url = "http://html5test.com/";
        binding.webViews.getSettings().setJavaScriptEnabled(true);
        binding.webViews.setWebViewClient(new WebViewClient());

        binding.webViews.loadUrl(url);
        binding.webViews.canGoBack();
        CookieManager.getInstance().setAcceptCookie(true);
        binding.webViews.getSettings().setAppCacheEnabled(true);
        binding.webViews.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_BACK
                    && event.getAction() == MotionEvent.ACTION_UP
                    && binding.webViews.canGoBack()) {
                binding.webViews.goBack();
                return true;
            }
            return false;
        });
        return binding.getRoot();
    }


}
