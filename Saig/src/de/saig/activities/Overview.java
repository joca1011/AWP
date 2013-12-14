package de.saig.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import de.awp.saig.R;


public class Overview extends Activity {

	Spinner spinnerSettings;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_overview);
		
		spinnerSettings = (Spinner) findViewById(R.id.spinnerSettings);
		  ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.spinnerSettings, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerSettings.setAdapter(adapter);
		spinnerSettings.setOnItemSelectedListener(new MyOnItemSelectedListener());

	}
	
	
	public class MyOnItemSelectedListener implements OnItemSelectedListener {
		
		@Override
		public void onItemSelected(AdapterView<?> parent,
			View view, int pos, long id) {
			String str = parent.getItemAtPosition(pos).toString();	//normalerweise noch .toUpper()
			System.out.println(str);
		}
		
		@Override
		public void onNothingSelected(AdapterView parent){
			//Tue nichts
		}
	}
	
	
	
	
	public void showMedia (View view) {
		//TODO validate login
		Intent intent = new Intent(this, MediaOverview.class);
	    startActivity(intent);
	  
	    
	}
	
	public void recordVoice (View view) {
		//TODO validate login
		Intent intent = new Intent(this, AudioRecording.class);
	    startActivity(intent);
	    
		
	}
	
}
