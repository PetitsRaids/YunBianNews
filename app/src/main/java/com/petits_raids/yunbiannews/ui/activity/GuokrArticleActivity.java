package com.petits_raids.yunbiannews.ui.activity;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.petits_raids.yunbiannews.R;
import com.petits_raids.yunbiannews.YunBianApplication;

public class GuokrArticleActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guokr_article);

        long articleId = getIntent().getLongExtra("article_id", -1);
        if (articleId == -1)
            finish();
        webView = findViewById(R.id.guokr_article_web_view);
        webView.post(() -> webView.loadUrl("https://m.guokr.com/article/" + articleId + "/"));

        if (YunBianApplication.isNightMode())
            webView.setAlpha(0.3f);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView.destroy();
    }

}
