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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import de.awp.saig.R;
import de.saig.activities.MediaOverview.MyOnItemSelectedListener;
import de.saig.podio.PodioService;
import de.saig.podio.Workshop;

public class SettingsGame extends Activity{
	
	Spinner spinnerWorkshop;
	Spinner spinnerGame;
	Spinner spinnerRound;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings_game);
		
		
		// Dieser Teil muss in jede Activity die einen Netzwerkzugriff hat
		StrictMode.ThreadPolicy policy = new 
		StrictMode.ThreadPolicy.Builder().permitAll().build(); 
		StrictMode.setThreadPolicy(policy);
		
		
		
		//Spinner Workshop
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
		
		spinnerWorkshop = (Spinner) findViewById(R.id.settings_workshop_spinner);
		ArrayAdapter  <Workshop> dataAdapter = new ArrayAdapter  <Workshop> (this, android.R.layout.simple_spinner_item,a );
		spinnerWorkshop.setAdapter(dataAdapter);
		
	
		//Spinner Game		
		spinnerGame = (Spinner) findViewById(R.id.spinnerGame);
		  ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.spinnerGame, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerGame.setAdapter(adapter);
		spinnerGame.setOnItemSelectedListener(new MyOnItemSelectedListener());
		
		
	}
	
	
	public class MyOnItemSelectedListener implements OnItemSelectedListener {
		
		@Override
		public void onItemSelected(AdapterView<?> parent,
			View view, int pos, long id) {
			String str = parent.getItemAtPosition(pos).toString();	//normalerweise noch .toUpper()
			//TODO: statt "editText1.setText(str);" muss man hier das aktuelle Spiel auf das ausgewählte spiel setzen
		}
		
		@Override
		public void onNothingSelected(AdapterView parent){
			//Tue nichts
		}
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
