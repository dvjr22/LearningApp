package com.example.valdeslab.learningapp.GoogleCharts;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.example.valdeslab.learningapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class WebViewFragment extends Fragment {


    public WebViewFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.webview_layout, container, false);

        WebView webView = (WebView) view.findViewById(R.id.webView1);
        webView.loadUrl("https://www.google.com/");

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient());

        return view;
    }

}
