package sample.application.calculator;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipboardManager;
import android.widget.TextView;

@SuppressLint("NewApi")
public class Copy implements FunctionLogic {

	@SuppressWarnings("deprecation")
	@Override
	public void doFunction(CalculatorActivity ca) {
		ClipboardManager cm = (ClipboardManager) ca.getSystemService(Activity.CLIPBOARD_SERVICE);
		cm.setText(((TextView)ca.findViewById(R.id.displayPanel)).getText());
	}

}