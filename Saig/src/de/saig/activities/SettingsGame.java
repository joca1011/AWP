package de.saig.activities;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

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
import de.awp.saig.R;
import de.saig.podio.PodioService;
import de.saig.podio.Workshop;

public class SettingsGame extends Activity{
	
	Spinner spinnerWorkshop;
	Spinner spinnerGame;
	Spinner spinnerRound;
	SharedPreferences sharedPrefs;
	SharedPreferences preferences;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings_game);
		

		
		// Dieser Teil muss in jede Activity die einen Netzwerkzugriff hat
		StrictMode.ThreadPolicy policy = new 
		StrictMode.ThreadPolicy.Builder().permitAll().build(); 
		StrictMode.setThreadPolicy(policy);
		
		
		//getSharedPreferences
		sharedPrefs = getSharedPreferences(String.valueOf(R.string.preference_file_key), Context.MODE_PRIVATE);
		
		
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
		ArrayAdapter  <Workshop> workshopAdapter = new ArrayAdapter  <Workshop> (this, android.R.layout.simple_spinner_item,a );
		spinnerWorkshop.setAdapter(workshopAdapter);
		spinnerWorkshop.setOnItemSelectedListener(new MyOnWorkshopSelectedListener());
		
		//Spinner Game		
		spinnerGame = (Spinner) findViewById(R.id.spinnerGame);
		ArrayAdapter<CharSequence> gameAdapter = ArrayAdapter.createFromResource(
				this, R.array.spinnerGame, android.R.layout.simple_spinner_item);
		gameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerGame.setAdapter(gameAdapter);
		//spinnerGame.setOnItemSelectedListener(new MyOnGameSelectedListener());
		
		//Spinner Round		
		spinnerRound = (Spinner) findViewById(R.id.spinnerRound);
		ArrayAdapter<CharSequence> roundAdapter = ArrayAdapter.createFromResource(
				this, R.array.spinnerRound, android.R.layout.simple_spinner_item);
		roundAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerRound.setAdapter(roundAdapter);
		spinnerRound.setOnItemSelectedListener(new MyOnRoundSelectedListener());
			
	
	}
	
	

	public class MyOnWorkshopSelectedListener implements OnItemSelectedListener {
		@Override
		public void onItemSelected(AdapterView<?> parent,
			View view, int pos, long id) {
			String workshopName = parent.getItemAtPosition(pos).toString();	
			
			Workshop workshop = (Workshop) parent.getItemAtPosition(pos);

			int workshopId = workshop.getId();
			//TODO: Man muss es schaffen, das hier nicht der Name des sondern die ID übergeben wird, 
			//sodass man mit dieser dann weiterarbeiten kann
			
			  preferences = getSharedPreferences(String.valueOf(R.string.preference_file_key), Context.MODE_PRIVATE);
			
			  SharedPreferences.Editor editor = preferences.edit();
			  editor.putString("currentWorkshopName",workshopName);
			  editor.putInt("currentWorkshopId",workshopId);
			  editor.commit();
			  
		}
		
		@Override
		public void onNothingSelected(AdapterView parent){
			//Tue nichts
		}
	}
	
	
	
	
	
	
	
	public class MyOnGameSelectedListener implements OnItemSelectedListener {
		
		@Override
		public void onItemSelected(AdapterView<?> parent,
			View view, int pos, long id) {
			
			/*
			 * 
			 * TODO: DAS KANN HIER GARNICHT GEHEN; WEIL WIRL HIER TEXT UND KEIN OBJEKT RAUSZIEHEN!!!!!!!!!
			 * 
			try {
			String gameName = parent.getItemAtPosition(pos).toString();	
			
			
			//preferences = PreferenceManager.getDefaultSharedPreferences(this);
			
			  preferences = getSharedPreferences(String.valueOf(R.string.preference_file_key), Context.MODE_PRIVATE);
			
			  SharedPreferences.Editor editor = preferences.edit();
			  editor.putString("thisName",gameName);
			  editor.commit();
			
			  
			} catch (Exception e) {
				// TODO Auto-generated catch block
				
				System.out.println("Hier gabs den Fehler FFF :\n"+e.getMessage());
			}
			 */ 
			//normalerweise noch .toUpper()
			//TODO: statt "editText1.setText(str);" muss man hier das aktuelle Spiel auf das ausgewählte spiel setzen
		}
		
		@Override
		public void onNothingSelected(AdapterView parent){
			//Tue nichts
		}
	}
	
	
	
	
	
	public class MyOnRoundSelectedListener implements OnItemSelectedListener {
		@Override
		public void onItemSelected(AdapterView<?> parent,
			View view, int pos, long id) {
			String roundName = parent.getItemAtPosition(pos).toString();	

			//TODO: Man muss es schaffen, das hier nicht der Name des sondern die ID übergeben wird, 
			//sodass man mit dieser dann weiterarbeiten kann
			
			  preferences = getSharedPreferences(String.valueOf(R.string.preference_file_key), Context.MODE_PRIVATE);
			
			  SharedPreferences.Editor editor = preferences.edit();
			  editor.putString("currentRoundName",roundName);
			  editor.commit();
			  
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
