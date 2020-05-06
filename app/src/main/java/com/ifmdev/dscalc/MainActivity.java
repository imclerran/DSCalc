package com.ifmdev.dscalc;

import android.app.*;
import android.graphics.*;
import android.os.*;
import android.view.*;
import android.widget.*;


public class MainActivity extends Activity 
{
	EditText txtBodyHook;
	EditText txtCoverHook;
	EditText txtSeamWidth;
	
	TextView lblOverlap;
	TextView lblBHB;
	
	LinearLayout hllOverlap;
	LinearLayout hllBHB;
	
	Button btnCalculate;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		txtBodyHook = (EditText)findViewById(R.id.txtBodyHook);
		txtCoverHook = (EditText)findViewById(R.id.txtCoverHook);
		txtSeamWidth = (EditText)findViewById(R.id.txtSeamWidth);
		
		lblOverlap = (TextView)findViewById(R.id.lblOverlap);
		lblBHB = (TextView)findViewById(R.id.lblBHB);
		
		hllOverlap = (LinearLayout)findViewById(R.id.hllOverlap);
		hllBHB = (LinearLayout)findViewById(R.id.hllBHB);
		
		btnCalculate = (Button)findViewById(R.id.btnCalculate);
		
		txtSeamWidth.clearFocus();
    }
	
	public void btnCalculateOnClick(View view) {
		String bodyHookStr = txtBodyHook.getText().toString();
		String coverHookStr = txtCoverHook.getText().toString();
		String seamWidthStr = txtSeamWidth.getText().toString();
		
		float bodyHook = 0;
		float coverHook = 0;
		float seamWidth = 0;
		
		if(bodyHookStr.length() > 0) {
			bodyHook= Integer.parseInt(bodyHookStr);
		}
		
		if(coverHookStr.length() > 0) {
			coverHook	= Integer.parseInt(coverHookStr);
		}
		
		if(seamWidthStr.length() > 0) {
			seamWidth = Integer.parseInt(seamWidthStr);
		}
		
		
		float bodyThickness = 11;
		float endThickness = 9;
		
		if(0 != seamWidth && 0 != bodyHook && 0 != coverHook) {
			int overlap = (int)(bodyHook + coverHook + bodyThickness - seamWidth);
			float bhb = (bodyHook - (1.1f * bodyThickness))/(seamWidth - (1.1f *((2*endThickness) + bodyThickness)));
			int bhbInt = Math.round((bhb * 100));
		
			
			if(overlap >= 40)
				hllOverlap.setBackgroundColor(Color.GREEN);
			else
				hllOverlap.setBackgroundColor(Color.RED);
				
			if(bhbInt >= 75 && bhbInt <= 85)
				hllBHB.setBackgroundColor(Color.GREEN);
			else if(bhbInt >= 65 && bhbInt <= 95)
				hllBHB.setBackgroundColor(Color.YELLOW);
			else
				hllBHB.setBackgroundColor(Color.RED);
			
			lblOverlap.setText(String.valueOf(overlap));
			lblBHB.setText(String.valueOf(bhbInt));
		}
		else {
			// toast
			Toast.makeText(this, "INCOMPLETE USER INPUT", Toast.LENGTH_SHORT).show();
		}
	}
	
	public void btnClearOnClick(View view) {
		txtBodyHook.setText("");
		txtCoverHook.setText("");
		txtSeamWidth.setText("");
		lblOverlap.setText("");
		lblBHB.setText("");
		
		hllOverlap.setBackgroundColor(Color.parseColor("#ECECEC"));
		hllBHB.setBackgroundColor(Color.parseColor("#ECECEC"));
		
		txtSeamWidth.requestFocus();
	}
}
