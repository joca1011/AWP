package de.saig.activities;

import java.util.List;

import de.awp.saig.R;
import de.saig.activities.MediaOverview.MyOnItemSelectedListener;
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
		
		PodioService ps = new PodioService();
		
		List<Workshop> a = null;
		//a = ps.getWorkshops(this);
		
		//Spinner spinnerWorkshop = (Spinner) findViewById(R.id.settings_workshop_spinner);
		//ArrayAdapter  <Workshop> dataAdapter = new ArrayAdapter  <Workshop> (this, android.R.layout.simple_spinner_item,a );
		//spinnerWorkshop.setAdapter(dataAdapter);
		
	
	}
	
	
	//Neuen Workshop erstellen
	public void newWorkshop (View view) {
		Intent intent = new Intent(this, NewWorkshop.class);
	    startActivity(intent);
	}
	
	//Neues Spiel erstellen
	public void newGame (View view) {
		Intent intent = new Intent(this, NewGame.class);
	    startActivity(intent);
	}	
	
	//Neue Runde erstellen
	public void newRound (View view) {
		Intent intent = new Intent(this, NewRound.class);
	    startActivity(intent);
	}
	

	
	
	
	
	public void backToOverview (View view) {
		Intent intent = new Intent(this, Overview.class);
	    startActivity(intent);
	}
	
	
	public void saveSettingsGame (View view) {
		//TODO Funktionalität, dass hier dann auch gespeichert wird fehlt noch
		Intent intent = new Intent(this, Overview.class);
	    startActivity(intent);
	}
}
