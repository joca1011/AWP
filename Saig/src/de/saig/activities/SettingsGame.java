package de.saig.activities;

import de.awp.saig.R;
import de.saig.podio.PodioService;
import de.saig.podio.Workshop;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SettingsGame extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings_game);
		
//		PodioService ps = new PodioService();
//		
//		Spinner spinner = findViewById(R.id.settings_workshop_spinner);
//		ArrayAdapter  <Workshop> dataAdapter = new ArrayAdapter  <Workshop> (this, android.R.layout.simple_spinner_item, ps.getWorkshops(this));
//		spinner.setAdapter(dataAdapter);
	}
	
	public void backToOverview (View view) {
		//TODO validate login
		Intent intent = new Intent(this, Overview.class);
	    startActivity(intent);
	}
}
