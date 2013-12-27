package de.saig.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import de.awp.saig.R;


public class Overview extends Activity {

	Spinner spinnerSettings;
	EditText editText2;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_overview);
		
		
		// Dieser Teil muss in jede Activity die einen Netzwerkzugriff hat, oder von der aus eine solche Activity aufgerufen wird
		StrictMode.ThreadPolicy policy = new 
		StrictMode.ThreadPolicy.Builder().permitAll().build(); 
		StrictMode.setThreadPolicy(policy);
		
		
		editText2 = (EditText) findViewById(R.id.editText2);
		
		spinnerSettings = (Spinner) findViewById(R.id.spinnerSettings);
		  ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.spinnerSettings, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerSettings.setAdapter(adapter);
		spinnerSettings.setOnItemSelectedListener(new MyOnItemSelectedListener());


	}
	
	
	public class MyOnItemSelectedListener  implements OnItemSelectedListener  {
		
		@Override
		public void onItemSelected(AdapterView<?> parent,
			View view, int pos, long id) {
			String str = parent.getItemAtPosition(pos).toString();	//normalerweise noch .toUpper()
			
			
			if(pos==0){
				editText2.setText(str);
			}
			else if(pos==1){
				editText2.setText(str);
				showLogout (parent);
				
			}
			
			else if(pos==2){
				editText2.setText(str);	
				showExternalLogins (parent);
			}
			
			else if(pos==3){
				editText2.setText(str);		
				showSettingsGame (parent);
			}
			
			else{
				editText2.setText(str);
			}
		}
		
		@Override
		public void onNothingSelected(AdapterView parent){
			//Tue nichts
		}
	}
	
	
	
	
	public void showMedia (View view) {
		Intent intent = new Intent(this, MediaOverview.class);
	    startActivity(intent);
	}
	
	
	
	public void recordVoice (View view) {
		Intent intent = new Intent(this, AudioRecording.class);
	    startActivity(intent);
	}
	
	
	public void showLogout (View view) {
		Intent intent = new Intent(this, Logout.class);
	    startActivity(intent);
	}
	
	
	public void showExternalLogins (View view) {
		Intent intent = new Intent(this, ExternalLogins.class);
	    startActivity(intent);
	}
	
	public void showSettingsGame (View view) {
		Intent intent = new Intent(this, SettingsGame.class);
	    startActivity(intent);
	}
	
	

	
}
