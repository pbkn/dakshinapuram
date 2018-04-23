package com.whysurfswim.dakshinapuram.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.ads.AdView;
import com.whysurfswim.dakshinapuram.MainActivity;
import com.whysurfswim.dakshinapuram.R;

public class GamesTab extends Fragment {

    private View tabView;
    private AdView adView;
    private WebView webView;
    private ProgressBar spinner;
    private SwipeRefreshLayout layoutRefresh;
    private Button retryButton;
    private ImageView retryImage;
    private TextView retryText;
    private String gameUrl = "https://dakshinapuram.wordpress.com/mini-games/";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        tabView = inflater.inflate(R.layout.games_tab, container, false);
        adView = tabView.findViewById(R.id.adView);
        retryButton = tabView.findViewById(R.id.retryButton);
        retryImage = tabView.findViewById(R.id.retryImageView);
        retryText = tabView.findViewById(R.id.retryTextView);
        spinner = tabView.findViewById(R.id.progressBar);
        layoutRefresh = tabView.findViewById(R.id.swipeLayout);

        webView = tabView.findViewById(R.id.webView);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setOverScrollMode(WebView.OVER_SCROLL_NEVER);
        webView.setWebViewClient(new GamesTab.CustomWebViewClient());
        if (((MainActivity) getActivity()).isOnline()) {
            webView.loadUrl(gameUrl);
        } else {
            setWebVisibility(View.INVISIBLE);
            setRetryVisibility(View.VISIBLE);
        }
        layoutRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (((MainActivity) getActivity()).isOnline()) {
                    webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
                    webView.loadUrl(gameUrl);
                } else {
                    setWebVisibility(View.INVISIBLE);
                    setRetryVisibility(View.VISIBLE);
                }
                layoutRefresh.setRefreshing(false);
            }
        });
        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((MainActivity) getActivity()).isOnline()) {
                    setWebVisibility(View.INVISIBLE);
                    setRetryVisibility(View.INVISIBLE);
                    spinner.setVisibility(View.VISIBLE);
                    webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
                    webView.loadUrl(gameUrl);
                }
            }
        });
        ((MainActivity) getActivity()).adLoad(adView);
        return tabView;
    }

    /**
     * setWebVisibility is used to hide or show web view
     *
     * @param visibility show or hide
     */
    private void setWebVisibility(int visibility) {
        spinner.setVisibility(visibility);
        webView.setVisibility(visibility);
    }

    /**
     * setRetryVisibility is used to hide or show retry view
     *
     * @param visibility show or hide
     */
    private void setRetryVisibility(int visibility) {
        retryImage.setVisibility(visibility);
        retryText.setVisibility(visibility);
        retryButton.setVisibility(visibility);
    }

    /**
     * CustomeWebViewClient used to load our main screen using webview
     */
    private class CustomWebViewClient extends WebViewClient {

        @Override
        public void onPageStarted(WebView webView, String url, Bitmap favicon) {
            if (!((MainActivity) getActivity()).isOnline()) {
                setWebVisibility(View.INVISIBLE);
                setRetryVisibility(View.VISIBLE);
            } else {
                setWebVisibility(View.INVISIBLE);
                setRetryVisibility(View.INVISIBLE);
                spinner.setVisibility(View.VISIBLE);
            }

            if (spinner.getVisibility() == View.VISIBLE) {
                webView.setVisibility(View.INVISIBLE);
                setRetryVisibility(View.INVISIBLE);
            }

            if (url.startsWith("https://www.google.com/logos")) {
                final Dialog dialog = new Dialog(tabView.getContext());
                dialog.setContentView(R.layout.game_play);
                WebView gamePlayView = dialog.findViewById(R.id.webView);
                gamePlayView.getSettings().setDomStorageEnabled(true);
                gamePlayView.getSettings().setAppCacheEnabled(true);
                gamePlayView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
                gamePlayView.getSettings().setJavaScriptEnabled(true);
                webView.setOverScrollMode(WebView.OVER_SCROLL_NEVER);
                gamePlayView.loadUrl(url);
                webView.loadUrl(gameUrl);
                dialog.show();
            }

            if (url.startsWith("https://play.google.com")) {
                Intent shareIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                if (((MainActivity) getActivity()).checkPackage(shareIntent, "com.android.vending")) {
                    startActivity(shareIntent);
                    webView.reload();
                } else {
                    Intent viewIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse(webView.getUrl()));
                    startActivity(viewIntent);
                }
            }


            if (url.startsWith("https://www.facebook")) {
                Intent shareIntent;
                if (url.equals("https://www.facebook.com/DakshinaPuram")) {
                    shareIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/1577681315786891"));
                } else {
                    shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_TEXT, webView.getUrl());
                    shareIntent.setType("text/plain");
                }
                if (((MainActivity) getActivity()).checkPackage(shareIntent, "com.facebook.kata") ||
                        ((MainActivity) getActivity()).checkPackage
                                (shareIntent, "com" + ".facebook.li")) {
                    startActivity(shareIntent);
                    webView.reload();
                } else {
                    Intent viewIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse(webView.getUrl()));
                    startActivity(viewIntent);
                }
            }

            if (url.startsWith("whatsapp://")) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, webView.getUrl());
                shareIntent.setType("text/plain");
                if (((MainActivity) getActivity()).checkPackage(shareIntent, "com.whatsapp")) {
                    startActivity(shareIntent);
                    webView.reload();
                } else {
                    Intent viewIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse(webView.getUrl()));
                    startActivity(viewIntent);
                }
            }

            if (url.startsWith("https://twitter")) {
                Intent shareIntent;
                if (url.equals("https://twitter.com/DakshinapuramO")) {
                    shareIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://twitter.com/intent/follow?user_id=961980384098463745"));
                    shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                } else {
                    shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_TEXT, webView.getUrl());
                    shareIntent.setType("text/plain");
                }
                if (((MainActivity) getActivity()).checkPackage(shareIntent, "com.twitter")) {
                    startActivity(shareIntent);
                    webView.reload();
                } else {
                    Intent viewIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse(webView.getUrl()));
                    startActivity(viewIntent);
                }
            }

            if (url.startsWith("http://instagram.com/")) {
                Intent shareIntent;
                if (url.equals("https://www.instagram.com/dakshinapuram/")) {
                    shareIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://instagram.com/_u/dakshinapuram"));
                } else {
                    shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_TEXT, webView.getUrl());
                    shareIntent.setType("text/plain");
                }
                if (((MainActivity) getActivity()).checkPackage(shareIntent, "com.instagram.android")) {
                    startActivity(shareIntent);
                    webView.reload();
                } else {
                    Intent viewIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse(webView.getUrl()));
                    startActivity(viewIntent);
                }
            }

        }

        @Override
        public void onPageFinished(WebView webView, String url) {
            if (!((MainActivity) getActivity()).isOnline()) {
                setWebVisibility(View.INVISIBLE);
                setRetryVisibility(View.VISIBLE);
            }
            spinner.setVisibility(View.INVISIBLE);
            webView.setVisibility(View.VISIBLE);
            super.onPageFinished(webView, url);
        }
    }
}
