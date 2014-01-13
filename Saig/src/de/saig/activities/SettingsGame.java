package de.saig.activities;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
import android.widget.TextView;
import de.awp.saig.R;
import de.saig.podio.Category;
import de.saig.podio.DataAnnotation;
import de.saig.podio.DataObject;
import de.saig.podio.PodioService;
import de.saig.podio.Workshop;
import de.saig.util.Constants;


public class SettingsGame extends Activity{
	
	Spinner spinnerWorkshop;
	Spinner spinnerGame;
	Spinner spinnerRound;
	SharedPreferences preferences;
	PodioService ps = new PodioService();
	TextView textView1;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings_game);
		
		textView1 = (TextView) findViewById(R.id.textView1);
		
		
		
		// Dieser Teil muss in jede Activity die einen Netzwerkzugriff hat
		StrictMode.ThreadPolicy policy = new 
		StrictMode.ThreadPolicy.Builder().permitAll().build(); 
		StrictMode.setThreadPolicy(policy);
		
	
		
		//Spinner Workshop
		
		
		List<Workshop> listAllWorkshops = null;
		try {
			listAllWorkshops = ps.getWorkshops(this);
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
		
		listAllWorkshops.add( 0, new Workshop(0, "bitte Workshop auswählen",new Date(),null, 0) );

		//Aktuellen Workshop bestimmen
		preferences = getSharedPreferences(String.valueOf(R.string.preference_file_key), Context.MODE_PRIVATE);
		int currentWorkshopId = preferences.getInt("currentWorkshopId",0);
		int workshopListPos = getWorkshopPositionInSpinner(listAllWorkshops, currentWorkshopId);

		
		
		spinnerWorkshop = (Spinner) findViewById(R.id.settings_workshop_spinner);
		ArrayAdapter  <Workshop> workshopAdapter = 
				new ArrayAdapter  <Workshop> (this, android.R.layout.simple_spinner_item, listAllWorkshops );
		spinnerWorkshop.setAdapter(workshopAdapter);
		spinnerWorkshop.setOnItemSelectedListener(new MyOnWorkshopSelectedListener());
		spinnerWorkshop.setSelection(workshopListPos);
		
		

		
		
		//Spinner Game	
		setSpinnerGame();
		
		
		
		
		
		
		
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
	
			
			  preferences = getSharedPreferences(String.valueOf(R.string.preference_file_key), Context.MODE_PRIVATE);
			
			  int currentWorkshopId = preferences.getInt("currentWorkshopId",0);
			  
			  SharedPreferences.Editor editor = preferences.edit();
			  editor.putString("currentWorkshopName",workshopName);
			  editor.putInt("currentWorkshopId",workshopId);
			  
			  editor.commit();
			  
			  
			  	
			  
			  
			  
			  //TODO: Hier sollte irgendwie die Seite neu geladen werden, damit damit veränderte spinner angezegit werden können,
			  // oder der spinner Worksop sollte verändert werden
			  
			  //textView1.setText(" -->"+Integer.toString(currentWorkshopId));
			  
			  //textView1.setText(" -->"+Category.getEnum(3).getId());
			  
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
			 
		     Category game = (Category) parent.getItemAtPosition(pos);
		     int gameId = game.getId();
		     
		     
		     
		     textView1.setText(" +++ sdfsdfsd ");
		     
			  preferences = getSharedPreferences(String.valueOf(R.string.preference_file_key), Context.MODE_PRIVATE);
			  
			  SharedPreferences.Editor editor = preferences.edit();

			 // editor.putString("currentGameName",gameName);
			  editor.putInt("currentGameId",gameId);
			  editor.commit();
			  
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
	
	
	
	public int  getGamePositionInSpinner(List<DataObject> listGames,int currentGameId){
		int idx = 0;
		int pos = 0;
		for (DataObject w : listGames) {
		    if(w.getId()==currentGameId){pos=idx; break;}
		    idx++;
		}
		return pos;
	}
	
	
	
	public int  getWorkshopPositionInSpinner(List<Workshop> listAllWorkshops,int currentWorkshopId){
		int idx = 0;
		int pos = 0;
		for (Workshop w : listAllWorkshops) {
		    if(w.getId()==currentWorkshopId){pos=idx; break;}
		    idx++;
		}
		return pos;
	}
	
	
	
	
	
	public void setSpinnerGame(){
		List<Category> allGames = new ArrayList<Category>(Arrays.asList(Category.values()));
		ArrayAdapter  <Category> gameAdapter = 
				new ArrayAdapter  <Category> (this, android.R.layout.simple_spinner_item, allGames);
		spinnerGame = (Spinner) findViewById(R.id.spinnerGame);
		spinnerGame.setAdapter(gameAdapter);
		/*
		spinnerGame.setOnItemSelectedListener(new MyOnGameSelectedListener());
		*/
		//spinnerGame.setSelection(gameListPos);
	}
	
	
	
	
	
	public void setSpinnerGame(int currentWorkshopId){
		//Spinner Game	
		List<DataObject> listAllGamesAndRoundsOfTheWorkshop = null;
		
		if(currentWorkshopId!=0)		//Means: If a workshop is selected
		{
			try {
				listAllGamesAndRoundsOfTheWorkshop = ps.getObjectsByWorkshopId(this,currentWorkshopId);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		//For the spinner Headline
		listAllGamesAndRoundsOfTheWorkshop.add( 0, new DataObject("bitte Auswählen") );
		
		//Aktuelles Spiel bestimmen
		preferences = getSharedPreferences(String.valueOf(R.string.preference_file_key), Context.MODE_PRIVATE);
		
		/*
		int currentGameId = preferences.getInt("currentGameId",0);
		
		int gameListPos = getGamePositionInSpinner(listAllGamesAndRoundsOfTheWorkshop, currentGameId);
		*/
		
		
		

		spinnerGame = (Spinner) findViewById(R.id.spinnerGame);
		ArrayAdapter  <DataObject> gameAdapter = 
				new ArrayAdapter  <DataObject> (this, android.R.layout.simple_spinner_item, listAllGamesAndRoundsOfTheWorkshop );
		spinnerGame.setAdapter(gameAdapter);
		spinnerGame.setOnItemSelectedListener(new MyOnGameSelectedListener());
		//spinnerGame.setSelection(gameListPos);
	}
	
	
	public void changeSpinnerGame(int currentWorkshopId){
		//Spinner Game	
		
		
		
		/*
		
		List<DataObject> listAllGamesAndRoundsOfTheW = null;
		
		if(currentWorkshopId!=0)		//Means: If a workshop is selected
		{
			try {
				listAllGamesAndRoundsOfTheW = ps.getObjectsByWorkshopId(this,currentWorkshopId);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//For the spinner Headline
	
		try {
		listAllGamesAndRoundsOfTheW.add( 0, new DataObject("bitte Auswählen") );
		} 
		catch (Exception e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		spinnerGame = (Spinner) findViewById(R.id.spinnerGame); 
		
		
		ArrayAdapter  <DataObject> gameAdapter = 
				new ArrayAdapter  <DataObject> (this, android.R.layout.simple_spinner_item, listAllGamesAndRoundsOfTheW );
		
		
		gameAdapter.notifyDataSetChanged();
		
		
		
		gameAdapter = (ArrayAdapter) spinnerGame.getAdapter();
		gameAdapter.notifyDataSetChanged();
		
		
		//spinnerGame.setAdapter(gameAdapter);
		
		/*
		spinnerGame.setOnItemSelectedListener(new MyOnGameSelectedListener());
		*/
		
	
	
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
	
	public void reload(View view) {
		//TODO Funktionalität, dass hier dann auch gespeichert wird fehlt noch
		Intent intent = new Intent(this, SettingsGame.class);
	    startActivity(intent);
	}
	
	
}
