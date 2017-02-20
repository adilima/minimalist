package es.hol.xaverius2017.personal;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.content.Context;

public class MainActivity extends Activity {

        @Override
        public void onCreate(Bundle savedInstanceBundle) {
                super.onCreate(savedInstanceBundle);
                // this.requestWindowFeature(Window.FEATURE_NO_TITLE);
                setContentView(R.layout.main);
                WebView webView = (WebView) findViewById(R.id.webView1);
		//setContentView(webView);

                // enable javascript, and also the way to communicate with it 
                webView.getSettings().setJavaScriptEnabled(true);
                webView.addJavascriptInterface(new AndroidObject(this), "AndroidObject");
                // load HTML internal file 
                webView.loadUrl("file:///android_asset/index.html");
        }

        class AndroidObject {
                Context context;

                AndroidObject(Context ctx) {
                        context = ctx;
                }

                public void showToast(String strText) {
                        Toast.makeText(context, strText, Toast.LENGTH_SHORT).show();
                }
        }

}
