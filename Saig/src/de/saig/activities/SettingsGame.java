package de.saig.activities;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import de.awp.saig.R;
import de.saig.podio.PodioService;
import de.saig.podio.Workshop;

public class SettingsGame extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings_game);
		
		
		// Dieser Teil muss in jede Activity die einen Netzwerkzugriff hat
		StrictMode.ThreadPolicy policy = new 
		StrictMode.ThreadPolicy.Builder().permitAll().build(); 
		StrictMode.setThreadPolicy(policy);
		
		
		PodioService ps = new PodioService();
		
		List<Workshop> a = null;
		try {
			a = ps.getWorkshops(this);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Spinner spinnerWorkshop = (Spinner) findViewById(R.id.settings_workshop_spinner);
		ArrayAdapter  <Workshop> dataAdapter = new ArrayAdapter  <Workshop> (this, android.R.layout.simple_spinner_item,a );
		spinnerWorkshop.setAdapter(dataAdapter);
		
	
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
