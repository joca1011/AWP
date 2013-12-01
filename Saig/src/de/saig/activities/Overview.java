package de.saig.activities;

import de.awp.saig.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class Overview extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_overview);
		
		
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
