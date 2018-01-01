package com.example.chrisantuseze.hadum.Nav;


import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.chrisantuseze.hadum.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class WebsiteFragment extends Fragment {
    public static final String ARG_ITEM_ID = "item_id";
    private static final String URL = "http://www.futo.edu.ng";

    public WebsiteFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.website_fragment, container, false);

            ((WebView) rootView.findViewById(R.id.website_detail)).loadUrl(URL);     //Just what I added
            WebView webView = (WebView)rootView.findViewById(R.id.website_detail);              //
            webView.setWebViewClient(new WebViewClient(){                                       //
                @Override                                                                       //
                public boolean shouldOverrideUrlLoading(WebView view, String url) {             //
                    return super.shouldOverrideUrlLoading(view, url);                           //
                }
            });
            webView.getSettings().setJavaScriptEnabled(true);                                   //
            webView.loadUrl(URL);                                                     //


        return rootView;
    }
}
