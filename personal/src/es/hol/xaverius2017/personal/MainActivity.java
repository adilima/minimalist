package es.hol.xaverius2017.personal;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.content.Context;
import java.io.InputStream;

public class MainActivity extends Activity {

        @Override
        public void onCreate(Bundle savedInstanceBundle) {
                super.onCreate(savedInstanceBundle);
                //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// WebView webView = (WebView)R.layout.main;
                //setContentView(webView);
                WebView webView = new WebView(this);
		setContentView(webView);

                // enable javascript, and also the way to communicate with it 
                webView.getSettings().setJavaScriptEnabled(true);
                webView.addJavascriptInterface(new AndroidObject(this), "Personal");
                // load HTML internal file 
                webView.loadUrl("file:///android_asset/index.html");
		// webView.loadUrl("https://contekan-2022.netlify.app");
        }

        class AndroidObject {
                Context context;

                AndroidObject(Context ctx) {
                        context = ctx;
                }

                public void showToast(String strText) {
                        Toast.makeText(context, strText, Toast.LENGTH_SHORT).show();
                }
		public String loadMarkdown(String strPath) {
		    String string = "Got an **empty** file.";
		    InputStream inputStream = null;
		    try {
			inputStream = getAssets().open("markdown/" + strPath);
			int size = inputStream.available();
			if (size != 0) {
			    byte[] buffer = new byte[size];
			    inputStream.read(buffer);
			    return new String(buffer);
			}
		    }
		    catch (Exception e) {
			return e.getMessage();
		    }
		    return string;
		}
        }

}

