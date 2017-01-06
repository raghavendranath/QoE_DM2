package com.example.ragha.qoe_dm;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.util.Log;
import com.example.logger.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {
    /*https://inducesmile.com/android/how-to-play-youtube-video-inside-android-webview-using-video-url/ */
    EditText edit;
    Button button;
    String video;
    WebView wView;
    String frameVideo;
    StringGetter stringGetter;
    public static long time = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.playVideo);
        edit = (EditText) findViewById(R.id.videoID);
        //initWebView();
        //event handling
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getting text from editText
                video = edit.getText().toString();
                time = System.currentTimeMillis();
                initWebView();
            }
        });
    }


    private void initWebView() {
        wView = (WebView) findViewById(R.id.wView);
        wView.setWebChromeClient(new WebChromeClient() {
            public boolean onConsoleMessage(ConsoleMessage cm) {
                try {
                    Log.d("MyApplication", cm.message() + " -- From line "
                            + cm.lineNumber() + " of "
                            + cm.sourceId() );
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return true;
            }
        });
        wView.setWebViewClient(new WebViewClient());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            wView.setWebContentsDebuggingEnabled(true);
        }
        stringGetter = new StringGetter(this);
        wView.setLayerType(wView.LAYER_TYPE_HARDWARE, null);
        WebSettings webSettings = wView.getSettings();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            webSettings.setMediaPlaybackRequiresUserGesture(false);
        }
        webSettings.setJavaScriptEnabled(true);
        /* try {
            final File path = new File(
                    Environment.getExternalStorageDirectory(), "QoE_DailyMotion_Logs");
            if (!path.exists()) {
                path.mkdir();
            }

            Runtime.getRuntime().exec(
                    "logcat -d -f " + path + File.separator
                            + "QoE_DM_logcat_" + System.currentTimeMillis()
                            + ".txt");

        } catch (IOException e) {
            e.printStackTrace();
        }*/

//https://github.com/travist/minplayer/blob/master/doc/minplayer.players.dailymotion.js.html
//http://api.dmcloud.net/static/dmplayer/dmplayer-sdk.html
//http://cgit.drupalcode.org/mediafront/tree/players/osmplayer/player/minplayer/src/minplayer.players.dailymotion.js?id=94dc313ee8a2533f80bf649fbb94fc4c49a595fa
//http://embed.plnkr.co/o2R49j/
        webSettings.setJavaScriptEnabled(true);
        wView.loadUrl("file:///android_asset/DailyMotion.html");
       /* try {
            final File path = new File(
                    Environment.getExternalStorageDirectory(), "QoE_DailyMotion_Logs");
            if (!path.exists()) {
                path.mkdir();
            }
            //"logcat  -d -f " + path + File.separator
            Runtime.getRuntime().exec(
                    "logcat -d -f " + path + File.separator
                            + "QoE_DM_logcat_" + System.currentTimeMillis()
                            + ".txt");

        } catch (IOException e) {
            e.printStackTrace();
        }*/
        //wView.addJavascriptInterface(new StringGetter(this), "AndroidFunction");

    }

    public class StringGetter {
        Context jContext;

        StringGetter(Context context){
            jContext = context;
        }
        @JavascriptInterface
        public String getString() {
            return video;

        }

        @JavascriptInterface
        public void setString() {
            //count ++;

        }
    }




}

