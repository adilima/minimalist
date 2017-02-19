package es.hol.xaverius2017.personal;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
	@Override
        public void onCreate(Bundle savedInstanceBundle) {
		super.onCreate(savedInstanceBundle);
                TextView textView = new TextView(this);
                textView.setText("MiniTest is a test program");
                setContentView(textView);
	}
}
