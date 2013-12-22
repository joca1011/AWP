package de.saig.activities;

import de.awp.saig.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class NewWorkshop extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings_game);
	}
	
	
	//Ohne speichern zurück
	public void backToSettings (View view) {
		Intent intent = new Intent(this, SettingsGame.class);
	    startActivity(intent);
	}
	
	//Neues Spiel eintragen
	public void saveNewGame (View view) {
		//TODO Spiel speichern
		Intent intent = new Intent(this, SettingsGame.class);
	    startActivity(intent);
	}	
	
}
