package com.example.testing;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MiniTest extends Activity {
	@Override
        public void onCreate(Bundle savedInstanceBundle) {
		super.onCreate(savedInstanceBundle);
                TextView textView = new TextView(this);
                textView.setText("MiniTest is a test program");
                setContentView(textView);
	}
}
