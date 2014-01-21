package de.saig.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import de.awp.saig.R;
import de.saig.util.Constants;

public class Login extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		//Shared Preferences vorbelegen
		SharedPreferences sharedPrefs = getSharedPreferences(String.valueOf(R.string.preference_file_key), Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPrefs.edit();
		editor.putString("thisName","Peeeeed2");
		editor.putInt("currentWorkshopId",Constants.WORKSHOP_ID);		
		editor.putInt("currentGameId",Constants.GAME_ID);
		editor.putInt("currentRoundId",Constants.ROUND_NR);
		editor.putString("currentWorkshopName","");
		editor.putString("currentGameName","");
		editor.commit();
	}
	
	//Weiterleitung zu ExternalLogins
	public void signIn (View view) {
		//TODO validate login
		Intent intent = new Intent(this, ExternalLogins.class);
	    startActivity(intent);
	}
	
	//Weiterleitung zu signUp
	public void signUp (View view) {
		Intent intent = new Intent(this, Register.class);
	    startActivity(intent);
	}
}
