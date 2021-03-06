package de.saig.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import de.awp.saig.R;


public class Overview extends Activity {

	Spinner spinnerSettings;
	TextView textviewHeadline;
	SharedPreferences sharedPrefs;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_overview);
		
		
		// Dieser Teil muss in jede Activity die einen Netzwerkzugriff hat, oder von der aus eine solche Activity aufgerufen wird
		StrictMode.ThreadPolicy policy = new 
		StrictMode.ThreadPolicy.Builder().permitAll().build(); 
		StrictMode.setThreadPolicy(policy);
		
		textviewHeadline = (TextView) findViewById(R.id.textview_headline);
		
		spinnerSettings = (Spinner) findViewById(R.id.spinnerSettings);
		  ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.spinnerSettings, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerSettings.setAdapter(adapter);
		spinnerSettings.setOnItemSelectedListener(new MyOnItemSelectedListener());



		
		
		//Auslesen der Shared Preferences um oben anzuzeigen welcher Workshop gerade ausgewählt ist
		  SharedPreferences preferences = getSharedPreferences(String.valueOf(R.string.preference_file_key), Context.MODE_PRIVATE);
		  int currentWorkshopId = preferences.getInt("currentWorkshopId",0);
		  int currentRoundId = preferences.getInt("currentRoundId",0);
		  
		  String currentWorkshopName = preferences.getString("currentWorkshopName","");
		  String currentGameName = preferences.getString("currentGameName","");
		  
		  
		  String WorkshopDataString;
		  
		  if(currentWorkshopId==666){ WorkshopDataString = "kein Spiel ausgewählt";}
		  else{WorkshopDataString =  	 	 "  Workshop: "+currentWorkshopName
											+"\n  Game : "+currentGameName
											+"\n  Round: "+currentRoundId;}
		  
		  textviewHeadline.setText(WorkshopDataString);
		  
		
	}
	
	public class MyOnItemSelectedListener  implements OnItemSelectedListener  {
		
		@Override
		public void onItemSelected(AdapterView<?> parent,
			View view, int pos, long id) {
			String str = parent.getItemAtPosition(pos).toString();	//normalerweise noch .toUpper()
			
			
			if(pos==0){
			}
			else if(pos==1){
				textviewHeadline.setText(str);
				showLogout (parent);
				
			}
			
			else if(pos==2){
				textviewHeadline.setText(str);	
				showExternalLogins (parent);
			}
			
			else if(pos==3){
				textviewHeadline.setText(str);		
				showSettingsGame (parent);
			}
			
			else{
				textviewHeadline.setText(str);
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
	
	public void newPhoto (View view) {	}
	
	
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
