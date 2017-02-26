package com.example.testing.iotest;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;
import android.webkit.WebView;
import java.io.*;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;


public class IOTest extends Activity {
	@Override
        public void onCreate(Bundle savedInstanceBundle) {
		super.onCreate(savedInstanceBundle);
                setContentView(R.layout.main);
		Button btn = (Button)findViewById(R.id.btnWrite);
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				writeToFile(view);
			}
		});
		btn = (Button)findViewById(R.id.btnRead);
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				readFromFile(view);
			}
		});
		btn = (Button)findViewById(R.id.view_as_html);
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				runHTMLIntent();
			}
		});
	}

	void writeToFile(View view) {
		TextView tv = (TextView)findViewById(R.id.textInput);
		CharSequence strTest = tv.getText();
		try {
			OutputStreamWriter os = new OutputStreamWriter(this.openFileOutput("test1.txt", MODE_PRIVATE));
			os.write(strTest.toString());
			os.close();
			Toast.makeText(this, "Written to test1.txt, inside application's folder",
					Toast.LENGTH_SHORT).show();
		}
		catch (Exception e) {
			Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
		}
	}
	
	void readFromFile(View view) {
		TextView tv = (TextView)findViewById(R.id.textOutput);
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(this.openFileInput("test1.txt")));
			String strTest = "test1.txt contains:\n";
			String str1 = "";
			while ((str1 = br.readLine()) != null) {
				strTest += str1 + "\n";
			}
			br.close();
			tv.setText(strTest);
		}
		catch (Exception e) {
			Toast.makeText(this,
					"Error reading from test1.txt from internal folder:\n" + e.getLocalizedMessage(),
					Toast.LENGTH_LONG).show();
		}
	}

	void runHTMLIntent() {
		TextView et = (TextView)findViewById(R.id.textInput);
		String strTest = et.getText().toString();
		WebView wv = new WebView(this);
		LinearLayout root = (LinearLayout)findViewById(R.id.root_layout);
		wv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		root.removeView(findViewById(R.id.textOutput));
		wv.getSettings().setJavaScriptEnabled(true);
		root.addView(wv);
		wv.loadData(strTest, "text/html", "utf-8");
	}
}
