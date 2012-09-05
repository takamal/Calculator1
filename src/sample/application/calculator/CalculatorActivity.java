package sample.application.calculator;



//import org.w3c.dom.Text;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import android.text.ClipboardManager;
import android.os.Bundle;
import android.R.string;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;

public class CalculatorActivity extends Activity {

	public Integer sum;
	public Integer num1 = 0;
	public Integer num2;
	public String strTemp = "";
	public String strResult = "0";
	public Integer operator = 0;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);//アクティビティかリュキュレーターのインスタンスの生成。
        readPreferences();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_calculator, menu);
        return true;
    }
    
    public void numKeyOnClick(View v) {
/*		Button button = (Button)v;
		Log.d("[buttonのtext]",	button.getText().toString());
		TextView tV = (TextView)this.findViewById(R.id.displayPanel);
		Log.d("[tVのインスタンスか確認]", "tV.text:" + tV.getText().toString());
		String sInt = tV.getText().toString();
		String clicksInt= button.getText().toString();
		if(sInt == "0"){
			tV.setText(clicksInt);
		}else
			tV.setText(sInt + clicksInt);			
*/
    	String strInKey = (String) ((Button)v).getText();
    	
    	if(strInKey.equals(".")){
    		if(strTemp.length()==0){
    			strTemp = "0.";
    		}else{
    			if(strTemp.indexOf(".") == -1){
    				strTemp = strTemp +".";
    			}
    		}
    	}else{
    		strTemp = strTemp + strInKey;
    	}
    	
    	this.showNumber(this.strTemp);
    	}	

    private void showNumber(String strNum) {
    	
    	DecimalFormat form = new DecimalFormat("#,##0");
    	String strDecimal="";
    	String strInt="";
    	String fText="";
    	
    	if (strNum.length()>0){
    		int decimalPoint = strNum.indexOf(".");
    		if(decimalPoint > -1){
    			strDecimal = strNum.substring(decimalPoint);
    			strInt = strNum.substring(0, decimalPoint);
    		}else{
    			strInt = strNum;
    		}
    		
    		fText = form.format(Double.parseDouble(strInt)) + strDecimal;
	    }else fText = "0";
		
    	((TextView)findViewById(R.id.displayPanel)).setText(fText);
    }
    
    public void functionKeyOnClick(View v){
    	switch(v.getId()){
    	case	R.id.keypadAC:
    		this.strTemp = "";
    		this.strResult = "0";
    		this.operator = 0;
    		break;
    		
    	case	R.id.keypadC:
    		this.strTemp = "";
    		break;
    	
    	case	R.id.keypadBS:
    		if(strTemp.length() == 0)return;
    		else strTemp = strTemp.substring(0,strTemp.length() -1);
    		break;
    	case 	R.id.keypadCopy:
    		ClipboardManager cm = (ClipboardManager)getSystemService(
    				CLIPBOARD_SERVICE);
    		cm.setText(((TextView)this.findViewById(R.id.displayPanel)).getText());
    		return;
    	}
    	this.showNumber(this.strTemp);
    }
    public void opratorKeyOnClick(View v){
    	
    	if(this.operator != 0){
    		if(this.strTemp.length() > 0){
    			this.strResult = this.doCalc();
    			this.showNumber(this.strResult);
    		}
    	}
    	else{
    		if(strTemp.length() > 0){
    			this.strResult = this.strTemp;
    		}
    	}
    	this.strTemp = "";
    	
    	if(v.getId() == R.id.keypadEq){
    		this.operator = 0;
    	}else{
    		this.operator = v.getId();
    	}
    }
    private String doCalc(){
    	BigDecimal bd1 = new BigDecimal(strResult);
    	BigDecimal bd2 = new BigDecimal(strTemp);
    	BigDecimal result = BigDecimal.ZERO;
    	
    	switch(operator){
    		case R.id.keypadAdd:
    			result = bd1.add(bd2);
    			break;
    		case R.id.keypadSub:
    			result = bd1.subtract(bd2);
    			break;
    		case R.id.keypadMulti:
    			result = bd1.multiply(bd2);
    			break;
    		case R.id.keypadDiv:
    			if(!bd2.equals(BigDecimal.ZERO)){
    				result = bd1.divide(bd2, 12, 3);
    			}else{
    				Toast toast = Toast.makeText(this, R.string.toast_div_by_zero,	1000);
    				toast.show();
    			}
    			break;
    	}
    	if(result.toString().indexOf(".") >= 0){
    		return result.toString().replaceAll("¥¥.0 + $|0 + $","");
    	}else{
    		return result.toString();
    	}
    }
    
    public void addKeyOnClick(View v) {
    	Button button = (Button)v;
		Log.d("[buttonのtext]",button.getText().toString());
    	TextView tV = (TextView)this.findViewById(R.id.displayPanel);
    	
    	Log.d("[addkeyがよばれたか確認]", "tv.text" +  tV.getText().toString());

    	String sum = tV.getText().toString();
		
		this.num1 = Integer.valueOf(sum);	//数字に変換する。
		tV.setText("0");
		
    }
    
    public void equalOnClick(View v) {
    	Button button = (Button)v;
    	Log.d("[equalkey呼ばれたr確認]",button.getText().toString());
		TextView tV = (TextView)this.findViewById(R.id.displayPanel);
		String sInt = tV.getText().toString();
		
		num2 = Integer.valueOf(sInt);  //数字に変換する。
		
		sInt = Integer.toString(num1 + num2);
		tV.setText(sInt);
		
    }

    public void writePreferences(){
    	SharedPreferences prefs = getSharedPreferences("CalcPrefs", MODE_PRIVATE);
    	SharedPreferences.Editor editor = prefs.edit();
    	editor.putString("strTemp",strTemp);
    	editor.putString("strResult",strResult);
    	editor.putInt("operator", operator);
    	editor.putString("strDisplay",
    			((TextView)findViewById(R.id.displayPanel)).getText().toString());
    	editor.commit();
    }
    public void readPreferences(){
    	SharedPreferences prefs = getSharedPreferences("CalcPrefs", MODE_PRIVATE);
    	strTemp = prefs.getString("strResult", "");
    	strResult = prefs.getString("strResult", "0");
    	this.operator = prefs.getInt("operator", 0);
    	((TextView)findViewById(R.id.displayPanel)).setText(prefs.getString("strDisplay","0"));
    }

	@Override
	protected void onStop() {
		super.onStop();
		writePreferences();
	}

}