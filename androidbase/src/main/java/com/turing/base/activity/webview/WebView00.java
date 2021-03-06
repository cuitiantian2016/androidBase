package com.turing.base.activity.webview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;

/**
 * 根据URL加载网页-直接在Activity上加载一个WebView
 */
public class WebView00 extends AppCompatActivity {

    private WebView webView;
    private long exitTime = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 初始化webView
        webView = new WebView(this);

        webView.setWebViewClient(new WebViewClient() {
            // 设置WebView点击打开的网页在当前界面显示，而不是跳到新的浏览器中
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        // 设置WebView属性，允许执行JS脚本,不然加载出来的网页很难看
        webView.getSettings().setJavaScriptEnabled(true);
        // 调用LoadUrl，载入url
        webView.loadUrl("http://www.baidu.com");

        // 调用Activity的setContentView 将webView 显示出来
        setContentView(webView);

    }

    /**
     * 重写回退按钮的时间,当用户点击回退按钮：
     * 1.webView.canGoBack()判断网页是否能后退,可以则goback()
     * 2.如果不可以连续点击两次退出App,否则弹出提示Toast
     */
    @Override
    public void onBackPressed() {

        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            // 第一次的差，肯定大于2S,此时，弹出Toast，将当前时间设置给exitTime,然后再进行比较
            if (System.currentTimeMillis() - exitTime > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                /**
                 * finish()：结束当前Activity，不会立即释放内存。遵循android内存管理机制。
                 * exit()：结束当前组件如Activity，并立即释放当前Activity所占资源。
                 * killProcess()：结束当前组件如Activity，并立即释放当前Activity所占资源。
                 */
                // 并不能彻底退出APP,只是会结束当前Act，释放资源
                LogUtils.e("PID:" + android.os.Process.myPid());
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);
            }
        }

    }
}
