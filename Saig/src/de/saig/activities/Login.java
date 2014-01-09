package de.saig.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import de.awp.saig.R;

public class Login extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		
		  //Shared Preferences Vorbelegen
		
		SharedPreferences sharedPrefs = getSharedPreferences(String.valueOf(R.string.preference_file_key), Context.MODE_PRIVATE);
		/* SharedPreferences.Editor editor = sharedPrefs.edit();
		
		  SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this); */
		  SharedPreferences.Editor editor = sharedPrefs.edit();
		  editor.putString("thisName","Peeeeed2");
		  editor.putInt("currentWorkshopId",555);
		  editor.putInt("currentGameId",666);
		  editor.putInt("currentRoundId",777);
		  
		  editor.putString("currentWorkshopName","");
		  editor.putString("currentGameName","");
		  editor.putString("currentRoundName","");
		  
		  editor.commit();
		  //	int WorkshopId = sharedPrefs.getInt("WORKSHOP_ID",Constants.WORKSHOP_ID);
		  //	int GameId = sharedPrefs.getInt("GameId",Constants.GAME_ID);
		  //	int RoundNr = sharedPrefs.getInt("RoundNr",Constants.ROUND_NR);
		
		
		
	}
	
	public void signIn (View view) {
		//TODO validate login
		Intent intent = new Intent(this, ExternalLogins.class);
	    startActivity(intent);
	    
		
	}
	
	public void signUp (View view) {
		Intent intent = new Intent(this, Register.class);
	    startActivity(intent);
	}
	
	
}
