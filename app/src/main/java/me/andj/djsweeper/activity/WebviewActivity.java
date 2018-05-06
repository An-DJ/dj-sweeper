package me.andj.djsweeper.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import me.andj.djsweeper.R;

/**
 * Created by AnDJ on 2018/5/2.
 */

public class WebviewActivity extends AppCompatActivity {
    WebView webView;
    String website;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more_web_view);
        webView=findViewById(R.id.more_web_view_web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        Intent intent=getIntent();
        website=intent.getStringExtra("website");
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webView.loadUrl(website);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_webview_activity,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_web_view_ooen_in_browser_item:
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse(website);
                intent.setData(content_url);
                startActivity(intent);
                break;
            default:
                break;
        }

        return true;
    }
}
